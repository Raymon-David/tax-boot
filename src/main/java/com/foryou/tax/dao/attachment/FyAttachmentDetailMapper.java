package com.foryou.tax.dao.attachment;

import com.foryou.tax.pojo.attachment.FyAttachmentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 附件明细表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
@Component
@Mapper
public interface FyAttachmentDetailMapper  {

    int insertSelective(HttpServletRequest request, FyAttachmentDetail fyAttachmentDetail);

    FyAttachmentDetail selectBySourcePkValue(FyAttachmentDetail fyAttachmentDetail);
}
