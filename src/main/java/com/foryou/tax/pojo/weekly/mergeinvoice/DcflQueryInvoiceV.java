package com.foryou.tax.pojo.weekly.mergeinvoice;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@TableName("DCFL_QUERY_INVOICE_V")
public class DcflQueryInvoiceV implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    @TableField("DOCUMENT_NUMBER")
    private String documentNumber;
    @TableField("KEYWORDS")
    private String keywords;
    /**
     * 发票代码
     */
    @TableField("INVOICE_CODE")
    private String invoiceCode;
    /**
     * 发票号
     */
    @TableField("INVOICE_NUMBER")
    private String invoiceNumber;
    /**
     * 购方名称
     */
    @TableField("INVOICE_TITLE")
    private String invoiceTitle;
    /**
     * 购方税号
     */
    @TableField("TAX_REGISTRY_NUM")
    private String taxRegistryNum;
    /**
     * 购方地址电话
     */
    @TableField("INVOICE_OBJECT_ADDRESS_PHONE")
    private String invoiceObjectAddressPhone;
    /**
     * 开户行+账号
     */
    @TableField("ISSUED_TIME")
    private String issuedTime;
    /**
     * 所属月份
     */
    @TableField("ISSUED_MONTH")
    private String issuedMonth;
    /**
     * 含税金额 订单总价合计
     */
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    /**
     * 不含税金额 合计金额
     */
    @TableField("TAX_NET_AMOUNT")
    private BigDecimal taxNetAmount;
    /**
     * 税率
     */
    @TableField("TAX_RATE")
    private BigDecimal taxRate;
    /**
     * 税额 合计税额
     */
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;
    /**
     * 备注
     */
    @TableField("INVOICE_MEMO")
    private String invoiceMemo;
    /**
     * 开票人
     */
    @TableField("ISSUER")
    private String issuer;
    /**
     * 复核人
     */
    @TableField("REVIEWER")
    private String reviewer;
    @TableField("INVOICE_INVALID_FLAG")
    private String invoiceInvalidFlag;


    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxRegistryNum() {
        return taxRegistryNum;
    }

    public void setTaxRegistryNum(String taxRegistryNum) {
        this.taxRegistryNum = taxRegistryNum;
    }

    public String getInvoiceObjectAddressPhone() {
        return invoiceObjectAddressPhone;
    }

    public void setInvoiceObjectAddressPhone(String invoiceObjectAddressPhone) {
        this.invoiceObjectAddressPhone = invoiceObjectAddressPhone;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getIssuedMonth() {
        return issuedMonth;
    }

    public void setIssuedMonth(String issuedMonth) {
        this.issuedMonth = issuedMonth;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxNetAmount() {
        return taxNetAmount;
    }

    public void setTaxNetAmount(BigDecimal taxNetAmount) {
        this.taxNetAmount = taxNetAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getInvoiceMemo() {
        return invoiceMemo;
    }

    public void setInvoiceMemo(String invoiceMemo) {
        this.invoiceMemo = invoiceMemo;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getInvoiceInvalidFlag() {
        return invoiceInvalidFlag;
    }

    public void setInvoiceInvalidFlag(String invoiceInvalidFlag) {
        this.invoiceInvalidFlag = invoiceInvalidFlag;
    }

    @Override
    public String toString() {
        return "DcflQueryInvoiceV{" +
        ", documentNumber=" + documentNumber +
        ", keywords=" + keywords +
        ", invoiceCode=" + invoiceCode +
        ", invoiceNumber=" + invoiceNumber +
        ", invoiceTitle=" + invoiceTitle +
        ", taxRegistryNum=" + taxRegistryNum +
        ", invoiceObjectAddressPhone=" + invoiceObjectAddressPhone +
        ", issuedTime=" + issuedTime +
        ", issuedMonth=" + issuedMonth +
        ", totalAmount=" + totalAmount +
        ", taxNetAmount=" + taxNetAmount +
        ", taxRate=" + taxRate +
        ", taxAmount=" + taxAmount +
        ", invoiceMemo=" + invoiceMemo +
        ", issuer=" + issuer +
        ", reviewer=" + reviewer +
        ", invoiceInvalidFlag=" + invoiceInvalidFlag +
        "}";
    }
}
