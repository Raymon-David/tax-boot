package com.foryou.tax.api.constant;

public enum StatusCodeEnum {
    /**
     * 电子发票状态 ELE_INVOICE_STATUS_CODE
     */
    ES_2001("ES2001", "新建"),
    ES_2002("ES2002", "已传入"),
    ES_2003("ES2003", "已下载"),
    ES_2004("ES2004", "数据有误"),

    /**
     * 电子发票金税接口状态 INVOICE_INTERFACE_STATUS_CODE
     */
    EIS_3001("EIS3001", "新建"),
    EIS_3002("EIS3002", "暂挂"),
    EIS_3003("EIS3003", "已传金税"),
    EIS_3004("EIS3004", "金税已回写"),
    ;

    private String statusCode;

    private String statusName;

    StatusCodeEnum() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    StatusCodeEnum(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }


}
