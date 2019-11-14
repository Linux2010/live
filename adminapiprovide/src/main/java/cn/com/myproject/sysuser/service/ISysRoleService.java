package cn.com.myproject.sysuser.service;

import cn.com.myproject.sysuser.entity.PO.SysRole;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by JYP on 2017/6/21.
 */
public interface ISysRoleService {
    List<String> getAllRoleName();

    List<String> getAllRoleId();

    List<String> getAllUseRoleId();

    List<String> getRoleIds(String userId);

    PageInfo<SysRole> getPage(int pageNum, int pageSize);

    void addRoles(SysRole sysRole);

    void updateRoles(SysRole sysRole);

    Integer checkRoles(String roleName);

    void delRoles(Integer id);

    SysRole selectRoles(Integer id);

    List<SysRole> getRole();

}
