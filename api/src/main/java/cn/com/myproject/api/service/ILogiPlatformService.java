package cn.com.myproject.api.service;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by JYP on 2017/9/18 0018.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface ILogiPlatformService {

    @GetMapping("/plat/selectOpenPlat")
    public LogiPlatform selectOpenPlat();
}
