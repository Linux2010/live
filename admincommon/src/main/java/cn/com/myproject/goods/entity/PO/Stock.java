package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.15
 */
@ApiModel(value = "Stock", description = "库存表对象")
public class Stock extends BasePO{

    @ApiModelProperty(value = "库存表业务ID")
    private String stockId;

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    @ApiModelProperty(value = "规格值ID(商品规格值表业务ID)")
    private String goodsSpecValueId;

    @ApiModelProperty(value = "规格值ID(商品规格值表业务ID)1")
    private String goodsSpecValueId1;

    @ApiModelProperty(value = "规格值ID(商品规格值表业务ID)2")
    private String goodsSpecValueId2;

    @ApiModelProperty(value = "规格值ID(商品规格值表业务ID)3")
    private String goodsSpecValueId3;

    @ApiModelProperty(value = "规格值ID(商品规格值表业务ID)4")
    private String goodsSpecValueId4;

    @ApiModelProperty(value = "总库存")
    private int  num;

    @ApiModelProperty(value = "已销售数量")
    private int  useNum;

    @ApiModelProperty(value = "剩余库存数量")
    private int  ownNum;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

    public  Stock(){

    }
    public  Stock(String stockId, String goodsId, String goodsSpecValueId,String goodsSpecValueId1,String goodsSpecValueId2,String goodsSpecValueId3,String goodsSpecValueId4,int  num,int  useNum,int  ownNum,Long updateTime){
        this.stockId = stockId;
        this.goodsId = goodsId;
        this.goodsSpecValueId = goodsSpecValueId;
        this.goodsSpecValueId1 = goodsSpecValueId1;
        this.goodsSpecValueId2 = goodsSpecValueId2;
        this.goodsSpecValueId3 = goodsSpecValueId3;
        this.goodsSpecValueId4 = goodsSpecValueId4;
        this.num = num;
        this.useNum = useNum;
        this.ownNum = ownNum;
        this.updateTime = updateTime;

    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpecValueId() {
        return goodsSpecValueId;
    }

    public void setGoodsSpecValueId(String goodsSpecValueId) {
        this.goodsSpecValueId = goodsSpecValueId;
    }

    public String getGoodsSpecValueId1() {
        return goodsSpecValueId1;
    }

    public void setGoodsSpecValueId1(String goodsSpecValueId1) {
        this.goodsSpecValueId1 = goodsSpecValueId1;
    }

    public String getGoodsSpecValueId2() {
        return goodsSpecValueId2;
    }

    public void setGoodsSpecValueId2(String goodsSpecValueId2) {
        this.goodsSpecValueId2 = goodsSpecValueId2;
    }

    public String getGoodsSpecValueId3() {
        return goodsSpecValueId3;
    }

    public void setGoodsSpecValueId3(String goodsSpecValueId3) {
        this.goodsSpecValueId3 = goodsSpecValueId3;
    }

    public String getGoodsSpecValueId4() {
        return goodsSpecValueId4;
    }

    public void setGoodsSpecValueId4(String goodsSpecValueId4) {
        this.goodsSpecValueId4 = goodsSpecValueId4;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public int getOwnNum() {
        return ownNum;
    }

    public void setOwnNum(int ownNum) {
        this.ownNum = ownNum;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
