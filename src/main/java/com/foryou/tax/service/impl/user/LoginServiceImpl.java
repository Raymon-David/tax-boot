package com.foryou.tax.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.api.constant.Constants;
import com.foryou.tax.dao.user.LoginMapper;
import com.foryou.tax.service.PermissionService;
import com.foryou.tax.service.user.LoginService;
import com.foryou.tax.util.CommonUtil;
import com.foryou.tax.util.LoggerUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: raymon
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private PermissionService permissionService;

    /**
     * 登录表单提交
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject returnData = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        JSONObject jsonObject1 = null;
        try {
            //JSONObject jsonObject1 = this.getUser(username, password);

            currentUser.login(token);
            returnData.put("result", "success");
            jsonObject1 = CommonUtil.successJson(returnData);
        } catch (LockedAccountException lae) {
            token.clear();
            returnData.put("result", "用户已经被锁定不能登录，请与管理员联系！");
            jsonObject1 = CommonUtil.successJson(returnData);
        } catch (AuthenticationException e) {
            token.clear();
            returnData.put("result", "fail");
            jsonObject1 = CommonUtil.successJson(returnData);
        }
        LoggerUtils.debug(getClass(), jsonObject1.toJSONString());
        return jsonObject1;
    }

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public JSONObject getUser(String username, String password) {
        return loginMapper.getUser(username, password);
    }

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
    @Override
    public JSONObject getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject returnData = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        returnData.put("userPermission", userPermission);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
        }
        return CommonUtil.successJson();
    }
}
