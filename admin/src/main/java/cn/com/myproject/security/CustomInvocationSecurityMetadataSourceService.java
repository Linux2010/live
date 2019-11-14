package cn.com.myproject.security;


import cn.com.myproject.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    @Autowired//权限数据来自redis
    private IRedisService redisService;

    @Override//参数是要访问的url，返回这个url对于的所有权限（或角色）
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        Set<Object> set = redisService.getKey(WebSecurityConfig.URL_SECURITY_KEY);//加载所有url和权限（或角色）的对应关系

        for(Object key : set) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(key.toString());
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return (Collection<ConfigAttribute>) redisService.getHashValue(WebSecurityConfig.URL_SECURITY_KEY,key.toString());
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
