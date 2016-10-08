package com.hb.app.dao;

import com.hb.app.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/4
 * 项目   :  FirstAppServer
 * 功能   ：
 */
@Repository
public interface IUserDao {
    UserEntity selectUserByMobile(String mobile);

    UserEntity selectUserByQQ(String qqId);

    UserEntity selectUserByWeChat(String weChatId);

    UserEntity selectUserByUid(int uid);

    int addUser(String mobile);

    String selectTokenByUid(int uid);

    int selectUidByToken(String token);

    int insertToken(int uid, String token);

    int updateToken(int uid, String token);

    int deleteToken(String token);
}
