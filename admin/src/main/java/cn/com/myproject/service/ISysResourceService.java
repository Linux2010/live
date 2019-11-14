package cn.com.myproject.service;

import cn.com.myproject.sysuser.entity.PO.SysResource;
import cn.com.myproject.sysuser.entity.VO.MenuVO;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@FeignClient(value = "admin-api-provide",url = "${feignclient.url}")
public interface ISysResourceService {

    @GetMapping("/sysresource/getMenu")
    LinkedHashSet<MenuVO> getMenu(@RequestParam("menuId") String menuId, @RequestParam("userId") String userId);

    @PostMapping("/sysresource/getSysResource")
    List<SysResource> getSysResouce(@RequestBody SysResource sysResource);

    @PostMapping("/sysresource/getSysResourceTree")
    List<SysResource> getSysResourceTree(@RequestBody SysResource sysResource,@RequestParam("treeId") String treeId);

    @GetMapping("/sysresource/getSysResourceByResourceId")
    SysResource getSysResourceByResourceId(@RequestParam("resourceId") String resourceId);

    @PostMapping("/sysresource/addSysResource")
    int addSysResource(@RequestBody SysResource sysResource);

    @PostMapping("/sysresource/updateSysResource")
    int updateSysResource(@RequestBody SysResource sysResource);

    @PostMapping("/sysresource/deleteSysResource")
    int deleteSysResource(@RequestBody String resourceId);

}


