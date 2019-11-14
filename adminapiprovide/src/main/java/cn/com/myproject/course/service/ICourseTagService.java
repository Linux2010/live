package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.CourseTag;
import com.github.pagehelper.PageInfo;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签Service接口
 */
public interface ICourseTagService {

    /**
     * 添加课程标签
     *
     * @param ct
     * @return
     */
    boolean addCourseTag(CourseTag ct);

    /**
     * 根据courseTagId删除课程标签
     *
     * @param courseTagId
     * @return
     */
    boolean removeCourseTag(String courseTagId);

    /**
     * 修改课程标签
     *
     * @param ct
     * @return
     */
    boolean modifyCourseTag(CourseTag ct);

    /**
     * 分页查询课程标签
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    PageInfo<CourseTag> searchCtList(int pageNum,int pageSize,String name);

    /**
     * 查询课程标签列表
     *
     * @return
     */
    List<CourseTag> searchCourseTagList();

}