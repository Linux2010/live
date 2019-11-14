package cn.com.myproject.user.service.impl;

import cn.com.myproject.user.entity.PO.UserPush;
import cn.com.myproject.user.mapper.UserPushMapper;
import cn.com.myproject.user.service.IUserPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther CQC
 * @create 2017.9.14
 */
public class UserPushService implements IUserPushService{

    private static final Logger logger = LoggerFactory.getLogger(UserPushService.class);


    @Autowired
    private UserPushMapper userPushMapper;

    @Override
    public int deleteByPrimaryKey(String pushId) {
        return userPushMapper.deleteByPrimaryKey(pushId);
    }

    @Override
    public int insert(UserPush record) {
        return userPushMapper.insert(record);
    }

    @Override
    public int insertSelective(UserPush record) {
        return userPushMapper.insertSelective(record);
    }

    @Override
    public UserPush selectByPrimaryKey(String pushId) {
        return userPushMapper.selectByPrimaryKey(pushId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserPush record) {
        return userPushMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserPush record) {
        return userPushMapper.updateByPrimaryKeySelective(record);
    }
}
