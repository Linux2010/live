package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程
 */
@ApiModel(value = "Course", description = "课程对像")
public class Course extends BasePO{

    // 课程ID
    @ApiModelProperty(value = "课程ID")
    private String courseId;

    // 课程分类ID
    @ApiModelProperty(value = "课程分类ID")
    private String courseTypeId;

    // 序列号
    @ApiModelProperty(value = "序列号")
    private long seqno;

    // 课程封面
    @ApiModelProperty(value = "课程封面")
    private String courseCover;

    // 课程列表图片
    @ApiModelProperty(value = "课程列表图片")
    private String courseImg;

    // 课程名称
    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    // 课程内容
    @ApiModelProperty(value = "课程内容")
    private String courseContent;

    // 课程开始时间
    @ApiModelProperty(value = "课程开始时间")
    private long courseBeginTime;

    // 课程结束时间
    @ApiModelProperty(value = "课程结束时间")
    private long courseEndTime;

    // 讲师ID
    @ApiModelProperty(value = "讲师ID")
    private String teacherId;

    // 课程普通用户价格
    @ApiModelProperty(value = "课程普通用户价格")
    private double costPrice;

    // 课程会员价
    @ApiModelProperty(value = "课程会员价")
    private double vipPrice;

    // 课程简介
    @ApiModelProperty(value = "课程简介")
    private String courseIntro;

    // 课程积分
    @ApiModelProperty(value = "课程积分")
    private int courseIntegral;

    // 课程题ID
    @ApiModelProperty(value = "课程题ID")
    private String courseTopicId;

    // 江湖大课授课地址
    @ApiModelProperty(value = "江湖大课授课地址")
    private String courseAddress;

    // 课程标签ID
    @ApiModelProperty(value = "课程标签ID")
    private String courseTagId;

    // 课程类型名称
    @ApiModelProperty(value = "课程类型名称")
    private String tyname;

    // 讲师名称
    @ApiModelProperty(value = "讲师名称")
    private String tname;

    // 课程类别
    @ApiModelProperty(value = "课程类别：spkc(视频课程)、wzkc(文字课程)、(ypkc)音频课程、(zbkc)直播课程、(jhdk)江湖大课")
    private String tyval;

    // 视频的网易映射ID
    @ApiModelProperty(value = "视频的网易映射ID")
    private String videoId;

    // 视频上传之后的对象名称
    @ApiModelProperty(value = "视频上传之后的对象名称")
    private String objName;

    // 课程类型名称
    @ApiModelProperty(value = "课程类型名称")
    private String ctName;

    // 直播类型：sp代表视频，yp代表音频
    @ApiModelProperty(value = "直播类型：sp代表视频，yp代表音频")
    private String zbType;

    // 讲师头像
    @ApiModelProperty(value = "讲师头像")
    private String photo;

    // 讲师签名
    @ApiModelProperty(value = "讲师签名")
    private String signature;

    // 是否已关注讲师：0代表未关注，1代表已关注
    @ApiModelProperty(value = "是否已关注讲师：0代表未关注，1代表已关注")
    private int attentionFlag;

    // 是否已收藏课程：0代表未收藏，1代表已收藏
    @ApiModelProperty(value = "是否已收藏课程：0代表未收藏，1代表已收藏")
    private int collectFlag;

    // 是否已购买课程：0代表未购买，1代表已购买
    @ApiModelProperty(value = "是否已购买课程：0代表未购买，1代表已购买")
    private int orderFlag;

    // 视频或音频播放路径
    @ApiModelProperty(value = "视频或音频播放路径")
    private String wyUrlVal;

    @ApiModelProperty(value = "课程预约数")
    private int orderNumber;

    @ApiModelProperty(value = "是否可以开启直播1：可以开启，0不可以开启 课程开始前五分钟前开始")
    private int iSCanOpenLive;

    @ApiModelProperty(value = "课程发布时间")
    private Long courseFbTime;

    // 江湖大课详细地址
    @ApiModelProperty(value = "江湖大课详细地址")
    private String courseAddressDetail;

    // 课程订单编号
    @ApiModelProperty(value = "课程订单编号")
    private String courseOrderNo;

    @ApiModelProperty(value = "会员观看状态，0 未完成 1 已完成")
    private int viewFinishStatus;

    @ApiModelProperty(value = "教头聊天室房间ID")
    private String imRoomid;

    @ApiModelProperty(value = "会员观看时长")
    private int pauseDuration;

    @ApiModelProperty(value = "是否答过题：0代表未答过题，1代表已答过题")
    private int dtFlag;

    @ApiModelProperty(value = "评论总条数")
    private int totalCcCount;

    @ApiModelProperty(value = "考题数量")
    private int ktCount;

    @ApiModelProperty(value = "是否有课程考题：0代表无课程考题，1代表有课程考题")
    private int ktYnFlag;

    @ApiModelProperty(value = "用户身份：1代表普通用户，2代表会员")
    private int userIdentity;

    @ApiModelProperty(value = "考卷ID")
    private String kjId;

    public String getKjId() {
        return kjId;
    }

    public void setKjId(String kjId) {
        this.kjId = kjId;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getImRoomid() {
        return imRoomid;
    }

    public void setImRoomid(String imRoomid) {
        this.imRoomid = imRoomid;
    }

    public int getiSCanOpenLive() {
        return iSCanOpenLive;
    }

    public void setiSCanOpenLive(int iSCanOpenLive) {
        this.iSCanOpenLive = iSCanOpenLive;
    }

    public Long getCourseFbTime() {
        return courseFbTime;
    }

    public void setCourseFbTime(Long courseFbTime) {
        this.courseFbTime = courseFbTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTyval() {
        return tyval;
    }

    public void setTyval(String tyval) {
        this.tyval = tyval;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public long getSeqno() {
        return seqno;
    }

    public void setSeqno(long seqno) {
        this.seqno = seqno;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public int getCourseIntegral() {
        return courseIntegral;
    }

    public void setCourseIntegral(int courseIntegral) {
        this.courseIntegral = courseIntegral;
    }

    public String getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(String courseTopicId) {
        this.courseTopicId = courseTopicId;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getCourseTagId() {
        return courseTagId;
    }

    public void setCourseTagId(String courseTagId) {
        this.courseTagId = courseTagId;
    }

    public String getTyname() {
        return tyname;
    }

    public void setTyname(String tyname) {
        this.tyname = tyname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getZbType() {
        return zbType;
    }

    public void setZbType(String zbType) {
        this.zbType = zbType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getAttentionFlag() {
        return attentionFlag;
    }

    public void setAttentionFlag(int attentionFlag) {
        this.attentionFlag = attentionFlag;
    }

    public int getCollectFlag() {
        return collectFlag;
    }

    public void setCollectFlag(int collectFlag) {
        this.collectFlag = collectFlag;
    }

    public String getWyUrlVal() {
        return wyUrlVal;
    }

    public void setWyUrlVal(String wyUrlVal) {
        this.wyUrlVal = wyUrlVal;
    }

    public int getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(int orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getCourseAddressDetail() {
        return courseAddressDetail;
    }

    public void setCourseAddressDetail(String courseAddressDetail) {
        this.courseAddressDetail = courseAddressDetail;
    }

    public String getCourseOrderNo() {
        return courseOrderNo;
    }

    public void setCourseOrderNo(String courseOrderNo) {
        this.courseOrderNo = courseOrderNo;
    }

    public int getViewFinishStatus() {
        return viewFinishStatus;
    }

    public void setViewFinishStatus(int viewFinishStatus) {
        this.viewFinishStatus = viewFinishStatus;
    }

    public int getPauseDuration() {
        return pauseDuration;
    }

    public void setPauseDuration(int pauseDuration) {
        this.pauseDuration = pauseDuration;
    }

    public int getDtFlag() {
        return dtFlag;
    }

    public void setDtFlag(int dtFlag) {
        this.dtFlag = dtFlag;
    }

    public int getTotalCcCount() {
        return totalCcCount;
    }

    public void setTotalCcCount(int totalCcCount) {
        this.totalCcCount = totalCcCount;
    }

    public int getKtCount() {
        return ktCount;
    }

    public void setKtCount(int ktCount) {
        this.ktCount = ktCount;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public int getKtYnFlag() {
        return ktYnFlag;
    }

    public void setKtYnFlag(int ktYnFlag) {
        this.ktYnFlag = ktYnFlag;
    }

}