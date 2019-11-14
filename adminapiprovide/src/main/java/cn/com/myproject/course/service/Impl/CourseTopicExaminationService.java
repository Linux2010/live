package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseTopicExaminationMapper;
import cn.com.myproject.course.service.ICourseTopicExaminationService;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.VO.CourseRegisterExamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
@Service
public class CourseTopicExaminationService implements ICourseTopicExaminationService{

    @Autowired
    private CourseTopicExaminationMapper courseTopicExaminationMapper;

    @Override
    public CourseRegisterExamVO getRegisterById(String courseTopicExaminationId) {
        return courseTopicExaminationMapper.getRegisterById(courseTopicExaminationId);
    }

    @Override
    public CourseTopicExamination getRegisterExamMessage() {
        return courseTopicExaminationMapper.getRegisterExamMessage();
    }
}
