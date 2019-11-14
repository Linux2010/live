package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseTagMapper;
import cn.com.myproject.course.service.ICourseTagService;
import cn.com.myproject.live.entity.PO.CourseTag;
import cn.com.myproject.user.entity.PO.StudyLabel;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签Service接口实现类
 */
@Service
public class CourseTagService implements ICourseTagService{

    private static final Logger logger = LoggerFactory.getLogger(CourseTagService.class);

    @Autowired
    private CourseTagMapper courseTagMapper;

    /**
     * 添加课程标签
     *
     * @param ct
     * @return
     */
    @Override
    @Transactional
    public boolean addCourseTag(CourseTag ct){
        boolean flagVal = false;
        int countVal = courseTagMapper.insertCourseTag(ct);
        if(countVal > 0){
            flagVal = true;
            // 同步添加学习标签
            StudyLabel label = new StudyLabel();
            label.setCourseTagId(ct.getCourseTagId());
            label.setLabelid(ct.getCourseTagId());
            label.setLabelname(ct.getName());
            label.setLabeltype(ct.getName());
            label.setCreateTime(ct.getCreateTime());
            label.setStatus(ct.getStatus());
            label.setVersion(ct.getVersion());
            int j = courseTagMapper.insertStudyLabel(label);
            if(j != 1){
                logger.error("同步添加学习标签失败courseTagMapper.deleteStudyLabel(courseTagId)");
                throw new RuntimeException("同步添加学习标签失败courseTagMapper.deleteStudyLabel(courseTagId)");
            }
        }
        return flagVal;
    }

    /**
     * 根据courseTagId删除课程标签
     *
     * @param courseTagId
     * @return
     */
    @Override
    @Transactional
    public boolean removeCourseTag(String courseTagId){
        boolean flagVal = false;
        int countVal = courseTagMapper.deleteCourseTag(courseTagId);
        if(countVal > 0){
            flagVal = true;
            // 同步删除学习标签
            int j = courseTagMapper.deleteStudyLabel(courseTagId);
            if(j != 1){
                logger.error("同步删除学习标签失败courseTagMapper.deleteStudyLabel(courseTagId)");
                throw new RuntimeException("同步删除学习标签失败courseTagMapper.deleteStudyLabel(courseTagId)");
            }
        }
        return flagVal;
    }

    /**
     * 修改课程标签
     *
     * @param ct
     * @return
     */
    @Override
    @Transactional
    public boolean modifyCourseTag(CourseTag ct){
        boolean flagVal = false;
        int countVal = courseTagMapper.updateCourseTag(ct);
        if(countVal > 0){
            flagVal = true;
            // 同步修改学习标签
            StudyLabel label = new StudyLabel();
            label.setLabeltype(ct.getName());
            label.setLabelname(ct.getName());
            label.setCourseTagId(ct.getCourseTagId());
            int j =  courseTagMapper.updateStudyLabel(label);

            if(j != 1){
                logger.error("同步修改学习标签失败courseTagMapper.updateStudyLabel(label)");
                throw new RuntimeException("同步修改学习标签失败courseTagMapper.updateStudyLabel(label)");
            }
        }
        return flagVal;
    }

    /**
     * 分页查询课程标签
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageInfo<CourseTag> searchCtList(int pageNum, int pageSize, String name){
        List<CourseTag> ctList = courseTagMapper.searchCtList(pageNum,pageSize,name);
        return convert(ctList);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<CourseTag> convert(List<CourseTag> list) {
        PageInfo<CourseTag> info = new PageInfo(list);
        List<CourseTag> _list = info.getList();
        info.setList(null);
        List<CourseTag> __list = new ArrayList<>(10);
        PageInfo<CourseTag> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(CourseTag ct : _list) {
                __list.add(ct);
            }
            _info.setList(__list);
        }
        return _info;
    }

    /**
     * 查询课程标签列表
     *
     * @return
     */
    @Override
    public List<CourseTag> searchCourseTagList(){
        return courseTagMapper.searchCourseTagList();
    }

}