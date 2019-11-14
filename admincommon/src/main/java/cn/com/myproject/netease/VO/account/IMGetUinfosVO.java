package cn.com.myproject.netease.VO.account;

import com.google.gson.Gson;
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
public class IMGetUinfosVO  implements Serializable {

    /***
     * 用户帐号（例如：JSONArray对应的accid串，如：["zhangsan"]，如果解析出错，会报414）（一次查询最多为200）
     */
    public List<String> accids;

    public List<String> getAccids() {
        return accids;
    }

    public void setAccids(List<String> accids) {
        this.accids = accids;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != accids){
            list = new LinkedList<>();
            list.add(new Gson().toJson(accids));
            map.put("accids",list);
        }
        return CollectionUtils.toMultiValueMap(map);
    }
}
