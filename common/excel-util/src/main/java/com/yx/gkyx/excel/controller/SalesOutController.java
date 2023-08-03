package com.yx.gkyx.excel.controller;

import com.google.common.collect.Lists;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.yx.gkyx.excel.annotation.RequestExcel;
import com.yx.gkyx.excel.model.excel.SalesOut;
import com.yx.gkyx.excel.service.ExcelImportService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

/**
 * 销售出库单
 */
@RestController
@ApiOperation("销售出库单")
@RequestMapping(value = "/sales-out", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class SalesOutController {

    ExcelImportService excelImportService;

    @ResponseExcel
    @ApiOperation("销售出库单Excel模板")
    @GetMapping(value = "/template", produces = APPLICATION_OCTET_STREAM_VALUE)
    public List<SalesOut> excelTemplate() {
        return Lists.newArrayList(new SalesOut());
    }

    @ApiOperation("销售出库单导入")
    @PostMapping(value = "/import")
    public void importData(@RequestExcel List<SalesOut> data, BindingResult result) {
        excelImportService.saveError(data, result);
        excelImportService.importData(data, SalesOut.class);
    }

}
