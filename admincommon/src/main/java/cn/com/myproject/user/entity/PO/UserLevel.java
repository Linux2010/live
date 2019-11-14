package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by JYP on 2017/8/8 0008.
 */
@ApiModel(value = "UserLevel", description = "用户级别对象")
public class UserLevel extends BasePO{
    @ApiModelProperty(value = "用户级别名称")
    private String stuname;

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }
}
