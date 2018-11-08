package com.springboot.rabbitmq.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wb
 * @data: 2018/11/8 10:38 PM
 * @see:
 * @since:
 */
@Component
public class MyProvider {

    @Autowired
    AmqpTemplate amqpTemplate;


    public void send() {
        // 发送4条消息

        amqpTemplate.convertAndSend("", "FIRST_QUEUE", "-------- a direct msg");

        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "aa.booxj.11", "-------- a topic msg : aa.booxj.11");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "bb.booxj.22", "-------- a topic msg : bb.booxj.22");

        amqpTemplate.convertAndSend("FANOUT_EXCHANGE", "", "-------- a fanout msg");

    }

}
