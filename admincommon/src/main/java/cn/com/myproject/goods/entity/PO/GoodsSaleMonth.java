package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther LeiJia
 * @create 2017.9.30
 */
@ApiModel(value = "GoodsSaleMonth", description = "商品月销统计表对象")
public class GoodsSaleMonth extends BasePO{

    @ApiModelProperty(value = "商品月销统计ID")
    private String goodsSaleMonthId;

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    @ApiModelProperty(value = "商品skuID")
    private String goodsSpecId;

    @ApiModelProperty(value = "销售量")
    private int  saleNum;

    @ApiModelProperty(value = "销售额")
    private BigDecimal saleMoney;

    @ApiModelProperty(value = "时间点(年月)")
    private Long stateTime;

    public String getGoodsSaleMonthId() {
        return goodsSaleMonthId;
    }

    public void setGoodsSaleMonthId(String goodsSaleMonthId) {
        this.goodsSaleMonthId = goodsSaleMonthId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpecId() {
        return goodsSpecId;
    }

    public void setGoodsSpecId(String goodsSpecId) {
        this.goodsSpecId = goodsSpecId;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Long getStateTime() {
        return stateTime;
    }

    public void setStateTime(Long stateTime) {
        this.stateTime = stateTime;
    }
}
