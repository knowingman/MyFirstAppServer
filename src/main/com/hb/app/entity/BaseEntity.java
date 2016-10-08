/*
 * Copyright (c) 2016 HENGDA, Inc.
 * All Rights Reserved. 
 * HENGDA Proprietary and Confidential.
 * Author:渚花
 * Time锛�016/7/13
 * Revision History.
 */

package com.hb.app.entity;

import com.hb.app.constants.Constant;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 基本的实体类
 */
//请求返回结果类，如果有需要传递的数据将数据 放入Data中
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseEntity<T> {

    private int status = Constant.REQUEST_OK;
    private String msg = Constant.REQUEST_OK_TEXT;

    private T data;

    public BaseEntity() {
    }

    public BaseEntity(T data) {
        this.data = data;
    }

    public BaseEntity(int code, String msg) {
        this.status = code;
        this.msg = msg;
    }

    public BaseEntity(int code, String msg, T data) {
        this.status = code;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static BaseEntity createDefaultError() {
        return new BaseEntity(Constant.REQUEST_PARAMETER_ERROR, Constant.REQUEST_PARAMETER_ERROR_TEXT);
    }


    public static BaseEntity createServerError() {
        return new BaseEntity(Constant.SERVER_ERROR, Constant.SERVER_ERROR_TEXT);
    }

    public static BaseEntity createOutOfDateError() {
        return new BaseEntity(Constant.SERVER_ERROR, Constant.SERVER_ERROR_TEXT);
    }
    public static BaseEntity createDefaultSucceed() {
        return new BaseEntity();
    }
}
