package cn.com.myproject.external;

import cn.com.myproject.live.entity.PO.CourseComment;
import cn.com.myproject.live.service.ICourseCommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by JYP on 2017/8/14 0014.
 */
@RestController
@RequestMapping("/comment")
public class CourseCommentController {

    @Autowired
    private ICourseCommentService courseCommentService;

    @GetMapping("/getPage")
    public PageInfo<CourseComment> getPage(int pageNum, int pageSize) {
        return courseCommentService.getPage(pageNum, pageSize);
    }

    @GetMapping("/getById")
    public CourseComment getById(@RequestParam("commid") String commid) {
        return courseCommentService.getById(commid);
    }

    @GetMapping("/delcomm")
    public Integer delcomm(@RequestParam("commid")  String commid) {
        int info = 0;
        try {
            courseCommentService.delcomm(commid);
            info = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }

    @PostMapping("/addcomm")
    public Integer addcomm(@RequestBody CourseComment courseComment){
        int info = 0;
        try{
            courseCommentService.addcomm(courseComment);
            info = 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return info;
    }

    @PostMapping("/checkcomment")
    public Integer checkcomment(@RequestBody  CourseComment courseComment) {
        return courseCommentService.checkcomment(courseComment);
    }


}
