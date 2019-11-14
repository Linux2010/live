package cn.com.myproject.service;

import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ISysUserService {

    @GetMapping("/sysuser/getByLoginName")
    SysUserVO getByLoginName(@RequestParam("loginName") String loginName);

    @GetMapping("/sysuser/getByUserName")
    SysUserVO getByUserName(@RequestParam("userName") String userName);

    @GetMapping("/sysuser/getPage")
    PageInfo<SysUserVO> getPage(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("keyword") String keyword);

    @PostMapping("/sysuser/addUser")
    void addUsers(@RequestBody SysUser sysUser);


    @PostMapping("/sysuser/updateUser")
    void updateUsers(@RequestBody SysUser sysUser);

    @PostMapping("/sysuser/delusers")
    void delusers(@RequestBody Integer id);

    @GetMapping("/sysuser/findByUserId")
    SysUser findByUserId(@RequestParam("userId") String userId);

    @GetMapping("/sysuser/findAll")
    List<SysUser> findAll();

    @GetMapping("/sysuser/checkUser")
    Integer checkUsers(@RequestParam("userName") String userName);

    @PostMapping("/sysuser/selectUser")
    SysUser selectUsers(Integer id);

    @GetMapping("/sysuser/getSysUserByLoginName")
    SysUser getSysUserByLoginName(@RequestParam("loginName") String loginName);

    @GetMapping("/sysuser/getSysUserByUserName")
    SysUser getSysUserByUserName(@RequestParam("userName") String userName);

    @GetMapping("/sysuser/searchUserIdAndName")
    List<User> searchUserIdAndName();

}