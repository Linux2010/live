package cn.com.myproject.admincon.entity.PO;

import cn.com.myproject.util.BasePO;

import java.math.BigDecimal;

public class ProfitShareSetting extends BasePO{

    private String setId;

    private BigDecimal primaryRule;

    private BigDecimal secondaryRule;

    private Integer type;

    private Short isOpen;

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

    public Short getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Short isOpen) {
        this.isOpen = isOpen;
    }

}