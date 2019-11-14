package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.UserPush;
import cn.com.myproject.user.service.IUserPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther CQC
 * @create 2017.9.14
 */
public class UserPushController {

    private static final Logger logger = LoggerFactory.getLogger(UserPushController.class);

    @Autowired
    private IUserPushService userPushService;

    int deleteByPrimaryKey(String pushId){
        return userPushService.deleteByPrimaryKey(pushId);
    }

    int insert(UserPush record){
        return userPushService.insert(record);
    }

    int insertSelective(UserPush record){
        return userPushService.insertSelective(record);
    }

    UserPush selectByPrimaryKey(String pushId){
        return userPushService.selectByPrimaryKey(pushId);
    }

    int updateByPrimaryKeySelective(UserPush record){
        return userPushService.updateByPrimaryKeySelective(record);
    }

    int updateByPrimaryKey(UserPush record){
        return userPushService.updateByPrimaryKey(record);
    }

}
