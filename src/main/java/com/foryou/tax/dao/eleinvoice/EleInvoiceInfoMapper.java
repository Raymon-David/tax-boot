package com.foryou.tax.dao.eleinvoice;

import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
}
