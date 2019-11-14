package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

import java.math.BigDecimal;

public class UserCapital extends BasePO{

    private String capitalId;

    private String userId;

    private BigDecimal tael;

    private BigDecimal integral;

    private BigDecimal freezetael;

    private BigDecimal freezeintegral;

    private String userIds;

    public UserCapital(){}
    public UserCapital(BigDecimal tael,String userId){
        this.tael = tael;
        this.userId = userId;
    }
    public UserCapital(BigDecimal tael,String userId,String capitalId){
        this.tael = tael;
        this.userId = userId;
        this.capitalId = capitalId;
    }

    public String getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(String capitalId) {
        this.capitalId = capitalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTael() {
        return tael;
    }

    public void setTael(BigDecimal tael) {
        this.tael = tael;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getFreezetael() {
        return freezetael;
    }

    public void setFreezetael(BigDecimal freezetael) {
        this.freezetael = freezetael;
    }

    public BigDecimal getFreezeintegral() {
        return freezeintegral;
    }

    public void setFreezeintegral(BigDecimal freezeintegral) {
        this.freezeintegral = freezeintegral;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}