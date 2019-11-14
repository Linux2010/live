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
public class IMRequestAddrVO implements Serializable {
    /**
     *聊天室id
     * */
    private Long roomid;
    /**
     *进入聊天室的账号
     * */
    private String accid;
    /**
     *1:weblink; 2:commonlink, 默认1
     * */
    private Integer clienttype = 1;


    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public Integer getClienttype() {
        return clienttype;
    }

    public void setClienttype(Integer clienttype) {
        this.clienttype = clienttype;
    }


    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != accid){
            list = new LinkedList<>();
            list.add(accid);
            map.put("accid",list);
        }

        if(null != clienttype){
            list = new LinkedList<>();
            list.add(clienttype+"");
            map.put("clienttype",list);
        }


        return CollectionUtils.toMultiValueMap(map);
    }
}
