package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/8/29 0029.
 */
@ApiModel(value = "UserTeacherRewardOrder", description = "用户打赏教头记录对像")
public class UserTeacherRewardOrder extends BasePO {
    @ApiModelProperty(value = "用户打赏教头表业务ID")
    private String userTeacherRewardId;
    @ApiModelProperty(value = "关注者用户业务ID")
    private String userId;
    @ApiModelProperty(value = "教头用户业务ID")
    private String teacherId;
    @ApiModelProperty(value = "打赏金额")
    private double money;
    @ApiModelProperty(value = "打赏支付订单号")
    private String  userTeacherRewardOrderId;
    @ApiModelProperty(value = "第三方交易订单号")
    private String  transactionId;
    @ApiModelProperty(value = "订单状态（0：代付款；1：已购买)")
    private int payStatus;
    @ApiModelProperty(value = "支付方式(0:银两支付；1:支付宝；2：微信；3：Apple Pay 4：Andriod Pay)")
    private int payWay;
    @ApiModelProperty(value = "订单状态（0：未支付；1：已支付)")
    private int orderStatus;
    public UserTeacherRewardOrder(){

    }
    public UserTeacherRewardOrder(String userId, String teacherId){
        this.userId = userId;
        this.teacherId = teacherId;
    }

    public UserTeacherRewardOrder(String userTeacherRewardOrderId){
        this.userTeacherRewardOrderId = userTeacherRewardOrderId;
    }

    public String getUserTeacherRewardId() {
        return userTeacherRewardId;
    }

    public void setUserTeacherRewardId(String userTeacherRewardId) {
        this.userTeacherRewardId = userTeacherRewardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUserTeacherRewardOrderId() {
        return userTeacherRewardOrderId;
    }

    public void setUserTeacherRewardOrderId(String userTeacherRewardOrderId) {
        this.userTeacherRewardOrderId = userTeacherRewardOrderId;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
