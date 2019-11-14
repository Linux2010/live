package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.26
 */
@ApiModel(value = "CouponGoods", description = "优惠劵商品对象")
public class CouponGoods extends BasePO{
    @ApiModelProperty(value = "优惠劵商品表业务ID")
    private String couponGoodsId;

    @ApiModelProperty(value = "优惠劵表业务ID")
    private String couponId;

    @ApiModelProperty(value = "商品ID")
    private String goodsId;


    public CouponGoods(){}

    public CouponGoods(String couponId, String goodsId){
        this.couponId  = couponId;
        this.goodsId = goodsId;
    }
    public CouponGoods(String couponGoodsId,String couponId, String goodsId){
        this.couponGoodsId = couponGoodsId;
        this.couponId  = couponId;
        this.goodsId = goodsId;
    }

    public String getCouponGoodsId() {
        return couponGoodsId;
    }

    public void setCouponGoodsId(String couponGoodsId) {
        this.couponGoodsId = couponGoodsId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
