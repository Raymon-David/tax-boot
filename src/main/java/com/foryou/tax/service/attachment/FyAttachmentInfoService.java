package com.foryou.tax.service.attachment;

import com.foryou.tax.pojo.attachment.FyAttachmentInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 附件表（关联表名） 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
public interface FyAttachmentInfoService {

    int insertSelective(HttpServletRequest request, FyAttachmentInfo fyAttachmentInfo);

    FyAttachmentInfo selectByTablePkValue(FyAttachmentInfo fyAttachmentInfo);

    int updateByPrimaryKeySelective(FyAttachmentInfo fyAttachmentInfo);
}
