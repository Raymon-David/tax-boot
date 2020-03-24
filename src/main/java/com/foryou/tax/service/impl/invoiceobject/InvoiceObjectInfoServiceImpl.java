package com.foryou.tax.service.impl.invoiceobject;

import com.foryou.tax.dao.invoiceobject.InvoiceObjectInfoMapper;
import com.foryou.tax.pojo.invoiceobject.InvoiceObjectInfo;
import com.foryou.tax.service.invoiceobject.InvoiceObjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 开票对象信息表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-23
 */
@Service
public class InvoiceObjectInfoServiceImpl implements InvoiceObjectInfoService {

    @Autowired
    InvoiceObjectInfoMapper invoiceObjectInfoMapper;

    @Override
    public InvoiceObjectInfo getInvoiceObjectInfo(InvoiceObjectInfo invoiceObjectInfo) {
        return invoiceObjectInfoMapper.getInvoiceObjectInfo(invoiceObjectInfo);
    }
}
