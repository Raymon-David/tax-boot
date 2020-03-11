package com.foryou.tax.process.contract;

import com.foryou.tax.pojo.contract.ContractInfoPojo;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.contract.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log =  LoggerFactory.getLogger(ContractProcess.class);

    @Autowired
    private ContractService contractService;

    /**
     * 根据合同号查询合同信息
     */
    public void queryContractInfoByContractNO(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String contract_no){
        ContractInfoPojo record = new ContractInfoPojo();
        record.setCntrtNo(contract_no);
        ContractInfoPojo contractInfo = contractService.queryContractInfoByContractNO(record);
        writeClientJson(response, contractInfo, null);
    }

    /**
     * 通过redis查询合同信息
     */
    public void queryContractInfoByRedis(HttpServletRequest request, HttpServletResponse response){
        ContractInfoPojo contractInfo = contractService.queryContractInfoByRedis();
        writeClientJson(response, contractInfo, null);
    }
}
