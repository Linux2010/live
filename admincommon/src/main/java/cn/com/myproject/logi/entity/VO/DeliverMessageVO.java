package cn.com.myproject.logi.entity.VO;

import cn.com.myproject.util.BasePO;

/**
 * Created by JYP on 2017/9/18 0018.
 */
public class DeliverMessageVO extends BasePO{

    private String deliId;
    private String deliNo;
    private String orderNo;
    private String logiId;
    private String addressId;
    private String name;
    private String code;

    public String getDeliId() {
        return deliId;
    }

    public void setDeliId(String deliId) {
        this.deliId = deliId;
    }

    public String getDeliNo() {
        return deliNo;
    }

    public void setDeliNo(String deliNo) {
        this.deliNo = deliNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLogiId() {
        return logiId;
    }

    public void setLogiId(String logiId) {
        this.logiId = logiId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
