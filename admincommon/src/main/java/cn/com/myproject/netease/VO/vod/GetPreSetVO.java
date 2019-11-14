package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class GetPreSetVO implements Serializable {

    /**
     * 视频转码模板Id
     * 必填：是
     * */
    private Integer presetId;

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }
}
