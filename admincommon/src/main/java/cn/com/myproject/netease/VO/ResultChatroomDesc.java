package cn.com.myproject.netease.VO;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class ResultChatroomDesc implements Serializable {
    private Integer code;
    private LinkedHashMap<String,String> desc;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public LinkedHashMap<String, String> getDesc() {
        return desc;
    }

    public void setDesc(LinkedHashMap<String, String> desc) {
        this.desc = desc;
    }
}
