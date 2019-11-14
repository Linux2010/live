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
 * desc：江湖大课控制器类
 */
@Controller
@RequestMapping(value = "/courseJhdk")
public class CourseJhdkController {

    @Autowired
    private ICourseService courseService;

    /**
     * 进入江湖大课首页
     *
     * @return
     */
    @RequestMapping(value = "/courseJhdkIndex")
    public String courseIndex(){
        return "/course/course_jhdk_index";
    }

    /**
     * 进入江湖大课发布页面
     *
     * @return
     */
    @RequestMapping(value = "/courseJhdkAdd")
    public String courseAdd(){
        return "/course/course_jhdk_add";
    }

    /**
     * 进入江湖大课修改页面
     *
     * @return
     */
    @RequestMapping(value = "/courseJhdkEdit")
    public ModelAndView courseJhdkbEdit(String courseId){
        ModelAndView view = new ModelAndView("/course/course_jhdk_edit");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 进入江湖大课内容查看页面
     *
     * @return
     */
    @RequestMapping(value = "/courseJhdkDetail")
    public ModelAndView courseJhdkDetail(String courseId){
        ModelAndView view = new ModelAndView("/course/course_jhdk_detail");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 进入江湖大课内容上传页面
     *
     * @return
     */
    @RequestMapping(value = "/courseJhdkDetailUpload")
    public ModelAndView courseWzDetailUpload(String courseId){
        ModelAndView view = new ModelAndView("/course/course_jhdk_detail_upload");
        view.addObject("courseId",courseId);
        return view;
    }

    /**
     * 分页查询江湖大课
     *
     * @param page
     * @param rows
     * @param courseTitle
     * @return
     */
    @RequestMapping(value = "/searchCList")
    @ResponseBody
    public PageInfo<Course> searchCList(int page, int rows, String courseTitle){
        String typeVal = "jhdk";// 江湖大课的标记
        return courseService.searchCourseList(page,rows,typeVal,courseTitle,"");
    }

}