package cn.com.myproject.live.entity.PO;

import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
@ApiModel(value = "CourseComment", description = "课程评论")
public class CourseComment extends BasePO{

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

    private User user;
    private Course course;

    @ApiModelProperty(value = "课程评论用户名称")
    private String accname;

    @ApiModelProperty(value = "课程讲师名称")
    private String teaname;

    @ApiModelProperty(value = "课程名称")
    private String cctitle;

    private String hcontent;
    private Long htime;

    // 评论级别：1是好评，2是差评
    @ApiModelProperty(value = "评论级别：1是好评，2是差评")
    private int commentLevel;

    public String getHcontent() {
        return hcontent;
    }

    public void setHcontent(String hcontent) {
        this.hcontent = hcontent;
    }

    public Long getHtime() {
        return htime;
    }

    public void setHtime(Long htime) {
        this.htime = htime;
    }

    public String getCctitle() {
        return cctitle;
    }

    public void setCctitle(String cctitle) {
        this.cctitle = cctitle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

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

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }
}