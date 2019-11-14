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
 * Created by pangdatao on 2017-08-22
 * desc：直播课程控制器类
 */
@Controller
@RequestMapping(value = "/courseZb")
public class CourseZbController {

    @Autowired
    private ICourseService courseService;

    /**
     * 进入直播课程首页
     *
     * @return
     */
    @RequestMapping(value = "/courseZbIndex")
    public String courseIndex(){
        return "/course/course_zb_index";
    }

    /**
     * 进入直播课程发布页面
     *
     * @return
     */
    @RequestMapping(value = "/courseZbAdd")
    public String courseAdd(){
        return "/course/course_zb_add";
    }

    /**
     * 进入直播课程修改页面
     *
     * @return
     */
    @RequestMapping(value = "/courseZbEdit")
    public ModelAndView courseZbEdit(String courseId){
        ModelAndView view = new ModelAndView("/course/course_zb_edit");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 分页查询直播课程
     *
     * @param page
     * @param rows
     * @param courseTitle
     * @return
     */
    @RequestMapping(value = "/searchCList")
    @ResponseBody
    public PageInfo<Course> searchCList(int page, int rows, String courseTitle, String zbType){
        String typeVal = "zbkc";// 直播课程的标记
        return courseService.searchCourseList(page,rows,typeVal,courseTitle,zbType);
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
        view.addObject("menuIdVal","51");// 菜单类型ID
        view.addObject("cType","zbkc");// 课程类型
        view.addObject("courseId",courseId);// 课程ID
        return view;
    }

}