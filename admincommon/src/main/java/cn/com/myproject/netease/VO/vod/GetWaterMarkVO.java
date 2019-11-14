package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class GetWaterMarkVO implements Serializable {


    /**
     * 视频水印模板Id
     * 必填：是
     * */
    private Integer watermarkId;

    public Integer getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId(Integer watermarkId) {
        this.watermarkId = watermarkId;
    }
}
