package com.foryou.tax.service.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceInfoTemp;
import com.baomidou.mybatisplus.service.IService;

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
}