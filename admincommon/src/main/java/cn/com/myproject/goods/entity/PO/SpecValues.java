package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther LeiJia
 * @create 2017.9.14
 */
@ApiModel(value = "SpecValues", description = "商品规格值对象")
public class SpecValues extends BasePO{

    @ApiModelProperty(value = "商品规格值表业务ID")
    private String goodsSpecValuesId;

    @ApiModelProperty(value = "规格主表业务ID")
    private String specId;

    @ApiModelProperty(value = "商品规格值名称")
    private String valuesName;

    @ApiModelProperty(value = "类型1：文字;2：图片")
    private String type;

    @ApiModelProperty(value = "规格图片路径，当type=2时，设置该值")
    private String imageUrl;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "序号")
    private String seqno;

    public String getGoodsSpecValuesId() {
        return goodsSpecValuesId;
    }

    public void setGoodsSpecValuesId(String goodsSpecValuesId) {
        this.goodsSpecValuesId = goodsSpecValuesId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getValuesName() {
        return valuesName;
    }

    public void setValuesName(String valuesName) {
        this.valuesName = valuesName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
}
