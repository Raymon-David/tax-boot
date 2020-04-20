package com.foryou.tax.pojo.weekly;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 从页面导入电子发票 临时表
 * </p>
 *
 * @author raymon
 * @since 2020-04-20
 */
@TableName("DCFL_ELE_INVOICE_IMPORT_TEMP")
public class DcflEleInvoiceImportTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求流水号
     */
    @TableId("SERIAL_NUM")
    private String serialNum;
    /**
     * 发票种类
     */
    @TableField("BILL_TYPE")
    private String billType;
    /**
     * 开票类型
     */
    @TableField("INVOICE_TYPE")
    private String invoiceType;
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
     * 开票机号
     */
    @TableField("BILL_MACHINE_NUM")
    private String billMachineNum;
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
    @TableField("INVOICE_OBJECT_BANK_ACCOUNT")
    private String invoiceObjectBankAccount;
    /**
     * 开票日期
     */
    @TableField("ISSUED_TIME")
    private Date issuedTime;
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
     * 商品名称 主要商品名称
     */
    @TableField("PRODUCT_NAME")
    private String productName;
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
     * 收款员
     */
    @TableField("ACCOUNT_PAYEE")
    private String accountPayee;
    /**
     * 复核人
     */
    @TableField("REVIEWER")
    private String reviewer;
    /**
     * 发票作废标志
     */
    @TableField("INVOICE_INVALID_FLAG")
    private String invoiceInvalidFlag;
    @TableField("CREATED_BY")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("UPDATED_BY")
    private Integer updatedBy;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;


    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
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

    public String getBillMachineNum() {
        return billMachineNum;
    }

    public void setBillMachineNum(String billMachineNum) {
        this.billMachineNum = billMachineNum;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DcflEleInvoiceImportTemp{" +
        ", serialNum=" + serialNum +
        ", billType=" + billType +
        ", invoiceType=" + invoiceType +
        ", invoiceCode=" + invoiceCode +
        ", invoiceNumber=" + invoiceNumber +
        ", billMachineNum=" + billMachineNum +
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
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
