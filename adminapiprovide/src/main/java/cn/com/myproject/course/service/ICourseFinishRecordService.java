package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import java.util.List;

/**
 * Created by CQC on 2017.9.1.
 */
public interface ICourseFinishRecordService {

        int searchCuCountByPId(String userId);

        int deleteByPrimaryKey(String cfrId);

        int insert(CourseFinishRecord record);

        int insertSelective(CourseFinishRecord record);

        CourseFinishRecord selectByPrimaryKey(String cfrId);

        CourseFinishRecord selectByCourseId(String courseId);

        List<CourseFinishRecord> selectByUserId(String userId);

        int updateByPrimaryKeySelective(CourseFinishRecord record);

        int updateByPrimaryKey(CourseFinishRecord record);

        public int getFinishNum(String userId);

        public int getFinishTeacherNum(String userId);

        long getTotalDuration(String userId);

        CourseFinishRecord selectByCourseIdAndUserId(String userId,String courseId);

}
