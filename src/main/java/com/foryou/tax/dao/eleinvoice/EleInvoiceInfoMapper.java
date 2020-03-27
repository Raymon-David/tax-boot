package com.foryou.tax.dao.eleinvoice;

import com.foryou.tax.pojo.companies.FyCompanies;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 电子发票表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-03-18
 */
@Component
@Mapper
public interface EleInvoiceInfoMapper{

    EleInvoiceInfo getEleInvoiceInfo(EleInvoiceInfo eleInvoiceInfo);

    int updateEleInvoiceTaxError(EleInvoiceInfo eleInvoiceInfo);

    int deleteData(EleInvoiceInfo eleInvoiceInfo);

    String searchSerialNum(FyCompanies fyCompanies);

    String getSerialNum1(FyCompanies fyCompanies);

    String getSerialNum2(FyCompanies fyCompanies);

    int insertData(HttpServletRequest request, EleInvoiceInfo eleInvoiceInfo);

    int updateEleInvoiceInterfaceStatus(EleInvoiceInfo eleInvoiceInfo1);

    int updateEleInvoiceInfo(EleInvoiceInfo eleInvoiceInfo);

    EleInvoiceInfo selectBySerialNum(EleInvoiceInfo eleInvoiceInfo);
}
