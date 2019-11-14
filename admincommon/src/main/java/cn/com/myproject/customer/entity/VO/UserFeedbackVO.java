package cn.com.myproject.customer.entity.VO;

import cn.com.myproject.customer.entity.PO.UserFeedback;

public class UserFeedbackVO {

    private Integer id;
    private Long createTime;
    private Short status;
    private Integer version;

    private String feedbackId;

    private String userId;

    private String content;

    private String userName;

    private String phone;

    private String name;

    private short dealStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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

    public UserFeedbackVO(){
        super();
    }

    public UserFeedbackVO(UserFeedback userFeedback){
        this.setId(userFeedback.getId());
        this.setFeedbackId(userFeedback.getFeedbackId());
        this.setStatus(userFeedback.getStatus());
        this.setVersion(userFeedback.getVersion());
        this.setCreateTime(userFeedback.getCreateTime());
        this.setUserId(userFeedback.getUserId());
        this.setUserName(userFeedback.getUserName());
        this.setContent(userFeedback.getContent());
        this.setDealStatus(userFeedback.getDealStatus());
        this.setPhone(userFeedback.getPhone());
        this.setName(userFeedback.getName());
    }
}