package cn.com.myproject.live.entity.PO.live.entity;

import cn.com.myproject.util.BasePO;

import java.math.BigDecimal;

/**
 * Created by LeiJia on 2017/9/4 0004.
 */
public class GiftBrushOrder extends BasePO {

    private String giftBrushOrderId;

    private String giftOrderId;

    private String userId;

    private String giftId;

    private String giftNumber;

    private BigDecimal giftWorth;

    private BigDecimal giftAllWorth;

    private int payWay;

    private int payStatus;

    private int orderStatus;

    private String imRoomId;

    public String getImRoomId() {
        return imRoomId;
    }

    public void setImRoomId(String imRoomId) {
        this.imRoomId = imRoomId;
    }


    public BigDecimal getGiftAllWorth() {
        return giftAllWorth;
    }

    public void setGiftAllWorth(BigDecimal giftAllWorth) {
        this.giftAllWorth = giftAllWorth;
    }

    public String getGiftOrderId() {
        return giftOrderId;
    }

    public void setGiftOrderId(String giftOrderId) {
        this.giftOrderId = giftOrderId;
    }

    public String getGiftBrushOrderId() {
        return giftBrushOrderId;
    }

    public void setGiftBrushOrderId(String giftBrushOrderId) {
        this.giftBrushOrderId = giftBrushOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftNumber() {
        return giftNumber;
    }

    public void setGiftNumber(String giftNumber) {
        this.giftNumber = giftNumber;
    }

    public BigDecimal getGiftWorth() {
        return giftWorth;
    }

    public void setGiftWorth(BigDecimal giftWorth) {
        this.giftWorth = giftWorth;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
