package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeiJia on 2017/8/29 0029.
 */
@ApiModel(value = "UserTeacherQuestion", description = "用户提问教头表")
public class UserTeacherQuestion extends BasePO {

    @ApiModelProperty(value = "用户提问教头表业务ID")
    private String userTeacherQuestionId;

    @ApiModelProperty(value = "提问者用户业务ID")
    private String userId;

    @ApiModelProperty(value = "教头用户业务ID")
    private String teacherId;

    @ApiModelProperty(value = "提问者提问问题")
    private String  question;

    @ApiModelProperty(value = "提问者用户头像")
    private String photo;

    @ApiModelProperty(value = "提问者用户真实姓名")
    private String realName;

    @ApiModelProperty(value = "提问者用户名称")
    private String userName;

    @ApiModelProperty(value = "提问者登录名称")
    private String loginName;

    @ApiModelProperty(value = "提问者用户昵称")
    private String nickName;

    @ApiModelProperty(value = "提问者用户签名")
    private String signature;

    @ApiModelProperty(value = "用户身份 1:普通用户2：会员用户")
    private Integer userIdentity;

    @ApiModelProperty(value = "提问时间")
    private Long questionTime;

    @ApiModelProperty(value = "回复列表")
    private List<UserTeacherQuestionReply> replyList = new ArrayList<UserTeacherQuestionReply>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Long getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Long questionTime) {
        this.questionTime = questionTime;
    }

    public UserTeacherQuestion(){

    }
    public UserTeacherQuestion(String userId, String teacherId){
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

    public List<UserTeacherQuestionReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<UserTeacherQuestionReply> replyList) {
        this.replyList = replyList;
    }

    public String getUserTeacherQuestionId() {
        return userTeacherQuestionId;
    }

    public void setUserTeacherQuestionId(String userTeacherQuestionId) {
        this.userTeacherQuestionId = userTeacherQuestionId;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
}
