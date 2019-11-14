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
public class IMUpdateMyRoomRoleVO implements Serializable {

    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *需要变更角色信息的accid
     * */
    private String accid;
    /**
     *变更的信息是否需要持久化，默认false，仅对聊天室固定成员生效
     * */
    private Boolean save = false;
    /**
     *是否需要做通知
     * */
    private Boolean needNotify = false;
    /**
     *通知的内容，长度限制2048
     * */
    private String notifyExt;
    /**
     *聊天室室内的角色信息：昵称
     * */
    private String nick;
    /**
     *聊天室室内的角色信息：头像
     * */
    private String avator;
    /**
     *聊天室室内的角色信息：开发者扩展字段
     * */
    private String ext;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public Boolean getSave() {
        return save;
    }

    public void setSave(Boolean save) {
        this.save = save;
    }

    public Boolean getNeedNotify() {
        return needNotify;
    }

    public void setNeedNotify(Boolean needNotify) {
        this.needNotify = needNotify;
    }

    public String getNotifyExt() {
        return notifyExt;
    }

    public void setNotifyExt(String notifyExt) {
        this.notifyExt = notifyExt;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
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
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != accid){
            list = new LinkedList<>();
            list.add(accid);
            map.put("accid",list);
        }

        if(null != save){
            list = new LinkedList<>();
            list.add(String.valueOf(save));
            map.put("save",list);
        }

        if(null != needNotify){
            list = new LinkedList<>();
            list.add(String.valueOf(needNotify));
            map.put("needNotify",list);
        }

        if(null != notifyExt){
            list = new LinkedList<>();
            list.add(notifyExt);
            map.put("notifyExt",list);
        }

        if(null != nick){
            list = new LinkedList<>();
            list.add(nick);
            map.put("nick",list);
        }

        if(null != avator){
            list = new LinkedList<>();
            list.add(avator);
            map.put("avator",list);
        }

        if(null != ext){
            list = new LinkedList<>();
            list.add(ext);
            map.put("ext",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
