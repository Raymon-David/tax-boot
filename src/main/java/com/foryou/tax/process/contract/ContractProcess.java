package com.foryou.tax.process.contract;

import com.foryou.tax.pojo.contract.ContractInfoPojo;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.contract.ContractService;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description : 合同处理process
 */
@Service
public class ContractProcess extends BaseProcess {

    @Autowired
    private ContractService contractService;

    /**
     * 根据合同号查询合同信息
     */
    public void queryContractInfoByContractNO(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String contract_no){
        ContractInfoPojo record = new ContractInfoPojo();
        record.setCntrtNo(contract_no);
        ContractInfoPojo contractInfo = contractService.queryContractInfoByContractNO(record);
        LoggerUtils.debug(getClass(),"根据合同号查询合同信息" + contractInfo);
        writeClientJson(response, contractInfo, null);
    }

    /**
     * 通过redis查询合同信息
     */
    public void queryContractInfoByRedis(HttpServletRequest request, HttpServletResponse response){
        ContractInfoPojo contractInfo = contractService.queryContractInfoByRedis();
        LoggerUtils.debug(getClass(),"通过redis查询合同信息" + contractInfo);
        writeClientJson(response, contractInfo, null);
    }
}
