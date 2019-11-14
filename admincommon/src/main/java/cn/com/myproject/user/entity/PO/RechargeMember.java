package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LSG on 2017/8/29 0029.
 */
@ApiModel(value = "rechargeMember", description = "会员充值对象")
public class RechargeMember extends BasePO {

    @ApiModelProperty(value = "充值id")
    private String rechargeMemberId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "充值时间")
    private long rechargeTime;
    @ApiModelProperty(value = "订单编号Id")
    private String orderNo;
    @ApiModelProperty(value = "充值类型:1、月卡 2、年卡 3、永久")
    private int rechargeType;
    @ApiModelProperty(value = "支付类型：1、银两支付 2、在线支付")
    private int payType;
    @ApiModelProperty(value = "支付状态：1、已支付 2、未支付")
    private int payStatus;
    @ApiModelProperty(value = "会员到期时间: 值为-1表示永久会员")
    private long expirationDate;
    @ApiModelProperty(value = "充值金额")
    private double rechargeMoney;

    public String getRechargeMemberId() {
        return rechargeMemberId;
    }

    public void setRechargeMemberId(String rechargeMemberId) {
        this.rechargeMemberId = rechargeMemberId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(long rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public int getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(int rechargeType) {
        this.rechargeType = rechargeType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }
}
