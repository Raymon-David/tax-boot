package com.foryou.tax.service.classificationcode;

import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCodeTemp;

import java.util.List;

/**
 * <p>
 * 税收分类编码临时表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
public interface InvoiceTaxClassificationCodeTempService {

    int insetData(InvoiceTaxClassificationCodeTemp invoiceTaxClassificationCodeTemp);

    List<InvoiceTaxClassificationCodeTemp> selectData();
}
