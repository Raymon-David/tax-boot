package com.foryou.tax.service.classificationcode;

import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCode;

/**
 * <p>
 * 税收分类编码 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
public interface InvoiceTaxClassificationCodeService {

    InvoiceTaxClassificationCode selectByTaxClassificationCode(String taxClassCode);

    int insertData(InvoiceTaxClassificationCode invoiceTaxClassificationCode);
}
