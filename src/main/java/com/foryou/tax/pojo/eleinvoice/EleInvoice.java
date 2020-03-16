package com.foryou.tax.pojo.eleinvoice;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电子发票表
 * </p>
 *
 * @author raymon
 * @since 2020-03-16
 */
@TableName("ELE_INVOICE")
public class EleInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 电子发票ID/PK
     */
    @TableId(value = "ELE_INVOICE_ID", type = IdType.AUTO)
    private Integer eleInvoiceId;
    /**
     * 发票表ID
     */
    @TableField("INVOICE_ID")
    private Integer invoiceId;
    /**
     * 单据类型
     */
    @TableField("DOCUMENT_TYPE")
    private String documentType;
    /**
     * 单据类别
     */
    @TableField("DOCUMENT_CATEGORY")
    private String documentCategory;
    /**
     * 流水号
     */
    @TableField("SERIAL_NUM")
    private String serialNum;
    /**
     * 销方税号
     */
    @TableField("SALE_TAX")
    private String saleTax;
    /**
     * 购方名称
     */
    @TableField("CUST_NAME")
    private String custName;
    /**
     * 购方税号
     */
    @TableField("CUST_TAX_NO")
    private String custTaxNo;
    /**
     * 购方地址
     */
    @TableField("CUST_ADDR")
    private String custAddr;
    /**
     * 购货方手机号
     */
    @TableField("CUST_TELEPHONE")
    private String custTelephone;
    /**
     * 购方固定电话
     */
    @TableField("CUST_PHONE")
    private String custPhone;
    /**
     * 购方邮箱
     */
    @TableField("CUST_EMAIL")
    private String custEmail;
    /**
     * 开户行+账号
     */
    @TableField("CUST_BANK_ACCOUNT")
    private String custBankAccount;
    /**
     * 购货方企业类型
     */
    @TableField("CUST_TYPE")
    private String custType;
    /**
     * 备注
     */
    @TableField("ELE_INVOICE_MEMO")
    private String eleInvoiceMemo;
    /**
     * 发票类型
     */
    @TableField("ELE_INVOICE_TYPE")
    private String eleInvoiceType;
    /**
     * 开票日期
     */
    @TableField("BILL_DATE")
    private Date billDate;
    /**
     * 退货单号
     */
    @TableField("CANCEL_NUM")
    private String cancelNum;
    /**
     * 开票类型
     */
    @TableField("BILL_TYPE")
    private String billType;
    /**
     * 特殊红冲标志
     */
    @TableField("SPECIAL_RED_FLAG")
    private String specialRedFlag;
    /**
     * 操作代码
     */
    @TableField("OPERATION_CODE")
    private String operationCode;
    /**
     * 开票人
     */
    @TableField("LSSUER")
    private String lssuer;
    /**
     * 收款员
     */
    @TableField("PAYEE")
    private String payee;
    /**
     * 复核人
     */
    @TableField("REVIEWER")
    private String reviewer;
    /**
     * 原发票代码
     */
    @TableField("FORMER_INVOICE_CODE")
    private String formerInvoiceCode;
    /**
     * 原发票号码
     */
    @TableField("FORMER_INVOICE_NUM")
    private String formerInvoiceNum;
    /**
     * 红冲原因
     */
    @TableField("RED_REASON")
    private String redReason;
    /**
     * 电子发票状态
     */
    @TableField("ELE_INVOICE_STATUS")
    private String eleInvoiceStatus;
    /**
     * 电子接口返回的pdf附件URL
     */
    @TableField("PDF_URL")
    private String pdfUrl;
    /**
     * 开票日期
     */
    @TableField("ERROR_CODE")
    private String errorCode;
    /**
     * 电子发票返回错误message
     */
    @TableField("ERROR_MESSAGE")
    private String errorMessage;
    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private Integer updatedBy;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;


    public Integer getEleInvoiceId() {
        return eleInvoiceId;
    }

    public void setEleInvoiceId(Integer eleInvoiceId) {
        this.eleInvoiceId = eleInvoiceId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(String documentCategory) {
        this.documentCategory = documentCategory;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getSaleTax() {
        return saleTax;
    }

    public void setSaleTax(String saleTax) {
        this.saleTax = saleTax;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustTaxNo() {
        return custTaxNo;
    }

    public void setCustTaxNo(String custTaxNo) {
        this.custTaxNo = custTaxNo;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getCustTelephone() {
        return custTelephone;
    }

    public void setCustTelephone(String custTelephone) {
        this.custTelephone = custTelephone;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustBankAccount() {
        return custBankAccount;
    }

    public void setCustBankAccount(String custBankAccount) {
        this.custBankAccount = custBankAccount;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getEleInvoiceMemo() {
        return eleInvoiceMemo;
    }

    public void setEleInvoiceMemo(String eleInvoiceMemo) {
        this.eleInvoiceMemo = eleInvoiceMemo;
    }

    public String getEleInvoiceType() {
        return eleInvoiceType;
    }

    public void setEleInvoiceType(String eleInvoiceType) {
        this.eleInvoiceType = eleInvoiceType;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(String cancelNum) {
        this.cancelNum = cancelNum;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getSpecialRedFlag() {
        return specialRedFlag;
    }

    public void setSpecialRedFlag(String specialRedFlag) {
        this.specialRedFlag = specialRedFlag;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getLssuer() {
        return lssuer;
    }

    public void setLssuer(String lssuer) {
        this.lssuer = lssuer;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getFormerInvoiceCode() {
        return formerInvoiceCode;
    }

    public void setFormerInvoiceCode(String formerInvoiceCode) {
        this.formerInvoiceCode = formerInvoiceCode;
    }

    public String getFormerInvoiceNum() {
        return formerInvoiceNum;
    }

    public void setFormerInvoiceNum(String formerInvoiceNum) {
        this.formerInvoiceNum = formerInvoiceNum;
    }

    public String getRedReason() {
        return redReason;
    }

    public void setRedReason(String redReason) {
        this.redReason = redReason;
    }

    public String getEleInvoiceStatus() {
        return eleInvoiceStatus;
    }

    public void setEleInvoiceStatus(String eleInvoiceStatus) {
        this.eleInvoiceStatus = eleInvoiceStatus;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
        return "EleInvoice{" +
        ", eleInvoiceId=" + eleInvoiceId +
        ", invoiceId=" + invoiceId +
        ", documentType=" + documentType +
        ", documentCategory=" + documentCategory +
        ", serialNum=" + serialNum +
        ", saleTax=" + saleTax +
        ", custName=" + custName +
        ", custTaxNo=" + custTaxNo +
        ", custAddr=" + custAddr +
        ", custTelephone=" + custTelephone +
        ", custPhone=" + custPhone +
        ", custEmail=" + custEmail +
        ", custBankAccount=" + custBankAccount +
        ", custType=" + custType +
        ", eleInvoiceMemo=" + eleInvoiceMemo +
        ", eleInvoiceType=" + eleInvoiceType +
        ", billDate=" + billDate +
        ", cancelNum=" + cancelNum +
        ", billType=" + billType +
        ", specialRedFlag=" + specialRedFlag +
        ", operationCode=" + operationCode +
        ", lssuer=" + lssuer +
        ", payee=" + payee +
        ", reviewer=" + reviewer +
        ", formerInvoiceCode=" + formerInvoiceCode +
        ", formerInvoiceNum=" + formerInvoiceNum +
        ", redReason=" + redReason +
        ", eleInvoiceStatus=" + eleInvoiceStatus +
        ", pdfUrl=" + pdfUrl +
        ", errorCode=" + errorCode +
        ", errorMessage=" + errorMessage +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
