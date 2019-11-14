package cn.com.myproject.sysuser.dao;

import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;

import java.util.List;


public interface BatchSysUserRoleDao {
    void saveBatch(List<SysUserRole> list);
}
