package cn.com.myproject.user.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/31.
 */
@ApiModel(value = "NewsCommentVO",description="资讯评论")
public class NewsCommentVO {

@ApiModelProperty(value="资讯评论Id")
 private String newsCommentId;
@ApiModelProperty(value = "资讯Id")
    private String newsId;
@ApiModelProperty(value = "资讯评论内容")
    private String newsContent;
@ApiModelProperty(value = "资讯评论用户Id")
    private  String userId;
 @ApiModelProperty(value = "资讯评论时间")
 private long commentTime;

    @ApiModelProperty(value = "资讯评论者名称")
    private String  realName;

    @ApiModelProperty(value = "用户身份1:普通用户2：会员用户")
    private String userIdentity;

    @ApiModelProperty(value = "用户头像")
    private String photo;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    private List<NewsReplyVO> crList;

    public List<NewsReplyVO> getCrList() {
        return crList;
    }

    public void setCrList(List<NewsReplyVO> crList) {
        this.crList = crList;
    }

    public String getNewsCommentId() {
        return newsCommentId;
    }

    public void setNewsCommentId(String newsCommentId) {
        this.newsCommentId = newsCommentId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
