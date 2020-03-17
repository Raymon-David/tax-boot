package com.foryou.tax.pojo.eleinvoice;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电子发票明细表
 * </p>
 *
 * @author raymon
 * @since 2020-03-17
 */
@TableName("ELE_INVOICE_DETAIL")
public class EleInvoiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 电子发票明细表ID/PK
     */
    @TableId(value = "ELE_INVOICE_DETAIL_ID", type = IdType.AUTO)
    private Integer eleInvoiceDetailId;
    /**
     * 电子发票ID
     */
    @TableField("ELE_INVOICE_ID")
    private Integer eleInvoiceId;
    /**
     * 发票表ID
     */
    @TableField("INVOICE_ID")
    private Integer invoiceId;
    /**
     * 发票明细表ID
     */
    @TableField("INVOICE_DETAIL_ID")
    private Integer invoiceDetailId;
    /**
     * 合同号
     */
    @TableField("CONTRACT_NO")
    private String contractNo;
    /**
     * 订单号
     */
    @TableField("BILL_NO")
    private String billNo;
    /**
     * 商品名称
     */
    @TableField("BILL_NAME")
    private String billName;
    /**
     * 商品编号（税收分类编码）
     */
    @TableField("BILL_CODE")
    private String billCode;
    /**
     * 发票行性质 0代表正常行
     */
    @TableField("LINE_TYPE")
    private String lineType;
    /**
     * 规格型号
     */
    @TableField("SPEC")
    private String spec;
    /**
     * 计量单位
     */
    @TableField("UNIT")
    private String unit;
    /**
     * 税率
     */
    @TableField("TAX_RATE")
    private BigDecimal taxRate;
    /**
     * 数量
     */
    @TableField("TAX_RQUANTITYATE")
    private Integer taxRquantityate;
    /**
     * 单价
     */
    @TableField("TAX_PRICE")
    private BigDecimal taxPrice;
    /**
     * 含税金额
     */
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    /**
     * 税收优惠政策标志
     */
    @TableField("YHZCBS")
    private String yhzcbs;
    /**
     * 享受税收优惠政策内容
     */
    @TableField("YHZCNR")
    private String yhzcnr;
    /**
     * 零税率标识 空表示非0税率
     */
    @TableField("LSLBS")
    private Integer lslbs;
    /**
     * 自行编码
     */
    @TableField("ZXBM")
    private String zxbm;
    /**
     * 扣除额
     */
    @TableField("KCE")
    private BigDecimal kce;
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


    public Integer getEleInvoiceDetailId() {
        return eleInvoiceDetailId;
    }

    public void setEleInvoiceDetailId(Integer eleInvoiceDetailId) {
        this.eleInvoiceDetailId = eleInvoiceDetailId;
    }

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

    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getTaxRquantityate() {
        return taxRquantityate;
    }

    public void setTaxRquantityate(Integer taxRquantityate) {
        this.taxRquantityate = taxRquantityate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getYhzcbs() {
        return yhzcbs;
    }

    public void setYhzcbs(String yhzcbs) {
        this.yhzcbs = yhzcbs;
    }

    public String getYhzcnr() {
        return yhzcnr;
    }

    public void setYhzcnr(String yhzcnr) {
        this.yhzcnr = yhzcnr;
    }

    public Integer getLslbs() {
        return lslbs;
    }

    public void setLslbs(Integer lslbs) {
        this.lslbs = lslbs;
    }

    public String getZxbm() {
        return zxbm;
    }

    public void setZxbm(String zxbm) {
        this.zxbm = zxbm;
    }

    public BigDecimal getKce() {
        return kce;
    }

    public void setKce(BigDecimal kce) {
        this.kce = kce;
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
        return "EleInvoiceDetail{" +
        ", eleInvoiceDetailId=" + eleInvoiceDetailId +
        ", eleInvoiceId=" + eleInvoiceId +
        ", invoiceId=" + invoiceId +
        ", invoiceDetailId=" + invoiceDetailId +
        ", contractNo=" + contractNo +
        ", billNo=" + billNo +
        ", billName=" + billName +
        ", billCode=" + billCode +
        ", lineType=" + lineType +
        ", spec=" + spec +
        ", unit=" + unit +
        ", taxRate=" + taxRate +
        ", taxRquantityate=" + taxRquantityate +
        ", taxPrice=" + taxPrice +
        ", totalAmount=" + totalAmount +
        ", yhzcbs=" + yhzcbs +
        ", yhzcnr=" + yhzcnr +
        ", lslbs=" + lslbs +
        ", zxbm=" + zxbm +
        ", kce=" + kce +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
