package com.foryou.tax.process.classificationcode;

import com.foryou.tax.api.constant.StatusCodeEnum;
import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCode;
import com.foryou.tax.pojo.classificationcode.InvoiceTaxClassificationCodeTemp;
import com.foryou.tax.service.classificationcode.InvoiceTaxClassificationCodeService;
import com.foryou.tax.service.classificationcode.InvoiceTaxClassificationCodeTempService;
import com.foryou.tax.util.JDBCUtil;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/31
 * @description: 税收分类编码process
 */
public class InvoiceTaxClassificationCodeProcess {

    @Autowired
    private InvoiceTaxClassificationCodeService invoiceTaxClassificationCodeService;

    @Autowired
    private InvoiceTaxClassificationCodeTempService invoiceTaxClassificationCodeTempService;


    /**
     * 先从 DCFL 导出税收分类编码的表
     * 然后写入saas 的临时表
     * 然后从临时表获得数据导入 INVOICE_TAX_CLASSIFICATION_CODE
     */
    public void invoiceTaxClassificationCodeImport(HttpServletRequest request, HttpServletResponse response) {

        String sql = "SELECT *\n" +
                "  FROM (SELECT dt.class_id,\n" +
                "               dt.contract_type,\n" +
                "               (SELECT hbt.description\n" +
                "                  FROM hls_business_type hbt\n" +
                "                 WHERE dt.contract_type = hbt.business_type)\n" +
                "                  AS contract_type_n,\n" +
                "               dt.division,\n" +
                "               (SELECT hd.description\n" +
                "                  FROM hls_division hd\n" +
                "                 WHERE dt.division = hd.division)\n" +
                "                  AS division_n,\n" +
                "               dt.cf_item,\n" +
                "               (SELECT hci.description\n" +
                "                  FROM hls_cashflow_item hci\n" +
                "                 WHERE dt.cf_item = hci.cf_item)\n" +
                "                  AS cf_item_n,\n" +
                "               dt.cf_type,\n" +
                "               (SELECT hct.description\n" +
                "                  FROM hls_cashflow_type hct\n" +
                "                 WHERE dt.cf_type = hct.cf_type)\n" +
                "                  AS cf_type_n,\n" +
                "               tax_class_num,\n" +
                "               goods_version\n" +
                "          FROM DS_TAX_CLASS_NUM dt) t";

        List<Map<String, String>> mapList = JDBCUtil.selectData(sql);

        for (int i = 0; i < mapList.size(); i++) {
            InvoiceTaxClassificationCodeTemp invoiceTaxClassificationCodeTemp = new InvoiceTaxClassificationCodeTemp();
            invoiceTaxClassificationCodeTemp.setClassId(Integer.valueOf(mapList.get(i).get("class_id")));
            invoiceTaxClassificationCodeTemp.setContractType(mapList.get(i).get("contract_type"));
            invoiceTaxClassificationCodeTemp.setContractTypeN(mapList.get(i).get("contract_type_n"));
            invoiceTaxClassificationCodeTemp.setDivision(mapList.get(i).get("division"));
            invoiceTaxClassificationCodeTemp.setDivisionN(mapList.get(i).get("division_n"));
            invoiceTaxClassificationCodeTemp.setCfItem(mapList.get(i).get("cf_item"));
            invoiceTaxClassificationCodeTemp.setCfItemN(mapList.get(i).get("cf_item_n"));
            invoiceTaxClassificationCodeTemp.setCfType(mapList.get(i).get("cf_type"));
            invoiceTaxClassificationCodeTemp.setCfTypeN(mapList.get(i).get("cf_type_n"));
            invoiceTaxClassificationCodeTemp.setTaxClassNum(mapList.get(i).get("tax_class_num"));
            invoiceTaxClassificationCodeTemp.setGoodsVersion(mapList.get(i).get("goods_version"));

            LoggerUtils.debug(getClass(), "invoiceTaxClassificationCodeTemp is :" + invoiceTaxClassificationCodeTemp);
            invoiceTaxClassificationCodeTempService.insetData(invoiceTaxClassificationCodeTemp);
        }

        List<InvoiceTaxClassificationCodeTemp> invoiceTaxClassificationCodeTemps = invoiceTaxClassificationCodeTempService.selectData();
        for (int i = 0; i < invoiceTaxClassificationCodeTemps.size(); i++) {
            String taxClassCode = invoiceTaxClassificationCodeTemps.get(i).getTaxClassNum();
            InvoiceTaxClassificationCode taxClassificationCode = invoiceTaxClassificationCodeService.selectByTaxClassificationCode(taxClassCode);

            if (taxClassificationCode == null) {
                InvoiceTaxClassificationCode invoiceTaxClassificationCode = new InvoiceTaxClassificationCode();
                invoiceTaxClassificationCode.setTaxClassificationCode(taxClassCode);

                String goodsCode = invoiceTaxClassificationCodeTemps.get(i).getContractType();
                if ("LEASE".equals(goodsCode)) {
                    invoiceTaxClassificationCode.setGoodsCode(StatusCodeEnum.G_1001.getStatusCode());
                    invoiceTaxClassificationCode.setGoodsName(StatusCodeEnum.G_1001.getStatusName());
                }else if ("LEASEBACK".equals(goodsCode)) {
                    invoiceTaxClassificationCode.setGoodsCode(StatusCodeEnum.G_1002.getStatusCode());
                    invoiceTaxClassificationCode.setGoodsName(StatusCodeEnum.G_1002.getStatusName());
                }

                String productCode = invoiceTaxClassificationCodeTemps.get(i).getDivision();
                if ("00".equals(productCode)) {
                    invoiceTaxClassificationCode.setProductCode(StatusCodeEnum.P_1001.getStatusCode());
                    invoiceTaxClassificationCode.setProductName(StatusCodeEnum.P_1001.getStatusName());
                } else if ("01".equals(productCode)) {
                    invoiceTaxClassificationCode.setProductCode(StatusCodeEnum.P_1002.getStatusCode());
                    invoiceTaxClassificationCode.setProductName(StatusCodeEnum.P_1002.getStatusName());
                }else if ("03".equals(productCode)) {
                    invoiceTaxClassificationCode.setProductCode(StatusCodeEnum.P_1007.getStatusCode());
                    invoiceTaxClassificationCode.setProductName(StatusCodeEnum.P_1007.getStatusName());
                } else if ("10".equals(productCode)) {
                    invoiceTaxClassificationCode.setProductCode(StatusCodeEnum.P_1003.getStatusCode());
                    invoiceTaxClassificationCode.setProductName(StatusCodeEnum.P_1003.getStatusName());
                } else if ("20".equals(productCode)) {
                    invoiceTaxClassificationCode.setProductCode(StatusCodeEnum.P_1005.getStatusCode());
                    invoiceTaxClassificationCode.setProductName(StatusCodeEnum.P_1005.getStatusName());
                } else if ("30".equals(productCode)) {
                    invoiceTaxClassificationCode.setProductCode(StatusCodeEnum.P_1006.getStatusCode());
                    invoiceTaxClassificationCode.setProductName(StatusCodeEnum.P_1006.getStatusName());
                }

                invoiceTaxClassificationCode.setCashflowItemCode(invoiceTaxClassificationCodeTemps.get(i).getCfItem());
                invoiceTaxClassificationCode.setCashflowItemName(invoiceTaxClassificationCodeTemps.get(i).getCfItemN());
                invoiceTaxClassificationCode.setGoodsVersion(invoiceTaxClassificationCodeTemps.get(i).getGoodsVersion());

                invoiceTaxClassificationCodeService.insertData(invoiceTaxClassificationCode);
            }
        }

    }
}
