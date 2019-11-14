package cn.com.myproject.sysuser.entity.VO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class SysRoleResourceVO{

    private String resourceId;
    private String roleId;
    private Integer id;
    private Long createTime;
    private Short status;
    private Integer version;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFCreateTime() {
        if(null != this.createTime){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createTime));
        }
        return "";
    }
}
