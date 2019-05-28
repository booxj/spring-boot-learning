package com.springboot.quartz.jdbc.service.impl;

import com.springboot.quartz.jdbc.service.JobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: JobServiceImpl.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 11:00
 * @version:
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls, String cron) {
        try {
            // 获取调度器
            Scheduler sched = scheduler;
            // 创建一项作业
            JobDetail job = JobBuilder.newJob(cls)
                    .withIdentity(jobName, jobGroupName).build();
            // 创建一个触发器
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            // 告诉调度器使用该触发器来安排作业
            sched.scheduleJob(job, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modifyJobTime(String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
        try {
            Scheduler sched = scheduler;
            CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey
                    .triggerKey(oldtriggerName, oldtriggerGroup));
            if (trigger == null) {
                return false;
            }

            JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName, oldtriggerGroup);

            JobDetail job = sched.getJobDetail(jobKey);
            Class jobClass = job.getJobClass();
            // 停止触发器
            sched.pauseTrigger(triggerKey);
            // 移除触发器
            sched.unscheduleJob(triggerKey);
            // 删除任务
            sched.deleteJob(jobKey);

            addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass, cron);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        try {
            Scheduler sched = scheduler;
            CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey
                    .triggerKey(triggerName, triggerGroupName));
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                CronTrigger ct = (CronTrigger) trigger;
                // 修改时间
                ct.getTriggerBuilder()
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                        .build();
                // 重启触发器
                sched.resumeTrigger(TriggerKey.triggerKey(triggerName,
                        triggerGroupName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = scheduler;
            // 停止触发器
            sched.pauseTrigger(TriggerKey.triggerKey(triggerName,
                    triggerGroupName));
            // 移除触发器
            sched.unscheduleJob(TriggerKey.triggerKey(triggerName,
                    triggerGroupName));
            // 删除任务
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startSchedule() {
        try {
            Scheduler sched = scheduler;
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdownSchedule() {
        try {
            Scheduler sched = scheduler;
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
