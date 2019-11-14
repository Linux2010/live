package cn.com.myproject.external;

import cn.com.myproject.configSetting.service.IConfigSettingService;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

@RestController
@RequestMapping("/configSetting")
public class ConfigSettingController {

    @Autowired
    private IConfigSettingService configSettingService;

    @PostMapping("/page")
    public PageInfo<ConfigSetting> getPage(int pageNum , int pageSize, String keyword){

        return configSettingService.getPage(pageNum, pageSize, keyword);
    }

    @GetMapping("/selectConfigSettings")
    public List<ConfigSetting> selectConfigSettings(){

        return configSettingService.selectConfigSettings();
    }

    @PostMapping("/selectConfigSettingById")
    public ConfigSetting selectConfigSettingById(String configId){

        return configSettingService.selectConfigSettingById(configId);
    }

    @PostMapping("/selectConfigSettingBySign")
    public ConfigSetting selectConfigSettingBySign(String configSign){
        return configSettingService.selectConfigSettingBySign(configSign);
    }

    @PostMapping("/addConfigSetting")
    public int addConfigSetting(@RequestBody ConfigSetting configSetting){

        int result = 0;
        try {
            configSetting.setConfigId(UUID.randomUUID().toString().replace("-", ""));
            configSetting.setStatus((short)1);
            configSetting.setVersion(1);
            configSetting.setCreateTime(new Date().getTime());
            configSettingService.addConfigSetting(configSetting);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @PostMapping("/updateConfigSetting")
    public int updateConfigSetting(@RequestBody ConfigSetting configSetting){

        int result = 0;
        try {
            configSettingService.updateConfigSetting(configSetting);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @PostMapping("/delConfigSettingById")
    public int delConfigSettingById(String configId){

        int result = 0;
        try {
            configSettingService.delConfigSettingById(configId);
             result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }
}
























