package com.foryou.tax.service.impl.redis;


import com.alibaba.fastjson.JSON;
import com.foryou.tax.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/12
 * @description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    @Override
    public void putValue(String key, Object obj, Integer timeout) {
        if (null != obj) {
            ValueOperations<String, Object> operations = this.redisTemplate
                    .opsForValue();
            // 设置过期时间
            operations.set(key, obj,
                    timeout, TimeUnit.SECONDS);
        }
    }

    @Override
    public void addList(String key, List<Object> obj, Integer timeout) {
        ListOperations<String, Object> operations = redisTemplate
                .opsForList();
        operations.leftPushAll(key, obj.toArray(new Object[obj.size()]));
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void addHash(String key, Object obj, Integer timeout) {
    }

    @Override
    public Object get(String key) {
        ValueOperations<String, Object> operations = this.redisTemplate
                .opsForValue();
        Object obj = operations.get(key);
        return obj;
    }

    @Override
    public Object getList(int start, int end, String key) {
        ListOperations<String, Object> operations = redisTemplate
                .opsForList();
        List<Object> content = operations.range(key, start, end);
        return content;
    }

    @Override
    public Object getHash(String key) {
        return null;
    }

    @Override
    public  <T> T get(String key, Class<T> clazz) {
        ValueOperations<String, Object> operations = this.redisTemplate
                .opsForValue();
        Object obj = operations.get(key);
        return JSON.parseObject(obj.toString(), clazz);
    }

    @Override
    public void del(String token) {
        this.redisTemplate.delete(token);
    }

    @Override
    public void  expire(String token,int seconds){
        redisTemplate.expire(token, seconds, TimeUnit.SECONDS);
    }
}
