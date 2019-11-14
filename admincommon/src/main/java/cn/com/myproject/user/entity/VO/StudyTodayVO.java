package cn.com.myproject.user.entity.VO;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.user.entity.PO.GoodArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jyp on 2017/8/31 0031.
 */
@ApiModel(value = "StudyTodayVO", description = "学习-今日")
public class StudyTodayVO {

    @ApiModelProperty(value = "优质课程")
   private List<Course> excecourlist;
    @ApiModelProperty(value = "今日课程")
    private List<Course> todayrecomlist;
    @ApiModelProperty(value = "好文推荐")
   private List<GoodArticle> goodArticleList;
    @ApiModelProperty(value = "今日学习")
   private List<Course> courseOrderList;

    public List<GoodArticle> getGoodArticleList() {
        return goodArticleList;
    }

    public void setGoodArticleList(List<GoodArticle> goodArticleList) {
        this.goodArticleList = goodArticleList;
    }

    public List<Course> getCourseOrderList() {
        return courseOrderList;
    }

    public void setCourseOrderList(List<Course> courseOrderList) {
        this.courseOrderList = courseOrderList;
    }

    public List<Course> getExcecourlist() {
        return excecourlist;
    }

    public void setExcecourlist(List<Course> excecourlist) {
        this.excecourlist = excecourlist;
    }

    public List<Course> getTodayrecomlist() {
        return todayrecomlist;
    }

    public void setTodayrecomlist(List<Course> todayrecomlist) {
        this.todayrecomlist = todayrecomlist;
    }
}
