package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.PO.NewsReply;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/22.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface INewsCommentService {
    @GetMapping("/NewsComment/getAll")
    PageInfo<NewsComment> getAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

      @PostMapping("/NewsComment/selectById")
    NewsComment selectById(@RequestParam("id") Integer id);
   @PostMapping("/NewsComment/delById")
   NewsComment delById(@RequestParam("id") Integer id);

   @PostMapping("/NewsComment/add")
    void add(@RequestBody NewsComment newsComment);

   @PostMapping("/NewsComment/get")
    NewsComment getById(@RequestParam("id") Integer id);

@PostMapping("/NewsComment/reply")
    String addReply(@RequestBody NewsReply newsReply);

    @PostMapping("/NewsComment/replyNewsComment")
    int replyNewsComment(@RequestBody NewsComment newsComment);

    @PostMapping("/NewsComment/selectReply")
    NewsReply selectReply(@RequestParam("replyId") String replyId);

    @PostMapping("/NewsComment/updateCommentContent")
    int updateCommentContent(@RequestBody NewsComment newsComment);
   }







