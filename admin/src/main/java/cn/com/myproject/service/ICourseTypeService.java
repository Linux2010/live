package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.CourseType;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseTypeService {

    @PostMapping("/courseType/addCourseType")
    boolean addCourseType(@RequestBody CourseType ct);

    @PostMapping("/courseType/removeCourseType")
    boolean removeCourseType(@RequestParam("courseTypeId") String courseTypeId);

    @PostMapping("/courseType/modifyCourseType")
    boolean modifyCourseType(@RequestBody CourseType ct);

    @GetMapping("/courseType/searchCtList")
    PageInfo<CourseType> searchCtList(@RequestParam("pageNum") int pageNum,
                                             @RequestParam("pageSize") int pageSize,
                                             @RequestParam("typeName") String typeName,
                                             @RequestParam("typeVal") String typeVal,
                                             @RequestParam("parentId") String parentId);

    @GetMapping("/courseType/searchCtById")
    CourseType searchCtById(@RequestParam("courseTypeId") String courseTypeId);

    @GetMapping("/courseType/searchCtByParentId")
    List<CourseType> searchCtByParentId(@RequestParam("parentId") String parentId,
                                               @RequestParam("typeVal") String typeVal);

    @GetMapping("/courseType/searchCourseCountByCtId")
    int searchCourseCountByCtId(@RequestParam("courseTypeId") String courseTypeId);

}