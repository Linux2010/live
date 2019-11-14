package cn.com.myproject.live.entity.PO.live.entity;

import cn.com.myproject.util.BasePO;

import java.math.BigDecimal;

/**
 * Created by LeiJia on 2017/9/4 0004.
 */
public class GiftBushLog extends BasePO {

    private String giftBrushLogId;

    private String userId;

    private String teacherId;

    private String giftId;

    private String giftNumber;

    private BigDecimal giftWorth;

    private BigDecimal giftAllWorth;

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

    public String getGiftBrushLogId() {
        return giftBrushLogId;
    }

    public void setGiftBrushLogId(String giftBrushLogId) {
        this.giftBrushLogId = giftBrushLogId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
}
