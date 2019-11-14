package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户推送
 */
public class UserPush extends BasePO{

    @ApiModelProperty(value = "业务Id",required = false)
    private String pushId;

    @ApiModelProperty(value = "会员Id",required = false)
    private String userId;

    @ApiModelProperty(value = "手机号",required = false)
    private String phone;

    @ApiModelProperty(value = "账户",required = false)
    private String account;

    @ApiModelProperty(value = "设备号",required = false)
    private String device;

    @ApiModelProperty(value = "别名",required = false)
    private String alias;

    @ApiModelProperty(value = "标签",required = false)
    private String tag;

    @ApiModelProperty(value = "设备类型",required = false)
    private Short devicetype;

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Short getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(Short devicetype) {
        this.devicetype = devicetype;
    }
}