package com.gkyx.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum CouponStatus {
    NOT_USED(1,"未使用"),
    USED(2,"已使用");

    @EnumValue
    private final Integer code ;
    private final String comment ;

    CouponStatus(Integer code, String comment ){
        this.code=code;
        this.comment=comment;
    }
}