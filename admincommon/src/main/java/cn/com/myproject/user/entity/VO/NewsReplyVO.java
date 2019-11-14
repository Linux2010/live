package cn.com.myproject.user.entity.VO;

import cn.com.myproject.user.entity.PO.NewsComment;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 李延超 on 2017/8/31.
 */

@ApiModel(value= "NewsReplyVO",description="资讯回复")

public class NewsReplyVO {

@ApiModelProperty(value = "资讯评论Id")
private String newsCommentId;
    @ApiModelProperty(value="回复用户Id")
    private String replyUserId;
    @ApiModelProperty(value = "回复用户名字")
    private String replyUserName;
    @ApiModelProperty(value ="回复内容")
    private String replyContent;
    @ApiModelProperty(value = "回复时间")
    private Long  replyTime;

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Long replyTime) {
        this.replyTime = replyTime;
    }

    public String getNewsCommentId() {
        return newsCommentId;
    }

    public void setNewsCommentId(String newsCommentId) {
        this.newsCommentId = newsCommentId;
    }
}
