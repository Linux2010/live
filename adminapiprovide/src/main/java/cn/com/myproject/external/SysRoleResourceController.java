package cn.com.myproject.external;


import cn.com.myproject.sysuser.entity.PO.SysRoleResource;
import cn.com.myproject.sysuser.service.ISysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysroleresource")
public class SysRoleResourceController {

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;

    @PostMapping("/getSysRoleResource")
    List<SysRoleResource> getSysRoleResource(@RequestBody SysRoleResource sysRoleResource) {
        List<SysRoleResource> result = sysRoleResourceService.getSysRoleResource(sysRoleResource);
        return result;
    }

    @PostMapping("/getListByResourceIds")
    List<SysRoleResource> getListByResource(@RequestBody String resourceIds) {
        List<SysRoleResource> result = sysRoleResourceService.getListByResourceIds(resourceIds);
        return result;
    }

    @PostMapping("/batchInsert")
    void batchInsert(@RequestBody List<SysRoleResource> sysRoleResources){
        sysRoleResourceService.batchInsert(sysRoleResources);
    }

    @PostMapping("/batchDelete")
    void batchDelete(@RequestBody List<SysRoleResource> sysRoleResources){
        sysRoleResourceService.batchDelete(sysRoleResources);
    }

    @GetMapping("/batchEditSysRoleResource")
    boolean batchEditSysRoleResource(String resourceIds,String roleId){
        return sysRoleResourceService.batchEditSysRoleResource(resourceIds,roleId);
    }
}
