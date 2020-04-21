package com.foryou.tax.process.writeoffinfo;

import cn.hutool.core.convert.Convert;
import com.foryou.tax.pojo.writeoffinfo.WriteOffInfoTemp;
import com.foryou.tax.service.writeoffinfo.WriteOffInfoTempService;
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
 * @date ：Created in 2020/4/13
 * @description : 核销事务 process
 */
@Service
public class WriteOffInfoProcess {

    @Autowired
    WriteOffInfoTempService writeOffInfoTempService;

    public void writeOffInfoImport(HttpServletRequest request, HttpServletResponse response) {

        String sql = "SELECT  \n" +
                "  WRITE_OFF_ID,\n" +
                "  CONTRACT_NUMBER,\n" +
                "  CONTRACT_STATUS_DESC,\n" +
                "  BP_ID_TENANT,\n" +
                "  BP_NAME,\n" +
                "  CF_ITEM,\n" +
                "  CF_ITEM_N,\n" +
                "  WRITE_OFF_DUE_AMOUNT,\n" +
                "  WRITE_OFF_PRINCIPAL,\n" +
                "  WRITE_OFF_INTEREST,\n" +
                "  DUE_DATE_C,\n" +
                "  TIMES,\n" +
                "  LEASE_TIMES,\n" +
                "  TRANSACTION_DATE_C,\n" +
                "  WRITE_OFF_DATE_C,\n" +
                "  JOURNAL_DATE_C,\n" +
                "  BANK_BRANCH_NAME,\n" +
                "  TRANSACTION_NUM,\n" +
                "  BP_BANK_ACCOUNT_NAME,\n" +
                "  TRANSACTION_TYPE,\n" +
                "  TRANSACTION_TYPE_N,\n" +
                "  RECEIPT_TYPE,\n" +
                "  RECEIPT_TYPE_N,\n" +
                "  WRITE_OFF_TYPE,\n" +
                "  WRITE_OFF_TYPE_N,\n" +
                "  WRITE_OFF_CLASSIFICATION,\n" +
                "  WRITE_OFF_CLASSIFICATION_N,\n" +
                "  COLLECTION_CLASSES,\n" +
                "  COLLECTION_CLASSES_N,\n" +
                "  REVERSED_FLAG,\n" +
                "  REVERSED_FLAG_N,\n" +
                "  REVERSED_DATE,\n" +
                "  DESCRIPTION,\n" +
                "  BP_ID_AGENT_LEVEL1,\n" +
                "  BP_ID_AGENT_LEVEL1_N,\n" +
                "  WRITE_OFF_DES,\n" +
                "  JOURNAL_NUM,\n" +
                "  SAP_BELNR,\n" +
                "  CREATED_BY,\n" +
                "  CREATED_BY_N\n" +
                "FROM WRITE_OFF_QUERY_V\n" +
                "WHERE WRITE_OFF_DATE_C between to_char(sysdate - 7, 'yyyy-mm-dd') and to_char(sysdate, 'yyyy-mm-dd')";

        LoggerUtils.debug(getClass(), "WriteOffInfoTemp sql is: " + sql);

        String dt = DateUtil.format(new Date());
        writeOffInfoTempService.backUpData(dt);
        List<Map<String, String>> mapList = JDBCUtil.selectData(sql);

        for (int i = 0; i < mapList.size(); i++) {
            WriteOffInfoTemp writeOffInfoTemp = new WriteOffInfoTemp();
            writeOffInfoTemp.setWriteOffId(Convert.toStr(mapList.get(i).get("write_off_id")));
            writeOffInfoTemp.setContractNumber(mapList.get(i).get("contract_number"));
            writeOffInfoTemp.setContractStatusDesc(mapList.get(i).get("contract_status_desc"));
            writeOffInfoTemp.setBpIdTenant(Convert.toStr(mapList.get(i).get("bp_id_tenant")));
            writeOffInfoTemp.setBpName(mapList.get(i).get("bp_name"));
            writeOffInfoTemp.setCfItem(Convert.toStr(mapList.get(i).get("cf_item")));
            writeOffInfoTemp.setCfItemN(mapList.get(i).get("cf_item_n"));
            writeOffInfoTemp.setWriteOffDueAmount(Convert.toBigDecimal(mapList.get(i).get("write_off_due_amount")));
            writeOffInfoTemp.setWriteOffPrincipal(Convert.toBigDecimal(mapList.get(i).get("write_off_principal")));
            writeOffInfoTemp.setWriteOffInterest(Convert.toBigDecimal(mapList.get(i).get("write_off_interest")));
            writeOffInfoTemp.setDueDateC(Convert.toDate(mapList.get(i).get("due_date_c")));
            writeOffInfoTemp.setTimes(Convert.toStr(mapList.get(i).get("times")));
            writeOffInfoTemp.setLeaseTimes(Convert.toStr(mapList.get(i).get("lease_times")));
            writeOffInfoTemp.setTransactionDateC(Convert.toDate(mapList.get(i).get("transaction_date_c")));
            writeOffInfoTemp.setWriteOffDateC(Convert.toDate(mapList.get(i).get("write_off_date_c")));
            writeOffInfoTemp.setJournalDateC(Convert.toDate(mapList.get(i).get("journal_date_c")));
            writeOffInfoTemp.setBankBranchName(mapList.get(i).get("bank_branch_name"));
            writeOffInfoTemp.setTransactionNum(Convert.toStr(mapList.get(i).get("transaction_num")));
            writeOffInfoTemp.setBpBankAccountName(mapList.get(i).get("bp_bank_account_name"));
            writeOffInfoTemp.setTransactionType(Convert.toStr(mapList.get(i).get("transaction_type")));
            writeOffInfoTemp.setTransactionTypeN(mapList.get(i).get("transaction_type_n"));
            writeOffInfoTemp.setReceiptType(Convert.toStr(mapList.get(i).get("receipt_type")));
            writeOffInfoTemp.setReceiptTypeN(mapList.get(i).get("receipt_type_n"));
            writeOffInfoTemp.setWriteOffType(Convert.toStr(mapList.get(i).get("write_off_type")));
            writeOffInfoTemp.setWriteOffTypeN(mapList.get(i).get("write_off_type_n"));
            writeOffInfoTemp.setWriteOffClassification(Convert.toStr(mapList.get(i).get("write_off_classification")));
            writeOffInfoTemp.setWriteOffClassificationN(mapList.get(i).get("write_off_classification_n"));
            writeOffInfoTemp.setCollectionClasses(Convert.toStr(mapList.get(i).get("collection_classes")));
            writeOffInfoTemp.setCollectionClassesN(mapList.get(i).get("collection_classes_n"));
            writeOffInfoTemp.setReversedFlag(Convert.toStr(mapList.get(i).get("reversed_flag")));
            writeOffInfoTemp.setReversedFlagN(mapList.get(i).get("reversed_flag_n"));
            writeOffInfoTemp.setReversedDate(Convert.toDate(mapList.get(i).get("reversed_date")));
            writeOffInfoTemp.setDescription(mapList.get(i).get("description"));
            writeOffInfoTemp.setBpIdAgentLevel1(Convert.toStr(mapList.get(i).get("bp_id_agent_level1")));
            writeOffInfoTemp.setBpIdAgentLevel1N(mapList.get(i).get("bp_id_agent_level1_n"));
            writeOffInfoTemp.setWriteOffDes(mapList.get(i).get("write_off_des"));
            writeOffInfoTemp.setJournalNum(Convert.toStr(mapList.get(i).get("journal_num")));
            writeOffInfoTemp.setSapBelnr(Convert.toStr(mapList.get(i).get("sap_belnr")));
            writeOffInfoTemp.setCreatedBy(Convert.toStr(mapList.get(i).get("created_by")));
            writeOffInfoTemp.setCreatedByN(mapList.get(i).get("created_by_n"));

            LoggerUtils.debug(getClass(), "writeOffInfoTemp is: " + writeOffInfoTemp);
            writeOffInfoTempService.insertData(writeOffInfoTemp);
        }
    }
}
