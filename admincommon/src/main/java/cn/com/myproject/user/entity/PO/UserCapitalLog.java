package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
@ApiModel(value = "UserCapitalLog", description = "用户日志对象")
public class UserCapitalLog extends BasePO {

    @ApiModelProperty(value = "业务ID")
    private String capitalLogId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "账户类型  1 银两账户  2 积分账户")
    private Short accountType;

    @ApiModelProperty(value = "操作类型   -1 减  1  加")
    private Short operateType;

    @ApiModelProperty(value = "操作账目")
    private BigDecimal account;

    @ApiModelProperty(value = "操作后账户余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "关联Id")
    private String relationId;

    @ApiModelProperty(value = "关联对象")
    private String relationObj;

    @ApiModelProperty(value = "关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏  6、课程分润 7、会员分润 8、商品分润 9、分享课程 10、开设课程）")
    private Short relationType;

    private String userIds;

    @ApiModelProperty(value = "描述信息")
    private String des_message;

    public String getCapitalLogId() {
        return capitalLogId;
    }

    public void setCapitalLogId(String capitalLogId) {
        this.capitalLogId = capitalLogId == null ? null : capitalLogId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Short getAccountType() {
        return accountType;
    }

    public void setAccountType(Short accountType) {
        this.accountType = accountType;
    }

    public Short getOperateType() {
        return operateType;
    }

    public void setOperateType(Short operateType) {
        this.operateType = operateType;
    }

    public Short getRelationType() {
        return relationType;
    }

    public void setRelationType(Short relationType) {
        this.relationType = relationType;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getRelationObj() {
        return relationObj;
    }

    public void setRelationObj(String relationObj) {
        this.relationObj = relationObj == null ? null : relationObj.trim();
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDes_message() {
        return des_message;
    }

    public void setDes_message(String des_message) {
        this.des_message = des_message;
    }
}