package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * Created by 李延超 on 2017/9/20.
 */
@ApiModel(value = "GoodsComment",description="商品评论")
public class GoodsComment extends BasePO {
    @ApiModelProperty(value = "商品ID")
    private String goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品评论Id")
    private String goodsCommentId;

    @ApiModelProperty(value = "商品评论内容")
    private String goodsComment;

    @ApiModelProperty(value = "评论是否匿名0：匿名；1：实名")
    private  int reviewerType;

    @ApiModelProperty(value = "评论人ID")
    private String reviewerId;

    @ApiModelProperty(value = "评论人名称")
    private String reviewerName;

    @ApiModelProperty(value = "评论图片")
    private String photo;

    @ApiModelProperty(value = "评论图片1")
    private String photo1;

    @ApiModelProperty(value = "评论图片2")
    private String photo2;

    @ApiModelProperty(value = "评论图片3")
    private String photo3;

    @ApiModelProperty(value = "评论图片4")
    private String photo4;

    @ApiModelProperty(value = "商品质量评价星星数量(5:好评，3-5中评,1-3差评)")
    private int goodsStars;

    @ApiModelProperty(value = "物流服务评价星星数量(5:好评，3-5中评,1-3差评)")
   private  int logiStars;

    @ApiModelProperty(value = "服务态度评价星星数量(5:好评，3-5中评,1-3差评)")
    private  int serviceStars;

    @ApiModelProperty(value = "评论总数")
    private  int allComm;

    @ApiModelProperty(value = "图片")
    private Map<String, Object> imgUrl;

    @ApiModelProperty(value = "评论人头像")
    private  String userPhoto;

    @ApiModelProperty(value = "好评数量")
    private int goodCommNum;

    @ApiModelProperty(value = "中评数量")
    private int midCommNum;

    @ApiModelProperty(value = "差评数量")
    private int badCommNum;

    @ApiModelProperty(value = "晒图数量")
    private  int showPhoto;

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCommentId() {
        return goodsCommentId;
    }

    public void setGoodsCommentId(String goodsCommentId) {
        this.goodsCommentId = goodsCommentId;
    }

    public String getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(String goodsComment) {
        this.goodsComment = goodsComment;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getAllComm() {
        return allComm;
    }

    public void setAllComm(int allComm) {
        this.allComm = allComm;
    }

    public int getReviewerType() {
        return reviewerType;
    }

    public void setReviewerType(int reviewerType) {
        this.reviewerType = reviewerType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public int getGoodsStars() {
        return goodsStars;
    }

    public void setGoodsStars(int goodsStars) {
        this.goodsStars = goodsStars;
    }

    public int getLogiStars() {
        return logiStars;
    }

    public void setLogiStars(int logiStars) {
        this.logiStars = logiStars;
    }

    public int getServiceStars() {
        return serviceStars;
    }

    public void setServiceStars(int serviceStars) {
        this.serviceStars = serviceStars;
    }

    public Map<String, Object> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Map<String, Object> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getGoodCommNum() {
        return goodCommNum;
    }

    public void setGoodCommNum(int goodCommNum) {
        this.goodCommNum = goodCommNum;
    }

    public int getMidCommNum() {
        return midCommNum;
    }

    public void setMidCommNum(int midCommNum) {
        this.midCommNum = midCommNum;
    }

    public int getBadCommNum() {
        return badCommNum;
    }

    public void setBadCommNum(int badCommNum) {
        this.badCommNum = badCommNum;
    }

    public int getShowPhoto() {
        return showPhoto;
    }

    public void setShowPhoto(int showPhoto) {
        this.showPhoto = showPhoto;
    }
}
