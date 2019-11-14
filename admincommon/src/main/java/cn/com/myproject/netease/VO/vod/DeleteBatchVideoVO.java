package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class DeleteBatchVideoVO implements Serializable {

    private List<Integer> vids;

    public List<Integer> getVids() {
        return vids;
    }

    public void setVids(List<Integer> vids) {
        this.vids = vids;
    }
}
