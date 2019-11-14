package cn.com.myproject.customer.controller;

import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.UserFeedbackVO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageRecordController {
    private static final Logger logger = LoggerFactory.getLogger(MessageRecordController.class);

    @Autowired
    private IUserFeedbackService userFeedbackService;

    @Autowired
    private IMessageRecordService messageRecordService;

    @Autowired
    private IUserService userService;

    String moName = "信息记录";

    @RequestMapping("/note/")
    public String index() {
        return "customer/user_feedback_index";
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
            map.put("status","");
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

    @RequestMapping("/add")
    public ModelAndView add(String businessId) {
        UserFeedback record = new UserFeedback();
        record = userFeedbackService.selectByPrimaryKey(businessId);
        ModelAndView mv = new ModelAndView("customer/user_feedback_reply");
        if(record.getDealStatus()!=0){
            mv = new ModelAndView("customer/user_feedback_view");
        }
        List<MessageRecord> messageRecordList = messageRecordService.getListByRelationId(record.getFeedbackId());
        mv.addObject("messageRecordList",messageRecordList);
        mv.addObject("record",record);
        return mv;
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<UserFeedbackVO> list(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,String keyword) {
        PageInfo<UserFeedbackVO> info = userFeedbackService.getPage(page,rows,keyword);
        return info;
    }

    @ResponseBody
    @RequestMapping("/addRecord")
    public Map<String,Object> addRecord(UserFeedback record,String replayContent) {
        Map<String,Object> result = new HashMap();
        try {
            record.setDealStatus((short)1);
            int i = userFeedbackService.updateByPrimaryKeySelective(record);
            if(i>0){
                MessageRecord messageRecord = new MessageRecord();
                messageRecord.setRelationId(record.getFeedbackId());
                messageRecord.setRelationType((short)1);
                messageRecord.setSendUserId("-1");
                messageRecord.setReceiveUserId(record.getUserId());
                messageRecord.setContent(replayContent);
                messageRecord.setCreateTime(new Date().getTime());
                int j = messageRecordService.insert(messageRecord);
                if(j>0){
                    result.put("status","success");
                }else{
                    result.put("status","fail");
                    result.put("message","操作失败");
                }
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加"+moName,ex.getMessage());
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

}
