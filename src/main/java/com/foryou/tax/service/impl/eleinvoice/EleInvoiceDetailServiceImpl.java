package com.foryou.tax.service.impl.eleinvoice;

import com.foryou.tax.dao.eleinvoice.EleInvoiceDetailMapper;
import com.foryou.tax.pojo.allinvoice.AllInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceDetail;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import com.foryou.tax.service.eleinvoice.EleInvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 电子发票明细表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-17
 */
@Service
public class EleInvoiceDetailServiceImpl implements EleInvoiceDetailService {

    @Autowired
    EleInvoiceDetailMapper eleInvoiceDetailMapper;

    @Override
    public List<EleInvoiceDetail> getEleInvoiceDetailInfo(EleInvoiceDetail eleInvoiceDetail) {
        return eleInvoiceDetailMapper.getEleInvoiceDetailInfo(eleInvoiceDetail);
    }

    @Override
    public int deleteData(EleInvoiceInfo eleInvoiceInfo) {
        return eleInvoiceDetailMapper.deleteData(eleInvoiceInfo);
    }

    @Override
    public int insertData(HttpServletRequest request, EleInvoiceDetail eleInvoiceDetail) {
        return eleInvoiceDetailMapper.insertData(request, eleInvoiceDetail);
    }

    @Override
    public String getTaxClassificationCode(AllInvoiceDetail allInvoiceDetail) {
        return eleInvoiceDetailMapper.getTaxClassificationCode(allInvoiceDetail);
    }

    @Override
    public List<Map> getContractDetail(Integer cashflowId) {
        return eleInvoiceDetailMapper.getContractDetail(cashflowId);
    }
}
