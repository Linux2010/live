package cn.com.myproject.external;

import cn.com.myproject.sms.ISendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信验证码
 * Created by JYP on 2017/8/28 0028.
 */
@RestController
@RequestMapping("/message")
public class ISendMessageController {

    @Autowired
    private ISendSmsService sendSmsService;

    /**
     * 发送国内的
     */
    @RequestMapping("/sendChind")
    public Boolean sendChind(String mobile,String content){
       return sendSmsService.sendSms(mobile,content);
    }

    /**
     * 发送国际的
     */
    @RequestMapping("/sendInternation")
    public Boolean sendInternation(String mobile,String content){
        return  sendSmsService.sendGsms(mobile,content);
    }
}
