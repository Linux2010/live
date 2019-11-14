package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.UserCapitalLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * Created by CQC on 2017.9.20.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IUserCapitalLogService {

    @PostMapping("/usercapitallog/getUserCapitalAccount")
    List<Map<String,Object>> getUserCapitalAccount(@RequestBody UserCapitalLog record);
}
