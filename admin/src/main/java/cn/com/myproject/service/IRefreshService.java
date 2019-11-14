package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liyang-macbook on 2017/8/16.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IRefreshService {
    @GetMapping("/refresh/resource")
    String resource();
}
