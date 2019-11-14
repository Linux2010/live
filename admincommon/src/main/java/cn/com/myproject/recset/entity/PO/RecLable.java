package cn.com.myproject.recset.entity.PO;

import cn.com.myproject.util.BasePO;

/**
 * @title 推荐标签
 * Created by JYP on 2017/8/17 0017.
 */
public class RecLable extends BasePO{

    private String recLabId;
    private String recLabName;
    //类型 1：今日推荐
    private String recLabMark;
    public String getRecLabId() {
        return recLabId;
    }

    public void setRecLabId(String recLabId) {
        this.recLabId = recLabId;
    }

    public String getRecLabName() {
        return recLabName;
    }

    public void setRecLabName(String recLabName) {
        this.recLabName = recLabName;
    }

    public String getRecLabMark() {
        return recLabMark;
    }

    public void setRecLabMark(String recLabMark) {
        this.recLabMark = recLabMark;
    }
}
