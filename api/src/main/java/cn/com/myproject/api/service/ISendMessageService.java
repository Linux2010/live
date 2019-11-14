package cn.com.myproject.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jyp on 2017/8/28 0028.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface ISendMessageService {

    @PostMapping("/message/sendChind")
    boolean sendChina(@RequestParam("mobile") String mobile, @RequestParam("content") String content) ;

    @PostMapping("/message/sendInternation")
    boolean sendInternation(@RequestParam("mobile") String mobile, @RequestParam("content") String content);
}
