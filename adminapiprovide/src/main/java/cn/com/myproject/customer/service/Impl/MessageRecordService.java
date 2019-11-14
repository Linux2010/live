package cn.com.myproject.customer.service.Impl;


import cn.com.myproject.aliyun.push.IAliyunPushService;
import cn.com.myproject.customer.dao.BatchMessageRecordDao;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.MessageRecordVO;
import cn.com.myproject.customer.entity.VO.UserFeedbackVO;
import cn.com.myproject.customer.mapper.MessageRecordMapper;
import cn.com.myproject.customer.mapper.UserFeedbackMapper;
import cn.com.myproject.customer.service.IMessageRecordService;
import cn.com.myproject.customer.service.IUserFeedbackService;
import cn.com.myproject.sms.ISendSmsService;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.mapper.UserMapper;
import cn.com.myproject.user.service.impl.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @auther CQC
 * @create 2017.8.15
 */
@Service
public class MessageRecordService implements IMessageRecordService {

    @Autowired
    private MessageRecordMapper messageRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BatchMessageRecordDao messageRecordDao;

    @Autowired
    private ISendSmsService sendSmsService;

    @Autowired
    private UserService userService;

    @Autowired
    private IAliyunPushService aliyunPushService;

    public static final Logger logger = LoggerFactory.getLogger(MessageRecordService.class);

    @Override
    public int deleteByPrimaryKey(String businessId) {
        return messageRecordMapper.deleteByPrimaryKey(businessId);
    }

    @Override
    public int insert(MessageRecord record) {
        int i = messageRecordMapper.insert(record);
        if(i>0){
            record.setMessageId(UUID.randomUUID().toString().replace("-",""));
            record.setRelationId(record.getSendUserId());
            i = messageRecordMapper.insert(record);
        }
        return i;
    }

    @Override
    public int insertSelective(MessageRecord record) {
        int result = 0;
        try{

            Map<String,Object> map = new HashMap<>();
            map.put("userId",record.getRelationId());
            List<User> userList = userMapper.getUserListByMap(map);
            User user = userList.get(0);
            if((short)2 == record.getRelationType()){ //短信
                if(null != userList && userList.size()>0){
                    if(StringUtils.isNotBlank(user.getCountryCode())){
                        if(StringUtils.isNotBlank(user.getPhone()) && StringUtils.isNotBlank(record.getContent())){
                            boolean flag = false;
                            if("86".equals(user.getCountryCode())){
                                flag = sendSmsService.sendSms(user.getPhone(),record.getContent());
                            }else{
                                flag = sendSmsService.sendGsms(user.getPhone(),record.getContent());
                            }
                            if(!flag){
                                logger.error("[{系统发送短信}]("+user.getLoginName()+")[{失败}]");
                            }
                        }
                    }
                }
            }else if((short)3 == record.getRelationType()){
                aliyunPushService.push(record.getTitle(),record.getIntro(),"ACCOUNT",user.getUserId(),"ALL","NOTICE",record.getAliPushExtParameters()==null?"":record.getAliPushExtParameters());
            }else if((short)4 == record.getRelationType()){
                aliyunPushService.push(record.getTitle(),record.getIntro(),"ACCOUNT",user.getUserId(),"ALL","MESSAGE",record.getAliPushExtParameters()==null?"":record.getAliPushExtParameters());
            }
            result = messageRecordMapper.insertSelective(record);
            if(result > 0){
                record.setMessageId(UUID.randomUUID().toString().replace("-",""));
                record.setRelationId(record.getSendUserId());
                result = messageRecordMapper.insertSelective(record);
            }else{
                logger.error("[{}]时，数据存储失败","存储消息数据");
            }
        }catch (Exception e){
            logger.error("[{}]时，发生异常，异常信息为：[{}]","系统发送信息",e.getMessage());
        }
        return result;
    }

    @Override
    public int batchInsertSelective(Map<String, Object> map) {
        int result = 0;
        if(null != map && map.size()>0){

            String userIds = (String) map.get("userIds");
            int relationType = (int) map.get("relationType");
            String sendUserId = (String) map.get("sendUserId");
            int messageType = (int) map.get("messageType");
            String content = (String) map.get("content");
            String title = (String) map.get("title");

            String intro = (String) map.get("intro");
            String url = (String) map.get("url");
            int classify = (int) map.get("classify");

            if(StringUtils.isNotBlank(userIds)){

                String[] userIdArray = userIds.split(",");

                String fileUserIds = "";
                List<MessageRecord> messageRecordList = new ArrayList<>();
                for(String str : userIdArray){
                    fileUserIds += ",'" + str +"'";
                    MessageRecord messageRecord = new MessageRecord();
                    messageRecord.setContent(content);
                    messageRecord.setSendUserId(sendUserId);
                    messageRecord.setMessageType((short)messageType);
                    messageRecord.setReceiveUserId(str);
                    messageRecord.setRelationId(str);
                    messageRecord.setRelationType((short)relationType);
                    messageRecord.setCreateTime(new Date().getTime());
                    messageRecord.setMessageId(UUID.randomUUID().toString().replace("-",""));

                    messageRecord.setIntro(intro);
                    messageRecord.setClassify((short)classify);
                    messageRecord.setUrl(url);

                    messageRecordList.add(messageRecord);

                    MessageRecord _messageRecord = new MessageRecord();
                    _messageRecord.setContent(content);
                    _messageRecord.setSendUserId(sendUserId);
                    _messageRecord.setMessageType((short)messageType);
                    _messageRecord.setReceiveUserId(str);
                    _messageRecord.setRelationType((short)relationType);
                    _messageRecord.setCreateTime(new Date().getTime());
                    _messageRecord.setRelationId(messageRecord.getSendUserId());
                    _messageRecord.setMessageId(UUID.randomUUID().toString().replace("-",""));

                    _messageRecord.setIntro(intro);
                    _messageRecord.setClassify((short)classify);
                    _messageRecord.setUrl(url);

                    messageRecordList.add(_messageRecord);
                }

                Map<String, Object> fileMap = new HashMap<>();
                fileMap.put("userIds",fileUserIds.substring(fileUserIds.indexOf(",")+1));
                List<User> userList = userMapper.getUserListByMap(fileMap);

                if(2 == relationType && StringUtils.isNotBlank(content)){ // 短信发送
                    dealSendSMS(userList,content);
                }else if(3==relationType){ // 通知发送
                    dealInMail(userList,title,intro);
                }else if(4==relationType){ // 私信发送
                    dealPrivateLetter(userList,title,content);
                }
                messageRecordDao.saveBatch(messageRecordList);
                result = 1;
            }
        }
        return result;
    }

    @Override
    public MessageRecord selectByPrimaryKey(String businessId) {
        return messageRecordMapper.selectByPrimaryKey(businessId);
    }

    @Override
    public Integer getMessageCount(MessageRecord record) {
        return messageRecordMapper.getMessageCount(record);
    }

    @Override
    public PageInfo<MessageRecordVO> getPage(int pageNum, int pageSize, String keyword) {
        List<MessageRecord> messageRecordList = messageRecordMapper.getPage(pageNum,pageSize,keyword);
        return convert(messageRecordList);
    }

    @Override
    public int updateByPrimaryKeySelective(MessageRecord record) {
        return messageRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MessageRecord record) {
        return messageRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MessageRecord> getListByRelationId(String relationId) {
        return messageRecordMapper.getListByRelationId(relationId);
    }

    @Override
    public List<MessageRecord> getMessageByGP(int pageNum, int pageSize, Map<String, Object> map) {
        return messageRecordMapper.getMessageByGP(pageNum,pageSize,map);
    }

    @Override
    public PageInfo<MessageRecordVO> getPageByGP (Map<String, Object> map) {
        List<MessageRecord> messageRecordList = messageRecordMapper.getMessageByGP(Integer.valueOf(map.get("pageNum").toString()),Integer.valueOf(map.get("pageSize").toString()),map);
        if(null != messageRecordList){

            StringBuffer userIds = new StringBuffer();
            for(MessageRecord messageRecord:messageRecordList){
                userIds.append(",").append("'"+messageRecord.getSendUserId()+"'").append(",").append("'"+messageRecord.getReceiveUserId()+"'");
            }
            Map<String, Object> fileMap = new HashMap<>();
            fileMap.put("userIds",userIds.substring(userIds.indexOf(",")+1));
            List<User> userList = userMapper.getUserListByMap(fileMap);

            for(MessageRecord messageRecord:messageRecordList){
                for(User user:userList){
                    if(user.getUserId().equals(messageRecord.getSendUserId())){
                        messageRecord.setUserId(user.getUserId());
                        messageRecord.setPhone(user.getPhone());
                        messageRecord.setPhoto(user.getPhoto());
                        messageRecord.setUserName(user.getUserName());
                        messageRecord.setLoginName(user.getLoginName());
                        messageRecord.setNickName(user.getNickName());
                        break;
                    }
                    if(user.getUserId().equals(messageRecord.getReceiveUserId())){
                        messageRecord.setUserId(user.getUserId());
                        messageRecord.setPhone(user.getPhone());
                        messageRecord.setPhoto(user.getPhoto());
                        messageRecord.setUserName(user.getUserName());
                        messageRecord.setLoginName(user.getLoginName());
                        messageRecord.setNickName(user.getNickName());
                        break;
                    }
                }
            }
            return convert(messageRecordList);
        }else{
            return null;
        }
    }

    @Override
    public PageInfo<MessageRecordVO> getPageByMap(Map<String, Object> map) {
        List<MessageRecord> messageRecordList = messageRecordMapper.getPageByMap(Integer.valueOf(map.get("pageNum").toString()),Integer.valueOf(map.get("pageSize").toString()),map);
        //listSort(messageRecordList);
        if(null != messageRecordList){
            StringBuffer userIds = new StringBuffer();
            for(MessageRecord messageRecord:messageRecordList){
                userIds.append(",").append("'"+messageRecord.getSendUserId()+"'").append(",").append("'"+messageRecord.getReceiveUserId()+"'");
            }
            Map<String, Object> fileMap = new HashMap<>();
            if(StringUtils.isNotBlank(userIds)){
                fileMap.put("userIds",userIds.substring(userIds.indexOf(",")+1));
            }
            List<User> userList = userMapper.getUserListByMap(fileMap);

            for(MessageRecord messageRecord:messageRecordList){
                for(User user:userList){
                    if(user.getUserId().equals(messageRecord.getSendUserId())){
                        messageRecord.setUserId(user.getUserId());
                        messageRecord.setPhone(user.getPhone());
                        messageRecord.setUserName(user.getUserName());
                        messageRecord.setLoginName(user.getLoginName());
                        messageRecord.setNickName(user.getNickName());
                        break;
                    }
                    if(user.getUserId().equals(messageRecord.getReceiveUserId())){
                        messageRecord.setUserId(user.getUserId());
                        messageRecord.setPhone(user.getPhone());
                        messageRecord.setUserName(user.getUserName());
                        messageRecord.setLoginName(user.getLoginName());
                        messageRecord.setNickName(user.getNickName());
                        break;
                    }
                }
            }
            return convert(messageRecordList);
        }else{
            return null;
        }
    }

    @Override
    public int insert4SendAll(int pageNum,int pageSize,Map<String, Object> map) {

        int result = 0;
        if(null != map && map.size()>0){

            String isSendAll = (String) map.get("isSendAll");
            int relationType = (int) map.get("relationType");
            String sendUserId = (String) map.get("sendUserId");
            int messageType = (int) map.get("messageType");
            String content = (String) map.get("content");
            String search = (String) map.get("search");
            String title = (String) map.get("title");
            String intro = (String) map.get("intro");
            String url = (String) map.get("url");
            int classify = (int) map.get("classify");

            if(StringUtils.isNotBlank(isSendAll) && "1".equals(isSendAll)){ // 全部发送

                Map<String,Object> searchUserFilter = new HashMap<>();
                searchUserFilter.put("status","1");
                searchUserFilter.put("keyword",search);
                PageInfo<User> userPageInfo = userService.getPageForeach(pageNum,pageSize,searchUserFilter);
                if(null != userPageInfo){
                    int pages = userPageInfo.getPages();
                    List<User> userList = userPageInfo.getList();
                    if(null != userList){
                        List<MessageRecord> messageRecordList = new ArrayList<>();
                        for(User user:userList){
                            MessageRecord messageRecord = new MessageRecord();
                            messageRecord.setContent(content);
                            messageRecord.setSendUserId(sendUserId);
                            messageRecord.setMessageType((short)messageType);
                            messageRecord.setReceiveUserId(user.getUserId());
                            messageRecord.setRelationId(user.getUserId());
                            messageRecord.setRelationType((short)relationType);
                            messageRecord.setCreateTime(new Date().getTime());
                            messageRecord.setMessageId(UUID.randomUUID().toString().replace("-",""));
                            messageRecord.setIntro(intro);
                            messageRecord.setClassify((short)classify);
                            messageRecord.setUrl(url);
                            messageRecordList.add(messageRecord);

                            MessageRecord _messageRecord = new MessageRecord();
                            _messageRecord.setContent(content);
                            _messageRecord.setSendUserId(sendUserId);
                            _messageRecord.setMessageType((short)messageType);
                            _messageRecord.setReceiveUserId(user.getUserId());
                            _messageRecord.setRelationType((short)relationType);
                            _messageRecord.setCreateTime(new Date().getTime());
                            _messageRecord.setRelationId(messageRecord.getSendUserId());
                            _messageRecord.setMessageId(UUID.randomUUID().toString().replace("-",""));
                            _messageRecord.setIntro(intro);
                            _messageRecord.setClassify((short)classify);
                            _messageRecord.setUrl(url);
                            messageRecordList.add(_messageRecord);

                        }
                        if(2 == relationType && StringUtils.isNotBlank(content)){ // 短信发送
                            dealSendSMS(userList,content);
                        }
                        messageRecordDao.saveBatch(messageRecordList);
                        pageNum++;
                        if(pageNum<=pages){
                            insert4SendAll(pageNum,pageSize,map);
                        }else{
                            if(3==relationType){// 通知发送
                                dealInMailAll(title,intro);
                            }else if(4==relationType){// 私信发送
                                dealPrivateLetterAll(title,content);
                            }
                        }
                    }
                }
                result = 1;
            }
        }
        return result;
    }

    @Override
    public int updateByTCU(Map<String, Object> map) {
        return messageRecordMapper.updateByTCU(map);
    }

    @Override
    public List<MessageRecord> wxbecomelowerlevelnotice(String receiveUserId) {
        return messageRecordMapper.wxbecomelowerlevelnotice(receiveUserId);
    }

    private PageInfo<MessageRecordVO> convert(List<MessageRecord> list) {
        PageInfo<MessageRecord> info = new PageInfo(list);
        List<MessageRecord> _list = info.getList();
        info.setList(null);
        List<MessageRecordVO> __list = new ArrayList<>(10);
        PageInfo<MessageRecordVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(MessageRecord messageRecord : _list) {
                __list.add(new MessageRecordVO(messageRecord));
            }
            _info.setList(__list);
        }
        return _info;
    }

    private void listSort(List<MessageRecord> list){
        Collections.sort(list, new Comparator<MessageRecord>(){
            public int compare(MessageRecord o1, MessageRecord o2) {
                try {
                    Long dt1 = o1.getCreateTime();
                    Long dt2 = o2.getCreateTime();
                    if ( dt1.compareTo(dt2)>0) {
                       return 1;
                    } else if (dt1.compareTo(dt2)<0) {
                       return -1;
                    } else {
                       return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    /**
     * 短信发送处理
     * @param userList
     * @param content
     */
    private void dealSendSMS(List<User> userList,String content){
        List<String> foreignPhone = new ArrayList<>();
        List<String> inland = new ArrayList<>();
        for(User user : userList){
            if(StringUtils.isNotBlank(user.getCountryCode()) && StringUtils.isNotBlank(user.getPhone())){
                if("86".equals(user.getCountryCode())){
                    inland.add(user.getCountryCode()+user.getPhone());
                }else{
                    foreignPhone.add(user.getCountryCode()+user.getPhone());
                }
            }
        }
        if(inland.size()>0){
            int i = 0;
            List<String> inlands = new ArrayList<>();
            for(String str :inland){
                i++;
                inlands.add(str);
                if(i%200 == 0){
                    sendSmsService.batchSendSmsMarketing(inlands,content);
                    inlands.clear();
                }
            }
            if(inlands.size()>0){
                sendSmsService.batchSendSmsMarketing(inlands,content);
            }
        }

        if(foreignPhone.size()>0){
            int i = 0;
            List<String> foreignPhones = new ArrayList<>();
            for(String str :foreignPhone){
                i++;
                foreignPhones.add(str);
                if(i%200 == 0){
                    sendSmsService.batchSendGsms(foreignPhones,content);
                    foreignPhones.clear();
                }
            }
            if(foreignPhones.size()>0){
                sendSmsService.batchSendGsms(foreignPhones,content);
            }
        }
    }

    /**
     * 处理站内信
     * @param userList
     * @param title
     * @param content
     */
    private void dealInMail(List<User> userList,String title,String intro){
        int i= 0;
        String targetValue = "";
        for(User user:userList){
            i++;
            targetValue +=","+user.getUserId();
            if(i%100 ==0){
                aliyunPushService.push(title,intro,"ACCOUNT",targetValue.substring(1),"ALL","NOTICE","");
                targetValue = "";
            }
        }
        if(targetValue.length()>0){
            aliyunPushService.push(title,intro,"ACCOUNT",targetValue.substring(1),"ALL","NOTICE","");
        }
    }

    private void dealInMailAll(String title,String intro){
        aliyunPushService.push(title,intro,"ALL","ALL","ALL","NOTICE","");
    }

    private void dealPrivateLetter(List<User> userList,String title,String content){
        int i= 0;
        String targetValue = "";
        for(User user:userList){
            i++;
            targetValue +=","+user.getUserId();
            if(i%100 ==0){
                aliyunPushService.push(title,content,"ACCOUNT",targetValue.substring(1),"ALL","MESSAGE","");
                targetValue = "";
            }
        }
        if(targetValue.length()>0){
            aliyunPushService.push(title,content,"ACCOUNT",targetValue.substring(1),"ALL","MESSAGE","");
        }
    }

    private void dealPrivateLetterAll(String title,String content){
        aliyunPushService.push(title,content,"ALL","ALL","ALL","MESSAGE","");
    }
}
