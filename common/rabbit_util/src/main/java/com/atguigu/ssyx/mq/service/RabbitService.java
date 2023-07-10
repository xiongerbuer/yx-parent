package com.atguigu.ssyx.mq.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RabbitService {

    private RabbitTemplate rabbitTemplate;

    //发送消息的方法
    //exchange交换机
    //routingKey路由
    //message消息
    public void sendMessage(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

}
