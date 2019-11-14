package cn.com.myproject.user.controller;


import cn.com.myproject.service.ISysUserService;
import cn.com.myproject.service.IUserRoleService;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liyang-macbook on 2017/7/11.
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IUserRoleService userRoleService;

    @RequestMapping("/")
    public String index() {
        return "sys/user";
    }

    @RequestMapping("/add")
    public String add() {
       return "sys/user_add";
    }

    @RequestMapping("/getUser")
    public String getUser(String userId,HttpServletRequest request,SysUserVO model){
        request.setAttribute("user", sysUserService.findByUserId(userId));
        return "/editUser";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<SysUserVO> list(Integer rows, Integer page,String keyword) {
        PageInfo<SysUserVO> info = sysUserService.getPage(page,rows,keyword);
        return info;
    }

    @RequestMapping("/demoUE")
    public String demoUE() {
        return "demoUE";
    }

    @RequestMapping("/getAllUser")
    public List<SysUser> getAllUser(HttpServletRequest request,SysUserVO model){
        List<SysUser> user = sysUserService.findAll();
        request.setAttribute("userList", user);
        return user;
    }

    @RequestMapping("/searchUserIdAndName")
    @ResponseBody
    public List<User> searchUserIdAndName(){
        return sysUserService.searchUserIdAndName();
    }

    @ResponseBody
    @RequestMapping("/addUsers")
    public Map<String,Object> addUsers(SysUser sysUser,String roleIds,String linkUserId_add) {
        if(sysUser != null){
            if(StringUtils.isNotBlank(linkUserId_add)){
                sysUser.setLinkUserId(linkUserId_add.replace(" ", ""));
            }
        }
        Map<String,Object> result = new HashMap();
        try {
            SysUser sysUserVO = sysUserService.getSysUserByUserName(sysUser.getUserName());
            if(null != sysUserVO){
                result.put("status","fail");
                result.put("message","用户名已存在");
                return result;
            }
            sysUserVO = sysUserService.getSysUserByLoginName(sysUser.getLoginName());
            if(null != sysUserVO){
                result.put("status","fail");
                result.put("message","登录名已存在");
                return result;
            }
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            if(StringUtils.isNotBlank(roleIds)){
                roleIds = roleIds.substring(roleIds.indexOf(",")+1);
                String[] roleIdList = roleIds.split(",");
                for(String roleId : roleIdList){
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setVersion(1);
                    sysUserRole.setStatus((short)1);
                    sysUserRoleList.add(sysUserRole);
                }
            }
            sysUser.setSysUserRoleList(sysUserRoleList);
            sysUser.setPassword(new BCryptPasswordEncoder(4).encode("888888"));
            sysUser.setCreateTime(System.currentTimeMillis());
            sysUserService.addUsers(sysUser);
            result.put("status","success");
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加后台账户",ex.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    public int checkUsers(String userName){
        int result = 0;
        try {
            Integer info = sysUserService.checkUsers(userName);
            if (null != info && info.intValue()>0) {
                result = 1;
            }
        }catch (Exception ex){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加后台账户，检验用户名",ex.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String,Object> updateUsers(SysUser sysUser,String roleIds,String linkUserId_update) {
        if(sysUser != null){
            if(StringUtils.isNotBlank(linkUserId_update)){
                sysUser.setLinkUserId(linkUserId_update.replace(" ", ""));
            }
        }
        Map<String,Object> result = new HashMap<>();
        try {
            SysUser dataSysUser =  sysUserService.findByUserId(sysUser.getUserId());

            SysUser sysUserVO = sysUserService.getSysUserByUserName(sysUser.getUserName());

            if(null != sysUserVO && !(sysUserVO.getUserId().equals(dataSysUser.getUserId())) && sysUserVO.getUserName().equals(sysUser.getUserName())){
                result.put("status","fail");
                result.put("message","用户名已存在");
                return result;
            }

            sysUserVO = sysUserService.getSysUserByLoginName(sysUser.getLoginName());
            if(null != sysUserVO && !(sysUserVO.getUserId().equals(dataSysUser.getUserId())) && sysUserVO.getLoginName().equals(sysUser.getLoginName())){
                result.put("status","fail");
                result.put("message","登录名已存在");
                return result;
            }

            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            if(StringUtils.isNotBlank(roleIds)){
                roleIds = roleIds.substring(roleIds.indexOf(",")+1);
                String[] roleIdList = roleIds.split(",");
                for(String roleId : roleIdList){
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setVersion(1);
                    sysUserRole.setStatus((short)1);
                    sysUserRoleList.add(sysUserRole);
                }
            }
            sysUser.setSysUserRoleList(sysUserRoleList);
            sysUserService.updateUsers(sysUser);
            result.put("status","success");

        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改后台账户",e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/updateUserPwd")
    public Map<String,Object> updateUserPwd(SysUser sysUser) {
        Map<String,Object> result = new HashMap<>();
        try {
            if(StringUtils.isNotBlank(sysUser.getPassword())){
                sysUser.setPassword(new BCryptPasswordEncoder(4).encode(sysUser.getPassword()));
            }
            sysUserService.updateUsers(sysUser);
            result.put("status","success");
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改后台账户密码",e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delusers")
    public void delusers(@RequestParam(value = "id") Integer id) {
        sysUserService.delusers(id);
    }

    @ResponseBody
    @RequestMapping("/selectUser")
    public Map<String,Object> selectUsers(@RequestParam("id") Integer id) {
        Map<String,Object> result = new HashMap<>();
        SysUser user = sysUserService.selectUsers(id);
        if(null != user){
            List<SysUserRole> sysUserRoleList = userRoleService.findSysUserRoleByUserId(user.getUserId());
            result.put("userRoleList",sysUserRoleList);
        }
        result.put("user",user);
        result.put("status","success");
        return result;
    }

    /**
     * 查询关联的前台用户ID
     *
     * @param userId
     * @return
     */
    @RequestMapping("/searchLinkUserId")
    @ResponseBody
    public String searchLinkUserId(String userId){
        String linkUserId = "";
        SysUser sysUser = sysUserService.findByUserId(userId);
        if(sysUser != null){
            linkUserId = sysUser.getLinkUserId();
        }
        return linkUserId;
    }

}