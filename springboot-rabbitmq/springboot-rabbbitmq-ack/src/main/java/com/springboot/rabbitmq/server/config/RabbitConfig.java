package com.springboot.rabbitmq.server.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean("ackQueue")
    public Queue queue() {
        /**
         * Queue可以有4个参数
         * 1.队列名
         * 2.durable 持久化
         * 3.auto-delete 自动删除
         * 4.是否只在当前connection生效
         */
        return new Queue("ACK_QUEUE");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("ACK_EXCHANGE");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ACK_ROUTEKEY");
    }


    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.debug("消息发送到exchange成功,原因: {}", cause);
        } else {
            logger.debug("消息发送到exchange失败,原因: {}", cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        logger.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
    }
}
