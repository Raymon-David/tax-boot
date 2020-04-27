package com.foryou.tax.controller.contract;

import com.foryou.tax.process.contract.ContractProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description : 操作合同controller
 */
@RestController
public class ContractController {

    @Autowired
    private ContractProcess contractProcess;

    /**
     * 根据合同号查询合同信息
     * @return
     */
    @RequestMapping(value ="/contract/queryContractInfoByContractNO" ,method = RequestMethod.GET)
    public void queryContractInfoByContractNO(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String contract_no){

        contractProcess.queryContractInfoByContractNO(request, response, contract_no);
    }

    /**
     * 通过redis查询合同信息
     */
    @RequestMapping(value ="/contract/queryContractInfoByRedis" ,method = RequestMethod.POST)
    public void queryContractInfoByRedis(HttpServletRequest request, HttpServletResponse response){

        contractProcess.queryContractInfoByRedis(request, response);
    }
}
