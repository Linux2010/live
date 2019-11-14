package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseTypeMapper;
import cn.com.myproject.course.service.ICourseTypeService;
import cn.com.myproject.live.entity.PO.CourseType;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类Service接口实现类
 */
@Service
public class CourseTypeService implements ICourseTypeService{

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    /**
     * 添加课程分类
     *
     * @param ct
     * @return
     */
    @Override
    @Transactional
    public boolean addCourseType(CourseType ct){
        boolean flagVal = true;
        int countVal = courseTypeMapper.insertCourseType(ct);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    /**
     * 根据courseTypeId删除课程分类
     *
     * @param courseTypeId
     * @return
     */
    @Override
    @Transactional
    public boolean removeCourseType(String courseTypeId){
        boolean flagVal = true;
        int countVal = courseTypeMapper.deleteCourseType(courseTypeId);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    /**
     * 修改课程分类
     *
     * @param ct
     * @return
     */
    @Override
    @Transactional
    public boolean modifyCourseType(CourseType ct){
        boolean flagVal = true;
        int countVal = courseTypeMapper.updateCourseType(ct);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

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
    @Override
    public PageInfo<CourseType> searchCtList(int pageNum, int pageSize, String typeName, String typeVal, String parentId){
        List<CourseType> ctList = courseTypeMapper.searchCtList(pageNum,pageSize,typeName,typeVal,parentId);
        return convert(ctList);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<CourseType> convert(List<CourseType> list) {
        PageInfo<CourseType> info = new PageInfo(list);
        List<CourseType> _list = info.getList();
        info.setList(null);
        List<CourseType> __list = new ArrayList<>(10);
        PageInfo<CourseType> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(CourseType ct : _list) {
                __list.add(ct);
            }
            _info.setList(__list);
        }
        return _info;
    }

    /**
     * 根据ID查询课程分类信息
     *
     * @param courseTypeId
     * @return
     */
    @Override
    public CourseType searchCtById(String courseTypeId){
        return courseTypeMapper.searchCtById(courseTypeId);
    }

    /**
     * 根据父节点查询子节点信息
     *
     * @param parentId
     * @return
     */
    @Override
    public List<CourseType> searchCtByParentId(String parentId,String typeVal){
        return courseTypeMapper.searchCtByParentId(parentId,typeVal);
    }

    /**
     * 根据课程类别和父节点ID查询课程分类
     *
     * @param typeVal
     * @param parentId
     * @return
     */
    @Override
    public List<CourseType> searchAllCtList(String typeVal,String parentId){
        return courseTypeMapper.searchAllCtList(typeVal,parentId);
    }

    /**
     * 根据课程分类ID查询课程数量
     *
     * @param courseTypeId
     * @return
     */
    @Override
    public int searchCourseCountByCtId(String courseTypeId){
        return courseTypeMapper.searchCourseCountByCtId(courseTypeId);
    }

}