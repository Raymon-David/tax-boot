package com.foryou.tax.service.weekly;

import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;

/**
 * <p>
 * 从页面导入电子发票 临时表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-04-20
 */
public interface DcflEleInvoiceImportTempService {

    int insetData(DcflEleInvoiceImportTemp dcflEleInvoiceImportTemp);
}
