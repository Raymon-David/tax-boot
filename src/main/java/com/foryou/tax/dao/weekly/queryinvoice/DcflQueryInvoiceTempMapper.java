package com.foryou.tax.dao.weekly.queryinvoice;

import com.foryou.tax.pojo.weekly.queryinvoice.DcflQueryInvoiceTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 从DCFL销项发票查询导入数据 临时表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-04-28
 */
@Component
@Mapper
public interface DcflQueryInvoiceTempMapper{

    int insertData(DcflQueryInvoiceTemp dcflQueryInvoiceTemp);

    int backUpData(@Param("newTableName")String newTableName);

    int deleteData();
}
