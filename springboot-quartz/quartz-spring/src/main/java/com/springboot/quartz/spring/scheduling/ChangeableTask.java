package com.springboot.quartz.spring.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * @ClassName: ChangeableTask.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 10:15
 * @version:
 */
@Configuration
public class ChangeableTask implements SchedulingConfigurer {

    /**
     * 可以从数据库获取
     */
    private static String cron = "0/2 * * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {

        registrar.addTriggerTask(
                () -> {
                    System.out.println("ChangeableTask.task() :        " + LocalDateTime.now());
                }
                , triggerContext -> {
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }
}
