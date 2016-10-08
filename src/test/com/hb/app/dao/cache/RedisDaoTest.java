package com.hb.app.dao.cache;

import com.hb.app.dao.IUserDao;
import com.hb.app.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/8
 * 项目   :  FirstAppServer
 * 功能   ：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-config.xml"})
public class RedisDaoTest {

    @Autowired
    private RedisDao mRedisDao;

    @Autowired
    private IUserDao mUserDao;

    private int uid = 1;

    @Test
    public void testUserEntity() {
        UserEntity userByToken = mRedisDao.getUserByToken(uid + "");
        if (userByToken == null) {
            UserEntity userEntity = mUserDao.selectUserByUid(uid);
            if (userEntity != null) {
                String s = mRedisDao.setUserByToken(uid + "", userEntity);
                System.out.println(s);
                UserEntity entity = mRedisDao.getUserByToken(uid + "");
                System.out.println(entity);
            }
        }
    }
}