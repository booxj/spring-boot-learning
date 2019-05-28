package com.springboot.quartz.spring.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

@RestController
public class DynamicTask {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @RequestMapping("/startCron")
    public String startCron() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String clazzName = "com.springboot.quartz.spring.job.SimpleJob";
        Class cls = Class.forName(clazzName);
        future = threadPoolTaskScheduler.schedule((Runnable) cls.newInstance(), new CronTrigger("0/5 * * * * *"));
        System.out.println("DynamicTask.startCron()");
        return "startCron";
    }

    @RequestMapping("/stopCron")
    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTask.stopCron()");
        return "stopCron";
    }

    @RequestMapping("/changeCron10")

    public String startCron10() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        stopCron();// 先停止，在开启.
        String clazzName = "com.springboot.quartz.spring.job.SimpleJob";
        Class cls = Class.forName(clazzName);
        future = threadPoolTaskScheduler.schedule((Runnable) cls.newInstance(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTask.startCron10()");
        return "changeCron10";
    }

//    private class MyRunnable implements Runnable {
//        @Override
//        public void run() {
//            System.out.println("DynamicTask.MyRunnable.run() : " + LocalDateTime.now());
//        }
//    }

}
