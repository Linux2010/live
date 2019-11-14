package cn.com.myproject.external;

import cn.com.myproject.sysuser.entity.PO.SysRole;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.sysuser.service.ISysUserService;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService service;

    @GetMapping("/getPage")
    public PageInfo<SysUserVO> getPage(int pageNum, int pageSize,String keyword) {
        return service.getPage(pageNum,pageSize,keyword);
    }

    @GetMapping("/getByLoginName")
    public SysUserVO getByLoginName(String loginName) {
        return service.getByLoginName(loginName);
    }

    @GetMapping("/getByUserName")
    public SysUserVO getByUserName(String userName) {
        return service.getByUserName(userName);
    }

   @GetMapping("/findAll")
   public List<SysUser> findAll() {
       List<SysUser> findAllList = service.findAll();
       return findAllList;
   }

    @GetMapping("/findByUserId")
    public SysUser findById(String userId) {
        SysUser user = service.findByUserId(userId);
        return user;
    }

    @GetMapping("/checkUser")
    public Integer checkUser(String userName) {
        Integer info = service.checkUsers(userName);
        if (null != info && 1 <= info) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/selectUser")
    public SysUser selectUser(@RequestBody Integer id) {
        SysUser user = new SysUser();
        user = service.selectUsers(id);
        return user;
    }

    @PostMapping("/delusers")
    public void delUsers(@RequestBody Integer id) {
        service.delusers(id);
    }

    @PostMapping("/updateUser")
    public String updateUsers(@RequestBody SysUser sysUser) {
        String result = "";
        try {
            List<SysUserRole> sysUserRoleList = sysUser.getSysUserRoleList();
            if(null != sysUserRoleList && sysUserRoleList.size() > 0){
                for(SysUserRole sysUserRole:sysUserRoleList){
                    sysUserRole.setUserId(sysUser.getUserId());
                    sysUserRole.setCreateTime(new Date().getTime());
                }
            }
            service.updateUsers(sysUser,sysUserRoleList);
            result = "修改成功!";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "修改失败！";
        }
        return result;
    }

    @PostMapping("/addUser")
    public int addUsers(@RequestBody SysUser sysUser) {
        int result = 0;
        try {
            List<SysUserRole> sysUserRoleList = sysUser.getSysUserRoleList();
            if (StringUtils.isNotBlank(sysUser.getUserName())) {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                sysUser.setUserId(uuid);
                sysUser.setVersion(1);
                sysUser.setStatus((short) 1);
                if(null != sysUserRoleList && sysUserRoleList.size() > 0){
                    for(SysUserRole sysUserRole:sysUserRoleList){
                        sysUserRole.setUserId(uuid);
                        sysUserRole.setCreateTime(new Date().getTime());
                    }
                }
                service.addUsers(sysUser,sysUserRoleList);
            }
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @GetMapping("/getSysUserByLoginName")
    public SysUser getSysUserByLoginName(String loginName) {
        return service.getSysUserByLoginName(loginName);
    }

    @GetMapping("/getSysUserByUserName")
    public SysUser getSysUserByUserName(String userName) {
        return service.getSysUserByUserName(userName);
    }

    @GetMapping("/searchUserIdAndName")
    public List<User> searchUserIdAndName(){
        return service.searchUserIdAndName();
    }

}