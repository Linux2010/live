package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.CourseType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-26
 * desc：课程分类Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseTypeService {

    @GetMapping("/courseType/searchAllCtList")
    List<CourseType> searchAllCtList(@RequestParam("typeVal") String typeVal,
                                     @RequestParam("parentId") String parentId);

}