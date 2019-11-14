package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class StorageStatsVO implements Serializable {
    /**
     * 需要查询的开始时间戳（单位：毫秒）
     * 必填：是
     * */
    private Integer startDate;
    /**
     * 需要查询的结束时间戳（单位：毫秒
     * 必填：是
     * */
    private Integer endDate;
    /**
     * 获取存储数据列表分页后的索引
     * 必填：是
     * */
    private Integer currentPage;
    /**
     * 获取存储数据列表一页的记录数（若为-1，表示不用分页）
     * 必填：是
     * */
    private Integer pageSize;

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
