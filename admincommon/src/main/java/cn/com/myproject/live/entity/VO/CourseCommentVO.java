package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-29
 * desc：课程评论
 */
@ApiModel(value = "CourseCommentVO", description = "课程评论")
public class CourseCommentVO {

    @ApiModelProperty(value = "课程评论ID")
    private String courseCommentId;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "课程评论内容")
    private String commentContent;

    @ApiModelProperty(value = "课程评论用户ID")
    private String userId;

    @ApiModelProperty(value = "课程评论时间")
    private Long commentTime;

    @ApiModelProperty(value = "课程评论用户名称")
    private String accname;

    @ApiModelProperty(value = "讲师头像")
    private String photo;

    @ApiModelProperty(value = "讲师签名")
    private String signature;

    @ApiModelProperty(value = "用户身份1:普通用户2：会员用户")
    private int userIdentity;

    // 评论级别：1是好评，2是差评
    @ApiModelProperty(value = "评论级别：1是好评，2是差评")
    private int commentLevel;

    private List<CourseReplyVO> crList;

    public String getCourseCommentId() {
        return courseCommentId;
    }

    public void setCourseCommentId(String courseCommentId) {
        this.courseCommentId = courseCommentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public List<CourseReplyVO> getCrList() {
        return crList;
    }

    public void setCrList(List<CourseReplyVO> crList) {
        this.crList = crList;
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

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }
}