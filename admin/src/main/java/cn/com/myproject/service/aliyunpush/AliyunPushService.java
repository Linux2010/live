package cn.com.myproject.service.aliyunpush;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @auther CQC
 * @create 2017.9.14
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface AliyunPushService {

    /**
     *
     * @param title
     * @param body
     * @param target
     * @param targetValue (最多100)
     * @param deviceType
     * @param pushType
     * @return
     */
    @GetMapping("/push")
    Map<String,String> push(String title, String body, String target, String targetValue, String deviceType, String pushType);

}
