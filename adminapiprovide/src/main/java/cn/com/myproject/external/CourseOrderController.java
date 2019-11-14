package cn.com.myproject.external;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.live.service.ICourseOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by JYP on 2017/8/15 0015.
 */
@RestController
@RequestMapping("/course_order")
public class CourseOrderController {

    @Autowired
    private ICourseOrderService courseOrderService;

    @PostMapping("/getPageForQuery")
    public PageInfo<CourseOrder> getPageForQuery(@RequestBody Map<String, Object> map) {
        PageInfo<CourseOrder> list = courseOrderService.getPageForQuery(Integer.parseInt(map.get("pageNum") + ""), Integer.parseInt(map.get("pageSize") + ""), map);
        return list;
    }

    @GetMapping("/getById")
    public CourseOrder getById(String corderid) {
        return courseOrderService.getById(corderid);
    }

    @PostMapping("/addcorder")
    public void addcorder(@RequestBody CourseOrder courseOrder) {
        courseOrderService.addcorder(courseOrder);
    }

    @PostMapping("/updatecorder")
    public void updatecorder(@RequestBody CourseOrder courseOrder) {
        courseOrderService.updatecorder(courseOrder);
    }

    @PostMapping("/checkPurchase")
    public Integer checkPurchase(@RequestBody CourseOrder courseOrder){
        return courseOrderService.ckeckPurchase(courseOrder);
    }

    @PostMapping("/select_today_study")
    public List<Course> select_today_study(int pageNum, int pageSize,String userId){
        return courseOrderService.select_today_study(pageNum,pageSize,userId);
    }

    @GetMapping("/searchCoMoneyByCno")
    public CourseOrder searchCoMoneyByCno(String courseOrderNo){
        return courseOrderService.searchCoMoneyByCno(courseOrderNo);
    }

}