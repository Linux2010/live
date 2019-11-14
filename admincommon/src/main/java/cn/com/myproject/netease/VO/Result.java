package cn.com.myproject.netease.VO;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class Result implements Serializable {
    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
