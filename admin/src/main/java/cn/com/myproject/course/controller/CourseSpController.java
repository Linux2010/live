package cn.com.myproject.course.controller;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.service.ICourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 * Created by pangdatao on 2017-08-16
 * desc：视频课程控制器类
 */
@Controller
@RequestMapping(value = "/courseSp")
public class CourseSpController {

    @Autowired
    private ICourseService courseService;

    /**
     * 进入视频课程首页
     *
     * @return
     */
    @RequestMapping(value = "/courseSpIndex")
    public String courseIndex(){
        return "/course/course_sp_index";
    }

    /**
     * 进入视频课程发布页面
     *
     * @return
     */
    @RequestMapping(value = "/courseSpAdd")
    public String courseAdd(){
        return "/course/course_sp_add";
    }

    /**
     * 进入视频课程修改页面
     *
     * @return
     */
    @RequestMapping(value = "/courseSpEdit")
    public ModelAndView courseSpEdit(String courseId){
        ModelAndView view = new ModelAndView("/course/course_sp_edit");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 分页查询视频课程
     *
     * @param page
     * @param rows
     * @param courseTitle
     * @return
     */
    @RequestMapping(value = "/searchCList")
    @ResponseBody
    public PageInfo<Course> searchCList(int page, int rows, String courseTitle){
        String typeVal = "spkc";// 视频课程的标记
        return courseService.searchCourseList(page,rows,typeVal,courseTitle,"");
    }

    /**
     * 查看课程考题
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/showCtDetail")
    public ModelAndView showCtDetail(String courseId){
        ModelAndView view = new ModelAndView("/course/course_topic_detail");
        view.addObject("menuIdVal","48");// 菜单类型ID
        view.addObject("cType","spkc");// 课程类型
        view.addObject("courseId",courseId);// 课程ID
        return view;
    }

}