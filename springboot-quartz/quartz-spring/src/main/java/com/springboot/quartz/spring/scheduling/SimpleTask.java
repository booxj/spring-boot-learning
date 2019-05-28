package com.springboot.quartz.spring.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @ClassName: SimpleTask.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 10:13
 * @version:
 */
@Configuration
public class SimpleTask {

    @Scheduled(cron = "0/1 * * * * ?")
    public void task() {
        System.out.println("SimpleTask.task() :            " + LocalDateTime.now());
    }
}
