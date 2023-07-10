package com.atguigu.ssyx.mq.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class MQProducerAckConfig implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    //  我们发送消息使用的是 private RabbitTemplate rabbitTemplate; 对象
    //  如果不做设置的话 当前的rabbitTemplate 与当前的配置类没有任何关系！
    private RabbitTemplate rabbitTemplate;

    //  设置 表示修饰一个非静态的void方法，在服务器加载Servlet的时候运行。并且只执行一次！
    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 表示消息是否正确发送到了交换机上
     *
     * @param correlationData 消息的载体
     * @param ack             判断是否发送到交换机上
     * @param cause           原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功！");
        } else {
            log.info("消息发送失败！" + cause);
        }
    }

    /**
     * 消息如果没有正确发送到队列中，则会走这个方法！如果消息被正常处理，则这个方法不会走！
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息主体: " + new String(message.getBody()));
        log.info("应答码: " + replyCode);
        log.info("描述：" + replyText);
        log.info("消息使用的交换器 exchange : " + exchange);
        log.info("消息使用的路由键 routing : " + routingKey);
    }
}
