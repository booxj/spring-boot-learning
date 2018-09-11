package com.springboot.spring;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

//@Component
public class SpringTaskCronChangeable implements SchedulingConfigurer {

    public static String cron;

    public SpringTaskCronChangeable() {
        cron = "0/5 * * * * *";
        new Thread(new Runnable() {
            @Override
            public void run() {
                cron = "0/10 * * * * *";
            }
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("SpringTaskCronChangeable.task() : " + LocalDateTime.now());
            }
        };

        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext context) {
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(context);
                return nextExec;
            }
        };

        registrar.addTriggerTask(task, trigger);
    }
}
