package com.foryou.tax.process.allinvoice;

import cn.hutool.core.convert.Convert;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfoTemp;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.allinvoice.AllInvoiceInfoTempService;
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
 * @date ：Created in 2020/4/8
 * @description : 发票总表 process
 */
@Service
public class AllInvoiceProcess extends BaseProcess {

    @Autowired
    AllInvoiceInfoTempService allInvoiceInfoTempService;

    public void allInvoiceImport(HttpServletRequest request, HttpServletResponse response) {

        String sql = "SELECT T1.COMPANY_ID,\n" +
                "         T1.PROJECT_ID,\n" +
                "         T1.PROJECT_NUMBER,\n" +
                "         T1.PROJECT_NAME,\n" +
                "         T1.CONTRACT_ID,\n" +
                "         T1.CONTRACT_NUMBER,\n" +
                "         T1.CONTRACT_NAME,\n" +
                "         T1.INCEPTION_OF_LEASE,\n" +
                "         T1.CONTRACT_STATUS,\n" +
                "         T1.CONTRACT_STATUS_DESC,\n" +
                "         T1.BILLING_METHOD,\n" +
                "         T1.BILLING_METHOD_DESC,\n" +
                "         DECODE (T2.CF_ITEM,\n" +
                "                 301, (SELECT CC.BP_ID_AGENT_LEVEL1\n" +
                "                         FROM CON_CONTRACT CC\n" +
                "                        WHERE CC.CONTRACT_ID = T1.CONTRACT_ID),\n" +
                "                 300, (SELECT CTI.PURCHASER_BP_ID\n" +
                "                         FROM CON_TRAILER_INFO CTI\n" +
                "                        WHERE CTI.CONTRACT_ID = T1.CONTRACT_ID AND ROWNUM = 1),\n" +
                "                 T2.BILLING_ID)\n" +
                "            AS BILLING_OBJECT_ID,\n" +
                "         T1.BILLING_OBJECT_CODE,\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT CC.BP_ID_AGENT_LEVEL1_N\n" +
                "                    FROM CON_CONTRACT_LV CC\n" +
                "                   WHERE CC.CONTRACT_ID = T1.CONTRACT_ID),\n" +
                "            300, (SELECT HBM.BP_NAME\n" +
                "                    FROM HLS_BP_MASTER HBM\n" +
                "                   WHERE HBM.BP_ID =\n" +
                "                            (SELECT CTI.PURCHASER_BP_ID\n" +
                "                               FROM CON_TRAILER_INFO CTI\n" +
                "                              WHERE     CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                                    AND ROWNUM = 1)),\n" +
                "            NVL (T2.BILLING_OBJECT_NAME,\n" +
                "                 (SELECT CC.BP_ID_TENANT_N\n" +
                "                    FROM CON_CONTRACT_LV CC\n" +
                "                   WHERE CC.CONTRACT_ID = T1.CONTRACT_ID)))\n" +
                "            BILLING_OBJECT_NAME,\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT H.TAXPAYER_TYPE\n" +
                "                    FROM HLS_BP_MASTER H, CON_CONTRACT CC\n" +
                "                   WHERE     CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID),\n" +
                "            300, (SELECT H.TAXPAYER_TYPE\n" +
                "                    FROM HLS_BP_MASTER H, CON_TRAILER_INFO CTI\n" +
                "                   WHERE     CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CTI.PURCHASER_BP_ID = H.BP_ID),\n" +
                "            T1.OBJECT_TAXPAYER_TYPE)\n" +
                "            OBJECT_TAXPAYER_TYPE,\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT V.CODE_VALUE_NAME\n" +
                "                    FROM HLS_BP_MASTER H, CON_CONTRACT CC, SYS_CODE_VALUES_V V\n" +
                "                   WHERE     V.CODE = 'HLS211_TAXPAYER_TYPE'\n" +
                "                         AND V.CODE_VALUE = H.TAXPAYER_TYPE\n" +
                "                         AND CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID),\n" +
                "            300, (SELECT V.CODE_VALUE_NAME\n" +
                "                    FROM HLS_BP_MASTER H,\n" +
                "                         CON_TRAILER_INFO CTI,\n" +
                "                         SYS_CODE_VALUES_V V\n" +
                "                   WHERE     V.CODE = 'HLS211_TAXPAYER_TYPE'\n" +
                "                         AND V.CODE_VALUE = H.TAXPAYER_TYPE\n" +
                "                         AND CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CTI.PURCHASER_BP_ID = H.BP_ID),\n" +
                "            T1.OBJECT_TAXPAYER_TYPE_DESC)\n" +
                "            OBJECT_TAXPAYER_TYPE_DESC,\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT H.TAX_REGISTRY_NUM\n" +
                "                    FROM HLS_BP_MASTER H, CON_CONTRACT CC\n" +
                "                   WHERE     CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID),\n" +
                "            300, (SELECT H.TAX_REGISTRY_NUM\n" +
                "                    FROM HLS_BP_MASTER H, CON_TRAILER_INFO CTI\n" +
                "                   WHERE     CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CTI.PURCHASER_BP_ID = H.BP_ID),\n" +
                "            T2.TAX_REGISTRY_NUM)\n" +
                "            OBJECT_TAX_REGISTRY_NUM,\n" +
                "         NVL (T2.BILL_OBJECT_BP_CLASS, T1.BILL_OBJECT_BP_CLASS)\n" +
                "            BILL_OBJECT_BP_CLASS,\n" +
                "         NVL (T2.BILL_OBJECT_BP_CLASS_DESC, T1.BILL_OBJECT_BP_CLASS_DESC)\n" +
                "            BILL_OBJECT_BP_CLASS_DESC,\n" +
                "         T1.TAX_TYPE_VAT,\n" +
                "         T1.DESCRIPTION,\n" +
                "         --t1.INVOICE_TITLE,\n" +
                "         --t1.INVOICE_BP_ADDRESS_PHONE_NUM,\n" +
                "         --t1.INVOICE_BP_BANK_ACCOUNT,\n" +
                "         -- t1.TAX_REGISTRY_NUM,\n" +
                "         --start 融资二期 by sunmingrui 20200109  若为NP，则发票抬头直接填充bp_name\n" +
                "         --start 融资二期 by sunmingrui  20200316 对于非301、300的情况，若NP取bp_name\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT DECODE (H.BP_CLASS, 'NP', H.BP_NAME, H.INVOICE_TITLE)\n" +
                "                    FROM HLS_BP_MASTER H, CON_CONTRACT CC\n" +
                "                   WHERE     CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID),\n" +
                "            300, (SELECT DECODE (H.BP_CLASS, 'NP', H.BP_NAME, H.INVOICE_TITLE)\n" +
                "                    FROM HLS_BP_MASTER H, CON_TRAILER_INFO CTI\n" +
                "                   WHERE     CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CTI.PURCHASER_BP_ID = H.BP_ID),\n" +
                "            DECODE (T2.BILL_OBJECT_BP_CLASS,\n" +
                "                    'NP', T2.BILLING_OBJECT_NAME,\n" +
                "                    T2.INVOICE_TITLE))\n" +
                "            INVOICE_TITLE,\n" +
                "         --start 融资二期 by sunmingrui  20200316 对于非301、300的情况，若NP取bp_name\n" +
                "         --end  融资二期 sunmingrui 20200109  若为NP，则发票抬头直接填充bp_name\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT (H.INVOICE_BP_ADDRESS || '' || H.INVOICE_BP_PHONE_NUM)\n" +
                "                    FROM HLS_BP_MASTER H, CON_CONTRACT CC\n" +
                "                   WHERE     CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID),\n" +
                "            300, (SELECT (H.INVOICE_BP_ADDRESS || '' || H.INVOICE_BP_PHONE_NUM)\n" +
                "                    FROM HLS_BP_MASTER H, CON_TRAILER_INFO CTI\n" +
                "                   WHERE     CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CTI.PURCHASER_BP_ID = H.BP_ID),\n" +
                "            T2.INVOICE_BP_ADDRESS_PHONE_NUM)\n" +
                "            INVOICE_BP_ADDRESS_PHONE_NUM,\n" +
                "         DECODE (\n" +
                "            T2.CF_ITEM,\n" +
                "            301, (SELECT (   H.INVOICE_BP_BANK\n" +
                "                          || ''\n" +
                "                          || H.INVOICE_BP_BANK_ACCOUNT_ID)\n" +
                "                    FROM HLS_BP_MASTER H, CON_CONTRACT CC\n" +
                "                   WHERE     CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID),\n" +
                "            300, (SELECT (   H.INVOICE_BP_BANK\n" +
                "                          || ''\n" +
                "                          || H.INVOICE_BP_BANK_ACCOUNT_ID)\n" +
                "                    FROM HLS_BP_MASTER H, CON_TRAILER_INFO CTI\n" +
                "                   WHERE     CTI.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                         AND CTI.PURCHASER_BP_ID = H.BP_ID),\n" +
                "            T2.INVOICE_BP_BANK_ACCOUNT)\n" +
                "            INVOICE_BP_BANK_ACCOUNT,\n" +
                "         T2.TAX_REGISTRY_NUM,\n" +
                "         T1.PRJ_SEARCH_TERM_1,\n" +
                "         T1.PRJ_SEARCH_TERM_2,\n" +
                "         T1.CON_SEARCH_TERM_1,\n" +
                "         T1.CON_SEARCH_TERM_2,\n" +
                "         T1.DOCUMENT_TYPE,\n" +
                "         T1.BP_NAME,\n" +
                "         T2.CASHFLOW_ID,\n" +
                "         T2.CF_ITEM,\n" +
                "         T2.CF_ITEM_DESC,\n" +
                "         T2.TIMES,\n" +
                "         T2.LAST_RECEIVED_DATE,\n" +
                "         T2.DUE_DATE,\n" +
                "         T2.DUE_AMOUNT,\n" +
                "         T2.PRINCIPAL,\n" +
                "         T2.INTEREST,\n" +
                "         T2.RECEIVED_AMOUNT,\n" +
                "         T2.RECEIVED_PRINCIPAL,\n" +
                "         T2.RECEIVED_INTEREST,\n" +
                "         T2.NOTRECEIVED_AMOUNT,\n" +
                "         T2.NOTRECEIVED_PRINCIPAL,\n" +
                "         T2.NOTRECEIVED_INTEREST,\n" +
                "         T2.BILLING_AMOUNT,\n" +
                "         T2.BILLING_PRINCIPAL,\n" +
                "         T2.BILLING_INTEREST,\n" +
                "         DECODE (T1.BUSINESS_TYPE,\n" +
                "                 'LEASEBACK', T2.NOTBILLING_AMOUNT - T2.NOTBILLING_PRINCIPAL,\n" +
                "                 T2.NOTBILLING_AMOUNT)\n" +
                "            NOTBILLING_AMOUNT,\n" +
                "         DECODE (T1.BUSINESS_TYPE, 'LEASEBACK', 0, T2.NOTBILLING_PRINCIPAL)\n" +
                "            NOTBILLING_PRINCIPAL,\n" +
                "         T2.NOTBILLING_INTEREST,\n" +
                "         NVL (T2.VAT_DUE_AMOUNT, 0) VAT_DUE_AMOUNT,\n" +
                "         NVL (T2.VAT_PRINCIPAL, 0) VAT_PRINCIPAL,\n" +
                "         NVL (T2.VAT_INTEREST, 0) VAT_INTEREST,\n" +
                "         NVL (T2.NET_DUE_AMOUNT, 0) NET_DUE_AMOUNT,\n" +
                "         NVL (T2.NET_PRINCIPAL, 0) NET_PRINCIPAL,\n" +
                "         NVL (T2.NET_INTEREST, 0) NET_INTEREST,\n" +
                "         T2.CURRENCY,\n" +
                "         T2.CURRENCY_DESC,\n" +
                "         T2.EXCHANGE_RATE,\n" +
                "         T2.EXCHANGE_RATE_TYPE,\n" +
                "         T2.EXCHANGE_RATE_TYPE_DESC,\n" +
                "         T2.EXCHANGE_RATE_QUOTATION,\n" +
                "         T2.EXCHANGE_RATE_QUOTATION_DESC,\n" +
                "         T1.LEASE_CHANNEL_DESC,\n" +
                "         T1.BUSINESS_TYPE,\n" +
                "         T1.BUSINESS_TYPE_DESC,\n" +
                "         T1.DIVISION,\n" +
                "         T1.DIVISION_DESC,\n" +
                "         T1.BILLING_FREQUENCY,\n" +
                "         T1.BILLING_FREQUENCY_N,\n" +
                "         T1.BILLING_WAY,\n" +
                "         T1.BILLING_WAY_N,\n" +
                "         DECODE (T2.CF_ITEM,\n" +
                "                 '2', (T2.DUE_AMOUNT - NVL (T2.BILLING_AMOUNT, 0)),\n" +
                "                 '3', (T2.DUE_AMOUNT - NVL (T2.BILLING_AMOUNT, 0)),\n" +
                "                 (T2.RECEIVED_AMOUNT - NVL (T2.BILLING_AMOUNT, 0)))\n" +
                "            NEED_BILL_AMOUNT,\n" +
                "         T1.INV_BILLING_OBJECT_NAME,\n" +
                "         T1.ID_CARD_NO AS BILLING_OBJECT_ID_CARD,\n" +
                "         --t1.small_scale_taxpayer,\n" +
                "         (SELECT T.TAXPAYER_TYPE\n" +
                "            FROM HLS_BP_MASTER_TAXPAYER_TYPE T\n" +
                "           WHERE     T.ENABLED_FLAG = 'Y'\n" +
                "                 AND T.VALID_START_DATE <= TRUNC (SYSDATE)\n" +
                "                 AND T.VALID_END_DATE > TRUNC (SYSDATE)\n" +
                "                 AND T.BP_ID = T2.BILLING_ID)\n" +
                "            SMALL_SCALE_TAXPAYER,\n" +
                "         (SELECT H.INVOICE_INFO_CONFIRM\n" +
                "            FROM HLS_BP_MASTER H, CON_CONTRACT CC\n" +
                "           WHERE     CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                 AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID)\n" +
                "            INVOICE_INFO_CONFIRM,\n" +
                "         (SELECT V.CODE_VALUE_NAME\n" +
                "            FROM HLS_BP_MASTER H, CON_CONTRACT CC, SYS_CODE_VALUES_V V\n" +
                "           WHERE     V.CODE = 'YES_NO'\n" +
                "                 AND V.CODE_VALUE = H.INVOICE_INFO_CONFIRM\n" +
                "                 AND CC.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "                 AND CC.BP_ID_AGENT_LEVEL1 = H.BP_ID)\n" +
                "            INVOICE_INFO_CONFIRM_DESC,\n" +
                "         (SELECT MAX (A.JOURNAL_DATE)\n" +
                "            FROM CSH_WRITE_OFF A\n" +
                "           WHERE     A.CF_ITEM = T2.CF_ITEM\n" +
                "                 AND A.TIMES = T2.TIMES\n" +
                "                 AND A.CONTRACT_ID = T2.CONTRACT_ID\n" +
                "                 AND REVERSED_FLAG = 'N')\n" +
                "            AS LAST_WRITE_OFF_DATE,\n" +
                "         T2.EXPEDITED_FLAG\n" +
                "    FROM ACR_INVOICE_CONTRACT_V T1, ACR_INVOICE_CONTRACT_CF_V T2\n" +
                "   WHERE     T2.CONTRACT_ID = T1.CONTRACT_ID\n" +
                "         AND T1.BILLING_STATUS IN ('NOT', 'PARTIAL')\n" +
                "         AND T1.CONTRACT_STATUS NOT IN ('CLOSED', 'CANCEL')\n" +
                "         AND T2.CF_STATUS = 'RELEASE'\n" +
                "         AND T2.WRITE_OFF_FLAG != 'NOT'\n" +
                "         AND T2.BILLING_STATUS != 'FULL'\n" +
                "         AND T2.CF_DIRECTION = 'INFLOW'\n" +
                "         AND T2.CF_ITEM_DESC NOT IN ('诉讼费回收',\n" +
                "                                     '营业外收入',\n" +
                "                                     '代理商代付客户保证金',\n" +
                "                                     '应收保险费',\n" +
                "                                     '保证金')\n" +
                "         AND NOT (T2.CF_ITEM = 301 AND NVL (DIRECT_SALES_AGENT_FLAG, 'N') = 'Y')\n" +
                "         AND T2.NOTRECEIVED_AMOUNT >= 0\n" +
                "         AND T2.NOTRECEIVED_AMOUNT <= 0\n" +
                "         AND T2.NOTBILLING_AMOUNT >= 0.1\n" +
//                "         AND TO_CHAR(SYSDATE, 'yyyy/mm/dd') <= \n" +
//                "              TO_CHAR((SELECT MAX (A.JOURNAL_DATE)\n" +
//                "            FROM CSH_WRITE_OFF A\n" +
//                "           WHERE     A.CF_ITEM = T2.CF_ITEM\n" +
//                "                 AND A.TIMES = T2.TIMES\n" +
//                "                 AND A.CONTRACT_ID = T2.CONTRACT_ID\n" +
//                "                 AND REVERSED_FLAG = 'N') + 7, 'yyyy/mm/dd')"+
                "ORDER BY T2.EXPEDITED_FLAG,\n" +
                "         T1.CONTRACT_ID,\n" +
                "         T2.TIMES,\n" +
                "         T2.DUE_DATE,\n" +
                "         T2.CF_ITEM";

        LoggerUtils.debug(getClass(), "AllInvoiceInfoTemp sql is: " + sql);

        String dt = DateUtil.format(new Date());
        allInvoiceInfoTempService.backUpData(dt);
        List<Map<String, String>> mapList = JDBCUtil.selectData(sql);

        for (int i = 0; i < mapList.size(); i++) {
            AllInvoiceInfoTemp allInvoiceInfoTemp = new AllInvoiceInfoTemp();
            allInvoiceInfoTemp.setCompanyId(Convert.toStr(mapList.get(i).get("company_id")));
            allInvoiceInfoTemp.setProjectId(Convert.toStr(mapList.get(i).get("project_id")));
            allInvoiceInfoTemp.setProjectNumber(mapList.get(i).get("project_number"));
            allInvoiceInfoTemp.setProjectName(mapList.get(i).get("project_name"));
            allInvoiceInfoTemp.setContractId(Convert.toStr(mapList.get(i).get("contract_id")));
            allInvoiceInfoTemp.setContractNumber(mapList.get(i).get("contract_number"));
            allInvoiceInfoTemp.setContractName(mapList.get(i).get("contract_name"));
            allInvoiceInfoTemp.setInceptionOfLease(Convert.toDate(mapList.get(i).get("inception_of_lease"), null));
            allInvoiceInfoTemp.setContractStatus(mapList.get(i).get("contract_status"));
            allInvoiceInfoTemp.setContractStatusDesc(mapList.get(i).get("contract_status_desc"));
            allInvoiceInfoTemp.setBillingMethod(mapList.get(i).get("billing_method"));
            allInvoiceInfoTemp.setBillingMethodDesc(mapList.get(i).get("billing_method_desc"));
            allInvoiceInfoTemp.setBillingObjectId(Convert.toStr(mapList.get(i).get("billing_object_id")));
            allInvoiceInfoTemp.setBillingObjectCode(mapList.get(i).get("billing_object_code"));
            allInvoiceInfoTemp.setBillingObjectName(mapList.get(i).get("billing_object_name"));
            allInvoiceInfoTemp.setBillingObjectIdCard(Convert.toStr(mapList.get(i).get("billing_object_id_card")));
            allInvoiceInfoTemp.setObjectTaxpayerType(mapList.get(i).get("object_taxpayer_type"));
            allInvoiceInfoTemp.setObjectTaxpayerTypeDesc(mapList.get(i).get("object_taxpayer_type_desc"));
            allInvoiceInfoTemp.setObjectTaxRegistryNum(mapList.get(i).get("object_tax_registry_num"));
            allInvoiceInfoTemp.setBillObjectBpClass(mapList.get(i).get("bill_object_bp_class"));
            allInvoiceInfoTemp.setBillObjectBpClassDesc(mapList.get(i).get("bill_object_bp_class_desc"));
            allInvoiceInfoTemp.setTaxTypeVat(mapList.get(i).get("tax_type_vat"));
            allInvoiceInfoTemp.setDescription(mapList.get(i).get("description"));
            allInvoiceInfoTemp.setInvoiceTitle(mapList.get(i).get("invoice_title"));
            allInvoiceInfoTemp.setInvoiceBpAddressPhoneNum(mapList.get(i).get("invoice_bp_address_phone_num"));
            allInvoiceInfoTemp.setInvoiceBpBankAccount(mapList.get(i).get("invoice_bp_bank_account"));
            allInvoiceInfoTemp.setTaxRegistryNum(mapList.get(i).get("tax_registry_num"));
            allInvoiceInfoTemp.setPrjSearchTerm1(mapList.get(i).get("prj_search_term_1"));
            allInvoiceInfoTemp.setPrjSearchTerm2(mapList.get(i).get("prj_search_term_2"));
            allInvoiceInfoTemp.setConSearchTerm1(mapList.get(i).get("con_search_term_1"));
            allInvoiceInfoTemp.setConSearchTerm2(mapList.get(i).get("con_search_term_2"));
            allInvoiceInfoTemp.setDocumentType(mapList.get(i).get("document_type"));
            allInvoiceInfoTemp.setBpName(mapList.get(i).get("bp_name"));
            allInvoiceInfoTemp.setCashflowId(Convert.toStr(mapList.get(i).get("cashflow_id")));
            allInvoiceInfoTemp.setCfItem(Convert.toStr(mapList.get(i).get("cf_item")));
            allInvoiceInfoTemp.setCfItemDesc(mapList.get(i).get("cf_item_desc"));
            allInvoiceInfoTemp.setTimes(Convert.toStr(mapList.get(i).get("times")));
            allInvoiceInfoTemp.setLastReceivedDate(Convert.toDate(mapList.get(i).get("last_received_date"), null));
            allInvoiceInfoTemp.setDueDate(Convert.toDate(mapList.get(i).get("due_date"), null));
            allInvoiceInfoTemp.setDueAmount(Convert.toBigDecimal(mapList.get(i).get("due_amount"), null));
            allInvoiceInfoTemp.setPrincipal(Convert.toBigDecimal(mapList.get(i).get("principal"), null));
            allInvoiceInfoTemp.setInterest(Convert.toBigDecimal(mapList.get(i).get("interest"), null));
            allInvoiceInfoTemp.setReceivedAmount(Convert.toBigDecimal(mapList.get(i).get("received_amount"), null));
            allInvoiceInfoTemp.setReceivedPrincipal(Convert.toBigDecimal(mapList.get(i).get("received_principal"), null));
            allInvoiceInfoTemp.setReceivedInterest(Convert.toBigDecimal(mapList.get(i).get("received_interest"), null));
            allInvoiceInfoTemp.setNotreceivedAmount(Convert.toBigDecimal(mapList.get(i).get("notreceived_amount"), null));
            allInvoiceInfoTemp.setNotreceivedPrincipal(Convert.toBigDecimal(mapList.get(i).get("notreceived_principal"), null));
            allInvoiceInfoTemp.setNotreceivedInterest(Convert.toBigDecimal(mapList.get(i).get("notreceived_interest"), null));
            allInvoiceInfoTemp.setBillingAmount(Convert.toBigDecimal(mapList.get(i).get("billing_amount"), null));
            allInvoiceInfoTemp.setBillingPrincipal(Convert.toBigDecimal(mapList.get(i).get("billing_principal"), null));
            allInvoiceInfoTemp.setBillingInterest(Convert.toBigDecimal(mapList.get(i).get("billing_interest"), null));
            allInvoiceInfoTemp.setNeedBillingAmount(Convert.toBigDecimal(mapList.get(i).get("notbilling_amount"), null));
            allInvoiceInfoTemp.setNotbillingPrincipal(Convert.toBigDecimal(mapList.get(i).get("notbilling_principal"), null));
            allInvoiceInfoTemp.setNotbillingInterest(Convert.toBigDecimal(mapList.get(i).get("notbilling_interest"), null));
            allInvoiceInfoTemp.setVatDueAmount(Convert.toBigDecimal(mapList.get(i).get("vat_due_amount"), null));
            allInvoiceInfoTemp.setVatPrincipal(Convert.toBigDecimal(mapList.get(i).get("vat_principal"), null));
            allInvoiceInfoTemp.setVatInterest(Convert.toBigDecimal(mapList.get(i).get("vat_interest"), null));
            allInvoiceInfoTemp.setNetDueAmount(Convert.toBigDecimal(mapList.get(i).get("net_due_amount"), null));
            allInvoiceInfoTemp.setNetPrincipal(Convert.toBigDecimal(mapList.get(i).get("net_principal"), null));
            allInvoiceInfoTemp.setNetInterest(Convert.toBigDecimal(mapList.get(i).get("net_interest"), null));
            allInvoiceInfoTemp.setCurrency(mapList.get(i).get("currency"));
            allInvoiceInfoTemp.setCurrencyDesc(mapList.get(i).get("currency_desc"));
            allInvoiceInfoTemp.setExchangeRate(Convert.toStr(mapList.get(i).get("exchange_rate")));
            allInvoiceInfoTemp.setExchangeRateTypeDesc(mapList.get(i).get("exchange_rate_type"));
            allInvoiceInfoTemp.setExchangeRateTypeDesc(mapList.get(i).get("exchange_rate_type_desc"));
            allInvoiceInfoTemp.setExchangeRateQuotation(mapList.get(i).get("exchange_rate_quotation"));
            allInvoiceInfoTemp.setExchangeRateQuotationDesc(mapList.get(i).get("exchange_rate_quotation_desc"));
            allInvoiceInfoTemp.setLeaseChannelDesc(mapList.get(i).get("lease_channel_desc"));
            allInvoiceInfoTemp.setBusinessType(Convert.toStr(mapList.get(i).get("business_type")));
            allInvoiceInfoTemp.setBusinessTypeDesc(mapList.get(i).get("business_type_desc"));
            allInvoiceInfoTemp.setDivision(Convert.toStr(mapList.get(i).get("division")));
            allInvoiceInfoTemp.setDivisionDesc(mapList.get(i).get("division_desc"));
            allInvoiceInfoTemp.setBillingFrequency(mapList.get(i).get("billing_frequency"));
            allInvoiceInfoTemp.setBillingFrequencyN(mapList.get(i).get("billing_frequency_n"));
            allInvoiceInfoTemp.setBillingWay(Convert.toStr(mapList.get(i).get("billing_way")));
            allInvoiceInfoTemp.setBillingWayN(mapList.get(i).get("billing_way_n"));
            allInvoiceInfoTemp.setNeedBillingAmount(Convert.toBigDecimal(mapList.get(i).get("need_billing_amount"), null));
            allInvoiceInfoTemp.setInvBillingObjectName(mapList.get(i).get("inv_billing_object_name"));
            allInvoiceInfoTemp.setSmallScaleTaxpayer(mapList.get(i).get("small_scale_taxpayer"));
            allInvoiceInfoTemp.setInvoiceInfoConfirm(mapList.get(i).get("invoice_info_confirm"));
            allInvoiceInfoTemp.setInvoiceInfoConfirmDesc(mapList.get(i).get("invoice_info_confirm_desc"));
            allInvoiceInfoTemp.setLastWriteOffDate(Convert.toDate(mapList.get(i).get("last_write_off_date"), null));
            allInvoiceInfoTemp.setExpeditedFlag(mapList.get(i).get("expedited_flag"));

            LoggerUtils.debug(getClass(), "allInvoiceInfoTemp is: " + allInvoiceInfoTemp);
            allInvoiceInfoTempService.insertData(allInvoiceInfoTemp);
        }
    }
}
