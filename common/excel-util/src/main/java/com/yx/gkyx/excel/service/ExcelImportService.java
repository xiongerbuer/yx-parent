package com.yx.gkyx.excel.service;

import cn.hutool.core.util.ReflectUtil;
import com.yx.gkyx.common.constant.DateTimeConstants;
import com.google.common.collect.Lists;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.yx.gkyx.common.http.ContextContainer;
import com.yx.gkyx.common.http.UserContext;
import com.yx.gkyx.common.utils.StreamUtils;
import com.yx.gkyx.excel.annotation.Schema;
import com.yx.gkyx.excel.annotation.Table;
import com.yx.gkyx.excel.dao.ErrorRowDao;
import com.yx.gkyx.excel.dao.ImportLogDao;
import com.yx.gkyx.excel.dao.SpecificationMappingDao;
import com.yx.gkyx.excel.dao.clickhouse.ImportExcelDataDao;
import com.yx.gkyx.excel.model.excel.*;
import com.yx.gkyx.excel.model.mysql.ErrorRow;
import com.yx.gkyx.excel.model.mysql.ExcelImportStatus;
import com.yx.gkyx.excel.model.mysql.ImportLog;
import com.yx.gkyx.excel.model.mysql.SpecificationMapping;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class ExcelImportService {

    ConcurrentMap<Class<?>, Schema> schemaCache = new ConcurrentHashMap<>();

    ThreadLocal<ImportLog> importLogThreadLocal = new ThreadLocal<>();

    boolean pmdKnownBroken;

//    @Autowired
//    MinioProperties minioProperties;

    @Autowired
    @Qualifier("clickhouseDataSource")
    private DataSource dataSource;

//    @Autowired
//    MinioService minioService;

    @Autowired
    ImportLogDao importLogDao;

    @Autowired
    ErrorRowDao errorRowDao;

    @Autowired
    ImportExcelDataDao importExcelDataDao;

    @Autowired
    SpecificationMappingDao specificationMappingDao;

    @SneakyThrows
    public <T> void importData(List<T> dataRows, Class<T> elementClass) {
        Schema schema = schemaCache.computeIfAbsent(elementClass, this::computeSchema);
        try (Connection c = dataSource.getConnection()) { // clickhouse默认的粒度是8k
            Lists.partition(dataRows, 1024 * 8).forEach(batch -> {
                //deleteDuplicate(c, batch, schema);
                insertBatch(c, batch, schema);
            });
        }
    }

    @SneakyThrows
    private <T> void deleteDuplicate(Connection conn, List<T> batch, Schema schema) {
        try (PreparedStatement ps = conn.prepareStatement(schema.deleteSql())) {
            List<Schema.ColumnMetadata> primaryKeys = schema.getColumns().stream()
                    .filter(Schema.ColumnMetadata::isPrimaryKey)
                    .sorted(Comparator.comparing(Schema.ColumnMetadata::getKeyOrder))
                    .collect(Collectors.toList());
            for (T row : batch) {
                Object[] args = schema.getDeleteParamArray(row, primaryKeys);
                fillStatement(ps, args);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @SneakyThrows
    private <T> void insertBatch(Connection conn, List<T> batch, Schema schema) {
        UserContext context = ContextContainer.getContext();
        try (PreparedStatement ps = conn.prepareStatement(schema.insertSql())) {
            for (T row : batch) {
                if (row instanceof ExcelModel) {
                    ExcelModel model = (ExcelModel) row;
                    model.setTenantId(context.getCurrentTenantId());
                    model.setCreateBy(context.getCurrentUserName());
                    model.setModifyBy(context.getCurrentUserName());
                    model.setCreateAt(LocalDateTime.now());
                    model.setModifyAt(LocalDateTime.now());
                }
                // 获取规格型号映射
                List<SpecificationMapping> mapping = specificationMappingDao.findSpecificationMappingByTenantId(context.getCurrentTenantId());
                Map<String, SpecificationMapping> mapingMap = new HashMap<>();
                StreamUtils.mapToList(mapping, x -> mapingMap.put(x.getMaterialName() + x.getSpecification(), x));
                if (row instanceof SaleOrder) {
                    SaleOrder order = (SaleOrder) row;
                    String key  = order.getMaterialName() + order.getSpecification();
                    if (mapingMap.containsKey(key)) {
                        order.setMainSpecification(mapingMap.get(key).getMainSpecification());
                    }
                }
                if (row instanceof SalesOut) {
                    SalesOut out = (SalesOut) row;
                    String key  = out.getMaterialName() + out.getSpecification();
                    if (mapingMap.containsKey(key)) {
                        out.setMainSpecification(mapingMap.get(key).getMainSpecification());
                    }
                }
                if (row instanceof Waybill) {
                    Waybill waybill = (Waybill) row;
                    String key  = waybill.getMaterialName() + waybill.getSpecification();
                    if (mapingMap.containsKey(key)) {
                        waybill.setMainSpecification(mapingMap.get(key).getMainSpecification());
                    }
                }
                if (row instanceof Receivables) {
                    Receivables receivables = (Receivables) row;
                    String key  = receivables.getMaterialName() + receivables.getSpecification();
                    if (mapingMap.containsKey(key)) {
                        receivables.setMainSpecification(mapingMap.get(key).getMainSpecification());
                    }
                }
                Object[] args = schema.getInsertParamArray(row);
                fillStatement(ps, args);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private <T> Schema computeSchema(Class<T> elementClass) {
        boolean annotationPresent = elementClass.isAnnotationPresent(Table.class);
        String tableName;
        if (annotationPresent) {
            tableName = elementClass.getAnnotation(Table.class).name();
        } else {
            tableName = elementClass.getSimpleName();
        }
        Field[] fields = ReflectUtil.getFields(elementClass);
        List<Schema.ColumnMetadata> columns = Arrays.stream(fields)
                .peek(f -> f.setAccessible(true))
                .map(Schema.ColumnMetadata::new)
                .collect(Collectors.toList());
        return new Schema(tableName, columns);
    }

    @SneakyThrows
    private void fillStatement(PreparedStatement stmt, Object... params) {
        ParameterMetaData pmd = null;
        if (!pmdKnownBroken) {
            try {
                pmd = stmt.getParameterMetaData();
                if (pmd == null) { // 数据源不支持
                    pmdKnownBroken = true;
                } else {
                    int stmtCount = pmd.getParameterCount();
                    int paramsCount = params == null ? 0 : params.length;

                    if (stmtCount != paramsCount) {
                        throw new SQLException("Wrong number of parameters: expected "
                                + stmtCount + ", was given " + paramsCount);
                    }
                }
            } catch (SQLFeatureNotSupportedException ex) {
                pmdKnownBroken = true;
            }
        }

        if (params == null) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null && !Objects.equals(params[i], "null")) {
                if (params[i] instanceof LocalDateTime) {
                    stmt.setObject(i + 1, ((LocalDateTime) params[i]).format(DateTimeConstants.DATE_TIME));
                } else if (params[i] instanceof LocalDate) {
                    stmt.setObject(i + 1, ((LocalDate) params[i]).format(DateTimeConstants.DATE));
                } else if (params[i] instanceof BigDecimal) {
                    stmt.setBigDecimal(i + 1, (BigDecimal) params[i]);
                } else {
                    stmt.setObject(i + 1, params[i]);
                }
            } else {
                // 根据元数据推断null值的类型
                int sqlType = Types.VARCHAR;
                if (!pmdKnownBroken && pmd != null) {
                    try {
                        sqlType = pmd.getParameterType(i + 1);
                    } catch (SQLException e) {
                        pmdKnownBroken = true;
                    }
                }
                stmt.setNull(i + 1, sqlType);
            }
        }
    }

    @SneakyThrows
    public InputStream uploadFile(InputStream is, String originFileName, Class<?> clazz) {
        LocalDateTime now = LocalDateTime.now();
        String dir = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String tempFileName = now.format(DateTimeFormatter.ofPattern("HHmmss.SSS"));
        Path tempFile = Files.createTempFile(tempFileName, ".xlsx");
        Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
        // 上传MinIO
//        String minioFileName = originFileName + UUID.randomUUID() + ".xlsx";
//        Path minioPath = Paths.get("gst-excel", dir, minioFileName);
//        minioService.upload(minioPath, Files.newInputStream(tempFile));

        // 生成导入日志
        UserContext context = ContextContainer.getContext();
        Table annotation = clazz.getAnnotation(Table.class);
        String modelClass = annotation == null ? clazz.getCanonicalName() : annotation.name();
        ImportLog importLog = new ImportLog();
        importLog.setName(originFileName);
        importLog.setStatus(ExcelImportStatus.RUNNING);
        importLog.setUserId(context.getCurrentUserId());
        importLog.setTenantId(context.getCurrentTenantId());
        importLog.setDeleted(false);
//        importLog.setFileUrl(minioProperties.getUrl() + "/" + minioProperties.getBucket() + "/" + minioPath);
        importLog.setModelType(modelClass);
        importLog.setCreateBy(String.valueOf(context.getCurrentUserId()));
        importLog.setModifyBy(String.valueOf(context.getCurrentUserId()));
        importLog = importLogDao.save(importLog);
        importLogThreadLocal.set(importLog);
        return Files.newInputStream(tempFile);
    }

    public <T> void saveError(List<T> data, BindingResult result) {
        ImportLog importLog = importLogThreadLocal.get();
        if (importLog == null) {
            importLogThreadLocal.remove();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "导入失败");
        }
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) result.getTarget();
        if (CollectionUtils.isEmpty(errorMessageList)) {
            importLog.setInfo("上传成功");
            importLog.setStatus(ExcelImportStatus.SUCCESS);
        } else {
            importLog.setStatus(ExcelImportStatus.FAIL);
//            importLog.setErrorFileUrl("/gst/sale-excel/error-rows/" + importLog.getLogId());
            importLog.setInfo(String.format("成功%s条，失败%s", data.size(), errorMessageList.size()));
            List<ErrorRow> errorRows = errorMessageList.stream().map(m -> {
                ErrorRow row = new ErrorRow();
                row.setLogId(importLog.getLogId());
                row.setRowId(m.getLineNum());
                row.setErrorMsg(String.join("；", m.getErrors()));
                return row;
            }).collect(Collectors.toList());
            errorRowDao.saveAll(errorRows);
        }
        importLogDao.save(importLog);
        importLogThreadLocal.remove();
    }

    @SneakyThrows
    public Page<?> queryImportData(UserContext userContext, String dataTable,
                                   LocalDateTime createStart, LocalDateTime createEnd,
                                   Pageable pageable) {
        List<Map<String, String>> rows = importExcelDataDao.importExcelData(
                userContext.getCurrentTenantId(),
                dataTable, createStart, createEnd,
                pageable.getPageSize(), pageable.getOffset()
        );
        long count = importExcelDataDao.countByRange(userContext.getCurrentTenantId(),
                dataTable, createStart, createEnd);
        return new PageImpl<>(rows, pageable, count);
    }

}
