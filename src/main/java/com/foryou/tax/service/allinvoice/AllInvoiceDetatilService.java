package com.foryou.tax.service.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceDetail;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;

import java.util.List;

/**
 * <p>
 * 发票明细表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-24
 */
public interface AllInvoiceDetatilService {

    List<AllInvoiceDetail> getAllInvoiceDetailInfo(AllInvoiceInfo allInvoiceInfo);

}
