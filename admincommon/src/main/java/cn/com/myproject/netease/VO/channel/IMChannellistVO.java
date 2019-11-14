package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
public class IMChannellistVO implements Serializable {

    /**
     *单页记录数，默认值为10
     * 必填：否
     * */
    private Integer records = 10;
    /**
     *要取第几页，默认值为1
     * 必填：否
     * */
    private Integer pnum = 1;
    /**
     *排序的域，支持的排序域为：ctime（默认）
     * 必填：否
     * */
    private String ofield = "ctime";
    /**
     * 升序还是降序，1升序，0降序，默认为desc
     *  必填：否
     * */
    private Integer sort = 0;

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

    public String getOfield() {
        return ofield;
    }

    public void setOfield(String ofield) {
        this.ofield = ofield;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
