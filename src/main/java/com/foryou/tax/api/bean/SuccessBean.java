package com.foryou.tax.api.bean;

import com.foryou.tax.api.bean.success.SuccessInfo;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/19
 * @description: Success bean
 */
public class SuccessBean {
    private SuccessInfo success;
    public SuccessInfo getSuccess() {
        return success;
    }

    public void setSuccess(SuccessInfo success) {
        this.success = success;
    }
}
