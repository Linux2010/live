package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-08-30
 * desc：课程订单
 */
@ApiModel(value = "CourseOrderVO", description = "课程订单")
public class CourseOrderVO {

    // 用户ID
    @ApiModelProperty(value = "用户ID")
    private String userId;

    // 用户名
    @ApiModelProperty(value = "用户名")
    private String userName;

    // 真实姓名
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    // 用户电话
    @ApiModelProperty(value = "用户电话")
    private String userPhone;

    // 用户邮箱
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;

    // 讲师名称
    @ApiModelProperty(value = "讲师名称")
    private String tName;

    // 讲师头像
    @ApiModelProperty(value = "讲师头像")
    private String tPhoto;

    // 课程ID
    @ApiModelProperty(value = "课程ID")
    private String courseId;

    // 课程名称
    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    // 课程封面
    @ApiModelProperty(value = "课程封面")
    private String courseCover;

    // 课程列表图片
    @ApiModelProperty(value = "课程列表图片")
    private String courseImg;

    // 课程开始时间
    @ApiModelProperty(value = "课程开始时间")
    private long courseBeginTime;

    // 课程结束时间
    @ApiModelProperty(value = "课程结束时间")
    private long courseEndTime;

    // 课程普通用户价格
    @ApiModelProperty(value = "课程普通用户价格")
    private double costPrice;

    // 课程会员价
    @ApiModelProperty(value = "课程会员价")
    private double vipPrice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPhoto() {
        return tPhoto;
    }

    public void settPhoto(String tPhoto) {
        this.tPhoto = tPhoto;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public long getCourseBeginTime() {
        return courseBeginTime;
    }

    public void setCourseBeginTime(long courseBeginTime) {
        this.courseBeginTime = courseBeginTime;
    }

    public long getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(long courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }
}