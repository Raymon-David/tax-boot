package com.foryou.tax.pojo.classificationcode;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 税收分类编码
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
@TableName("INVOICE_TAX_CLASSIFICATION_CODE")
public class InvoiceTaxClassificationCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "INVOICE_TAX_CLASSIFICATION_ID", type = IdType.AUTO)
    private Integer invoiceTaxClassificationId;
    /**
     * 税收分类编码
     */
    @TableField("TAX_CLASSIFICATION_CODE")
    private String taxClassificationCode;
    /**
     * 商品或服务code
     */
    @TableField("GOODS_CODE")
    private String goodsCode;
    /**
     * 商品或服务name LEASE-融资租赁 / LEASEBACK-售后回租
     */
    @TableField("GOODS_NAME")
    private String goodsName;
    /**
     * 商品或服务说明
     */
    @TableField("GOODS_DESC")
    private String goodsDesc;
    /**
     * 产品线CODE
     */
    @TableField("PRODUCT_CODE")
    private String productCode;
    /**
     * 产品线NAME
     */
    @TableField("PRODUCT_NAME")
    private String productName;
    /**
     * 现金流项目CODE
     */
    @TableField("CASHFLOW_ITEM_CODE")
    private String cashflowItemCode;
    /**
     * 现金流项目NAME
     */
    @TableField("CASHFLOW_ITEM_NAME")
    private String cashflowItemName;
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


    public Integer getInvoiceTaxClassificationId() {
        return invoiceTaxClassificationId;
    }

    public void setInvoiceTaxClassificationId(Integer invoiceTaxClassificationId) {
        this.invoiceTaxClassificationId = invoiceTaxClassificationId;
    }

    public String getTaxClassificationCode() {
        return taxClassificationCode;
    }

    public void setTaxClassificationCode(String taxClassificationCode) {
        this.taxClassificationCode = taxClassificationCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCashflowItemCode() {
        return cashflowItemCode;
    }

    public void setCashflowItemCode(String cashflowItemCode) {
        this.cashflowItemCode = cashflowItemCode;
    }

    public String getCashflowItemName() {
        return cashflowItemName;
    }

    public void setCashflowItemName(String cashflowItemName) {
        this.cashflowItemName = cashflowItemName;
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
        return "InvoiceTaxClassificationCode{" +
        ", invoiceTaxClassificationId=" + invoiceTaxClassificationId +
        ", taxClassificationCode=" + taxClassificationCode +
        ", goodsCode=" + goodsCode +
        ", goodsName=" + goodsName +
        ", goodsDesc=" + goodsDesc +
        ", productCode=" + productCode +
        ", productName=" + productName +
        ", cashflowItemCode=" + cashflowItemCode +
        ", cashflowItemName=" + cashflowItemName +
        ", goodsVersion=" + goodsVersion +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
