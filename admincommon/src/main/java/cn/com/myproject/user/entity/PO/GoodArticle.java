package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

/**
 * Created by LSG on 2017/8/23 0023.
 */
public class GoodArticle extends BasePO {

    private String goodArticleId;
    private String title;
    private String content;
    private String cover;
    private int seqno;
    //详情内容
    private String contentUrl;

    public String getGoodArticleId() {
        return goodArticleId;
    }

    public void setGoodArticleId(String goodArticleId) {
        this.goodArticleId = goodArticleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
