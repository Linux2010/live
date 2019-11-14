package cn.com.myproject.sysuser.service.impl;

import cn.com.myproject.sysuser.dao.BatchSysUserRoleDao;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.sysuser.mapper.SysRoleMapper;
import cn.com.myproject.sysuser.mapper.SysUserMapper;
import cn.com.myproject.sysuser.mapper.SysUserRoleMapper;
import cn.com.myproject.sysuser.service.ISysUserRoleService;
import cn.com.myproject.sysuser.service.ISysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleService implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private BatchSysUserRoleDao batchSysUserRoleDao;

    @Override
    public void addSysUserRole(SysUserRole sysUserRole) {
        sysUserRoleMapper.addSysUserRole(sysUserRole);
    }

    @Override
    public void updateSysUserRole(SysUserRole sysUserRole) {
        sysUserRoleMapper.updateSysUserRole(sysUserRole);
    }

    @Override
    public List<SysUserRole> findSysUserRoleByUserId(String userId) {
        return sysUserRoleMapper.findSysUserRoleByUserId(userId);
    }

    @Override
    public void deleteSysUserRole(String userId) {
        sysUserRoleMapper.deleteSysUserRole(userId);
    }

    @Override
    public void batchAddSysUserRole(List<SysUserRole> sysUserRoleList) {
        batchSysUserRoleDao.saveBatch(sysUserRoleList);
    }
}
