package com.foryou.tax.service.weekly.mergeinvoice;

import com.foryou.tax.pojo.weekly.mergeinvoice.DcflMergeInvoiceResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    int deleteData();

    int dropTableEveryMonth(@Param("tableName")String tableName);

    List<Map<String, Object>> queryableEveryMonth(@Param("dropDate") String dropDate);
}
