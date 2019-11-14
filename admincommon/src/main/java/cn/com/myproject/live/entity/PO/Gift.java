package cn.com.myproject.live.entity.PO;


import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@ApiModel(value = "Gift", description = "礼物对像")
public class Gift extends BasePO {
    /**
     *礼物ID
     * */
    @ApiModelProperty(value = "礼物ID",example = "0")
    private String giftId;
    /**
     *礼物名称
     * */
    @ApiModelProperty(value = "礼物名称")
    private String giftName;
    /**
     *价值
     * */
    @ApiModelProperty(value = "礼物价值")
    private BigDecimal virtualMoney;
    /**
     *排序 倒序
     * */
    @ApiModelProperty(value = "礼物排序")
    private Integer seqno;
    /**
     *图片
     * */
    @ApiModelProperty(value = "礼物图片")
    private String imgUrl;
    /**
     *大图片
     * */
    @ApiModelProperty(value = "礼物大图片")
    private String bigImgUrl;
    /**
     *发送数量设置
     * */
    @ApiModelProperty(value = "发送数量设置")
    private String enclises;


    public Gift(){

    }
    public Gift(String giftId){
        this.giftId = giftId;
    }
    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public BigDecimal getVirtualMoney() {
        return virtualMoney;
    }

    public void setVirtualMoney(BigDecimal virtualMoney) {
        this.virtualMoney = virtualMoney;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getEnclises() {
        return enclises;
    }

    public void setEnclises(String enclises) {
        this.enclises = enclises;
    }
}
