package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.CourseTag;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程标签Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseTagService {

    @PostMapping("/courseTag/addCourseTag")
    boolean addCourseTag(@RequestBody CourseTag ct);

    @PostMapping("/courseTag/removeCourseTag")
    boolean removeCourseTag(@RequestParam("courseTagId") String courseTagId);

    @PostMapping("/courseTag/modifyCourseTag")
    boolean modifyCourseTag(@RequestBody CourseTag ct);

    @GetMapping("/courseTag/searchCtList")
    PageInfo<CourseTag> searchCtList(@RequestParam("pageNum") int pageNum,
                                            @RequestParam("pageSize") int pageSize,
                                            @RequestParam("name") String name);

    @GetMapping("/courseTag/searchCourseTagList")
    List<CourseTag> searchCourseTagList();

}