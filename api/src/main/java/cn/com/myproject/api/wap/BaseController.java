package cn.com.myproject.api.wap;


import cn.com.myproject.api.config.Constants;
import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.user.controller.RegisterController;
import cn.com.myproject.api.util.CookieTool;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyang-macbook on 2017/10/27.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private HttpServletRequest request;

    @Autowired
    protected IRedisService redisService;

    protected User getCurrUser(){
        Cookie cookie = CookieTool.getCookie(getRequest(), Constants.SESSION_ID);
        if (cookie != null) {
            try {
                User user = (User) JSON.parseObject(redisService.getValue(cookie.getValue()),User.class);
                return user;
            } catch (Exception e) {
                logger.error("缓存中没有user_id=" + cookie.getValue() + "的用户" + e.getMessage(), e);
            }
        }
        return null;
    }



    public String getSessionId() {
        Cookie cookie = CookieTool.getCookie(getRequest(), Constants.SESSION_ID);
        if (cookie != null) {
            return cookie.getValue();
        }
        return "";
    }


    public HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        this.request = ((ServletRequestAttributes) ra).getRequest();
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
