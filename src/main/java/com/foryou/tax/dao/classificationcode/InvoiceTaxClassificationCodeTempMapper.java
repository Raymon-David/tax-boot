package com.foryou.tax.dao.classificationcode;

import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCodeTemp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 税收分类编码临时表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-03-31
 */
@Component
@Mapper
public interface InvoiceTaxClassificationCodeTempMapper {

    int insertData(InvoiceTaxClassificationCodeTemp invoiceTaxClassificationCodeTemp);

    List<InvoiceTaxClassificationCodeTemp> selectData();
}
