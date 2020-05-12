package com.foryou.tax.dao.weekly.mergeinvoice;

import com.foryou.tax.pojo.weekly.mergeinvoice.DcflMergeInvoiceResult;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 金税系统的开票数据和融资资料系统的开票数据对比result Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@Component
@Mapper
public interface DcflMergeInvoiceResultMapper extends BaseMapper<DcflMergeInvoiceResult> {

    int insertData(DcflMergeInvoiceResult dcflMergeInvoiceResult);

    List<DcflMergeInvoiceResult> queryMergeResultData();

    int deleteData();
}
