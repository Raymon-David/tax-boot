package com.foryou.tax.service.contract;

import com.foryou.tax.pojo.contract.ContractInfoPojo;

import java.util.List;

/**
 * 查询合同信息
 * Created by raymon.
 */
public interface ContractService {

    List<ContractInfoPojo> queryContractInfoByRedis();

    ContractInfoPojo queryContractInfoByContractNO(ContractInfoPojo record);
}
