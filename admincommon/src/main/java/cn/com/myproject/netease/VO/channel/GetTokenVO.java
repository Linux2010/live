package cn.com.myproject.netease.VO.channel;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/20.
 */
public class GetTokenVO implements Serializable {

    /**
     * 用户帐号唯一标识
     * 必填：是
     * */
    private String accid;
    /**
     * 是否重复使用，默认可以重复使用
     * 必填：否
     * */
    private Boolean repeatUse;
    /**
     * 过期的秒数，默认10分钟，最大1天
     * 必填：否
     * */
    private Integer expireAt;
    /**
     * 频道名称，缺失则仅以uid为维度
     * 必填：否
     * */
    private String channelName;


    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public Boolean getRepeatUse() {
        return repeatUse;
    }

    public void setRepeatUse(Boolean repeatUse) {
        this.repeatUse = repeatUse;
    }

    public Integer getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Integer expireAt) {
        this.expireAt = expireAt;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != accid){
            list = new LinkedList<>();
            list.add(accid);
            map.put("accid",list);
        }

        if(null != repeatUse){
            list = new LinkedList<>();
            list.add(repeatUse+"");
            map.put("name",list);
        }

        if(null != expireAt){
            list = new LinkedList<>();
            list.add(expireAt+"");
            map.put("expireAt",list);
        }

        if(null != channelName){
            list = new LinkedList<>();
            list.add(channelName);
            map.put("channelName",list);
        }


        return CollectionUtils.toMultiValueMap(map);
    }
}
