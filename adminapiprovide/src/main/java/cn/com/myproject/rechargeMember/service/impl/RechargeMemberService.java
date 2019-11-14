package cn.com.myproject.rechargeMember.service.impl;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.service.impl.ProfitShareRelationDealService;
import cn.com.myproject.admincon.service.impl.ProfitShareRelationService;
import cn.com.myproject.aliyun.push.IAliyunPushService;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.service.IMessageRecordService;
import cn.com.myproject.enums.CapitalLogDesEnum;
import cn.com.myproject.rechargeMember.mapper.RechargeMemberMapper;
import cn.com.myproject.rechargeMember.service.IRechargeMemberService;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import cn.com.myproject.user.mapper.UserMapper;
import cn.com.myproject.user.service.impl.UserCapitalLogService;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * Created by Administrator on 2017/8/29 0007.
 */
@Service
public class RechargeMemberService implements IRechargeMemberService {

    @Autowired
    private RechargeMemberMapper rechargeMemberMapper;

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private UserCapitalLogService userCapitalLogService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfitShareRelationDealService  profitShareRelationDealService;

    @Autowired
    private ProfitShareRelationService profitShareRelationService;

    @Autowired
    private IAliyunPushService aliyunPushService;

    @Autowired
    private IMessageRecordService messageRecordService;

    @Override
    public PageInfo<RechargeMember> selectAll(int pageNum, int pageSize) {

        List<RechargeMember> list = rechargeMemberMapper.selectAll(pageNum, pageSize);
        return convert(list);
    }

    @Override
    public RechargeMember selectByUserId(String userId) {

        return rechargeMemberMapper.selectByUserId(userId);
    }

    @Override
    public RechargeMember selectByRechargeMemberId(String rechargeMemberId) {

        return rechargeMemberMapper.selectByRechargeMemberId(rechargeMemberId);
    }

    @Override
    public int addRechargeMember(RechargeMember rechargeMember) {
        int result = 0;

        if (rechargeMember.getPayType() == 1){//银两支付
            User  user = userMapper.selectById(rechargeMember.getUserId());
            UserCapitalLog userCapitalLog = new UserCapitalLog();
            UserCapital userCapital = userCapitalMapper.selectByUserId(rechargeMember.getUserId());
            BigDecimal minus_money = new BigDecimal(rechargeMember.getRechargeMoney()).multiply(new BigDecimal(-1));//消费金额
            BigDecimal userMoney = userCapital.getTael();
            BigDecimal money = minus_money.multiply(new BigDecimal(10));
            userCapital.setTael(money);
            userCapital.setUserId(rechargeMember.getUserId());
            int updateresult = userCapitalMapper.updateByPrimaryKeySelective(userCapital);
            if (updateresult > 0){
                BigDecimal account = new BigDecimal(rechargeMember.getRechargeMoney()).multiply(new BigDecimal(10));
                userCapitalLog.setAccount(account); // 操作账目
                userCapitalLog.setOperateType((short) -1); // 操作类型   -1 减  1  加
                BigDecimal balance = userMoney.subtract(new BigDecimal(rechargeMember.getRechargeMoney()).multiply(new BigDecimal(10)));
                userCapitalLog.setBalance(balance); // 操作后账户余额 // 此处注意userCapital.getTael()是否已经被减去
                userCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户
                userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
                userCapitalLog.setDescription(CapitalLogDesEnum.BUY_VIP.getKey());
                userCapitalLog.setRelationType((short) 3); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
                userCapitalLog.setUserId(rechargeMember.getUserId());
                userCapitalLog.setCreateTime(new Date().getTime());
                userCapitalLog.setVersion(1);
            }
            result = userCapitalLogService.insertSelective(userCapitalLog);
            if(result > 0){
                User _user = new User();
                _user.setUserId(rechargeMember.getUserId());
                _user.setUserIdentity(2);
                _user.setExpirationDate(rechargeMember.getExpirationDate());
                result = userMapper.updateVIP(_user);
                rechargeMemberMapper.updateRechargeMember(rechargeMember.getRechargeMemberId());
                pushBuyVIPMessage(user);
            }
        }
        result = rechargeMemberMapper.addRechargeMember(rechargeMember);
        return result;
    }

    @Override
    public void updateRechargeMember(String rechargeMemberId) {
        rechargeMemberMapper.updateRechargeMember(rechargeMemberId);
    }

    @Override
    public void delByRechargeMemberId(String rechargeMemberId) {

        rechargeMemberMapper.delByRechargeMemberId(rechargeMemberId);
    }

    private PageInfo<RechargeMember> convert(List<RechargeMember> list) {
        PageInfo<RechargeMember> info = new PageInfo(list);

        List<RechargeMember> _list = info.getList();
        info.setList(null);
        List<RechargeMember> __list = new ArrayList<>(10);

        PageInfo<RechargeMember> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(RechargeMember rechargeMember : _list) {
                __list.add(rechargeMember);
            }
            _info.setList(__list);
        }
        return _info;
    }

    @Override
    public RechargeMember selectNewByUserId(String userId) {

        return rechargeMemberMapper.selectNewByUserId(userId);
    }

    @Override
    public int setPayFinishRechargeMemberByOrderOn(String orderNo,String transactionId) {
        int result = rechargeMemberMapper.setPayFinishRechargeMemberByOrderOn(orderNo,transactionId);
        User user = new User();
        if(result > 0){
            RechargeMember rechargeMember = rechargeMemberMapper.selectByOrderOn(orderNo);
            user = userMapper.selectById(rechargeMember.getUserId());
            User _user = new User();
            _user.setUserId(user.getUserId());
            _user.setUserIdentity(2);
            _user.setExpirationDate(rechargeMember.getExpirationDate());
            result = userMapper.updateVIP(_user);
        }
        if(result > 0){

            profitShareRelationDealService.dealBuyMemberType(orderNo);

            //发送通知
            pushBuyVIPMessage(user);
        }
         return result;
    }

    @Override
    public RechargeMember selectByOrderOn(String orderNo) {

        return rechargeMemberMapper.selectByOrderOn(orderNo);
    }

    private void pushBuyVIPMessage(User user){
        MessageRecord buyVIPMessage = new MessageRecord();
        buyVIPMessage.setTitle("购买vip");
        buyVIPMessage.setIntro("购买VIP成功");
        buyVIPMessage.setContent("购买VIP成功");
        buyVIPMessage.setSendUserId("-1");
        buyVIPMessage.setClassify((short)2);
        buyVIPMessage.setMessageType((short)1);
        buyVIPMessage.setReceiveUserId(user.getUserId());
        buyVIPMessage.setRelationId(user.getUserId());
        buyVIPMessage.setRelationType((short)2);
        buyVIPMessage.setCreateTime(new Date().getTime());
        buyVIPMessage.setStatus((short)1);
        buyVIPMessage.setVersion(1);
        buyVIPMessage.setMessageStatus((short)0);
        buyVIPMessage.setMessageId(UUID.randomUUID().toString().replace("-",""));
        messageRecordService.insert(buyVIPMessage);
        aliyunPushService.pushNotice("购买vip","购买VIP成功",user.getUserId(),"{\"type\":\"buyVIP\"}");

        //给上级会员发送通知
        ProfitShareRelation relation = profitShareRelationService.getUpLevelUser(user.getUserId());

        if(null != relation && StringUtils.isNotBlank(relation.getParentUserId())){
            MessageRecord _buyVIPMessage = new MessageRecord();
            _buyVIPMessage.setTitle("购买通知");
            _buyVIPMessage.setIntro("您的下级会员"+user.getUserName()+"购买VIP成功");
            _buyVIPMessage.setContent("您的下级会员"+user.getUserName()+"购买VIP成功");
            _buyVIPMessage.setSendUserId("-1");
            _buyVIPMessage.setClassify((short)2);
            _buyVIPMessage.setMessageType((short)1);
            _buyVIPMessage.setReceiveUserId(user.getUserId());
            _buyVIPMessage.setRelationId(user.getUserId());
            _buyVIPMessage.setRelationType((short)3);
            _buyVIPMessage.setCreateTime(new Date().getTime());
            _buyVIPMessage.setMessageId(UUID.randomUUID().toString().replace("-",""));
            _buyVIPMessage.setStatus((short)1);
            _buyVIPMessage.setVersion(1);
            _buyVIPMessage.setMessageStatus((short)1);
            messageRecordService.insert(_buyVIPMessage);
            aliyunPushService.pushNotice("购买通知","您的下级会员"+user.getUserName()+"购买VIP成功",relation.getParentUserId(),"{\"type\":\"lowerBuyVIP\"}");
        }
    }
}
