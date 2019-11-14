package cn.com.myproject.course.controller;

import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.VO.CourseTopicExaminationVO;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.ICourseTopicService;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by LeiJia on 2017/8/15 0015.
 * @title 处理注册考卷与考题业务
 */
@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    ICourseTopicService iCourseTopicService;

    @RequestMapping("/course/add")
    public String course_add(String courseId,String courseName,String cType,Model model) {
        model.addAttribute("courseId",courseId);
        model.addAttribute("courseName",courseName);
       switch (cType){
           case "spkc" :
               model.addAttribute("redirectUrl","courseSp/courseSpIndex");
               break;
           case "wzkc":
               model.addAttribute("redirectUrl","courseWz/courseWzIndex");
               break;
           case "ypkc":
               model.addAttribute("redirectUrl","courseYp/courseYpIndex");
               break;
           case "zbkc":
               model.addAttribute("redirectUrl","courseZb/courseZbIndex");
               break;
           default:
               model.addAttribute("redirectUrl","courseSp/courseSpIndex");
               break;
       }
        return "topic/course_add_topic_index";
    }

    @RequestMapping("/course")
    public String course() {
        return "topic/course_topic_index";
    }
    @RequestMapping("/register")
    public String register() {
        return "topic/register_topic_index";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<CourseTopicExaminationVO> getPage(HttpServletRequest request, Integer rows, Integer page, CourseTopicExaminationVO searchVo) {
        String  keyword =searchVo.getKeyword();
        try{
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
                keyword = URLDecoder.decode(keyword, "UTF-8");
                searchVo.setKeyword(keyword);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        PageInfo<CourseTopicExaminationVO> list = iCourseTopicService.getPageCourseTopicExaminations(page,rows,searchVo);
        return list;
    }

    @ResponseBody
    @RequestMapping("/getStudyLabels")
    public List<Map<String ,Object>> getStudyLabels( Map<String,Object> map) {
        return  iCourseTopicService.getStudyLabels(map);
    }
    @ResponseBody
    @RequestMapping("/insertTopics")
    @Transactional
    public int insertTopics(String data,HttpServletRequest request){
        int topicInsertResult =0;
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
            SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
            topicInsertResult = iCourseTopicService.insertTopics(data,user.getUserId());
            if(topicInsertResult == 1){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/updateTopics")
    @Transactional
    public int updateTopics(String data, HttpServletRequest request){
        int topicUpdateResult =0;
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
            SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
            topicUpdateResult = iCourseTopicService.updateTopics(data,user.getUserId());
            if(topicUpdateResult == 1){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/getCourseListByCourseTypeId")
    public List<Map<String ,Object>> getCourseListByCourseTypeId(String courseTypeId){
        return iCourseTopicService.getCourseListByCourseTypeId(courseTypeId);
    }
    @ResponseBody
    @RequestMapping("/delCourseTopicExam")
    public  int delCourseTopicExam(String courseTopicExaminationId ){
        int result =  iCourseTopicService.delCourseTopicExam(courseTopicExaminationId);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    @ResponseBody
    @RequestMapping("/getExamDetailCourseTopicExamId")
    public String getExamAndTopicsInfoByCourseTopicExamId(String courseTopicExaminationId){
        CourseTopicExaminationVO vo = iCourseTopicService.getExamAndTopicsInfoByCourseTopicExamId(courseTopicExaminationId);
        return new  JSONObject(vo).toString();
    }
    @ResponseBody
    @RequestMapping("/selectRegisterCourseTopicExamination")
    public  Map<String ,Object> selectRegisterCourseTopicExamination(){
        return iCourseTopicService.selectRegisterCourseTopicExamination();
    }

    @ResponseBody
    @RequestMapping("/selectTopicsByCourseTopicCourseId")
    public CourseTopicExamination selectTopicsByCourseTopicCourseId(String courseId){
        return iCourseTopicService.selectTopicsByCourseTopicCourseId(courseId);
    }

}

