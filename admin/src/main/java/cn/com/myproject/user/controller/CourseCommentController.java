package cn.com.myproject.user.controller;

import cn.com.myproject.live.entity.PO.CommentReply;
import cn.com.myproject.live.entity.PO.CourseComment;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.ICommentReplyService;
import cn.com.myproject.service.ICourseCommentService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by JYP on 2017/8/14 0014.
 */
@RequestMapping("/comment")
@Controller
public class CourseCommentController {

    public static final Logger logger = LoggerFactory.getLogger(CourseCommentController.class);

    @Autowired
    private ICourseCommentService courseCommentService;

    @Autowired
    private ICommentReplyService commentReplyService;

    @RequestMapping("/")
    public String index(){
        return "buiness/comment";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<CourseComment> getPage(int rows,int page){
       PageInfo<CourseComment> alllist = courseCommentService.getPage(page,rows);
        return alllist;
    }

    @ResponseBody
    @RequestMapping("/get")
    public CourseComment getById(String commid){
        CourseComment courseComment = courseCommentService.getById(commid);
        if(courseComment != null){
            try {
                // 展示之前进行解码
                courseComment.setCommentContent(java.net.URLDecoder.decode(courseComment.getCommentContent(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return courseComment;
    }

    @ResponseBody
    @RequestMapping("/del")
    public Integer delcomm(String commid){
        Integer info = 0;
        try{
            courseCommentService.delcomm(commid);
            commentReplyService.delreplay(commid);//删除评论以及关于评论的回复
            info = 1;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return info;
    }
    @ResponseBody
    @RequestMapping("/replay/add")
    public Integer addreply(CommentReply commentReply, HttpServletRequest request){
        int info = 0;
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(null == context) {
            info = 0;
        }

        SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
        if(null == user) {
            info = 0;
        }
        try{
            commentReply.setCourseCommentId(commentReply.getCourseCommentId());
            commentReply.setReplayContent(commentReply.getReplayContent());
            commentReply.setCourseReplyId(UUID.randomUUID().toString().replace("-", ""));
            commentReply.setReplayUserId(user.getUserId());//登录人userId
            commentReply.setReplayTime(new Date().getTime());
            commentReplyService.addreply(commentReply);
            info = 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return info;
    }
}
