package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseTopicUserAnswerMapper;
import cn.com.myproject.course.service.ICourseTopicUserAnswerService;
import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
@Service
public class CourseTopicUserAnswerService implements ICourseTopicUserAnswerService{

    @Autowired
    private CourseTopicUserAnswerMapper courseTopicUserAnswerMapper;


    @Override
    public void userAnswer(CourseTopicUserAnswer courseTopicUserAnswer) {
        courseTopicUserAnswer.setCreateTime(new Date().getTime());
        courseTopicUserAnswer.setCourseTopicUserAnswerId(UUID.randomUUID().toString().replace("-", ""));
        courseTopicUserAnswerMapper.userAnswer(courseTopicUserAnswer);
    }

    @Override
    public List<CourseTopicUserAnswer> selectAnswerByUserId(String userId) {

        return courseTopicUserAnswerMapper.selectAnswerByUserId(userId);
    }

    @Override
    public CourseTopicUserAnswer selectTopicNoAnswer(CourseTopicUserAnswer courseTopicUserAnswer) {
        return courseTopicUserAnswerMapper.selectTopicNoAnswer(courseTopicUserAnswer);
    }
}
