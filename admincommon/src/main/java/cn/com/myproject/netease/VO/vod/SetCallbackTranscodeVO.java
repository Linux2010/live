package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class SetCallbackTranscodeVO implements Serializable {
    /**
     * 转码成功后回调客户端的URL地址
     * 必填：否
     * */
    private String callbackUrl;
    /**
     * 转码成功后回调客户端的签名字符串，要求是由字母和数字组成且不超过32位
     * 必填：否
     * */
    private String signKey;

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
