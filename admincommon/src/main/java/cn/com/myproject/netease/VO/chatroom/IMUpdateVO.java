package cn.com.myproject.netease.VO.chatroom;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/29.
 */
public class IMUpdateVO implements Serializable {
    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *聊天室名称，长度限制128个字符
     * */
    private String name;
    /**
     *公告，长度限制4096个字符
     * */
    private String announcement;
    /**
     *直播地址，长度限制1024个字符
     * */
    private String broadcasturl;
    /**
     *扩展字段，长度限制4096个字符
     * */
    private String ext = "true";
    /**
     *true或false,是否需要发送更新通知事件，默认true
     * */
    private String needNotify;
    /**
     * 通知事件扩展字段，长度限制2048
     * */
    private String notifyExt;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getBroadcasturl() {
        return broadcasturl;
    }

    public void setBroadcasturl(String broadcasturl) {
        this.broadcasturl = broadcasturl;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
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

        if(null != name){
            list = new LinkedList<>();
            list.add(name);
            map.put("name",list);
        }

        if(null != announcement){
            list = new LinkedList<>();
            list.add(announcement);
            map.put("announcement",list);
        }

        if(null != broadcasturl){
            list = new LinkedList<>();
            list.add(broadcasturl);
            map.put("broadcasturl",list);
        }

        if(null != ext){
            list = new LinkedList<>();
            list.add(ext);
            map.put("ext",list);
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
