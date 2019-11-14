package cn.com.myproject.goods.entity.VO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.30
 */
@ApiModel(value = "GoodsSpecVO", description = "商品skuVO对象")
public class GoodsSpecVO extends BasePO{

    @ApiModelProperty(value = "规格ID")
    private String specId;

    @ApiModelProperty(value = "商品规格值ID")
    private String goodsSpecValuesId;

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getGoodsSpecValuesId() {
        return goodsSpecValuesId;
    }

    public void setGoodsSpecValuesId(String goodsSpecValuesId) {
        this.goodsSpecValuesId = goodsSpecValuesId;
    }
}
