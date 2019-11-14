package cn.com.myproject.api.customer.controller;

import cn.com.myproject.api.service.customer.IMessageService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.goods.entity.PO.Coupon;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @auther CQC
 * @create 2017.10.14
 */

@RestController
@RequestMapping("/api/message")
@Api(value="个人中心-消息|通知",tags = "消息|通知")
public class MessageController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("MessageController");

    @Autowired
    private IMessageService messageService;

    @ApiOperation(value = "个人中心-消息|通知-获取列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID", required = true, dataType = "String",defaultValue = "2fc708bf602041e59f9f174675dfe783"),
            @ApiImplicitParam(paramType="query",name = "classify", value = "列表类型，1、站内信 2、系统通知", required = true, dataType = "String",defaultValue = "1")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = MessageRecord.class, responseContainer = "List" ) })
    @PostMapping("/getMessageList")
    public Message<List<MessageRecord>> getMessage4InMail(String pageNum,String pageSize,String userId,String classify){
        try {

            if(StringUtils.isBlank(userId)){
                return MessageUtils.getSuccess("会员ID不能为空");
            }
            Map<String,Object> map = new HashMap<>();
            map.put("relationId",userId);
            map.put("pageNum",pageNum);
            map.put("pageSize",pageSize);
            map.put("classify",classify);
            PageInfo pageInfo = messageService.getPageByMap(map);
            List list = new ArrayList();
            if(null != pageInfo && null != pageInfo.getList()){
                list = pageInfo.getList();
            }
            Message<List<MessageRecord>>  message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "API-个人中心-消息|通知-获取站内信列表", e.getMessage());
            return MessageUtils.getFail("API-个人中心-消息|通知-获取站内信列表" + e.getMessage());
        }
    }

    @ApiOperation(value = "个人中心--消息|通知-删除消息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "messageId", value = "消息ID", required = true, dataType = "String",defaultValue = "e9929a3d3b7c4cd3b14132d0eed7860f")
    })
    @PostMapping("/delMessage")
    public Message delMessage(String messageId) {
        try {
            if (StringUtils.isBlank(messageId)) {
                return MessageUtils.getSuccess("消息ID不能为空");
            }
            int i = messageService.deleteByPrimaryKey(messageId);
            Message message = null;
            if (i > 0) {
                message = MessageUtils.getSuccess("success");
            } else {
                message = MessageUtils.getSuccess("删除失败");
            }
            return message;
        } catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "API-个人中心-消息|通知-删除消息", e.getMessage());
            return MessageUtils.getFail("API-个人中心-消息|通知-删除消息" + e.getMessage());
        }
    }

    @ApiOperation(value = "个人中心--消息|通知-设为已读消息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "messageId", value = "消息ID", required = true, dataType = "String",defaultValue = "e9929a3d3b7c4cd3b14132d0eed7860f")
    })
    @PostMapping("/updateMessageStatus")
    public Message updateMessageStatus(String messageId){
        try {
            if(StringUtils.isBlank(messageId)){
                return MessageUtils.getSuccess("消息ID不能为空");
            }

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setMessageId(messageId);
            messageRecord.setMessageStatus((short)1);
            int i = messageService.updateByPrimaryKeySelective(messageRecord);
            Message message = null;
            if(i>0){
                message = MessageUtils.getSuccess("success");
            }else{
                message = MessageUtils.getSuccess("设置失败");
            }
            return message;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "API-个人中心-消息|通知-设为已读消息", e.getMessage());
            return MessageUtils.getFail("API-个人中心-消息|通知-设为已读消息" + e.getMessage());
        }
    }


    @ApiOperation(value = "个人中心--消息|通知-获取未读消息数", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "2fc708bf602041e59f9f174675dfe783"),

    })
    @PostMapping("/getMessageCount")
    public Message getMessageCount(String userId){
        try {
            if(StringUtils.isBlank(userId)){
                return MessageUtils.getSuccess("会员ID不能为空");
            }

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setRelationId(userId);
            messageRecord.setClassify((short) 1);
            messageRecord.setMessageStatus((short)0);
            Integer messageCount = messageService.getMessageCount(messageRecord);
            Map<String,Object> map = new HashMap<>();
            map.put("inMailCount",messageCount==null?0:messageCount);

            messageRecord.setClassify((short) 2);
            Integer sysMailCount = messageService.getMessageCount(messageRecord);
            map.put("sysMailCount",sysMailCount==null?0:sysMailCount);


            Message message = MessageUtils.getSuccess("success");
            message.setData(map);
            return message;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "API-个人中心-消息|通知-设为已读消息", e.getMessage());
            return MessageUtils.getFail("API-个人中心-消息|通知-设为已读消息" + e.getMessage());
        }
    }


    @ApiOperation(value = "个人中心--消息|通知-获取消息详情 (H5)", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "messageId", value = "消息ID", required = true, dataType = "String",defaultValue = "c5eafdd5eecf46529c5535d27a0930d9"),

    })
    @GetMapping("/getMessageDetails")
    public ModelAndView getMessageDetails(String messageId){
        try {
            ModelAndView view = new ModelAndView("customer/message_details");
            MessageRecord messageRecord = messageService.selectByPrimaryKey(messageId);
            view.addObject("message",messageRecord);
            return view;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "个人中心--消息|通知-获取消息详情 (H5)", e.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "个人中心-消息|通知-更新列表为已读", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID", required = true, dataType = "String",defaultValue = "2fc708bf602041e59f9f174675dfe783"),
            @ApiImplicitParam(paramType="query",name = "updateType", value = "更新类型，1、全部更新 2、整页更新", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "classify", value = "列表类型，1、站内信 2、系统通知", required = true, dataType = "String",defaultValue = "1")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = MessageRecord.class, responseContainer = "List" ) })
    @PostMapping("/setListReaded")
    public Message setListReaded(String pageNum,String pageSize,String userId,String classify,String updateType){
        try {

            if(StringUtils.isBlank(userId)){
                return MessageUtils.getSuccess("会员ID不能为空");
            }

            if(StringUtils.isBlank(classify)){
                return MessageUtils.getSuccess("列表类型不能为空");
            }

            Map<String,Object> updateConditionMap = new HashMap<>();
            updateConditionMap.put("relationId",userId);
            updateConditionMap.put("classify",classify);
            updateConditionMap.put("createTime",new Date().getTime());

            if(StringUtils.isNotBlank(updateType) && "2".equals(updateType)){

                Map<String,Object> map = new HashMap<>();
                map.put("relationId",userId);
                map.put("pageNum",StringUtils.isBlank(pageNum)?1:pageNum);
                map.put("pageSize",StringUtils.isBlank(pageSize)?10:pageSize);
                map.put("classify",classify);
                PageInfo pageInfo = messageService.getPageByMap(map);
                List list = new ArrayList();

                if(null != pageInfo && null != pageInfo.getList()){
                    list = pageInfo.getList();
                }

                if(list.size()>0){
                    StringBuffer messageIds = new StringBuffer();
                    for(int i = 0;i<list.size();i++){
                        MessageRecord messageRecord = (MessageRecord) list.get(i);
                        if(null != messageRecord && StringUtils.isNotBlank(messageRecord.getMessageId())){
                            messageIds.append(",").append(messageRecord.getMessageId());
                        }
                    }
                    if(messageIds.toString().length()>0){
                        updateConditionMap.put("messageIds",messageIds.toString().substring(1));
                    }
                }
            }
            Message  message = new Message();
            int i = messageService.updateByTCU(updateConditionMap);
            if(i<1){
                message = MessageUtils.getSuccess("更新失败");
            }else{
                message = MessageUtils.getSuccess("success");
            }
            return message;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "API-个人中心-消息|通知-更新列表为已读", e.getMessage());
            return MessageUtils.getFail("API-个人中心-消息|通知-更新列表为已读" + e.getMessage());
        }
    }

}
