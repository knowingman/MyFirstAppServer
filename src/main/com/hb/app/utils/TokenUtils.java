package com.hb.app.utils;

import sun.misc.BASE64Encoder;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ：
 */
public class TokenUtils {

    public static String makeToken() {
        String l = System.currentTimeMillis() + "";
        String i = (int) (Math.random() * 1000) + "";
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode((l + i).getBytes());
    }
}
