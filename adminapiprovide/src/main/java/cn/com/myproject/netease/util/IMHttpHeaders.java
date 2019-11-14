package cn.com.myproject.netease.util;


import cn.com.myproject.netease.common.CheckSumBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Date;

/**
 * Created by liyang-macbook on 2017/6/29.
 */
public class IMHttpHeaders {
    private static final String APPKEY = "b6ef7a173c2f9fb5ef43f2787984921c";

    private static final String APPSECRET = "b63a571e5494";

    public static HttpHeaders get() {

        String nonce =  "myproject";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APPSECRET, nonce ,curTime);//参考 计算CheckSum的java代码

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.parseMediaType("application/x-www-form-urlencoded;charset=utf-8"));
        headers.set("AppKey", APPKEY);
        headers.set("Nonce", nonce);
        headers.set("CurTime", curTime);
        headers.set("CheckSum", checkSum);

        return headers;
    }

    public static HttpHeaders getJSON() {
        String nonce =  "doers.cn";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APPSECRET, nonce ,curTime);//参考 计算CheckSum的java代码

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.parseMediaType("application/json;charset=utf-8"));
        headers.set("AppKey", APPKEY);
        headers.set("Nonce", nonce);
        headers.set("CurTime", curTime);
        headers.set("CheckSum", checkSum);

        return headers;
    }

    public static HttpHeaders getUpload(String x_nos_token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-nos-token",x_nos_token);
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));

        return headers;
    }
}
