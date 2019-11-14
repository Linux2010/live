package cn.com.myproject.user.service;


import cn.com.myproject.user.entity.PO.UserSignIn;

public interface IUserSignInService {

    int deleteByPrimaryKey(String bid);

    int insert(UserSignIn record);

    int insertSelective(UserSignIn record);

    UserSignIn selectByPrimaryKey(String bid);

    UserSignIn selectByUserId(String userId);

    int updateByPrimaryKeySelective(UserSignIn record);

    int updateByPrimaryKey(UserSignIn record);

}