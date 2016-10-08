package com.hb.app.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.hb.app.entity.UserEntity;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/8
 * 项目   :  FirstAppServer
 * 功能   ： Redis数据访问对象
 */
@Repository
public class RedisDao {

    private JedisPool mJedisPool;

    private RedisDao(String host, int port) {
        mJedisPool = new JedisPool(host, port);
    }

    private RuntimeSchema<UserEntity> schema = RuntimeSchema.createFrom(UserEntity.class);

    public UserEntity getUserByToken(String token) {
        Jedis jedis = mJedisPool.getResource();
        byte[] bytes = jedis.get(token.getBytes());
        if (bytes != null) {

            UserEntity userEntity = schema.newMessage();
            ProtobufIOUtil.mergeFrom(bytes, userEntity, schema);
            return userEntity;
        }
        return null;
    }

    public String setUserByToken(String token, UserEntity userEntity) {
        Jedis jedis = mJedisPool.getResource();
        byte[] byteArray = ProtobufIOUtil.toByteArray(userEntity, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        return jedis.setex(token.getBytes(), 68 * 60, byteArray);
    }
}
