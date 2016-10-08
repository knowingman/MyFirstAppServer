package com.hb.app.constants;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppClient
 * 功能   ： 常量接口类
 */
public interface Constant {

    public static final String SHARE_API = "https://webapi.sms.mob.com/sms/verify";
    //share sdk的app key
    public static final String SHARE_SDK_APP_KEY = "1511520b0f128";
    //share sdk的app secret
    public static final String SHARE_SDK_APP_SECRET = "4c31771d804e48bb51dc5181bb040aec";

    public static final int REQUEST_OK = 1;
    public static final String REQUEST_OK_TEXT = "请求成功";

    public static final int REQUEST_PARAMETER_ERROR = 403;
    public static final int SERVER_ERROR = 404;
    public static final int OUT_OF_DATE_ERROR = 405;
    public static final String REQUEST_PARAMETER_ERROR_TEXT = "请求参数错误";
    public static final String SERVER_ERROR_TEXT = "服务器错误";
    public static final String OUT_OF_DATE_TEXT = "登录过期";
}
