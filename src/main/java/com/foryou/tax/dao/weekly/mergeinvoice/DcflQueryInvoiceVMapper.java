package com.foryou.tax.dao.weekly.mergeinvoice;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.foryou.tax.pojo.weekly.mergeinvoice.DcflQueryInvoiceV;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@Component
@Mapper
public interface DcflQueryInvoiceVMapper extends BaseMapper<DcflQueryInvoiceV> {

    DcflQueryInvoiceV dcflQueryInvoiceDataByKeywords(String keyWords);
}
