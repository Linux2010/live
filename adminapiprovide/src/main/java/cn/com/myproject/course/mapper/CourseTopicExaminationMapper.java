package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.VO.CourseRegisterExamVO;
import cn.com.myproject.live.entity.VO.CourseTopicExaminationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/7 0007.
 */
@Mapper
public interface CourseTopicExaminationMapper {

    /**
     * 根据课程ID查询考卷ID
     *
     * @param courseId
     * @return
     */
    String selectKjIdByCId(@Param("courseId") String courseId);

    List<CourseTopicExaminationVO> getPageCourseTopicExaminations(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("vo") CourseTopicExaminationVO vo);

     List<Map<String,Object>> getStudyLabels(@Param("map") Map<String, Object> map);

     int insertTopicExamination(@Param("map") Map<String, Object> map);

     int updateTopicExamination(@Param("map") Map<String, Object> map);

     List<Map<String ,Object>>   getCourseListByCourseTypeId(@Param("courseTypeId") String courseTypeId);

     int delCourseTopicExam(@Param("course_topic_examination_id") String course_topic_examination_id);

     CourseTopicExaminationVO getExamAndTopicsInfoByCourseTopicExamId(@Param("courseTopicExaminationId") String courseTopicExaminationId);

     CourseTopicExamination selectTopicsByCourseTopicCourseId(@Param("courseId") String courseId);

     Map<String ,Object>  selectRegisterCourseTopicExamination();

    CourseRegisterExamVO getRegisterById(@Param("courseTopicExaminationId") String courseTopicExaminationId);

    CourseTopicExamination getRegisterExamMessage();
}
