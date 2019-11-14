package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

import java.util.Date;

/**
 * Created by JYP on 2017/8/14 0014.
 */
public class CommentReply extends BasePO{

    private String courseReplyId;
    private String courseCommentId;
    private String replayContent;
    private String replayUserId;
    private Long replayTime;

    private CourseComment comm;

    public CourseComment getComm() {
        return comm;
    }

    public void setComm(CourseComment comm) {
        this.comm = comm;
    }


    public String getCourseReplyId() {
        return courseReplyId;
    }

    public void setCourseReplyId(String courseReplyId) {
        this.courseReplyId = courseReplyId;
    }

    public String getCourseCommentId() {
        return courseCommentId;
    }

    public void setCourseCommentId(String courseCommentId) {
        this.courseCommentId = courseCommentId;
    }

    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent;
    }

    public String getReplayUserId() {
        return replayUserId;
    }

    public void setReplayUserId(String replayUserId) {
        this.replayUserId = replayUserId;
    }

    public Long getReplayTime() {
        return replayTime;
    }

    public void setReplayTime(Long replayTime) {
        this.replayTime = replayTime;
    }
}
