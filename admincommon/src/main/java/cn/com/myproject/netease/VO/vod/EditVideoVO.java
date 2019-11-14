package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class EditVideoVO implements Serializable {

    /**
     * 视频Id
     * 必填：是
     * */
    private Integer vid;
    /**
     * 视频的名称
     * 必填：是
     * */
    private String videoName;
    /**
     * 视频分类Id
     * 必填：是
     * */
    private Integer typeId;
    /**
     * 视频的描述信息
     * 必填：是
     * */
    private String description;


    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
