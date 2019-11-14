package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMCreateVO implements Serializable {
    /**
     *频道名称（最大长度64个字符，只支持中文、字母、数字和下划线）
     * 必填:是
     * */
    private String name;
    /**
     *
     *频道类型（0:rtmp）
     * 必填:是
     * */
    private Integer type = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
