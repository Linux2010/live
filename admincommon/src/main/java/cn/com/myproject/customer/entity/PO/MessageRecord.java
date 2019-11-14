package cn.com.myproject.customer.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MessageRecord", description = "消息记录")
public class MessageRecord extends BasePO{

    @ApiModelProperty(value = "消息Id")
    private String messageId;
    @ApiModelProperty(value = "发送方")
    private String sendUserId;
    @ApiModelProperty(value = "接收方")
    private String receiveUserId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "消息内容")
    private String content;
    @ApiModelProperty(value = "消息内容类型(1、文字 2、图片  3、语音  4、视频 )")
    private Short messageType;
    @ApiModelProperty(value = "关联会员（发送方与接收方 各关联一条数据）")
    private String relationId;
    @ApiModelProperty(value = "消息类型（1、用户反馈 2、短信 3、站内信 4、私信） / (1、购买课程 2、购买vip  3、下级购买vip通知 )")
    private Short relationType;

    @ApiModelProperty(value = "消息状态（0、未读 1、已读  2、已撤回 3、已删除）")
    private Short messageStatus;

    @ApiModelProperty(value = "类型 1、站内信 2、系统通知")
    private Short classify;
    @ApiModelProperty(value = "简介")
    private String intro;
    @ApiModelProperty(value = "图片地址")
    private String url;

    private String userId;
    private String userName;
    private String nickName;
    private String loginName;
    private String phone;
    private String photo;

    private String operType;
    private String operId;

    private String aliPushExtParameters;


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

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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

    public Short getClassify() {
        return classify;
    }

    public void setClassify(Short classify) {
        this.classify = classify;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAliPushExtParameters() {
        return aliPushExtParameters;
    }

    public void setAliPushExtParameters(String aliPushExtParameters) {
        this.aliPushExtParameters = aliPushExtParameters;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
}