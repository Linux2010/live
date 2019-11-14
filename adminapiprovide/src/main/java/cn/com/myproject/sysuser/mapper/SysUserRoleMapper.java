package cn.com.myproject.sysuser.mapper;


import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {


    void addSysUserRole(SysUserRole sysUserRole);

    void updateSysUserRole(SysUserRole sysUserRole);

    List<SysUserRole> findSysUserRoleByUserId(String userId);

    void deleteSysUserRole(String userId);

}
