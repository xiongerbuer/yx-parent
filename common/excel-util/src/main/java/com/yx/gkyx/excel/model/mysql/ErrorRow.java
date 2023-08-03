package com.yx.gkyx.excel.model.mysql;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ErrorRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelIgnore
    private Long errorId;

    /**
     * @see ImportLog#getLogId()
     */
    @ExcelIgnore
    private Long logId;

    @ExcelProperty("行号")
    private Long rowId;

    @ExcelProperty("错误信息")
    private String errorMsg;

}
