package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import cn.com.myproject.live.entity.VO.CourseTopicCourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LeiJia on 2017/8/7 0007.
 */
@Mapper
public interface CourseTopicUserAnswerMapper {

    //填写注册考题答案
    public void userAnswer(CourseTopicUserAnswer courseTopicUserAnswer);

    public int insertUserAnswer(CourseTopicUserAnswer courseTopicUserAnswer);

    public List<CourseTopicCourseVO> getUserTopicCourseList(@Param("pageNumKey")int pageNum,
                                                            @Param("pageSizeKey")int pageSize,
                                                            @Param("userId") String userId);

    public  List<CourseTopicCourseVO>  getUserTopicCourseNoAnswerTheTopicsList(@Param("pageNumKey")int pageNum,
                                                                               @Param("pageSizeKey")int pageSize,
                                                                               @Param("userId") String userId);

    List<CourseTopicUserAnswer> selectAnswerByUserId(String userId);

    CourseTopicUserAnswer selectTopicNoAnswer (CourseTopicUserAnswer courseTopicUserAnswer);
}
