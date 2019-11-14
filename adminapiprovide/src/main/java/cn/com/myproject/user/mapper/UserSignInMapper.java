package cn.com.myproject.user.mapper;


import cn.com.myproject.user.entity.PO.UserSignIn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSignInMapper {

    int deleteByPrimaryKey(String signInId);

    int insert(UserSignIn record);

    int insertSelective(UserSignIn record);

    UserSignIn selectByPrimaryKey(String signInId);

    UserSignIn selectByUserId(String userId);

    int updateByPrimaryKeySelective(UserSignIn record);

    int updateByPrimaryKey(UserSignIn record);

}