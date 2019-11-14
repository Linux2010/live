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
 * desc：音频课程控制器类
 */
@Controller
@RequestMapping(value = "/courseYp")
public class CourseYpController {

    @Autowired
    private ICourseService courseService;

    /**
     * 进入音频课程首页
     *
     * @return
     */
    @RequestMapping(value = "/courseYpIndex")
    public String courseIndex(){
        return "/course/course_yp_index";
    }

    /**
     * 进入音频课程发布页面
     *
     * @return
     */
    @RequestMapping(value = "/courseYpAdd")
    public String courseAdd(){
        return "/course/course_yp_add";
    }

    /**
     * 进入音频课程修改页面
     *
     * @return
     */
    @RequestMapping(value = "/courseYpEdit")
    public ModelAndView courseYpEdit(String courseId){
        ModelAndView view = new ModelAndView("/course/course_yp_edit");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 分页查询音频课程
     *
     * @param page
     * @param rows
     * @param courseTitle
     * @return
     */
    @RequestMapping(value = "/searchCList")
    @ResponseBody
    public PageInfo<Course> searchCList(int page, int rows, String courseTitle){
        String typeVal = "ypkc";// 音频课程的标记
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
        view.addObject("menuIdVal","50");// 菜单类型ID
        view.addObject("cType","ypkc");// 课程类型
        view.addObject("courseId",courseId);// 课程ID
        return view;
    }

}