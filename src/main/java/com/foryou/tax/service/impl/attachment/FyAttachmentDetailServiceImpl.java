package com.foryou.tax.service.impl.attachment;

import com.foryou.tax.dao.attachment.FyAttachmentDetailMapper;
import com.foryou.tax.pojo.attachment.FyAttachmentDetail;
import com.foryou.tax.service.attachment.FyAttachmentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 附件明细表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-27
 */
@Service
public class FyAttachmentDetailServiceImpl implements FyAttachmentDetailService {

    @Autowired
    FyAttachmentDetailMapper fyAttachmentDetailMapper;

    @Override
    public int insertSelective(HttpServletRequest request, FyAttachmentDetail fyAttachmentDetail) {
        return fyAttachmentDetailMapper.insertSelective(request, fyAttachmentDetail);
    }

    @Override
    public FyAttachmentDetail selectBySourcePkValue(FyAttachmentDetail fyAttachmentDetail) {
        return fyAttachmentDetailMapper.selectBySourcePkValue(fyAttachmentDetail);
    }
}
