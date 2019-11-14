package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by JYP on 2017/9/13 0013.
 */
@ApiModel(value = "UserAddress", description = "收货地址对象")
public class UserAddress extends BasePO{

    @ApiModelProperty(value = "收货地址业务id")
    private String addId;
    @ApiModelProperty(value = "用户业务id")
    private String userId;
    @ApiModelProperty(value = "收件人姓名")
    private String userName;
    @ApiModelProperty(value = "收件人所在国家")
    private String countries;
    @ApiModelProperty(value = "收件人所在国家id")
    private String countriesId;
    @ApiModelProperty(value = "收件人所在省市")
    private String province;
    @ApiModelProperty(value = "收件人所在省市Id")
    private String provinceId;
    @ApiModelProperty(value = "收件人所在城市")
    private String city;
    @ApiModelProperty(value = "收件人所在城市Id")
    private String cityId;
    @ApiModelProperty(value = "收件人所在地区")
    private String area;
    @ApiModelProperty(value = "收件人所在地区Id")
    private String areaId;
    @ApiModelProperty(value = "收件人详细地址")
    private String address;
    @ApiModelProperty(value = "收件人联系方式")
    private String phone;
    @ApiModelProperty(value = "是不是默认地址1:代表默认 0:不是默认")
    //是不是默认地址
    private Integer isDefault;

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(String countriesId) {
        this.countriesId = countriesId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
