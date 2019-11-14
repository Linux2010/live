package cn.com.myproject.service;

import cn.com.myproject.sysuser.entity.PO.SysRole;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by JYP on 2017/7/26.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface ISysRoleService {

    @GetMapping("/sysrole/getPage")
    public PageInfo<SysRole> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/sysrole/addRoles")
    void addRoles(@RequestBody SysRole sysRole);

    @PostMapping("/sysrole/updateRoles")
    void updateRoles(@RequestBody SysRole sysRole);

    @PostMapping("/sysrole/checkRoles")
    Integer checkRoles(String roleName);

    @GetMapping("/sysrole/delRoles")
    void delRoles(Integer id);

    @PostMapping("/sysrole/selectRoles")
    SysRole selectRoles(Integer id);

    @GetMapping("/sysrole/getRole")
    List<SysRole> getRole();
}


