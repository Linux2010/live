package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther LeiJia
 * @create 2017.9.15
 */
@ApiModel(value = "StockLog", description = "库存表对象")
public class StockLog extends BasePO{
    @ApiModelProperty(value = "库存日志表业务ID")
    private String stockLogId;

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    @ApiModelProperty(value = "库存表业务ID")
    private String stockId;

    @ApiModelProperty(value = "操作前库存总量")
    private int  startNum;

    @ApiModelProperty(value = "操作数量")
    private int  handleNum;

    @ApiModelProperty(value = "库存剩余总量")
    private int  endNum;

    @ApiModelProperty(value = "0:初始化;1:预占；2释放预占；3：购买；4：退货；5：管理员手动")
    private int  type;

    public StockLog(){}

    public StockLog( String goodsId,String stockId){
        this.goodsId = goodsId;
        this.stockId = stockId;
    }
    public StockLog( String stockLogId,String goodsId,String stockId, int  startNum, int  handleNum, int  endNum,int  type){
        this.stockLogId = stockLogId;
        this.goodsId = goodsId;
        this.stockId = stockId;
        this.startNum = startNum;
        this.handleNum = handleNum;
        this.endNum = endNum;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStockLogId() {
        return stockLogId;
    }

    public void setStockLogId(String stockLogId) {
        this.stockLogId = stockLogId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getHandleNum() {
        return handleNum;
    }

    public void setHandleNum(int handleNum) {
        this.handleNum = handleNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
