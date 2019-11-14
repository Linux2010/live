package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-09-06
 * desc：结算实体类
 */
@ApiModel(value = "JsVo", description = "课程订单结算")
public class JsVo {

    // 订单编号
    @ApiModelProperty(value = "订单编号")
    private String courseOrderNo;

    // 结算是否成功：0代表失败，1代表成功
    @ApiModelProperty(value = "结算是否成功：0代表失败，1代表成功")
    private int resultVal;

    public String getCourseOrderNo() {
        return courseOrderNo;
    }

    public void setCourseOrderNo(String courseOrderNo) {
        this.courseOrderNo = courseOrderNo;
    }

    public int getResultVal() {
        return resultVal;
    }

    public void setResultVal(int resultVal) {
        this.resultVal = resultVal;
    }
}