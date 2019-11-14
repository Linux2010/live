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
public class IMQueueOfferVO implements Serializable {

    /**
     * 聊天室id
     * */
    private Long roomid;
    /**
     * elementKey,新元素的UniqKey,长度限制128字符
     * */
    private String key;
    /**
     * elementValue,新元素内容，长度限制4096字符
     * */
    private String value;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != key){
            list = new LinkedList<>();
            list.add(key);
            map.put("key",list);
        }

        if(null != value){
            list = new LinkedList<>();
            list.add(value);
            map.put("value",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
