package cn.com.myproject.logi.entity.VO;

/**
 * Created by JYP on 2017/9/27 0027.
 */
public class OrderDeliverVO {
    private String orderNo;
    private String orderType;
    private Double orderMoney;
    private Double orderYl;
    private String userName;
    private long createTime;
    private String deliNo;
    private String deliId;
    private String name;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Double getOrderYl() {
        return orderYl;
    }

    public void setOrderYl(Double orderYl) {
        this.orderYl = orderYl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDeliNo() {
        return deliNo;
    }

    public void setDeliNo(String deliNo) {
        this.deliNo = deliNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliId() {
        return deliId;
    }

    public void setDeliId(String deliId) {
        this.deliId = deliId;
    }
}
