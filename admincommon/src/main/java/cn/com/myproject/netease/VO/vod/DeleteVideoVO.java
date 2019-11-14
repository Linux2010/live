package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class DeleteVideoVO implements Serializable {

    /**
     * 视频Id
     * 必填：是
     * */
    private Integer vid;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }
}
