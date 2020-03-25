package com.foryou.tax.service.eleinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 电子发票明细表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-17
 */
public interface EleInvoiceDetailService {

    List<EleInvoiceDetail> getEleInvoiceDetailInfo(EleInvoiceDetail eleInvoiceDetail);

    int deleteData(EleInvoiceInfo eleInvoiceInfo);

    int insertData(HttpServletRequest request, EleInvoiceDetail eleInvoiceDetail);

    String getTaxClassificationCode(AllInvoiceDetail allInvoiceDetail);

    List<Map> getContractDetail(Integer cashflowId);
}
