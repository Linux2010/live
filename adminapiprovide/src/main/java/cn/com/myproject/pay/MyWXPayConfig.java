package cn.com.myproject.pay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by liyang-macbook on 2017/9/7.
 */
public class MyWXPayConfig implements WXPayConfig {

    private byte[] certData;

    private String key = "19db41eb64ac48a68e44ffb3e9959cb3";

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
        return "wx5a3468977fdf22c8";
    }
    @Override
    public String getMchID() {
        return "1488299762";
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
