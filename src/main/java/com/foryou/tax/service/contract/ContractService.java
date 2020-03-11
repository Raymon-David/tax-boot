package com.foryou.tax.service.contract;

import com.foryou.tax.pojo.contract.ContractInfoPojo;

/**
 * 查询合同信息
 * Created by raymon.
 */
public interface ContractService {

    ContractInfoPojo queryContractInfoByRedis();

    ContractInfoPojo queryContractInfoByContractNO(ContractInfoPojo record);
}
