package cn.com.myproject.shop.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-09-13
 * desc：购物车实体类
 */
public class Cart extends BasePO{

    // 购物车ID
    private String cartId;

    // 商品ID
    private String goodsId;

    // 商品数量
    private int goodsNum;

    // 商品规格ID:以1:12;2:23这样的形式存储
    private String goodsSpecId;

    // 商品规格值：以颜色:红色;大小:175cm的形式储存
    private String goodsSpecVal;

    // 商品价格(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double goodsMoney;

    // 商品类型：1是金额商品，2是银两商品
    private int goodsType;

    // 用户ID
    private String userId;

    // 是否选中：0代表未选中，1代表已选中
    private int checked;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}