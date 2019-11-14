package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

public class UserSignIn extends BasePO{

    private String signId;

    private String userId;

    private Integer continueDays;

    private Long lastSignTime;

    private String signTimeRecord;

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getContinueDays() {
        return continueDays;
    }

    public void setContinueDays(Integer continueDays) {
        this.continueDays = continueDays;
    }

    public Long getLastSignTime() {
        return lastSignTime;
    }

    public void setLastSignTime(Long lastSignTime) {
        this.lastSignTime = lastSignTime;
    }

    public String getSignTimeRecord() {
        return signTimeRecord;
    }

    public void setSignTimeRecord(String signTimeRecord) {
        this.signTimeRecord = signTimeRecord;
    }

}