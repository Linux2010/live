package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class ResultChannel implements Serializable {

    private Integer code;
    private String msg;
    private LinkedHashMap<String,String> ret;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LinkedHashMap<String, String> getRet() {
        return ret;
    }

    public void setRet(LinkedHashMap<String, String> ret) {
        this.ret = ret;
    }
}
