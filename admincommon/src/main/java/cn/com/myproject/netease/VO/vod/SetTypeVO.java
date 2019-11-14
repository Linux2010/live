package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class SetTypeVO implements Serializable {

    /**
     * 视频Id
     * 必填：是
     * */
    private Integer vid;
    /**
     * 视频分类的Id
     * 必填：是
     * */
    private Integer typeId;


    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
