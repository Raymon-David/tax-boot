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
 * 发票明细表
 * </p>
 *
 * @author raymon
 * @since 2020-03-24
 */
@TableName("ALL_INVOICE_DETAIL")
public class AllInvoiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发票明细表ID/PK
     */
    @TableId(value = "INVOICE_DETAIL_ID", type = IdType.AUTO)
    private Integer invoiceDetailId;
    /**
     * 发票表ID
     */
    @TableField("INVOICE_ID")
    private Integer invoiceId;
    /**
     * 现金流ID
     */
    @TableField("CASHFLOW_ID")
    private Integer cashflowId;
    /**
     * 现金流项目CODE
     */
    @TableField("CASHFLOW_ITEM_CODE")
    private String cashflowItemCode;
    /**
     * 商品ID
     */
    @TableField("PRODUCT_ID")
    private Integer productId;
    /**
     * 商品名称
     */
    @TableField("PRODUCT_NAME")
    private String productName;
    /**
     * 规格型号
     */
    @TableField("SPEC")
    private String spec;
    /**
     * 数量
     */
    @TableField("TAX_QUANTITY")
    private Integer taxQuantity;
    /**
     * 计量单位
     */
    @TableField("UNIT")
    private String unit;
    /**
     * 含税单价
     */
    @TableField("TAX_PRICE")
    private BigDecimal taxPrice;
    /**
     * 不含税单价
     */
    @TableField("TAX_NET_PRICE")
    private BigDecimal taxNetPrice;
    /**
     * 税种
     */
    @TableField("TAX_TYPE")
    private String taxType;
    /**
     * 税率
     */
    @TableField("TAX_RATE")
    private Integer taxRate;
    /**
     * 含税标志
     */
    @TableField("TAX_INCLUDED_FLAG")
    private String taxIncludedFlag;
    /**
     * 含税金额
     */
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    /**
     * 税额
     */
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;
    /**
     * 不含税金额
     */
    @TableField("TAX_NET_AMOUNT")
    private BigDecimal taxNetAmount;
    /**
     * 行备注/行摘要
     */
    @TableField("INVOICE_DETAIL_MEMO")
    private String invoiceDetailMemo;
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


    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getCashflowId() {
        return cashflowId;
    }

    public void setCashflowId(Integer cashflowId) {
        this.cashflowId = cashflowId;
    }

    public String getCashflowItemCode() {
        return cashflowItemCode;
    }

    public void setCashflowItemCode(String cashflowItemCode) {
        this.cashflowItemCode = cashflowItemCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getTaxQuantity() {
        return taxQuantity;
    }

    public void setTaxQuantity(Integer taxQuantity) {
        this.taxQuantity = taxQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTaxNetPrice() {
        return taxNetPrice;
    }

    public void setTaxNetPrice(BigDecimal taxNetPrice) {
        this.taxNetPrice = taxNetPrice;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxIncludedFlag() {
        return taxIncludedFlag;
    }

    public void setTaxIncludedFlag(String taxIncludedFlag) {
        this.taxIncludedFlag = taxIncludedFlag;
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

    public BigDecimal getTaxNetAmount() {
        return taxNetAmount;
    }

    public void setTaxNetAmount(BigDecimal taxNetAmount) {
        this.taxNetAmount = taxNetAmount;
    }

    public String getInvoiceDetailMemo() {
        return invoiceDetailMemo;
    }

    public void setInvoiceDetailMemo(String invoiceDetailMemo) {
        this.invoiceDetailMemo = invoiceDetailMemo;
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
        return "AllInvoiceDetatil{" +
                ", invoiceDetailId=" + invoiceDetailId +
                ", invoiceId=" + invoiceId +
                ", cashflowId=" + cashflowId +
                ", cashflowItemCode=" + cashflowItemCode +
                ", productId=" + productId +
                ", productName=" + productName +
                ", spec=" + spec +
                ", taxQuantity=" + taxQuantity +
                ", unit=" + unit +
                ", taxPrice=" + taxPrice +
                ", taxNetPrice=" + taxNetPrice +
                ", taxType=" + taxType +
                ", taxRate=" + taxRate +
                ", taxIncludedFlag=" + taxIncludedFlag +
                ", totalAmount=" + totalAmount +
                ", taxAmount=" + taxAmount +
                ", taxNetAmount=" + taxNetAmount +
                ", invoiceDetailMemo=" + invoiceDetailMemo +
                ", createdBy=" + createdBy +
                ", createTime=" + createTime +
                ", updatedBy=" + updatedBy +
                ", updateTime=" + updateTime +
                "}";
    }
}
