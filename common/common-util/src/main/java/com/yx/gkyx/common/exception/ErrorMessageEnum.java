package com.yx.gkyx.common.exception;


import com.yx.gkyx.common.vo.IErrorCode;

/**
 * 返回前端错误信息提示语及编码的枚举
 * 错误编码格式
 * 通用类型错误编码：从1001开始,最多9999，添加时在已有错误类型后加1，如1001,1002,1003
 * 模块通用类型错误编码：模块（从1开始，添加时在已有模块类型后加1）+定长4位的错误编号（从1开始，添加时在已有错误类型后加1），如10001,10002
 * 接口类型错误：模块（从1开始，添加时在已有模块类型后加1）+定长3位的接口编号（从1开始，添加时在已有接口类型后加1）+定长3位的错误编号（从1开始，添加时在已有错误类型后加1），如：1001001
 */
public enum ErrorMessageEnum implements IErrorCode {
    //兼容旧错误类型代码
    //服务器异常错误，信息模板无要求
    SERVER_EXCEPTION(99, "%s", "服务器异常"),
    //未登录异常，信息模板无要求
    NOT_LOGIN(100, "%s", "您的操作异常，请先进行登录。"),
    //公共异常
    //参数异常错误，信息模板无要求
    PARAMETER_ERROR(1001, "%s", "参数异常"),

    //非正常操作
    INFO_NOT_COMMON_OPERATE(1002, "非正常操作%s", "非正常操作"),

    //请求来源异常
    API_TYPE_EXCEPTION(1003, "请求来源异常%s", "请求来源异常"),
    //权限异常，无权做该请求时提示此错误
    PERMISSION_EXCEPTION(1004, "%s您暂时没有操作此项业务的权限！", "您暂时没有操作此项业务的权限！"),


    //验证码模块异常
    //短信验证码未过期异常，信息模板无要求
    SECURITY_CODE_SENT(10001, "%s", "短信验证码未过期"),
    //验证码填写错误时，信息模板无要求
    SECURITY_CODE_VERIFICATION_FAILED(10002, "%s", "验证码错误"),
    //短信验证码发送超过次数限制，信息模板无要求
    SECURITY_CODE_EXCEED_THE_LIMIT(10003, "%s", "获取验证码次数过多，当天内无法再获取"),
    //验证码填写错误时，信息模板无要求
    SECURITY_CODE_VERIFICATION_INVALID(10004, "%s", "验证码失效，请重新获取"),
    //短信验证码验证超过次数限制，信息模板无要求
    SECURITY_CODE_VERIFICATION_EXCEED_THE_LIMIT(10005, "%s", "您已验证失败5次，请重新获取验证码"),
    //修改手机号：短信手机号异常，不能发信息，信息模板无要求
    SECURITY_CODE_MOBILE_ERROR_MODIFY_MOBILE(10006, "%s", "无法完成本次操作，此手机号已绑定到其他账号"),
    //重置密码：短信手机号异常，不能发信息，信息模板无要求
    SECURITY_CODE_MOBILE_ERROR_RESET(10007, "%s手机号暂未注册", "手机号暂未注册"),
    //登录：短信手机号异常，不能发信息，信息模板无要求
    SECURITY_CODE_MOBILE_ERROR_LOGIN(10008, "%s手机号暂未注册，可采用其他方式登录", "手机号暂未注册，可采用其他方式登录"),
    //验证码模块异常


    //新版用户中心错误---开始
    USER_NOT_EXIST(21001, "%s填写的%s不在系统内%s", "您填写的账号不在系统内"),
    USER_KEY_DUPLICATE(21002, "%s", "您当前的账号重复，请切换其他账号或者联系客服人员"),
    //填写的信息和库中已有的信息重复
    USER_KEY_DUPLICATE_FOR_UPDATE_OR_REGISTER(21003, "提交失败，该%s已有账号%s", "您填写的信息已存在，请重新填写"),
    //填写的信息和自己当前填写的信息字段间重复
    USER_KEY_DUPLICATE_FOR_UPDATE_OR_REGISTER_INPUT(21004, "\"%s\"和\"%s\"不能重复，请您重新填写", "您填写的\"用户名\"，\"身份证号\"，\"手机号\"，\"邮箱\"信息间不可一致，请重新填写"),
    //新版用户中心错误---结束
    ;

    ErrorMessageEnum(long code, String message, String defaultMessage) {
        this.code = code;
        this.message = message;
        this.defaultMessage = defaultMessage;
    }

    //错误编码
    private final long code;
    //该错误信息个性化提示需要遵循的模板
    private final String message;
    //该错误信息默认的提示语
    private final String defaultMessage;

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
