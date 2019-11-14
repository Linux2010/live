package cn.com.myproject.api.interceptor;

import cn.com.myproject.api.config.Constants;
import cn.com.myproject.api.redis.impl.RedisService;
import cn.com.myproject.api.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class WapInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = CookieUtil.getCookie(Constants.SESSION_ID,request);
        if(StringUtils.isBlank(sessionId)) {
            String XRequested =request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(XRequested)) {
                response.getWriter().write("{\"result\":1,\"message\":\"IsAjax\"}");
            }else {
                request.getRequestDispatcher("/wap/wx/login").forward(
                        request, response);
            }
            return false;
        }
        Boolean b = redisService.containKey(sessionId);
        if(!b){
            String XRequested =request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(XRequested)) {
                response.getWriter().write("{\"result\":1,\"message\":\"IsAjax\"}");
            }else {
                request.getRequestDispatcher("/wap/wx/login").forward(
                        request, response);
            }
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}
