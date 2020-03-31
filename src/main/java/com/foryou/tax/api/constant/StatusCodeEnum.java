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
    EIS_3002("EIS3002", "已传金税"),
    EIS_3003("EIS3003", "金税已回写"),
    EIS_3004("EIS3004", "金税返回错误"),

    /**
     * 税收分类编码中的商品名称
     */
    G_1001("LEASE", "融资租赁"),
    G_1002("LEASEBACK", "售后回租"),

    /**
     * DCFL 产品线
     */
    P_1001("P1001", "挖掘机"),
    P_1002("P1002", "装载机"),
    P_1003("P1003", "OEM"),
    P_1004("P1004", "破碎器"),
    P_1005("P1005", "二手挖掘机"),
    P_1006("P1006", "二手装载机"),
    P_1007("P1007", "其他设备"),
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
