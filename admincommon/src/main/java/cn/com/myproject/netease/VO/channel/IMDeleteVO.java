package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMDeleteVO implements Serializable {
    /**
     *频道ID，32位字符串
     * 必填：是
     * */
    private String cid;


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
