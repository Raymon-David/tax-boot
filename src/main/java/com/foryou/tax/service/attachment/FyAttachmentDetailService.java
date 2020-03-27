package com.foryou.tax.service.attachment;

import com.foryou.tax.pojo.attachment.FyAttachmentDetail;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 附件明细表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
public interface FyAttachmentDetailService {

    int insertSelective(HttpServletRequest request, FyAttachmentDetail fyAttachmentDetail);

    FyAttachmentDetail selectBySourcePkValue(FyAttachmentDetail fyAttachmentDetail);
}
