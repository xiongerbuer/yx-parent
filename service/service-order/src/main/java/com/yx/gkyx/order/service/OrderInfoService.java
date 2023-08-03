package com.yx.gkyx.order.service;

import com.yx.gkyx.model.order.OrderInfo;
import com.yx.gkyx.vo.order.OrderConfirmVo;
import com.yx.gkyx.vo.order.OrderSubmitVo;
import com.yx.gkyx.vo.order.OrderUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 */
public interface OrderInfoService extends IService<OrderInfo> {

    //确认订单
    OrderConfirmVo confirmOrder();

    //生成订单
    Long submitOrder(OrderSubmitVo orderParamVo);

    //订单详情
    OrderInfo getOrderInfoById(Long orderId);

    //根据orderNo查询订单信息
    OrderInfo getOrderInfoByOrderNo(String orderNo);

    //订单支付成功，更新订单状态，扣减库存
    void orderPay(String orderNo);

    //订单查询
    IPage<OrderInfo> getOrderInfoByUserIdPage(Page<OrderInfo> pageParam, OrderUserQueryVo orderUserQueryVo);
}
