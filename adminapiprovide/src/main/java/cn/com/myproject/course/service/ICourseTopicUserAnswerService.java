package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
public interface ICourseTopicUserAnswerService {

    public void userAnswer(CourseTopicUserAnswer courseTopicUserAnswer);

    List<CourseTopicUserAnswer> selectAnswerByUserId(String userId);

    CourseTopicUserAnswer selectTopicNoAnswer (CourseTopicUserAnswer courseTopicUserAnswer);
}
