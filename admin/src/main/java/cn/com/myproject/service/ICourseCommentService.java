package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.CourseComment;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by JYP on 2017/8/14 0014.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface ICourseCommentService {

    @GetMapping("/comment/getPage")
    PageInfo<CourseComment> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @GetMapping("/comment/getById")
    CourseComment getById(@RequestParam("commid") String commid);

    @GetMapping("/comment/delcomm")
    void delcomm(@RequestParam("commid") String commid);
}
