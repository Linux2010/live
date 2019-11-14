package cn.com.myproject.sysuser.service;

import cn.com.myproject.sysuser.entity.PO.SysRole;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import redis.clients.jedis.BinaryClient;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
public interface ISysUserService {

    SysUserVO getByLoginName(String loginName);

    SysUserVO getByUserName(String userName);

    List<SysUser> getAll();

    PageInfo<SysUserVO> getPage(int pageNum, int pageSize,String keyword);

    void addUsers(SysUser sysUser, List<SysUserRole> sysUserRoleList);

    void updateUsers(SysUser sysUser, List<SysUserRole> sysUserRoleList);

    void delusers(Integer id);

    SysUser findByUserId(String userId);

    List<SysUser> findAll();

    Integer checkUsers(String userName);

    SysUser selectUsers(Integer id);

    SysUser getSysUserByLoginName(String loginName);

    SysUser getSysUserByUserName(String userName);

    /**
     * 查询用户ID和用户名
     *
     * @return
     */
    List<User> searchUserIdAndName();

}