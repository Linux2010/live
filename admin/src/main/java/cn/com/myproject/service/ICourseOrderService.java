package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.CourseComment;
import cn.com.myproject.live.entity.PO.CourseOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by JYP on 2017/8/15 0015.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface ICourseOrderService {

    @PostMapping("/course_order/getPageForQuery")
    PageInfo<CourseOrder> getPageForQuery(@RequestBody Map<String ,Object> map);

    @GetMapping("/course_order/getById")
    CourseOrder getById(@RequestParam("corderid") String corderid);
}
