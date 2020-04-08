package com.foryou.tax.service.impl.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceInfoTemp;
import com.foryou.tax.dao.allinvoice.AllInvoiceInfoTempMapper;
import com.foryou.tax.service.allinvoice.AllInvoiceInfoTempService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 发票总表临时表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-04-08
 */
@Service
public class AllInvoiceInfoTempServiceImpl implements AllInvoiceInfoTempService {

    @Autowired
    AllInvoiceInfoTempMapper allInvoiceInfoTempMapper;

    @Override
    public void insertData(AllInvoiceInfoTemp allInvoiceInfoTemp) {
        allInvoiceInfoTempMapper.insertData(allInvoiceInfoTemp);
    }
}
