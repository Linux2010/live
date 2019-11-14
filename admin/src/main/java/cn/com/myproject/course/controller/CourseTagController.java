package cn.com.myproject.course.controller;

import cn.com.myproject.live.entity.PO.CourseTag;
import cn.com.myproject.service.ICourseTagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签控制器类
 */
@Controller
@RequestMapping("/courseTag")
public class CourseTagController {

    @Autowired
    private ICourseTagService courseTagService;

    /**
     * 进入课程标签首页
     *
     * @return
     */
    @RequestMapping("/courseTagIndex")
    public String courseTagIndex(){
        return "/course/course_tag_index";
    }

    /**
     * 添加课程标签
     *
     * @param ct
     * @return
     */
    @RequestMapping("/addCt")
    @ResponseBody
    public String addCt(CourseTag ct){
        String result = "1";
        boolean flagVal = courseTagService.addCourseTag(ct);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 根据courseTagId删除课程标签
     *
     * @param courseTagId
     * @return
     */
    @RequestMapping("/removeCt")
    @ResponseBody
    public String removeCt(String courseTagId){
        String result = "1";
        boolean flagVal = courseTagService.removeCourseTag(courseTagId);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 修改课程标签
     *
     * @param ct
     * @return
     */
    @RequestMapping("/modifyCt")
    @ResponseBody
    public String modifyCt(CourseTag ct){
        String result = "1";
        boolean flagVal = courseTagService.modifyCourseTag(ct);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 分页查询课程标签
     *
     * @param page
     * @param rows
     * @param name
     * @return
     */
    @RequestMapping("/searchCtList")
    @ResponseBody
    public PageInfo<CourseTag> searchCtList(int page,int rows,String name){
        return courseTagService.searchCtList(page,rows,name);
    }

    /**
     * 查询课程标签列表
     *
     * @return
     */
    @RequestMapping("/searchCourseTagList")
    @ResponseBody
    public List<CourseTag> searchCourseTagList(){
        return courseTagService.searchCourseTagList();
    }

}