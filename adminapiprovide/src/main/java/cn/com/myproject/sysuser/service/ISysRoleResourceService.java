package cn.com.myproject.sysuser.service;
import cn.com.myproject.sysuser.entity.PO.SysRoleResource;

import java.util.List;

public interface ISysRoleResourceService {

    List<SysRoleResource> getSysRoleResource(SysRoleResource sysRoleResource);

    List<SysRoleResource> getListByResourceIds(String resourceIds);

    void batchInsert(List<SysRoleResource> sysRoleResources);

    void batchDelete(List<SysRoleResource> sysRoleResources);

    boolean batchEditSysRoleResource(String resourceIds,String roleId);

}
