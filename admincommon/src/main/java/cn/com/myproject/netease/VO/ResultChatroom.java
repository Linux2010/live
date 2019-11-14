package cn.com.myproject.netease.VO;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class ResultChatroom implements Serializable {
    private Integer code;
    private LinkedHashMap<String,String> chatroom;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public LinkedHashMap<String, String> getChatroom() {
        return chatroom;
    }

    public void setChatroom(LinkedHashMap<String, String> chatroom) {
        this.chatroom = chatroom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
