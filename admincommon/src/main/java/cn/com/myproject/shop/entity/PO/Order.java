package cn.com.myproject.shop.entity.PO;

import cn.com.myproject.util.BasePO;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单实体类
 */
public class Order extends BasePO{

    // 订单ID
    private String orderId;

    // 订单编号
    private String orderNo;

    // 订单类型：1是金额商品订单，2是银两商品订单
    private int orderType;

    // 用户ID
    private String userId;

    // 收货地址ID
    private String addressId;

    // 订单金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderMoney;

    // 优惠金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderYh;

    // 应付金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderYf;

    // 实付金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderSf;

    // 支付状态：1是待付款，2是已付款，3是退款
    private int payStatus;

    // 发货状态：1是待发货，2是待收货，3是待评价
    private int orderStatus;

    // 用户名称
    private String userName;

    // 用户手机号
    private String userPhone;

    // 用户收货地址
    private String userAddress;

    // 快递单号
    private String kdNo;

    // 物流公司的ID
    private String logiId;

    // 优惠券ID
    private String couponId;

    // 订单商品详情
    private List<OrderDetail> orderDetailList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public double getOrderYh() {
        return orderYh;
    }

    public void setOrderYh(double orderYh) {
        this.orderYh = orderYh;
    }

    public double getOrderYf() {
        return orderYf;
    }

    public void setOrderYf(double orderYf) {
        this.orderYf = orderYf;
    }

    public double getOrderSf() {
        return orderSf;
    }

    public void setOrderSf(double orderSf) {
        this.orderSf = orderSf;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getKdNo() {
        return kdNo;
    }

    public void setKdNo(String kdNo) {
        this.kdNo = kdNo;
    }

    public String getLogiId() {
        return logiId;
    }

    public void setLogiId(String logiId) {
        this.logiId = logiId;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
}