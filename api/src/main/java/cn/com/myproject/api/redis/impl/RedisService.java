package cn.com.myproject.api.redis.impl;


import cn.com.myproject.api.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyang-macbook on 2017/6/22.
 */
@Service("redisService")
public class RedisService implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Boolean setValue(String key, String value,long timeOut) {
        redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.MINUTES);
        return redisTemplate.hasKey(key);
    }

    @Override
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean containKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void setHashValue(String key, String hashKey, Object value){
        redisTemplate.opsForHash().put(key,hashKey,value);
    }

    @Override
    public void setHashValue(String key, String hashKey, Object value, long timeOut) {
        redisTemplate.opsForHash().put(key,hashKey,value);
        redisTemplate.boundHashOps(key).expire(timeOut,TimeUnit.MINUTES);
    }

    @Override
    public void setHashAll(String key,Map<String,Object> map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    @Override
    public Object getHashValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    @Override
    public Set<Object> getKey(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public boolean haskey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
