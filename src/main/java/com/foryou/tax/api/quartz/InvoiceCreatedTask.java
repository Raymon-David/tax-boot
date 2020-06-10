package com.foryou.tax.api.quartz;

import com.foryou.tax.process.allinvoice.AllInvoiceProcess;
import com.foryou.tax.util.LoggerUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/6/10
 * @description : 销项发票创建查询的数据，每天跑一次
 */
public class InvoiceCreatedTask extends QuartzJobBean {

    @Autowired
    private AllInvoiceProcess allInvoiceProcess;

    public InvoiceCreatedTask(){}

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        HttpServletRequest request = null;
        HttpServletResponse response = null;

        LoggerUtils.debug(getClass(), "InvoiceCreatedTask start");

        LoggerUtils.debug(getClass(), "导入发票创建 开始");
        allInvoiceProcess.dcflCreateInvoiceImport(request, response);
        LoggerUtils.debug(getClass(), "导入发票创建 结束");

        LoggerUtils.debug(getClass(), "InvoiceCreatedTask end");
    }
}
