package com.foryou.tax.service.impl.contract;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.dao.contract.ContractInfoPojoMapper;
import com.foryou.tax.pojo.contract.ContractInfoPojo;
import com.foryou.tax.service.contract.ContractService;
import com.foryou.tax.service.redis.RedisService;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private RedisService redisService;

    @Override
    public ContractInfoPojo queryContractInfoByContractNO(ContractInfoPojo record) {
        return mapper.queryDataByContractNO(record);
    }


    @Override
    public List<ContractInfoPojo> queryContractInfoByRedis() {
        LoggerUtils.debug(getClass()," ------ ContractServiceImpl queryContractInfoByRedis start -----");

        ContractInfoPojo contractInfo = null;
        String redisValue = JSONObject.toJSONString(redisService.get("queryContractInfoByRedis"));
        List<ContractInfoPojo> list = new ArrayList<>();

        if(!"null".equals(redisValue)){
            JSONArray jsonArray = JSONArray.parseArray(redisValue);
            LoggerUtils.debug(getClass(), "ContractServiceImpl queryContractInfoByRedis List<ContractInfoPojo> jsonArray is: " + jsonArray);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(Convert.toStr(jsonArray.get(i)));
                contractInfo = JSON.toJavaObject(jsonObject, ContractInfoPojo.class);
                LoggerUtils.debug(getClass(), "ContractServiceImpl queryContractInfoByRedis List<ContractInfoPojo> JSONObject to contractInfo is: " + contractInfo);
                list.add(contractInfo);
            }
            LoggerUtils.debug(getClass(), "ContractServiceImpl queryContractInfoByRedis List<ContractInfoPojo> from redis is: " + list);
        }else{
            List<ContractInfoPojo> ll = mapper.queryData();
            LoggerUtils.debug(getClass(), "ContractServiceImpl queryContractInfoByRedis List<ContractInfoPojo> from DB is: " + ll);
            redisService.putValue("queryContractInfoByRedis", ll, 3000);
            list = ll;
        }

        LoggerUtils.debug(getClass(), " ------ ContractServiceImpl queryContractInfoByRedis end -----");
        return list;
    }
}
