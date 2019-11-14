package cn.com.myproject.netease.VO;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class ResultData implements Serializable {
    private Integer code;
    private List<LinkedHashMap<String,String>> data;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<LinkedHashMap<String, String>> getData() {
        return data;
    }

    public void setData(List<LinkedHashMap<String, String>> data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
