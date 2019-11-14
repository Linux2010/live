package cn.com.myproject.shop.entity.VO;

/*
 * Created by pangdatao on 2017-09-15
 * desc：订单详情View实体类
 */
public class OrderDetailVO {

    // 订单ID
    private String orderId;

    // 商品名称
    private String goodsName;

    // 商品图片
    private String goodsImg;

    // 商品规格值
    private String goodsSpecVal;

    // 商品价值(金额值或银两值)
    private String goodsJz;

    // 商品数量
    private int goodsNum;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getGoodsSpecVal() {
        return goodsSpecVal;
    }

    public void setGoodsSpecVal(String goodsSpecVal) {
        this.goodsSpecVal = goodsSpecVal;
    }

    public String getGoodsJz() {
        return goodsJz;
    }

    public void setGoodsJz(String goodsJz) {
        this.goodsJz = goodsJz;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

}