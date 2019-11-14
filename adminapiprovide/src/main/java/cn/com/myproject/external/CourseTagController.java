package cn.com.myproject.external;

import cn.com.myproject.course.service.ICourseTagService;
import cn.com.myproject.live.entity.PO.CourseTag;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签服务控制器类
 */
@RestController
@RequestMapping("/courseTag")
public class CourseTagController {

    @Autowired
    private ICourseTagService courseTagService;

    @PostMapping("/addCourseTag")
    public boolean addCourseTag(@RequestBody CourseTag ct){
        if(StringUtils.isNotBlank(ct.getName())){
            String ctId = UUID.randomUUID().toString().replace("-", "");
            ct.setCourseTagId(ctId);// 设置课程标签的ID
            ct.setCreateTime(new Date().getTime());// 默认当前时间
            ct.setVersion(1);// 默认第一版本
            ct.setStatus((short)1);// 默认有效
        }
        return courseTagService.addCourseTag(ct);
    }

    @PostMapping("/removeCourseTag")
    public boolean removeCourseTag(String courseTagId){
        return courseTagService.removeCourseTag(courseTagId);
    }

    @PostMapping("/modifyCourseTag")
    public boolean modifyCourseTag(@RequestBody CourseTag ct){
        return courseTagService.modifyCourseTag(ct);
    }

    @GetMapping("/searchCtList")
    public PageInfo<CourseTag> searchCtList(int pageNum,int pageSize,String name){
        return courseTagService.searchCtList(pageNum,pageSize,name);
    }

    @GetMapping("/searchCourseTagList")
    public List<CourseTag> searchCourseTagList(){
        return courseTagService.searchCourseTagList();
    }

}