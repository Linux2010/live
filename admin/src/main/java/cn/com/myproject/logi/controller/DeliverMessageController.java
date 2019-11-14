package cn.com.myproject.logi.controller;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import cn.com.myproject.logi.entity.VO.OrderDeliverVO;
import cn.com.myproject.service.IDeliverMessageService;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jyp on 2017/9/28 0028.
 */
@RequestMapping("/deliver")
@Controller
public class DeliverMessageController {

    private static final Logger logger = LoggerFactory.getLogger(DeliverMessageController.class);

    @Autowired
    private IDeliverMessageService deliverMessageService;

    @RequestMapping("/index")
    public String index(){
        return "/logi/deliver_message_index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<OrderDeliverVO> list(int rows, int page){
        return deliverMessageService.selectOrderDeliverMessage(page,rows);
    }

    @RequestMapping("/selectByDeliNo")
    @ResponseBody
    public DeliverMessage selectByDeliNo(String deliNo){
        return deliverMessageService.selectByDeliNo(deliNo);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int update (String deliNo,String deliId){
        int info = 0;
       try{
           int countVal = deliverMessageService.updateDeliNo(deliNo,deliId);
           if(countVal > 0){
               info = 1;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return info;
    }
}
