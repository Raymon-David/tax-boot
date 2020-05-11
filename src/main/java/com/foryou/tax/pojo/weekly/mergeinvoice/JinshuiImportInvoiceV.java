package com.foryou.tax.pojo.weekly.mergeinvoice;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@TableName("JINSHUI_IMPORT_INVOICE_V")
public class JinshuiImportInvoiceV implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("BILL_TYPE")
    private String billType;
    @TableField("KEYWORDS")
    private String keywords;
    @TableField("INVOICE_CODE")
    private String invoiceCode;
    @TableField("INVOICE_NUMBER")
    private String invoiceNumber;
    @TableField("INVOICE_TITLE")
    private String invoiceTitle;
    @TableField("TAX_REGISTRY_NUM")
    private String taxRegistryNum;
    @TableField("INVOICE_OBJECT_ADDRESS_PHONE")
    private String invoiceObjectAddressPhone;
    @TableField("INVOICE_OBJECT_BANK_ACCOUNT")
    private String invoiceObjectBankAccount;
    @TableField("ISSUED_TIME")
    private Date issuedTime;
    @TableField("ISSUED_MONTH")
    private String issuedMonth;
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @TableField("TAX_NET_AMOUNT")
    private BigDecimal taxNetAmount;
    @TableField("TAX_RATE")
    private BigDecimal taxRate;
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;
    @TableField("PRODUCT_NAME")
    private String productName;
    @TableField("INVOICE_MEMO")
    private String invoiceMemo;
    @TableField("ISSUER")
    private String issuer;
    @TableField("ACCOUNT_PAYEE")
    private String accountPayee;
    @TableField("REVIEWER")
    private String reviewer;
    @TableField("INVOICE_INVALID_FLAG")
    private String invoiceInvalidFlag;


    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
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

    public String getInvoiceObjectBankAccount() {
        return invoiceObjectBankAccount;
    }

    public void setInvoiceObjectBankAccount(String invoiceObjectBankAccount) {
        this.invoiceObjectBankAccount = invoiceObjectBankAccount;
    }

    public Date getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Date issuedTime) {
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getAccountPayee() {
        return accountPayee;
    }

    public void setAccountPayee(String accountPayee) {
        this.accountPayee = accountPayee;
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
        return "JinshuiImportInvoiceV{" +
        ", billType=" + billType +
        ", keywords=" + keywords +
        ", invoiceCode=" + invoiceCode +
        ", invoiceNumber=" + invoiceNumber +
        ", invoiceTitle=" + invoiceTitle +
        ", taxRegistryNum=" + taxRegistryNum +
        ", invoiceObjectAddressPhone=" + invoiceObjectAddressPhone +
        ", invoiceObjectBankAccount=" + invoiceObjectBankAccount +
        ", issuedTime=" + issuedTime +
        ", issuedMonth=" + issuedMonth +
        ", totalAmount=" + totalAmount +
        ", taxNetAmount=" + taxNetAmount +
        ", taxRate=" + taxRate +
        ", taxAmount=" + taxAmount +
        ", productName=" + productName +
        ", invoiceMemo=" + invoiceMemo +
        ", issuer=" + issuer +
        ", accountPayee=" + accountPayee +
        ", reviewer=" + reviewer +
        ", invoiceInvalidFlag=" + invoiceInvalidFlag +
        "}";
    }
}
