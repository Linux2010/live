package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class CreateWaterMarkVO implements Serializable {

    /**
     * 视频水印的名称
     * 必填：是
     * */
    private String watermarkName;
    /**
     * 视频水印图片的Id
     * 必填：是
     * */
    private Integer imageId;
    /**
     * 视频水印的描述信息
     * 必填：否
     * */
    private String description;
    /**
     * 视频水印左上角的坐标，其值为相对值，比如：
     * 8%_6%表示在视频左上角的偏右8%视频宽度、偏下6%视频高度的位置。
     * 如果不选填，默认设置为5%_5%
     * 必填：否
     * */
    private String coordinate;
    /**
     * 视频水印的长宽，其值为相对值，比如：
     * 10%x15%表示长为视频宽度的10%，宽为视频宽度的15%。
     * 如果不选填，默认设置为不缩放或拉伸
     * 必填：否
     * */
    private String scale;


    public String getWatermarkName() {
        return watermarkName;
    }

    public void setWatermarkName(String watermarkName) {
        this.watermarkName = watermarkName;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
