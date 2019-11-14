package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class UpdatePreSetVO implements Serializable {

    /**
     * 视频转码模板的Id
     * 必填：是
     * */
    private Integer presetId;
    /**
     * 视频转码模板的名称
     * 必填：是
     * */
    private String presetName;
    /**
     * 流畅Mp4格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer sdMp4;
    /**
     * 标清Mp4格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer hdMp4;
    /**
     * 高清Mp4格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer shdMp4;
    /**
     * 流畅Flv格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer sdFlv;
    /**
     * 标清Flv格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer hdFlv;
    /**
     * 高清Flv格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer shdFlv;
    /**
     * 流畅Hls格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer sdHls;
    /**
     * 标清Hls格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer hdHls;
    /**
     * 高清Hls格式（1表示选择，0表示不选择）
     * 必填：是
     * */
    private Integer shdHls;

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public Integer getSdMp4() {
        return sdMp4;
    }

    public void setSdMp4(Integer sdMp4) {
        this.sdMp4 = sdMp4;
    }

    public Integer getHdMp4() {
        return hdMp4;
    }

    public void setHdMp4(Integer hdMp4) {
        this.hdMp4 = hdMp4;
    }

    public Integer getShdMp4() {
        return shdMp4;
    }

    public void setShdMp4(Integer shdMp4) {
        this.shdMp4 = shdMp4;
    }

    public Integer getSdFlv() {
        return sdFlv;
    }

    public void setSdFlv(Integer sdFlv) {
        this.sdFlv = sdFlv;
    }

    public Integer getHdFlv() {
        return hdFlv;
    }

    public void setHdFlv(Integer hdFlv) {
        this.hdFlv = hdFlv;
    }

    public Integer getShdFlv() {
        return shdFlv;
    }

    public void setShdFlv(Integer shdFlv) {
        this.shdFlv = shdFlv;
    }

    public Integer getSdHls() {
        return sdHls;
    }

    public void setSdHls(Integer sdHls) {
        this.sdHls = sdHls;
    }

    public Integer getHdHls() {
        return hdHls;
    }

    public void setHdHls(Integer hdHls) {
        this.hdHls = hdHls;
    }

    public Integer getShdHls() {
        return shdHls;
    }

    public void setShdHls(Integer shdHls) {
        this.shdHls = shdHls;
    }
}
