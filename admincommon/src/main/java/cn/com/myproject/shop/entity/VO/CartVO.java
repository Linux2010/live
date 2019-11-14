package cn.com.myproject.shop.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-09-13
 * desc：购物车View实体类
 */
@ApiModel(value = "CartVO", description = "购物车")
public class CartVO {

    // 购物车ID
    @ApiModelProperty(value = "购物车ID")
    private String cartId;

    // 商品ID
    @ApiModelProperty(value = "商品ID")
    private String goodsId;

    // 商品名称
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    // 商品主图
    @ApiModelProperty(value = "商品主图")
    private String mastImg;

    // 商品数量
    @ApiModelProperty(value = "商品数量")
    private int goodsNum;

    // 商品规格ID:以1:12;2:23这样的形式存储
    @ApiModelProperty(value = "商品规格ID:以1:12;2:23这样的形式存储")
    private String goodsSpecId;

    // 商品规格值：以颜色:红色;大小:175cm的形式储存
    @ApiModelProperty(value = "商品规格值：以颜色:红色;大小:175cm的形式储存")
    private String goodsSpecVal;

    // 商品价格
    @ApiModelProperty(value = "商品价格")
    private double mktPrice;

    // 是否选中：0代表未选中，1代表已选中
    @ApiModelProperty(value = "是否选中：0代表未选中，1代表已选中")
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMastImg() {
        return mastImg;
    }

    public void setMastImg(String mastImg) {
        this.mastImg = mastImg;
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

    public double getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(double mktPrice) {
        this.mktPrice = mktPrice;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}