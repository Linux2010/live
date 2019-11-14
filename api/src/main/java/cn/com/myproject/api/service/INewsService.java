package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.PO.News;
import cn.com.myproject.user.entity.PO.NewsComment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/28.
 */
@FeignClient(name="admin-api-provide" ,url="${feignclient.url}" )
public interface INewsService {
    @GetMapping("/news/getAll")
      List<News> getAll() ;


    @GetMapping("/news/searchNewsList")
    List<News> searchAllNewsList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize,
                                 @RequestParam("title") String title);


    @GetMapping("/news/searchNewsById")
    News searchNewsById(@RequestParam("businessId") String businessId);

}
