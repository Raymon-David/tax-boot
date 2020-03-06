package com.foryou.tax.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author: raymon
 * @date: 2017/10/30 13:28
 */
@Component
@Mapper
public interface PermissionDao  extends BaseMapper {
    /**
     * 查询用户的角色 菜单 权限
     *
     * @param username
     * @return
     */
    JSONObject getUserPermission(String username);

    /**
     * 查询所有的菜单
     *
     * @return
     */
    Set<String> getAllMenu();

    /**
     * 查询所有的权限
     *
     * @return
     */
    Set<String> getAllPermission();
}
