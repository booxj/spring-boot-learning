package com.springboot.rabbitmq.server.config;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "ACK_QUEUE")
public class Consumer {

    private Logger log = LoggerFactory.getLogger(Consumer.class);

    @RabbitHandler
    public void process(Channel channel, String message, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        channel.basicAck(tag, false);
        log.info("receive: " + message);
    }
}
