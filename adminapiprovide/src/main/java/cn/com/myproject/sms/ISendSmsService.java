package cn.com.myproject.sms;

import java.util.List;

/**
 * 发送短信接口
 * Created by liyang-macbook on 2017/7/25.
 */
public interface ISendSmsService {
    /**
     * 发送国内验证码短信
     * */
    boolean sendSms(String mobile, String content);

    /**
     * 发送国内通知短信
     * */
    boolean sendSmsNotify(String mobile, String content);

    /**
     * 发送国内营销短信
     * */
    boolean sendSmsMarketing(String mobile, String content);

    /**
     * 批量发送国内验证码短信(最多200个手机号)
     * */
    boolean batchSendSms(List<String> mobile, String content);

    /**
     *  批量发送国内通知短信(最多200个手机号)
     * */
    boolean batchSendSmsNotify(List<String> mobile, String content);

    /**
     *  批量发送国内营销短信(最多200个手机号)
     * */
    boolean batchSendSmsMarketing(List<String> mobile, String content);


    /**
     * 发送国外验证码短信
     * */
    boolean sendGsms(String mobile, String content);

    /**
     * 批量发送国外验证码短信(最多2000个手机号)
     * */
    boolean batchSendGsms(List<String> mobile, String content);


}
