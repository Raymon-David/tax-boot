package com.foryou.tax.process.user;

import com.alibaba.fastjson.JSONObject;
import com.foryou.tax.api.bean.SuccessBean;
import com.foryou.tax.api.bean.success.SuccessDesc;
import com.foryou.tax.api.bean.success.SuccessInfo;
import com.foryou.tax.api.constant.Constants;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.service.UserService;
import com.foryou.tax.service.redis.RedisService;
import com.foryou.tax.service.user.LoginService;
import com.foryou.tax.util.MemberTools;
import com.foryou.tax.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/12
 * @description:
 */
@Service
public class UserProcess extends BaseProcess {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;


    /**
     * 登录
     */
    public void authLogin(HttpServletRequest request, HttpServletResponse response, JSONObject requestJson){
        JSONObject jsonObject = loginService.authLogin(requestJson);
        writeClientJson(response, jsonObject, null);
    }

    /**
     * 查询当前登录用户的信息
     *
     */
    public void getInfo(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = loginService.getInfo();
        writeClientJson(response, jsonObject, null);
    }


    /**
     * 登出
     *
     */
    public void logout(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = loginService.logout();
        /**
         * redis退出
         */
        String key = MemberTools.getCookieValue(request, Constants.USERlOGIN_KEY_EXT);
        if(StringUtils.isNotBlank(key)){
            redisService.del(key);
            //清除cookie
            MemberTools.deleteCookie(request, response, Constants.USERlOGIN_KEY_EXT);
        }
        writeClientJson(response, jsonObject, null);
    }

    public void testJson(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        SuccessBean successBean = new SuccessBean();
        SuccessInfo successInfo = new SuccessInfo();
        SuccessDesc successDesc = new SuccessDesc();

        successDesc.setCode("200");
        successDesc.setMessage("上传成功！");
        successInfo.setType("success");
        successInfo.setSuccessDesc(successDesc);
        successBean.setSuccess(successInfo);

        writeClientJson(response, successBean, null);
    }
}
