package com.foryou.tax.api.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public enum StatusCode {
    @CodeAnnot("正确处理") OK(1),
    @CodeAnnot("系统错误") SystemError(9999),
    @CodeAnnot("系统繁忙,请稍后再试") SystemBusy(9998),
    @CodeAnnot("MD5验证失败") Md5ValidEroor(9997),
    @CodeAnnot("应用程序错误") AppError(2),
    @CodeAnnot("数据库错误") DBException(3),

    @CodeAnnot("参数错误") RequestParamsNotValid(9001),
    @CodeAnnot("邮箱格式不正确") EmailValid(10001),
    @CodeAnnot("用户名长度必须为5-13") NickNameLengthValid(10002),
    @CodeAnnot("接口错误") ItfcError(999),
    @CodeAnnot("已存在") Exists(1111),
    @CodeAnnot("记录不存在") NotExists(0000),
    @CodeAnnot("申请成功，请等待平台审核!") WaitApply(11110),
    @CodeAnnot("一审通过") FirstAdopt(11111),
    @CodeAnnot("二审通过") Adopt(11112),
    @CodeAnnot("已提交银行处理") Handling(11131),
    @CodeAnnot("暂时不支持此银行") BankErr(11141),
    @CodeAnnot("注册失败") RegisterError(11142),
    @CodeAnnot("登陆失败") LoginError(11143),
    @CodeAnnot("客户状态失效") clientStatusInActive(800001),
    @CodeAnnot("文件找不到") UploadFileNotFound(9002),
    @CodeAnnot("该用户已存在") UserNameHasExits(10001),
    @CodeAnnot("邮箱格式错误") EmailFormatError(10002),
    @CodeAnnot("用户名为空") UserNameIsEmpty(10003),
    @CodeAnnot("用户名和邮箱绑定关系不存在,或者存在多个绑定关系") EmailAndUserNameBindError(10004),
    @CodeAnnot("邮箱地址未注册") EmailNotExsits(10005),
    @CodeAnnot("用户名或密码错误") UserOrPwdError(10006),
    @CodeAnnot("用户账号不唯一") UserNotUnique(10007),
    @CodeAnnot("用户账号不存在") UserAccountNotExit(10008),
    @CodeAnnot("原密码输入错误") OriginalPasswordError(10009),
    @CodeAnnot("会员信息不存在") MemberNotExitsError(10010),
    @CodeAnnot("该邮箱账号已经存在") UserEmailHasExits(10011),
    @CodeAnnot("账号未激活") UserNotActivite(10012),
    @CodeAnnot("账号激活码不存在") AccountCodeNotExits(10013),
    @CodeAnnot("账号激活码已过期") AccountCodeExpire (10014),
    @CodeAnnot("用户证件编号为空") UserIdCodeIsEmpty(10015),
    @CodeAnnot("用户手机号为空") UserPhoneIsEmpty(10016),
    @CodeAnnot("账户余额不足") NotSufficientFunds(10020),
    @CodeAnnot("登录次数过多，账户已锁定") LockUser(10021),
    @CodeAnnot("用户类型") UserTypeIsEmpty(10022),
    @CodeAnnot("当前版本过低，请您升级至最新版本") ANDROID_UPDATE(10999),
    @CodeAnnot("账号状态异常") UserStatusError(10024),
    @CodeAnnot("设置默认异常") SetUnionCardDefError(10025),
    @CodeAnnot("身份证验证失败") IdCardVerifyFail(10026),
    @CodeAnnot("手机号认证失败") PhoneVerifyFail(10027),
    @CodeAnnot("支付密码已存在，不能设置支付密码") pwdIsExists(10028),
    @CodeAnnot("两次密码输入不匹配") pwdNotMatch(10029),
    @CodeAnnot("修改密码失败") pwdUpdateFail(10030),
    @CodeAnnot("重置支付密码失败") resetPayPasswdFail(10032),
    @CodeAnnot("证件号码为空") idNoIsEmpty(10031),
    @CodeAnnot("手机号格式错误") PhoneModelError(10037),
    @CodeAnnot("密码错误或验证码超时!") PasswordThereError(10038),
    @CodeAnnot("客户已冻结") UserIsFreezed(10038),
    @CodeAnnot("解除默认异常") SetUnionCardNoDefError(10039),
    @CodeAnnot("手机号不是11位") PhoneaIsNotEleven(10040),
    @CodeAnnot("用户已签到") SignExist(10041),
    @CodeAnnot("签到失败") SignError(10042),
    @CodeAnnot("未实名认证") NoRealName(10044),
    @CodeAnnot("客户已注销") UserIsCloseAccount(10045),
    @CodeAnnot("密码为空") PasswordIsEmpty(30003),
    @CodeAnnot("验证码为空") VerifyCodeIsEmpty(30005),
    @CodeAnnot("Token为空") TokenIsEmpty(30007),
    @CodeAnnot("没有查到数据") DataIsEmpty(30008),
    @CodeAnnot("期限不能为空") TimeLimitIsEmpty(30009),
    @CodeAnnot("验证码发送失败") VerifyCodeFail(30010),
    @CodeAnnot("请输入反馈内容") AdviceIsEmpty(30011),
    @CodeAnnot("用户不存在") UserNotExit(300019),
    @CodeAnnot("用户未登录")UserNotLogin(300026),
    @CodeAnnot("用户注销异常")UserLogOutError(300027),
    @CodeAnnot("新密码为空")NewPasswordIsEmpty(300028),
    @CodeAnnot("修改数据异常")ModifyDataError(300029),
    @CodeAnnot("密码未设置") PasswordNoSet(300051),
    @CodeAnnot("客户号为空") ClientCodeIsEmpty(60101),
    @CodeAnnot("客户姓名为空") ClientNameIsEmpty(60102),
    @CodeAnnot("关联卡号为空") CardNoIsEmpty(60201),
    @CodeAnnot("参数错误") DepositGoldIsEmpty(70000),
    @CodeAnnot("输入错误") InvalidInput(70001),
    @CodeAnnot("重置密码成功") pwdResetSuccess(1000009),
    @CodeAnnot("重置密码失败") pwdResetFail(1000010),
    @CodeAnnot("当前时段不支持此业务办理") notSupportBiz(1000011),
    @CodeAnnot("ip地址有误")IPError(1000012),
    @CodeAnnot("获取ip地址异常")GetIPError(1000013),
    @CodeAnnot("登录日志添加失败")LoginLogInsertError(1000014),
    @CodeAnnot("成功") accountSumSuccess(1000015),
    @CodeAnnot("失败") accountSumFail(1000016),
    ;

    private final int value;
    private static final Map<String, String> hMap = new HashMap<String, String>();

    /**
     * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
     */
    StatusCode(int value) {
        this.value = value;
    }

    static {
        Field[] fields = StatusCode.class.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CodeAnnot.class)) {
                hMap.put(field.getName(), field.getAnnotation(CodeAnnot.class).value());
            }
        }
    }

    public int getValue() {
        return value;
    }

    public String GetDescription() {
        return hMap.get(this.toString());
    }
}
