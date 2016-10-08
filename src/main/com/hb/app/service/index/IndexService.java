package com.hb.app.service.index;

import com.hb.app.dao.IUserDao;
import com.hb.app.entity.BaseEntity;
import com.hb.app.entity.UserEntity;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/6
 * 项目   :  FirstAppServer
 * 功能   ：
 */
@Service
public class IndexService {

    @Autowired
    IUserDao mUserDao;

    public BaseEntity getIndex(String token) {
        if (TextUtils.isEmpty(token)) {
            return BaseEntity.createDefaultError();
        }
        int uid = mUserDao.selectUidByToken(token);
        BaseEntity baseEntity = new BaseEntity();
        if (uid > 0) {
            UserEntity userEntity = mUserDao.selectUserByUid(uid);
            baseEntity.setData(userEntity);
            return baseEntity;
        } else {
            return BaseEntity.createOutOfDateError();
        }
    }
}
