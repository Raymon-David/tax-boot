package com.foryou.tax.service.weekly;

import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;
import com.foryou.tax.pojo.weekly.DcflPaperInvoiceImportTemp;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 从页面导入电子发票 临时表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-04-20
 */
public interface DcflEleInvoiceImportTempService {

    int insetEleData(DcflEleInvoiceImportTemp dcflEleInvoiceImportTemp);

    int insetPaperData(DcflPaperInvoiceImportTemp dcflPaperInvoiceImportTemp);

    int backUpPaperData(@Param("newTableName")String newTableName);

    int deletePaperData();

    int backUpEleData(@Param("newTableName")String newTableName);

    int deleteEleData();
}
