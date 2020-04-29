package com.foryou.tax.service.impl.weekly.queryinvoice;

import com.foryou.tax.dao.weekly.queryinvoice.DcflQueryInvoiceTempMapper;
import com.foryou.tax.pojo.weekly.queryinvoice.DcflQueryInvoiceTemp;
import com.foryou.tax.service.weekly.queryinvoice.DcflQueryInvoiceTempService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 从DCFL销项发票查询导入数据 临时表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-04-28
 */
@Service
public class DcflQueryInvoiceTempServiceImpl implements DcflQueryInvoiceTempService {

    @Autowired
    DcflQueryInvoiceTempMapper dcflQueryInvoiceTempMapper;

    @Override
    public int insertData(DcflQueryInvoiceTemp dcflQueryInvoiceTemp) {
        return dcflQueryInvoiceTempMapper.insertData(dcflQueryInvoiceTemp);
    }

    @Override
    public int backUpData(@Param("newTableName")String newTableName) {
        return dcflQueryInvoiceTempMapper.backUpData(newTableName);
    }

    @Override
    public int deleteData() {
        return dcflQueryInvoiceTempMapper.deleteData();
    }
}
