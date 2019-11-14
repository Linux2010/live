package cn.com.myproject.goods.entity.VO;

import cn.com.myproject.goods.entity.PO.GoodsImg;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther LeiJia
 * @create 2017.9.20
 */
@ApiModel(value = "GoodsSpecStockVO", description = "商品规格库存VO对象")
public class GoodsSpecStockVO extends BasePO{

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    @ApiModelProperty(value = "无规格商品成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "无规格商品市场价格")
    private BigDecimal userPrice;

    @ApiModelProperty(value = "无规格商品会员价")
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "无规格商品重量(克)")
    private Double weight;

    @ApiModelProperty(value = "无规格商品总库存")
    private Integer stockNum;

    @ApiModelProperty(value = "商品的规格对象列表")
    private List<GoodsSpec> goodsSpecs  = new ArrayList<GoodsSpec>();

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public List<GoodsSpec> getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(List<GoodsSpec> goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(BigDecimal userPrice) {
        this.userPrice = userPrice;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }
}
