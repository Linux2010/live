package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseFinishRecordMapper;
import cn.com.myproject.course.service.ICourseFinishRecordService;
import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@Service
public class CourseFinishRecordService implements ICourseFinishRecordService {

    @Autowired
    private CourseFinishRecordMapper courseFinishRecordMapper;

    @Override
    public int deleteByPrimaryKey(String cfrId) {
        return courseFinishRecordMapper.deleteByPrimaryKey(cfrId);
    }

    @Override
    public int insert(CourseFinishRecord record) {
        return courseFinishRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseFinishRecord record) {
        return courseFinishRecordMapper.insertSelective(record);
    }

    @Override
    public int searchCuCountByPId(String userId){
        return courseFinishRecordMapper.selectCuCountByPId(userId);
    }

    @Override
    public CourseFinishRecord selectByPrimaryKey(String cfrId) {
        return courseFinishRecordMapper.selectByPrimaryKey(cfrId);
    }

    @Override
    public CourseFinishRecord selectByCourseId(String courseId) {
        return courseFinishRecordMapper.selectByCourseId(courseId);
    }

    @Override
    public List<CourseFinishRecord> selectByUserId(String userId){
        return courseFinishRecordMapper.selectByUserId(userId);
    }


    @Override
    public int updateByPrimaryKeySelective(CourseFinishRecord record) {
        return courseFinishRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseFinishRecord record) {
        return courseFinishRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int getFinishNum(String userId) {
        Integer finishNum = courseFinishRecordMapper.getFinishNum(userId);
        return finishNum == null?0:finishNum;
    }

    @Override
    public int getFinishTeacherNum(String userId) {
        Integer finishTeacherNum = courseFinishRecordMapper.getFinishTeacherNum(userId);
        return finishTeacherNum == null?0:finishTeacherNum;
    }

    @Override
    public long getTotalDuration(String userId) {
        Long totalDuration = courseFinishRecordMapper.getTotalDuration(userId);
        if(null == totalDuration){
            totalDuration = 0l;
        }
        return totalDuration;
    }

    @Override
    public CourseFinishRecord selectByCourseIdAndUserId(String userId, String courseId) {
        return courseFinishRecordMapper.selectByCourseIdAndUserId(userId,courseId);
    }
}
