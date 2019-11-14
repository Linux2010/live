package cn.com.myproject.logi.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by JYP on 2017/9/13 0013.
 */
@ApiModel(value = "logiCompany", description = "物流公司对象")
public class LogiCompany extends BasePO{

    @ApiModelProperty(value = "物流公司业务id")
    private String logiId;
    @ApiModelProperty(value = "物流公司名称")
    private String name;
    @ApiModelProperty(value = "物流公司编码")
    private String code;

    public String getLogiId() {
        return logiId;
    }

    public void setLogiId(String logiId) {
        this.logiId = logiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
