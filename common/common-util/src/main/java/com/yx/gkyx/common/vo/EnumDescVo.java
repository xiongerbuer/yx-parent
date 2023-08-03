package com.yx.gkyx.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "系统枚举")
public class EnumDescVo {

    @Schema(description = "枚举类的全限定类名")
    private String type;

    @Schema(description = "枚举类的枚举值")
    private String value;

    @Schema(description = "要展示给前端用户的文本信息")
    private String text;

}
