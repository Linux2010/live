package cn.com.myproject.user.mapper;


import cn.com.myproject.user.entity.PO.UserPush;

public interface UserPushMapper {

    int deleteByPrimaryKey(String pushId);

    int insert(UserPush record);

    int insertSelective(UserPush record);

    UserPush selectByPrimaryKey(String pushId);

    int updateByPrimaryKeySelective(UserPush record);

    int updateByPrimaryKey(UserPush record);

}