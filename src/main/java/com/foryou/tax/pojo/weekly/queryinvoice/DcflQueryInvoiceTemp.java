package com.foryou.tax.pojo.weekly.queryinvoice;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 从DCFL销项发票查询导入数据 临时表
 * </p>
 *
 * @author raymon
 * @since 2020-06-10
 */
@TableName("DCFL_QUERY_INVOICE_TEMP")
public class DcflQueryInvoiceTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    @TableField("DOCUMENT_NUMBER")
    private String documentNumber;
    /**
     * 发票代码
     */
    @TableField("INVOICE_CODE")
    private String invoiceCode;
    /**
     * 发票号
     */
    @TableId("INVOICE_NUMBER")
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
     * 发票作废标志
     */
    @TableField("INVOICE_INVALID_FLAG")
    private String invoiceInvalidFlag;
    /**
     * 复核人
     */
    @TableField("REVIEWER")
    private String reviewer;
    @TableField("CF_ITEM")
    private String cfItem;
    @TableField("CF_ITEM_DESC")
    private String cfItemDesc;
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


    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public String getInvoiceInvalidFlag() {
        return invoiceInvalidFlag;
    }

    public void setInvoiceInvalidFlag(String invoiceInvalidFlag) {
        this.invoiceInvalidFlag = invoiceInvalidFlag;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getCfItem() {
        return cfItem;
    }

    public void setCfItem(String cfItem) {
        this.cfItem = cfItem;
    }

    public String getCfItemDesc() {
        return cfItemDesc;
    }

    public void setCfItemDesc(String cfItemDesc) {
        this.cfItemDesc = cfItemDesc;
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
        return "DcflQueryInvoiceTemp{" +
                ", documentNumber=" + documentNumber +
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
                ", invoiceMemo=" + invoiceMemo +
                ", issuer=" + issuer +
                ", accountPayee=" + accountPayee +
                ", invoiceInvalidFlag=" + invoiceInvalidFlag +
                ", reviewer=" + reviewer +
                ", cfItem=" + cfItem +
                ", cfItemDesc=" + cfItemDesc +
                ", createdBy=" + createdBy +
                ", createTime=" + createTime +
                ", updatedBy=" + updatedBy +
                ", updateTime=" + updateTime +
                "}";
    }
}
