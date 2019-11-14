package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMVodvideolistVO implements Serializable {
    /**
     * 频道ID，32位字符串
     * 必填：是
     * */
    private String cid;
    /**
     * 查询的起始时间戳(毫秒)
     * 必填：是
     * */
    private Long beginTime;
    /**
     * 查询的结束时间戳(毫秒)
     * 必填：是
     * */
    private Long endTime;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
