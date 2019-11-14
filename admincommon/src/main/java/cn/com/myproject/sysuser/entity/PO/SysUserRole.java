package cn.com.myproject.sysuser.entity.PO;


import cn.com.myproject.util.BasePO;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
public class SysUserRole extends BasePO {
    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
