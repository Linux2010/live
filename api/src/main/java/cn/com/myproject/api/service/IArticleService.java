package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.Article;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LSG on 2017/8/28 0028.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IArticleService {

    @PostMapping("/article/selectByStatus")
    Article selectByStatus(@RequestBody int status);

    @PostMapping("/article/selectArticleById")
    Article selectArticleById(@RequestParam("articleId") String articleId);
}

