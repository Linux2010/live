package cn.com.myproject.enums;

/*
 * Created by pangdatao on 2017-09-15
 * desc：订单标记
 */
public enum OrderEnum implements BaseEnum{

    goumaikecheng("a"),
    dashang("b"),
    huiyuanchongzhi("c"),
    CHARGE_TAEL("d"), //充值银两
    livegift("f"),
    shoporder("o");

    private final String key;

    private OrderEnum(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

}