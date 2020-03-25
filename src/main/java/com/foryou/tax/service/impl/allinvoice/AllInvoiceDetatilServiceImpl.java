package com.foryou.tax.service.impl.allinvoice;

import com.foryou.tax.dao.allinvoice.AllInvoiceDetatilMapper;
import com.foryou.tax.pojo.allinvoice.AllInvoiceDetail;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;
import com.foryou.tax.service.allinvoice.AllInvoiceDetatilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 发票明细表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-24
 */
@Service
public class AllInvoiceDetatilServiceImpl implements AllInvoiceDetatilService {

    @Autowired
    AllInvoiceDetatilMapper allInvoiceDetatilMapper;

    @Override
    public List<AllInvoiceDetail> getAllInvoiceDetailInfo(AllInvoiceInfo allInvoiceInfo) {
        return allInvoiceDetatilMapper.getAllInvoiceDetailInfo(allInvoiceInfo);
    }
}
