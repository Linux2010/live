package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.ConfigSetting;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LSG on 2017/8/29 0029.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IRechargeMemberService {

    @PostMapping("/rechargeMember/addRechargeMember")
    String addRechargeMember(@RequestBody RechargeMember rechargeMember);

    @PostMapping("/rechargeMember/selectByUserId")
    RechargeMember selectByUserId(@RequestParam("userId") String userId);

    @PostMapping("/rechargeMember/selectByRechargeMemberId")
    RechargeMember selectByRechargeMemberId(@RequestParam("/rechargeMemberId") String rechargeMemberId);

    @PostMapping("/rechargeMember/updateRechargeMember")
    int updateRechargeMember(@RequestParam("rechargeMemberId") String rechargeMemberId);

    @PostMapping("/configSetting/selectConfigSettingBySign")
    ConfigSetting selectConfigSettingBySign(@RequestParam("configSign") String configSign);

    @GetMapping("/configSetting/selectConfigSettings")
    List<ConfigSetting> selectConfigSettings();

    @PostMapping("/rechargeMember/selectNewByUserId")
    RechargeMember selectNewByUserId(@RequestParam("userId") String userId);

    @GetMapping("/rechargeMember/setPayFinishRechargeMemberByOrderOn")
    int setPayFinishRechargeMemberByOrderOn(@RequestParam("orderNo") String orderNo,@RequestParam("transactionId") String transactionId);

    @PostMapping("/rechargeMember/selectByOrderOn")
    RechargeMember selectByOrderOn(@RequestParam("orderNo") String orderNo);
}
