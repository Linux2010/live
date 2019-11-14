package cn.com.myproject.netease.VO.account;

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
public class IMSetDonnopVO implements Serializable {
    /***
     * 用户账号
     */
    private String accid;

    /**
     * 桌面端在线时，移动端是否不推送：
     * true:移动端不需要推送，false:移动端需要推送
     */
    private String donnopOpen;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getDonnopOpen() {
        return donnopOpen;
    }

    public void setDonnopOpen(String donnopOpen) {
        this.donnopOpen = donnopOpen;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != accid){
            list = new LinkedList<>();
            list.add(accid);
            map.put("accid",list);
        }

        if(null != donnopOpen){
            list = new LinkedList<>();
            list.add(donnopOpen);
            map.put("donnopOpen",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
