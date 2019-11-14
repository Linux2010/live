package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-08-30
 * desc：课程回复
 */
@ApiModel(value = "CourseReplyVO", description = "课程回复")
public class CourseReplyVO {

    @ApiModelProperty(value = "回复用户ID")
    private String replayUserId;

    @ApiModelProperty(value = "回复用户名称")
    private String replayUserName;

    @ApiModelProperty(value = "回复内容")
    private String replayContent;

    @ApiModelProperty(value = "回复时间")
    private long replayTime;

    public String getReplayUserId() {
        return replayUserId;
    }

    public void setReplayUserId(String replayUserId) {
        this.replayUserId = replayUserId;
    }

    public String getReplayUserName() {
        return replayUserName;
    }

    public void setReplayUserName(String replayUserName) {
        this.replayUserName = replayUserName;
    }

    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent;
    }

    public long getReplayTime() {
        return replayTime;
    }

    public void setReplayTime(long replayTime) {
        this.replayTime = replayTime;
    }

}