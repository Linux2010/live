package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class DeleteTypeVO implements Serializable {


    /**
     * 视频分类Id
     * */
    private Integer typeId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
