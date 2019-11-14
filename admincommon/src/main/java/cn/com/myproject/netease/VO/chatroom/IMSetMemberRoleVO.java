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
public class IMSetMemberRoleVO implements Serializable {

    /**
     * 聊天室id
     * */
    private Long roomid;

    /**
     * 操作者账号accid
     * */
    private String operator;

    /**
     *被操作者账号accid
     * */
    private String target;

    /**
     * 操作：
     1: 设置为管理员，operator必须是创建者
     2:设置普通等级用户，operator必须是创建者或管理员
     -1:设为黑名单用户，operator必须是创建者或管理员
     -2:设为禁言用户，operator必须是创建者或管理员
     * */
    private Integer opt;

    /**
     * true或false，true:设置；false:取消设置
     * */
    private String optvalue;

    /**
     *通知扩展字段，长度限制2048，请使用json格式
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getOpt() {
        return opt;
    }

    public void setOpt(Integer opt) {
        this.opt = opt;
    }

    public String getOptvalue() {
        return optvalue;
    }

    public void setOptvalue(String optvalue) {
        this.optvalue = optvalue;
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

        if(null != target){
            list = new LinkedList<>();
            list.add(target);
            map.put("target",list);
        }

        if(null != opt){
            list = new LinkedList<>();
            list.add(opt+"");
            map.put("opt",list);
        }

        if(null != optvalue){
            list = new LinkedList<>();
            list.add(optvalue);
            map.put("optvalue",list);
        }

        if(null != notifyExt){
            list = new LinkedList<>();
            list.add(notifyExt);
            map.put("notifyExt",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
