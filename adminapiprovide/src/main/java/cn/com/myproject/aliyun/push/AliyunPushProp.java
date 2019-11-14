package cn.com.myproject.aliyun.push;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2017/7/24.
 */
@Component
@ConfigurationProperties(prefix = "aliyun.push")
public class AliyunPushProp {

    private Long appKey;
    private String appKeyId;
    private String appSecret;


    public Long getAppKey() {
        return appKey;
    }

    public void setAppKey(Long appKey) {
        this.appKey = appKey;
    }

    public String getAppKeyId() {
        return appKeyId;
    }

    public void setAppKeyId(String appKeyId) {
        this.appKeyId = appKeyId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
