package cn.com.myproject.api.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信推送模板
 * Created by jyp on 2017/10/31 0031.
 */
public class TemplatConfig {

    private  String temoplateOfflineId = "hCKBlNGW6t75G-FGdWt_FvmWG8oxjnkA2_36_P2sEwk";//我的团队 - 下线模板通知

    private  String temoplatePurchaseId = "xZbBplVeLxKrFg54noTh5KgXTy248JNS0lZZMd1hhfA";//购买模板通知

    public String getTemoplateOfflineId() {
        return temoplateOfflineId;
    }

    public void setTemoplateOfflineId(String temoplateOfflineId) {
        this.temoplateOfflineId = temoplateOfflineId;
    }

    public String getTemoplatePurchaseId() {
        return temoplatePurchaseId;
    }

    public void setTemoplatePurchaseId(String temoplatePurchaseId) {
        this.temoplatePurchaseId = temoplatePurchaseId;
    }
}
