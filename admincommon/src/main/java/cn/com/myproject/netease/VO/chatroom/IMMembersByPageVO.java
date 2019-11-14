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
public class IMMembersByPageVO implements Serializable {
    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *需要查询的成员类型,0:固定成员;1:非固定成员;2:仅返回在线的固定成员
     * */
    private Integer type;
    /**
     *单位毫秒，按时间倒序最后一个成员的时间戳,0表示系统当前时间
     * */
    private Long endtime;
    /**
     *返回条数，<=100
     * */
    private Long limit;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != type){
            list = new LinkedList<>();
            list.add(type+"");
            map.put("type",list);
        }

        if(null != endtime){
            list = new LinkedList<>();
            list.add(endtime+"");
            map.put("endtime",list);
        }

        if(null != limit){
            list = new LinkedList<>();
            list.add(limit+"");
            map.put("limit",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
