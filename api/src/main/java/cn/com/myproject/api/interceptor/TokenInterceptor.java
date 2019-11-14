package cn.com.myproject.api.interceptor;

import cn.com.myproject.api.config.Constants;
import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther CQC
 * @create 2017.8.28
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = getToken(request);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        if(StringUtils.isEmpty(token)){
            String info = "{\"result\":1,\"message\":\"tokenIsNull\"}";
            response.getWriter().write(info);
            response.getWriter().close();
            return false;
        }

        Boolean b = redisService.containKey(token);
        if(!b){
            String info = "{\"result\":1,\"message\":\"tokenTimeOut\"}";
            response.getWriter().write(info);
            response.getWriter().close();
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    private String getToken(HttpServletRequest request){

        String token = request.getHeader(Constants.token);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(Constants.token);
        }
        return token;
    }

}
