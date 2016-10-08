package com.hb.app.constants;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ： 请求参数的集合
 */
public interface ParameterKey {

    public static final String DOMAIN = "http://ddhy.fmm188.com";
    //发短信接口
    public static final String SEND_SMS = DOMAIN + "?c=user&m=send_sms";

    //参数mobile，value=String,手机号,参数code,value=int,验证码；
    public static final String LOGIN = DOMAIN + "?c=user&m=user_login";

    public static final String MOBILE = "mobile";
    public static final String CODE = "code";
    public static final String TOKEN = "token";
}
