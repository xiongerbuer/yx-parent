package com.yx.ssyx.excel.controller;

import com.google.common.collect.Lists;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.yx.ssyx.excel.annotation.RequestExcel;
import com.yx.ssyx.excel.model.excel.Logistics;
import com.yx.ssyx.excel.service.ExcelImportService;
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


@Api(tags = "物流费用单")
@RestController
@RequestMapping(value = "/logistics", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LogisticsController {

    ExcelImportService excelImportService;

    @ResponseExcel
    @ApiOperation("物流费用单Excel模板")
    @GetMapping(value = "/template", produces = APPLICATION_OCTET_STREAM_VALUE)
    public List<Logistics> excelTemplate() {
        return Lists.newArrayList(new Logistics());
    }

    @ApiOperation("物流费用单Excel导入")
    @PostMapping(value = "/import")
    public void importData(@RequestExcel List<Logistics> data,
                           BindingResult result) {
        excelImportService.saveError(data, result);
        excelImportService.importData(data, Logistics.class);
    }

}
