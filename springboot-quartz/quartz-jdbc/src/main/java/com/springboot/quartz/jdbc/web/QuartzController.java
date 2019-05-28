package com.springboot.quartz.jdbc.web;

import com.springboot.quartz.jdbc.entity.JobEntity;
import com.springboot.quartz.jdbc.service.JobService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: QuartzController.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 11:06
 * @version:
 */
@RestController
@RequestMapping("job")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobService quartzService;

    /**
     * 定时列表页
     *
     * @return
     * @throws SchedulerException
     */
    @RequestMapping(value = "/listJob")
    public List<JobEntity> listJob(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
        List<JobEntity> jobInfos = this.getSchedulerJobInfo();
        return jobInfos;
    }

    /**
     * 新增job
     *
     * @param request
     * @param response
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @PostMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws SchedulerException, ClassNotFoundException {
//        String jobName = request.getParameter("jobName");
//        String jobGroupName = request.getParameter("jobGroupName");
//        String triggerName = request.getParameter("triggerName");
//        String triggerGroupName = request.getParameter("triggerGroupName");
//        String clazz = request.getParameter("clazz");

        String jobName = "jobName";
        String jobGroupName = "jobGroupName";
        String triggerName = "triggerName";
        String triggerGroupName = "triggerGroupName";
        String clazz = "com.springboot.quartz.jdbc.job.SampleJob";
        String cron = "0/2 * * * * ?";

        Class cls = Class.forName(clazz);
        quartzService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, cls, cron);
        return "添加任务成功";
    }


    /**
     * 编辑job
     *
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @PostMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response) throws SchedulerException, ClassNotFoundException {
        String jobName = request.getParameter("jobName");
        String jobGroupName = request.getParameter("jobGroupName");
        String triggerName = request.getParameter("triggerName");
        String triggerGroupName = request.getParameter("triggerGroupName");
        String clazz = request.getParameter("clazz");
        Class cls = Class.forName(clazz);
        String cron = request.getParameter("cron");

        String oldjobName = request.getParameter("oldjobName");
        String oldjobGroup = request.getParameter("oldjobGroup");
        String oldtriggerName = request.getParameter("oldtriggerName");
        String oldtriggerGroup = request.getParameter("oldtriggerGroup");

        boolean result = quartzService.modifyJobTime(oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup,
                jobName, jobGroupName, triggerName, triggerGroupName, cron);

        return result ? "修改任务成功" : "修改任务失败";
    }


    @PostMapping(value = "/pauseJob")
    public String pauseJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName) {

        String result;
        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(jobGroupName)) {
            result = "暂停任务失败";
        } else {
            quartzService.pauseJob(jobName, jobGroupName);
            result = "暂停任务成功";
        }

        return result;
    }

    @PostMapping(value = "/resumeJob")
    public String resumeJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName) {
        String result;

        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(jobGroupName)) {
            result = "继续任务失败";
        } else {
            quartzService.resumeJob(jobName, jobGroupName);
            result = "继续任务成功";
        }

        return result;
    }

    @PostMapping(value = "/deleteJob")
    public String deleteJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName,
                            @RequestParam("triggerName") String triggerName, @RequestParam("triggerGroupName") String triggerGroupName) {
        String result;

        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(jobGroupName) ||
                StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(triggerGroupName)) {
            result = "删除任务失败";
        } else {
            quartzService.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
            result = "删除任务成功";
        }

        return result;
    }

    private List<JobEntity> getSchedulerJobInfo() throws SchedulerException {
        List<JobEntity> jobInfos = new ArrayList<>();
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String triggerGroupName : triggerGroupNames) {
            Set<TriggerKey> triggerKeySet = scheduler
                    .getTriggerKeys(GroupMatcher
                            .triggerGroupEquals(triggerGroupName));
            for (TriggerKey triggerKey : triggerKeySet) {
                Trigger t = scheduler.getTrigger(triggerKey);
                if (t instanceof CronTrigger) {
                    CronTrigger trigger = (CronTrigger) t;
                    JobKey jobKey = trigger.getJobKey();
                    JobDetail jd = scheduler.getJobDetail(jobKey);
                    JobEntity jobInfo = new JobEntity();
                    jobInfo.setJobName(jobKey.getName());
                    jobInfo.setJobGroup(jobKey.getGroup());
                    jobInfo.setTriggerName(triggerKey.getName());
                    jobInfo.setTriggerGroupName(triggerKey.getGroup());
                    jobInfo.setCronExpr(trigger.getCronExpression());
                    jobInfo.setNextFireTime(trigger.getNextFireTime());
                    jobInfo.setPreviousFireTime(trigger.getPreviousFireTime());
                    jobInfo.setStartTime(trigger.getStartTime());
                    jobInfo.setEndTime(trigger.getEndTime());
                    jobInfo.setJobClass(jd.getJobClass().getCanonicalName());
                    // jobInfo.setDuration(Long.parseLong(jd.getDescription()));
                    Trigger.TriggerState triggerState = scheduler
                            .getTriggerState(trigger.getKey());
                    jobInfo.setJobStatus(triggerState.toString());
                    // NONE无,
                    // NORMAL正常,
                    // PAUSED暂停,
                    // COMPLETE完全,
                    // ERROR错误,
                    // BLOCKED阻塞
                    JobDataMap map = scheduler.getJobDetail(jobKey)
                            .getJobDataMap();
                    if (null != map && map.size() != 0) {
                        jobInfo.setCount(Integer.parseInt((String) map
                                .get("count")));
                        jobInfo.setJobDataMap(map);
                    } else {
                        jobInfo.setJobDataMap(new JobDataMap());
                    }
                    jobInfos.add(jobInfo);
                }
            }
        }
        return jobInfos;
    }
}
