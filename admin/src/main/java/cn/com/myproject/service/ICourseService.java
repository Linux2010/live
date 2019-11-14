package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-17
 * desc：课程Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseService {

    @PostMapping("/course/addCourse")
    boolean addCourse(@RequestBody Course c);

    @PostMapping("/course/removeCourse")
    boolean removeCourse(@RequestParam("courseId") String courseId);

    @PostMapping("/course/modifyCourse")
    boolean modifyCourse(@RequestBody Course c);

    @GetMapping("/course/searchCourseList")
    PageInfo<Course> searchCourseList(@RequestParam("pageNum") int pageNum,
                                             @RequestParam("pageSize") int pageSize,
                                             @RequestParam("typeVal") String typeVal,
                                             @RequestParam("courseTitle") String courseTitle,
                                             @RequestParam("zbType") String zbType);

    @GetMapping("/course/searchTeaList")
    List<User> searchTeaList();

    @GetMapping("/course/searchCourseById")
    Course searchCourseById(@RequestParam("courseId") String courseId);

    @PostMapping("/course/modifyVideo")
    boolean modifyVideo(@RequestBody Course c);

    @PostMapping("/course/getVideoUrl")
    String getVideoUrl(@RequestParam("vId") int vId);

    @GetMapping("/course/searchCtListByCId")
    List<CourseTopic> searchCtListByCId(@RequestParam("courseId") String courseId);

    @PostMapping("/course/modifyCourseContent")
    boolean modifyCourseContent(@RequestBody Course c);

    @GetMapping("/course/searchCourseContentById")
    Course searchCourseContentById(@RequestParam("courseId") String courseId);

    @GetMapping("/course/searchCtCountByCId")
    int searchCtCountByCId(@RequestParam("courseId") String courseId);

    @GetMapping("/course/selectAllCourse")
    List<Course> selectAllCourse();

    @PostMapping("/course/selectCategory")
    List<Course> selectCategory(@RequestParam("type_val") String type_val);

}