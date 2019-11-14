package cn.com.myproject.live.entity.PO.live.entity;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by LeiJia on 2017/9/4 0004.
 */
@ApiModel(value = "GiftCollectionStatistics", description = "礼物赠收统计象")
public class GiftCollectionStatistics extends BasePO {

    @ApiModelProperty(value = "礼物赠收统计表业务ID")
    private String giftCollectionStatisticsId;
    @ApiModelProperty(value = "用户业务ID")
    private String userId;
    @ApiModelProperty(value = "赠收类别：0:会员赠送;1:教头接受礼物")
    private int type;
    @ApiModelProperty(value = "礼物总数量")
    private int giftAllNumber;
    @ApiModelProperty(value = "礼物总价值(单位:银两)")
    private BigDecimal giftAllWorth;
    private Long updateTime;
    @ApiModelProperty(value = "用户真实名称/登录名称")
    private String userName;
    @ApiModelProperty(value = "用户头像")
    private String photo;
    @ApiModelProperty(value = "网易聊天室ID")
    private String imRoomId;

    public GiftCollectionStatistics(){

    }
    public GiftCollectionStatistics(String userId,int type) {
        this.userId = userId;
        this.type = type;
    }
    public GiftCollectionStatistics(String userId,int type,String imRoomId) {
        this.userId = userId;
        this.type = type;
        this.imRoomId = imRoomId;
    }
    public String getImRoomId() {
        return imRoomId;
    }

    public void setImRoomId(String imRoomId) {
        this.imRoomId = imRoomId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getGiftCollectionStatisticsId() {
        return giftCollectionStatisticsId;
    }

    public void setGiftCollectionStatisticsId(String giftCollectionStatisticsId) {
        this.giftCollectionStatisticsId = giftCollectionStatisticsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGiftAllNumber() {
        return giftAllNumber;
    }

    public void setGiftAllNumber(int giftAllNumber) {
        this.giftAllNumber = giftAllNumber;
    }

    public BigDecimal getGiftAllWorth() {
        return giftAllWorth;
    }

    public void setGiftAllWorth(BigDecimal giftAllWorth) {
        this.giftAllWorth = giftAllWorth;
    }
}
