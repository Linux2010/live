package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/18.
 */
public class ListVideoVO implements Serializable {

    /**
     * 获取视频列表分页后的索引
     * 必填：是
     * */
    private Integer currentPage;
    /**
     * 获取视频列表一页的记录数（若为-1，表示不用分页）
     * 必填：是
     * */
    private Integer pageSize;
    /**
     * 根据视频状态过滤选择（0表示获取所有状态视频，10表示初始，20表示失败，30表示处理中，40表示成功，50表示屏蔽）
     * 必填：是
     * */
    private Integer status;
    /**
     * 根据视频分类过滤选择（0表示获取所有分类视频）
     * 必填：是
     * */
    private Integer type;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
