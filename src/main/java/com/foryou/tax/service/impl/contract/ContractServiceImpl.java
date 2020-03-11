package com.foryou.tax.service.impl.contract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.dao.contract.ContractInfoPojoMapper;
import com.foryou.tax.pojo.contract.ContractInfoPojo;
import com.foryou.tax.service.contract.ContractService;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/11
 * @description :
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractInfoPojoMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ContractInfoPojo queryContractInfoByContractNO(ContractInfoPojo record) {
        return mapper.queryDataByContractNO(record);
    }


    @Override
    public ContractInfoPojo queryContractInfoByRedis() {
        LoggerUtils.debug(getClass()," ------ ContractServiceImpl queryContractInfoByRedis start -----");

        ContractInfoPojo contractInfo = null;

        if(redisTemplate.opsForValue().get("queryContractInfoByRedis") != null){
            String str = JSON.toJSONString(redisTemplate.opsForValue().get("queryContractInfoByRedis"));
            JSONObject userJson = JSONObject.parseObject(str);
            contractInfo = JSON.toJavaObject(userJson, ContractInfoPojo.class);
        }else{
            redisTemplate.opsForValue().set("queryContractInfoByRedis", mapper.queryData());
            List<ContractInfoPojo> ll = mapper.queryData();
        }

        LoggerUtils.debug(getClass(), " ------ ContractServiceImpl queryContractInfoByRedis end -----");
        return contractInfo;
    }
}
