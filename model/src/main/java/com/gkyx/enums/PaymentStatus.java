package com.gkyx.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PaymentStatus {
    UNPAID(1,"支付中"),
    PAID(2,"已支付");
    //REFUND(-1,"已退款");

    @EnumValue
    private final Integer code ;
    private final String comment ;

    PaymentStatus(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }

}
