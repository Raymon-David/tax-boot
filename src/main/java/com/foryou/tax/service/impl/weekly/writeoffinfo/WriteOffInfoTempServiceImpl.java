package com.foryou.tax.service.impl.weekly.writeoffinfo;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.dao.weekly.writeoffinfo.WriteOffInfoTempMapper;
import com.foryou.tax.pojo.weekly.writeoffinfo.WriteOffInfoTemp;
import com.foryou.tax.service.redis.RedisService;
import com.foryou.tax.service.weekly.writeoffinfo.WriteOffInfoTempService;
import com.foryou.tax.util.JsonTools;
import com.foryou.tax.util.LoggerUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 核销事务查询临时表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-04-13
 */
@Service
public class WriteOffInfoTempServiceImpl implements WriteOffInfoTempService {

    @Autowired
    WriteOffInfoTempMapper writeOffInfoTempMapper;

    @Autowired
    RedisService redisService;

    @Override
    public int insertData(WriteOffInfoTemp writeOffInfoTemp) {
        return writeOffInfoTempMapper.insertData(writeOffInfoTemp);
    }

    @Override
    public int backUpData(@Param("newTableName") String newTableName) {
        return writeOffInfoTempMapper.backUpData(newTableName);
    }

    @Override
    public int deleteData() {
        return writeOffInfoTempMapper.deleteData();
    }

    @Override
    public List<Map<String, Object>> writeOffInfoQueryWeekly() {
        LoggerUtils.debug(getClass(), "------ writeOffInfoQueryWeekly start -----");

        List<Map<String, Object>> mapList = null;
        String redisValue = JSONObject.toJSONString(redisService.get("writeOffInfoQueryWeekly"));
        LoggerUtils.debug(getClass(), "redisValue is: " + redisValue);

        /**
         * 因为值是从redis里取出来的，空值被赋了"null"
         */
        if (!"null".equals(redisValue)) {

            mapList = JsonTools.parseJsonArrayStrToListForMaps(redisValue);
            LoggerUtils.debug(getClass(), "writeOffInfoQueryWeekly from redis is: " + mapList);

        }else {
            List<Map<String, Object>> list = writeOffInfoTempMapper.writeOffInfoQueryWeekly();
            LoggerUtils.debug(getClass(), "writeOffInfoQueryWeekly from DB is: " + list);
            redisService.putValue("writeOffInfoQueryWeekly", list, 10);
            mapList = list;
        }

        LoggerUtils.debug(getClass(), "------ writeOffInfoQueryWeekly end -----");

        return mapList;
    }
}
