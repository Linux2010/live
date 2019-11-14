package cn.com.myproject.external;

import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.sysuser.service.ISysUserRoleService;
import cn.com.myproject.sysuser.service.ISysUserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleController.class);

    @Autowired
    private ISysUserRoleService service;

    @PostMapping("/addSysUserRole")
    void addSysUserRole(@RequestBody SysUserRole sysUserRole){
        service.addSysUserRole(sysUserRole);
    }

    @PostMapping("/updateSysUserRole")
    void updateSysUserRole(@RequestBody SysUserRole sysUserRole){
        service.updateSysUserRole(sysUserRole);
    }

    @PostMapping("/findSysUserRoleByUserId")
    List<SysUserRole> findSysUserRoleByUserId(@RequestBody String userId){
        return service.findSysUserRoleByUserId(userId);
    }

    @PostMapping("/deleteSysUserRole")
    void deleteSysUserRole(@RequestBody String userId){
        service.deleteSysUserRole(userId);
    }

    @PostMapping("/batchAddSysUserRole")
    void batchAddSysUserRole(@RequestBody List<SysUserRole> sysUserRoleList){
        if(null != sysUserRoleList && sysUserRoleList.size()>0){
            for(SysUserRole sysUserRole:sysUserRoleList){
                sysUserRole.setVersion(1);
                sysUserRole.setCreateTime(new Date().getTime());
                sysUserRole.setStatus((short)1);
            }
            service.batchAddSysUserRole(sysUserRoleList);
        }
    };
}
