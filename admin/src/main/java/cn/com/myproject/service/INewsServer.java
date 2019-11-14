package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.News;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/15.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface INewsServer {


        @PostMapping("/news/addNews")
        int addNews(@RequestBody News news);

        @PostMapping("/news/delNewsById")
        void delNewsById(@RequestParam("businessId") String businessId);

        @PostMapping("/news/selectNewsById")
         News  selectNewsById(@RequestParam("businessId") String businessId);

        @PostMapping("/news/updateNews")
        void updateNews(@RequestBody News news);

        @GetMapping("/news/selectNews")
        List<News> selectNews(@RequestParam("keyword") String keyword);

        @GetMapping("/news/getAll")
        List<News> getAll();

        @PostMapping("/news/updateNewsTitle")
        int updateNewsTitle(@RequestBody News news);

        @GetMapping("/news/searchAllNewsList")
        PageInfo<News> searchAllNewsList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam("title") String title);

    }

















