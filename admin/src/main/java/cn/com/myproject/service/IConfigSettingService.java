package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.ConfigSetting;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IConfigSettingService {

    @PostMapping("/configSetting/page")
    PageInfo<ConfigSetting> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") String keyword);

    @PostMapping("/configSetting/selectConfigSettingById")
    ConfigSetting selectConfigSettingById(@RequestParam("configId") String configId);

    @GetMapping("/configSetting/selectConfigSettings")
    List<ConfigSetting> selectConfigSettings();

    @PostMapping("/configSetting/addConfigSetting")
    int addConfigSetting(@RequestBody ConfigSetting configSetting);

    @PostMapping("/configSetting/updateConfigSetting")
    int updateConfigSetting(@RequestBody ConfigSetting configSetting);

    @PostMapping("/configSetting/delConfigSettingById")
    int delConfigSettingById(@RequestParam("configId") String configId);

    @PostMapping("/netease/video/uploadFileImg")
    String uploadFileImg(@RequestParam("upfile") File file, @RequestParam("dir") String dir);

    @PostMapping("/configSetting/selectConfigSettingBySign")
    ConfigSetting selectConfigSettingBySign(@RequestParam("configSign")  String configSign);


}
