package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by JYP on 2017/8/28 0028.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IRecSettingsService {

   //推荐-今日课程
   @PostMapping("/recsetting/select_tui_tcour")
   List<Course> select_tui_tcour(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

   //今日- 今日推荐课程
   @PostMapping("/recsetting/select_today_gcour")
   List<Course> select_today_gcour(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize);

   //今日- 优质课程
   @PostMapping("/recsetting/select_today_excellentcour")
   List<Course> select_today_excellentcour(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize);

   //今日- 优质商品
   @PostMapping("/recsetting/select_today_goods")
   List<Course> select_today_goods(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize);


   //学习-今日
   @PostMapping("/recsetting/select_recomm_teacher")
   List<User> select_recomm_teacher(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

   //今日- 今日推荐课程
   @PostMapping("/recsetting/select_today_gcour_by_label")
   List<Course> select_today_gcour_by_label(@RequestParam("courseTagId") String courseTagId,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize);
}
