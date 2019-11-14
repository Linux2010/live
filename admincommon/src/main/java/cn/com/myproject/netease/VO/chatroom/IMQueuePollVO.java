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
public class IMQueuePollVO implements Serializable {
    /**
     *聊天室id
     * */
    private Long roomid;

    /**
     *目前元素的elementKey,长度限制128字符，不填表示取出头上的第一个
     * */
    private String key;

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

        return CollectionUtils.toMultiValueMap(map);
    }
}
