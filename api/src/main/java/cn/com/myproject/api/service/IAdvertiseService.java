package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.Advertise;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LSG on 2017/8/28 0028.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IAdvertiseService {

    @PostMapping("/advertise/selectAdverType")
    List<Advertise> selectAdverType(@RequestParam("type") int type, @RequestParam("status") int status);
}
