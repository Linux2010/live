package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by LSG on 2017/9/14 0014.
 */
@ApiModel(value = "article", description = "文章海报对象")
public class ReturnGoods extends BasePO{

    @ApiModelProperty(value = "退货申请id")
    private String returnGoodsId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    @ApiModelProperty(value = "退货金额id")
    private BigDecimal money;
    @ApiModelProperty(value = "退货后台操作：1、同意 2、拒绝 3、退款中")
    private int operationType;//操作类型： 1、同意 2、拒绝 3、未处理
    @ApiModelProperty(value = "拒绝原因")
    private String refuseReason;//拒绝原因
    @ApiModelProperty(value = "处理时间")
    private long handleTime;//处理时间
    @ApiModelProperty(value = "退货人姓名")
    private String userRealName;//退货人姓名
    @ApiModelProperty(value = "商品id")
    private String goodsName;//商品名称
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;//物流公司
    @ApiModelProperty(value = "物流单号")
    private String logisticsNumber;//物流单号

    public String getReturnGoodsId() {
        return returnGoodsId;
    }

    public void setReturnGoodsId(String returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(long handleTime) {
        this.handleTime = handleTime;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }
}
