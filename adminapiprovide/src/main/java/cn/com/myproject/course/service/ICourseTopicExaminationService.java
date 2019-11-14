package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.VO.CourseRegisterExamVO;

/**
 * Created by Administrator on 2017/8/15 0007.
 */
public interface ICourseTopicExaminationService {

    //获取注册考题
    public CourseRegisterExamVO getRegisterById(String courseTopicExaminationId);

    //得到注册考题的id
    CourseTopicExamination getRegisterExamMessage();
}
