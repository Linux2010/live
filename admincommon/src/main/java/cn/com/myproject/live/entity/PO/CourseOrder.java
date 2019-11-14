package cn.com.myproject.live.entity.PO;

import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by JYP on 2017/8/15 0015.
 */
@ApiModel(value = "Course", description = "课程订单")
public class CourseOrder extends BasePO{

    @ApiModelProperty(value = "课程订单ID")
    private String courseOrderId;

    @ApiModelProperty(value = "课程订单时间")
    private Long orderCreateTime;

    // 0:代付款 1:已购买
    @ApiModelProperty(value = "0:代付款 1:已购买")
    private Integer orderStatus;

    // 0:未付款 1:已付款
    @ApiModelProperty(value = "0:未付款 1:已付款")
    private Integer payStatus;

    // 0：银两支付1:支付宝 2:微信3：paypal
    @ApiModelProperty(value = "0：银两支付1:支付宝 2:微信3：paypal")
    private Integer payType;

    @ApiModelProperty(value = "总金额")
    private Double totalMoney;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    private Course course;

    private User user;

    private CourseType courseType;

    // 订单编号
    @ApiModelProperty(value = "订单编号：a+业务ID")
    private String courseOrderNo;

    @ApiModelProperty(value = "完成状态")
    private Integer FinishStatus;

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(String courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    public Long getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Long orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourseOrderNo() {
        return courseOrderNo;
    }

    public void setCourseOrderNo(String courseOrderNo) {
        this.courseOrderNo = courseOrderNo;
    }

    public Integer getFinishStatus() {
        return FinishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        FinishStatus = finishStatus;
    }
}