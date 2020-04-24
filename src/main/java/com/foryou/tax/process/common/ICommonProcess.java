package com.foryou.tax.process.common;

import com.foryou.tax.api.bean.ErrorBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/10
 * @description : process顶级接口，定义部分方法和常量
 */
public interface ICommonProcess {
    /**
     * 默认编码方式
     */
    String DEFAULT_ENCODE="UTF-8";
    /**
     * 必要字段标识
     */
    String DEFAULT_MUST = "must";
    /**
     * 验证结果正则
     */
    String DEFAULT_REGEX = "regex";
    /**
     * 错误代码
     */
    String DEFAULT_ERROR = "error";
    /**
     * 错误描述
     */
    String DEFAULT_DESC = "desc";
    /**
     * 必要标识true
     */
    String DEFAULT_TRUE = "true";
    /**
     * 非必要标识
     */
    String DEFAULT_FALSE = "false";

    /**
     * 手机号对应key
     */
    String DEFAULT_MOBILE="mobile";

    /**
     * 登录密码对应
     */
    String  DEFAULT_LOGINPWD = "password";

    /**
     * 登录密码对应
     */
    String  DEFAULT_NEWPASSWORD = "newpassword";

    /**
     * 登录密码对应
     */
    String  DEFAULT_REPASSWORD = "repassword";
    /**
     * 支付密码验证
     */
    String  DEFAULT_PAYPWD="paypwd";

    /**
     * 支付密码验证
     */
    String  DEFAULT_NEWPAYPWD="newpaypwd";

    /**
     * 支付密码验证
     */
    String  DEFAULT_REPAYPWD="repaypwd";

    /**
     * 验证码
     */
    String  DEFAULT_CHKCODE="chkcode";


    String DEFAULT_MOBILECODE="mobileCode";
    /**
     * 身份证号码
     */
    String  DEFAULT_IDCARD="idcard";

    /**
     * 邮箱验证
     */
    String  DEFAULT_EMAIL="email";


    /**
     * 克重验证
     */
    String  DEFAULT_GRAM="gram";

    /**
     * 金额验证
     */
    String  DEFAULT_AMOUNT="amount";

    /**
     * 用户类
     */
    String DEFAULT_USER_TYPE="userType";
    /**
     * 周期
     */
    String DEFAULT_CYCLE="cycle";

    /**
     * 登录类型
     */
    String DEFAULT_USER_LOGIN_TYPE="type";
    /**
     * 用户名
     */
    String DEFAULT_USER_NAME="username";
    /**
     * 登录后参数名
     */
    String DEFAULT_TO_URL="toUrl";

    /**
     * 登录后地址
     */
    String DEFAULT_INDEX_URL="/views/capital/myCapital.html";
    /**
     * 登录地址
     */
    String DEFAULT_TO_LOGIN="/index/tologin";

    /**
     * 银行卡号
     */
    String DEFAULT_BANK_CARD ="unionCardNo";

    /**
     * 企业电话
     */
    String DEFAULT_COMPANY_PHONE ="comPhone";

    /**
     * 公司紧急联系人电话
     */
    String DEFAULT_COMPANY_EMER_PHONE ="comEmerPhone";

    /**
     * 营业执照号
     */
    String DEFAULT_COMPANY_BUSIlICENSE ="busiLicense";


    /**
     * 优惠券子编码
     */
    String COUPON_NUMBER ="couponNumber";

    /**
     * 优惠券编码
     */
    String COUPON_CODE ="couponCode";


    /**
     * 业务类型
     */
    String DEFAULT_BIZ_TYPE="bizType";

    String DEFAULT_CARD_ID ="cardId";
    /**
     * 订单号
     */
    String DEFAULT_ORDER_NO ="orderNo";
    /**
     * 返回客户端方法
     * @param response
     * @param obj
     * @param callback
     */
    void writeClientJson(HttpServletResponse response, Object obj, String callback) ;
    /**
     * 返回客户端方法
     * @param response
     * @param res
     * @param callback
     */
    void writeClientText(HttpServletResponse response, String res, String callback);
    /**
     * 通用验证方法
     * @param error
     * @param json
     * @return
     */
    ErrorBean validate(ErrorBean error, String json, Map<String, Map<String, String>> validateMap, HttpServletRequest request);

}
