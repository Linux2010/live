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
public class IMTemporaryMuteVO implements Serializable {

    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *操作者accid,必须是管理员或创建者
     * */
    private String operator;
    /**
     *被禁言的目标账号accid
     * */
    private String target;
    /**
     *0:解除禁言;>0设置禁言的秒数，不能超过2592000秒(30天)
     * */
    private Long muteDuration;
    /**
     *操作完成后是否需要发广播，true或false，默认true
     * */
    private String needNotify;
    /**
     *通知广播事件中的扩展字段，长度限制2048字符
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

    public Long getMuteDuration() {
        return muteDuration;
    }

    public void setMuteDuration(Long muteDuration) {
        this.muteDuration = muteDuration;
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

        if(null != target){
            list = new LinkedList<>();
            list.add(target);
            map.put("target",list);
        }

        if(null != muteDuration){
            list = new LinkedList<>();
            list.add(muteDuration+"");
            map.put("muteDuration",list);
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
