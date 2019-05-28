package com.springboot.quartz.jdbc.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @ClassName: SampleJob.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/28 11:31
 * @version:
 */
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("----hello world---" + new Date());
    }
}
