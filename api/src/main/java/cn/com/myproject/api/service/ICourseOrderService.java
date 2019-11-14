package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.CourseOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseOrderService {

    @PostMapping("/course_order/getPageForQuery")
    PageInfo<CourseOrder> getPageForQuery(@RequestBody Map<String, Object> map);

    @GetMapping("/course_order/searchCoMoneyByCno")
    CourseOrder searchCoMoneyByCno(@RequestParam("courseOrderNo") String courseOrderNo);

}