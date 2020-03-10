package com.foryou.tax.api.bean;

import com.foryou.tax.api.bean.success.StatusBean;

public class SuccessResult {
    private StatusBean status;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }
}
