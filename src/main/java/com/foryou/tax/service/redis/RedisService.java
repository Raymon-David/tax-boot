package com.foryou.tax.service.redis;

import java.util.List;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/12
 * @description:
 */
public interface RedisService {

    void putValue(String key, Object obj, Integer timeout);

    void addList(String key, List<Object> obj, Integer timeout);

    void addHash(String key, Object obj, Integer timeout);

    Object get(String key);

    Object getList(int start, int end, String key);

    Object getHash(String key);

    void  expire(String token, int minute);



    <T> T get(String key, Class<T> clazz);

    void del(String token);
}
