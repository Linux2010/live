package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class InitUploadVO implements Serializable {

    /**
     * 上传文件的原始名称（包含后缀名）
     * 必填：是
     * */
    private String originFileName;
    /**
     * 用户命名的上传文件名称
     * 必填：否
     * */
    private String userFileName;
    /**
     * 视频所属的类别Id（不填写为默认分类）
     * 必填：否
     * */
    private Integer typeId;
    /**
     * 视频所需转码模板Id（不填写为默认模板，默认模板不进行转码）
     * 必填：否
     * */
    private Integer presetId;
    /**
     * 上传成功后回调客户端的URL地址（需标准http格式）
     * 必填：否
     * */
    private String uploadCallbackUrl;
    /**
     * 转码成功后回调客户端的URL地址（需标准http格式）
     * 必填：否
     * */
    private String callbackUrl;
    /**
     * 上传视频的描述信息
     * 必填：否
     * */
    private String description;
    /**
     * 视频水印Id（不填写为不添加水印，如果选择，
     * 请务必在水印管理中提前完成水印图片的上传和参数的配置；
     * 且必需设置prestId字段，且presetId字段不为默认模板）
     * 必填：否
     * */
    private Integer watermarkId;
    /**
     * 用户自定义信息，回调会返回此信息
     * 必填：否
     * */
    private String userDefInfo;


    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }

    public String getUploadCallbackUrl() {
        return uploadCallbackUrl;
    }

    public void setUploadCallbackUrl(String uploadCallbackUrl) {
        this.uploadCallbackUrl = uploadCallbackUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
