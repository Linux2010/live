package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LSG on 2017/9/26 0026.
 */
@ApiModel(value = "shortVideo", description = "app启动短视频对像")
public class ShortVideo extends BasePO{

    @ApiModelProperty(value = "视频id")
    private String videoId;
    @ApiModelProperty(value = "视频名称")
    private String videoName;
    @ApiModelProperty(value = "视频文件id")
    private String fileId;
    @ApiModelProperty(value = "视频文件")
    private String videoFile;
    @ApiModelProperty(value = "视频文件url")
    private String videoUrl;
    @ApiModelProperty(value = "视频类型：1、ios使用视频 2、Android使用视频")
    private int videoType;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }
}
