package cn.com.myproject.aliyun.push;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/24.
 */
public interface IAliyunPushService{

    void push() throws Exception;

    Map<String,String> push(String title,String body,String target,String targetValue,String deviceType,String pushType,String extParameters);

    /**
     * 推送单条通知
     * @param title 标题
     * @param body 内容
     * @param account 账户
     * @param extParameters 扩展属性
     * @return
     */
    Map<String,String> pushNotice(String title,String body,String account,String extParameters);

}
