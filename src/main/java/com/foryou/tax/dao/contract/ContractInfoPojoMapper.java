package com.foryou.tax.dao.contract;


import com.foryou.tax.pojo.contract.ContractInfoPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ContractInfoPojoMapper {

    ContractInfoPojo queryDataByContractNO(ContractInfoPojo record);

    List<ContractInfoPojo> queryData();
}
