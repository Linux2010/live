package cn.com.myproject.netease.VO.chatroom;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/30.
 */
public class IMMuteRoomVO implements Serializable {

    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *操作者accid，必须是管理员或创建者
     * */
    private String operator;
    /**
     *true或false
     * */
    private String mute;
    /**
     *true或false，默认true
     * */
    private String needNotify;
    /**
     *通知扩展字段
     * */
    private String notifyExt;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMute() {
        return mute;
    }

    public void setMute(String mute) {
        this.mute = mute;
    }

    public String getNeedNotify() {
        return needNotify;
    }

    public void setNeedNotify(String needNotify) {
        this.needNotify = needNotify;
    }

    public String getNotifyExt() {
        return notifyExt;
    }

    public void setNotifyExt(String notifyExt) {
        this.notifyExt = notifyExt;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != operator){
            list = new LinkedList<>();
            list.add(operator);
            map.put("operator",list);
        }

        if(null != mute){
            list = new LinkedList<>();
            list.add(mute);
            map.put("mute",list);
        }

        if(null != needNotify){
            list = new LinkedList<>();
            list.add(needNotify);
            map.put("needNotify",list);
        }

        if(null != notifyExt){
            list = new LinkedList<>();
            list.add(notifyExt);
            map.put("notifyExt",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
