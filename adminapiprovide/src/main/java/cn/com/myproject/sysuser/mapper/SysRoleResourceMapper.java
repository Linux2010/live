package cn.com.myproject.sysuser.mapper;

import cn.com.myproject.sysuser.entity.PO.SysResource;
import cn.com.myproject.sysuser.entity.PO.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashSet;
import java.util.List;

@Mapper
public interface SysRoleResourceMapper {

    List<SysRoleResource> getSysRoleResource(SysRoleResource sysRoleResource);

    List<SysRoleResource> getListByResourceIds(String resourceIds);

    void batchInsert(List<SysRoleResource> sysRoleResources);

    void batchDelete(List<SysRoleResource> sysRoleResources);

    int deleteByRoleId(String roleId);

}
