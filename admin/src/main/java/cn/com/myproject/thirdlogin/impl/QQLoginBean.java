package cn.com.myproject.thirdlogin.impl;

import cn.com.myproject.thirdlogin.IThirdLoginBean;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyang-macbook on 2017/8/4.
 */
@Service("qqLoginBean")
public class QQLoginBean implements IThirdLoginBean {
    @Override
    public String getWebUrl(HttpServletRequest request) {
        try {
            return new Oauth().getAuthorizeURL(request);
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "";
    }
}
