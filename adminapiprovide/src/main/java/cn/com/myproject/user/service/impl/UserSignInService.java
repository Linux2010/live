package cn.com.myproject.user.service.impl;

import cn.com.myproject.configSetting.mapper.ConfigSettingMapper;
import cn.com.myproject.enums.CapitalLogDesEnum;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.entity.PO.UserSignIn;
import cn.com.myproject.user.mapper.UserCapitalLogMapper;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import cn.com.myproject.user.mapper.UserSignInMapper;
import cn.com.myproject.user.service.IUserSignInService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@Service
public class UserSignInService implements IUserSignInService{

    @Autowired
    private UserSignInMapper userSignInMapper;

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private ConfigSettingMapper configSettingMapper;

    @Autowired
    private UserCapitalLogMapper userCapitalLogMapper;

    @Override
    public int deleteByPrimaryKey(String bid) {
        return userSignInMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insert(UserSignIn record) {
        return userSignInMapper.insert(record);
    }

    @Override
    @Transient
    public int insertSelective(UserSignIn record) {

        if(StringUtils.isBlank(record.getUserId())){
            return 0;
        }

        //签到送积分
        ConfigSetting configSetting = configSettingMapper.selectConfigSettingBySign("signIn");
        if(null != configSetting && StringUtils.isNotBlank(configSetting.getConfig_value())){

            BigDecimal integral = new BigDecimal(configSetting.getConfig_value());
            UserCapital userCapital = userCapitalMapper.selectByUserId(record.getUserId());

            if(null == userCapital){
                userCapital = new UserCapital();
                userCapital.setIntegral(integral);
                userCapital.setUserId(record.getUserId());
                userCapital.setStatus((short)1);
                userCapital.setVersion(1);
                userCapital.setCreateTime(new Date().getTime());
                userCapital.setCapitalId(UUID.randomUUID().toString().replace("-",""));
                userCapitalMapper.insertSelective(userCapital);
            }else{
                UserCapital _userCapital = new UserCapital();
                _userCapital.setIntegral(integral);
                _userCapital.setUserId(record.getUserId());
                _userCapital.setCapitalId(userCapital.getCapitalId());
                userCapitalMapper.updateByPrimaryKeySelective(_userCapital);
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
                userCapitalLog.setDescription(CapitalLogDesEnum.SIGNIN.getKey());
                userCapitalLog.setRelationId(userCapital.getCapitalId());
                if(integral.compareTo(BigDecimal.ZERO) < 0){
                    userCapitalLog.setOperateType((short)-1);
                }else if(integral.compareTo(BigDecimal.ZERO) > 0){
                    userCapitalLog.setOperateType((short)1);
                }
                userCapitalLog.setRelationType((short)4);
                userCapitalLogMapper.insertSelective(userCapitalLog);
            }
        }
        return userSignInMapper.insertSelective(record);
    }

    @Override
    public UserSignIn selectByPrimaryKey(String bid) {
        return userSignInMapper.selectByPrimaryKey(bid);
    }

    @Override
    public UserSignIn selectByUserId(String userId) {
        return userSignInMapper.selectByUserId(userId);
    }

    @Override
    @Transient
    public int updateByPrimaryKeySelective(UserSignIn record) {

        if(StringUtils.isBlank(record.getUserId())){
            return 0;
        }

        //签到送积分
        ConfigSetting configSetting = configSettingMapper.selectConfigSettingBySign("signIn");
        if(null != configSetting && StringUtils.isNotBlank(configSetting.getConfig_value())){

            BigDecimal integral = new BigDecimal(configSetting.getConfig_value());
            UserCapital userCapital = userCapitalMapper.selectByUserId(record.getUserId());

            if(null == userCapital){
                userCapital = new UserCapital();
                userCapital.setIntegral(integral);
                userCapital.setUserId(record.getUserId());
                userCapital.setStatus((short)1);
                userCapital.setVersion(1);
                userCapital.setCreateTime(new Date().getTime());
                userCapital.setCapitalId(UUID.randomUUID().toString().replace("-",""));
                userCapitalMapper.insertSelective(userCapital);
            }else{
                UserCapital _userCapital = new UserCapital();
                _userCapital.setIntegral(integral);
                _userCapital.setUserId(record.getUserId());
                _userCapital.setCapitalId(userCapital.getCapitalId());
                userCapitalMapper.updateByPrimaryKeySelective(_userCapital);
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
                userCapitalLog.setDescription(CapitalLogDesEnum.SIGNIN.getKey());
                userCapitalLog.setRelationId(userCapital.getCapitalId());
                if(integral.compareTo(BigDecimal.ZERO) < 0){
                    userCapitalLog.setOperateType((short)-1);
                }else if(integral.compareTo(BigDecimal.ZERO) > 0){
                    userCapitalLog.setOperateType((short)1);
                }
                userCapitalLog.setRelationType((short)4);
                userCapitalLogMapper.insertSelective(userCapitalLog);
            }
        }
        return userSignInMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserSignIn record) {
        return userSignInMapper.updateByPrimaryKey(record);
    }
}
