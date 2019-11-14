package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseShare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * Created by pangdatao on 2017-08-31
 * desc：课程分享Mapper接口
 */
@Mapper
public interface CourseShareMapper {

    /**
     * 插入分享课程记录
     *
     * @param courseShare
     * @return
     */
    public int insertCourseShare(CourseShare courseShare);

    /**
     * 查询当天分享课程的次数
     *
     * @param userId
     * @return
     */
    public int searchCourseShareCount(@Param("userId") String userId);

    public Integer getCourseShareCount(String userId);

}