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
public class IMToggleCloseStatVO implements Serializable {
    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *操作者账号，必须是创建者才可以操作
     * */
    private String operator;
    /**
     *true或false，false:关闭聊天室；true:打开聊天室
     * */
    private String valid;

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

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

        if(null != valid){
            list = new LinkedList<>();
            list.add(valid);
            map.put("valid",list);
        }


        return CollectionUtils.toMultiValueMap(map);
    }
}
