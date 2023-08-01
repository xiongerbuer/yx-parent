package com.yx.ssyx.excel.controller;


import com.yx.ssyx.excel.dao.ImportLogDao;
import com.yx.ssyx.excel.model.mysql.ImportLog;
import com.yx.ssyx.excel.service.ExcelImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "导入日志")
@RestController
@RequestMapping("/import-log")
public class ImportLogController {

    @Autowired
    Map<String, Class<?>> excelModels;

    @Autowired
    ImportLogDao importLogDao;

    @Autowired
    ExcelImportService excelImportService;

    @GetMapping("/types")
    @ApiOperation(value = "Excel日志类型")
    public Set<String> types() {
        return excelModels.keySet();
    }

    @GetMapping
    @ApiOperation(value = "Excel导入日志")
    public Page<ImportLog> queryPage(@CurrentUser UserContext userContext,
                                     ImportLogQuery query,
                                     @PageableDefault(sort = "createAt", direction = DESC) Pageable pageable) {
        query.setTenantId(userContext.getCurrentTenantId());
        return importLogDao.findByQuery(query, pageable);
    }

    @GetMapping("/{dataTable}")
    @ApiOperation(value = "查询数据列表")
    public Page<?> queryDataList(@CurrentUser UserContext userContext,
                                 @PathVariable String dataTable,
                                 @PageableDefault Pageable pageable,
                                 @Validated RequestDateRange dateRange) {
//        if (!excelModels.containsKey(dataTable)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "不存在");
//        }
        return excelImportService.queryImportData(userContext, dataTable,
                dateRange.getCreateStart(), dateRange.getCreateEnd(),
                pageable);
    }

}
