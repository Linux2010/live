package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class GetLbsReturnVO implements Serializable {
    /**
     * httpDNS的IP访问地址，用于避免后续查询的DNS解析时间、以及域名劫持
     * */
    private String lbs;

    /**
     *
     *  upload	List	上传节点列表（前面的优先级高）
     * */
    private List<String> upload;


    private String code;
    private String message;

    public String getLbs() {
        return lbs;
    }

    public void setLbs(String lbs) {
        this.lbs = lbs;
    }

    public List<String> getUpload() {
        return upload;
    }

    public void setUpload(List<String> upload) {
        this.upload = upload;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
