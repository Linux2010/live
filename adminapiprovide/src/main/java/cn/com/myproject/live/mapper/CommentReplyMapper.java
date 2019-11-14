package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.CommentReply;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by JYP on 2017/8/14 0014.
 */
@Mapper
public interface CommentReplyMapper {

    void delreplay(String commid);

    void addreply(CommentReply commentReply);
}
