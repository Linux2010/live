package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

import java.math.BigDecimal;

public class CourseFinishRecord extends BasePO{

    private String cfrId;

    private String courseId;

    private String userId;

    private Integer pauseDuration;

    private Integer duration;

    private Short recordStatus;

    public String getCfrId() {
        return cfrId;
    }

    public void setCfrId(String cfrId) {
        this.cfrId = cfrId;
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

    public Integer getPauseDuration() {
        return pauseDuration;
    }

    public void setPauseDuration(Integer pauseDuration) {
        this.pauseDuration = pauseDuration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Short getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Short recordStatus) {
        this.recordStatus = recordStatus;
    }
}