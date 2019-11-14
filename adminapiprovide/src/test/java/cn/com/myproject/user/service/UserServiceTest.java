package cn.com.myproject.user.service;

import cn.com.myproject.AdminApiProvideApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liyang-macbook on 2017/9/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdminApiProvideApplication.class)
@WebAppConfiguration
public class UserServiceTest  {
    @Autowired
    private IUserService userService;

    @Test
    public void testcreateQrCode() {
       String url =  userService.createQrCode("http://www.myproject.com.cn/solo/articles/2017/06/17/1497673161449.html?111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111","123123");
        System.out.println(url);
    }
}
