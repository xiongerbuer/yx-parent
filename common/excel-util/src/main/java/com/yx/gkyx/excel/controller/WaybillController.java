package com.yx.gkyx.excel.controller;

import com.google.common.collect.Lists;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.yx.gkyx.excel.annotation.RequestExcel;
import com.yx.gkyx.excel.model.excel.Waybill;
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
 * 发运单
 */
@RestController
@ApiOperation("发运单")
@RequestMapping(value = "/waybill", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class WaybillController {

    ExcelImportService excelImportService;

    @ResponseExcel
    @ApiOperation("发运单Excel模板")
    @GetMapping(value = "/template", produces = APPLICATION_OCTET_STREAM_VALUE)
    public List<Waybill> excelTemplate() {
        return Lists.newArrayList(new Waybill());
    }

    @ApiOperation("发运单导入")
    @PostMapping(value = "/import")
    public void importData(@RequestExcel List<Waybill> data, BindingResult result) {
        excelImportService.saveError(data, result);
        excelImportService.importData(data, Waybill.class);
    }

}
