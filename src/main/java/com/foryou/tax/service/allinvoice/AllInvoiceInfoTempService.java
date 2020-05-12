package com.foryou.tax.service.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceInfoTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发票总表临时表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-04-08
 */
public interface AllInvoiceInfoTempService {

    void insertData(AllInvoiceInfoTemp allInvoiceInfoTemp);

    int backUpData(@Param("newTableName") String newTableName);

    int deleteData();

    List<Map<String, Object>> createInvoiceQueryWeekly();
}
