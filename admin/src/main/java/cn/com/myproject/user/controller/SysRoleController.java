package cn.com.myproject.user.controller;

import cn.com.myproject.service.ISysRoleService;
import cn.com.myproject.sysuser.entity.PO.SysRole;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * Created by JYP on 2017/8/3 0003.
 */
@Controller
@RequestMapping("/sysrole")
public class SysRoleController {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private ISysRoleService sysRoleService;

    @RequestMapping("/")
    public String index() {
        return "sys/role";
    }

    @RequestMapping("/add")
    public String add() {
        return "sys/role_add";
    }

    @RequestMapping("/update")
    public String update() {
        return "sys/role_update";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<SysRole> getList(Integer rows,Integer page) {
        PageInfo<SysRole> list = sysRoleService.getPage(page, rows);
        return list;
    }

    @ResponseBody
    @RequestMapping("/addRoles")
    public String addRoles(SysRole sysRole, @RequestParam("rolename") String rolename) {
        String result = "";
        try {
            if (org.apache.commons.lang.StringUtils.isNotBlank(rolename)) {
                sysRole.setRoleId(URLDecoder.decode(rolename, "UTF-8"));
                sysRole.setRoleName(URLDecoder.decode(rolename, "UTF-8"));
                sysRole.setVersion(1);
                sysRole.setStatus((short) 1);
                sysRole.setCreateTime(System.currentTimeMillis());
                sysRoleService.addRoles(sysRole);
            }
            result = "添加成功";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "添加失败";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateRoles")
    public String updateRoles(SysRole sysRole) {
        String result = "";
        try {
            sysRoleService.updateRoles(sysRole);
            result = "修改成功!";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "修改失败！";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/checkRoles")
    public Integer checkRoles(@RequestParam("roleName") String roleName) throws UnsupportedEncodingException {
        logger.info("修改的角色名称:" + URLDecoder.decode(roleName, "UTF-8"));

        Integer info = sysRoleService.checkRoles(roleName);

        if (1 == info) {
            return 1;
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping(value = "/delRoles")
    public void delRoles(@RequestParam(value = "id") Integer id) {
        logger.info(id + "你是大幅度饭");
        // String[] s = id.split(",");
        sysRoleService.delRoles(id);
    }


    @ResponseBody
    @RequestMapping("/selectRoles")
    public SysRole selectRoles(@RequestParam("id") Integer id) {
        SysRole role = new SysRole();
        role = sysRoleService.selectRoles(id);
        logger.info(role + "对象");
        return role;
    }

    @ResponseBody
    @RequestMapping("/getRole")
    public Map<String,Object> getRole() {
        Map<String,Object> result = new HashMap<String,Object>();
        List<SysRole> roleList = sysRoleService.getRole();
        result.put("status","success");
        result.put("roleList",roleList);
        return result;
    }
}
