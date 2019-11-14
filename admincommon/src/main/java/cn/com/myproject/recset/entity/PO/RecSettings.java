package cn.com.myproject.recset.entity.PO;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseTag;
import cn.com.myproject.live.entity.PO.CourseType;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.util.BasePO;
import sun.print.BackgroundServiceLookup;

/**
 * @Title推荐设置参数
 * Created by JYP on 2017/8/17 0017.
 */
public class RecSettings extends BasePO{

    private String recSetId;
    private String recSetObjId;
    private Integer recSort;
    /**
     * 1:教头 2:课程 3:商品
     */
    private Integer recLabType;
    //推荐设置参数外键
    private String recLabId;

    private User user;
    private Course course;
    private CourseType courseType;
    private CourseTag courseTag;

    public String getRecSetId() {
        return recSetId;
    }

    public void setRecSetId(String recSetId) {
        this.recSetId = recSetId;
    }

    public String getRecSetObjId() {
        return recSetObjId;
    }

    public void setRecSetObjId(String recSetObjId) {
        this.recSetObjId = recSetObjId;
    }

    public Integer getRecSort() {
        return recSort;
    }

    public void setRecSort(Integer recSort) {
        this.recSort = recSort;
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

    public Integer getRecLabType() {
        return recLabType;
    }

    public void setRecLabType(Integer recLabType) {
        this.recLabType = recLabType;
    }

    public String getRecLabId() {
        return recLabId;
    }

    public void setRecLabId(String recLabId) {
        this.recLabId = recLabId;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public CourseTag getCourseTag() {
        return courseTag;
    }

    public void setCourseTag(CourseTag courseTag) {
        this.courseTag = courseTag;
    }

}
