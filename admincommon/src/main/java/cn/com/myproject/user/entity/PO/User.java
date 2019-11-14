package cn.com.myproject.user.entity.PO;


import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyang-macbook on 2017/6/27.
 */
@ApiModel(value = "User", description = "用户对象")
public class User extends BasePO {

    @ApiModelProperty(value = "用户业务id")
    private String userId;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "地区区号")
    private String countryCode;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "登录密码")
    private String password;
    @ApiModelProperty(value = "支付密码")
    private String payPassword;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "序列号")
    private String IDNum;
    @ApiModelProperty(value="首页名优教头头像尺寸比例为1.725:1")
    private String rectanglePhoto;

    /**
     * 网易IM账号ID
     * */
    @ApiModelProperty(value = "网易IM账号ID")
    private String accid;
    /**
     * 网易IM账号token
     * */
    @ApiModelProperty(value = "网易IM账号token")
    private String accidToken;
    @ApiModelProperty(value = "银两")
    private Double money;
    @ApiModelProperty(value = "国家名称")
    private String countries;
    @ApiModelProperty(value = "省名称")
    private String province;
    @ApiModelProperty(value = "市名称")
    private String city;
    @ApiModelProperty(value = "地区名称")
    private String area;
    @ApiModelProperty(value = "国家ID")
    private String countriesId;
    @ApiModelProperty(value = "省ID")
    private String provinceId;
    @ApiModelProperty(value = "城市ID")
    private String cityId;
    @ApiModelProperty(value = "地区ID")
    private String areaId;
    @ApiModelProperty(value = "积分")
    private Double score;
    /**
     * 用户类型1：教头 2：用户
     */
    @ApiModelProperty(value = "用户类型{1：教头 2：用户}")
    private Integer userType;
    @ApiModelProperty(value = "用户标签")
    private String labelId;
    private StudyLabel stulabel;
    @ApiModelProperty(value = "用户等级")
    private Integer userLevel;

    private UserLevel userlevel;
    @ApiModelProperty(value = "用户身份")
    private Integer userIdentity;
    @ApiModelProperty(value = "用户头像")
    private String photo;
    //个性签名
    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "推荐人(手机号)")
    private String commendname;

    @ApiModelProperty(value = "令牌")
    private String token;
    @ApiModelProperty(value = "会员到期时间")
    private long expirationDate;
    //是否设置支付密码
    //0: 未设置1: 已设置
    @ApiModelProperty(value = "是否设置支付密码{0: 未设置1: 已设置}")
    private String is_pay;
    //文字课程篇数
    @ApiModelProperty(value = "文字课程篇数")
    private Integer wzkcNum;
    //音频课程篇数
    @ApiModelProperty(value = "音频课程篇数")
    private Integer ypkcNum;
    //视频课程篇数
    @ApiModelProperty(value = "视频课程篇数")
    private Integer spkcNum;

    // 我的课程数量
    @ApiModelProperty(value = "我的课程数量")
    private Integer mkNum;

    // 我的收藏数量
    @ApiModelProperty(value = "我的收藏数量")
    private Integer msNum;

    @ApiModelProperty(value = "我的关注数量")
    private Integer myFocuNumber;

    // 二维码图片路径
    @ApiModelProperty(value = "二维码图片路径")
    private String qrCodeUrl;

    @ApiModelProperty(value = "我的标签集合")
    private String labelName;

    // 邀请好友-海报与二维码页面路径
    @ApiModelProperty(value = "邀请好友-海报与二维码页面路径")
    private String qrCodePage;

    // 邀请好友-海报与二维码图片路径
    @ApiModelProperty(value = "邀请好友-海报与二维码图片路径")
    private String qrCodeImgUrl;

    // qq登录
    @ApiModelProperty(value = "qq登录")
    private String openId;

    @ApiModelProperty(value = "微信登录标识")
    private String wxOpenId;

    private int couponNum;

    private int isClaim; //是否认领:0：未认领；1：认领了

    private String address;
    @ApiModelProperty(value = "个人资料是否完善 0:待完善 1:已完善")
    private int is_complet;

    @ApiModelProperty(value = "微博登录标识")
    private String weiboOpenId;

    @ApiModelProperty(value = "公众号openId标识")
    private String thePublicOpenId;

    public String getQrCodeImgUrl() {
        return qrCodeImgUrl;
    }

    public void setQrCodeImgUrl(String qrCodeImgUrl) {
        this.qrCodeImgUrl = qrCodeImgUrl;
    }

    public int getIsClaim() {
        return isClaim;
    }

    public void setIsClaim(int isClaim) {
        this.isClaim = isClaim;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRectanglePhoto() {
        return rectanglePhoto;
    }

    public void setRectanglePhoto(String rectanglePhoto) {
        this.rectanglePhoto = rectanglePhoto;
    }

    public Integer getMyFocuNumber() {
        return myFocuNumber;
    }

    public void setMyFocuNumber(Integer myFocuNumber) {
        this.myFocuNumber = myFocuNumber;
    }

    public StudyLabel getStulabel() {
        return stulabel;
    }

    public void setStulabel(StudyLabel stulabel) {
        this.stulabel = stulabel;
    }


    public UserLevel getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(UserLevel userlevel) {
        this.userlevel = userlevel;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(String countriesId) {
        this.countriesId = countriesId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }


    public String getAccidToken() {
        return accidToken;
    }

    public void setAccidToken(String accidToken) {
        this.accidToken = accidToken;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCommendname() {
        return commendname;
    }

    public void setCommendname(String commendname) {
        this.commendname = commendname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(String is_pay) {
        this.is_pay = is_pay;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getWzkcNum() {
        return wzkcNum;
    }

    public void setWzkcNum(Integer wzkcNum) {
        this.wzkcNum = wzkcNum;
    }

    public Integer getYpkcNum() {
        return ypkcNum;
    }

    public void setYpkcNum(Integer ypkcNum) {
        this.ypkcNum = ypkcNum;
    }

    public Integer getSpkcNum() {
        return spkcNum;
    }

    public void setSpkcNum(Integer spkcNum) {
        this.spkcNum = spkcNum;
    }

    public Integer getMkNum() {
        return mkNum;
    }

    public void setMkNum(Integer mkNum) {
        this.mkNum = mkNum;
    }

    public Integer getMsNum() {
        return msNum;
    }

    public void setMsNum(Integer msNum) {
        this.msNum = msNum;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getQrCodePage() {
        return qrCodePage;
    }

    public void setQrCodePage(String qrCodePage) {
        this.qrCodePage = qrCodePage;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public int getIs_complet() {
        return is_complet;
    }

    public void setIs_complet(int is_complet) {
        this.is_complet = is_complet;
    }

    public String getWeiboOpenId() {
        return weiboOpenId;
    }

    public void setWeiboOpenId(String weiboOpenId) {
        this.weiboOpenId = weiboOpenId;
    }

    public String getThePublicOpenId() {
        return thePublicOpenId;
    }

    public void setThePublicOpenId(String thePublicOpenId) {
        this.thePublicOpenId = thePublicOpenId;
    }
}