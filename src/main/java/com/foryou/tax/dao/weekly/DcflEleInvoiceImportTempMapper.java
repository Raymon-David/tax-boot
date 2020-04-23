package com.foryou.tax.dao.weekly;

import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;
import com.foryou.tax.pojo.weekly.DcflPaperInvoiceImportTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 从页面导入电子发票 临时表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-04-20
 */
@Component
@Mapper
public interface DcflEleInvoiceImportTempMapper {

    int insertEleData(DcflEleInvoiceImportTemp dcflEleInvoiceImportTemp);

    int insetPaperData(DcflPaperInvoiceImportTemp dcflPaperInvoiceImportTemp);

    int backUpPaperData(@Param("newTableName")String newTableName);

    int deletePaperData();

    int backUpEleData(@Param("newTableName")String newTableName);

    int deleteEleData();
}
