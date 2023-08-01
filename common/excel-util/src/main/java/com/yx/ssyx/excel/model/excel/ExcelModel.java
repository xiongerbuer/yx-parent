package com.yx.ssyx.excel.model.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExcelModel {

    @ExcelIgnore
    private Long tenantId;
    @ExcelIgnore
    private String modifyBy;
    @ExcelIgnore
    private String createBy;
    @ExcelIgnore
    private LocalDateTime modifyAt;
    @ExcelIgnore
    private LocalDateTime createAt;
    @ExcelIgnore
    private String mainSpecification;

}
