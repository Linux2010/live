package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.service.IUserAdressService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.UserAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
@RestController
@RequestMapping("/api/user_address")
@Api(value = "收货地址类", tags = "收货地址")
public class UserAddressController {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private IUserAdressService userAdressService;

    @RequestMapping(value = "/add_address", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "添加收货地址", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "收件人名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "countries", value = "国家名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "province", value = "省名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "city", value = "市名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "area", value = "地区名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "coun_id", value = "国家Id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pro_id", value = "省id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "city_id", value = "市id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "area_id", value = "地区id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "address", value = "详细地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "isDefault", value = "是不是默认地址 1：是默认，0：不默认", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "String")
    })
    private Message add_address(String userName, String countries, String province, String city, String area, String coun_id, String pro_id, String city_id, String area_id, String phone, String address, String isDefault, String userId) {

        Message message = new Message();
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("用户Id不能为空!");
        } else {
            if (StringUtils.isBlank(userName)) {
                return MessageUtils.getFail("收件人姓名不能为空!");
            }
            if (StringUtils.isBlank(phone)) {
                return MessageUtils.getFail("手机号不能为空!");
            }
            if (StringUtils.isBlank(address)) {
                return MessageUtils.getFail("详细地址不能为空!");
            }
            if(StringUtils.isBlank(isDefault)){
                return MessageUtils.getFail("没有选择是否为默认!");
            }
            String area_;
            if (StringUtils.isBlank(area)) {
                area_ = "";
            } else {
                area_ = area;
            }
            String area_id_;
            if (StringUtils.isBlank(area_id)) {
                area_id_ = "";
            } else {
                area_id_ = area_id;
            }
            UserAddress userAddress = new UserAddress();
            userAddress.setUserName(userName);
            userAddress.setCountries(countries);
            userAddress.setCountriesId(city_id);
            userAddress.setProvince(province);
            userAddress.setProvinceId(pro_id);
            userAddress.setCity(city);
            userAddress.setCityId(city_id);
            userAddress.setArea(area_);
            userAddress.setAreaId(area_id_);
            userAddress.setAddress(address);
            userAddress.setPhone(phone);
            userAddress.setIsDefault(Integer.valueOf(isDefault));
            userAddress.setUserId(userId);
           try{

               UserAddress default_ = userAdressService.selectDefaultAdd(userId);
               if(Integer.valueOf(isDefault) == 0){
                   userAdressService.add_address(userAddress);
               }else{
                   if(null != default_){
                       userAdressService.updateChecked(default_.getAddId());
                       userAdressService.add_address(userAddress);
                   }else{
                       userAdressService.add_address(userAddress);
                   }
               }
               message = MessageUtils.getSuccess("success");
           }catch (Exception e){
               logger.error("api-userAddressController-add_address-添加失败:"+e);
               return MessageUtils.getFail("添加失败");
           }
        }
        return message;
    }

    @RequestMapping(value = "/update_address", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "修改收货地址", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "收件人名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "countries", value = "国家名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "province", value = "省名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "city", value = "市名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "area", value = "地区名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "coun_id", value = "国家Id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pro_id", value = "省id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "city_id", value = "市id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "area_id", value = "地区id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "address", value = "详细地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "isDefault", value = "是不是默认地址 1：是默认，0：不默认", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "addId", value = "地址id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "String")
    })
    private Message update_address(String userName, String countries, String province, String city, String area, String coun_id, String pro_id, String city_id, String area_id, String phone, String address, String isDefault, String addId,String userId) {

        Message message = new Message();
        UserAddress userAddress = new UserAddress();
        UserAddress user_address = userAdressService.selectById(addId);
        if(user_address == null){
            return MessageUtils.getFail("此条信息不存在!");
        }
        if (StringUtils.isBlank(addId)) {
            return MessageUtils.getFail("用户Id不能为空!");
        } else {
            if(StringUtils.isBlank(userName)){
                userAddress.setUserName(user_address.getUserName());
            }else{
                userAddress.setUserName(userName);
            }
            if(StringUtils.isBlank(countries)){
                userAddress.setCountries(user_address.getCountries());
            }else{
                userAddress.setCountries(countries);
            }
            if(StringUtils.isBlank(province)){
                userAddress.setProvince(user_address.getProvince());
            }else{
                userAddress.setProvince(province);
            }
            if(StringUtils.isBlank(city)){
                userAddress.setCity(user_address.getCity());
            }else{
                userAddress.setCity(city);
            }
            if(StringUtils.isBlank(area)){
                userAddress.setArea(user_address.getArea());
            }else{
                userAddress.setArea(area);
            }
            if(StringUtils.isBlank(phone)){
                userAddress.setPhone(user_address.getPhone());
            }else{
                userAddress.setPhone(phone);
            }
            if(StringUtils.isBlank(address)){
                userAddress.setAddress(user_address.getAddress());
            }else{
                userAddress.setAddress(address);
            }
            if(StringUtils.isBlank(isDefault)){
                userAddress.setIsDefault(user_address.getIsDefault());
            }else{
                userAddress.setIsDefault(Integer.valueOf(isDefault));
            }
            if(StringUtils.isBlank(coun_id)){
                userAddress.setCountriesId(user_address.getCountriesId());
            }else{
                userAddress.setCountriesId(coun_id);
            }
            if(StringUtils.isBlank(pro_id)){
                userAddress.setProvinceId(user_address.getProvinceId());
            }else{
                userAddress.setProvinceId(pro_id);
            }
            if(StringUtils.isBlank(city_id)){
                userAddress.setCityId(user_address.getCityId());
            }else{
                userAddress.setCityId(city_id);
            }
            if(StringUtils.isBlank(area_id)){
                userAddress.setAreaId(user_address.getAreaId());
            }else{
                userAddress.setAreaId(area_id);
            }
            String area_;
            if (StringUtils.isBlank(area)) {
                area_ = "";
            } else {
                area_ = area;
            }
            String area_id_;
            if (StringUtils.isBlank(area_id)) {
                area_id_ = "";
            } else {
                area_id_ = area_id;
            }
            userAddress.setAddId(addId);
            try{
                UserAddress default_ = userAdressService.selectDefaultAdd(userId);
                if(null == default_){
                    userAdressService.update_address(userAddress);
                }else{
                  if(Integer.valueOf(isDefault) == 0){
                      userAdressService.update_address(userAddress);
                  }else{
                      userAdressService.updateChecked(default_.getAddId());
                      userAdressService.update_address(userAddress);
                  }
                }
                message = MessageUtils.getSuccess("success");
            }catch (Exception e){
                logger.error("api-userAddressController-update_address-修改失败:"+e);
                return MessageUtils.getFail("修改失败");
            }
        }
        return message;
    }
    @RequestMapping(value = "/address_list", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "收货地址列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前第几页", required = false, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示多少条", required = false, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "String",defaultValue = "fgdg")
    })
    public Message address_list(String pageNum,String pageSize,String userId){
        Message message = new Message();
        List<UserAddress> list = null;
        if(StringUtils.isBlank(userId)){
            return MessageUtils.getFail("用户Id不能为空!");
        }else{
            list = userAdressService.list(Integer.valueOf(pageNum),Integer.valueOf(pageSize),userId);
            message = MessageUtils.getSuccess("success");
        }
        message.setData(list);
        return message;
    }

    @RequestMapping(value = "/del_address", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "删除收货地址", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "addId", value = "收货地址id", required = true, dataType = "String")
    })
    public Message del_address(String addId){
        Message message = new Message();
        UserAddress user_address = userAdressService.selectById(addId);
        if(user_address == null){
            return MessageUtils.getFail("此条信息不存在!");
        }
        if(StringUtils.isBlank(addId)){
            return MessageUtils.getFail("收货地址Id不能为空!");
        }else{
            userAdressService.del_address(addId);
            message = MessageUtils.getSuccess("success");
        }
        return message;
    }

    @RequestMapping(value = "/selectById", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "查询某一个收货地址的详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "addId", value = "收货地址id", required = true, dataType = "String")
    })
    public Message selectById(String addId){
        Message message = new Message();
        UserAddress userAddress = new UserAddress();
        if(StringUtils.isBlank(addId)){
            return MessageUtils.getFail("收货地址Id不能为空!");
        }else{
            userAddress = userAdressService.selectById(addId);
            message = MessageUtils.getSuccess("success");
        }
        message.setData(userAddress);
        return message;
    }

    @RequestMapping(value = "/selectDefault", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "查询用户的默认地址", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id", required = true, dataType = "String",defaultValue = "49bef1ba0754488c8aa711a52b623e72")
    })
    public Message selectDefault(String userId){
        Message message = new Message();
        UserAddress userAddress = new UserAddress();
        if(StringUtils.isBlank(userId)){
            return MessageUtils.getFail("用户Id不能为空!");
        }else{
            userAddress = userAdressService.selectDefaultAdd(userId);
           if(userAddress != null){
               message = MessageUtils.getSuccess("success");
           }else{
               message = MessageUtils.getFail("你还没设置默认地址");
           }
        }
        message.setData(userAddress);
        return message;
    }

}
