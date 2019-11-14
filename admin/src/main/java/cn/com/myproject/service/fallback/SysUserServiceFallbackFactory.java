package cn.com.myproject.service.fallback;

import cn.com.myproject.service.ISysUserService;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/8.
 */
@Component
public class SysUserServiceFallbackFactory implements FallbackFactory<ISysUserService> {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceFallbackFactory.class);
    @Override
    public ISysUserService create(Throwable cause) {
        return new ISysUserService() {

            @Override
            public SysUserVO getByLoginName(String loginName) {
                logger.error("fallback;reason was:" + cause.getMessage());
                return null;
            }

            @Override
            public SysUserVO getByUserName(String userName) {
                logger.error("fallback;reason was:" + cause.getMessage());
                return null;
            }

            public SysUser getSysUserByLoginName(String loginName) {
                logger.error("fallback;reason was:" + cause.getMessage());
                return null;
            }

            @Override
            public SysUser getSysUserByUserName(String userName) {
                logger.error("fallback;reason was:" + cause.getMessage());
                return null;
            }

            @Override
            public PageInfo<SysUserVO> getPage(int pageNum, int pageSize,String keyword) {
                logger.error("fallback;reason was:" + cause.getMessage());
                return null;
            }

            @Override
            public void addUsers(SysUser sysUser) {

            }

            @Override
            public void updateUsers(SysUser sysUser) {

            }

            @Override
            public void delusers(Integer id) {

            }

            @Override
            public SysUser findByUserId(String userId) {
                return null;
            }

            @Override
            public List<SysUser> findAll() {
                return null;
            }

            @Override
            public Integer checkUsers(String userName) {
                return null;
            }

            @Override
            public SysUser selectUsers(Integer id) {
                return null;
            }

            @Override
            public List<User> searchUserIdAndName(){
                return null;
            }

        };
    }
}