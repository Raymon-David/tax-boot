package com.foryou.tax.dao.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceInfoTemp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 发票总表临时表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-04-08
 */
@Component
@Mapper
public interface AllInvoiceInfoTempMapper{

    int insertData(AllInvoiceInfoTemp allInvoiceInfoTemp);

    void backUpData(String dt);
}
