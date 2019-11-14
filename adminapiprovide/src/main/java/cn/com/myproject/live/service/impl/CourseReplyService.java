package cn.com.myproject.live.service.impl;

import cn.com.myproject.live.mapper.CommentReplyMapper;
import cn.com.myproject.live.service.ICommentReplyService;
import cn.com.myproject.live.entity.PO.CommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
@Service
public class CourseReplyService implements ICommentReplyService {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Transactional
    @Override
    public void delreplay(String commid) {
        commentReplyMapper.delreplay(commid);
    }

    @Transactional
    @Override
    public void addreply(CommentReply commentReply) {
        commentReplyMapper.addreply(commentReply);
    }
}
