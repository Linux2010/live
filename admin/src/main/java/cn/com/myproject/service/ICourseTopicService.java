package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.VO.CourseTopicExaminationVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/15 0007.
 */

@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseTopicService {
    //分页获取考试卷列表
    @PostMapping("/courseTopic/getPageCourseTopicExaminations")
    public   PageInfo<CourseTopicExaminationVO> getPageCourseTopicExaminations(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize, @RequestBody CourseTopicExaminationVO vo);

    //学习标签集合
    @PostMapping("/courseTopic/getStudyLabels")
    public List<Map<String ,Object>> getStudyLabels(@RequestBody Map<String,Object> map);

    //添加考卷
    @PostMapping("/courseTopic/insertTopicExamination")
    public  int insertTopicExamination(@RequestBody Map<String,Object> map);

    //添加考题
    @PostMapping("/courseTopic/insertTopic")
    public int insertTopic(@RequestBody Map<String, Object> map);

    //根据学习标签ID获取课程列表
    @PostMapping("/courseTopic/getCourseListByCourseTypeId")
    public List<Map<String ,Object>>   getCourseListByCourseTypeId(@RequestParam("courseTypeId") String courseTypeId);

    //根据考卷ID删除考卷与考题
    @PostMapping("/courseTopic/delCourseTopicExam")
    public  int delCourseTopicExam(@RequestParam("course_topic_examination_id") String course_topic_examination_id );

    @PostMapping("/courseTopic/insertTopics")
    public int insertTopics(@RequestParam("data") String data ,@RequestParam("userId") String userId);

    //修改考题
    @PostMapping("/courseTopic/updateTopics")
    public int updateTopics(@RequestParam("data") String data,@RequestParam("userId") String userId);

    @PostMapping("/courseTopic/getExamAndTopicsInfoByCourseTopicExamId")
    public CourseTopicExaminationVO getExamAndTopicsInfoByCourseTopicExamId(@RequestParam("courseTopicExaminationId") String courseTopicExaminationId);

    @PostMapping("/courseTopic/selectRegisterCourseTopicExamination")
    public  Map<String ,Object> selectRegisterCourseTopicExamination();

    @PostMapping("/courseTopic/selectTopicsByCourseTopicCourseId")
    public CourseTopicExamination selectTopicsByCourseTopicCourseId(@RequestParam("courseId") String courseId);
}
