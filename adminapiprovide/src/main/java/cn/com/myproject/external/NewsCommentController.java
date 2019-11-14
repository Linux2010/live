package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.VO.NewsCommentVO;
import cn.com.myproject.user.entity.VO.NewsReplyVO;
import cn.com.myproject.news.service.ImplNewsCommentService;
import cn.com.myproject.user.entity.PO.*;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 李延超 on 2017/8/22.
 */
@RestController
@RequestMapping("/NewsComment")
public class NewsCommentController {
    public static final Logger logger = LoggerFactory.getLogger(NewsCommentController.class);
    @Autowired
    private ImplNewsCommentService commentService;

    @GetMapping("/getAll")
    public PageInfo<NewsComment> getAll(int pageNum,int pageSize) {
        return commentService.getAll(pageNum,pageSize);
    }

    @PostMapping("/selectById")
    public NewsComment selectById(Integer id) {
        return commentService.selectById(id);
    }

    @PostMapping("/delById")
    public void delById(Integer id) {
        commentService.delNewsComment(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody NewsComment newsComment) {
        String result = "";
        try {
            newsComment.setNewsContent(newsComment.getNewsContent());
            newsComment.setNewsName(newsComment.getNewsName());
            newsComment.setNewsUsername(newsComment.getNewsUsername());
            newsComment.setCreateTime(new Date().getTime());
            newsComment.setStatus((short) 1);
            newsComment.setVersion(1);
            commentService.addNewsComment(newsComment);
            result = "1";
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = "0";
        }
        return result;
    }

    @PostMapping("/get")
    NewsComment getById(Integer id) {
        return commentService.getById(id);
    }
//添加回复
    @PostMapping("/reply")
    public String addReply(@RequestBody NewsReply newsReply) {
        String result = "";
        try {
            commentService.addReply(newsReply);

            newsReply.setCreateTime(new Date().getTime());
            newsReply.setVersion(1);
            newsReply.setStatus((short)1);
          newsReply.setReplyContent(newsReply.getReplyContent());
          newsReply.setReplyId(newsReply.getReplyId());

            result = "1";
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = "0";
        }
        return result;
    }


    @PostMapping("/getPage")
   public PageInfo<NewsComment> getPage(int pageNum ,int pageSize){
        return commentService.getPage(pageNum,pageSize);
    }
    @PostMapping("/getById1")
    public NewsComment getById1(@RequestParam("newsCommentId") String newsCommentId){
       return commentService.getById(newsCommentId);
    }

//    @PostMapping("/addcomm")
//    public Integer addcomm( @RequestBody NewsComment newsComment) {
//
//        int info = 0;
//        try {
//            commentService.addcomm(newsComment);
//         info=1;
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        return info;
//    }
   @PostMapping("/delcomm")
    public Integer delcomm(@RequestParam("newsCommentId")String newsCommentId){
        int info=0;
        try{
            commentService.delcomm(newsCommentId);
            info=1;
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return info;
   }

   @PostMapping("/checkcomment")
    public Integer checkcomment(@RequestBody NewsComment newsComment){
        return commentService.checkcomment(newsComment);
   }

   @PostMapping("/addCn")
    public boolean addCn(@RequestBody NewsCollect newsCollect){
        return  commentService.addCn(newsCollect);
   }

   @PostMapping("/removeCn")
    public boolean removeNc(@RequestBody NewsCollect newsCollect){
        return commentService.removeCn(newsCollect);
   }

    @GetMapping("/searcUserNewCollect")
    public NewsCollect searcUserNewCollect(String userId ,String newsId){
        return  commentService.searcUserNewCollect(userId ,newsId);
    }

    @GetMapping("/searchCcList")
    public List<NewsComment> searchCcList(int pageNum, int pageSize, String newsId){
        return commentService.searchCcList(pageNum,pageSize,newsId);
    }

    @GetMapping("/searchCrList")
    public List<NewsReplyVO> searchCrList(String newsCommentId){
        return commentService.searchCrList(newsCommentId);
    }


@PostMapping("/updateColl")
    public boolean updatecollect(@RequestBody NewsCollect newsCollect){
   return  commentService.updatecollect(newsCollect);
}

    @PostMapping("/replyNewsComment")
    public int replyNewsComment(@RequestBody NewsComment newsComment){

        int result = 0;
        try {
            commentService.replyNewsComment(newsComment);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;

    }

    @PostMapping("/selectReply")
    public NewsReply selectById(String replyId){

        return commentService.selectReply(replyId);
    }

    @PostMapping("/addComm")
    public boolean addcomm(@RequestBody NewsComment newsComment){

        boolean falg = false;
        try {
            newsComment.setNewsCommentId(UUID.randomUUID().toString().replace("-", ""));
            newsComment.setCreateTime(System.currentTimeMillis());
            newsComment.setVersion(1);
            newsComment.setStatus((short)1);
            commentService.addComment(newsComment);
            falg = true;
        }catch (RuntimeException e){
            e.printStackTrace();
            falg = false;
        }
        return falg;
    }

    @PostMapping("/updateCommentContent")
    public int updateCommentContent(@RequestBody NewsComment newsComment){

        int result = 0;
        try {
            commentService.updateCommentContent(newsComment);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }
}