package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.ConfigSetting;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@FeignClient("admin-api-provide")
public interface IConfigSettingService {

    @GetMapping("/configSetting/selectConfigSettingBySign")
    public ConfigSetting selectConfigSettingBySign(String configSign);

}

