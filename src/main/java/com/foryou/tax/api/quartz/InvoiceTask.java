package com.foryou.tax.api.quartz;

import com.foryou.tax.process.allinvoice.AllInvoiceProcess;
import com.foryou.tax.process.writeoffinfo.WriteOffInfoProcess;
import com.foryou.tax.util.LoggerUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/21
 * @description: 现金事物查询一周的数据量，发票创建查询所有数据
 */
public class InvoiceTask extends QuartzJobBean {

    @Autowired
    private WriteOffInfoProcess writeOffInfoProcess;

    @Autowired
    private AllInvoiceProcess allInvoiceProcess;

    public InvoiceTask(){}

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        LoggerUtils.debug(getClass(), "InvoiceTask start");
        allInvoiceProcess.allInvoiceImport(request, response);
        writeOffInfoProcess.writeOffInfoImport(request, response);
        LoggerUtils.debug(getClass(), "InvoiceTask end");
    }


}
