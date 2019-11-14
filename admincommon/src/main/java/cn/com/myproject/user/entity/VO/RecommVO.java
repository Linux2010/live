package cn.com.myproject.user.entity.VO;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.PO.User;

import java.util.List;

/**
 * Created by jyp on 2017/8/31 0031.
 */
public class RecommVO {

    //精英教头
    private List<User> teacherList;
    //优质课程
    private List<Course> recommCourList;
    //优选商品
    List<Course> goodselectCourList;
    public List<User> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<User> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Course> getRecommCourList() {
        return recommCourList;
    }

    public void setRecommCourList(List<Course> recommCourList) {
        this.recommCourList = recommCourList;
    }

    public List<Course> getGoodselectCourList() {
        return goodselectCourList;
    }

    public void setGoodselectCourList(List<Course> goodselectCourList) {
        this.goodselectCourList = goodselectCourList;
    }
}
