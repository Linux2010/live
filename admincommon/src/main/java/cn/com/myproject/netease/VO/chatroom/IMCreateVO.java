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
public class IMCreateVO implements Serializable {

    /***
     * 聊天室属主的账号accid
     */
    private String creator;

    /***
     * 聊天室名称，长度限制128个字符
     */
    private String name;
    /***
     * 公告，长度限制4096个字符
     */
    private String announcement;
    /**
     * 直播地址，长度限制1024个字符
     * */
    private String broadcasturl;
    /**
     *扩展字段，最长4096字符
     * */
    private String ext;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != creator){
            list = new LinkedList<>();
            list.add(creator);
            map.put("creator",list);
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

        return CollectionUtils.toMultiValueMap(map);
    }
}
