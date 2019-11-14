package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.GoodArticle;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by LSG on 2017/8/23 0023.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IGoodArticleService {

    @PostMapping("/goodArticle/getPage")
    PageInfo<GoodArticle> getPage(@RequestBody Map<String, Object> map);

    @PostMapping("/goodArticle/selectById")
    GoodArticle selectById(@RequestParam("goodArticleId") String goodArticleId);

    @PostMapping("/goodArticle/addGoodArticle")
    int addGoodArticle(@RequestBody GoodArticle goodArticle);

    @PostMapping("/goodArticle/updateGoodArticle")
    int updateGoodArticle(@RequestBody GoodArticle goodArticle);

    @PostMapping("/goodArticle/delGoodArticle")
    int delGoodArticle(@RequestParam("goodArticleId") String goodArticleId);

    @PostMapping("/goodArticle/updateRecommend")
    int updateRecommend(@RequestBody GoodArticle goodArticle);

    @PostMapping("/goodArticle/updateContent")
    int updateContent(@RequestBody GoodArticle goodArticle);

}
