package com.foryou.tax.api.bean.success;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/19
 * @description: success info
 */
public class SuccessInfo {
    private String type;
    private SuccessDesc successDesc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SuccessDesc getSuccessDesc() {
        return successDesc;
    }

    public void setSuccessDesc(SuccessDesc successDesc) {
        this.successDesc = successDesc;
    }
}
