package cn.com.myproject.admincon.mapper;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProfitShareSettingMapper {

    int deleteByPrimaryKey(String setId);

    int insert(ProfitShareSetting record);

    int insertSelective(ProfitShareSetting record);

    ProfitShareSetting selectByPrimaryKey(String setId);

    ProfitShareSetting selectByType(int type);

    List<ProfitShareSetting> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    int updateByPrimaryKeySelective(ProfitShareSetting record);

    int updateByPrimaryKey(ProfitShareSetting record);

}