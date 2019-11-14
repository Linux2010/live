package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.26
 */
@ApiModel(value = "Coupon", description = "商城优惠劵对象")
public class Coupon extends BasePO{

    @ApiModelProperty(value = "优惠劵表业务ID")
    private String couponId;

    @ApiModelProperty(value = "优惠劵名称")
    private String couponName;

    @ApiModelProperty(value = "优惠劵描述")
    private String couponDesc;

    @ApiModelProperty(value = "优惠劵面值金额")
    private BigDecimal couponValue;

    @ApiModelProperty(value = "优惠劵类别0：商品，1：课程，3：全场优惠劵")
    private int couponType;

    @ApiModelProperty(value = "优惠劵图片")
    private String couponImgUrl;

    @ApiModelProperty(value="限制金额(使用该优惠劵的限制金额)")
    private BigDecimal limitMoney;

    @ApiModelProperty(value = "使用条件(如：满500可使用该优惠劵)")
    private String limitMoneyDesc;

    @ApiModelProperty(value = "优惠劵使用开始日期")
    private Long couponBeginTime;

    @ApiModelProperty(value = "优惠劵使用截止日期")
    private Long couponEndTime;

    @ApiModelProperty(value = "序号")
    private int seqno;

    @ApiModelProperty(value = "是否使用该优惠劵0：未使用，1：使用过")
    private int isUse;

    @ApiModelProperty(value = "是否认领:0：未认领；1：认领了")
    private int isClaim;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户登录名称")
    private String loginName;

    public int getIsClaim() {
        return isClaim;
    }

    public void setIsClaim(int isClaim) {
        this.isClaim = isClaim;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public Long getCouponBeginTime() {
        return couponBeginTime;
    }

    public void setCouponBeginTime(Long couponBeginTime) {
        this.couponBeginTime = couponBeginTime;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(BigDecimal limitMoney) {
        this.limitMoney = limitMoney;
    }

    public String getLimitMoneyDesc() {
        return limitMoneyDesc;
    }

    public void setLimitMoneyDesc(String limitMoneyDesc) {
        this.limitMoneyDesc = limitMoneyDesc;
    }

    public Long getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(Long couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getCouponImgUrl() {
        return couponImgUrl;
    }

    public void setCouponImgUrl(String couponImgUrl) {
        this.couponImgUrl = couponImgUrl;
    }
}
