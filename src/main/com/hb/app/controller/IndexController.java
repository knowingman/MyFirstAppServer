package com.hb.app.controller;

import com.hb.app.entity.BaseEntity;
import com.hb.app.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/6
 * 项目   :  FirstAppServer
 * 功能   ：
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    IndexService mService;

    @ResponseBody
    @RequestMapping("/getIndex")
    public BaseEntity getIndex(String token) {
        return mService.getIndex(token);
    }
}
