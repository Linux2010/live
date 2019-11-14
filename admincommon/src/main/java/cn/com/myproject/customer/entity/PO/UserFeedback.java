package cn.com.myproject.customer.entity.PO;


import cn.com.myproject.util.BasePO;

public class UserFeedback extends BasePO{

    private String feedbackId;

    private String userId;

    private String content;

    private String userName;

    private String phone;

    private String name;

    private short dealStatus;

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(short dealStatus) {
        this.dealStatus = dealStatus;
    }
}