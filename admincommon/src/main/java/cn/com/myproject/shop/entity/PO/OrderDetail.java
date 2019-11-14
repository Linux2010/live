package cn.com.myproject.shop.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单详情实体类
 */
public class OrderDetail extends BasePO{

    // 订单详情ID
    private String orderDetailId;

    // 订单ID
    private String orderId;

    // 商品ID
    private String goodsId;

    // 商品规格ID
    private String goodsSpecId;

    // 商品规格值
    private String goodsSpecVal;

    // 商品类型：1是金额商品，2是银两商品
    private int goodsType;

    // 商品金额值(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double goodsMoney;

    // 商品数量
    private int goodsNum;

    // 用户ID
    private String userId;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getGoodsSpecVal() {
        return goodsSpecVal;
    }

    public void setGoodsSpecVal(String goodsSpecVal) {
        this.goodsSpecVal = goodsSpecVal;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public double getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(double goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}