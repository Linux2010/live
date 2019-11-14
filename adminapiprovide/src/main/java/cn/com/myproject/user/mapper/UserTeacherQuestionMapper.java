package cn.com.myproject.user.mapper;
import cn.com.myproject.user.entity.PO.UserTeacherQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTeacherQuestionMapper {
  //  List<UserTeacherQuestion> getUserTeacherQuestionList(UserTeacherQuestion question);

    List<UserTeacherQuestion> getUserTeacherQuestionAndReplyList(@Param("pageNumKey") int pageNum,
                                                                 @Param("pageSizeKey") int pageSize,
                                                                 @Param("teacherId") String teacherId,
                                                                 @Param("userId") String userId);


    int insertUserTeacherQuestion(UserTeacherQuestion question);


    /**
     * 我的问答列表
     * @param userId
     * @return
     */
   List<UserTeacherQuestion> myQuestionsList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("userId") String userId);


    /**
     * 他人提问列表
     * @param teacherId
     * @return
     */
   List<UserTeacherQuestion> otherQuestionsList(@Param("pageNumKey") int pageNum,
                                                @Param("pageSizeKey") int pageSize,
                                                @Param("teacherId") String teacherId);

}
