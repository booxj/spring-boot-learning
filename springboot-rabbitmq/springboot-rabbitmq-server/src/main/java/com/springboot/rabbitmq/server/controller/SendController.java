package com.springboot.rabbitmq.server.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq")
public class SendController {

    @Autowired
    AmqpTemplate amqpTemplate;

    @RequestMapping("first")
    public void first(@RequestParam("msg") String msg) {
        amqpTemplate.convertAndSend("", "FIRST_QUEUE", msg);
    }

    @RequestMapping("second")
    public void second(@RequestParam("msg") String msg) {
        amqpTemplate.convertAndSend("", "SECOND_QUEUE", msg);
    }

    @RequestMapping("third")
    public void third(@RequestParam("msg") String msg) {
        amqpTemplate.convertAndSend("", "THIRD_QUEUE", msg);
    }

    /**
     *
     * @param routingKey 路由规则->routingKey.#
     * @param msg
     */
    @RequestMapping("topic")
    public void topic(@RequestParam("routingKey") String routingKey, @RequestParam("msg") String msg) {
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", routingKey + ".#", msg);
    }

    @RequestMapping("fanout")
    public void fanout(@RequestParam("msg") String msg) {
        amqpTemplate.convertAndSend("FANOUT_EXCHANGE", "", msg);
    }
}
