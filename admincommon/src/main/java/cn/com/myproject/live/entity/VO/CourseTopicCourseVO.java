package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/14 0014.
 */
@ApiModel(value = "CourseTopicExaminationAnswerVO", description = "我的答题-用户答题课程对象")
public class CourseTopicCourseVO implements Serializable {

    @ApiModelProperty(value = "考题试卷业务ID")
    private String courseTopicExaminationId;

    @ApiModelProperty(value = "用户答题表业务ID")
    private String courseTopicUserAnswerId;

    @ApiModelProperty(value = "课程考题业务ID",required = true)
    private  String courseTopicId;

    @ApiModelProperty(value = "答题用户业务ID",required = true)
    private String answerUserId;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "课程NAME")
    private String courseName;

    // 课程名称
    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    // 课程内容
    @ApiModelProperty(value = "课程内容")
    private String courseContent;

    // 课程封面
    @ApiModelProperty(value = "课程封面")
    private String courseCover;

    // 课程列表图片
    @ApiModelProperty(value = "课程列表图片")
    private String courseImg;

    // 课程简介
    @ApiModelProperty(value = "课程简介")
    private String courseIntro;

    @ApiModelProperty(value = "课程发布时间")
    private Long courseFbTime;

    @ApiModelProperty(value = "课程开始时间")
    private Long courseBeginTime;

    @ApiModelProperty(value = "课程讲师真实姓名")
    private String realName;

    // 讲师ID
    @ApiModelProperty(value = "讲师ID")
    private String teacherId;

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public Long getCourseBeginTime() {
        return courseBeginTime;
    }

    public void setCourseBeginTime(Long courseBeginTime) {
        this.courseBeginTime = courseBeginTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCourseTopicExaminationId() {
        return courseTopicExaminationId;
    }

    public void setCourseTopicExaminationId(String courseTopicExaminationId) {
        this.courseTopicExaminationId = courseTopicExaminationId;
    }

    public String getCourseTopicUserAnswerId() {
        return courseTopicUserAnswerId;
    }

    public void setCourseTopicUserAnswerId(String courseTopicUserAnswerId) {
        this.courseTopicUserAnswerId = courseTopicUserAnswerId;
    }

    public String getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(String courseTopicId) {
        this.courseTopicId = courseTopicId;
    }

    public String getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(String answerUserId) {
        this.answerUserId = answerUserId;
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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public Long getCourseFbTime() {
        return courseFbTime;
    }

    public void setCourseFbTime(Long courseFbTime) {
        this.courseFbTime = courseFbTime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
