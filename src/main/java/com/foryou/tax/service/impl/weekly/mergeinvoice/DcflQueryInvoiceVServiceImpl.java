package com.foryou.tax.service.impl.weekly.mergeinvoice;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.dao.weekly.mergeinvoice.DcflQueryInvoiceVMapper;
import com.foryou.tax.pojo.weekly.mergeinvoice.DcflQueryInvoiceV;
import com.foryou.tax.service.redis.RedisService;
import com.foryou.tax.service.weekly.mergeinvoice.DcflQueryInvoiceVService;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-05-11
 */
@Service
public class DcflQueryInvoiceVServiceImpl implements DcflQueryInvoiceVService {

    @Autowired
    DcflQueryInvoiceVMapper dcflQueryInvoiceVMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public DcflQueryInvoiceV dcflQueryInvoiceDataByKeywords(String keyWords) {
        return dcflQueryInvoiceVMapper.dcflQueryInvoiceDataByKeywords(keyWords);
    }

    @Override
    public List<DcflQueryInvoiceV> queryDcflInvoiceData() {
        LoggerUtils.debug(getClass(), "------ DcflQueryInvoiceVServiceImpl queryDcflInvoiceData start --------");

        DcflQueryInvoiceV dcflQueryInvoiceV = null;
        String redisValue = JSONObject.toJSONString(redisService.get("queryDcflImportDataByRedis"));
        List<DcflQueryInvoiceV> list = new ArrayList<>();

        if(!"null".equals(redisValue)){
            JSONArray jsonArray = JSONArray.parseArray(redisValue);
            LoggerUtils.debug(getClass(), "DcflQueryInvoiceVServiceImpl queryDcflInvoiceData List<DcflQueryInvoiceV> jsonArray is: " + jsonArray);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(Convert.toStr(jsonArray.get(i)));
                dcflQueryInvoiceV = JSON.toJavaObject(jsonObject, DcflQueryInvoiceV.class);
                LoggerUtils.debug(getClass(), "DcflQueryInvoiceVServiceImpl queryDcflInvoiceData List<DcflQueryInvoiceV> JSONObject to DcflQueryInvoiceV is: " + dcflQueryInvoiceV);
                list.add(dcflQueryInvoiceV);
            }
            LoggerUtils.debug(getClass(), "DcflQueryInvoiceVServiceImpl queryDcflInvoiceData List<DcflQueryInvoiceV> from redis is: " + list);
        }else{
            List<DcflQueryInvoiceV> ll = dcflQueryInvoiceVMapper.queryDcflInvoiceData();
            LoggerUtils.debug(getClass(), "DcflQueryInvoiceVServiceImpl queryDcflInvoiceData List<DcflQueryInvoiceV> from DB is: " + ll);
            redisService.putValue("queryDcflImportDataByRedis", ll, 60);
            list = ll;
        }
        LoggerUtils.debug(getClass(), "------ DcflQueryInvoiceVServiceImpl queryDcflInvoiceData end --------");
        return list;
    }
}
