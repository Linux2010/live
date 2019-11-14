package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class UploadFileVO implements Serializable {

    /**
     * 请求头参数，上传token
     * */
    private String x_nos_token;
    /**
     * 存储对象的桶名
     * */
    private String bucket;
    /**
     * 生成的唯一对象名
     * */
    private String object;

    private String uploadHost;

    public String getX_nos_token() {
        return x_nos_token;
    }

    public void setX_nos_token(String x_nos_token) {
        this.x_nos_token = x_nos_token;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }



    public String getUploadHost() {
        return uploadHost;
    }

    public void setUploadHost(String uploadHost) {
        this.uploadHost = uploadHost;
    }
}
