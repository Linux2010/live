package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class UploadFileResultVO implements Serializable {

    /**
     * uuid字符串，服务器端生成的唯一UUID
     * */
    private String requestId;
    /**
     * 下一个上传片在上传块中的偏移。
     * 注意：偏移从0开始，比如：用户上传0-128字节后，
     * 服务器返回的offset为128，下一次上传offset值应置为128
     * */
    private Long offset;
    /**
     * 上传上下文
     * */
    private String context;
    /**
     * 上传回调信息
     * */
    private String callbackRetMsg;
    /**
     * 错误描述信息
     * */
    private String errMsg;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCallbackRetMsg() {
        return callbackRetMsg;
    }

    public void setCallbackRetMsg(String callbackRetMsg) {
        this.callbackRetMsg = callbackRetMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
