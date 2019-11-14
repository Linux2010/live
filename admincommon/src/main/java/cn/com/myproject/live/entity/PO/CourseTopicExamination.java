package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeiJia on 2017/8/14 0014.
 */

@ApiModel(value = "CourseTopicExamination", description = "考卷对象")
public class CourseTopicExamination extends BasePO {

    @ApiModelProperty(value = "考题试卷业务ID",required = true )
    private String courseTopicExaminationId;
    @ApiModelProperty(value = "课程业务ID",required = false )
    private String courseId;
    @ApiModelProperty(value = "考卷名称",required = false )
    private String examinationName;
    @ApiModelProperty(value = "是否是注册考题试卷(0:是注册考题;1:课程考题)",required = false)
    private int isRegisterTopic;
    @ApiModelProperty(value = "考题创建人业务ID",required = false)
    private String creater;
    @ApiModelProperty(value = "考题更新时间",required = false)
    private long updateTime;
    @ApiModelProperty(value = "考题更新人业务ID",required = false)
    private String updater;
    @ApiModelProperty(value = "用户提交的考题答案",required = true)
    private List<CourseTopicUserAnswer> answerList = new ArrayList<CourseTopicUserAnswer>();

    @ApiModelProperty(value = "提交考题答案的用户ID",required = true)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CourseTopicUserAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<CourseTopicUserAnswer> answerList) {
        this.answerList = answerList;
    }

    public String getCourseTopicExaminationId() {
        return courseTopicExaminationId;
    }

    public void setCourseTopicExaminationId(String courseTopicExaminationId) {
        this.courseTopicExaminationId = courseTopicExaminationId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }

    public int getIsRegisterTopic() {
        return isRegisterTopic;
    }

    public void setIsRegisterTopic(int isRegisterTopic) {
        this.isRegisterTopic = isRegisterTopic;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
