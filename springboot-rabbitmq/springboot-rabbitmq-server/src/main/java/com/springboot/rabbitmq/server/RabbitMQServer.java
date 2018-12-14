package com.springboot.rabbitmq.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQServer {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQServer.class, args);
    }
}
