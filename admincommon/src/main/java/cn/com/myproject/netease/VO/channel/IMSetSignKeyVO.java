package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/4.
 */
public class IMSetSignKeyVO implements Serializable {

    /**
     *加签秘钥
     *必填：是
     * */
    private String signKey;

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
