package cn.com.myproject.external;

import cn.com.myproject.live.service.ICommentReplyService;
import cn.com.myproject.live.entity.PO.CommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@RestController
@RequestMapping("/reply")
public class CourseReplyController {

    @Autowired
    private ICommentReplyService commentReplyService;

    @PostMapping("/delreplay")
    public Integer delreplay(@RequestBody String commid){
        int info = 0;
        try{
            commentReplyService.delreplay(commid);
            info = 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return info;
    }

    @PostMapping("/addreply")
    public Integer addreply(@RequestBody CommentReply commentReply){
        int info = 0;
        try{
            commentReplyService.addreply(commentReply);
            info = 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return info;
    }
}
