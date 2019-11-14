package cn.com.myproject.netease.VO;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class ResultUinfos implements Serializable {
    private Integer code;
    private List<LinkedHashMap<String,String>> uinfos;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public List<LinkedHashMap<String, String>> getUinfos() {
        return uinfos;
    }

    public void setUinfos(List<LinkedHashMap<String, String>> uinfos) {
        this.uinfos = uinfos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
