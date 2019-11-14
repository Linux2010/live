package cn.com.myproject.user.entity.PO;

import cn.com.myproject.user.entity.VO.NewsReplyVO;
import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/22.
 */
@ApiModel(value="newsComment" ,description ="资讯评论")
public class NewsComment extends BasePO{

    @ApiModelProperty(value = "资讯评论名称")
    private  String newsName;

    @ApiModelProperty(value = "资讯评论者名称")
    private String  newsUsername;

   @ApiModelProperty(value = "资讯评论内容")
    private String newsContent;

    @ApiModelProperty(value="资讯评论ID")
    private String newsCommentId;

    @ApiModelProperty(value="资讯Id")
    private String newsId;

    @ApiModelProperty(value = "资讯评论用户ID")
    private String userId;

    @ApiModelProperty(value="资讯评论时间")
    private Long commentTime;

    @ApiModelProperty(value = "1、普通用户 2、会员用户")
    private int userIdentity;

    @ApiModelProperty(value = "评论者头像")
    private String photo;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value="评论总数")
    private int commentNum;

    @ApiModelProperty(value = "评论内容")
    private String replyContent;

    @ApiModelProperty(value = "评论时间")
    private long replyTime;

    private  List<NewsReplyVO> crList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
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

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsUsername() {
        return newsUsername;
    }

    public void setNewsUsername(String newsUsername) {
        this.newsUsername = newsUsername;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public List<NewsReplyVO> getCrList() {
        return crList;
    }

    public void setCrList(List<NewsReplyVO> crList) {
        this.crList = crList;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }
}
