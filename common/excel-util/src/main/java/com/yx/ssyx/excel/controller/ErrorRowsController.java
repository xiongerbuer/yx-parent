package com.yx.ssyx.excel.controller;

import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.yx.ssyx.excel.dao.ErrorRowDao;
import com.yx.ssyx.excel.model.mysql.ErrorRow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "错误报告")
@RequestMapping("/error-rows")
public class ErrorRowsController {

    @Autowired
    ErrorRowDao errorRowDao;

    @ResponseExcel(name = "错误报告.xlsx")
    @GetMapping("/{logId}")
    @ApiOperation(value = "错误报告导出", notes = "hff", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    List<ErrorRow> logErrorRows(@PathVariable Long logId) {
        return errorRowDao.findByLogId(logId);
    }

}
