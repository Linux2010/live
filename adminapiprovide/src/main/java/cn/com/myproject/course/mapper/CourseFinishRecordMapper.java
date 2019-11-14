package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseFinishRecordMapper {

    int selectCuCountByPId(String userId);

    int deleteByPrimaryKey(String cfrId);

    int insert(CourseFinishRecord record);

    int insertSelective(CourseFinishRecord record);

    CourseFinishRecord selectByPrimaryKey(String cfrId);

    CourseFinishRecord selectByCourseId(String courseId);

    List<CourseFinishRecord> selectByUserId(String userId);

    int updateByPrimaryKeySelective(CourseFinishRecord record);

    int updateByPrimaryKey(CourseFinishRecord record);

    Integer getFinishNum(String userId);

    Integer getFinishTeacherNum(String userId);

    Long getTotalDuration(String userId);

    CourseFinishRecord selectByCourseIdAndUserId(@Param("userId")String userId, @Param("courseId") String courseId);

}