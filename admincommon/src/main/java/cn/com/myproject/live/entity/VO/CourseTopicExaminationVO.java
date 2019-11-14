package cn.com.myproject.live.entity.VO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/14 0014.
 */
@ApiModel(value = "CourseTopicExaminationVO", description = "注册考题对象")
public class CourseTopicExaminationVO implements Serializable {

    @ApiModelProperty(value = "考题试卷业务ID")
    private String courseTopicExaminationId;
    @ApiModelProperty(value = "课程ID")
    private String courseId;
    @ApiModelProperty(value = "课程NAME")
    private String courseName;
    @ApiModelProperty(value = "考题试卷名称")
    private String examinationName;
    @ApiModelProperty(value = "课程类型ID")
    private String courseTypeId;
    @ApiModelProperty(value = "课程类型Name")
    private String courseTypeName;
    @ApiModelProperty(value = "是否是注册考题 0：代表注册考题")
    private Integer isRegisterTopic;
    private String creater;
    private Long createTime;
    private Long updateTime;
    private String updater;
    private String keyword;

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    private List<Map<String,Object>> topicMaps = new ArrayList<Map<String,Object>>();

    public void setTopicMaps(List<Map<String, Object>> topicMaps) {
        this.topicMaps = topicMaps;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public List<Map<String, Object>> getTopicMaps() {
        return topicMaps;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }

    public Integer getIsRegisterTopic() {
        return isRegisterTopic;
    }

    public void setIsRegisterTopic(Integer isRegisterTopic) {
        this.isRegisterTopic = isRegisterTopic;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
