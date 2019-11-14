package cn.com.myproject.shop.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-09-18
 * desc：金额订单View实体类
 */
@ApiModel(value = "OrderMoneyVO", description = "金额订单")
public class OrderMoneyVO {

    // 商品ID
    @ApiModelProperty(value = "商品ID")
    private String goodsId;

    // 商品名称
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    // 商品图片
    @ApiModelProperty(value = "商品图片")
    private String goodsImg;

    // 商品数量
    @ApiModelProperty(value = "商品数量")
    private int goodsNum;

    // 商品属性ID
    @ApiModelProperty(value = "商品属性ID")
    private String goodsSpecId;

    // 商品属性值
    @ApiModelProperty(value = "商品属性值")
    private String goodsSpecVal;

    // 商品金额
    @ApiModelProperty(value = "商品金额")
    private double goodsMoney;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsSpecId() {
        return goodsSpecId;
    }

    public void setGoodsSpecId(String goodsSpecId) {
        this.goodsSpecId = goodsSpecId;
    }

    public String getGoodsSpecVal() {
        return goodsSpecVal;
    }

    public void setGoodsSpecVal(String goodsSpecVal) {
        this.goodsSpecVal = goodsSpecVal;
    }

    public double getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(double goodsMoney) {
        this.goodsMoney = goodsMoney;
    }
}