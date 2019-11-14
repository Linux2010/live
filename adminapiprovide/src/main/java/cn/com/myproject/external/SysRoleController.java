package cn.com.myproject.external;

import cn.com.myproject.sysuser.entity.PO.SysRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.sysuser.service.ISysRoleService;
import cn.com.myproject.sysuser.service.ISysUserService;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@RestController
@RequestMapping("/sysrole")
public class SysRoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private ISysRoleService service;

    @GetMapping("/getPage")
    public PageInfo<SysRole> getPage(int pageNum, int pageSize) {
        return service.getPage(pageNum, pageSize);
    }

    @PostMapping("/addRoles")
    public int addRoles(@RequestBody SysRole sysRole) {
        int result = 0;
        try {
            if (org.apache.commons.lang.StringUtils.isNotBlank(sysRole.getRoleName())) {
                sysRole.setRoleId(UUID.randomUUID().toString().replace("-", ""));
                sysRole.setRoleName(sysRole.getRoleName());
                sysRole.setVersion(1);
                sysRole.setStatus((short) 1);
                service.addRoles(sysRole);
            }
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @PostMapping("/updateRoles")
    public String updateRoles(@RequestBody SysRole sysRole) {
        String result = "";
        try {
            service.updateRoles(sysRole);
            result = "修改成功!";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "修改失败！";
        }
        return result;
    }

    @PostMapping("/checkRoles")
    public Integer checkRoles(@RequestBody String roleName) {
        Integer info = service.checkRoles(roleName);
        if (1 == info) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/delRoles")
    public void delRoles(@RequestBody Integer id) {
        service.delRoles(id);
    }

    @PostMapping("/selectRoles")
    public SysRole selectRoles(@RequestBody Integer id) {
        SysRole role = new SysRole();
        role = service.selectRoles(id);
        return role;
    }

    @GetMapping("/getRole")
    public List<SysRole> getRole(){
        return service.getRole();
    }

}
