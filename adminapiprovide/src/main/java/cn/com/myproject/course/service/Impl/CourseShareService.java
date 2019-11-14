package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseShareMapper;
import cn.com.myproject.course.service.ICourseShareService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@Service
public class CourseShareService implements ICourseShareService{

    @Autowired
    private CourseShareMapper courseShareMapper;

    @Override
    public int getCourseShareCount(String userId) {
        Integer courseShareCount = courseShareMapper.getCourseShareCount(userId);
        return courseShareCount == null?0:courseShareCount;
    }
}
