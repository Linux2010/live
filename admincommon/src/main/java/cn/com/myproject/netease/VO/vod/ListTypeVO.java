package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/17.
 */
public class ListTypeVO implements Serializable {

    /**
     * 获取视频分类列表分页后的索引
     * 必填：是
     * */
    private Integer currentPage;
    /**
     * 获取视频分类列表一页的记录数（若为-1，表示不用分页）
     *必填：是
     * **/
    private Integer pageSize;

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
