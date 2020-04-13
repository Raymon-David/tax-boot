package com.foryou.tax.pojo.writeoffinfo;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 核销事务查询临时表
 * </p>
 *
 * @author raymon
 * @since 2020-04-13
 */
@TableName("WRITE_OFF_INFO_TEMP")
public class WriteOffInfoTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("WRITE_OFF_ID")
    private String writeOffId;
    /**
     * 合同编号
     */
    @TableField("CONTRACT_NUMBER")
    private String contractNumber;
    /**
     * 合同状态
     */
    @TableField("CONTRACT_STATUS_DESC")
    private String contractStatusDesc;
    @TableField("BP_ID_TENANT")
    private String bpIdTenant;
    /**
     * 合同客户名
     */
    @TableField("BP_NAME")
    private String bpName;
    /**
     * 核销现金流
     */
    @TableField("CF_ITEM")
    private String cfItem;
    /**
     * 核销现金流
     */
    @TableField("CF_ITEM_N")
    private String cfItemN;
    /**
     * 核销金额
     */
    @TableField("WRITE_OFF_DUE_AMOUNT")
    private BigDecimal writeOffDueAmount;
    /**
     * 核销本金
     */
    @TableField("WRITE_OFF_PRINCIPAL")
    private BigDecimal writeOffPrincipal;
    /**
     * 核销利息
     */
    @TableField("WRITE_OFF_INTEREST")
    private BigDecimal writeOffInterest;
    /**
     * 应收日期
     */
    @TableField("DUE_DATE_C")
    private Date dueDateC;
    /**
     * 核销期数
     */
    @TableField("TIMES")
    private String times;
    @TableField("LEASE_TIMES")
    private String leaseTimes;
    /**
     * 收款日期
     */
    @TableField("TRANSACTION_DATE_C")
    private Date transactionDateC;
    /**
     * 核销基准日
     */
    @TableField("WRITE_OFF_DATE_C")
    private Date writeOffDateC;
    /**
     * 核销日期
     */
    @TableField("JOURNAL_DATE_C")
    private Date journalDateC;
    /**
     * 收款银行名称
     */
    @TableField("BANK_BRANCH_NAME")
    private String bankBranchName;
    /**
     * 现金事务编号
     */
    @TableField("TRANSACTION_NUM")
    private String transactionNum;
    /**
     * 对方账户
     */
    @TableField("BP_BANK_ACCOUNT_NAME")
    private String bpBankAccountName;
    /**
     * 事务类型
     */
    @TableField("TRANSACTION_TYPE")
    private String transactionType;
    /**
     * 事务类型
     */
    @TableField("TRANSACTION_TYPE_N")
    private String transactionTypeN;
    /**
     * 收款类型
     */
    @TableField("RECEIPT_TYPE")
    private String receiptType;
    /**
     * 收款类型
     */
    @TableField("RECEIPT_TYPE_N")
    private String receiptTypeN;
    /**
     * 核销类型
     */
    @TableField("WRITE_OFF_TYPE")
    private String writeOffType;
    /**
     * 核销类型
     */
    @TableField("WRITE_OFF_TYPE_N")
    private String writeOffTypeN;
    /**
     * 核销分类
     */
    @TableField("WRITE_OFF_CLASSIFICATION")
    private String writeOffClassification;
    /**
     * 核销分类
     */
    @TableField("WRITE_OFF_CLASSIFICATION_N")
    private String writeOffClassificationN;
    /**
     * 款项分类
     */
    @TableField("COLLECTION_CLASSES")
    private String collectionClasses;
    /**
     * 款项分类
     */
    @TableField("COLLECTION_CLASSES_N")
    private String collectionClassesN;
    /**
     * 反冲标志
     */
    @TableField("REVERSED_FLAG")
    private String reversedFlag;
    /**
     * 反冲标志
     */
    @TableField("REVERSED_FLAG_N")
    private String reversedFlagN;
    /**
     * 反冲日期
     */
    @TableField("REVERSED_DATE")
    private Date reversedDate;
    /**
     * 收款备注
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 合同代理商
     */
    @TableField("BP_ID_AGENT_LEVEL1")
    private String bpIdAgentLevel1;
    /**
     * 合同代理商
     */
    @TableField("BP_ID_AGENT_LEVEL1_N")
    private String bpIdAgentLevel1N;
    /**
     * 核销备注
     */
    @TableField("WRITE_OFF_DES")
    private String writeOffDes;
    /**
     * 凭证编号
     */
    @TableField("JOURNAL_NUM")
    private String journalNum;
    @TableField("SAP_BELNR")
    private String sapBelnr;
    /**
     * 提交人
     */
    @TableField("CREATED_BY")
    private String createdBy;
    /**
     * 提交人
     */
    @TableField("CREATED_BY_N")
    private String createdByN;
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


    public String getWriteOffId() {
        return writeOffId;
    }

    public void setWriteOffId(String writeOffId) {
        this.writeOffId = writeOffId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractStatusDesc() {
        return contractStatusDesc;
    }

    public void setContractStatusDesc(String contractStatusDesc) {
        this.contractStatusDesc = contractStatusDesc;
    }

    public String getBpIdTenant() {
        return bpIdTenant;
    }

    public void setBpIdTenant(String bpIdTenant) {
        this.bpIdTenant = bpIdTenant;
    }

    public String getBpName() {
        return bpName;
    }

    public void setBpName(String bpName) {
        this.bpName = bpName;
    }

    public String getCfItem() {
        return cfItem;
    }

    public void setCfItem(String cfItem) {
        this.cfItem = cfItem;
    }

    public String getCfItemN() {
        return cfItemN;
    }

    public void setCfItemN(String cfItemN) {
        this.cfItemN = cfItemN;
    }

    public BigDecimal getWriteOffDueAmount() {
        return writeOffDueAmount;
    }

    public void setWriteOffDueAmount(BigDecimal writeOffDueAmount) {
        this.writeOffDueAmount = writeOffDueAmount;
    }

    public BigDecimal getWriteOffPrincipal() {
        return writeOffPrincipal;
    }

    public void setWriteOffPrincipal(BigDecimal writeOffPrincipal) {
        this.writeOffPrincipal = writeOffPrincipal;
    }

    public BigDecimal getWriteOffInterest() {
        return writeOffInterest;
    }

    public void setWriteOffInterest(BigDecimal writeOffInterest) {
        this.writeOffInterest = writeOffInterest;
    }

    public Date getDueDateC() {
        return dueDateC;
    }

    public void setDueDateC(Date dueDateC) {
        this.dueDateC = dueDateC;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getLeaseTimes() {
        return leaseTimes;
    }

    public void setLeaseTimes(String leaseTimes) {
        this.leaseTimes = leaseTimes;
    }

    public Date getTransactionDateC() {
        return transactionDateC;
    }

    public void setTransactionDateC(Date transactionDateC) {
        this.transactionDateC = transactionDateC;
    }

    public Date getWriteOffDateC() {
        return writeOffDateC;
    }

    public void setWriteOffDateC(Date writeOffDateC) {
        this.writeOffDateC = writeOffDateC;
    }

    public Date getJournalDateC() {
        return journalDateC;
    }

    public void setJournalDateC(Date journalDateC) {
        this.journalDateC = journalDateC;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getBpBankAccountName() {
        return bpBankAccountName;
    }

    public void setBpBankAccountName(String bpBankAccountName) {
        this.bpBankAccountName = bpBankAccountName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionTypeN() {
        return transactionTypeN;
    }

    public void setTransactionTypeN(String transactionTypeN) {
        this.transactionTypeN = transactionTypeN;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptTypeN() {
        return receiptTypeN;
    }

    public void setReceiptTypeN(String receiptTypeN) {
        this.receiptTypeN = receiptTypeN;
    }

    public String getWriteOffType() {
        return writeOffType;
    }

    public void setWriteOffType(String writeOffType) {
        this.writeOffType = writeOffType;
    }

    public String getWriteOffTypeN() {
        return writeOffTypeN;
    }

    public void setWriteOffTypeN(String writeOffTypeN) {
        this.writeOffTypeN = writeOffTypeN;
    }

    public String getWriteOffClassification() {
        return writeOffClassification;
    }

    public void setWriteOffClassification(String writeOffClassification) {
        this.writeOffClassification = writeOffClassification;
    }

    public String getWriteOffClassificationN() {
        return writeOffClassificationN;
    }

    public void setWriteOffClassificationN(String writeOffClassificationN) {
        this.writeOffClassificationN = writeOffClassificationN;
    }

    public String getCollectionClasses() {
        return collectionClasses;
    }

    public void setCollectionClasses(String collectionClasses) {
        this.collectionClasses = collectionClasses;
    }

    public String getCollectionClassesN() {
        return collectionClassesN;
    }

    public void setCollectionClassesN(String collectionClassesN) {
        this.collectionClassesN = collectionClassesN;
    }

    public String getReversedFlag() {
        return reversedFlag;
    }

    public void setReversedFlag(String reversedFlag) {
        this.reversedFlag = reversedFlag;
    }

    public String getReversedFlagN() {
        return reversedFlagN;
    }

    public void setReversedFlagN(String reversedFlagN) {
        this.reversedFlagN = reversedFlagN;
    }

    public Date getReversedDate() {
        return reversedDate;
    }

    public void setReversedDate(Date reversedDate) {
        this.reversedDate = reversedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBpIdAgentLevel1() {
        return bpIdAgentLevel1;
    }

    public void setBpIdAgentLevel1(String bpIdAgentLevel1) {
        this.bpIdAgentLevel1 = bpIdAgentLevel1;
    }

    public String getBpIdAgentLevel1N() {
        return bpIdAgentLevel1N;
    }

    public void setBpIdAgentLevel1N(String bpIdAgentLevel1N) {
        this.bpIdAgentLevel1N = bpIdAgentLevel1N;
    }

    public String getWriteOffDes() {
        return writeOffDes;
    }

    public void setWriteOffDes(String writeOffDes) {
        this.writeOffDes = writeOffDes;
    }

    public String getJournalNum() {
        return journalNum;
    }

    public void setJournalNum(String journalNum) {
        this.journalNum = journalNum;
    }

    public String getSapBelnr() {
        return sapBelnr;
    }

    public void setSapBelnr(String sapBelnr) {
        this.sapBelnr = sapBelnr;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByN() {
        return createdByN;
    }

    public void setCreatedByN(String createdByN) {
        this.createdByN = createdByN;
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
        return "WriteOffInfoTemp{" +
        ", writeOffId=" + writeOffId +
        ", contractNumber=" + contractNumber +
        ", contractStatusDesc=" + contractStatusDesc +
        ", bpIdTenant=" + bpIdTenant +
        ", bpName=" + bpName +
        ", cfItem=" + cfItem +
        ", cfItemN=" + cfItemN +
        ", writeOffDueAmount=" + writeOffDueAmount +
        ", writeOffPrincipal=" + writeOffPrincipal +
        ", writeOffInterest=" + writeOffInterest +
        ", dueDateC=" + dueDateC +
        ", times=" + times +
        ", leaseTimes=" + leaseTimes +
        ", transactionDateC=" + transactionDateC +
        ", writeOffDateC=" + writeOffDateC +
        ", journalDateC=" + journalDateC +
        ", bankBranchName=" + bankBranchName +
        ", transactionNum=" + transactionNum +
        ", bpBankAccountName=" + bpBankAccountName +
        ", transactionType=" + transactionType +
        ", transactionTypeN=" + transactionTypeN +
        ", receiptType=" + receiptType +
        ", receiptTypeN=" + receiptTypeN +
        ", writeOffType=" + writeOffType +
        ", writeOffTypeN=" + writeOffTypeN +
        ", writeOffClassification=" + writeOffClassification +
        ", writeOffClassificationN=" + writeOffClassificationN +
        ", collectionClasses=" + collectionClasses +
        ", collectionClassesN=" + collectionClassesN +
        ", reversedFlag=" + reversedFlag +
        ", reversedFlagN=" + reversedFlagN +
        ", reversedDate=" + reversedDate +
        ", description=" + description +
        ", bpIdAgentLevel1=" + bpIdAgentLevel1 +
        ", bpIdAgentLevel1N=" + bpIdAgentLevel1N +
        ", writeOffDes=" + writeOffDes +
        ", journalNum=" + journalNum +
        ", sapBelnr=" + sapBelnr +
        ", createdBy=" + createdBy +
        ", createdByN=" + createdByN +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
