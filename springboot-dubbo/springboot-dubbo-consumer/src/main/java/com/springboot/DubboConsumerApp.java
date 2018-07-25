package com.springboot;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@ImportResource({"classpath:dubbo-consumer.xml"})
public class DubboConsumerApp {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(DubboConsumerApp.class, args);
    }

}
