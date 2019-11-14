package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseTag;
import cn.com.myproject.user.entity.PO.StudyLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签Mapper接口
 */
@Mapper
public interface CourseTagMapper {

    /**
     * 插入课程标签
     *
     * @param ct
     * @return
     */
    int insertCourseTag(CourseTag ct);

    /**
     * 添加学习标签
     *
     * @param label
     * @return
     */
    int insertStudyLabel(StudyLabel label);

    /**
     * 根据课程标签呢ID删除课程标签
     *
     * @param courseTagId
     * @return
     */
    int deleteCourseTag(@Param("courseTagId") String courseTagId);

    /**
     * 根据课程标签呢ID删除学习标签
     *
     * @param courseTagId
     * @return
     */
    int deleteStudyLabel(@Param("courseTagId") String courseTagId);

    /**
     * 修改学习标签
     *
     * @param label
     * @return
     */
    int updateStudyLabel(StudyLabel label);

    /**
     * 修改学习标签
     *
     * @param ct
     * @return
     */
    int updateCourseTag(CourseTag ct);

    /**
     * 分页查询课程标签
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    List<CourseTag> searchCtList(@Param("pageNumKey") int pageNum,
                                 @Param("pageSizeKey") int pageSize,
                                 @Param("name") String name);

    /**
     * 查询课程标签列表
     *
     * @return
     */
    List<CourseTag> searchCourseTagList();

}