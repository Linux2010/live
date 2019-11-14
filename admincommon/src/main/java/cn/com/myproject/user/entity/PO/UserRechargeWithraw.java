package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

import java.math.BigDecimal;

public class UserRechargeWithraw extends BasePO{

    private String rwId;

    private String userId;

    private short accountType;

    private short operateType;

    private BigDecimal money;

    private BigDecimal balance;

    private String description;

    private short way;

    private String accountNumber;

    private String name;

    private String phone;

    private String relationId;

    private String relationObj;

    private short opStatus;

    //第三方交易订单号
    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRwId() {
        return rwId;
    }

    public void setRwId(String rwId) {
        this.rwId = rwId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public short getAccountType() {
        return accountType;
    }

    public void setAccountType(short accountType) {
        this.accountType = accountType;
    }

    public short getOperateType() {
        return operateType;
    }

    public void setOperateType(short operateType) {
        this.operateType = operateType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
        this.description = description;
    }

    public short getWay() {
        return way;
    }

    public void setWay(short way) {
        this.way = way;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getRelationObj() {
        return relationObj;
    }

    public void setRelationObj(String relationObj) {
        this.relationObj = relationObj;
    }

    public short getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(short opStatus) {
        this.opStatus = opStatus;
    }
}