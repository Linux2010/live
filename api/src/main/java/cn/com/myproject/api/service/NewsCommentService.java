package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.News;
import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.VO.NewsCommentVO;
import cn.com.myproject.user.entity.VO.NewsReplyVO;
import cn.com.myproject.user.entity.PO.NewsCollect;
import cn.com.myproject.user.entity.PO.NewsComment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/30.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface NewsCommentService {

//    @PostMapping("/NewsComment/addcomm")
//    public boolean addComm(@RequestBody NewsComment newsComment);

    @GetMapping("/NewsComment/searcUserNewCollect")
    public NewsCollect searcUserNewCollect(@RequestParam("userId") String userId ,@RequestParam("newsId") String newsId);

    @PostMapping("/NewsComment/addCn")
    public boolean addCn(@RequestBody NewsCollect newsCollect);

    @PostMapping("/NewsComment/removeCn")
   public boolean removeCn(@RequestBody NewsCollect newsCollect);

    @GetMapping("/NewsComment/searchCcList")
    public List<NewsComment> searchCcList(@RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize,
                                              @RequestParam("newsId") String newsId);


    @GetMapping("/NewsComment/searchCrList")
    public List<NewsReplyVO> searchCrList(@RequestParam("newsCommentId") String newsCommentId);

    @PostMapping("/NewsComment/addComm")
    public boolean addcomm(@RequestBody NewsComment newsComment);

    @PostMapping("/NewsComment/updateColl")
    public  boolean updatecollect(@RequestBody NewsCollect newsCollect);

}
