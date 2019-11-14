package cn.com.myproject.customer.mapper;


import cn.com.myproject.customer.entity.PO.UserFeedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFeedbackMapper {

    int deleteByPrimaryKey(String feedbackId);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(String feedbackId);

    List<UserFeedback> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("keyword")String keyword);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);

}