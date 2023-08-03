package com.yx.gkyx.excel.dao;

import com.yx.gkyx.common.jpa.BaseJpaRepository;
import com.yx.gkyx.excel.model.mysql.ExcelImportStatus;
import com.yx.gkyx.excel.model.mysql.ImportLog;
//import com.yx.ssyx.excel.model.mysql.ImportLog_;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ImportLogDao extends BaseJpaRepository<ImportLog, Long> {

    default Page<ImportLog> findByQuery(ImportLogQuery query, Pageable pageable) {
//        return findAll(Specification.where(BaseJpaRepository.like(ImportLog_.name, query.name))
//                .and(eq(ImportLog_.modelType, query.modelType))
//                .and(eq(ImportLog_.status, query.status))
//                .and(eq(ImportLog_.userId, query.userId))
//                .and(eq(ImportLog_.tenantId, query.tenantId)), pageable);
        return null;
    }

    @Data
    @Schema(description = "Excel导入查询")
    class ImportLogQuery {
        @ApiParam(value = "类型")
        String modelType;
        @ApiParam(value = "名称")
        String name;
        @ApiParam(value = "状态")
        ExcelImportStatus status;
        @ApiParam(value = "用户ID")
        Long userId;
        @ApiParam(value = "租户ID")
        Long tenantId;
    }

}
