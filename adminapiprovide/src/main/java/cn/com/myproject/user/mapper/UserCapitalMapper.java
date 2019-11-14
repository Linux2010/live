package cn.com.myproject.user.mapper;

import cn.com.myproject.user.entity.PO.UserCapital;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserCapitalMapper {

    int deleteByPrimaryKey(String capitalId);

    int insert(UserCapital record);

    int insertSelective(UserCapital record);

    UserCapital selectByPrimaryKey(String capitalId);

    UserCapital selectByUserId(String userId);

    int updateByPrimaryKeySelective(UserCapital record);

    int updateByPrimaryKey(UserCapital record);

    List<UserCapital> getUserCapitalList(UserCapital record);

}