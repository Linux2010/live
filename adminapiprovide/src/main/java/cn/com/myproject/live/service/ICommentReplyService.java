package cn.com.myproject.live.service;

import cn.com.myproject.live.entity.PO.CommentReply;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public interface ICommentReplyService {

    void delreplay(String commid);

    void addreply(CommentReply commentReply);
}
