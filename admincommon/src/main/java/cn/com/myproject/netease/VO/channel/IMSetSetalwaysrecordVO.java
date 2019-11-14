package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMSetSetalwaysrecordVO implements Serializable {

    /**
     *频道ID，32位字符串
     * 必填：是
     * */
    private String cid;
    /**
     * 1-开启录制； 0-关闭录制
     * 必填：是
     * */
    private Integer needRecord;
    /**
     *1-flv； 0-mp4
     * */
    private Integer format;
    /**
     *录制切片时长(分钟)，5~120分钟
     * */
    private Integer duration;
    /**
     *录制后文件名（只支持中文、字母和数字），格式为filename_YYYYMMDD-HHmmssYYYYMMDD-HHmmss, 文件名录制起始时间（年月日时分秒) -录制结束时间（年月日时分秒)
     * */
    private String filename;


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getNeedRecord() {
        return needRecord;
    }

    public void setNeedRecord(Integer needRecord) {
        this.needRecord = needRecord;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
