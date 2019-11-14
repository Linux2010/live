package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther LeiJia
 * @create 2017.9.13
 */
@ApiModel(value = "GoodsCat", description = "商品类目表对象")
public class GoodsCat extends BasePO{

    @ApiModelProperty(value = "商品类目表业务ID")
    private String catId;

    @ApiModelProperty(value = "商品类目名称")
    private String catName;

    @ApiModelProperty(value = "父级类目业务ID")
    private String parentId;

    @ApiModelProperty(value = "商品类目图片路径")
    private String imageUrl;

    @ApiModelProperty(value = "商品类目级别")
    private int level;

    @ApiModelProperty(value = "序号")
    private String seqno;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
}
