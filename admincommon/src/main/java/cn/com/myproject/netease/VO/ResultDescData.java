package cn.com.myproject.netease.VO;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class ResultDescData implements Serializable {
    private Integer code;
    private LinkedHashMap<String,List<LinkedHashMap<String,String>>> desc;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LinkedHashMap<String, List<LinkedHashMap<String, String>>> getDesc() {
        return desc;
    }

    public void setDesc(LinkedHashMap<String, List<LinkedHashMap<String, String>>> desc) {
        this.desc = desc;
    }
}
