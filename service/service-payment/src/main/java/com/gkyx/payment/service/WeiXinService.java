package com.gkyx.payment.service;

import java.util.Map;

public interface WeiXinService {

    //调用微信支付系统生成预付单
    Map<String, String> createJsapi(String orderNo);

    //1 调用微信支付系统接口查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);
}
