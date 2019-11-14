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
public class IMAddRobotVO implements Serializable {

    /***
     * 聊天室id
     * 必填：是
     */
    private Long roomid;
    /**
     *机器人账号accid列表，必须是有效账号，账号数量上限100个
     * JSONArray
     *
     *["zhangsan","lisi"]
     *
     * 必填：是
     * */
    private String accids;
    /**
     *机器人信息扩展字段，请使用json格式，长度4096字符
     *
     * 必填：否
     * */
    private String roleExt;
    /***
     * 机器人进入聊天室通知的扩展字段，请使用json格式，长度2048字符
     *
     * 必填：否
     */
    private String notifyExt;


    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getAccids() {
        return accids;
    }

    public void setAccids(String accids) {
        this.accids = accids;
    }

    public String getRoleExt() {
        return roleExt;
    }

    public void setRoleExt(String roleExt) {
        this.roleExt = roleExt;
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

        if(null != accids){
            list = new LinkedList<>();
            list.add(accids);
            map.put("accids",list);
        }

        if(null != roleExt){
            list = new LinkedList<>();
            list.add(roleExt);
            map.put("roleExt",list);
        }

        if(null != notifyExt){
            list = new LinkedList<>();
            list.add(notifyExt);
            map.put("notifyExt",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
