package cn.com.myproject.sysuser.mapper;


import cn.com.myproject.sysuser.entity.PO.SysRegion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRegionMapper {

    int deleteByPrimaryKey(String regionId);

    int insert(SysRegion record);

    int insertSelective(SysRegion record);

    SysRegion selectByPrimaryKey(String regionId);

    List<SysRegion> selectByPid(String pid);

    int updateByPrimaryKeySelective(SysRegion record);

    int updateByPrimaryKey(SysRegion record);

    List<SysRegion> select_countries_code();

}