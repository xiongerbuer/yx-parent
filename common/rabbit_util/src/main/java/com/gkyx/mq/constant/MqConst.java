package com.gkyx.mq.constant;

public class MqConst {
    /**
     * 消息补偿
     */
    public static final String MQ_KEY_PREFIX = "gkyx.mq:list";
    public static final int RETRY_COUNT = 3;

    /**
     * 商品上下架
     */
    public static final String EXCHANGE_GOODS_DIRECT = "gkyx.goods.direct";
    public static final String ROUTING_GOODS_UPPER = "gkyx.goods.upper";
    public static final String ROUTING_GOODS_LOWER = "gkyx.goods.lower";
    //队列
    public static final String QUEUE_GOODS_UPPER  = "gkyx.goods.upper";
    public static final String QUEUE_GOODS_LOWER  = "gkyx.goods.lower";

    /**
     * 团长上下线
     */
    public static final String EXCHANGE_LEADER_DIRECT = "gkyx.leader.direct";
    public static final String ROUTING_LEADER_UPPER = "gkyx.leader.upper";
    public static final String ROUTING_LEADER_LOWER = "gkyx.leader.lower";
    //队列
    public static final String QUEUE_LEADER_UPPER  = "gkyx.leader.upper";
    public static final String QUEUE_LEADER_LOWER  = "gkyx.leader.lower";

    //订单
    public static final String EXCHANGE_ORDER_DIRECT = "gkyx.order.direct";
    public static final String ROUTING_ROLLBACK_STOCK = "gkyx.rollback.stock";
    public static final String ROUTING_MINUS_STOCK = "gkyx.minus.stock";

    public static final String ROUTING_DELETE_CART = "gkyx.delete.cart";
    //解锁普通商品库存
    public static final String QUEUE_ROLLBACK_STOCK = "gkyx.rollback.stock";
    public static final String QUEUE_SECKILL_ROLLBACK_STOCK = "gkyx.seckill.rollback.stock";
    public static final String QUEUE_MINUS_STOCK = "gkyx.minus.stock";
    public static final String QUEUE_DELETE_CART = "gkyx.delete.cart";

    //支付
    public static final String EXCHANGE_PAY_DIRECT = "gkyx.pay.direct";
    public static final String ROUTING_PAY_SUCCESS = "gkyx.pay.success";
    public static final String QUEUE_ORDER_PAY  = "gkyx.order.pay";
    public static final String QUEUE_LEADER_BILL  = "gkyx.leader.bill";

    //取消订单
    public static final String EXCHANGE_CANCEL_ORDER_DIRECT = "gkyx.cancel.order.direct";
    public static final String ROUTING_CANCEL_ORDER = "gkyx.cancel.order";
    //延迟取消订单队列
    public static final String QUEUE_CANCEL_ORDER  = "gkyx.cancel.order";

    /**
     * 定时任务
     */
    public static final String EXCHANGE_DIRECT_TASK = "gkyx.exchange.direct.task";
    public static final String ROUTING_TASK_23 = "gkyx.task.23";
    //队列
    public static final String QUEUE_TASK_23  = "gkyx.queue.task.23";
}