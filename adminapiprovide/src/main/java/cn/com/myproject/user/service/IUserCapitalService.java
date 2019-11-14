package cn.com.myproject.user.service;

import cn.com.myproject.user.entity.PO.UserCapital;

import java.util.List;

public interface IUserCapitalService {

    int deleteByPrimaryKey(String bid);

    int insert(UserCapital record);

    int insertSelective(UserCapital record);

    UserCapital selectByPrimaryKey(String bid);

    UserCapital selectByUserId(String userId);

    int updateByPrimaryKeySelective(UserCapital record);

    int updateByPrimaryKey(UserCapital record);

    int updateUserAmount(String userId,short accountType,String accout,short relationType,String description) throws Exception;

    List<UserCapital> getUserCapitalList(UserCapital record);

}