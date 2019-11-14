package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther LeiJia
 * @create 2017.9.26
 */
@ApiModel(value = "CouponClaimUser", description = "用户认领优惠劵记录对象")
public class CouponClaimUser extends BasePO{
    @ApiModelProperty(value = "用户认领优惠劵记录表业务ID")
    private String couponClaimUserId;

    @ApiModelProperty(value = "优惠劵表业务ID")
    private String couponId;

    @ApiModelProperty(value = "商品ID")
    private String userId;

    @ApiModelProperty(value = "是否认领:0：未认领；1：认领了")
    private int isClaim;

    @ApiModelProperty(value = "认领时间")
    private Long claimTime;

    public CouponClaimUser(){}

    public CouponClaimUser(String couponClaimUserId,String couponId,String userId){
        this.couponClaimUserId = couponClaimUserId;
        this.couponId = couponId;
        this.userId = userId;
    }

    public CouponClaimUser(String couponId,String userId){
        this.couponId = couponId;
        this.userId = userId;
    }

    public CouponClaimUser(String couponId){
        this.couponId = couponId;}

    public String getCouponClaimUserId() {
        return couponClaimUserId;
    }

    public void setCouponClaimUserId(String couponClaimUserId) {
        this.couponClaimUserId = couponClaimUserId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIsClaim() {
        return isClaim;
    }

    public void setIsClaim(int isClaim) {
        this.isClaim = isClaim;
    }

    public Long getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Long claimTime) {
        this.claimTime = claimTime;
    }
}
