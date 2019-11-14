package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 李延超 on 2017/9/19.
 */
@ApiModel(value = "GoodsCollect",description="商品收藏")
public class GoodsCollect extends BasePO {

    @ApiModelProperty(value = "商品Id")
    private String goodsId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "是否收藏，0不收藏，1收藏")
    private int iscollect;

    @ApiModelProperty(value="商品名称")
    private String goodsName;

    @ApiModelProperty(value = "收藏业务Id")
    private String collectId;

    @ApiModelProperty(value = "收藏列表")
    private  String goodsIdList;

    public String getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(String goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIscollect() {
        return iscollect;
    }

    public void setIscollect(int iscollect) {
        this.iscollect = iscollect;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }
}
