package com.foryou.tax.process.common;

import com.foryou.tax.api.constant.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: raymon
 * Date: 2019/6/13
 * Description:通用验证库   多个正则时，需要用;;双分号隔开
 */
public class CommonValidateMap {

    private static Map<String, Map<String, String>> validateMap = new ConcurrentHashMap<String, Map<String, String>>();
    static{
        /**
         * 手机号码验证
         */
        Map<String, String> mobileMap = new HashMap<String, String>();
        mobileMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        mobileMap.put(ICommonProcess.DEFAULT_REGEX,"^((13[0-9])|(14[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        mobileMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.PhoneModelError.getValue());
        mobileMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.PhoneModelError.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_MOBILE, mobileMap);

        /**
         * 登录密码验证
         */
        Map<String, String> loginpwdMap = new HashMap<String, String>();
        loginpwdMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        loginpwdMap.put(ICommonProcess.DEFAULT_REGEX,".*?[A-Za-z]+.*?;;.*?[0-9]+.*?;;^[a-z_A-Z0-9]{8,20}$");
        loginpwdMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserOrPwdError.getValue());
        loginpwdMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserOrPwdError.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_LOGINPWD, loginpwdMap);

        /**
         * 新登录密码验证
         */
        Map<String, String> newloginpwdMap = new HashMap<String, String>();
        newloginpwdMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        newloginpwdMap.put(ICommonProcess.DEFAULT_REGEX,".*?[A-Za-z]+.*?;;.*?[0-9]+.*?;;^[a-z_A-Z0-9]{8,20}$");
        newloginpwdMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserOrPwdError.getValue());
        newloginpwdMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserOrPwdError.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_NEWPASSWORD, newloginpwdMap);

        /**
         * 确认登录密码验证
         */
        Map<String, String> reloginpwdMap = new HashMap<String, String>();
        reloginpwdMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        reloginpwdMap.put(ICommonProcess.DEFAULT_REGEX,".*?[A-Za-z]+.*?;;.*?[0-9]+.*?;;^[a-z_A-Z0-9]{8,20}$");
        reloginpwdMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserOrPwdError.getValue());
        reloginpwdMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserOrPwdError.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_REPASSWORD, reloginpwdMap);

        /**
         * 邮箱验证
         */
        Map<String, String> emailMap = new HashMap<String, String>();
        emailMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_FALSE);
        emailMap.put(ICommonProcess.DEFAULT_REGEX,"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        emailMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.EmailValid.getValue());
        emailMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.EmailValid.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_EMAIL, emailMap);

        /**
         *身份证验证
         */
        Map<String, String> idcardMap = new HashMap<String, String>();
        idcardMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        idcardMap.put(ICommonProcess.DEFAULT_REGEX,"^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$;;^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$");
        idcardMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.IdCardVerifyFail.getValue());
        idcardMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.IdCardVerifyFail.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_IDCARD, idcardMap);

        /**
         *用户类型
         */
        Map<String, String> userTypeMap = new HashMap<String, String>();
        userTypeMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        userTypeMap.put(ICommonProcess.DEFAULT_REGEX,"^((0[1-2]){1})$");
        userTypeMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserTypeIsEmpty.getValue());
        userTypeMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserTypeIsEmpty.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_USER_TYPE, userTypeMap);

        /**
         *登录类型
         */
        Map<String, String> typeMap = new HashMap<String, String>();
        typeMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        typeMap.put(ICommonProcess.DEFAULT_REGEX,"^(([1-2]){1})$");
        typeMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserTypeIsEmpty.getValue());
        typeMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserTypeIsEmpty.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_USER_LOGIN_TYPE, typeMap);

        /**
         *用户名
         */
        Map<String, String> usernameMap = new HashMap<String, String>();
        usernameMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        usernameMap.put(ICommonProcess.DEFAULT_REGEX,"^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$||^(([0-9]){19})$");
        usernameMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserAccountNotExit.getValue());
        usernameMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserAccountNotExit.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_USER_NAME, usernameMap);

        /**
         *
         */
        /**
         *登录类型
         */
        Map<String, String> busTypeMap = new HashMap<String, String>();
        busTypeMap.put(ICommonProcess.DEFAULT_MUST, ICommonProcess.DEFAULT_TRUE);
        busTypeMap.put(ICommonProcess.DEFAULT_REGEX,"^(([1-4]){1})$");
        busTypeMap.put(ICommonProcess.DEFAULT_ERROR,"" + StatusCode.UserTypeIsEmpty.getValue());
        busTypeMap.put(ICommonProcess.DEFAULT_DESC,	"" + StatusCode.UserTypeIsEmpty.GetDescription());
        validateMap.put(ICommonProcess.DEFAULT_BIZ_TYPE, busTypeMap);

    }
    public static Map<String, String> getValueByKey(String key){
        return validateMap.get(key);
    }
}
