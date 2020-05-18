package com.foryou.tax.api.quartz;

import com.foryou.tax.process.weekly.WeeklyProcess;
import com.foryou.tax.util.DateUtil;
import com.foryou.tax.util.LoggerUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/5/18
 * @description: 定时删除备份表
 */
public class DropTableTask extends QuartzJobBean {

    @Autowired
    private WeeklyProcess weeklyProcess;

    /**
     * 有人说要写这个构造器
     */
    public DropTableTask(){

    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        LoggerUtils.debug(getClass(), "DropTableTask is: " + DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));

        LoggerUtils.debug(getClass(), "定时删除备份表 开始");
        weeklyProcess.dropTableEveryMonth(request, response);
        LoggerUtils.debug(getClass(), "定时删除备份表 结束");
    }
}
