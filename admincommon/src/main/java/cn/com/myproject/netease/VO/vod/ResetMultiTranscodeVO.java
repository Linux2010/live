package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class ResetMultiTranscodeVO implements Serializable {

    /**
     * 多个视频Id组成的列表
     * 必填：是
     * **/
    private List<Integer> vids;
    /**
     * 转码模板Id
     * 必填：是
     * **/
    private Integer presetId;
    /**
     * 视频水印Id（不填写为不添加水印，且若填写，presetId值不能为默认模板）
     * 必填：否
     * **/
    private Integer watermarkId;
    /**
     * 用户自定义信息，回调会返回此信息
     * 必填：否
     * **/
    private String userDefInfo;

    public List<Integer> getVids() {
        return vids;
    }

    public void setVids(List<Integer> vids) {
        this.vids = vids;
    }

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }

    public Integer getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId(Integer watermarkId) {
        this.watermarkId = watermarkId;
    }

    public String getUserDefInfo() {
        return userDefInfo;
    }

    public void setUserDefInfo(String userDefInfo) {
        this.userDefInfo = userDefInfo;
    }
}
