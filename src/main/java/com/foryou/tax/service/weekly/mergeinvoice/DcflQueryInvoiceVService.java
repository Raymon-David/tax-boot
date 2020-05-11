package com.foryou.tax.service.weekly.mergeinvoice;

import com.foryou.tax.pojo.weekly.mergeinvoice.DcflQueryInvoiceV;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
public interface DcflQueryInvoiceVService {

    DcflQueryInvoiceV dcflQueryInvoiceDataByKeywords(String keyWords);

    List<DcflQueryInvoiceV> queryDcflInvoiceData();
}
