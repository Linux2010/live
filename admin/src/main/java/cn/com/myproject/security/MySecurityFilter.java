package cn.com.myproject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * url filter，主要为自定义url过滤
 * Created by liyang-macbook on 2017/6/22.
 * 访问拦截。
 */
@Component("mySecurityFilter")//自定义登录拦截器。
public class MySecurityFilter   extends AbstractSecurityInterceptor implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MySecurityFilter.class);

    @Autowired//配置文件注入  private FilterInvocationSecurityMetadataSource securityMetadataSource; 增强方法
    private CustomInvocationSecurityMetadataSourceService  mySecurityMetadataSource;

    @Resource
    private CustomAccessDecisionManager customAccessDecisionManager;

//    @Autowired
//    private AuthenticationManager authenticationManager;


    @PostConstruct
    public void init(){
   //     super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(customAccessDecisionManager);
       // super.setRejectPublicInvocations(true);
    }
    //登陆后，每次访问资源都通过这个拦截器拦截
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation( request, response, chain );
        invoke(fi);

    }


    public Class<? extends Object> getSecureObjectClass(){
        return FilterInvocation.class;
    }


    public void invoke( FilterInvocation fi ) throws IOException, ServletException{
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        if(logger.isDebugEnabled()){
            logger.debug("filter..........................");
        }
        InterceptorStatusToken token = super.beforeInvocation(fi);//最重要的是beforeInvocation这个方法，它首先会调用MyInvocationSecurityMetadataSource类的getAttributes方法获取被拦截url所需的权限，在调用MyAccessDecisionManager类decide方法判断用户是否够权限。弄完这一切就会执行下一个拦截器。
        try{
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }

    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource(){
        if(logger.isDebugEnabled()){
            logger.debug("obtainSecurityMetadataSource");
        }
        return this.mySecurityMetadataSource;
    }

    @Override
    public void destroy(){
        if(logger.isDebugEnabled()){
            logger.debug("filter===========================end");
        }
    }

    @Override
    public void init( FilterConfig filterconfig ) throws ServletException{

        if(logger.isDebugEnabled()){
            logger.debug("filter===========================");
        }
    }

}
