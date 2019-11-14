package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMPauseListVO implements Serializable {
    /**
     *idList	JsonArray	频道ID列表
     * 必填：是
     * */
    private List<String> cidList;


    public List<String> getCidList() {
        return cidList;
    }

    public void setCidList(List<String> cidList) {
        this.cidList = cidList;
    }
}
