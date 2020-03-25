package com.foryou.tax.dao.allinvoice;

import com.foryou.tax.pojo.allinvoice.AllInvoiceDetail;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 发票明细表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-03-24
 */
@Component
@Mapper
public interface AllInvoiceDetatilMapper {

    List<AllInvoiceDetail> getAllInvoiceDetailInfo(AllInvoiceInfo allInvoiceInfo);
}
