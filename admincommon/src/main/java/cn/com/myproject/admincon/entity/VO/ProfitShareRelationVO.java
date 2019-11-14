package cn.com.myproject.admincon.entity.VO;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;

/**
 * @auther CQC
 * @create 2017.8.16
 */
public class ProfitShareRelationVO {

    private String relationId;

    private String userId;

    private String parentUserId;

    private Integer no;

    private Integer id;

    private Long createTime;

    private Short status;

    private Integer version;


    private String userName;
    private String loginName;
    private String nickName;
    private String phone;
    private Integer userLevel;
    private Integer userIdentity;

    private String signature;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public ProfitShareRelationVO(){
        super();
    }

    public ProfitShareRelationVO(ProfitShareRelation profitShareRelation){
        this.setCreateTime(profitShareRelation.getCreateTime());
        this.setId(profitShareRelation.getId());
        this.setNo(profitShareRelation.getNo());
        this.setParentUserId(profitShareRelation.getParentUserId());
        this.setUserId(profitShareRelation.getUserId());
        this.setRelationId(profitShareRelation.getRelationId());
        this.setStatus(profitShareRelation.getStatus());
        this.setVersion(profitShareRelation.getVersion());
        this.setUserName(profitShareRelation.getUserName());
        this.setLoginName(profitShareRelation.getLoginName());
        this.setNickName(profitShareRelation.getNickName());
        this.setPhone(profitShareRelation.getPhone());
        this.setUserLevel(profitShareRelation.getUserLevel());
        this.setUserIdentity(profitShareRelation.getUserIdentity());
        this.setSignature(profitShareRelation.getSignature());
    }
}
