package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.CourseType;
import com.github.pagehelper.PageInfo;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类Service接口
 */
public interface ICourseTypeService {

    /**
     * 添加课程分类
     *
     * @param ct
     * @return
     */
    boolean addCourseType(CourseType ct);

    /**
     * 根据courseTypeId删除课程分类
     *
     * @param courseTypeId
     * @return
     */
    boolean removeCourseType(String courseTypeId);

    /**
     * 修改课程分类
     *
     * @param ct
     * @return
     */
    boolean modifyCourseType(CourseType ct);

    /**
     * 查询课程分页列表
     *
     * @param pageNum
     * @param pageSize
     * @param typeName
     * @param typeVal
     * @param parentId
     * @return
     */
    PageInfo<CourseType> searchCtList(int pageNum,int pageSize,String typeName,String typeVal,String parentId);

    /**
     * 根据ID查询课程分类信息
     *
     * @param courseTypeId
     * @return
     */
    CourseType searchCtById(String courseTypeId);

    /**
     * 根据父节点查询子节点信息
     *
     * @param parentId
     * @return
     */
    List<CourseType> searchCtByParentId(String parentId,String typeVal);

    /**
     * 根据课程类别和父节点ID查询课程分类
     *
     * @param typeVal
     * @param parentId
     * @return
     */
    List<CourseType> searchAllCtList(String typeVal,String parentId);

    /**
     * 根据课程分类ID查询课程数量
     *
     * @param courseTypeId
     * @return
     */
    int searchCourseCountByCtId(String courseTypeId);

}