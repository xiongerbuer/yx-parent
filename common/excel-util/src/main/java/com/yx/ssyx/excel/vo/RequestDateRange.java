package com.yx.ssyx.excel.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Validated
public class RequestDateRange {

    @NotNull(message = "起始时间不能为空")
    private LocalDateTime createStart;
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime createEnd;

}
