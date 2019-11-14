package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @auther LeiJia
 * @create 2017.9.15
 */
@ApiModel(value = "GoodsImg", description = "商品图片表对象")
public class GoodsImg extends BasePO{

    @ApiModelProperty(value = "商品图片表业务ID")
    private String goodsImgId;

    @ApiModelProperty(value = "商品表业务ID")
    private String goodsId;

    @ApiModelProperty(value = "图片URL")
    private String url;

    @ApiModelProperty(value = "1、主图 0、非主图")
    private int first;

    @ApiModelProperty(value = "序号")
    private int  seqno;

    public  GoodsImg(){

    }
    public GoodsImg( String url,int first,int  seqno){
        this.url = url;
        this.first = first;
        this.seqno = seqno;
    }

    public GoodsImg( String url,int first,int  seqno,String goodsImgId){
        this.url = url;
        this.first = first;
        this.seqno = seqno;
        this.goodsImgId = goodsImgId;
    }
    public String getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(String goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }
}
