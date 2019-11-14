package cn.com.myproject.external;

import cn.com.myproject.course.service.ICourseTypeService;
import cn.com.myproject.live.entity.PO.CourseType;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类服务控制器类
 */
@RestController
@RequestMapping("/courseType")
public class CourseTypeController {

    @Autowired
    private ICourseTypeService courseTypeService;

    @PostMapping("/addCourseType")
    public boolean addCourseType(@RequestBody CourseType ct){
        if(StringUtils.isNotBlank(ct.getTypeName())){
            String ctId = UUID.randomUUID().toString().replace("-", "");
            ct.setCourseTypeId(ctId);// 设置课程分类的ID
            ct.setCreateTime(new Date().getTime());// 默认当前时间
            ct.setVersion(1);// 默认第一版本
        }
        return courseTypeService.addCourseType(ct);
    }

    @PostMapping("/removeCourseType")
    public boolean removeCourseType(String courseTypeId){
        return courseTypeService.removeCourseType(courseTypeId);
    }

    @PostMapping("/modifyCourseType")
    public boolean modifyCourseType(@RequestBody CourseType ct){
        return courseTypeService.modifyCourseType(ct);
    }

    @GetMapping("/searchCtList")
    public PageInfo<CourseType> searchCtList(int pageNum,int pageSize,String typeName,String typeVal,String parentId){
        return courseTypeService.searchCtList(pageNum,pageSize,typeName,typeVal,parentId);
    }

    @GetMapping("/searchAllCtList")
    public List<CourseType> searchAllCtList(String typeVal,String parentId){
        return courseTypeService.searchAllCtList(typeVal,parentId);
    }

    @GetMapping("/searchCtById")
    public CourseType searchCtById(String courseTypeId){
        return courseTypeService.searchCtById(courseTypeId);
    }

    @GetMapping("/searchCtByParentId")
    public List<CourseType> searchCtByParentId(String parentId,String typeVal){
        return courseTypeService.searchCtByParentId(parentId,typeVal);
    }

    @GetMapping("/searchCourseCountByCtId")
    public int searchCourseCountByCtId(String courseTypeId){
        return courseTypeService.searchCourseCountByCtId(courseTypeId);
    }

}