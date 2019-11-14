package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class DeleteSingleVideoVO implements Serializable {

    /**
     * 视频Id
     * 必填：是
     * */
    private Integer vid;
    /**
     * 视频转码格式（1表示标清mp4，2表示高清mp4，3表示超清mp4，
     * 4表示标清flv，5表示高清flv，6表示超清flv，
     * 7表示标清hls，8表示高清hls，9表示超清hls）
     *
     * 必填：是
     * */
    private Integer style;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }
}
