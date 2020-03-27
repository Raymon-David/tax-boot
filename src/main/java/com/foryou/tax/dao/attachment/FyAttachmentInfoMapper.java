package com.foryou.tax.dao.attachment;

import com.foryou.tax.pojo.attachment.FyAttachmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 附件表（关联表名） Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
@Component
@Mapper
public interface FyAttachmentInfoMapper {

    int insertSelective(HttpServletRequest request, FyAttachmentInfo fyAttachmentInfo);

    FyAttachmentInfo selectByTablePkValue(FyAttachmentInfo fyAttachmentInfo);

    int updateByPrimaryKeySelective(FyAttachmentInfo fyAttachmentInfo);
}
