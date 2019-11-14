package cn.com.myproject.user.mapper;
import cn.com.myproject.user.entity.PO.UserTeacherFocus;
import com.netflix.ribbon.proxy.annotation.ClientProperties;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTeacherFocusMapper {
    UserTeacherFocus getUserTeacherFocus(UserTeacherFocus focus);
    int insertUserFocus(UserTeacherFocus focus);
    int updateUserFocusStatus(UserTeacherFocus focus);
    List<UserTeacherFocus> selectMyFocusTeachers(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("userId") String userId);
    List<UserTeacherFocus> selectFocusMeUsers(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,  @Param("userId") String userId);
    int deleteFocusMeAndMyFocus(@Param("userId") String userId);
}
