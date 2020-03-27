package com.foryou.tax.service.impl.attachment;

import com.foryou.tax.dao.attachment.FyAttachmentInfoMapper;
import com.foryou.tax.pojo.attachment.FyAttachmentInfo;
import com.foryou.tax.service.attachment.FyAttachmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 附件表（关联表名） 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
@Service
public class FyAttachmentInfoServiceImpl  implements FyAttachmentInfoService {

    @Autowired
    FyAttachmentInfoMapper fyAttachmentInfoMapper;

    @Override
    public int insertSelective(HttpServletRequest request, FyAttachmentInfo fyAttachmentInfo) {
        return fyAttachmentInfoMapper.insertSelective(request, fyAttachmentInfo);
    }

    @Override
    public FyAttachmentInfo selectByTablePkValue(FyAttachmentInfo fyAttachmentInfo) {
        return fyAttachmentInfoMapper.selectByTablePkValue(fyAttachmentInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(FyAttachmentInfo fyAttachmentInfo) {
        return fyAttachmentInfoMapper.updateByPrimaryKeySelective(fyAttachmentInfo);
    }
}
