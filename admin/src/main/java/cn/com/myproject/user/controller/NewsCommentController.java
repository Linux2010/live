package cn.com.myproject.user.controller;

import cn.com.myproject.live.entity.PO.CourseComment;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.INewsCommentService;
import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.PO.NewsReply;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李延超 on 2017/8/22.
 */
@RequestMapping("/newsComment")
@Controller
public class NewsCommentController {
    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(cn.com.myproject.user.controller.NewsController.class);

    @Autowired
    INewsCommentService newsCommentService1;

    @RequestMapping("/")
    public String index() {

        return "news/NewsComment";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<NewsComment> list(int page, int rows) {
        PageInfo<NewsComment> ncList = newsCommentService1.getAll(page,rows);
        return ncList;
    }

    @RequestMapping("/select")
    @ResponseBody
    public NewsComment select(Integer id) {
        return newsCommentService1.selectById(id);

    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(NewsComment newsComment) {
        Map<String, Object> result = new HashMap<>();
        try {
            newsCommentService1.add(newsComment);
            result.put("status", "success");
            result.put("message", "添加成功");

        } catch (RuntimeException e) {
            result.put("status", "fail");
            result.put("message", "添加失败");
            return result;
        }
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public int delete(Integer id) {
        int result =0;
        try {
            if (null == id || id <= 0) {
                result = 0;

                return result;
            }
            NewsComment newsComment = newsCommentService1.delById(id);
            if (null == newsComment) {
                result = 0;
                return result;
            }
            newsCommentService1.delById(id);
            result = 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return result = 0;
        }

        return result; }

    @ResponseBody
    @RequestMapping("/get")
    public NewsComment getById(Integer id){
        NewsComment newsComment = newsCommentService1.getById(id);
        if(newsComment != null){
            try {
                // 展示之前进行解码
                newsComment.setNewsContent(java.net.URLDecoder.decode(newsComment.getNewsContent(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newsComment;
    }

    @ResponseBody
    @RequestMapping("/addReply")
    public Map<String,Object> addReply(NewsComment newsComment,HttpServletRequest request){

        String id = request.getParameter("id");
        Map<String, Object> data = new HashMap<>();
        try {
            NewsComment newsComment2= newsCommentService1.getById(Integer.valueOf(id));
            if(null == newsComment2){
                data.put("status", "faile");
                data.put("message", "没有该评论！");
                return data;
            }
            newsComment2.setReplyTime(new Date().getTime());
            newsComment2.setReplyContent(newsComment.getReplyContent());
            if (newsCommentService1.updateCommentContent(newsComment2) == 1){
                data.put("status", "success");
                data.put("message", "回复失败！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "回复失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "回复失败！");
            return data;
        }
    }
}
