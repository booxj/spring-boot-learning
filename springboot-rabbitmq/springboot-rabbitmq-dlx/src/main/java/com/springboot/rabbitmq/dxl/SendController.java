package com.springboot.rabbitmq.dxl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.UUID;

@RestController
public class SendController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = {"DLX_REDIRECT_QUEUE"})
    public void redirect(String msg) {
        System.out.println("接收消息时间 " + LocalTime.now().toString());
        System.out.println("接收消息内容 " + msg);
    }

    @RequestMapping("/dlx")
    public void dlx() {
        UUID msg = UUID.randomUUID();
        System.out.println("发送消息时间 " + LocalTime.now().toString());
        System.out.println("发送消息内容 " + msg);
        amqpTemplate.convertAndSend("DLX_EXCHANGE", "KEY_DLX", msg);
    }

}
