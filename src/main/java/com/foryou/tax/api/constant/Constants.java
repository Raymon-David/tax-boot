package com.foryou.tax.api.constant;

/**
 * Author: raymon
 * Date: 2019/4/12
 * Description:rabbitmq所需的常量
 */
public class Constants {

    public static final String ORDER_SENDING = "0"; //发送中

    public static final String ORDER_SEND_SUCCESS = "1"; //成功

    public static final String ORDER_SEND_FAILURE = "2"; //失败

    public static final int ORDER_TIMEOUT = 1; /*分钟超时单位：min*/

    //验证码常量
    public static String RANDOMCODE_KEY = "dbjbVC";//verify key
    public static String TOKEN_KEY="dbjb_token";
    public static String RANDOMCODE_OVERDUETIME = "dbjbODT";// overduetime

    public static String USERlOGIN_KEY_EXT="lg_";

    public static String USERLOGIN_AGENT_EXT="agent";

    public static final String SUCCESS_CODE = "100";
    public static final String SUCCESS_MSG = "请求成功";

    /**
     * session中存放用户信息的key值
     */
    public static final String SESSION_USER_INFO = "userInfo";
    public static final String SESSION_USER_PERMISSION = "userPermission";
}
