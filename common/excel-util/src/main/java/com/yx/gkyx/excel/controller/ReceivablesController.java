package com.yx.gkyx.excel.controller;

import com.google.common.collect.Lists;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.yx.gkyx.excel.annotation.RequestExcel;
import com.yx.gkyx.excel.model.excel.Receivables;
import com.yx.gkyx.excel.service.ExcelImportService;
import io.swagger.annotations.Api;
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
 * 应收单
 */
@RestController
@Api(tags = "应收单")
@RequestMapping(value = "/receives", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ReceivablesController {

    ExcelImportService excelImportService;

    @ResponseExcel
    @ApiOperation("应收单Excel模板")
    @GetMapping(value = "/template", produces = APPLICATION_OCTET_STREAM_VALUE)
    public List<Receivables> excelTemplate() {
        return Lists.newArrayList(new Receivables());
    }

    @ApiOperation(value = "应收单Excel导入", notes = "hff")
    @PostMapping(value = "/import")
    public void importData(@RequestExcel List<Receivables> data, BindingResult result) {
        excelImportService.saveError(data, result);
        excelImportService.importData(data, Receivables.class);
    }

}
