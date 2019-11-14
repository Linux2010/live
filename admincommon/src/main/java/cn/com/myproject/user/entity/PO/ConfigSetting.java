package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/8/9 0009.
 */
@ApiModel(value = "configSetting", description = "系统参数对象")
public class ConfigSetting extends BasePO {

    @ApiModelProperty(value = "系统参数id")
    private String configId;
    @ApiModelProperty(value = "系统参数名称")
    private String config_name;
    @ApiModelProperty(value = "系统参数标识")
    private String config_sign;
    @ApiModelProperty(value = "系统参数对应的值")
    private String config_value;
    @ApiModelProperty(value = "注释")
    private String remark;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfig_name() {
        return config_name;
    }

    public void setConfig_name(String config_name) {
        this.config_name = config_name;
    }

    public String getConfig_sign() {
        return config_sign;
    }

    public void setConfig_sign(String config_sign) {
        this.config_sign = config_sign;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
