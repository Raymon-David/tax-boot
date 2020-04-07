package com.foryou.tax.pojo.classificationcode;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 税收分类编码临时表
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
@TableName("INVOICE_TAX_CLASSIFICATION_CODE_TEMP")
public class InvoiceTaxClassificationCodeTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CLASS_ID")
    private Integer classId;
    /**
     * 合同类型
     */
    @TableField("CONTRACT_TYPE")
    private String contractType;
    @TableField("CONTRACT_TYPE_N")
    private String contractTypeN;
    /**
     * 设备名称
     */
    @TableField("DIVISION")
    private String division;
    @TableField("DIVISION_N")
    private String divisionN;
    /**
     * 现金流项目
     */
    @TableField("CF_ITEM")
    private String cfItem;
    @TableField("CF_ITEM_N")
    private String cfItemN;
    /**
     * 现金流类型
     */
    @TableField("CF_TYPE")
    private String cfType;
    @TableField("CF_TYPE_N")
    private String cfTypeN;
    /**
     * 税收分类编码
     */
    @TableField("TAX_CLASS_NUM")
    private String taxClassNum;
    /**
     * 版本
     */
    @TableField("GOODS_VERSION")
    private String goodsVersion;
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


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractTypeN() {
        return contractTypeN;
    }

    public void setContractTypeN(String contractTypeN) {
        this.contractTypeN = contractTypeN;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivisionN() {
        return divisionN;
    }

    public void setDivisionN(String divisionN) {
        this.divisionN = divisionN;
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

    public String getCfType() {
        return cfType;
    }

    public void setCfType(String cfType) {
        this.cfType = cfType;
    }

    public String getCfTypeN() {
        return cfTypeN;
    }

    public void setCfTypeN(String cfTypeN) {
        this.cfTypeN = cfTypeN;
    }

    public String getTaxClassNum() {
        return taxClassNum;
    }

    public void setTaxClassNum(String taxClassNum) {
        this.taxClassNum = taxClassNum;
    }

    public String getGoodsVersion() {
        return goodsVersion;
    }

    public void setGoodsVersion(String goodsVersion) {
        this.goodsVersion = goodsVersion;
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
        return "InvoiceTaxClassificationCodeTemp{" +
        ", classId=" + classId +
        ", contractType=" + contractType +
        ", contractTypeN=" + contractTypeN +
        ", division=" + division +
        ", divisionN=" + divisionN +
        ", cfItem=" + cfItem +
        ", cfItemN=" + cfItemN +
        ", cfType=" + cfType +
        ", cfTypeN=" + cfTypeN +
        ", taxClassNum=" + taxClassNum +
        ", goodsVersion=" + goodsVersion +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
