package cn.com.myproject.api.wap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@Component
@ConfigurationProperties(prefix = "wxJspPay")
public class WXJspPayProp {

    private String secret;
    private String businessSecretKey;
    private String notifyUrl; //微信公众号通知回调Url

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBusinessSecretKey() {
        return businessSecretKey;
    }

    public void setBusinessSecretKey(String businessSecretKey) {
        this.businessSecretKey = businessSecretKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
