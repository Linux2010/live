package cn.com.myproject.live.entity.VO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @title 注册考题
 * Created by JYP on 2017/8/28 0028.
 */
@ApiModel(value = "CourseRegisterExamVO", description = "注册考题")
public class CourseRegisterExamVO implements Serializable {

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
    @ApiModelProperty(value = "课程题目业务ID")
    private String courseTopicId;
    @ApiModelProperty(value = "考题试卷业务ID")
    private String courseTopicExaminationId;
    @ApiModelProperty(value = "课程题目名称")
    private String topicName;
    @ApiModelProperty(value = "课程选项1")
    private String topicAvalue;
    @ApiModelProperty(value = "课程选项2")
    private String topicBvalue;
    @ApiModelProperty(value = "课程选项3")
    private String topicCvalue;
    @ApiModelProperty(value = "课程选项4")
    private String topicDvalue;
    @ApiModelProperty(value = "课程选项1学习标签业务ID")
    private String topicAlabelId;
    @ApiModelProperty(value = "课程选项2学习标签业务ID")
    private String topicBlabelId;
    @ApiModelProperty(value = "课程选项3学习标签业务ID")
    private String topicClabelId;
    @ApiModelProperty(value = "课程选项4学习标签业务ID")
    private String topicDlabelId;
    @ApiModelProperty(value = "用户选择的答案(A:选项A；B:选项B;C:选项C;D:选项D ),多个正确答案以英文逗号记录")
    private String rightAnswer;

    public String getCourseTopicExaminationId() {
        return courseTopicExaminationId;
    }

    public void setCourseTopicExaminationId(String courseTopicExaminationId) {
        this.courseTopicExaminationId = courseTopicExaminationId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicAvalue() {
        return topicAvalue;
    }

    public void setTopicAvalue(String topicAvalue) {
        this.topicAvalue = topicAvalue;
    }

    public String getTopicBvalue() {
        return topicBvalue;
    }

    public void setTopicBvalue(String topicBvalue) {
        this.topicBvalue = topicBvalue;
    }

    public String getTopicCvalue() {
        return topicCvalue;
    }

    public void setTopicCvalue(String topicCvalue) {
        this.topicCvalue = topicCvalue;
    }

    public String getTopicDvalue() {
        return topicDvalue;
    }

    public void setTopicDvalue(String topicDvalue) {
        this.topicDvalue = topicDvalue;
    }

    public String getTopicAlabelId() {
        return topicAlabelId;
    }

    public void setTopicAlabelId(String topicAlabelId) {
        this.topicAlabelId = topicAlabelId;
    }

    public String getTopicBlabelId() {
        return topicBlabelId;
    }

    public void setTopicBlabelId(String topicBlabelId) {
        this.topicBlabelId = topicBlabelId;
    }

    public String getTopicClabelId() {
        return topicClabelId;
    }

    public void setTopicClabelId(String topicClabelId) {
        this.topicClabelId = topicClabelId;
    }

    public String getTopicDlabelId() {
        return topicDlabelId;
    }

    public void setTopicDlabelId(String topicDlabelId) {
        this.topicDlabelId = topicDlabelId;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
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

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public Integer getIsRegisterTopic() {
        return isRegisterTopic;
    }

    public void setIsRegisterTopic(Integer isRegisterTopic) {
        this.isRegisterTopic = isRegisterTopic;
    }

    public String getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(String courseTopicId) {
        this.courseTopicId = courseTopicId;
    }
}
