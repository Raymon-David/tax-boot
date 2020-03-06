package com.foryou.tax.pojo.activiti;

import java.io.Serializable;

/**
 * @author ：raymon
 * @date ：Created in 2019/11/6 11:11 上午
 * @description：acticiti Processpojo
 * @modified By：
 * @version: 1.0$
 */
public class ProcessPojo implements Serializable {
    private Integer days;

    private String reason;

    private String date;

    private String processInstanceId;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
