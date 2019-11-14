package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.ICourseTopicService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import cn.com.myproject.live.entity.VO.CourseTopicCourseVO;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyp on 2017/8/28 0028.
 */
@RestController
@RequestMapping("/wap/topic")
@Api(value="考题类",tags = "考题")
public class WXTopicController extends BaseController {
       private static final org.slf4j.Logger logger = LoggerFactory.getLogger("TopicController");

    @Autowired
    private ICourseTopicService courseTopicService;

    @Autowired
    private IUserService userService;


    /**
     * 获取注册考题
     * @return
     */
    @RequestMapping(value = "/getExam", method = RequestMethod.POST)
    public Message<List<CourseTopic>> getExam(){
        CourseTopicExamination RegisterExamMeaaget = courseTopicService.getRegisterExamMessage();
        List<CourseTopic> list = courseTopicService.getExam(RegisterExamMeaaget.getCourseTopicExaminationId());
       if(list != null){
           Message<List<CourseTopic>> message = MessageUtils.getSuccess("考题获取成功");
           message.setData(list);
           return  message;
       }else{
           return MessageUtils.getFail("获取注册考题失败");
       }
    }
   /* *//*
    * 记录用户答案，匹配标签*//*
    @RequestMapping(value = "/userAnswer", method = RequestMethod.POST)
    public Message userAnswer(CourseTopicUserAnswer courseTopicUserAnswer){
       courseTopicService.insertanswer(courseTopicUserAnswer);
       return MessageUtils.getSuccess("添加记录成功");
    }*/

    /**
     * 课程考题查询
     * @param courseId
     * @return
     */
    @PostMapping("/courseTopics")
    public Message<List<CourseTopic>>  courseTopics( String courseId){
        List<CourseTopic> courseTopicList = null;
        try{
            courseTopicList =courseTopicService.searchCourseTopicsByCourseId(courseId);
        }catch(Exception e){
            logger.error("课程考题查询异常courseTopicService.searchCourseTopicsByCourseId(courseId)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message<List<CourseTopic>> message = MessageUtils.getSuccess("success");
        message.setData(courseTopicList);
        message.setResult(1);
        return message;
    }

    /**
     * 课程考题-提交课程考题答案
     * @param courseTopicExaminationId 考题试卷业务ID
     * @param userId 提交考题答案的用户ID
     * @param answerJsonStr 用户提交的考题答案json字符串
     * @return
     */
    @PostMapping("/submitCourseTopicsAnswer")
    public Message  submitCourseTopicsAnswer(String courseTopicExaminationId,String userId, String answerJsonStr){
       int courseTopicAnswerInsert = 0;
        try{
            CourseTopicExamination examination = new CourseTopicExamination();
            examination.setCourseTopicExaminationId(courseTopicExaminationId);
            examination.setUserId(userId);
            List<CourseTopicUserAnswer> answerList = examination.getAnswerList();
            answerJsonStr =answerJsonStr.replace("\\n","");
            answerList = JSONArray.parseArray(answerJsonStr, CourseTopicUserAnswer.class);
            examination.setAnswerList(answerList);
            courseTopicAnswerInsert =courseTopicService.submitCourseTopicsAnswer(examination);
        }catch(Exception e){
            logger.error("课程考题-提交课程考题答案异常courseTopicService.submitCourseTopicsAnswer(examination)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        if(courseTopicAnswerInsert==1){
            Message message = MessageUtils.getSuccess("success");
            message.setData(new ArrayList<>());
            message.setResult(1);
            return message;
        }else{
            Message message = MessageUtils.getFail("fail");
            message.setData(new ArrayList<>());
            return message;
        }
    }

    /**
     * 我的答题课程页面
     * @return
     */
    @RequestMapping("/my/myTopicsCourse")
    public ModelAndView myTopicsCourse(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/my/myTopicsCourse");
        User currUser = getCurrUser();
        if(currUser != null){
            User user = userService.selectById(currUser.getUserId());
            view.addObject("userId",currUser.getUserId());
            view.addObject("userIdentity",user.getUserIdentity());
        }
        return view;
    }
    /**
     * 我的答题-我的课程列表
     * @param pageNum
     * @param pageSize
     * @param type 课程类别0：我做过考题，1：我没做过考题
     * @return
     */
    @PostMapping("/myTopicsCourseList")
    public Message<List<CourseTopicCourseVO>>  myTopicsCourseList(String pageNum, String pageSize,String type,HttpServletRequest request){
        String userId=""; //答题用户ID
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        List<CourseTopicCourseVO> courseList = null;
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        try{
            if("0".equals(type)){
                courseList =courseTopicService.getUserTopicCourseList(pageNumVal,pageSizeVal,userId);
            }else if("1".equals(type)){
                courseList =courseTopicService.getUserTopicCourseNoAnswerTheTopicsList(pageNumVal,pageSizeVal,userId);
            }
        }catch(Exception e){
            logger.error("我的答题-我的课程列表courseTopicService.getUserTopicCourseList(pageNumVal,pageSizeVal,userId)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message<List<CourseTopicCourseVO>> message = MessageUtils.getSuccess("success");
        message.setData(courseList);
        message.setResult(1);
        return message;
    }

    /**
     *  我的答题页面
     * @param request
     * @param type type 课程类别0：我做过考题，1：我没做过考题
     * @return
     */
    @RequestMapping("/my/myTopic")
    public ModelAndView myTopic(HttpServletRequest request,String type,String courseTopicExaminationId) {
        ModelAndView view = null;
        if(type.equals("0")){
            view = new ModelAndView("/my/myTopic");
        }else if(type.equals("1")){
            view = new ModelAndView("/my/subMyTopic");
        }
        view.addObject("type",type);
        view.addObject("courseTopicExaminationId",courseTopicExaminationId);
        return view;
    }

    /**
     * 我的答题-查看答题
     * @param courseTopicExaminationId  考卷业务ID
     * @return
     */
    @PostMapping("/myTopicsAnswers")
    public Message<List<CourseTopic>>  myTopicsAnswers(String courseTopicExaminationId){
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
        List<CourseTopic> courseTopicsAnswerList = null;
        try{
            courseTopicsAnswerList =courseTopicService.selectUserCourseTopicsAndChooseAnswers(new CourseTopic(userId,courseTopicExaminationId));
            if(courseTopicsAnswerList != null && courseTopicsAnswerList.size() >0){
                for(CourseTopic topic:courseTopicsAnswerList){
                    String rightAnswer = topic.getRightAnswer();
                    String userChosseAnswer = topic.getUserChooseAnswer();
                    if(StringUtils.isNoneBlank(topicAnswerIsRight( rightAnswer , userChosseAnswer)) && StringUtils.isNotEmpty(topicAnswerIsRight( rightAnswer , userChosseAnswer))){
                        topic.setAnswerRate(topicAnswerIsRight( rightAnswer , userChosseAnswer));
                    }else{
                        topic.setAnswerRate("答题率：" + topic.getAnswerRate());
                    }
                }
            }
        }catch(Exception e){
            logger.error("我的答题-查看答题courseTopicService.selectUserCourseTopicsAndChooseAnswers(new CourseTopic(userId,courseTopicExaminationId))");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message<List<CourseTopic>> message = MessageUtils.getSuccess("success");
        message.setData(courseTopicsAnswerList);
        message.setResult(1);
        return message;
    }

    /**
     * 绝对性考题判断正确错误
     * @param rightAnswer
     * @param userChosseAnswer
     * @return
     */
    public String topicAnswerIsRight(String rightAnswer ,String userChosseAnswer){
        String[] rightAnswers={};
        String[] userChooseAnswers={};
        if(StringUtils.isNotBlank(rightAnswer) && StringUtils.isNotEmpty(rightAnswer)){
            rightAnswers = rightAnswer.split(",");
        }
        if(StringUtils.isNotBlank(userChosseAnswer) && StringUtils.isNotEmpty(userChosseAnswer)){
            userChooseAnswers = userChosseAnswer.split(",");
        }
        if(rightAnswers.length == 1 && userChooseAnswers.length == 1){ //
           if(StringUtils.isNotBlank(rightAnswers[0]) &&StringUtils.isNotEmpty(rightAnswers[0]) &&StringUtils.isNoneBlank(userChooseAnswers[0]) &&StringUtils.isNotEmpty(userChooseAnswers[0]) ){
               if( rightAnswers[0].equals(userChooseAnswers[0])){
                    return "对";
                }else{
                   return "错";
                }
           }
        }else{
            return "";
        }
        return "";
    }
}
