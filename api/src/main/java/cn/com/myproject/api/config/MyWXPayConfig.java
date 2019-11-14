package cn.com.myproject.api.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * Created by liyang-macbook on 2017/9/7.
 */
public class MyWXPayConfig implements WXPayConfig {

    private byte[] certData;

    private String key = "9bde8d38d38d83c8ddd01947c7acf819";

    private String appID;

    private String mchID;

    public MyWXPayConfig() {
//        String certPath = "/Users/liyang-macbook/Downloads/cert/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = null;
//        try {
//            certStream = new FileInputStream(file);
//            this.certData = new byte[(int) file.length()];
//            certStream.read(this.certData);
//            certStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public String getAppID() {
        return "wx2f3027943ae38987";
    }
    public void setAppID(String appID){
        this.appID = appID;
    }
    @Override
    public String getMchID() {
        return "1243355602";
    }

    public void setMchID(String mchID){
        this.mchID = mchID;
    }
    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }
    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
