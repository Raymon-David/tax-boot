package com.foryou.tax.api.quartz;

import com.foryou.tax.util.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/21
 * @description: test task
 */
public class TestTask extends QuartzJobBean {

    /**
     * 有人说要写这个构造器
     */
    public TestTask(){

    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestTask is: " + DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));
    }
}
