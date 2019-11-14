package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡
 */
public class UserGroupCard extends BasePO{

    // 用户ID
    private String userId;

    // 激活卡号
    private String cardNo;

    // 激活密码
    private String cardPassword;

    // 激活卡类型：1代表年卡，2代表月卡
    private int cardType;

    // 激活者用户ID
    private String activeUserId;

    // 激活时间
    private long activeTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(String activeUserId) {
        this.activeUserId = activeUserId;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }
}