package com.foryou.tax.pojo.allinvoice;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 发票总表临时表
 * </p>
 *
 * @author raymon
 * @since 2020-04-10
 */
@TableName("ALL_INVOICE_INFO_TEMP")
public class AllInvoiceInfoTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("COMPANY_ID")
    private String companyId;
    @TableField("PROJECT_ID")
    private String projectId;
    @TableField("PROJECT_NUMBER")
    private String projectNumber;
    @TableField("PROJECT_NAME")
    private String projectName;
    @TableField("CONTRACT_ID")
    private String contractId;
    @TableField("CONTRACT_NUMBER")
    private String contractNumber;
    @TableField("CONTRACT_NAME")
    private String contractName;
    @TableField("INCEPTION_OF_LEASE")
    private Date inceptionOfLease;
    @TableField("CONTRACT_STATUS")
    private String contractStatus;
    @TableField("CONTRACT_STATUS_DESC")
    private String contractStatusDesc;
    @TableField("BILLING_METHOD")
    private String billingMethod;
    @TableField("BILLING_METHOD_DESC")
    private String billingMethodDesc;
    @TableField("BILLING_OBJECT_ID")
    private String billingObjectId;
    @TableField("BILLING_OBJECT_CODE")
    private String billingObjectCode;
    @TableField("BILLING_OBJECT_NAME")
    private String billingObjectName;
    @TableField("BILLING_OBJECT_ID_CARD")
    private String billingObjectIdCard;
    @TableField("OBJECT_TAXPAYER_TYPE")
    private String objectTaxpayerType;
    @TableField("OBJECT_TAXPAYER_TYPE_DESC")
    private String objectTaxpayerTypeDesc;
    @TableField("OBJECT_TAX_REGISTRY_NUM")
    private String objectTaxRegistryNum;
    @TableField("BILL_OBJECT_BP_CLASS")
    private String billObjectBpClass;
    @TableField("BILL_OBJECT_BP_CLASS_DESC")
    private String billObjectBpClassDesc;
    @TableField("TAX_TYPE_VAT")
    private String taxTypeVat;
    @TableField("DESCRIPTION")
    private String description;
    @TableField("INVOICE_TITLE")
    private String invoiceTitle;
    @TableField("INVOICE_BP_ADDRESS_PHONE_NUM")
    private String invoiceBpAddressPhoneNum;
    @TableField("INVOICE_BP_BANK_ACCOUNT")
    private String invoiceBpBankAccount;
    @TableField("TAX_REGISTRY_NUM")
    private String taxRegistryNum;
    @TableField("PRJ_SEARCH_TERM_1")
    private String prjSearchTerm1;
    @TableField("PRJ_SEARCH_TERM_2")
    private String prjSearchTerm2;
    @TableField("CON_SEARCH_TERM_1")
    private String conSearchTerm1;
    @TableField("CON_SEARCH_TERM_2")
    private String conSearchTerm2;
    @TableField("DOCUMENT_TYPE")
    private String documentType;
    @TableField("BP_NAME")
    private String bpName;
    @TableField("CASHFLOW_ID")
    private String cashflowId;
    @TableField("CF_ITEM")
    private String cfItem;
    @TableField("CF_ITEM_DESC")
    private String cfItemDesc;
    @TableField("TIMES")
    private String times;
    @TableField("LAST_RECEIVED_DATE")
    private Date lastReceivedDate;
    @TableField("DUE_DATE")
    private Date dueDate;
    @TableField("DUE_AMOUNT")
    private BigDecimal dueAmount;
    @TableField("PRINCIPAL")
    private BigDecimal principal;
    @TableField("INTEREST")
    private BigDecimal interest;
    @TableField("RECEIVED_AMOUNT")
    private BigDecimal receivedAmount;
    @TableField("RECEIVED_PRINCIPAL")
    private BigDecimal receivedPrincipal;
    @TableField("RECEIVED_INTEREST")
    private BigDecimal receivedInterest;
    @TableField("NOTRECEIVED_AMOUNT")
    private BigDecimal notreceivedAmount;
    @TableField("NOTRECEIVED_PRINCIPAL")
    private BigDecimal notreceivedPrincipal;
    @TableField("NOTRECEIVED_INTEREST")
    private BigDecimal notreceivedInterest;
    @TableField("BILLING_AMOUNT")
    private BigDecimal billingAmount;
    @TableField("BILLING_PRINCIPAL")
    private BigDecimal billingPrincipal;
    @TableField("BILLING_INTEREST")
    private BigDecimal billingInterest;
    @TableField("NOTBILLING_AMOUNT")
    private BigDecimal notbillingAmount;
    @TableField("NOTBILLING_PRINCIPAL")
    private BigDecimal notbillingPrincipal;
    @TableField("NOTBILLING_INTEREST")
    private BigDecimal notbillingInterest;
    @TableField("VAT_DUE_AMOUNT")
    private BigDecimal vatDueAmount;
    @TableField("VAT_PRINCIPAL")
    private BigDecimal vatPrincipal;
    @TableField("VAT_INTEREST")
    private BigDecimal vatInterest;
    @TableField("NET_DUE_AMOUNT")
    private BigDecimal netDueAmount;
    @TableField("NET_PRINCIPAL")
    private BigDecimal netPrincipal;
    @TableField("NET_INTEREST")
    private BigDecimal netInterest;
    @TableField("CURRENCY")
    private String currency;
    @TableField("CURRENCY_DESC")
    private String currencyDesc;
    @TableField("EXCHANGE_RATE")
    private String exchangeRate;
    @TableField("EXCHANGE_RATE_TYPE")
    private String exchangeRateType;
    @TableField("EXCHANGE_RATE_TYPE_DESC")
    private String exchangeRateTypeDesc;
    @TableField("EXCHANGE_RATE_QUOTATION")
    private String exchangeRateQuotation;
    @TableField("EXCHANGE_RATE_QUOTATION_DESC")
    private String exchangeRateQuotationDesc;
    @TableField("LEASE_CHANNEL_DESC")
    private String leaseChannelDesc;
    @TableField("BUSINESS_TYPE")
    private String businessType;
    @TableField("BUSINESS_TYPE_DESC")
    private String businessTypeDesc;
    @TableField("DIVISION")
    private String division;
    @TableField("DIVISION_DESC")
    private String divisionDesc;
    @TableField("BILLING_FREQUENCY")
    private String billingFrequency;
    @TableField("BILLING_FREQUENCY_N")
    private String billingFrequencyN;
    @TableField("BILLING_WAY")
    private String billingWay;
    @TableField("BILLING_WAY_N")
    private String billingWayN;
    @TableField("NEED_BILLING_AMOUNT")
    private BigDecimal needBillingAmount;
    @TableField("INV_BILLING_OBJECT_NAME")
    private String invBillingObjectName;
    @TableField("SMALL_SCALE_TAXPAYER")
    private String smallScaleTaxpayer;
    @TableField("INVOICE_INFO_CONFIRM")
    private String invoiceInfoConfirm;
    @TableField("INVOICE_INFO_CONFIRM_DESC")
    private String invoiceInfoConfirmDesc;
    @TableField("LAST_WRITE_OFF_DATE")
    private Date lastWriteOffDate;
    @TableField("EXPEDITED_FLAG")
    private String expeditedFlag;
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


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Date getInceptionOfLease() {
        return inceptionOfLease;
    }

    public void setInceptionOfLease(Date inceptionOfLease) {
        this.inceptionOfLease = inceptionOfLease;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractStatusDesc() {
        return contractStatusDesc;
    }

    public void setContractStatusDesc(String contractStatusDesc) {
        this.contractStatusDesc = contractStatusDesc;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public String getBillingMethodDesc() {
        return billingMethodDesc;
    }

    public void setBillingMethodDesc(String billingMethodDesc) {
        this.billingMethodDesc = billingMethodDesc;
    }

    public String getBillingObjectId() {
        return billingObjectId;
    }

    public void setBillingObjectId(String billingObjectId) {
        this.billingObjectId = billingObjectId;
    }

    public String getBillingObjectCode() {
        return billingObjectCode;
    }

    public void setBillingObjectCode(String billingObjectCode) {
        this.billingObjectCode = billingObjectCode;
    }

    public String getBillingObjectName() {
        return billingObjectName;
    }

    public void setBillingObjectName(String billingObjectName) {
        this.billingObjectName = billingObjectName;
    }

    public String getBillingObjectIdCard() {
        return billingObjectIdCard;
    }

    public void setBillingObjectIdCard(String billingObjectIdCard) {
        this.billingObjectIdCard = billingObjectIdCard;
    }

    public String getObjectTaxpayerType() {
        return objectTaxpayerType;
    }

    public void setObjectTaxpayerType(String objectTaxpayerType) {
        this.objectTaxpayerType = objectTaxpayerType;
    }

    public String getObjectTaxpayerTypeDesc() {
        return objectTaxpayerTypeDesc;
    }

    public void setObjectTaxpayerTypeDesc(String objectTaxpayerTypeDesc) {
        this.objectTaxpayerTypeDesc = objectTaxpayerTypeDesc;
    }

    public String getObjectTaxRegistryNum() {
        return objectTaxRegistryNum;
    }

    public void setObjectTaxRegistryNum(String objectTaxRegistryNum) {
        this.objectTaxRegistryNum = objectTaxRegistryNum;
    }

    public String getBillObjectBpClass() {
        return billObjectBpClass;
    }

    public void setBillObjectBpClass(String billObjectBpClass) {
        this.billObjectBpClass = billObjectBpClass;
    }

    public String getBillObjectBpClassDesc() {
        return billObjectBpClassDesc;
    }

    public void setBillObjectBpClassDesc(String billObjectBpClassDesc) {
        this.billObjectBpClassDesc = billObjectBpClassDesc;
    }

    public String getTaxTypeVat() {
        return taxTypeVat;
    }

    public void setTaxTypeVat(String taxTypeVat) {
        this.taxTypeVat = taxTypeVat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceBpAddressPhoneNum() {
        return invoiceBpAddressPhoneNum;
    }

    public void setInvoiceBpAddressPhoneNum(String invoiceBpAddressPhoneNum) {
        this.invoiceBpAddressPhoneNum = invoiceBpAddressPhoneNum;
    }

    public String getInvoiceBpBankAccount() {
        return invoiceBpBankAccount;
    }

    public void setInvoiceBpBankAccount(String invoiceBpBankAccount) {
        this.invoiceBpBankAccount = invoiceBpBankAccount;
    }

    public String getTaxRegistryNum() {
        return taxRegistryNum;
    }

    public void setTaxRegistryNum(String taxRegistryNum) {
        this.taxRegistryNum = taxRegistryNum;
    }

    public String getPrjSearchTerm1() {
        return prjSearchTerm1;
    }

    public void setPrjSearchTerm1(String prjSearchTerm1) {
        this.prjSearchTerm1 = prjSearchTerm1;
    }

    public String getPrjSearchTerm2() {
        return prjSearchTerm2;
    }

    public void setPrjSearchTerm2(String prjSearchTerm2) {
        this.prjSearchTerm2 = prjSearchTerm2;
    }

    public String getConSearchTerm1() {
        return conSearchTerm1;
    }

    public void setConSearchTerm1(String conSearchTerm1) {
        this.conSearchTerm1 = conSearchTerm1;
    }

    public String getConSearchTerm2() {
        return conSearchTerm2;
    }

    public void setConSearchTerm2(String conSearchTerm2) {
        this.conSearchTerm2 = conSearchTerm2;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getBpName() {
        return bpName;
    }

    public void setBpName(String bpName) {
        this.bpName = bpName;
    }

    public String getCashflowId() {
        return cashflowId;
    }

    public void setCashflowId(String cashflowId) {
        this.cashflowId = cashflowId;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Date getLastReceivedDate() {
        return lastReceivedDate;
    }

    public void setLastReceivedDate(Date lastReceivedDate) {
        this.lastReceivedDate = lastReceivedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getReceivedPrincipal() {
        return receivedPrincipal;
    }

    public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
        this.receivedPrincipal = receivedPrincipal;
    }

    public BigDecimal getReceivedInterest() {
        return receivedInterest;
    }

    public void setReceivedInterest(BigDecimal receivedInterest) {
        this.receivedInterest = receivedInterest;
    }

    public BigDecimal getNotreceivedAmount() {
        return notreceivedAmount;
    }

    public void setNotreceivedAmount(BigDecimal notreceivedAmount) {
        this.notreceivedAmount = notreceivedAmount;
    }

    public BigDecimal getNotreceivedPrincipal() {
        return notreceivedPrincipal;
    }

    public void setNotreceivedPrincipal(BigDecimal notreceivedPrincipal) {
        this.notreceivedPrincipal = notreceivedPrincipal;
    }

    public BigDecimal getNotreceivedInterest() {
        return notreceivedInterest;
    }

    public void setNotreceivedInterest(BigDecimal notreceivedInterest) {
        this.notreceivedInterest = notreceivedInterest;
    }

    public BigDecimal getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(BigDecimal billingAmount) {
        this.billingAmount = billingAmount;
    }

    public BigDecimal getBillingPrincipal() {
        return billingPrincipal;
    }

    public void setBillingPrincipal(BigDecimal billingPrincipal) {
        this.billingPrincipal = billingPrincipal;
    }

    public BigDecimal getBillingInterest() {
        return billingInterest;
    }

    public void setBillingInterest(BigDecimal billingInterest) {
        this.billingInterest = billingInterest;
    }

    public BigDecimal getNotbillingAmount() {
        return notbillingAmount;
    }

    public void setNotbillingAmount(BigDecimal notbillingAmount) {
        this.notbillingAmount = notbillingAmount;
    }

    public BigDecimal getNotbillingPrincipal() {
        return notbillingPrincipal;
    }

    public void setNotbillingPrincipal(BigDecimal notbillingPrincipal) {
        this.notbillingPrincipal = notbillingPrincipal;
    }

    public BigDecimal getNotbillingInterest() {
        return notbillingInterest;
    }

    public void setNotbillingInterest(BigDecimal notbillingInterest) {
        this.notbillingInterest = notbillingInterest;
    }

    public BigDecimal getVatDueAmount() {
        return vatDueAmount;
    }

    public void setVatDueAmount(BigDecimal vatDueAmount) {
        this.vatDueAmount = vatDueAmount;
    }

    public BigDecimal getVatPrincipal() {
        return vatPrincipal;
    }

    public void setVatPrincipal(BigDecimal vatPrincipal) {
        this.vatPrincipal = vatPrincipal;
    }

    public BigDecimal getVatInterest() {
        return vatInterest;
    }

    public void setVatInterest(BigDecimal vatInterest) {
        this.vatInterest = vatInterest;
    }

    public BigDecimal getNetDueAmount() {
        return netDueAmount;
    }

    public void setNetDueAmount(BigDecimal netDueAmount) {
        this.netDueAmount = netDueAmount;
    }

    public BigDecimal getNetPrincipal() {
        return netPrincipal;
    }

    public void setNetPrincipal(BigDecimal netPrincipal) {
        this.netPrincipal = netPrincipal;
    }

    public BigDecimal getNetInterest() {
        return netInterest;
    }

    public void setNetInterest(BigDecimal netInterest) {
        this.netInterest = netInterest;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeRateType() {
        return exchangeRateType;
    }

    public void setExchangeRateType(String exchangeRateType) {
        this.exchangeRateType = exchangeRateType;
    }

    public String getExchangeRateTypeDesc() {
        return exchangeRateTypeDesc;
    }

    public void setExchangeRateTypeDesc(String exchangeRateTypeDesc) {
        this.exchangeRateTypeDesc = exchangeRateTypeDesc;
    }

    public String getExchangeRateQuotation() {
        return exchangeRateQuotation;
    }

    public void setExchangeRateQuotation(String exchangeRateQuotation) {
        this.exchangeRateQuotation = exchangeRateQuotation;
    }

    public String getExchangeRateQuotationDesc() {
        return exchangeRateQuotationDesc;
    }

    public void setExchangeRateQuotationDesc(String exchangeRateQuotationDesc) {
        this.exchangeRateQuotationDesc = exchangeRateQuotationDesc;
    }

    public String getLeaseChannelDesc() {
        return leaseChannelDesc;
    }

    public void setLeaseChannelDesc(String leaseChannelDesc) {
        this.leaseChannelDesc = leaseChannelDesc;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivisionDesc() {
        return divisionDesc;
    }

    public void setDivisionDesc(String divisionDesc) {
        this.divisionDesc = divisionDesc;
    }

    public String getBillingFrequency() {
        return billingFrequency;
    }

    public void setBillingFrequency(String billingFrequency) {
        this.billingFrequency = billingFrequency;
    }

    public String getBillingFrequencyN() {
        return billingFrequencyN;
    }

    public void setBillingFrequencyN(String billingFrequencyN) {
        this.billingFrequencyN = billingFrequencyN;
    }

    public String getBillingWay() {
        return billingWay;
    }

    public void setBillingWay(String billingWay) {
        this.billingWay = billingWay;
    }

    public String getBillingWayN() {
        return billingWayN;
    }

    public void setBillingWayN(String billingWayN) {
        this.billingWayN = billingWayN;
    }

    public BigDecimal getNeedBillingAmount() {
        return needBillingAmount;
    }

    public void setNeedBillingAmount(BigDecimal needBillingAmount) {
        this.needBillingAmount = needBillingAmount;
    }

    public String getInvBillingObjectName() {
        return invBillingObjectName;
    }

    public void setInvBillingObjectName(String invBillingObjectName) {
        this.invBillingObjectName = invBillingObjectName;
    }

    public String getSmallScaleTaxpayer() {
        return smallScaleTaxpayer;
    }

    public void setSmallScaleTaxpayer(String smallScaleTaxpayer) {
        this.smallScaleTaxpayer = smallScaleTaxpayer;
    }

    public String getInvoiceInfoConfirm() {
        return invoiceInfoConfirm;
    }

    public void setInvoiceInfoConfirm(String invoiceInfoConfirm) {
        this.invoiceInfoConfirm = invoiceInfoConfirm;
    }

    public String getInvoiceInfoConfirmDesc() {
        return invoiceInfoConfirmDesc;
    }

    public void setInvoiceInfoConfirmDesc(String invoiceInfoConfirmDesc) {
        this.invoiceInfoConfirmDesc = invoiceInfoConfirmDesc;
    }

    public Date getLastWriteOffDate() {
        return lastWriteOffDate;
    }

    public void setLastWriteOffDate(Date lastWriteOffDate) {
        this.lastWriteOffDate = lastWriteOffDate;
    }

    public String getExpeditedFlag() {
        return expeditedFlag;
    }

    public void setExpeditedFlag(String expeditedFlag) {
        this.expeditedFlag = expeditedFlag;
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
        return "AllInvoiceInfoTemp{" +
                ", companyId=" + companyId +
                ", projectId=" + projectId +
                ", projectNumber=" + projectNumber +
                ", projectName=" + projectName +
                ", contractId=" + contractId +
                ", contractNumber=" + contractNumber +
                ", contractName=" + contractName +
                ", inceptionOfLease=" + inceptionOfLease +
                ", contractStatus=" + contractStatus +
                ", contractStatusDesc=" + contractStatusDesc +
                ", billingMethod=" + billingMethod +
                ", billingMethodDesc=" + billingMethodDesc +
                ", billingObjectId=" + billingObjectId +
                ", billingObjectCode=" + billingObjectCode +
                ", billingObjectName=" + billingObjectName +
                ", billingObjectIdCard=" + billingObjectIdCard +
                ", objectTaxpayerType=" + objectTaxpayerType +
                ", objectTaxpayerTypeDesc=" + objectTaxpayerTypeDesc +
                ", objectTaxRegistryNum=" + objectTaxRegistryNum +
                ", billObjectBpClass=" + billObjectBpClass +
                ", billObjectBpClassDesc=" + billObjectBpClassDesc +
                ", taxTypeVat=" + taxTypeVat +
                ", description=" + description +
                ", invoiceTitle=" + invoiceTitle +
                ", invoiceBpAddressPhoneNum=" + invoiceBpAddressPhoneNum +
                ", invoiceBpBankAccount=" + invoiceBpBankAccount +
                ", taxRegistryNum=" + taxRegistryNum +
                ", prjSearchTerm1=" + prjSearchTerm1 +
                ", prjSearchTerm2=" + prjSearchTerm2 +
                ", conSearchTerm1=" + conSearchTerm1 +
                ", conSearchTerm2=" + conSearchTerm2 +
                ", documentType=" + documentType +
                ", bpName=" + bpName +
                ", cashflowId=" + cashflowId +
                ", cfItem=" + cfItem +
                ", cfItemDesc=" + cfItemDesc +
                ", times=" + times +
                ", lastReceivedDate=" + lastReceivedDate +
                ", dueDate=" + dueDate +
                ", dueAmount=" + dueAmount +
                ", principal=" + principal +
                ", interest=" + interest +
                ", receivedAmount=" + receivedAmount +
                ", receivedPrincipal=" + receivedPrincipal +
                ", receivedInterest=" + receivedInterest +
                ", notreceivedAmount=" + notreceivedAmount +
                ", notreceivedPrincipal=" + notreceivedPrincipal +
                ", notreceivedInterest=" + notreceivedInterest +
                ", billingAmount=" + billingAmount +
                ", billingPrincipal=" + billingPrincipal +
                ", billingInterest=" + billingInterest +
                ", notbillingAmount=" + notbillingAmount +
                ", notbillingPrincipal=" + notbillingPrincipal +
                ", notbillingInterest=" + notbillingInterest +
                ", vatDueAmount=" + vatDueAmount +
                ", vatPrincipal=" + vatPrincipal +
                ", vatInterest=" + vatInterest +
                ", netDueAmount=" + netDueAmount +
                ", netPrincipal=" + netPrincipal +
                ", netInterest=" + netInterest +
                ", currency=" + currency +
                ", currencyDesc=" + currencyDesc +
                ", exchangeRate=" + exchangeRate +
                ", exchangeRateType=" + exchangeRateType +
                ", exchangeRateTypeDesc=" + exchangeRateTypeDesc +
                ", exchangeRateQuotation=" + exchangeRateQuotation +
                ", exchangeRateQuotationDesc=" + exchangeRateQuotationDesc +
                ", leaseChannelDesc=" + leaseChannelDesc +
                ", businessType=" + businessType +
                ", businessTypeDesc=" + businessTypeDesc +
                ", division=" + division +
                ", divisionDesc=" + divisionDesc +
                ", billingFrequency=" + billingFrequency +
                ", billingFrequencyN=" + billingFrequencyN +
                ", billingWay=" + billingWay +
                ", billingWayN=" + billingWayN +
                ", needBillingAmount=" + needBillingAmount +
                ", invBillingObjectName=" + invBillingObjectName +
                ", smallScaleTaxpayer=" + smallScaleTaxpayer +
                ", invoiceInfoConfirm=" + invoiceInfoConfirm +
                ", invoiceInfoConfirmDesc=" + invoiceInfoConfirmDesc +
                ", lastWriteOffDate=" + lastWriteOffDate +
                ", expeditedFlag=" + expeditedFlag +
                ", createdBy=" + createdBy +
                ", createTime=" + createTime +
                ", updatedBy=" + updatedBy +
                ", updateTime=" + updateTime +
                "}";
    }
}
