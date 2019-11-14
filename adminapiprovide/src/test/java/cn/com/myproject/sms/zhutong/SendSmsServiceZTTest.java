package cn.com.myproject.sms.zhutong;

import cn.com.myproject.AdminApiProvideApplication;
import cn.com.myproject.sms.ISendSmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liyang-macbook on 2017/7/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdminApiProvideApplication.class)
@WebAppConfiguration
public class SendSmsServiceZTTest {

    @Autowired
    private ISendSmsService sendSmsServiceZTTest;

    @Test
    public void sendGsms() {
        sendSmsServiceZTTest.sendGsms("610481951268","尊敬的会员，您的验证码是123321,请勿泄露");
    }

}
