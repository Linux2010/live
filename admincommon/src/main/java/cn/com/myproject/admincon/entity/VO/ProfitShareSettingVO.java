package cn.com.myproject.admincon.entity.VO;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfitShareSettingVO {

    private Integer id;

    private String setId;

    private BigDecimal primaryRule;

    private BigDecimal secondaryRule;

    private Integer type;

    private Short isOpen;

    private Long createTime;

    private Short status;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId == null ? null : setId.trim();
    }

    public BigDecimal getPrimaryRule() {
        return primaryRule;
    }

    public void setPrimaryRule(BigDecimal primaryRule) {
        this.primaryRule = primaryRule;
    }

    public BigDecimal getSecondaryRule() {
        return secondaryRule;
    }

    public void setSecondaryRule(BigDecimal secondaryRule) {
        this.secondaryRule = secondaryRule;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Short getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Short isOpen) {
        this.isOpen = isOpen;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取格式化后的时间
     * */
    public String getFCreateTime() {
        if(null != this.createTime){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createTime));
        }
        return "";
    }

    public ProfitShareSettingVO(){
        super();
    }

    public ProfitShareSettingVO(ProfitShareSetting profitShareSetting){
        this.setId(profitShareSetting.getId());
        this.setSetId(profitShareSetting.getSetId());
        this.setType(profitShareSetting.getType());
        this.setPrimaryRule(profitShareSetting.getPrimaryRule());
        this.setSecondaryRule(profitShareSetting.getSecondaryRule());
        this.setIsOpen(profitShareSetting.getIsOpen());
        this.setVersion(profitShareSetting.getVersion());
        this.setStatus(profitShareSetting.getStatus());
        this.setCreateTime(profitShareSetting.getCreateTime());
    }

}