package com.foryou.tax.service.impl.weekly.mergeinvoice;

import com.foryou.tax.dao.weekly.mergeinvoice.DcflMergeInvoiceResultMapper;
import com.foryou.tax.pojo.weekly.mergeinvoice.DcflMergeInvoiceResult;
import com.foryou.tax.service.weekly.mergeinvoice.DcflMergeInvoiceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 金税系统的开票数据和融资资料系统的开票数据对比result 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@Service
public class DcflMergeInvoiceResultServiceImpl implements DcflMergeInvoiceResultService {

    @Autowired
    DcflMergeInvoiceResultMapper dcflMergeInvoiceResultMapper;

    @Override
    public int insertData(DcflMergeInvoiceResult dcflMergeInvoiceResult) {
        return dcflMergeInvoiceResultMapper.insertData(dcflMergeInvoiceResult);
    }
}
