package com.foryou.tax.service.impl.eleinvoice;

import com.foryou.tax.dao.eleinvoice.EleInvoiceInfoMapper;
import com.foryou.tax.pojo.companies.FyCompanies;
import com.foryou.tax.pojo.eleinvoice.EleInvoiceInfo;
import com.foryou.tax.service.eleinvoice.EleInvoiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 电子发票表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-16
 */
@Service
public class EleInvoiceInfoServiceImpl implements EleInvoiceInfoService {

    @Autowired
    EleInvoiceInfoMapper eleInvoiceInfoMapper;

    @Override
    public EleInvoiceInfo getEleInvoiceInfo(EleInvoiceInfo eleInvoiceInfo) {

        return eleInvoiceInfoMapper.getEleInvoiceInfo(eleInvoiceInfo);
    }

    @Override
    public int updateEleInvoiceTaxError(EleInvoiceInfo eleInvoiceInfo) {

        return eleInvoiceInfoMapper.updateEleInvoiceTaxError(eleInvoiceInfo);
    }

    @Override
    public int deleteData(EleInvoiceInfo eleInvoiceInfo) {
        return eleInvoiceInfoMapper.deleteData(eleInvoiceInfo);
    }

    @Override
    public String getSerialNum(FyCompanies fyCompanies) {

        String serialNum = eleInvoiceInfoMapper.searchSerialNum(fyCompanies);
        if (serialNum == null || "".equals(serialNum)) {
            serialNum = eleInvoiceInfoMapper.getSerialNum2(fyCompanies);
        }else {
            serialNum = eleInvoiceInfoMapper.getSerialNum1(fyCompanies);
        }
        return serialNum;
    }

    @Override
    public int insertData(HttpServletRequest request, EleInvoiceInfo eleInvoiceInfo) {
        return eleInvoiceInfoMapper.insertData(request, eleInvoiceInfo);
    }

    @Override
    public int updateEleInvoiceInterfaceStatus(EleInvoiceInfo eleInvoiceInfo1) {
        return eleInvoiceInfoMapper.updateEleInvoiceInterfaceStatus(eleInvoiceInfo1);
    }
}
