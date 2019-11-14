package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/9/20.
 */
@ApiModel(value = "GoodsCommentReply",description="商品评论回复表")
public class GoodsCommentReply extends BasePO {
    @ApiModelProperty(value = "商品评论回复表业务ID")

    private String goodsCommentReplyId;
    @ApiModelProperty(value = "商品评论Id")
    private String goodsCommentId;

    @ApiModelProperty(value = "商品评论回复内容")
    private String replyContent;

    @ApiModelProperty(value = "回复时间")
    private  Long replyTime;

    @ApiModelProperty(value = "回复人ID(后台管理员)")
    private String userId;

    public String getGoodsCommentReplyId() {
        return goodsCommentReplyId;
    }

    public void setGoodsCommentReplyId(String goodsCommentReplyId) {
        this.goodsCommentReplyId = goodsCommentReplyId;
    }

    public String getGoodsCommentId() {
        return goodsCommentId;
    }

    public void setGoodsCommentId(String goodsCommentId) {
        this.goodsCommentId = goodsCommentId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
