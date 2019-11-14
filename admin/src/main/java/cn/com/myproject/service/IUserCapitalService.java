package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.UserCapital;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * Created by CQC on 2017.9.20.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IUserCapitalService {

    @PostMapping("/usercapital/getUserCapitalList")
    List<UserCapital> getUserCapitalList(@RequestBody UserCapital userCapital);

}
