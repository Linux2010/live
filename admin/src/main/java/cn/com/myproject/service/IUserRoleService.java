package cn.com.myproject.service;


import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.List;

@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IUserRoleService {

    @PostMapping("/sysUserRole/addSysUserRole")
    void addSysUserRole(@RequestBody SysUserRole sysUserRole);

    @PostMapping("/sysUserRole/updateSysUserRole")
    void updateSysUserRole(@RequestBody SysUserRole sysUserRole);

    @PostMapping("/sysUserRole/findSysUserRoleByUserId")
    List<SysUserRole> findSysUserRoleByUserId(String userId);

    @PostMapping("/sysUserRole/deleteSysUserRole")
    void deleteSysUserRole(String userId);

    @PostMapping("/sysUserRole/batchAddSysUserRole")
    void batchAddSysUserRole(List<SysUserRole> sysUserRoleList);
}
