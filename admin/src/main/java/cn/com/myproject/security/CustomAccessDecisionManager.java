package cn.com.myproject.security;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by liyang-macbook on 2017/6/21.
 *
 */

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    //检查用户是否够权限访问资源
    //参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
    //参数object是url
    //参数configAttributes所需的权限
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if( configAttributes == null ) {
            return ;
        }
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while( ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig)ca).getAttribute();
             //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for( GrantedAuthority ga: authentication.getAuthorities()){
                if(needRole.trim().equals(ga.getAuthority().trim())){
                    return;
                }

            }

        }
        throw new AccessDeniedException(messages.getMessage(
                "AbstractAccessDecisionManager.accessDenied", "权限不足"));
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
