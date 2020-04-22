package com.foryou.tax.pojo.weekly;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 从页面导入纸质发票 临时表
 * </p>
 *
 * @author raymon
 * @since 2020-04-22
 */
@TableName("DCFL_PAPER_INVOICE_IMPORT_TEMP")
public class DcflPaperInvoiceImportTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发票种类
     */
    @TableField("BILL_TYPE")
    private String billType;
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
     * 报送状态
     */
    @TableField("SUBMIT_TYPE")
    private String submitType;
    /**
     * 报送日志
     */
    @TableField("SUBMIT_LOG")
    private String submitLog;
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
     * 商品税目
     */
    @TableField("PRODUCT_TAX_ITEM")
    private String productTaxItem;
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
     * 打印标志
     */
    @TableField("PRINT_TYPE")
    private String printType;
    /**
     * 发票作废标志
     */
    @TableField("INVOICE_INVALID_FLAG")
    private String invoiceInvalidFlag;
    /**
     * 清单标志
     */
    @TableField("LIST_TYPE")
    private String listType;
    /**
     * 修复标志
     */
    @TableField("REPARE_TYPE")
    private String repareType;
    /**
     * 复核人
     */
    @TableField("REVIEWER")
    private String reviewer;
    /**
     * 销售部门
     */
    @TableField("SALE_DEPARTMENT")
    private String saleDepartment;
    /**
     * 作废时间
     */
    @TableField("INVOICE_INVALID_DATE")
    private Date invoiceInvalidDate;
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


    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
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

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getSubmitLog() {
        return submitLog;
    }

    public void setSubmitLog(String submitLog) {
        this.submitLog = submitLog;
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

    public String getProductTaxItem() {
        return productTaxItem;
    }

    public void setProductTaxItem(String productTaxItem) {
        this.productTaxItem = productTaxItem;
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

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getInvoiceInvalidFlag() {
        return invoiceInvalidFlag;
    }

    public void setInvoiceInvalidFlag(String invoiceInvalidFlag) {
        this.invoiceInvalidFlag = invoiceInvalidFlag;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getRepareType() {
        return repareType;
    }

    public void setRepareType(String repareType) {
        this.repareType = repareType;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getSaleDepartment() {
        return saleDepartment;
    }

    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    public Date getInvoiceInvalidDate() {
        return invoiceInvalidDate;
    }

    public void setInvoiceInvalidDate(Date invoiceInvalidDate) {
        this.invoiceInvalidDate = invoiceInvalidDate;
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
        return "DcflPaperInvoiceImportTemp{" +
        ", billType=" + billType +
        ", invoiceCode=" + invoiceCode +
        ", invoiceNumber=" + invoiceNumber +
        ", billMachineNum=" + billMachineNum +
        ", invoiceTitle=" + invoiceTitle +
        ", taxRegistryNum=" + taxRegistryNum +
        ", invoiceObjectAddressPhone=" + invoiceObjectAddressPhone +
        ", invoiceObjectBankAccount=" + invoiceObjectBankAccount +
        ", issuedTime=" + issuedTime +
        ", submitType=" + submitType +
        ", submitLog=" + submitLog +
        ", issuedMonth=" + issuedMonth +
        ", totalAmount=" + totalAmount +
        ", taxRate=" + taxRate +
        ", taxAmount=" + taxAmount +
        ", productName=" + productName +
        ", productTaxItem=" + productTaxItem +
        ", invoiceMemo=" + invoiceMemo +
        ", issuer=" + issuer +
        ", accountPayee=" + accountPayee +
        ", printType=" + printType +
        ", invoiceInvalidFlag=" + invoiceInvalidFlag +
        ", listType=" + listType +
        ", repareType=" + repareType +
        ", reviewer=" + reviewer +
        ", saleDepartment=" + saleDepartment +
        ", invoiceInvalidDate=" + invoiceInvalidDate +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
