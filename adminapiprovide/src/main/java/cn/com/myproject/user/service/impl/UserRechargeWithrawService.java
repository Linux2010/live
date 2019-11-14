package cn.com.myproject.user.service.impl;

import cn.com.myproject.enums.CapitalLogDesEnum;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import cn.com.myproject.user.mapper.UserCapitalLogMapper;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import cn.com.myproject.user.mapper.UserMapper;
import cn.com.myproject.user.mapper.UserRechargeWithrawMapper;
import cn.com.myproject.user.service.IUserRechargeWithrawService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.pt.CalendarData_pt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.31
 */
@Service
public class UserRechargeWithrawService implements IUserRechargeWithrawService{

    @Autowired
    private UserRechargeWithrawMapper userRechargeWithrawMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private UserCapitalLogMapper userCapitalLogMapper;

    @Override
    public int deleteByPrimaryKey(String rwId) {
        return userRechargeWithrawMapper.deleteByPrimaryKey(rwId);
    }

    @Override
    public int insert(UserRechargeWithraw record) {
        return userRechargeWithrawMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(UserRechargeWithraw record) {

        if(StringUtils.isBlank(record.getUserId())){
            return 0;
        }

        if(2 == record.getOperateType()){ //提现
            BigDecimal money = record.getMoney();
            if(money.compareTo(BigDecimal.ZERO) != 0){
                money = BigDecimal.ZERO.subtract(money);
                UserCapital userCapital = userCapitalMapper.selectByUserId(record.getUserId());
                if(null == userCapital){ // 不够提现
                    return -1;
                }else{
                    if(userCapital.getTael().add(money).compareTo(BigDecimal.ZERO)<0){ // 不够提现
                        return -1;
                    }
                    userCapitalMapper.updateByPrimaryKeySelective(userCapital);
                }

                if(money.compareTo(BigDecimal.ZERO) != 0){
                    userCapital = userCapitalMapper.selectByUserId(record.getUserId());
                    UserCapitalLog userCapitalLog = new UserCapitalLog();
                    userCapitalLog.setAccountType((short)1);
                    userCapitalLog.setAccount(money);
                    userCapitalLog.setBalance(userCapital.getTael());
                    userCapitalLog.setUserId(record.getUserId());
                    userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-",""));
                    userCapitalLog.setCreateTime(new Date().getTime());
                    userCapitalLog.setDescription(CapitalLogDesEnum.WITHDRAW.getKey());
                    userCapitalLog.setRelationId(userCapital.getCapitalId());

                    if(money.compareTo(BigDecimal.ZERO) < 0){
                        userCapitalLog.setOperateType((short)-1);
                    }else if(money.compareTo(BigDecimal.ZERO) > 0){
                        userCapitalLog.setOperateType((short)1);
                    }

                    userCapitalLog.setRelationType((short)2);//关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
                    userCapitalLogMapper.insertSelective(userCapitalLog);
                }
            }
        }
        return userRechargeWithrawMapper.insertSelective(record);
    }

    @Override
    public UserRechargeWithraw selectByPrimaryKey(String rwId) {
        return userRechargeWithrawMapper.selectByPrimaryKey(rwId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRechargeWithraw record) {
        return userRechargeWithrawMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserRechargeWithraw record) {
        return userRechargeWithrawMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public int setPayFinishRecharge(String rwId,String transactionId) {
        int result = 0;
        if(StringUtils.isNotBlank(rwId)){
            UserRechargeWithraw record = userRechargeWithrawMapper.selectByPrimaryKey(rwId);
            if(null != record && 3 > record.getOpStatus()){
                record.setOpStatus((short) 3);
                BigDecimal tael = BigDecimal.ZERO;
                BigDecimal integral = BigDecimal.ZERO;
                if(1 == record.getAccountType()){//银两账户
                    tael = record.getMoney();
                }else if(2 == record.getAccountType()){//积分账户
                    integral = record.getMoney();
                }

                UserCapital userCapital = userCapitalMapper.selectByUserId(record.getUserId());
                if(null == userCapital){
                    userCapital = new UserCapital();
                    userCapital.setIntegral(integral);
                    userCapital.setTael(tael);
                    userCapital.setUserId(record.getUserId());
                    userCapital.setStatus((short)1);
                    userCapital.setVersion(1);
                    userCapital.setCreateTime(new Date().getTime());
                    userCapital.setCapitalId(UUID.randomUUID().toString().replace("-",""));
                    userCapitalMapper.insertSelective(userCapital);
                }else{
                    userCapital.setIntegral(integral);
                    userCapital.setTael(tael);
                    userCapitalMapper.updateByPrimaryKeySelective(userCapital);
                }

                if(integral.compareTo(BigDecimal.ZERO) != 0){
                    userCapital = userCapitalMapper.selectByUserId(record.getUserId());
                    UserCapitalLog userCapitalLog = new UserCapitalLog();
                    userCapitalLog.setAccountType((short)2);
                    userCapitalLog.setAccount(integral);
                    userCapitalLog.setBalance(userCapital.getIntegral());
                    userCapitalLog.setUserId(record.getUserId());
                    userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-",""));
                    userCapitalLog.setCreateTime(new Date().getTime());
                    userCapitalLog.setDescription(CapitalLogDesEnum.RECHARGE.getKey());
                    userCapitalLog.setRelationId(userCapital.getCapitalId());
                    if(integral.compareTo(BigDecimal.ZERO) < 0){
                        userCapitalLog.setOperateType((short)-1);
                    }else if(integral.compareTo(BigDecimal.ZERO) > 0){
                        userCapitalLog.setOperateType((short)1);
                    }
                    userCapitalLog.setRelationType((short)1);
                    userCapitalLogMapper.insertSelective(userCapitalLog);
                }

                if(tael.compareTo(BigDecimal.ZERO) != 0){
                    userCapital = userCapitalMapper.selectByUserId(record.getUserId());
                    UserCapitalLog userCapitalLog = new UserCapitalLog();
                    userCapitalLog.setAccountType((short)1);
                    userCapitalLog.setAccount(tael);
                    userCapitalLog.setBalance(userCapital.getTael());
                    userCapitalLog.setUserId(record.getUserId());
                    userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-",""));
                    userCapitalLog.setCreateTime(new Date().getTime());
                    userCapitalLog.setDescription(CapitalLogDesEnum.RECHARGE.getKey());
                    userCapitalLog.setRelationId(userCapital.getCapitalId());
                    if(integral.compareTo(BigDecimal.ZERO) < 0){
                        userCapitalLog.setOperateType((short)-1);
                    }else if(integral.compareTo(BigDecimal.ZERO) > 0){
                        userCapitalLog.setOperateType((short)1);
                    }
                    userCapitalLog.setRelationType((short)1);
                    userCapitalLogMapper.insertSelective(userCapitalLog);
                }

                if(1 == record.getAccountType()){ //银两账户
                    record.setBalance(userCapital.getTael());
                }else if(2 == record.getAccountType()){ //积分账户
                    record.setBalance(userCapital.getIntegral());
                }
                if(StringUtils.isNotBlank(transactionId)){
                    record.setTransactionId(transactionId);
                }
                result = userRechargeWithrawMapper.updateByPrimaryKeySelective(record);
            }
        }
        return result;
    }
}
