package cn.com.myproject.user.service.impl;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.service.IProfitShareRelationService;
import cn.com.myproject.aliyun.push.AliyunPushService;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.service.Impl.MessageRecordService;
import cn.com.myproject.live.entity.PO.UserGroupCard;
import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.mapper.UserGroupCardMapper;
import cn.com.myproject.user.service.IUserGroupCardService;
import cn.com.myproject.util.UserGroupCardUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡Service接口实现类
 */
@Service
public class UserGroupCardService implements IUserGroupCardService{

    private Logger logger = LoggerFactory.getLogger(UserGroupCardService.class);

    @Autowired
    private UserGroupCardMapper userGroupCardMapper;

    @Autowired
    private IProfitShareRelationService profitShareRelationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AliyunPushService aliyunPushService;

    @Autowired
    private MessageRecordService messageRecordService;
    /**
     * 生成用户团购卡
     *
     * @param userGroupCard
     * @return
     */
    @Transactional
    @Override
    public boolean addUgc(UserGroupCard userGroupCard){
        boolean flagVal = false;
        try {
            int cardNumVal = 0;
            Integer cardNum = userGroupCardMapper.selectUgcn();
            if(cardNum == null){
                int numCount = userGroupCardMapper.insertUgcn(1);
                if(numCount > 0){
                    cardNumVal = 1;
                }else{
                    logger.error("更新团购卡数量失败");
                }
            }else{
                if(cardNum.intValue() == 0){
                    int numCount = userGroupCardMapper.updateUgcn(1);
                    if(numCount > 0){
                        cardNumVal = 1;
                    }else{
                        logger.error("更新团购卡数量失败");
                    }
                }else{
                    int numCount = userGroupCardMapper.updateUgcn(cardNum.intValue()+1);
                    if(numCount > 0){
                        cardNumVal = cardNum.intValue()+1;
                    }else{
                        logger.error("更新团购卡数量失败");
                    }
                    if(String.valueOf(cardNumVal).indexOf("4") != -1){
                        // 把4替换成5
                        String cardNumStr = String.valueOf(cardNumVal).replace("4","5");
                        int nextCount = userGroupCardMapper.updateUgcn(Integer.parseInt(cardNumStr));
                        if(nextCount > 0){
                            cardNumVal = Integer.parseInt(cardNumStr);
                        }else{
                            logger.error("更新团购卡数量失败");
                        }
                    }
                }
            }
            // 生成卡号
            String cardNo = UserGroupCardUtil.getCardNo(cardNumVal);
            // 生成卡密
            String cardPassword = UserGroupCardUtil.getCardPassword();
            userGroupCard.setCardNo(cardNo);
            userGroupCard.setCardPassword(cardPassword);
            userGroupCard.setCreateTime(new Date().getTime());// 创建时间默认为当前时间
            userGroupCard.setStatus((short) 0);// 激活状态默认为未激活
            int countVal = userGroupCardMapper.insertUgc(userGroupCard);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("生成用户团购卡失败");
            }
        } catch (Exception e) {
            logger.error("生成用户团购卡失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 根据ID删除用户团购卡
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean removeUgcById(int id){
        boolean flagVal = false;
        try {
            int countVal = userGroupCardMapper.deleteUgcById(id);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("根据ID删除用户团购卡失败");
            }
        } catch (Exception e) {
            logger.error("根据ID删除用户团购卡失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 更新用户团购卡状态为已激活
     *
     * @param userGroupCard
     * @return
     */
    @Transactional
    @Override
    public boolean modifyUgcStatus(UserGroupCard userGroupCard){
        boolean flagVal = false;
        try {
            int countVal = userGroupCardMapper.updateUgcStatus(userGroupCard);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("更新用户团购卡状态为已激活失败");
            }
        } catch (Exception e) {
            logger.error("更新用户团购卡状态为已激活失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 激活用户团购卡
     *
     * @param userGroupCardVO
     * @return
     */
    @Transactional
    @Override
    public boolean activationGroupCard(UserGroupCardVO userGroupCardVO){
        boolean flag = true;
        //1.激活用户团购卡
        Calendar activeCalendar =  Calendar.getInstance();
        UserGroupCard userGroupCard = new UserGroupCard();
        userGroupCard.setCardNo(userGroupCardVO.getCardNo());
        userGroupCard.setCardPassword(userGroupCardVO.getCardPassword());
        UserGroupCardVO userGroupCardVo = this.getUserGroupCard(userGroupCard);//团购卡信息
        userGroupCard.setActiveTime(activeCalendar.getTimeInMillis());
        userGroupCard.setStatus((short) 1); //是否激活：0代表未激活，1代表已激活
        userGroupCard.setActiveUserId(userGroupCardVO.getActiveUserId());
        int countVal = userGroupCardMapper.updateUgcStatus(userGroupCard);
        if(countVal < 1) {
            logger.error("激活用户团购卡失败userGroupCardMapper.updateUgcStatus(userGroupCard)");
            throw new RuntimeException("激活用户团购卡失败userGroupCardMapper.updateUgcStatus(userGroupCard)");
        }

        //2.更新上下线关系
        ProfitShareRelation old_profitShareRelation =  profitShareRelationService.selectByUserId(userGroupCardVO.getActiveUserId()); //查询激活者parent不为-1的上下级分润关系
        if(old_profitShareRelation != null){
            //删除旧的分润关系
           int deleteFlag =  profitShareRelationService.deleteByPrimaryKey(old_profitShareRelation.getRelationId());
           if(deleteFlag < 1){
               logger.error("根据分润关系ID删除激活者旧的分润关系失败profitShareRelationService.deleteByPrimaryKey(old_profitShareRelation.getRelationId())");
               throw new RuntimeException("根据分润关系ID删除激活者旧的分润关系失败profitShareRelationService.deleteByPrimaryKey(old_profitShareRelation.getRelationId())");
           }
        }

        //添加新的分润关系
        ProfitShareRelation new_profitShareRelation = new ProfitShareRelation();
        new_profitShareRelation.setRelationId(UUID.randomUUID().toString().replace("-", ""));
        new_profitShareRelation.setUserId(userGroupCardVO.getActiveUserId()); //激活者ID
        new_profitShareRelation.setParentUserId(userGroupCardVO.getUserId()); //团购卡主ID
        new_profitShareRelation.setNo(1); //序号
        new_profitShareRelation.setCreateTime(Calendar.getInstance().getTimeInMillis());
        new_profitShareRelation.setStatus((short)1);
        new_profitShareRelation.setVersion(1);
        int insertFlag =  profitShareRelationService.insert(new_profitShareRelation);
        if(insertFlag < 1){
            logger.error("添加新的分润关系失败profitShareRelationService.insert(new_profitShareRelation)");
            throw new RuntimeException("添加新的分润关系失败profitShareRelationService.insert(new_profitShareRelation)");
        }else{
            pushRelationMessage(userGroupCardVO.getActiveUserId(),userGroupCardVO.getUserId());
        }

        //3.升级激活者用户为会员
        User user  =new User();
        user.setUserId(userGroupCardVO.getActiveUserId());
        user.setUserIdentity(2);//用户身份1:普通用户2：会员用户
        //校验有效期(月卡有效期31天，年卡有效期12*31天）
        Long l1 = activeCalendar.getTimeInMillis();
        if(userGroupCardVo.getCardType() == 1){ //激活卡类型：1代表年卡，2代表月卡
            activeCalendar.add(Calendar.DATE, 1*12*31);
        }else if(userGroupCardVo.getCardType() == 2){
            activeCalendar.add(Calendar.DATE, 31);
        }
        Long l2 = activeCalendar.getTimeInMillis();
        System.out.println("激活时间戳l1：" + l1 +"\t 有效期时间戳l2："+l2);
        user.setExpirationDate(activeCalendar.getTimeInMillis());
        User commendUser = userService.selectById(userGroupCardVo.getUserId());
        String code = commendUser.getCountryCode();
        String phone = commendUser.getPhone();
        String phoneNumber = "";
        if(StringUtils.isNotEmpty(code))
            phoneNumber += code+"-";
        if(StringUtils.isNotEmpty(phone))
            phoneNumber += phone;
        user.setCommendname(phoneNumber);
        userService.updateVIP(user);

        return flag;

    }

    /**
     * 发送消息推送
     * @param userId
     * @param parentUserId
     */
    private void pushRelationMessage(String userId,String parentUserId){
        User user = userService.selectById(userId);
        if(null != user){
            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setTitle("我的团队");
            messageRecord.setIntro("用户名为"+user.getLoginName()+"的会员，已成为您的下线");
            messageRecord.setContent("用户名为"+user.getLoginName()+"的会员，已成为您的下线");
            messageRecord.setSendUserId("-1");
            messageRecord.setClassify((short)2);
            messageRecord.setMessageType((short)1);
            messageRecord.setReceiveUserId(parentUserId);
            messageRecord.setRelationId(parentUserId);
            messageRecord.setRelationType((short)4);
            messageRecord.setCreateTime(new Date().getTime());
            messageRecord.setMessageId(UUID.randomUUID().toString().replace("-",""));
            messageRecord.setStatus((short)1);
            messageRecord.setVersion(1);
            messageRecord.setMessageStatus((short)0);
            messageRecordService.insert(messageRecord);

            //发送通知
            aliyunPushService.pushNotice(messageRecord.getTitle(),messageRecord.getIntro(),messageRecord.getReceiveUserId(),"{\"type\":\"relationPush\"");

        }
    }
    /**
     * 查询用户团购卡列表信息
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @return
     */
    public PageInfo<UserGroupCardVO> searchUgcList(int pageNum, int pageSize, String userId, String cardNo, int cardType, int status){
        List<UserGroupCardVO> userGroupCardVOList = userGroupCardMapper.selectUgcList(pageNum,pageSize,userId,cardNo,cardType,status);
        return convert(userGroupCardVOList);
    }

    /**
     * 查询用户团购卡导出列表信息
     *
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @return
     */
    @Override
    public List<UserGroupCardVO> searchExportUgcList(String userId, String cardNo, int cardType, int status){
        return userGroupCardMapper.selectExportUgcList(userId,cardNo,cardType,status);
    }

    /**
     * 查找团购卡信息
     *
     * @param userGroupCard
     * @return
     */
    @Override
    public UserGroupCardVO getUserGroupCard(UserGroupCard userGroupCard){
        return userGroupCardMapper.getUserGroupCard(userGroupCard);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<UserGroupCardVO> convert(List<UserGroupCardVO> list) {
        PageInfo<UserGroupCardVO> info = new PageInfo(list);
        List<UserGroupCardVO> _list = info.getList();
        info.setList(null);
        List<UserGroupCardVO> __list = new ArrayList<UserGroupCardVO>(10);
        PageInfo<UserGroupCardVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(UserGroupCardVO userGroupCardVO : _list) {
                __list.add(userGroupCardVO);
            }
            _info.setList(__list);
        }
        return _info;
    }

}