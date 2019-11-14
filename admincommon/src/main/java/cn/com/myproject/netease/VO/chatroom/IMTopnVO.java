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
public class IMTopnVO implements Serializable {
    /**
     *topn值，可选值 1~500，默认值100
     * */
    private Integer topn = 100;
    /**
     *需要查询的指标所在的时间坐标点，不提供则默认当前时间，单位秒/毫秒皆可
     * */
    private Long timestamp;
    /**
     *统计周期，可选值包括 hour/day, 默认hour
     * */
    private String period = "hour";
    /**
     *取排序值,可选值 active/enter/message,分别表示按日活排序，进入人次排序和消息数排序， 默认active
     * */
    private String orderby = "active";

    public Integer getTopn() {
        return topn;
    }

    public void setTopn(Integer topn) {
        this.topn = topn;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != topn){
            list = new LinkedList<>();
            list.add(topn+"");
            map.put("topn",list);
        }

        if(null != timestamp){
            list = new LinkedList<>();
            list.add(timestamp+"");
            map.put("timestamp",list);
        }

        if(null != period){
            list = new LinkedList<>();
            list.add(period);
            map.put("period",list);
        }

        if(null != orderby){
            list = new LinkedList<>();
            list.add(orderby);
            map.put("orderby",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
