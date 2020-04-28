package com.foryou.tax.service.weekly.queryinvoice;

import com.foryou.tax.pojo.weekly.queryinvoice.DcflQueryInvoiceTemp;

/**
 * <p>
 * 从DCFL销项发票查询导入数据 临时表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-04-28
 */
public interface DcflQueryInvoiceTempService {

    int insertData(DcflQueryInvoiceTemp dcflQueryInvoiceTemp);
}
