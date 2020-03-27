package com.foryou.tax.service.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;

/**
 * <p>
 * 发票总表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-18
 */
public interface AllInvoiceInfoService{

    AllInvoiceInfo getInvoiceNum(AllInvoiceInfo allInvoiceInfo);
}
