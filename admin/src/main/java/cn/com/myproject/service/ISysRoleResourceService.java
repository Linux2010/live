package cn.com.myproject.service;

import cn.com.myproject.sysuser.entity.PO.SysRoleResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface ISysRoleResourceService {

    @PostMapping("/sysroleresource/getSysRoleResource")
    List<SysRoleResource> getSysRoleResource(@RequestBody SysRoleResource sysRoleResource);

    @PostMapping("/sysroleresource/getListByResourceIds")
    List<SysRoleResource> getListByResourceIds(@RequestBody String resourceIds);

    @PostMapping("/sysroleresource/batchInsert")
    void batchInsert(@RequestBody List<SysRoleResource> sysRoleResources);

    @PostMapping("/sysroleresource/batchDelete")
    void batchDelete(@RequestBody List<SysRoleResource> sysRoleResources);

    @GetMapping("/sysroleresource/batchEditSysRoleResource")
    boolean batchEditSysRoleResource(@RequestParam("resourceIds") String resourceIds, @RequestParam("roleId") String roleId);

}


