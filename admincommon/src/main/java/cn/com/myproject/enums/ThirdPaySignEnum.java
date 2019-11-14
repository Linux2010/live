package cn.com.myproject.enums;

/**
 * Created by CQC on 2017.9.6.  第三方支付订单号标识
 */
public enum ThirdPaySignEnum implements BaseEnum {

    COURCE_ORDER("a"), //课程订单标识
    THCHER_RODER("b"), //教头打赏订单标识
    VIP_BUY_ORDER("c"), // VIP购买标识
    CHARGE_TAEL("d"), //充值银两
    ;

    private final String key;

    private ThirdPaySignEnum(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
