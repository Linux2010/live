package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class ResultChannelMap implements Serializable {

    private Integer code;
    private String msg;
    private LinkedHashMap<String,List<LinkedHashMap<String,String>>> ret;

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


    public LinkedHashMap<String, List<LinkedHashMap<String, String>>> getRet() {
        return ret;
    }

    public void setRet(LinkedHashMap<String, List<LinkedHashMap<String, String>>> ret) {
        this.ret = ret;
    }
}
