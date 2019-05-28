package com.springboot.quartz.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName: SpringSchedulingApplication.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 10:11
 * @version:
 */
@EnableScheduling
@SpringBootApplication
public class SpringSchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSchedulingApplication.class, args);
    }

}
