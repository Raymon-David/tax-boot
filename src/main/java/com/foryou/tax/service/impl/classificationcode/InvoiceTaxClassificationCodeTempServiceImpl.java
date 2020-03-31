package com.foryou.tax.service.impl.classificationcode;

import com.foryou.tax.dao.classificationcode.InvoiceTaxClassificationCodeTempMapper;
import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCodeTemp;
import com.foryou.tax.service.classificationcode.InvoiceTaxClassificationCodeTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 税收分类编码临时表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
@Service
public class InvoiceTaxClassificationCodeTempServiceImpl implements InvoiceTaxClassificationCodeTempService {

    @Autowired
    InvoiceTaxClassificationCodeTempMapper invoiceTaxClassificationCodeTempMapper;

    @Override
    public int insetData(InvoiceTaxClassificationCodeTemp invoiceTaxClassificationCodeTemp) {
        return invoiceTaxClassificationCodeTempMapper.insertData(invoiceTaxClassificationCodeTemp);
    }

    @Override
    public List<InvoiceTaxClassificationCodeTemp> selectData() {
        return invoiceTaxClassificationCodeTempMapper.selectData();
    }
}
