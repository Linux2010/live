package cn.com.myproject.api.redis;

import java.util.Map;
import java.util.Set;

/**
 * Created by liyang-macbook on 2017/6/22.
 */
public interface IRedisService {
    Boolean setValue(String key, String value, long timeOut);

    String getValue(String key);

    Boolean containKey(String key);

    void setHashValue(String key, String hashKey, Object value);

    void setHashValue(String key, String hashKey,Object value, long timeOut);

    void setHashAll(String key, Map<String, Object> map);

    Object getHashValue(String key, String hashKey);

    Set<Object> getKey(String key);

    boolean haskey(String key);

    void delete(String key);

}
