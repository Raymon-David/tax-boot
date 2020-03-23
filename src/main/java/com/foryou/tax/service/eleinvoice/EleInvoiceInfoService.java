package com.foryou.tax.service.eleinvoice;


import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;

/**
 * <p>
 * 电子发票表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-16
 */
public interface EleInvoiceInfoService {

    EleInvoiceInfo getEleInvoiceInfo(EleInvoiceInfo eleInvoiceInfo);

    int updateEleInvoiceTaxError(EleInvoiceInfo eleInvoiceInfo);

    int deleteData(EleInvoiceInfo eleInvoiceInfo);
}
