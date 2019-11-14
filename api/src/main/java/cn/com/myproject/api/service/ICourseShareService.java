package cn.com.myproject.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseShareService {

   @GetMapping("/courseshare/getCourseShareCount")
   public int getCourseShareCount(@RequestParam("userId") String userId);

}
