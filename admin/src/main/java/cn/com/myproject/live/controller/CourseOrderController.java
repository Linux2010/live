package cn.com.myproject.live.controller;

import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.service.ICourseOrderService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JYP on 2017/8/15 0015.
 */
@Controller
@RequestMapping("/course_order")
public class CourseOrderController {

    public static final Logger logger = LoggerFactory.getLogger(CourseOrderController.class);

    @Autowired
    private ICourseOrderService courseOrderService;

    @RequestMapping("/")
    public String index() {
        return "buiness/course_order";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<CourseOrder> getPageForQuery(int rows, int page, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotBlank(keyword)) {
            map.put("keyword", keyword);
        }
        if (StringUtils.isNotBlank(request.getParameter("status"))) {
            int status = Integer.parseInt(request.getParameter("status"));
            map.put("orders", status);
        }
        map.put("pageNum", page);
        map.put("pageSize", rows);
        PageInfo<CourseOrder> list = courseOrderService.getPageForQuery(map);
        return list;
    }

    @ResponseBody
    @RequestMapping("/get")
    public CourseOrder getById(HttpServletRequest request) {
        String corderid = request.getParameter("corderid");
        return courseOrderService.getById(corderid);
    }
}
