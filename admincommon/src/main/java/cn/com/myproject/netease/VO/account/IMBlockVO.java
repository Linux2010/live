package cn.com.myproject.netease.VO.account;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class IMBlockVO implements Serializable {
    private String accid;
    private String needkick = "false";

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getNeedkick() {
        return needkick;
    }

    public void setNeedkick(String needkick) {
        this.needkick = needkick;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != accid){
            list = new LinkedList<>();
            list.add(accid);
            map.put("accid",list);
        }

        if(null != needkick){
            list = new LinkedList<>();
            list.add(needkick);
            map.put("needkick",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }


}
