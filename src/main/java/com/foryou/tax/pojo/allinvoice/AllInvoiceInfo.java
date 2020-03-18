package com.foryou.tax.pojo.allinvoice;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 发票总表
 * </p>
 *
 * @author raymon
 * @since 2020-03-18
 */
@TableName("ALL_INVOICE_INFO")
public class AllInvoiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发票表ID/PK
     */
    @TableId(value = "INVOICE_ID", type = IdType.AUTO)
    private Integer invoiceId;
    /**
     * 公司ID
     */
    @TableField("COMPANY_ID")
    private Integer companyId;
    /**
     * 发票单据编号  AI + yyyymmdd + 1000001
     */
    @TableField("INVOICE_NUM")
    private String invoiceNum;
    /**
     * 发票种类（SPECIAL-专用发票 / ORDINARY-普通发票 / RECEIPT-收据）
     */
    @TableField("INVOICE_KIND")
    private String invoiceKind;
    /**
     * 开票方式 LB_INTEREST-回租利息票 / L_RENTAL - 直租本金票 / L_INTEREST-直租利息票 / LB_RENTAL-回租本金票
     */
    @TableField("BILLING_METHOD")
    private String billingMethod;
    /**
     * 开票方式 PAPER-纸质发票 / ELE - 电子发票
     */
    @TableField("BILLING_WAY")
    private String billingWay;
    /**
     * 区别于根据合同创建的发票 saas开票 / 接口开票 / 线下开票数据导入
     */
    @TableField("MANUAL_INVOICE_CODE")
    private String manualInvoiceCode;
    /**
     * 区别于根据合同创建的发票 saas开票 / 接口开票 / 线下开票数据导入
     */
    @TableField("MANUAL_INVOICE_NAME")
    private String manualInvoiceName;
    /**
     * 单据类别
     */
    @TableField("DOCUMENT_CATEGORY")
    private String documentCategory;
    /**
     * 商品或服务code 决定税收分类编码
     */
    @TableField("COMMODITY_CODE")
    private String commodityCode;
    /**
     * 商品或服name 决定税收分类编码
     */
    @TableField("COMMODITY_NAME")
    private String commodityName;
    /**
     * 单据所有者（用户ID） 日后可以按量收费
     */
    @TableField("OWNER_USER_ID")
    private Integer ownerUserId;
    /**
     * 开票对象code
     */
    @TableField("OBJECT_CODE")
    private String objectCode;
    /**
     * 开票对象name
     */
    @TableField("OBJECT_NAME")
    private String objectName;
    /**
     * 发票抬头name
     */
    @TableField("INVOICE_TITLE")
    private String invoiceTitle;
    /**
     * 纳税人登记号/税收编码
     */
    @TableField("TAX_REGISTRY_NUM")
    private String taxRegistryNum;
    /**
     * 地址及电话
     */
    @TableField("INVOICE_OBJECT_ADDRESS_PHONE")
    private String invoiceObjectAddressPhone;
    /**
     * 开户行及账号
     */
    @TableField("INVOICE_OBJECT_BANK_ACCOUNT")
    private String invoiceObjectBankAccount;
    /**
     * 备注
     */
    @TableField("INVOICE_MEMO")
    private String invoiceMemo;
    /**
     * 含税金额总计
     */
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    /**
     * 税额总计
     */
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;
    /**
     * 币种
     */
    @TableField("CURRENCY")
    private String currency;
    /**
     * 汇率类型
     */
    @TableField("EXCHANGE_RATE_TYPE")
    private String exchangeRateType;
    /**
     * 汇率标价方法
     */
    @TableField("EXCHANGE_RATE_QUOTATION")
    private String exchangeRateQuotation;
    /**
     * 汇率
     */
    @TableField("EXCHANGE_RATE")
    private String exchangeRate;
    /**
     * 期间序号
     */
    @TableField("PERIOD_CODE")
    private String periodCode;
    /**
     * 期间名
     */
    @TableField("PERIOD_NAME")
    private String periodName;
    /**
     * 发票状态CODE
     */
    @TableField("INVOICE_STATUS_CODE")
    private String invoiceStatusCode;
    /**
     * 发票状态NAME
     */
    @TableField("INVOICE_STATUS_NAME")
    private String invoiceStatusName;
    /**
     * 收款员
     */
    @TableField("ACCOUNT_PAYEE")
    private String accountPayee;
    /**
     * 记账日期
     */
    @TableField("ACCOUNT_TIME")
    private Date accountTime;
    /**
     * 开票人
     */
    @TableField("ISSUER")
    private Integer issuer;
    /**
     * 开票日期
     */
    @TableField("ISSUED_TIME")
    private Date issuedTime;
    /**
     * 复核人
     */
    @TableField("REVIEWER")
    private Integer reviewer;
    /**
     * 复核日期
     */
    @TableField("REVIEWED_TIME")
    private Date reviewedTime;
    /**
     * 过账人
     */
    @TableField("POSTER")
    private Integer poster;
    /**
     * 过账日期
     */
    @TableField("POSTED_TIME")
    private Date postedTime;
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


    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceKind() {
        return invoiceKind;
    }

    public void setInvoiceKind(String invoiceKind) {
        this.invoiceKind = invoiceKind;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public String getBillingWay() {
        return billingWay;
    }

    public void setBillingWay(String billingWay) {
        this.billingWay = billingWay;
    }

    public String getManualInvoiceCode() {
        return manualInvoiceCode;
    }

    public void setManualInvoiceCode(String manualInvoiceCode) {
        this.manualInvoiceCode = manualInvoiceCode;
    }

    public String getManualInvoiceName() {
        return manualInvoiceName;
    }

    public void setManualInvoiceName(String manualInvoiceName) {
        this.manualInvoiceName = manualInvoiceName;
    }

    public String getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(String documentCategory) {
        this.documentCategory = documentCategory;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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

    public String getInvoiceMemo() {
        return invoiceMemo;
    }

    public void setInvoiceMemo(String invoiceMemo) {
        this.invoiceMemo = invoiceMemo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeRateType() {
        return exchangeRateType;
    }

    public void setExchangeRateType(String exchangeRateType) {
        this.exchangeRateType = exchangeRateType;
    }

    public String getExchangeRateQuotation() {
        return exchangeRateQuotation;
    }

    public void setExchangeRateQuotation(String exchangeRateQuotation) {
        this.exchangeRateQuotation = exchangeRateQuotation;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }

    public String getInvoiceStatusName() {
        return invoiceStatusName;
    }

    public void setInvoiceStatusName(String invoiceStatusName) {
        this.invoiceStatusName = invoiceStatusName;
    }

    public String getAccountPayee() {
        return accountPayee;
    }

    public void setAccountPayee(String accountPayee) {
        this.accountPayee = accountPayee;
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public Integer getIssuer() {
        return issuer;
    }

    public void setIssuer(Integer issuer) {
        this.issuer = issuer;
    }

    public Date getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Date issuedTime) {
        this.issuedTime = issuedTime;
    }

    public Integer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Integer reviewer) {
        this.reviewer = reviewer;
    }

    public Date getReviewedTime() {
        return reviewedTime;
    }

    public void setReviewedTime(Date reviewedTime) {
        this.reviewedTime = reviewedTime;
    }

    public Integer getPoster() {
        return poster;
    }

    public void setPoster(Integer poster) {
        this.poster = poster;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
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
        return "AllInvoiceInfo{" +
        ", invoiceId=" + invoiceId +
        ", companyId=" + companyId +
        ", invoiceNum=" + invoiceNum +
        ", invoiceKind=" + invoiceKind +
        ", billingMethod=" + billingMethod +
        ", billingWay=" + billingWay +
        ", manualInvoiceCode=" + manualInvoiceCode +
        ", manualInvoiceName=" + manualInvoiceName +
        ", documentCategory=" + documentCategory +
        ", commodityCode=" + commodityCode +
        ", commodityName=" + commodityName +
        ", ownerUserId=" + ownerUserId +
        ", objectCode=" + objectCode +
        ", objectName=" + objectName +
        ", invoiceTitle=" + invoiceTitle +
        ", taxRegistryNum=" + taxRegistryNum +
        ", invoiceObjectAddressPhone=" + invoiceObjectAddressPhone +
        ", invoiceObjectBankAccount=" + invoiceObjectBankAccount +
        ", invoiceMemo=" + invoiceMemo +
        ", totalAmount=" + totalAmount +
        ", taxAmount=" + taxAmount +
        ", currency=" + currency +
        ", exchangeRateType=" + exchangeRateType +
        ", exchangeRateQuotation=" + exchangeRateQuotation +
        ", exchangeRate=" + exchangeRate +
        ", periodCode=" + periodCode +
        ", periodName=" + periodName +
        ", invoiceStatusCode=" + invoiceStatusCode +
        ", invoiceStatusName=" + invoiceStatusName +
        ", accountPayee=" + accountPayee +
        ", accountTime=" + accountTime +
        ", issuer=" + issuer +
        ", issuedTime=" + issuedTime +
        ", reviewer=" + reviewer +
        ", reviewedTime=" + reviewedTime +
        ", poster=" + poster +
        ", postedTime=" + postedTime +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
