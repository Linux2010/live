package cn.com.myproject.user.mapper;
import cn.com.myproject.user.entity.PO.UserTeacherFocus;
import cn.com.myproject.user.entity.PO.UserTeacherRewardOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTeacherRewardOrderMapper {

    List<UserTeacherRewardOrder> getUserTeacherRewardOrderList(UserTeacherRewardOrder order);
    UserTeacherRewardOrder getUserTeacherRewardOrder(UserTeacherRewardOrder order);
    int insertUserTeacherRewardOrder(UserTeacherRewardOrder order);
    int updateUserTeacherRewardOrder(UserTeacherRewardOrder order);
}
