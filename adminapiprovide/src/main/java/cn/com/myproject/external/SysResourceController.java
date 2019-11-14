package cn.com.myproject.external;

import cn.com.myproject.sysuser.entity.PO.SysResource;
import cn.com.myproject.sysuser.entity.VO.MenuVO;
import cn.com.myproject.sysuser.service.ISysResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;
import java.util.UUID;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@RestController
@RequestMapping("/sysresource")
public class SysResourceController {

    @Autowired
    private ISysResourceService sysResourceService;

    @GetMapping("/getMenu")
    public LinkedHashSet<MenuVO> getMenu(String menuId, String userId) {
        return sysResourceService.getMenu(menuId,userId);
    }

    @PostMapping("/getSysResource")
    List<SysResource> getSysResouce(@RequestBody SysResource sysResource) {
        List<SysResource> result = sysResourceService.getSysResource(sysResource);
        return result;
    }

    @PostMapping("/getSysResourceTree")
    List<SysResource> getSysResourceTree(@RequestBody SysResource sysResourc,String treeId){
        List<SysResource> result = sysResourceService.getSysResourceTree(sysResourc,treeId);
        return result;
    }

    @GetMapping("/getSysResourceByResourceId")
    SysResource getSysResourceByResourceId(String resourceId){
        return sysResourceService.getSysResourceByResourceId(resourceId);
    }

    @PostMapping("/addSysResource")
    int addSysResource(@RequestBody SysResource sysResource){
        sysResource.setResourceId(UUID.randomUUID().toString().replace("-", ""));
        sysResource.setVersion(1);
        sysResource.setStatus((short) 1);
        return sysResourceService.addSysResource(sysResource);
    }

    @PostMapping("/updateSysResource")
    int updateSysResource(@RequestBody SysResource sysResource){
        int result = 0;
        if(StringUtils.isNotBlank(sysResource.getResourceId())){
            result =sysResourceService.updateSysResource(sysResource);
        }
        return result;
    }

    @PostMapping("/deleteSysResource")
    int deleteSysResource(@RequestBody String resourceId){
        int result = 0;
        if(StringUtils.isNotBlank(resourceId)){
            result =sysResourceService.deleteSysResource(resourceId);
        }
        return result;
    }
}
