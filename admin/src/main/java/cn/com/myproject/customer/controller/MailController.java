package cn.com.myproject.customer.controller;

import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.MessageRecordVO;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.IUserService;
import cn.com.myproject.service.customer.IMessageRecordService;
import cn.com.myproject.service.customer.IUserFeedbackService;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mail")
public class MailController {
    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private IUserFeedbackService userFeedbackService;

    @Autowired
    private IMessageRecordService messageRecordService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadImgService uploadImgService;


    String moName = "站内信";

    @RequestMapping("/")
    public String index() {
        return "customer/user_mail_index";
    }

    @RequestMapping("/record")
    public String record() {
        return "customer/mail_record_index";
    }

    @RequestMapping("/getUserPage")
    @ResponseBody
    public PageInfo<User> getPageForeach(int rows, int page, HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        String keyword =request.getParameter("keyword");
        String phone = request.getParameter("phone");
        if("" != request.getParameter("status") && null != request.getParameter("status")){
            Integer s = Integer.parseInt(request.getParameter("status"));
            map.put("status",s);
        }else{
            map.put("status",1);
        }
        if("" != request.getParameter("identity")  && null != request.getParameter("identity")){
            Integer i = Integer.parseInt(request.getParameter("identity"));
            map.put("identity",i);
        }else{
            map.put("identity","");
        }
        if(request.getParameter("type") != null && request.getParameter("type") != ""){
            Integer t = Integer.parseInt(request.getParameter("type"));
            map.put("type",t);
        }else{
            map.put("type","");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(phone)) {
            map.put("phone",phone);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        PageInfo<User> list = userService.getPageForeach(map);
        return list;
    }

    @ResponseBody
    @RequestMapping("/addRecord")
    public Map<String,Object> addRecord(MessageRecord messageRecord, String userIds, String isSendAll, String search){
        Map<String,Object> result = new HashMap();
        try {
            int i = 0;
            String fileUrl = "";
            messageRecord.setUrl(fileUrl);
            if("1".equals(isSendAll)){ //发送符合条件的全部数据
                Map<String,Object> map = new HashMap<>();
                map.put("sendUserId",messageRecord.getSendUserId());
                map.put("messageType",(short)messageRecord.getMessageType());
                map.put("relationType",(short)messageRecord.getRelationType());
                map.put("content",messageRecord.getContent());
                map.put("title",messageRecord.getTitle());
                map.put("url",fileUrl);
                map.put("intro",messageRecord.getIntro());
                map.put("classify",messageRecord.getClassify());
                map.put("isSendAll",isSendAll);
                map.put("search",search);
                map.put("pageSize",100);
                map.put("pageNum",1);
                i = messageRecordService.insert4SendAll(map);
            }else{
                if(StringUtils.isNotBlank(userIds)){ // 发送一页数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("sendUserId",messageRecord.getSendUserId());
                    map.put("messageType",(short)messageRecord.getMessageType());
                    map.put("relationType",(short)messageRecord.getRelationType());
                    map.put("content",messageRecord.getContent());
                    map.put("title",messageRecord.getTitle());
                    map.put("userIds",userIds);
                    map.put("url",fileUrl);
                    map.put("intro",messageRecord.getIntro());
                    map.put("classify",messageRecord.getClassify());
                    i = messageRecordService.batchInsertSelective(map);
                }else{// 单发
                    i = messageRecordService.insertSelective(messageRecord);
                }
            }
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","发送失败");
            }
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","发送"+moName,ex.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateRecord")
    public Map<String,Object> updateRecord(UserFeedback record) {
        Map<String,Object> result = new HashMap<>();
        try {

            int i = userFeedbackService.updateByPrimaryKey(record);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改"+moName,e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRecord")
    public Map<String,Object> deleteRecord(String id) {
        Map<String,Object> result = new HashMap<>();
        try {
            if(StringUtils.isEmpty(id)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
            int i = userFeedbackService.deleteByPrimaryKey(id);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","删除"+moName,e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getSetting")
    public Map<String,Object> getRecord(String id) {
        Map<String,Object> result = new HashMap<>();
        try{
            if(StringUtils.isBlank(id)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
            UserFeedback record = userFeedbackService.selectByPrimaryKey(id);
            if(null != record){
                result.put("status","success");
                result.put("data",record);
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch(Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","获取"+moName,e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @RequestMapping("/getMailPage")
    @ResponseBody
    public PageInfo<MessageRecordVO> getPageForeach(int rows, int page, String keyword) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        map.put("relationType","3");
        map.put("relationId","-1");
        map.put("classify","1");
        PageInfo<MessageRecordVO> messageRecordPageInfo = messageRecordService.getPageByMap(map);
        return messageRecordPageInfo;
    }

}
