package com.foryou.tax.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.process.user.UserProcess;
import com.foryou.tax.util.CommonUtil;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: raymon
 * @description: 登录相关Controller
 * @date: 2017/10/24 10:33
 */
@Controller
public class LoginController {

    @Autowired
    private UserProcess userProcess;

    /**
     * 登录
     *
     */
    @RequestMapping("/login/auth")
    public void authLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password");
        LoggerUtils.debug(getClass(), requestJson.toJSONString());
        userProcess.authLogin(request, response, requestJson);
    }

    /**
     * 查询当前登录用户的信息
     *
     */
    @PostMapping("/getInfo")
    public void getInfo(HttpServletRequest request, HttpServletResponse response) {
        userProcess.getInfo(request, response);
    }

    /**
     * 登出
     *
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        userProcess.logout(request, response);
    }


    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "tax/login";
    }

    @RequestMapping(value = "/weeklyIndex", method = {RequestMethod.GET})
    public String weeklyIndex() {
        LoggerUtils.debug(getClass(), "进入主页");
        return "tax/weekly_index";
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public String notFound() { return "common/404"; }

    @RequestMapping(value = "/500", method = {RequestMethod.GET})
    public String exception() { return "common/500"; }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET})
    public String test() {
        return "user/index";
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.GET})
    public String ad() {
        return "user/file_upload";
    }

    @RequestMapping(value = "/testJson")
    public void testJson(HttpServletRequest request, HttpServletResponse response) {
        userProcess.testJson(request, response);
    }
}
