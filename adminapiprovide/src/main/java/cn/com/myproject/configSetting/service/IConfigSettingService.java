package cn.com.myproject.configSetting.service;

import cn.com.myproject.user.entity.PO.ConfigSetting;
import com.github.pagehelper.PageInfo;


import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0007.
 */
public interface IConfigSettingService {

    ConfigSetting selectConfigSettingById(String configId);

    ConfigSetting selectConfigSettingBySign(String configSign);

    List<ConfigSetting> selectConfigSettings();

    void addConfigSetting(ConfigSetting configSetting);

    void updateConfigSetting(ConfigSetting configSetting);

    void delConfigSettingById(String configId);

    PageInfo<ConfigSetting> getPage(int pageNum, int pageSize, String keyword);

}
