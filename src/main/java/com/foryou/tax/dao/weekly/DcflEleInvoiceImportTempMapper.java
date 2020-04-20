package com.foryou.tax.dao.weekly;

import com.foryou.tax.pojo.weekly.DcflEleInvoiceImportTemp;
import org.apache.ibatis.annotations.Mapper;
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

    int insertData(DcflEleInvoiceImportTemp dcflEleInvoiceImportTemp);
}
