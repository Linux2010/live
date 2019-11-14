package cn.com.myproject.user.controller;

import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.ISysResourceService;
import cn.com.myproject.service.ISysRoleResourceService;
import cn.com.myproject.service.ISysRoleService;
import cn.com.myproject.sysuser.entity.PO.SysResource;
import cn.com.myproject.sysuser.entity.PO.SysRole;
import cn.com.myproject.sysuser.entity.PO.SysRoleResource;
import cn.com.myproject.sysuser.entity.VO.MenuVO;
import cn.com.myproject.util.GridJsonResult;
import cn.com.myproject.util.JsonResultUtil;
import cn.com.myproject.util.Message;
import cn.com.myproject.util.MessageUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 权限控制
 */
@Controller
@RequestMapping("/auth/manage")
public class SysAuthController {

    @Autowired
    private ISysResourceService sysResourceService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;

    @RequestMapping({"/index","/"})
    public String index(HttpServletRequest request,HttpServletResponse response,Model model){

          //1、查询出所有的资源
         // List<SysResource> sysResourceList = sysResourceService.getSysResouce(new SysResource());
        //model.addAttribute("sysResourceList",sysResourceList);
        return "sys/auth";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Map getMenus(String menuId, HttpServletRequest request, HttpServletResponse response) {
        return new HashMap();
    }

    @ResponseBody
    @RequestMapping("/getRoleList")
    public PageInfo<SysRole> getList(Integer rows, Integer page) {
        PageInfo<SysRole> list = sysRoleService.getPage(page, rows);
        return list;
    }

    @ResponseBody
    @RequestMapping(value="/auth-json")
    public GridJsonResult getAuthResult(){
        List<SysResource> sysResourceList = sysResourceService.getSysResouce(new SysResource());
        return JsonResultUtil.getGridJson(sysResourceList);
    }
    @ResponseBody
    @RequestMapping(value="/getResourceJson")
    public String getMenuJson(String roleId){
        StringBuffer data  = new StringBuffer();
        data.append("[");
        List<SysResource> resourceList  = sysResourceService.getSysResourceTree(new SysResource(),"0");
        //com.enation.app.base.core.model.AuthAction authAction=null;
        List<SysRoleResource> sysRoleResourceList = null;
        if(StringUtils.isNotBlank(roleId)) {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(roleId);
            sysRoleResourceList = sysRoleResourceService.getSysRoleResource(sysRoleResource);
        }
        int i=0;
        for(SysResource sysResource:resourceList){
            if(i!=0){
                data.append(",");
            }
            data.append(this.menutoJson(sysResource,sysRoleResourceList));
            i++;
        }
        data.append("]");
        return data.toString();
    }

    private String menutoJson(SysResource sysResource,List<SysRoleResource> sysRoleResourceList){
        StringBuffer data  = new StringBuffer();
        data.append("{\"id\":\""+sysResource.getResourceId()+"\", \"text\":\""+sysResource.getResourceName()+"\"");
        if(null != sysRoleResourceList && sysRoleResourceList.size()>0){
            for (SysRoleResource sysRoleResource : sysRoleResourceList) {
                if((sysRoleResource.getResourceId()).equals(sysResource.getResourceId()) && !sysResource.getHasChildren()){
                    data.append(",\"checked\":true");
                }
            }
        }
        if(sysResource.getHasChildren()){ //如果有子，继续
            data.append(",\"children\":[");
            int i=0;
            List<SysResource> menuList= sysResource.getChildren();
            for(SysResource child:menuList){
                if(i!=0){
                    data.append(",");
                }
                if(sysRoleResourceList!=null){
                    data.append(this.menutoJson(child,sysRoleResourceList));
                }else{
                    data.append(this.menutoJson(child,null));
                }
                i++;
            }
            data.append("]");
        }
        data.append("} ");
        return data.toString();
    }

    @RequestMapping(value = "/authSave")
    @ResponseBody
    public Map<String,Object> authSave(String resourceIds,String roleId){
        Map<String,Object> result = new HashMap<String,Object>();
        if(StringUtils.isBlank(roleId)){
            result.put("message","参数缺失");
            result.put("status","fail");
            return result;
        }
        if(StringUtils.isNotBlank(resourceIds)){
            resourceIds = resourceIds.substring(resourceIds.indexOf(",") + 1);
        }
        boolean sign = sysRoleResourceService.batchEditSysRoleResource(resourceIds,roleId);
        if(sign){
            result.put("status","success");
        }else{
            result.put("message","更新失败");
            result.put("status","fail");
        }
        return result;
    }
}
