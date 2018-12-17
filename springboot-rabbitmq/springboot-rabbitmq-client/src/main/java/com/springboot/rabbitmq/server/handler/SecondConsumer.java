package com.springboot.rabbitmq.server.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wb
 * @data: 2018/11/8 10:35 PM
 * @see:
 * @since:
 */
@Component
@RabbitListener(queues = "SECOND_QUEUE")
public class SecondConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println(" second queue received msg : " + msg);
    }

}
