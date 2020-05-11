package com.foryou.tax.service.weekly.mergeinvoice;

import com.foryou.tax.pojo.weekly.mergeinvoice.DcflMergeInvoiceResult;

import java.util.List;

/**
 * <p>
 * 金税系统的开票数据和融资资料系统的开票数据对比result 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
public interface DcflMergeInvoiceResultService {

    int insertData(DcflMergeInvoiceResult dcflMergeInvoiceResult);

    List<DcflMergeInvoiceResult> queryMergeResultData();
}
