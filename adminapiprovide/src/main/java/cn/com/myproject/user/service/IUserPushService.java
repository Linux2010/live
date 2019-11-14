package cn.com.myproject.user.service;

import cn.com.myproject.user.entity.PO.UserPush;

/**
 * @auther CQC
 * @create 2017.9.14
 */
public interface IUserPushService {

    int deleteByPrimaryKey(String pushId);

    int insert(UserPush record);

    int insertSelective(UserPush record);

    UserPush selectByPrimaryKey(String pushId);

    int updateByPrimaryKeySelective(UserPush record);

    int updateByPrimaryKey(UserPush record);

}
