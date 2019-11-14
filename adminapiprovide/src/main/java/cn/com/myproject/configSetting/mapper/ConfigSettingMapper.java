package cn.com.myproject.configSetting.mapper;

import cn.com.myproject.user.entity.PO.ConfigSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0007.
 */
@Mapper
public interface ConfigSettingMapper {

    ConfigSetting selectConfigSettingById(String configId);

    ConfigSetting selectConfigSettingBySign(String configSign);

    List<ConfigSetting> selectConfigSettings();

    List<ConfigSetting> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("keyword") String keyword);

    void addConfigSetting(ConfigSetting configSetting);

    void updateConfigSetting(ConfigSetting configSetting);

    void delConfigSettingById(String configId);
}
