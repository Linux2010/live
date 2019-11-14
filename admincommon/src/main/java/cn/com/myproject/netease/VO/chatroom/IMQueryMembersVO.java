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
public class IMQueryMembersVO implements Serializable {

    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *["abc","def"], 账号列表，最多200条
     * */
    private String accids;

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

        return CollectionUtils.toMultiValueMap(map);
    }

}
