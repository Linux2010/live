package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.user.entity.PO.GoodArticle;
import cn.com.myproject.user.entity.PO.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by JYP on 2017/8/28 0028.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IGoodArticleService {
   //今日- 好文推荐
   @GetMapping("/goodArticle/select_good_article")
   List<GoodArticle> select_good_article(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);


   //今日学习
   @PostMapping("/course_order/select_today_study")
   List<Course> select_today_study(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("userId") String userId);


   //今日 - 好文推荐 - 详情
   @PostMapping("/goodArticle/selectById")
   GoodArticle selectById(@RequestParam("goodArticleId") String goodArticleId);




}
