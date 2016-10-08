package com.hb.app.service.user;

import com.hb.app.dao.IUserDao;
import com.hb.app.entity.BaseEntity;
import com.hb.app.entity.UserEntity;
import com.hb.app.service.mob.SmsService;
import com.hb.app.utils.TokenUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ：
 */
@Service
public class UserService {

    @Autowired
    SmsService mSmsService;
    @Autowired
    IUserDao mUserDao;

    /**
     * 登录
     *
     * @param tel      手机号码
     * @param authCode 验证码
     * @return 结果
     */
    public BaseEntity login(String tel, String authCode) {
        if (TextUtils.isEmpty(tel) || TextUtils.isEmpty(authCode)) {
            return BaseEntity.createDefaultError();
        }
        BaseEntity entity = mSmsService.verifyAuthCode(tel, authCode);
        if (entity.getStatus() == 1) {
            String token = TokenUtils.makeToken();
            entity.setData(token);
            UserEntity userEntity = mUserDao.selectUserByMobile(tel);
            if (userEntity != null) {
                int uid = userEntity.getUid();
                String tokenByUid = mUserDao.selectTokenByUid(uid);
                if (!TextUtils.isEmpty(tokenByUid)) {
                    mUserDao.updateToken(uid, token);
                } else {
                    mUserDao.insertToken(uid, token);
                }
            } else {
                mUserDao.addUser(tel);
                UserEntity userByMobile = mUserDao.selectUserByMobile(tel);
                if (userByMobile != null) {
                    mUserDao.insertToken(userByMobile.getUid(), token);
                }
            }
        }
        return entity;
    }

    /**
     * 获取验证码
     *
     * @param tel 手机号码
     * @return 结果
     */
    public BaseEntity getAuthCode(String tel) {
        return mSmsService.getAuthCode(tel);
    }

    /**
     * 登出
     *
     * @param token token
     * @return 结果
     */
    public BaseEntity logout(String token) {
        if (TextUtils.isEmpty(token)) {
            return BaseEntity.createDefaultError();
        }
        int i = mUserDao.deleteToken(token);
        if (i > 0) {
            return BaseEntity.createDefaultSucceed();
        } else {
            return BaseEntity.createServerError();
        }
    }
}
