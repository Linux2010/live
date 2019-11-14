package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther LeiJia
 * @create 2017.9.15
 */
@ApiModel(value = "Goods", description = "商品主表对象")
public class Goods extends BasePO{

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

    @ApiModelProperty(value = "商品详情")
    private String intro;

    @ApiModelProperty(value = "商品运费")
    private BigDecimal expreeFee;

    @ApiModelProperty(value = "商品类目表业务ID(商品二级类目表业务ID)")
    private String catId;

    @ApiModelProperty(value = "商品类目名称")
    private String catName;

    @ApiModelProperty(value = "是否下架0：下架;1：上架;")
    private int  isSoldOut;

    @ApiModelProperty(value = "商品发布人")
    private String creater;

    @ApiModelProperty(value = "序号")
    private int  seqno;

    @ApiModelProperty(value = "商品评论数")
    private int commNum;

    private String ext;

    private String ext1;

    private String ext2;

    private String mastImg;

    private String specNum;

    @ApiModelProperty(value = "商品图片列表")
    private List<GoodsImg> imgs = new ArrayList<GoodsImg>();

    @ApiModelProperty(value = "商品规格列表")
    private List<Spec>  specs = new ArrayList<Spec>();

    @ApiModelProperty(value = "商品规格sku列表,app商品详情页使用，加入购物车时带上goodsSepcId")
    private List<GoodsSpec>  goodsSpecs= new ArrayList<GoodsSpec>();

    @ApiModelProperty(value = "商品本月销售量")
    private int monthSaleNum;

    private int couponNum;

    @ApiModelProperty(value = "商品总库存")
    private int goodsStockNum;

    public int getGoodsStockNum() {
        return goodsStockNum;
    }

    public void setGoodsStockNum(int goodsStockNum) {
        this.goodsStockNum = goodsStockNum;
    }

    public int getMonthSaleNum() {
        return monthSaleNum;
    }

    public void setMonthSaleNum(int monthSaleNum) {
        this.monthSaleNum = monthSaleNum;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
    }

    public List<GoodsSpec> getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(List<GoodsSpec> goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    public String getSpecNum() {
        return specNum;
    }

    public void setSpecNum(String specNum) {
        this.specNum = specNum;
    }

    public String getMastImg() {
        return mastImg;
    }

    public void setMastImg(String mastImg) {
        this.mastImg = mastImg;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<GoodsImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<GoodsImg> imgs) {
        this.imgs = imgs;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BigDecimal getExpreeFee() {
        return expreeFee;
    }

    public void setExpreeFee(BigDecimal expreeFee) {
        this.expreeFee = expreeFee;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(int isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }


    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public int getCommNum() {
        return commNum;
    }

    public void setCommNum(int commNum) {
        this.commNum = commNum;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
