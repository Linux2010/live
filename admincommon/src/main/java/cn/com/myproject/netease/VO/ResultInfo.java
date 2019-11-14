package cn.com.myproject.netease.VO;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class ResultInfo implements Serializable {
    private Integer code;
    private LinkedHashMap<String,String> info;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LinkedHashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(LinkedHashMap<String, String> info) {
        this.info = info;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
