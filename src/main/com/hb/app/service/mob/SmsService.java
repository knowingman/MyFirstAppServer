package com.hb.app.service.mob;

import com.google.gson.Gson;
import com.hb.app.constants.ParameterKey;
import com.hb.app.entity.BaseEntity;
import com.hb.app.utils.http.HttpResponse;
import com.hb.app.utils.http.HttpUtils;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ： 短信验证服务
 */
@Service
public class SmsService {

    /**
     * 请求验证码
     *
     * @param tel 手机号码
     * @return 返回结果
     */
    public BaseEntity getAuthCode(String tel) {
        if (TextUtils.isEmpty(tel)) {
            return BaseEntity.createDefaultError();
        } else {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(ParameterKey.MOBILE, tel);
            HttpResponse response = HttpUtils.get().sendPost(ParameterKey.SEND_SMS, params);
            if (response.code == HttpURLConnection.HTTP_OK && !TextUtils.isEmpty(response.content)) {
                BaseEntity baseEntity = new Gson().fromJson(response.content, BaseEntity.class);
                if (baseEntity != null) {
                    return baseEntity;
                } else {
                    return new BaseEntity<String>("验证码请求失败");
                }
            } else {
                return BaseEntity.createServerError();
            }
        }
    }

    /**
     * 验证手机短信验证码是否正确
     *
     * @param tel      手机号码
     * @param authCode 验证码
     * @retun 结果
     */
    public BaseEntity verifyAuthCode(String tel, String authCode) {
        System.out.println("手机号码-->" + tel + "--" + authCode);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(ParameterKey.MOBILE, tel);
        params.put(ParameterKey.CODE, authCode);
        HttpResponse response = HttpUtils.get().sendPost(ParameterKey.LOGIN, params);
        if (response.code == HttpURLConnection.HTTP_OK && !TextUtils.isEmpty(response.content)) {
            BaseEntity baseEntity = new Gson().fromJson(response.content, BaseEntity.class);
            if (baseEntity != null) {
                return baseEntity;
            } else {
                return new BaseEntity<String>("验证失败");
            }
        } else {
            return BaseEntity.createServerError();
        }
    }
}
