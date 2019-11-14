package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.CourseType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类Mapper接口
 */
@Mapper
public interface CourseTypeMapper {

    /**
     * 插入课程分类
     *
     * @param ct
     * @return
     */
    int insertCourseType(CourseType ct);

    /**
     * 根据courseTypeId删除课程分类
     *
     * @param courseTypeId
     * @return
     */
    int deleteCourseType(@Param("courseTypeId") String courseTypeId);

    /**
     * 修改课程分类
     *
     * @param ct
     * @return
     */
    int updateCourseType(CourseType ct);

    /**
     * 分页查询课程分类列表
     *
     * @param pageNum
     * @param pageSize
     * @param typeName
     * @param typeVal
     * @return
     */
    List<CourseType> searchCtList(@Param("pageNumKey") int pageNum,
                                         @Param("pageSizeKey") int pageSize,
                                         @Param("typeName") String typeName,
                                         @Param("typeVal") String typeVal,
                                         @Param("parentId") String parentId);

    /**
     * 根据课程类别和父节点ID查询课程分类
     *
     * @param typeVal
     * @param parentId
     * @return
     */
    List<CourseType> searchAllCtList(@Param("typeVal") String typeVal,
                                            @Param("parentId") String parentId);

    /**
     * 根据ID查询课程分类信息
     *
     * @param courseTypeId
     * @return
     */
    CourseType searchCtById(@Param("courseTypeId") String courseTypeId);

    /**
     * 根据父节点查询子节点信息
     *
     * @param parentId
     * @return
     */
    List<CourseType> searchCtByParentId(@Param("parentId") String parentId,@Param("typeVal") String typeVal);

    /**
     * 根据课程分类ID查询课程数量
     *
     * @param courseTypeId
     * @return
     */
    int searchCourseCountByCtId(@Param("courseTypeId") String courseTypeId);

}