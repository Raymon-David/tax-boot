package com.foryou.tax.service.impl.weekly.mergeinvoice;

import com.foryou.tax.dao.weekly.mergeinvoice.DcflQueryInvoiceVMapper;
import com.foryou.tax.pojo.weekly.mergeinvoice.DcflQueryInvoiceV;
import com.foryou.tax.service.weekly.mergeinvoice.DcflQueryInvoiceVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@Service
public class DcflQueryInvoiceVServiceImpl implements DcflQueryInvoiceVService {

    @Autowired
    DcflQueryInvoiceVMapper dcflQueryInvoiceVMapper;

    @Override
    public DcflQueryInvoiceV dcflQueryInvoiceDataByKeywords(String keyWords) {
        return dcflQueryInvoiceVMapper.dcflQueryInvoiceDataByKeywords(keyWords);
    }
}
