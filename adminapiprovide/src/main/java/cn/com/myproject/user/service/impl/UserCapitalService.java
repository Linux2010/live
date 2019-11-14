package cn.com.myproject.user.service.impl;

import cn.com.myproject.enums.CapitalLogDesEnum;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.mapper.UserCapitalLogMapper;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import cn.com.myproject.user.service.IUserCapitalService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@Service
public class UserCapitalService implements IUserCapitalService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private UserCapitalLogMapper userCapitalLogMapper;

    @Override
    public int deleteByPrimaryKey(String bid) {
        return userCapitalMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insert(UserCapital record) {
        return userCapitalMapper.insert(record);
    }

    @Override
    public int insertSelective(UserCapital record) {
        return userCapitalMapper.insertSelective(record);
    }

    @Override
    public UserCapital selectByPrimaryKey(String bid) {
        return userCapitalMapper.selectByPrimaryKey(bid);
    }

    @Override
    public UserCapital selectByUserId(String userId) {
        return userCapitalMapper.selectByUserId(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserCapital record) {
        return userCapitalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserCapital record) {
        return userCapitalMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateUserAmount(String userId, short accountType, String account, short relationType,String description)  throws Exception{
        int result = 0;
        try{
            UserCapital userCapital = userCapitalMapper.selectByUserId(userId);
            BigDecimal accountBig = new BigDecimal(account);
            UserCapital uc = new UserCapital();
            if(1 == accountType){ //银两账户
                uc.setTael(accountBig);
            }else if(2 == accountType){ //积分账户
                uc.setIntegral(accountBig);
            }
            uc.setCapitalId(userCapital.getCapitalId());
            int subUserCapitalResult = userCapitalMapper.updateByPrimaryKeySelective(uc);

            // 记录操作日志
            if (subUserCapitalResult > 0) {
                UserCapitalLog userCapitalLog = new UserCapitalLog();
                userCapitalLog.setAccount(accountBig); // 操作账目
                if(accountBig.compareTo(BigDecimal.ZERO)<0){
                    userCapitalLog.setOperateType((short) -1);
                }else{
                    userCapitalLog.setOperateType((short) 1);
                }
                userCapital = userCapitalMapper.selectByUserId(userId);
                if(1 == accountType){ //银两账户
                    userCapitalLog.setBalance(userCapital.getTael()); // 操作后账户银两
                }else if(2 == accountType){ //积分账户
                    userCapitalLog.setBalance(userCapital.getIntegral()); // 操作后账户积分
                }
                userCapitalLog.setAccountType(accountType);// 账户类型  1 银两账户  2 积分账户
                userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
                userCapitalLog.setDescription(description);
                userCapitalLog.setRelationType(relationType); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏  6、课程分润 7、会员分润 8、商品分润 9、分享课程 10、开设课程）
                userCapitalLog.setUserId(userId);
                userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
                userCapitalLog.setVersion(1);
                userCapitalLog.setStatus((short) 1);
                int userCapitalLogInsertResult = userCapitalLogMapper.insertSelective(userCapitalLog);
                if (userCapitalLogInsertResult > 0) {
                    result = 1;
                } else {
                    logger.error("新增账户金额记录日志失败");
                    throw new RuntimeException("新增账户金额记录日志失败");
                }
            }
        }catch (Exception e){
            logger.error("[{}]时，发生异常，异常信息为:[{}]","更新账户记录",e.getMessage());
            throw new RuntimeException("新增账户金额记录日志失败");
        }
        return result;
    }

    @Override
    public List<UserCapital> getUserCapitalList(UserCapital record) {
        return userCapitalMapper.getUserCapitalList(record);
    }
}
