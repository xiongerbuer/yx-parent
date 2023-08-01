package com.yx.ssyx.excel.controller;

import com.google.common.collect.Lists;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.yx.ssyx.excel.annotation.RequestExcel;
import com.yx.ssyx.excel.model.excel.SaleOrder;
import com.yx.ssyx.excel.service.ExcelImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * 销售订单
 */
@Slf4j
@RestController
@Api(tags = {"销售订单接口"})
@RequestMapping(value = "/sale-order", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class SaleOrderController {

    ExcelImportService excelImportService;

    @ResponseExcel
    @ApiOperation("销售订单Excel模板")
    @GetMapping(value = "/template", produces = APPLICATION_OCTET_STREAM_VALUE)
    public List<SaleOrder> excelTemplate() {
        return Lists.newArrayList(new SaleOrder());
    }

    @ApiOperation(value = "导入销售订单")
    @PostMapping(value = "/import")
    public void importData(@RequestExcel List<SaleOrder> data, BindingResult result) {
        excelImportService.saveError(data, result);
        excelImportService.importData(data, SaleOrder.class);
    }

}
