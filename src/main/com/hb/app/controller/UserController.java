package com.hb.app.controller;

import com.hb.app.constants.ParameterKey;
import com.hb.app.entity.BaseEntity;
import com.hb.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ：
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService mUserService;

    @ResponseBody
    @RequestMapping("/login")
    public BaseEntity login(
            @RequestParam(value = ParameterKey.MOBILE) String tel,
            @RequestParam(value = ParameterKey.CODE) String authCode) {
        return mUserService.login(tel, authCode);
    }

    @ResponseBody
    @RequestMapping("/getAuthCode")
    public BaseEntity getAuthCode(@RequestParam(value = ParameterKey.MOBILE) String tel) {
        return mUserService.getAuthCode(tel);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public BaseEntity logout(@RequestParam(value = ParameterKey.TOKEN) String token) {
        return mUserService.logout(token);
    }
}
