package cn.com.myproject.user.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by leijia on 2017/6/30.
 */

@ApiModel(value = "TeacerRewardVO", description = "教头打赏返回结果对象")
public class TeacerRewardVO {

   @ApiModelProperty(value="打赏订单Id")
   private  String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
