package cn.com.myproject.configSetting.service.impl;

import cn.com.myproject.configSetting.mapper.ConfigSettingMapper;
import cn.com.myproject.configSetting.service.IConfigSettingService;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0007.
 */
@Service
public class ConfigSettingService implements IConfigSettingService{

    @Autowired
    private ConfigSettingMapper configSettingMapper;

    @Override
    public ConfigSetting selectConfigSettingById(String configId) {

        return configSettingMapper.selectConfigSettingById(configId);
    }

    @Override
    public ConfigSetting selectConfigSettingBySign(String configSign) {
        return configSettingMapper.selectConfigSettingBySign(configSign);
    }

    @Override
    public List<ConfigSetting> selectConfigSettings() {

        return configSettingMapper.selectConfigSettings();
    }

    @Override
    public void addConfigSetting(ConfigSetting configSetting) {

        configSettingMapper.addConfigSetting(configSetting);
    }

    @Override
    public void updateConfigSetting(ConfigSetting configSetting) {

        configSettingMapper.updateConfigSetting(configSetting);
    }

    @Override
    public void delConfigSettingById(String configId) {

        configSettingMapper.delConfigSettingById(configId);
    }

    @Override
    public PageInfo<ConfigSetting> getPage(int pageNum, int pageSize, String keyword) {

        List<ConfigSetting> list = configSettingMapper.getPage(pageNum, pageSize, keyword);
         return convert(list);
    }

    private PageInfo<ConfigSetting> convert(List<ConfigSetting> list) {
        PageInfo<ConfigSetting> info = new PageInfo(list);

        List<ConfigSetting> _list = info.getList();
        info.setList(null);
        List<ConfigSetting> __list = new ArrayList<>(10);

        PageInfo<ConfigSetting> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(ConfigSetting configSetting : _list) {
                __list.add(configSetting);
            }
            _info.setList(__list);
        }
        return _info;
    }
}
