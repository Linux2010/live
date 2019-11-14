package cn.com.myproject.external.aliyunpush;

import cn.com.myproject.aliyun.push.AliyunPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.9.14
 */
@RestController
@RequestMapping("/aliyunpush")
public class AliyunPushController {

    @Autowired
    private AliyunPushService aliyunPushService;

    void push() throws Exception{
        aliyunPushService.push();
    }

    @GetMapping("/push")
    Map<String,String> push(String title, String body, String target, String targetValue, String deviceType, String pushType,String extParameters){
        Map<String,String> result = new HashMap<>();
        result = aliyunPushService.push(title,body,target,targetValue,deviceType,pushType,extParameters);
        return result;
    }

}
