package com.foryou.tax.process.weekly.queryinvoice;

import cn.hutool.core.convert.Convert;
import com.foryou.tax.pojo.weekly.queryinvoice.DcflQueryInvoiceTemp;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.weekly.queryinvoice.DcflQueryInvoiceTempService;
import com.foryou.tax.util.DateUtil;
import com.foryou.tax.util.JDBCUtil;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/4/28
 * @description: 从DCFL销项发票查询导入数据 临时表 process
 */
@Service
public class DcflQueryInvoiceTempProcess extends BaseProcess {

    @Autowired
    private DcflQueryInvoiceTempService dcflQueryInvoiceTempService;

    String sql = "SELECT a.document_number,\n" +
            "         MAX (a.bp_name) bp_name,\n" +
            "         MAX (a.bp_tax_registry_num) bp_tax_registry_num,\n" +
            "         MAX (a.bp_address_phone_num) bp_address_phone_num,\n" +
            "         MAX (a.bp_bank_account) bp_bank_account,\n" +
            "         MAX (a.description) description,\n" +
            "         MAX (a.total_amount) total_amount,\n" +
            "         SUM (a.tax_amount) tax_amount,\n" +
            "         MAX (a.tax_type_rate) tax_type_rate,\n" +
            "         SUM (a.net_amount) net_amount,\n" +
            "         MAX (a.internal_period_num) internal_period_num,\n" +
            "         MAX (a.invoice_date) invoice_date,\n" +
            "         MAX (a.invoice_number) invoice_number,\n" +
            "         MAX (a.invalid_flag) invalid_flag,\n" +
            "         MAX (a.confirmed_date) confirmed_date,\n" +
            "         MAX (a.confirmed_by_n) confirmed_by_n,\n" +
            "         MAX (a.created_by_n) created_by_n,\n" +
            "         MAX (a.creation_date) creation_date,\n" +
            "         MAX (a.vat_invoice_code) vat_invoice_code,\n" +
            "         CASE WHEN MAX (a.cf_item) = 101 THEN 1 ELSE MAX (a.cf_item) END\n" +
            "            cf_item,\n" +
            "         CASE\n" +
            "            WHEN MAX (a.cf_item_n) = '租息' THEN '租金'\n" +
            "            ELSE MAX (a.cf_item_n)\n" +
            "         END\n" +
            "            cf_item_desc\n" +
            "    FROM ACR_INVOICE_HD_DETAIL_LV a\n" +
            "   WHERE a.accounting_date BETWEEN SYSDATE - 7 AND SYSDATE\n" +
            "GROUP BY a.document_number";

    public void dcflQueryInvoiceTempImport(HttpServletRequest request, HttpServletResponse response) {

        LoggerUtils.debug(getClass(), "DCFL_QUERY_INVOICE_TEMP 备份开始");
        String newTableName = "DCFL_QUERY_INVOICE_TEMP_" + DateUtil.parseDate(new Date());
        dcflQueryInvoiceTempService.backUpData(newTableName);
        LoggerUtils.debug(getClass(), "DCFL_QUERY_INVOICE_TEMP 备份结束");

        LoggerUtils.debug(getClass(), "DCFL_QUERY_INVOICE_TEMP 删除开始");
        dcflQueryInvoiceTempService.deleteData();
        LoggerUtils.debug(getClass(), "DCFL_QUERY_INVOICE_TEMP 删除结束");

        LoggerUtils.debug(getClass(), "DCFL_QUERY_INVOICE_TEMP sql is: " + sql);

        List<Map<String, String>> mapList = JDBCUtil.selectData(sql);

        for (int i = 0; i < mapList.size(); i++){
            DcflQueryInvoiceTemp dcflQueryInvoiceTemp = new DcflQueryInvoiceTemp();
            dcflQueryInvoiceTemp.setDocumentNumber(Convert.toStr(mapList.get(i).get("document_number")));
            dcflQueryInvoiceTemp.setInvoiceCode(Convert.toStr(mapList.get(i).get("vat_invoice_code")));
            dcflQueryInvoiceTemp.setInvoiceNumber(Convert.toStr(mapList.get(i).get("invoice_number")));
            dcflQueryInvoiceTemp.setInvoiceTitle(Convert.toStr(mapList.get(i).get("bp_name")));
            dcflQueryInvoiceTemp.setTaxRegistryNum(Convert.toStr(mapList.get(i).get("bp_tax_registry_num")));
            dcflQueryInvoiceTemp.setInvoiceObjectAddressPhone(Convert.toStr(mapList.get(i).get("bp_address_phone_num")));
            dcflQueryInvoiceTemp.setInvoiceObjectBankAccount(Convert.toStr(mapList.get(i).get("bp_bank_account")));
            dcflQueryInvoiceTemp.setIssuedTime(Convert.toDate(mapList.get(i).get("invoice_date")));
            dcflQueryInvoiceTemp.setIssuedMonth(Convert.toStr(mapList.get(i).get("internal_period_num")));
            dcflQueryInvoiceTemp.setTotalAmount(Convert.toBigDecimal(mapList.get(i).get("total_amount")));
            dcflQueryInvoiceTemp.setTaxAmount(Convert.toBigDecimal(mapList.get(i).get("tax_amount")));
            dcflQueryInvoiceTemp.setTaxNetAmount(Convert.toBigDecimal(mapList.get(i).get("net_amount")));
            dcflQueryInvoiceTemp.setTaxRate(Convert.toBigDecimal(mapList.get(i).get("tax_type_rate")));
            dcflQueryInvoiceTemp.setInvoiceMemo(Convert.toStr(mapList.get(i).get("description")));
            dcflQueryInvoiceTemp.setIssuer(Convert.toStr(mapList.get(i).get("created_by_n")));
            dcflQueryInvoiceTemp.setReviewer(Convert.toStr(mapList.get(i).get("confirmed_by_n")));
            dcflQueryInvoiceTemp.setInvoiceInvalidFlag(Convert.toStr(mapList.get(i).get("invalid_flag")));
            dcflQueryInvoiceTemp.setCreateTime(Convert.toDate(mapList.get(i).get("creation_date")));
            dcflQueryInvoiceTemp.setCfItem(Convert.toStr(mapList.get(i).get("cf_item")));
            dcflQueryInvoiceTemp.setCfItemDesc(mapList.get(i).get("cf_item_desc"));

            LoggerUtils.debug(getClass(), "DcflQueryInvoiceTemp is: " + dcflQueryInvoiceTemp);
            dcflQueryInvoiceTempService.insertData(dcflQueryInvoiceTemp);
        }

    }
}
