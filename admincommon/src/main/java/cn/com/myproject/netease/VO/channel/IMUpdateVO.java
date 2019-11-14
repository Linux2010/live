package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMUpdateVO implements Serializable {

    /**
     *频道名（最大长度64个字符）
     * 必填：是
     * */
    private String name;
    /**
     *频道ID，32位字符串
     * 必填：是
     * */
    private String cid;
    /**
     *频道类型 ( 0 : rtmp)
     * 必填：是
     * */
    private Integer type = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
