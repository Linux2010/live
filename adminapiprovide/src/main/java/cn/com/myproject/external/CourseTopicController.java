package cn.com.myproject.external;

import cn.com.myproject.course.service.ICourseTopicExaminationService;
import cn.com.myproject.course.service.ICourseTopicService;
import cn.com.myproject.course.service.ICourseTopicUserAnswerService;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import cn.com.myproject.live.entity.VO.CourseRegisterExamVO;
import cn.com.myproject.live.entity.VO.CourseTopicCourseVO;
import cn.com.myproject.live.entity.VO.CourseTopicExaminationVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/15 0015.
 * @title 处理课程考卷与注册考题相关业务
 */
@RestController
@RequestMapping("/courseTopic")
public class CourseTopicController {

      @Autowired
      ICourseTopicService iCourseTopicService;

      @Autowired
      private ICourseTopicExaminationService courseTopicExaminationService;

      @Autowired
      private ICourseTopicUserAnswerService courseTopicUserAnswerService;

      @PostMapping("/getPageCourseTopicExaminations")
      public PageInfo<CourseTopicExaminationVO> getPage(Integer pageNum, Integer pageSize, @RequestBody CourseTopicExaminationVO searchVo) {
            PageInfo<CourseTopicExaminationVO> list = iCourseTopicService.getPageCourseTopicExaminations(pageNum,pageSize,searchVo);
            return list;
      }

      @PostMapping("/getStudyLabels")
      public List<Map<String ,Object>> getStudyLabels(@RequestBody Map<String,Object> map) {
            return  iCourseTopicService.getStudyLabels(map);
      }

      @PostMapping("/insertTopicExamination")
      public  int insertTopicExamination(@RequestBody Map<String,Object> map){
            return iCourseTopicService.insertTopicExamination(map);
      }

      @PostMapping("/insertTopic")
      public int insertTopic(@RequestBody Map<String, Object> map){
            return iCourseTopicService.insertTopic(map);
      }

      @PostMapping("/getCourseListByCourseTypeId")
      public List<Map<String ,Object>>  getCourseListByCourseTypeId(String courseTypeId){
            return iCourseTopicService.getCourseListByCourseTypeId(courseTypeId);
      }

      @PostMapping("/delCourseTopicExam")
      public  int delCourseTopicExam( String course_topic_examination_id ){
            return iCourseTopicService.delCourseTopicExam(course_topic_examination_id);
      }

      @PostMapping("/insertTopics")
      public int insertTopics(String data,String userId){
            return iCourseTopicService.insertTopics(data,userId);
      }

      @PostMapping("/updateTopics")
      public int updateTopics(String data ,String userId){
            return iCourseTopicService.updateTopics(data,userId);
      }

      @PostMapping("/getExamAndTopicsInfoByCourseTopicExamId")
      public CourseTopicExaminationVO getExamAndTopicsInfoByCourseTopicExamId(String courseTopicExaminationId){
            return iCourseTopicService.getExamAndTopicsInfoByCourseTopicExamId(courseTopicExaminationId);
      }
      @PostMapping("/selectTopicsByCourseTopicCourseId")
      public CourseTopicExamination selectTopicsByCourseTopicCourseId(String courseId){
            return iCourseTopicService.selectTopicsByCourseTopicCourseId(courseId);
      }
      @PostMapping("/selectRegisterCourseTopicExamination")
      public  Map<String ,Object> selectRegisterCourseTopicExamination(){
            return iCourseTopicService.selectRegisterCourseTopicExamination();
      }

      @PostMapping("/getRegisterById")
      public CourseRegisterExamVO getRegisterById(String courseTopicExaminationId){
            return courseTopicExaminationService.getRegisterById(courseTopicExaminationId);
      }

      @PostMapping("/getExam")
      public List<CourseTopic> getExam(String courseTopicExaminationId){
            return iCourseTopicService.getExam(courseTopicExaminationId);
      }

      @PostMapping("/insertanswer")
      public void insertanswer(@RequestBody  CourseTopicUserAnswer courseTopicUserAnswer){
            courseTopicUserAnswerService.userAnswer(courseTopicUserAnswer);
      }

      @PostMapping("/searchCourseTopicsByCourseId")
      public List<CourseTopic> searchCourseTopicsByCourseId(@RequestParam("courseId") String courseId){
            return iCourseTopicService.searchCourseTopicsByCourseId(courseId);
      }

      @PostMapping("/submitCourseTopicsAnswer")
      public int  submitCourseTopicsAnswer(@RequestBody CourseTopicExamination examination){
            return iCourseTopicService.submitCourseTopicsAnswer(examination);
      }

      @PostMapping("/getUserTopicCourseList")
      public List<CourseTopicCourseVO> getUserTopicCourseList(int pageNum,int pageSize,String userId){
            return iCourseTopicService.getUserTopicCourseList(pageNum,pageSize,userId);
      }

      @PostMapping("/getUserTopicCourseNoAnswerTheTopicsList")
      public  List<CourseTopicCourseVO>  getUserTopicCourseNoAnswerTheTopicsList(int pageNum,int pageSize,String userId){
            return iCourseTopicService.getUserTopicCourseNoAnswerTheTopicsList(pageNum,pageSize,userId);
      }

      @PostMapping("/selectUserCourseTopicsAndChooseAnswers")
      public  List<CourseTopic> selectUserCourseTopicsAndChooseAnswers(@RequestBody CourseTopic topic){
            return iCourseTopicService.selectUserCourseTopicsAndChooseAnswers(topic);
      }

      @GetMapping("/getRegisterExamMessage")
      public CourseTopicExamination getRegisterExamMessage(){
            return  courseTopicExaminationService.getRegisterExamMessage();
      }

      @PostMapping("/selectAnswerByUserId")
      public List<CourseTopicUserAnswer> selectAnswerByUserId(String userId){

            return courseTopicUserAnswerService.selectAnswerByUserId(userId);
      }

      @PostMapping("/selectRegisterAnswerById")
      public CourseTopic selectRegisterAnswerById(String courseTopicId, String courseTopicExaminationId){

            return iCourseTopicService.selectRegisterAnswerById(courseTopicId, courseTopicExaminationId);
      }

      @PostMapping("selectTopicNoAnswer")
      public CourseTopicUserAnswer selectTopicNoAnswer(@RequestBody CourseTopicUserAnswer courseTopicUserAnswer){
            return  courseTopicUserAnswerService.selectTopicNoAnswer(courseTopicUserAnswer);
      }
      @PostMapping("selectTopicLable")
      public CourseTopic selectTopicLable (@RequestBody CourseTopic courseTopic){
            return iCourseTopicService.selectTopicLable(courseTopic);
      }

}
