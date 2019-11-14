package cn.com.myproject.external;

import cn.com.myproject.course.service.ICourseShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@RestController
@RequestMapping("/courseshare")
public class CourseShareController {

    @Autowired
    private ICourseShareService courseShareService;

    @GetMapping("/getCourseShareCount")
    public int getCourseShareCount(String userId){
        return courseShareService.getCourseShareCount(userId);
    }

}
