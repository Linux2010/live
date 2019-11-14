package cn.com.myproject.enums;

/**
 * Created by CQC on 2017.9.4.
 */
public enum  CapitalLogDesEnum implements BaseEnum{

    RECHARGE("充值"),
    WITHDRAW("提现"),
    SHAREPROFIT_COURSE("课程分润"),
    SHAREPROFIT_MEMBER("会员分润"),
    SHAREPROFIT_GOODS("商品分润"),
    SIGNIN("签到"),
    BUY_COUSE("购买课程"),
    BUY_VIP("购买VIP"),
    SHARE_COUSE("分享课程");

    private final String key;

    private CapitalLogDesEnum(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

}


