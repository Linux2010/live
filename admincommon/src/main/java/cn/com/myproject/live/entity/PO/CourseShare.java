package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-08-31
 * desc：课程分享实体类
 */
public class CourseShare extends BasePO{

    // 课程分享ID
    private String courseShareId;

    // 课程ID
    private String courseId;

    // 分享方式(0:朋友圈;1:微信好友;2：QQ空间;3:QQ好友;4:微博;)
    private int shareType;

    // 分享时间
    private long shareTime;

    // 分享的用户ID
    private String shareUserId;

    public String getCourseShareId() {
        return courseShareId;
    }

    public void setCourseShareId(String courseShareId) {
        this.courseShareId = courseShareId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getShareType() {
        return shareType;
    }

    public void setShareType(int shareType) {
        this.shareType = shareType;
    }

    public long getShareTime() {
        return shareTime;
    }

    public void setShareTime(long shareTime) {
        this.shareTime = shareTime;
    }

    public String getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(String shareUserId) {
        this.shareUserId = shareUserId;
    }
}