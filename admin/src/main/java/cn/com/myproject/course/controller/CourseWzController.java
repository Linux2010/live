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
 * Created by pangdatao on 2017-08-21
 * desc：文字课程控制器类
 */
@Controller
@RequestMapping(value = "/courseWz")
public class CourseWzController {

    @Autowired
    private ICourseService courseService;

    /**
     * 进入文字课程首页
     *
     * @return
     */
    @RequestMapping(value = "/courseWzIndex")
    public String courseIndex(){
        return "/course/course_wz_index";
    }

    /**
     * 进入文字课程发布页面
     *
     * @return
     */
    @RequestMapping(value = "/courseWzAdd")
    public String courseAdd(){
        return "/course/course_wz_add";
    }

    /**
     * 进入文字课程修改页面
     *
     * @return
     */
    @RequestMapping(value = "/courseWzEdit")
    public ModelAndView courseWzEdit(String courseId){
        ModelAndView view = new ModelAndView("/course/course_wz_edit");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 进入文字课程内容查看页面
     *
     * @return
     */
    @RequestMapping(value = "/courseWzDetail")
    public ModelAndView courseWzDetail(String courseId){
        ModelAndView view = new ModelAndView("/course/course_wz_detail");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 进入文字课程内容上传页面
     *
     * @return
     */
    @RequestMapping(value = "/courseWzDetailUpload")
    public ModelAndView courseWzDetailUpload(String courseId){
        ModelAndView view = new ModelAndView("/course/course_wz_detail_upload");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 上传文字课程内容
     *
     * @param c
     * @return
     */
    @RequestMapping(value = "/modifyCourseContent")
    @ResponseBody
    public String modifyCourseContent(Course c){
        String result = "1";
        boolean flagVal = courseService.modifyCourseContent(c);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 根据courseId查询课程内容
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/searchCourseContentById")
    @ResponseBody
    public Course searchCourseContentById(String courseId){
        return courseService.searchCourseContentById(courseId);
    }

    /**
     * 分页查询文字课程
     *
     * @param page
     * @param rows
     * @param courseTitle
     * @return
     */
    @RequestMapping(value = "/searchCList")
    @ResponseBody
    public PageInfo<Course> searchCList(int page, int rows, String courseTitle){
        String typeVal = "wzkc";// 文字课程的标记
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
        view.addObject("menuIdVal","49");// 菜单类型ID
        view.addObject("cType","wzkc");// 课程类型
        view.addObject("courseId",courseId);// 课程ID
        return view;
    }

}