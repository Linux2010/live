package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.ShortVideo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by LSG on 2017/9/27 0027.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IShortVideoService {

    @PostMapping("/shortVideo/selectByType")
    ShortVideo selectByType(@RequestParam("videoType") int videoType);
}
