package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by liyang-macbook on 2017/7/17.
 */
public class ResultVod implements Serializable {



    private Integer code;

    private LinkedHashMap<String,Object> ret;

    private String msg;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LinkedHashMap<String, Object> getRet() {
        return ret;
    }

    public void setRet(LinkedHashMap<String, Object> ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
