package cn.com.myproject.sysuser.service;


import cn.com.myproject.sysuser.entity.PO.SysResource;
import cn.com.myproject.sysuser.entity.VO.MenuVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/11.
 */
public interface ISysResourceService {

    /**
     *根据用户ID获取用户对应的菜单
     * */
    LinkedHashSet<MenuVO> getMenu(String menuId, String userId);

    List<SysResource> getSysResource(SysResource sysResource);

    List<SysResource> getSysResourceTree(SysResource sysResource,String treeId);

    SysResource getSysResourceByResourceId(String resourceId);

    int addSysResource(SysResource sysResource);

    int updateSysResource(SysResource sysResource);

    int deleteSysResource(String resourceId);

}
