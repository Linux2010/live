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
public class IMQueueInitVO implements Serializable {

    /**
     *聊天室id
     * */
    private Long roomid;

    /**
     *队列长度限制，0~1000
     * */
    private Long sizeLimit;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Long getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(Long sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != sizeLimit){
            list = new LinkedList<>();
            list.add(sizeLimit+"");
            map.put("sizeLimit",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
