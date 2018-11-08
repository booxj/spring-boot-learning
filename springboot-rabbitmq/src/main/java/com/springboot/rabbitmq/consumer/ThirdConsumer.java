package com.springboot.rabbitmq.consumer;

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
@RabbitListener(queues = "THIRD_QUEUE")
public class ThirdConsumer {


    @RabbitHandler
    public void process(String msg) {
        System.out.println(" third queue received msg : " + msg);
    }

}
