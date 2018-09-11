package com.springboot.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * spring 自带的定时任务方法
 */
//@Configuration
public class SpringTask {

    @Scheduled(cron = "0/1 * * * * ?")
    public void task() {
        System.out.println("SpringTask.task() : " + LocalDateTime.now());
    }
}
