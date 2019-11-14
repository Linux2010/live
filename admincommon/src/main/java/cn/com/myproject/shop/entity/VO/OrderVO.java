package cn.com.myproject.shop.entity.VO;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单View实体类
 */
public class OrderVO {

    // 订单ID
    private String orderId;

    // 订单编号
    private String orderNo;

    // 订单类型：1是金额商品订单，2是银两商品订单
    private int orderType;

    // 订单金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderMoney;

    // 优惠金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderYh;

    // 应付金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderYf;

    // 实付金额(如果订单是金额商品订单，则存储金额，如果订单是银两商品订单，则存储银两)
    private double orderSf;

    // 用户名
    private String userName;

    // 订单时间
    private long orderTime;

    // 支付状态：1是待付款，2是已付款，3是退款
    private int payStatus;

    // 发货状态：1是待发货，2是待收货，3是待评价
    private int orderStatus;

    // 订单状态：1是正常，2是已取消
    private int status;

    // 开始时间(用于订单时间的查询)
    private String startTime;

    // 结束时间(用于订单时间的查询)
    private String endTime;

    // 当前第几页
    private int pageNum;

    // 每页多少条
    private int pageSize;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}