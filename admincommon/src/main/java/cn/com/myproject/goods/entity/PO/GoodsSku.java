package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.15
 */
@ApiModel(value = "GoodsSku", description = "商品sku表对象")
public class GoodsSku extends BasePO{

    @ApiModelProperty(value = "商品sku表业务ID")
    private String goodsSkuId;

    @ApiModelProperty(value = "规格主表业务ID")
    private String specId;

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    public GoodsSku(){}

    public GoodsSku(String specId,String goodsId){
        this.specId = specId;
        this.goodsId = goodsId;
    }

    public GoodsSku(String goodsSkuId,String specId,String goodsId){
          this.goodsSkuId = goodsSkuId;
          this.specId = specId;
          this.goodsId = goodsId;
    }

    public String getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(String goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
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
}
