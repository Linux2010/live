package cn.com.myproject.user.entity.VO;


import cn.com.myproject.user.entity.PO.StudyLabel;
import cn.com.myproject.user.entity.PO.UserLevel;
import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * api教头信息
 * Created by LeiJia on 2017/6/27.
 */
@ApiModel(value = "APITearcherUser", description = "教头对像")
public class APITearcherUser extends BasePO {
    @ApiModelProperty(value = "userId即是讲师ID")
    private String userId;
    @ApiModelProperty(value = "登录账号")
    private String loginName;
    @ApiModelProperty(value = "教头名称")
    private String userName;
    @ApiModelProperty(value = "教头昵称")
    private String nickName;
    @ApiModelProperty(value = "教头真实姓名")
    private String realName;
    @ApiModelProperty(value = "教头手机号")
    private String phone;
    @ApiModelProperty(value = "教头头像")
    private String photo;
    @ApiModelProperty(value = "教头个性签名")
    private String signature;
    @ApiModelProperty(value = "教头用户简介富文本")
    private String userIntro;
    @ApiModelProperty(value = "教头用户简介简短")
    private String userIntroText;
    private String countryCode;
    @ApiModelProperty(value = "教头邮箱")
    private String email;
    @ApiModelProperty(value = "教头登录密码")
    private String password;
    @ApiModelProperty(value = "教头支付密码")
    private String payPassword;
    @ApiModelProperty(value = "身份证号")
    private String IDNum;
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
    @ApiModelProperty(value = "账号余额")
    private Double money;
    private String countries;
    private String province;
    private String city;
    private String area;
    private Integer countriesId;
    private Integer provinceId;
    private Integer cityId;
    private Integer areaId;
    @ApiModelProperty(value = "积分")
    private Double score;
    /**
     * 用户类型1：教头 2：用户
     */
    @ApiModelProperty(value = "用户类型1：教头 2：用户")
    private Integer userType;
    @ApiModelProperty(value = "用户学习标签对象ID")
    private String labelId;
    @ApiModelProperty(value = "用户学习标签对象")
    private StudyLabel stulabel;
    @ApiModelProperty(value = "教头等级")
    private String userLevel;
    private Integer userIdentity;
    @ApiModelProperty(value = "教头类别ID")
    private String userTypeId;
    @ApiModelProperty(value = "教头类别名称")
    private String userTypeName;

    @ApiModelProperty(value = "教头序号")
    private int seqno;

    @ApiModelProperty(value = "教头奖项")
    private String userAwards;

    @ApiModelProperty(value = "用户是否关注教头0：未关注；1:关注了")
    private int isFocus;

    @ApiModelProperty(value = "教头简介url")
    private String teacherIntroUrl;

    @ApiModelProperty(value="首页名优教头头像尺寸比例为1.725:1")
    private String rectanglePhoto;

    @ApiModelProperty(value="教头简介图片URL集合")
    private List<String> teacherIntroImgs = new ArrayList<String>();

    public List<String> getTeacherIntroImgs() {
        return teacherIntroImgs;
    }

    public void setTeacherIntroImgs(List<String> teacherIntroImgs) {
        this.teacherIntroImgs = teacherIntroImgs;
    }

    public String getRectanglePhoto() {
        return rectanglePhoto;
    }

    public void setRectanglePhoto(String rectanglePhoto) {
        this.rectanglePhoto = rectanglePhoto;
    }


    public String getTeacherIntroUrl() {
        return teacherIntroUrl;
    }

    public void setTeacherIntroUrl(String teacherIntroUrl) {
        this.teacherIntroUrl = teacherIntroUrl;
    }

    public String getUserIntroText() {
        return userIntroText;
    }

    public void setUserIntroText(String userIntroText) {
        this.userIntroText = userIntroText;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }

    public String getUserAwards() {
        return userAwards;
    }

    public void setUserAwards(String userAwards) {
        this.userAwards = userAwards;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public StudyLabel getStulabel() {
        return stulabel;
    }

    public void setStulabel(StudyLabel stulabel) {
        this.stulabel = stulabel;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
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

    public Integer getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(Integer countriesId) {
        this.countriesId = countriesId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
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

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
}
