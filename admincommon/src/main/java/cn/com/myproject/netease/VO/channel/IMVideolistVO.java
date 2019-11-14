package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMVideolistVO implements Serializable {
    /**
     *频道ID，32位字符串
     * 必填：是
     * */
    private String cid;
    /**
     *单页记录数，默认值为10
     * 必填：是
     * */
    private Integer records = 10;
    /**
     *要取第几页，默认值为1
     * 必填：是
     * */
    private Integer pnum = 1;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }
}
