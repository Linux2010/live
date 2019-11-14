package cn.com.myproject.goods.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther LeiJia
 * @create 2017.9.14
 */
@ApiModel(value = "Spec", description = "规格主表对象")
public class Spec extends BasePO{

    @ApiModelProperty(value = "规格主表业务ID")
    private String specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "序号")
    private String seqno;

    @ApiModelProperty(value = "规格值列表")
    private List<SpecValues> specValuesList = new ArrayList<SpecValues>();

    public List<SpecValues> getSpecValuesList() {
        return specValuesList;
    }

    public void setSpecValuesList(List<SpecValues> specValuesList) {
        this.specValuesList = specValuesList;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
}
