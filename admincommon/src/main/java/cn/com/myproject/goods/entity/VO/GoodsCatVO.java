package cn.com.myproject.goods.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**商品分类表
 * Created by JYP on 2017/9/22 0022.
 */
@ApiModel(value = "GoodsCatVO", description = "商品根据分类查对象")
public class GoodsCatVO {

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品类别标识(0:普通商品;1:银两商品)")
    private int  goodsType;

    @ApiModelProperty(value = "商品编号（独一无二）")
    private String goodsNumber;

    @ApiModelProperty(value = "商品成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "市场价格")
    private BigDecimal mktPrice;

    @ApiModelProperty(value = "会员价")
    private BigDecimal price;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "商品类目表业务ID(商品二级类目表业务ID)")
    private String catId;

    @ApiModelProperty(value = "商品类目名称")
    private String catName;

    @ApiModelProperty(value = "商品销量")
    private Integer goodsNum;

    @ApiModelProperty(value = "商品主图")
    private String mastImg;

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

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(BigDecimal mktPrice) {
        this.mktPrice = mktPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

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

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getMastImg() {
        return mastImg;
    }

    public void setMastImg(String mastImg) {
        this.mastImg = mastImg;
    }
}
