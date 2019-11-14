package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/8/29 0029.
 */
@ApiModel(value = "UserTeacherQuestionReply", description = "教头回复用户问答对象")
public class UserTeacherQuestionReply extends BasePO {
    @ApiModelProperty(value = "教头回答用户提问表业务ID")
    private String userTeacherQuestionReplyId;
    @ApiModelProperty(value = "用户提问教头表业务ID")
    private String userTeacherQuestionId;
    @ApiModelProperty(value = "关注者用户业务ID")
    private String userId;
    @ApiModelProperty(value = "教头用户业务ID")
    private String teacherId;
    @ApiModelProperty(value = "教头回复")
    private String  reply;
    @ApiModelProperty(value = "教头用户头像")
    private String photo;

    @ApiModelProperty(value = "名优教头图片展示(长方形图片345*200px）")
    private String rectanglePhoto;

    @ApiModelProperty(value = "教头用户名称")
    private String userName;

    @ApiModelProperty(value = "教头登录名称")
    private String loginName;

    @ApiModelProperty(value = "教头用户昵称")
    private String nickName;

    @ApiModelProperty(value = "教头真实姓名")
    private String realName;

    @ApiModelProperty(value = "教头用户签名")
    private String signature;

    @ApiModelProperty(value = "用户身份 1:普通用户2：会员用户")
    private Integer userIdentity;

    @ApiModelProperty(value = "教头回复时间")
    private Long replyTime;

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Long replyTime) {
        this.replyTime = replyTime;
    }

    public String getRectanglePhoto() {
        return rectanglePhoto;
    }

    public void setRectanglePhoto(String rectanglePhoto) {
        this.rectanglePhoto = rectanglePhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public UserTeacherQuestionReply(){

    }
    public UserTeacherQuestionReply(String userId, String teacherId){
        this.userId = userId;
        this.teacherId = teacherId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserTeacherQuestionId() {
        return userTeacherQuestionId;
    }

    public void setUserTeacherQuestionId(String userTeacherQuestionId) {
        this.userTeacherQuestionId = userTeacherQuestionId;
    }

    public String getUserTeacherQuestionReplyId() {
        return userTeacherQuestionReplyId;
    }

    public void setUserTeacherQuestionReplyId(String userTeacherQuestionReplyId) {
        this.userTeacherQuestionReplyId = userTeacherQuestionReplyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
