package cn.com.myproject.thirdlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyang-macbook on 2017/8/4.
 */

public interface IThirdLoginBean {
    /**
     * 获取web端快捷登录url
     * */
    String getWebUrl(HttpServletRequest request);
}
