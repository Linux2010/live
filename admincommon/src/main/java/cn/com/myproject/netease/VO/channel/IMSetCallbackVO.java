package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/4.
 */
public class IMSetCallbackVO implements Serializable {
    /**
     * 录制文件生成回调地址(http开头)
     * 必填：是
     * */
    private String recordClk;

    public String getRecordClk() {
        return recordClk;
    }

    public void setRecordClk(String recordClk) {
        this.recordClk = recordClk;
    }
}
