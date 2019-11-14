package cn.com.myproject.api.wap;

import cn.com.myproject.api.config.MyWXPayConfig;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.customer.IMessageService;
import cn.com.myproject.api.util.CommonUtil;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.api.util.TemplatConfig;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.wxpay.sdk.WXPay;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jyp on 2017/10/23 0023.
 */
@Controller
@RequestMapping("/wap/message")
public class WXMessageController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(WXMessageController.class);

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserService userService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    @Autowired
    private WXJspPayProp wXJspPayProp;

  // private TemplatConfig templatConfig;


    /**
     * 跳转到站内信页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public ModelAndView to_message(){
        ModelAndView model = new ModelAndView("/sysnoti/notification");
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        if(StringUtils.isBlank(userId)){
            model.addObject("message","会员ID不能为空");
        }
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setRelationId(userId);
        messageRecord.setClassify((short) 1);
        messageRecord.setMessageStatus((short)0);
        Integer messageCount = messageService.getMessageCount(messageRecord);
        model.addObject("count",messageCount==null?0:messageCount);

        return model;
    }

    /**
     * 获取站内信列表
     * @param pageNum
     * @param pageSize
     * @param classify 列表类型，1、站内信 2、系统通知
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Message<List<MessageRecord>> getMsgList(String pageNum, String pageSize, String classify){
        try {
            // 从session中获取userId
            String userId = "";
            User user = getCurrUser();
            if(user != null){
                userId = user.getUserId();
            }
            if(StringUtils.isBlank(userId)){
                return MessageUtils.getSuccess("会员ID不能为空");
            }
            Map<String,Object> map = new HashMap<>();
            map.put("relationId",userId);
            map.put("pageNum",pageNum);
            map.put("pageSize",pageSize);
            map.put("classify",1);
            PageInfo pageInfo = messageService.getPageByMap(map);
            List list = new ArrayList();
            if(null != pageInfo && null != pageInfo.getList()){
                list = pageInfo.getList();
            }
            Message<List<MessageRecord>>  message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "wx-个人中心-消息|通知-获取站内信列表", e.getMessage());
            return MessageUtils.getFail("wx-个人中心-消息|通知-获取站内信列表" + e.getMessage());
        }
    }

    /**
     * 跳转到列表页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/list_page")
    public ModelAndView to_message_list(){
        ModelAndView model = new ModelAndView("/sysnoti/msg");
        return model;
    }

    /**
     * 跳转到站内信页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/return_message")
    public ModelAndView return_message(){
        ModelAndView model = new ModelAndView("/sysnoti/notification");
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        if(StringUtils.isBlank(userId)){
            model.addObject("message","会员ID不能为空");
        }
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setRelationId(userId);
        messageRecord.setClassify((short) 1);
        messageRecord.setMessageStatus((short)0);
        Integer messageCount = messageService.getMessageCount(messageRecord);
        model.addObject("count",messageCount==null?0:messageCount);
        String classify = "1";
        String updateType = "1";
        setListReaded(classify,updateType);
        return model;
    }

    /**
     * 更新状态
     * @param classify 列表类型，1、站内信 2、系统通知
     * @param updateType 更新类型，1、全部更新 2、整页更新
     * @return
     */
    public Message setListReaded(String classify,String updateType){
        try {
            String pageNum = "";
            String pageSize = "";
            // 从session中获取userId
            String userId = "";
            User user = getCurrUser();
            if(user != null){
                userId = user.getUserId();
            }
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
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "wx-个人中心-消息|通知-更新列表为已读", e.getMessage());
            return MessageUtils.getFail("wx-个人中心-消息|通知-更新列表为已读" + e.getMessage());
        }
    }

    /**
     * 删除方法
     * @param messageId
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
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
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "wx-个人中心-消息|通知-删除消息", e.getMessage());
            return MessageUtils.getFail("wx-个人中心-消息|通知-删除消息" + e.getMessage());
        }
    }

    /**
     *
     * @param receiveUserId
     * @return
     */
    @ResponseBody
    @RequestMapping("/wxlist")
    public Message<List<MessageRecord>> getWxlist(String receiveUserId){
        try {

            if(StringUtils.isBlank(receiveUserId)){
                return MessageUtils.getSuccess("会员ID不能为空");
            }
            Map<String,Object> map = new HashMap<>();
            List<MessageRecord> list = messageService.wxbecomelowerlevelnotice(receiveUserId);
            Message<List<MessageRecord>>  message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "wx-个人中心-消息|通知-成为下线通知", e.getMessage());
            return MessageUtils.getFail("wx-个人中心-消息|通知-获取站内信列表" + e.getMessage());
        }
    }

    @RequestMapping("/auth")
    public String auth(HttpServletResponse response) throws IOException {
        String userId = "";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
        //共账号及商户相关参数
        String appid = new MyWXPayConfig().getAppID();
        String backUri = "http://jtxyapp.doers.cn/wap/message/to_record";
        backUri = backUri+"?userId="+userId;
        backUri = URLEncoder.encode(backUri);
        //scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + appid+
                "&redirect_uri=" +
                backUri+
                "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        response.sendRedirect(url);
        return null;
    }

    @RequestMapping("to_record")
    public ModelAndView to_record(String userId,String code){
        int flagVal = 0;

        ModelAndView model = new ModelAndView("/my/my");

        MyWXPayConfig config = new MyWXPayConfig();
        WXPay wxpay = new WXPay(config);

        String openId = "";
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+config.getAppID()+"&secret="+config.getKey()+"&code="+code+"&grant_type=authorization_code";

        net.sf.json.JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
        if (null != jsonObject &&   jsonObject.containsKey("openid")) {
            openId = jsonObject.getString("openid");
            //得到openId然后记录
            User user = new User();
            user.setUserId(userId);
            user.setThePublicOpenId(openId);
            flagVal = userService.updateThePublicOpenId(user);
            if(flagVal < 1) {
                flagVal =  0;
            }
        }else {
            logger.info("--------------------------------------");

        }
        return model;
    }

    @RequestMapping("/insertOpenId")
    @ResponseBody
    public int insert(String openId){
        User user = new User();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        user.setUserId(userId);
        user.setThePublicOpenId(openId);
        int flagVal = userService.updateThePublicOpenId(user);
        if(flagVal < 1){
            flagVal =  0;
        }
        return flagVal;
    }


    /**
     *推送
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendByMessageList")
    public void sendByMessageList(){
        try {
            // 从session中获取userId
            String userId = "";
            User user = getCurrUser();
            if(user != null){
                userId = user.getUserId();
            }
            Map<String,Object> map = new HashMap<>();
            List<MessageRecord> list = messageService.wxbecomelowerlevelnotice(userId);
            //判断是购买课程还是成为会员，还是下线成为会员 还是成为下线
            for(int i=0;i<list.size();i++){
                if(list.get(i).getRelationType() == 1){//购买课程
                    sendPurchaseMessage(list.get(i).getIntro(),"教头学院 - 课程有效期：永久","2",list.get(i).getOperType(),list.get(i).getOperId());
                }else if (list.get(i).getRelationType() == 2){//购买vip
                    sendPurchaseMessage(list.get(i).getIntro(),"教头学院 - 您现在可以享受会员免费观看课程的权益了!","1",list.get(i).getOperType(),list.get(i).getOperId());
                }else if(list.get(i).getRelationType() == 3){//下线购买vip通知
                    sendPurchaseMessage(list.get(i).getIntro(),"教头学院 - 您有一个会员成为vip了!","2",list.get(i).getOperType(),list.get(i).getOperId());
                }else{//我的下线
                    sendMessage("",list.get(i).getFCreateTime(),list.get(i).getIntro());//发送消息
                }
            }
            setListReaded("2","1");//更新消息状态为已读updateType 1：全部更新 classify 2 ：系统通知
        }catch (Exception e) {
            logger.error("{[{}]}时，发生异常，异常信息为{[{}]}", "wx-个人中心-消息|通知-成为下线通知", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 发送我的团队成为下线消息推送
     * @param userNo
     * @param date
     * @param userName
     */
    public void sendMessage(String userNo,String date,String userName){
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(new WXJspPayConfig().getAppID()); // 设置微信公众号的appid
        config.setSecret(wXJspPayProp.getSecret()); // 设置微信公众号的app corpSecret
        config.setToken("..."); // 设置微信公众号的token
        config.setAesKey("..."); // 设置微信公众号的EncodingAESKey

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");

        //获取用户的openId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String the_public_openId = userService.selectThePublicOpenId(userId);
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(the_public_openId)
                .templateId("hCKBlNGW6t75G-FGdWt_FvmWG8oxjnkA2_36_P2sEwk")
                .build();

        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("first", "您有新的下线加入", "#000"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("keyword1",userNo, "#000"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("keyword2", date, "#000"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("keyword3",userName, "#000"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("remark","教头学院欢迎您的加入!", "#000"));
        templateMessage.setUrl("");//跳转地址(根据需要设定可以为空)
        try {
            String msgId = wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);//msdId 主要是用来判断消息是否发送成功
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送购买成功消息推送
     * @param name
     * @param remark
     */
    public void sendPurchaseMessage(String name,String remark,String type,String operType,String operId){
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(new WXJspPayConfig().getAppID()); // 设置微信公众号的appid
        config.setSecret(wXJspPayProp.getSecret()); // 设置微信公众号的app corpSecret
        config.setToken("..."); // 设置微信公众号的token
        config.setAesKey("..."); // 设置微信公众号的EncodingAESKey

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");

        //获取用户的openId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String the_public_openId = userService.selectThePublicOpenId(userId);
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(the_public_openId)
                .templateId("xZbBplVeLxKrFg54noTh5KgXTy248JNS0lZZMd1hhfA")
                .build();
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("name",name, "#000"));

        if(type == "1"){
            templateMessage.addWxMpTemplateData(
                    new WxMpTemplateData("remark",remark, "#000"));
            templateMessage.setUrl("");//跳转地址(根据需要设定可以为空)购买会员
        }else{
            templateMessage.addWxMpTemplateData(
                    new WxMpTemplateData("remark",remark, "#000"));
            templateMessage.setUrl(jtxyappUrl+"/wap/course/courseDetail?courseType="+operType+"&courseId="+operId);//跳转地址(根据需要设定可以为空)购买课程
        }
        try {
            String msgId = wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);//msdId 主要是用来判断消息是否发送成功
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

}
