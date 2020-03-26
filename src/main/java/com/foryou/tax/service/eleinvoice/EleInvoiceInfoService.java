package com.foryou.tax.service.eleinvoice;


import com.foryou.tax.pojo.companies.FyCompanies;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;

import javax.servlet.http.HttpServletRequest;

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

    String getSerialNum(FyCompanies fyCompanies);

    int insertData(HttpServletRequest request, EleInvoiceInfo eleInvoiceInfo1);

    int updateEleInvoiceInterfaceStatus(EleInvoiceInfo eleInvoiceInfo1);
}
