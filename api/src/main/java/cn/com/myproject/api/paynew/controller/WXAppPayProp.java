package cn.com.myproject.api.paynew.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@Component
@ConfigurationProperties(prefix = "wxAppPay")
public class WXAppPayProp {

    private String secret;
    private String businessSecretKey;
    private String notifyUrl;

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
