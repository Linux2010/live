package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@ApiModel(value = "Advertise", description = "广告对象")
public class Advertise extends BasePO {

    @ApiModelProperty(value = "主键id")
    private String adverId;
    @ApiModelProperty(value = "广告名称")
    private String adverName;
    @ApiModelProperty(value = "后台选择添加类型写的名称")
    private String name;
    @ApiModelProperty(value = "图片")
    private String adverImg;
    @ApiModelProperty(value = "展示位置(没用到)")
    private String adverPlace;
    @ApiModelProperty(value = "后台上传图片选择类型：1、首页，2、学习3、商城")
    private int type;
    @ApiModelProperty(value = "链接地址(暂时没用)")
    private String linkAddress;
    @ApiModelProperty(value = "详情id")
    private String detailId;//详情id
    @ApiModelProperty(value = "链接类型:2、教头 3、商品 4、音频 5、视频 6、直播 7、文字 8、江湖大课")
    private int linkType;//链接类型

    public String getAdverId() {
        return adverId;
    }

    public void setAdverId(String adverId) {
        this.adverId = adverId;
    }

    public String getAdverName() {
        return adverName;
    }

    public void setAdverName(String adverName) {
        this.adverName = adverName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdverImg() {
        return adverImg;
    }

    public void setAdverImg(String adverImg) {
        this.adverImg = adverImg;
    }

    public String getAdverPlace() {
        return adverPlace;
    }

    public void setAdverPlace(String adverPlace) {
        this.adverPlace = adverPlace;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }
}
