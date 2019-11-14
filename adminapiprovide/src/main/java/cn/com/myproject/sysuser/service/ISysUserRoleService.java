package cn.com.myproject.sysuser.service;

import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
public interface ISysUserRoleService {

    void addSysUserRole(SysUserRole sysUserRole);

    void updateSysUserRole(SysUserRole sysUserRole);

    List<SysUserRole> findSysUserRoleByUserId(String userId);

    void deleteSysUserRole(String userId);

    void batchAddSysUserRole(List<SysUserRole> sysUserRoleList);

}


