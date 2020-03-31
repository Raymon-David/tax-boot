package com.foryou.tax.service.impl.classificationcode;

import com.foryou.tax.dao.classificationcode.InvoiceTaxClassificationCodeMapper;
import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCode;
import com.foryou.tax.service.classificationcode.InvoiceTaxClassificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 税收分类编码 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
@Service
public class InvoiceTaxClassificationCodeServiceImpl implements InvoiceTaxClassificationCodeService {

    @Autowired
    InvoiceTaxClassificationCodeMapper invoiceTaxClassificationCodeMapper;

    @Override
    public InvoiceTaxClassificationCode selectByTaxClassificationCode(String taxClassCode) {
        return invoiceTaxClassificationCodeMapper.selectByTaxClassificationCode(taxClassCode);
    }

    @Override
    public int insertData(InvoiceTaxClassificationCode invoiceTaxClassificationCode) {
        return invoiceTaxClassificationCodeMapper.insertData(invoiceTaxClassificationCode);
    }
}
