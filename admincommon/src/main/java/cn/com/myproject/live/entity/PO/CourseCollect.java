package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-08-29
 * desc：课程收藏实体类
 */
public class CourseCollect extends BasePO{

    // 收藏ID
    private String collectId;

    // 课程ID
    private String courseId;

    // 用户ID
    private String userId;

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
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
}