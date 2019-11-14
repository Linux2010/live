package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/17.
 */
public class UpdateTypeVO implements Serializable {
    /**
     * 视频分类的Id
     * 必填：是
     * */
    private Integer typeId;
    /***
     * 视频分类的名称
     * 必填：是
     * */
    private String typeName;
    /**
     *视频分类的描述信息
     * 必填：否
     * */
    private String description;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
