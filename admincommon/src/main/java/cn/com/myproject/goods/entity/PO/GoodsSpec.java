package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.14
 */
@ApiModel(value = "GoodsSpec", description = "商品sku对象")
public class GoodsSpec extends BasePO{

    @ApiModelProperty(value = "商品skuID")
    private String goodsSpecId;

    @ApiModelProperty(value = "规格ID")
    private String specId;

    @ApiModelProperty(value = "规格ID1")
    private String specId1;

    @ApiModelProperty(value = "规格ID2")
    private String specId2;

    @ApiModelProperty(value = "规格ID3")
    private String specId3;

    @ApiModelProperty(value = "规格ID4")
    private String specId4;

    @ApiModelProperty(value = "商品ID")
    private String goodsId;

    @ApiModelProperty(value = "商品规格值ID")
    private String goodsSpecValuesId;

    @ApiModelProperty(value = "商品规格值ID1")
    private String goodsSpecValuesId1;

    @ApiModelProperty(value = "商品规格值ID2")
    private String goodsSpecValuesId2;

    @ApiModelProperty(value = "商品规格值ID3")
    private String goodsSpecValuesId3;

    @ApiModelProperty(value = "商品规格值ID4")
    private String goodsSpecValuesId4;

    @ApiModelProperty(value = "商品成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "市场价格")
    private BigDecimal userPrice;

    @ApiModelProperty(value = "会员价")
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "重量(克)")
    private Double weight;

    @ApiModelProperty(value = "总库存")
    private Integer stockNum;

    private String specName;
    private String specName1;
    private String specName2;
    private String specName3;
    private String specName4;

    private String valuesName;
    private String valuesName1;
    private String valuesName2;
    private String valuesName3;
    private String valuesName4;

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecName1() {
        return specName1;
    }

    public void setSpecName1(String specName1) {
        this.specName1 = specName1;
    }

    public String getSpecName2() {
        return specName2;
    }

    public void setSpecName2(String specName2) {
        this.specName2 = specName2;
    }

    public String getSpecName3() {
        return specName3;
    }

    public void setSpecName3(String specName3) {
        this.specName3 = specName3;
    }

    public String getSpecName4() {
        return specName4;
    }

    public void setSpecName4(String specName4) {
        this.specName4 = specName4;
    }

    public String getValuesName() {
        return valuesName;
    }

    public void setValuesName(String valuesName) {
        this.valuesName = valuesName;
    }

    public String getValuesName1() {
        return valuesName1;
    }

    public void setValuesName1(String valuesName1) {
        this.valuesName1 = valuesName1;
    }

    public String getValuesName2() {
        return valuesName2;
    }

    public void setValuesName2(String valuesName2) {
        this.valuesName2 = valuesName2;
    }

    public String getValuesName3() {
        return valuesName3;
    }

    public void setValuesName3(String valuesName3) {
        this.valuesName3 = valuesName3;
    }

    public String getValuesName4() {
        return valuesName4;
    }

    public void setValuesName4(String valuesName4) {
        this.valuesName4 = valuesName4;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(BigDecimal userPrice) {
        this.userPrice = userPrice;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getGoodsSpecValuesId1() {
        return goodsSpecValuesId1;
    }

    public void setGoodsSpecValuesId1(String goodsSpecValuesId1) {
        this.goodsSpecValuesId1 = goodsSpecValuesId1;
    }

    public String getGoodsSpecValuesId2() {
        return goodsSpecValuesId2;
    }

    public void setGoodsSpecValuesId2(String goodsSpecValuesId2) {
        this.goodsSpecValuesId2 = goodsSpecValuesId2;
    }

    public String getGoodsSpecValuesId3() {
        return goodsSpecValuesId3;
    }

    public void setGoodsSpecValuesId3(String goodsSpecValuesId3) {
        this.goodsSpecValuesId3 = goodsSpecValuesId3;
    }

    public String getGoodsSpecValuesId4() {
        return goodsSpecValuesId4;
    }

    public void setGoodsSpecValuesId4(String goodsSpecValuesId4) {
        this.goodsSpecValuesId4 = goodsSpecValuesId4;
    }

    public String getGoodsSpecId() {
        return goodsSpecId;
    }

    public void setGoodsSpecId(String goodsSpecId) {
        this.goodsSpecId = goodsSpecId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpecValuesId() {
        return goodsSpecValuesId;
    }

    public void setGoodsSpecValuesId(String goodsSpecValuesId) {
        this.goodsSpecValuesId = goodsSpecValuesId;
    }

    public String getSpecId1() {
        return specId1;
    }

    public void setSpecId1(String specId1) {
        this.specId1 = specId1;
    }

    public String getSpecId2() {
        return specId2;
    }

    public void setSpecId2(String specId2) {
        this.specId2 = specId2;
    }

    public String getSpecId3() {
        return specId3;
    }

    public void setSpecId3(String specId3) {
        this.specId3 = specId3;
    }

    public String getSpecId4() {
        return specId4;
    }

    public void setSpecId4(String specId4) {
        this.specId4 = specId4;
    }
}
