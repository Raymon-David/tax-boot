package com.foryou.tax.service.impl.allinvoice;

import com.foryou.tax.dao.allinvoice.AllInvoiceInfoMapper;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;
import com.foryou.tax.service.allinvoice.AllInvoiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 发票总表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-18
 */
@Service
public class AllInvoiceInfoServiceImpl implements AllInvoiceInfoService {

    @Autowired
    AllInvoiceInfoMapper allInvoiceInfoMapper;

    @Override
    public AllInvoiceInfo getInvoiceNum(AllInvoiceInfo allInvoiceInfo) {
        return allInvoiceInfoMapper.getInvoiceNum(allInvoiceInfo);
    }
}
