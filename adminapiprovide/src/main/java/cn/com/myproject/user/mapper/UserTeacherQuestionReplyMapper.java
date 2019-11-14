package cn.com.myproject.user.mapper;
import cn.com.myproject.user.entity.PO.UserTeacherQuestionReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTeacherQuestionReplyMapper {
    List<UserTeacherQuestionReply> getUserTeacherQuestionReplyList(UserTeacherQuestionReply reply);

    UserTeacherQuestionReply getUserTeacherQuestionReply(UserTeacherQuestionReply reply);

    int insertUserTeacherQuestionReply(UserTeacherQuestionReply reply);
    
    int updateUserTeacherQuestionReply(UserTeacherQuestionReply reply);
}
