package cn.com.myproject.user.controller;

import cn.com.myproject.live.entity.PO.CommentReply;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.ICommentReplyService;
import cn.com.myproject.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@RequestMapping("/reply")
@Controller
public class CommentReplyController {

    @Autowired
    private ICommentReplyService commentReplyService;

    @ResponseBody
    @RequestMapping("/addreply")
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
