package cn.com.myproject.sysuser.entity.PO;


import cn.com.myproject.util.BasePO;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
public class SysRole extends BasePO {
    private String roleId;
    private String roleName;
    private String remark;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
