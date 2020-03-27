package com.foryou.tax.pojo.attachment;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 附件明细表
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
@TableName("FY_ATTACHMENT_DETAIL")
public class FyAttachmentDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ATTACHMENT_ID", type = IdType.AUTO)
    private Integer attachmentId;
    /**
     * 附件来源
     */
    @TableField("SOURCE_TYPE_CODE")
    private String sourceTypeCode;
    /**
     * FY_ATTACHMENT_INFO表主键
     */
    @TableField("SOURCE_PK_VALUE")
    private Integer sourcePkValue;
    /**
     * 附件格式
     */
    @TableField("FILE_TYPE_CODE")
    private String fileTypeCode;
    /**
     * 附件格式
     */
    @TableField("MIME_TYPE")
    private String mimeType;
    /**
     * 附件名称
     */
    @TableField("ATTACHMENT_NAME")
    private String attachmentName;
    /**
     * 附件大小
     */
    @TableField("ATTACHMENT_SIZE")
    private Integer attachmentSize;
    /**
     * 附件路径
     */
    @TableField("ATTACHMENT_PATH")
    private String attachmentPath;
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


    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getSourceTypeCode() {
        return sourceTypeCode;
    }

    public void setSourceTypeCode(String sourceTypeCode) {
        this.sourceTypeCode = sourceTypeCode;
    }

    public Integer getSourcePkValue() {
        return sourcePkValue;
    }

    public void setSourcePkValue(Integer sourcePkValue) {
        this.sourcePkValue = sourcePkValue;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public Integer getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(Integer attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
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
        return "FyAttachmentDetail{" +
        ", attachmentId=" + attachmentId +
        ", sourceTypeCode=" + sourceTypeCode +
        ", sourcePkValue=" + sourcePkValue +
        ", fileTypeCode=" + fileTypeCode +
        ", mimeType=" + mimeType +
        ", attachmentName=" + attachmentName +
        ", attachmentSize=" + attachmentSize +
        ", attachmentPath=" + attachmentPath +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
