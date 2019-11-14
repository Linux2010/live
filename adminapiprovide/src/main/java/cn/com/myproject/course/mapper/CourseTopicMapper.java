package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/7 0007.
 */
@Mapper
public interface CourseTopicMapper {

    int insertTopic(@Param("map") Map<String, Object> map);

    int updateTopic(@Param("map") Map<String, Object> map);

    int deleteCourseTopics(@Param("course_topic_examination_id") String courseTopic_examination_id);

    List<Map<String, Object>> selectTopicsByCourseTopicExaminationId(@Param("courseTopicExaminationId") String courseTopicExaminationId);

    //新加的方法
    List<CourseTopic> getExam(@Param("courseTopicExaminationId") String courseTopicExaminationId);

    //新加的方法
    List<CourseTopic> searchCourseTopicsByCourseId(@Param("courseId") String courseId);

    /**
     * 用户答题列表与选择答案
     *
     * @param topic
     * @return
     */
    List<CourseTopic> selectUserCourseTopicsAndChooseAnswers(CourseTopic topic);

    CourseTopic selectRegisterAnswerById(String courseTopicId, String courseTopicExaminationId);

    CourseTopic selectTopicLable(CourseTopic courseTopic);
}
