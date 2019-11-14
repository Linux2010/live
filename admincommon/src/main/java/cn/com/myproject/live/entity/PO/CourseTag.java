package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签
 */
public class CourseTag extends BasePO{

    // 课程标签ID
    private String courseTagId;

    // 课程标签名称
    private String name;

    public String getCourseTagId() {
        return courseTagId;
    }

    public void setCourseTagId(String courseTagId) {
        this.courseTagId = courseTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}