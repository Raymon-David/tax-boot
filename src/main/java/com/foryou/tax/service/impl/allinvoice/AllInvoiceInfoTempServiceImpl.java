package com.foryou.tax.service.impl.allinvoice;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.dao.allinvoice.AllInvoiceInfoTempMapper;
import com.foryou.tax.pojo.allinvoice.AllInvoiceInfoTemp;
import com.foryou.tax.service.allinvoice.AllInvoiceInfoTempService;
import com.foryou.tax.service.redis.RedisService;
import com.foryou.tax.util.JsonTools;
import com.foryou.tax.util.LoggerUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发票总表临时表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-04-08
 */
@Service
public class AllInvoiceInfoTempServiceImpl implements AllInvoiceInfoTempService {

    @Autowired
    AllInvoiceInfoTempMapper allInvoiceInfoTempMapper;

    @Autowired
    RedisService redisService;

    @Override
    public void insertData(AllInvoiceInfoTemp allInvoiceInfoTemp) {
        allInvoiceInfoTempMapper.insertData(allInvoiceInfoTemp);
    }

    @Override
    public int backUpData(@Param("newTableName") String newTableName) {
        return allInvoiceInfoTempMapper.backUpData(newTableName);
    }

    @Override
    public int deleteData() {
        return allInvoiceInfoTempMapper.deleteData();
    }

    @Override
    public List<Map<String, Object>> createInvoiceQueryWeekly() {

        LoggerUtils.debug(getClass(), "------ createInvoiceQueryWeekly start -----");

        List<Map<String, Object>> mapList = null;
        String redisValue = JSONObject.toJSONString(redisService.get("createInvoiceQueryWeekly"));
        LoggerUtils.debug(getClass(), "redisValue is: " + redisValue);

        /**
         * 因为值是从redis里取出来的，空值被赋了"null"
         */
        if (!"null".equals(redisValue)) {

            mapList = JsonTools.parseJsonArrayStrToListForMaps(redisValue);
            LoggerUtils.debug(getClass(), "createInvoiceQueryWeekly from redis is: " + mapList);

        }else {
            List<Map<String, Object>> list = allInvoiceInfoTempMapper.createInvoiceQueryWeekly();
            LoggerUtils.debug(getClass(), "createInvoiceQueryWeekly from DB is: " + list);
            redisService.putValue("createInvoiceQueryWeekly", list, 10);
            mapList = list;
        }

        LoggerUtils.debug(getClass(), "------ createInvoiceQueryWeekly end -----");

        return mapList;
    }
}
