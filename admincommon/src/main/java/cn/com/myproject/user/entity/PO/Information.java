package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 李延超 on 2017/8/17.
 */
@ApiModel(value = "information", description = "资讯对象")
public class Information extends BasePO{
    @ApiModelProperty(value = "资讯id")
    private String informationId;
    @ApiModelProperty(value = "资讯标题")
    private String title;
    @ApiModelProperty(value = "资讯富文本内容")
    private String content;
    @ApiModelProperty(value = "资讯点赞数")
    private int agreeNum;
    @ApiModelProperty(value = "资讯点到赞数")
    private int disagreeNum;
    @ApiModelProperty(value = "资讯发布人业务ID")
    private String userId;
    @ApiModelProperty(value = "资讯发布人姓名")
    private String userName;
    @ApiModelProperty(value = "资讯发布时间")
    private Long informationCreateTime;

    private String newsId;

    @ApiModelProperty(value = "资讯内容H5页面url")
    private String informationIntroUrl;

    @ApiModelProperty(value = "资讯收藏人数")
    private int informationCollectNum;

    @ApiModelProperty(value = "是否收藏：0没有藏，1收藏了")
    private int isCollect;

    @ApiModelProperty(value = "內容簡介")
    private String introduce;

    @ApiModelProperty(value="资讯评论总数")
    private int commentNum;

    @ApiModelProperty(value = "封面图片")
    private String photo;
    @ApiModelProperty(value = "是否操作过 1、未操作 2、已操作(API使用不记录数据库)")
    private int isOperation;
    @ApiModelProperty(value = "点赞类型：1、点赞 2、倒赞(API使用不记录数据库)")
    private int operationType;
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public int getInformationCollectNum() {
        return informationCollectNum;
    }

    public void setInformationCollectNum(int informationCollectNum) {
        this.informationCollectNum = informationCollectNum;
    }

    public Long getInformationCreateTime() {
        return informationCreateTime;
    }

    public void setInformationCreateTime(Long informationCreateTime) {
        this.informationCreateTime = informationCreateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInformationIntroUrl() {
        return informationIntroUrl;
    }

    public void setInformationIntroUrl(String informationIntroUrl) {
        this.informationIntroUrl = informationIntroUrl;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(int agreeNum) {
        this.agreeNum = agreeNum;
    }

    public int getDisagreeNum() {
        return disagreeNum;
    }

    public void setDisagreeNum(int disgreeNum) {
        this.disagreeNum = disgreeNum;
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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIsOperation() {
        return isOperation;
    }

    public void setIsOperation(int isOperation) {
        this.isOperation = isOperation;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
}
