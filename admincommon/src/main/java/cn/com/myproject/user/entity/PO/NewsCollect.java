package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

/**
 * Created by 李延超 on 2017/8/30.
 */
public class NewsCollect extends BasePO{
    private String collectId;
    private String userId;
    private String newsId;

    // 文章是否收藏：0是不收藏，1是收藏
    private int iscollect;

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public int getIscollect() {
        return iscollect;
    }

    public void setIscollect(int iscollect) {
        this.iscollect = iscollect;
    }
}
