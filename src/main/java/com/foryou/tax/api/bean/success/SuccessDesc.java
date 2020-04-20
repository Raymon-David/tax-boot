package com.foryou.tax.api.bean.success;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/19
 * @description: Success Desc
 */
public class SuccessDesc {
    /**
     * 代码
     */
    private String code;
    /**
     * 描述
     */
    private String message;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
