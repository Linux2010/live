package cn.com.myproject.sms.zhutong;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2017/7/25.
 */
@Component
@ConfigurationProperties(prefix = "sms.zhutong")
public class ZhuTongSmsProp {

    private String signature;

    /**
     * 国内单挑短信url
     * */
    private String url;

    /**
     * 国内批量短信url
     * */
    private String batchurl;

    /**
     * 国际短信url
     * */
    private String gurl;

    private String username;
    private String password;

    private String gusername;
    private String gpassword;
    /**
     * 验证码产品ID
     * */
    private String productid;

    /**
     * 通知产品ID
     * */
    private String productid2;

    /**
     * 营销产品ID
     * */
    private String productid3;

    private String productIdG;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductid2() {
        return productid2;
    }

    public void setProductid2(String productid2) {
        this.productid2 = productid2;
    }

    public String getProductid3() {
        return productid3;
    }

    public void setProductid3(String productid3) {
        this.productid3 = productid3;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBatchurl() {
        return batchurl;
    }

    public void setBatchurl(String batchurl) {
        this.batchurl = batchurl;
    }

    public String getGurl() {
        return gurl;
    }

    public void setGurl(String gurl) {
        this.gurl = gurl;
    }

    public String getProductIdG() {
        return productIdG;
    }

    public void setProductIdG(String productIdG) {
        this.productIdG = productIdG;
    }

    public String getGusername() {
        return gusername;
    }

    public void setGusername(String gusername) {
        this.gusername = gusername;
    }

    public String getGpassword() {
        return gpassword;
    }

    public void setGpassword(String gpassword) {
        this.gpassword = gpassword;
    }
}
