package cn.com.myproject.api.service;

/**
 * Created by jyp on 2017/8/28 0028.
 */

import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import cn.com.myproject.live.entity.VO.CourseTopicCourseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseTopicService {

    @PostMapping("/courseTopic/getExam")
    public List<CourseTopic> getExam(@RequestParam("courseTopicExaminationId") String courseTopicExaminationId);

    @PostMapping("/courseTopic/insertanswer")
    public void insertanswer(@RequestBody CourseTopicUserAnswer courseTopicUserAnswer);

    @PostMapping("/courseTopic/searchCourseTopicsByCourseId")
    public List<CourseTopic> searchCourseTopicsByCourseId(@RequestParam("courseId") String courseId);

    @PostMapping("/courseTopic/submitCourseTopicsAnswer")
    public int  submitCourseTopicsAnswer(@RequestBody CourseTopicExamination examination);

    @PostMapping("/courseTopic/getUserTopicCourseList")
    public List<CourseTopicCourseVO> getUserTopicCourseList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestParam("userId") String userId);

    @PostMapping("/courseTopic/getUserTopicCourseNoAnswerTheTopicsList")
    public List<CourseTopicCourseVO> getUserTopicCourseNoAnswerTheTopicsList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestParam("userId") String userId);

    @PostMapping("/courseTopic/selectUserCourseTopicsAndChooseAnswers")
    public  List<CourseTopic> selectUserCourseTopicsAndChooseAnswers(@RequestBody CourseTopic topic);

    @GetMapping("/courseTopic/getRegisterExamMessage")
    public CourseTopicExamination getRegisterExamMessage();

    @PostMapping("/courseTopic/selectAnswerByUserId")
    public List<CourseTopicUserAnswer> selectAnswerByUserId(@RequestParam("userId") String userId);

    @PostMapping("/courseTopic/selectRegisterAnswerById")
    CourseTopic selectRegisterAnswerById(@RequestParam("courseTopicId") String courseTopicId, @RequestParam("courseTopicExaminationId") String courseTopicExaminationId);

    @PostMapping("/courseTopic/selectTopicNoAnswer")
    CourseTopicUserAnswer selectTopicNoAnswer (@RequestBody CourseTopicUserAnswer courseTopicUserAnswer);

    @PostMapping("/courseTopic/selectTopicLable")
    CourseTopic selectTopicLable (@RequestBody CourseTopic courseTopic);
}
