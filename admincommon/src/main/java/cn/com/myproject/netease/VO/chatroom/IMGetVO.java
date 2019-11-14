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
public class IMGetVO implements Serializable {
    /**
     * 聊天室id
     */
    private Long roomid;
    /**
     * 是否需要返回在线人数，true或false，默认false
     */
    private String needOnlineUserCount;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getNeedOnlineUserCount() {
        return needOnlineUserCount;
    }

    public void setNeedOnlineUserCount(String needOnlineUserCount) {
        this.needOnlineUserCount = needOnlineUserCount;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != needOnlineUserCount){
            list = new LinkedList<>();
            list.add(needOnlineUserCount);
            map.put("needOnlineUserCount",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
