package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.Article;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IArticleService {


    @PostMapping("/article/addArticle")
    int addArticle(@RequestBody Article article);

    @PostMapping("/article/delArticleById")
    int delArticleById(@RequestParam("articleId") String articleId);

    @PostMapping("/article/selectArticleById")
    Article selectArticleById(@RequestParam("articleId") String articleId);

    @PostMapping("/article/updateArticle")
    int updateArticle(@RequestBody Article article);

    @PostMapping("/article/getPage")
    PageInfo<Article> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") String keyword);

    @PostMapping("/article/addArticleContent")
    int addArticleContent(@RequestBody Article article);

    @GetMapping("/article/allArticle")
    List<Article> allArticle();

    @PostMapping("/article/selectByStatus")
    Article selectByStatus(@RequestBody int status);

    @PostMapping("/article/updateStatus")
    int updateStatus(@RequestBody Article article);

    @PostMapping("/article/selectCount")
    int selectCount(@RequestBody int status);
}















