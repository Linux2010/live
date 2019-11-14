package cn.com.myproject.live.entity.VO;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡
 */
public class UserGroupCardVO {

    // 主键ID
    private int id;

    //用户ID
    private String userId;

    // 用户名
    private String userName;

    // 用户手机号
    private String phone;

    // 激活卡号
    private String cardNo;

    // 激活卡密码
    private String cardPassword;

    // 激活卡类型：1代表年卡，2代表月卡
    private int cardType;

    // 激活者用户名
    private String activeUserName;

    // 激活者手机号
    private String activePhone;

    // 激活时间
    private long activeTime;

    // 创建时间
    private long createTime;

    // 是否激活：0代表未激活，1代表已激活
    private int status;

    //激活团购卡用户ID
    private String activeUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(String activeUserId) {
        this.activeUserId = activeUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getActiveUserName() {
        return activeUserName;
    }

    public void setActiveUserName(String activeUserName) {
        this.activeUserName = activeUserName;
    }

    public String getActivePhone() {
        return activePhone;
    }

    public void setActivePhone(String activePhone) {
        this.activePhone = activePhone;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}