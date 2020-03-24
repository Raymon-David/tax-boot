package com.foryou.tax.pojo.companies;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 公司信息表
 * </p>
 *
 * @author raymon
 * @since 2020-03-24
 */
@TableName("FY_COMPANIES")
public class FyCompanies implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    @TableId(value = "COMPANY_ID", type = IdType.AUTO)
    private Integer companyId;
    /**
     * 公司code
     */
    @TableField("COMPANY_CODE")
    private String companyCode;
    /**
     * 公司状态，默认 1 为存续
     */
    @TableField("COMPANY_TYPE")
    private Integer companyType;
    /**
     * 公司地址
     */
    @TableField("COMPANY_ADDRESS")
    private String companyAddress;
    /**
     * 公司电话
     */
    @TableField("COMPANY_PHONE")
    private String companyPhone;
    /**
     * 存续开始时间
     */
    @TableField("ACTIVE_START_DATE")
    private Date activeStartDate;
    /**
     * 存续结束时间
     */
    @TableField("ACTIVE_END_DATE")
    private Date activeEndDate;
    /**
     * 公司发票抬头
     */
    @TableField("INVOICE_TITLE")
    private String invoiceTitle;
    /**
     * 公司税收编码
     */
    @TableField("TAX_REGISTRY_NUM")
    private String taxRegistryNum;
    /**
     * 公司发票地址电话
     */
    @TableField("INVOICE_COMPANY_ADDRESS_PHONE_NUM")
    private String invoiceCompanyAddressPhoneNum;
    /**
     * 公司账户所属银行
     */
    @TableField("INVOICE_COMPANY_BANK_ACCOUNT")
    private String invoiceCompanyBankAccount;
    /**
     * 公司全称
     */
    @TableField("COMPANY_FULL_NAME")
    private String companyFullName;
    /**
     * 公司简称
     */
    @TableField("COMPANY_SHORT_NAME")
    private String companyShortName;
    /**
     * 公司账号
     */
    @TableField("COMPANY_BANK_ACCOUNT_ID")
    private String companyBankAccountId;
    /**
     * 公司电子发票余量
     */
    @TableField("COMPANY_ELE_MARGIN")
    private Integer companyEleMargin;
    /**
     * 公司电子合同id
     */
    @TableField("COMPANY_CUSTOMER_ID")
    private String companyCustomerId;
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


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Date getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(Date activeStartDate) {
        this.activeStartDate = activeStartDate;
    }

    public Date getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(Date activeEndDate) {
        this.activeEndDate = activeEndDate;
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

    public String getInvoiceCompanyAddressPhoneNum() {
        return invoiceCompanyAddressPhoneNum;
    }

    public void setInvoiceCompanyAddressPhoneNum(String invoiceCompanyAddressPhoneNum) {
        this.invoiceCompanyAddressPhoneNum = invoiceCompanyAddressPhoneNum;
    }

    public String getInvoiceCompanyBankAccount() {
        return invoiceCompanyBankAccount;
    }

    public void setInvoiceCompanyBankAccount(String invoiceCompanyBankAccount) {
        this.invoiceCompanyBankAccount = invoiceCompanyBankAccount;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyBankAccountId() {
        return companyBankAccountId;
    }

    public void setCompanyBankAccountId(String companyBankAccountId) {
        this.companyBankAccountId = companyBankAccountId;
    }

    public Integer getCompanyEleMargin() {
        return companyEleMargin;
    }

    public void setCompanyEleMargin(Integer companyEleMargin) {
        this.companyEleMargin = companyEleMargin;
    }

    public String getCompanyCustomerId() {
        return companyCustomerId;
    }

    public void setCompanyCustomerId(String companyCustomerId) {
        this.companyCustomerId = companyCustomerId;
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
        return "FyCompanies{" +
        ", companyId=" + companyId +
        ", companyCode=" + companyCode +
        ", companyType=" + companyType +
        ", companyAddress=" + companyAddress +
        ", companyPhone=" + companyPhone +
        ", activeStartDate=" + activeStartDate +
        ", activeEndDate=" + activeEndDate +
        ", invoiceTitle=" + invoiceTitle +
        ", taxRegistryNum=" + taxRegistryNum +
        ", invoiceCompanyAddressPhoneNum=" + invoiceCompanyAddressPhoneNum +
        ", invoiceCompanyBankAccount=" + invoiceCompanyBankAccount +
        ", companyFullName=" + companyFullName +
        ", companyShortName=" + companyShortName +
        ", companyBankAccountId=" + companyBankAccountId +
        ", companyEleMargin=" + companyEleMargin +
        ", companyCustomerId=" + companyCustomerId +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
