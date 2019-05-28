package com.springboot.quartz.spring.job;

import java.time.LocalDateTime;

/**
 * @ClassName: SimpleJob.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 14:25
 * @version:
 */
public class SimpleJob implements Runnable {

    @Override
    public void run() {
        System.out.println("SimpleJob.run() : " + LocalDateTime.now());
    }
}
