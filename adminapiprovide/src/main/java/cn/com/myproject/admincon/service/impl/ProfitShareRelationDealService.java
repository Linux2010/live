package cn.com.myproject.admincon.service.impl;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.mapper.ProfitShareRelationMapper;
import cn.com.myproject.admincon.mapper.ProfitShareSettingMapper;
import cn.com.myproject.admincon.service.IProfitShareRelationDealService;
import cn.com.myproject.admincon.service.IProfitShareRelationService;
import cn.com.myproject.enums.CapitalLogDesEnum;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.live.mapper.CourseOrderMapper;
import cn.com.myproject.rechargeMember.mapper.RechargeMemberMapper;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.mapper.UserCapitalLogMapper;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import cn.com.myproject.user.mapper.UserMapper;
import cn.com.myproject.util.DecimalCalculateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.9.5
 */
@Service
public class ProfitShareRelationDealService implements IProfitShareRelationDealService{

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Autowired
    private ProfitShareRelationMapper profitShareRelationMapper;

    @Autowired
    private ProfitShareSettingMapper profitShareSettingMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private UserCapitalLogMapper userCapitalLogMapper;

    @Autowired
    private RechargeMemberMapper rechargeMemberMapper;

    /**
     * 课程订单
     * @param courseOrderId
     * @return
     */
    @Override
    public int dealCourseOrder(String courseOrderId) {
        int result = 0;
        if (StringUtils.isNotBlank(courseOrderId)) {

            CourseOrder courseOrder = courseOrderMapper.getById(courseOrderId);

            if (null != courseOrder && courseOrder.getOrderStatus() == 1 && courseOrder.getPayStatus() == 1) {

                ProfitShareSetting profitShareSetting = profitShareSettingMapper.selectByType(1);

                if (null != profitShareSetting) {

                    String userId = courseOrder.getUserId();
                    BigDecimal totalMoney = new BigDecimal(String.valueOf(courseOrder.getTotalMoney())).multiply(new BigDecimal("10"));

                    BigDecimal primaryRule = profitShareSetting.getPrimaryRule();      // 一级分销百分比
                    BigDecimal secondaryRule = profitShareSetting.getSecondaryRule(); //二级分销百分比

                    ProfitShareRelation profitShareRelation = profitShareRelationMapper.selectByUserId(userId); //上级用户

                    if (null != profitShareRelation) {
                        if (null != primaryRule && primaryRule.compareTo(BigDecimal.ZERO) > 0) {
                            double priMaryDou = DecimalCalculateUtil.div(DecimalCalculateUtil.mul(totalMoney.doubleValue(), primaryRule.doubleValue()), 100d, 2);
                            updateUserCapitalTael(profitShareRelation.getParentUserId(),new BigDecimal(String.valueOf(priMaryDou)),(short) 6,CapitalLogDesEnum.SHAREPROFIT_COURSE.getKey());
                        }

                        profitShareRelation = profitShareRelationMapper.selectByUserId(profitShareRelation.getParentUserId()); //上上级用户

                        if(null != profitShareRelation){
                            if (null != secondaryRule && secondaryRule.compareTo(BigDecimal.ZERO) > 0) {
                                double secondaryDou = DecimalCalculateUtil.div(DecimalCalculateUtil.mul(totalMoney.doubleValue(), secondaryRule.doubleValue()), 100d, 2);
                                updateUserCapitalTael(profitShareRelation.getParentUserId(),new BigDecimal(String.valueOf(secondaryDou)),(short) 6,CapitalLogDesEnum.SHAREPROFIT_COURSE.getKey());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 会员购买VIP
     * @param rechargeMemberId
     * @return
     */
    @Override
    public int dealBuyMemberType(String orderNo) {
        int result = 0;
        RechargeMember rechargeMember = rechargeMemberMapper.selectByOrderOn(orderNo);
        if(null != rechargeMember && rechargeMember.getPayStatus() == 1){
            ProfitShareSetting profitShareSetting = profitShareSettingMapper.selectByType(3); //分润类型 1、课程分润  2、商品分润  3、会员分润
            if (null != profitShareSetting) {

                String userId = rechargeMember.getUserId();

                BigDecimal totalMoney = new BigDecimal(String.valueOf(rechargeMember.getRechargeMoney()));

                if(rechargeMember.getPayType() == 1){ //支付类型 1、银两支付 2、支付宝 3、微信 4、AndroidPay 5、paypal
                    totalMoney = new BigDecimal(String.valueOf(rechargeMember.getRechargeMoney()));
                }else{
                    totalMoney = new BigDecimal(String.valueOf(rechargeMember.getRechargeMoney())).multiply(new BigDecimal("10"));
                }


                BigDecimal primaryRule = profitShareSetting.getPrimaryRule();      // 一级分销百分比
                BigDecimal secondaryRule = profitShareSetting.getSecondaryRule(); //二级分销百分比

                ProfitShareRelation profitShareRelation = profitShareRelationMapper.selectByUserId(userId); //上级用户

                if (null != profitShareRelation) {
                    if (null != primaryRule && primaryRule.compareTo(BigDecimal.ZERO) > 0) {
                        double priMaryDou = DecimalCalculateUtil.div(DecimalCalculateUtil.mul(totalMoney.doubleValue(), primaryRule.doubleValue()), 100d, 2);
                        updateUserCapitalTael(profitShareRelation.getParentUserId(),new BigDecimal(String.valueOf(priMaryDou)),(short) 7,CapitalLogDesEnum.SHAREPROFIT_MEMBER.getKey());
                    }

                    profitShareRelation = profitShareRelationMapper.selectByUserId(profitShareRelation.getParentUserId()); //上上级用户

                    if(null != profitShareRelation){
                        if (null != secondaryRule && secondaryRule.compareTo(BigDecimal.ZERO) > 0) {
                            double secondaryDou = DecimalCalculateUtil.div(DecimalCalculateUtil.mul(totalMoney.doubleValue(), secondaryRule.doubleValue()), 100d, 2);
                            updateUserCapitalTael(profitShareRelation.getParentUserId(),new BigDecimal(String.valueOf(secondaryDou)),(short) 7,CapitalLogDesEnum.SHAREPROFIT_MEMBER.getKey());
                        }
                    }
                }

            }
        }
        return result;
    }

    private int updateUserCapitalTael(String userId,BigDecimal account,short relationType,String description){
        int res = 0;
        UserCapital userCapital = userCapitalMapper.selectByUserId(userId);
        if (null == userCapital) {
            userCapital = new UserCapital();
            userCapital.setCapitalId(UUID.randomUUID().toString().replace("-", ""));
            userCapital.setCreateTime(new Date().getTime());
            userCapital.setVersion(1);
            userCapital.setStatus((short) 1);
            userCapital.setUserId(userId);
            userCapital.setTael(account);
            userCapitalMapper.insertSelective(userCapital);
        } else {
            UserCapital _userCapital = new UserCapital();
            _userCapital.setTael(account);
            _userCapital.setCapitalId(userCapital.getCapitalId());
            userCapitalMapper.updateByPrimaryKeySelective(_userCapital);
        }
        userCapital = userCapitalMapper.selectByUserId(userCapital.getUserId());
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        userCapitalLog.setAccountType((short) 1);
        userCapitalLog.setAccount(account);
        userCapitalLog.setBalance(userCapital.getTael());
        userCapitalLog.setUserId(userCapital.getUserId());
        userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
        userCapitalLog.setCreateTime(new Date().getTime());
        userCapitalLog.setDescription(description);
        userCapitalLog.setRelationId(userCapital.getCapitalId());
        userCapitalLog.setOperateType((short) 1);
        userCapitalLog.setRelationType(relationType);
        res = userCapitalLogMapper.insertSelective(userCapitalLog);
        return res;
    }
}
