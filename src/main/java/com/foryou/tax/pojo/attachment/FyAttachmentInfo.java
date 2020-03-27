package com.foryou.tax.pojo.attachment;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 附件表（关联表名）
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
@TableName("FY_ATTACHMENT_INFO")
public class FyAttachmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "RECORD_ID", type = IdType.AUTO)
    private Integer recordId;
    /**
     * 表名
     */
    @TableField("TABLE_NAME")
    private String tableName;
    /**
     * 表主键
     */
    @TableField("TABLE_PK_VALUE")
    private Integer tablePkValue;
    /**
     * 附件id
     */
    @TableField("ATTACHMENT_ID")
    private Integer attachmentId;
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


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTablePkValue() {
        return tablePkValue;
    }

    public void setTablePkValue(Integer tablePkValue) {
        this.tablePkValue = tablePkValue;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
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
        return "FyAttachmentInfo{" +
        ", recordId=" + recordId +
        ", tableName=" + tableName +
        ", tablePkValue=" + tablePkValue +
        ", attachmentId=" + attachmentId +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
