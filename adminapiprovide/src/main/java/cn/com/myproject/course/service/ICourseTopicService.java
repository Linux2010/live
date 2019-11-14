package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.VO.CourseRegisterExamVO;
import cn.com.myproject.live.entity.VO.CourseTopicCourseVO;
import cn.com.myproject.live.entity.VO.CourseTopicExaminationVO;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/15 0007.
 */
public interface ICourseTopicService {

    /**
     * 根据课程ID查询考卷ID
     *
     * @param courseId
     * @return
     */
    String selectKjIdByCId(String courseId);

    //分页获取考试卷列表
    public  PageInfo<CourseTopicExaminationVO> getPageCourseTopicExaminations(int pageNum, int pageSize, CourseTopicExaminationVO vo);

    //查询学习标签列表
    public List<Map<String ,Object>> getStudyLabels(Map<String, Object> map);

    //插入考试卷
    public  int insertTopicExamination(Map<String, Object> map);

    //插入考试题
    public int insertTopic(Map<String, Object> map);

    //根据课程类别ID获取课程类别
    public List<Map<String ,Object>>   getCourseListByCourseTypeId(String courseTypeId);

    //删除考卷考题
    public  int delCourseTopicExam(String course_topic_examination_id);

    //添加考题考卷
    public int insertTopics(String data, String userId);

    //修改考题
    public int updateTopics(String data, String userId);

    //根据考卷ID获取考卷与考题信息
    public CourseTopicExaminationVO getExamAndTopicsInfoByCourseTopicExamId(String courseTopicExaminationId);

    //根据课程ID获取考卷信息
    public CourseTopicExamination selectTopicsByCourseTopicCourseId(String courseId);

    //查询注册考题信息
    public  Map<String ,Object> selectRegisterCourseTopicExamination();

    //根据id获取注册考题
    public CourseRegisterExamVO getRegisterById(String courseTopicExaminationId);

    //获取注册考题
    List<CourseTopic> getExam(String courseTopicExaminationId);

    //获取课程考题
    public List<CourseTopic> searchCourseTopicsByCourseId(String courseId);

    //添加用户提交的答案
    public int  submitCourseTopicsAnswer(CourseTopicExamination examination);

    public List<CourseTopicCourseVO> getUserTopicCourseList(int pageNum,int pageSize,String userId);

    public  List<CourseTopicCourseVO>  getUserTopicCourseNoAnswerTheTopicsList(int pageNum,int pageSize,String userId);

    List<CourseTopic> selectUserCourseTopicsAndChooseAnswers(CourseTopic topic);

    //根据考题答案 跟 考题类型 查询考题对象
    CourseTopic selectRegisterAnswerById(String courseTopicId, String courseTopicExaminationId);

    CourseTopic selectTopicLable (CourseTopic courseTopic);

}

