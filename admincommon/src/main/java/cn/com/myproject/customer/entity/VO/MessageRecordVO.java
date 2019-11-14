package cn.com.myproject.customer.entity.VO;

import cn.com.myproject.customer.entity.PO.MessageRecord;

public class MessageRecordVO {

    private Integer id;
    private Long createTime;
    private Short status;
    private Integer version;

    private String messageId;

    private String sendUserId;

    private String receiveUserId;

    private String content;

    private Short messageType;

    private String relationId;

    private Short relationType;

    private Short messageStatus;

    private String title;
    private String intro;
    private Short classify;
    private String url;

    private String operType;
    private String operId;

    private String userName;
    private String nickName;
    private String loginName;
    private String phone;
    private String photo;

    private String userId;

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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getMessageType() {
        return messageType;
    }

    public void setMessageType(Short messageType) {
        this.messageType = messageType;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Short getRelationType() {
        return relationType;
    }

    public void setRelationType(Short relationType) {
        this.relationType = relationType;
    }

    public Short getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Short messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MessageRecordVO(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Short getClassify() {
        return classify;
    }

    public void setClassify(Short classify) {
        this.classify = classify;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public MessageRecordVO(MessageRecord record){

        this.setId(record.getId());
        this.setVersion(record.getVersion());
        this.setStatus(record.getStatus());
        this.setCreateTime(record.getCreateTime());
        this.setMessageId(record.getMessageId());
        this.setSendUserId(record.getSendUserId());
        this.setReceiveUserId(record.getReceiveUserId());
        this.setContent(record.getContent());
        this.setMessageType(record.getMessageType());
        this.setRelationId(record.getRelationId());
        this.setRelationType(record.getRelationType());
        this.setMessageStatus(record.getMessageStatus());

        this.setUserName(record.getUserName());
        this.setLoginName(record.getLoginName());
        this.setNickName(record.getNickName());
        this.setPhone(record.getPhone());
        this.setPhoto(record.getPhoto());
        this.setUserId(record.getUserId());
        this.setTitle(record.getTitle());
        this.setClassify(record.getClassify());
        this.setIntro(record.getIntro());
        this.setUrl(record.getUrl());
        this.setOperId(record.getOperId());
        this.setOperType(record.getOperType());
    }
}