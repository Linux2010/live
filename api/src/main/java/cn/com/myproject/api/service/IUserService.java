package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.ConfigSetting;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserTeacherFocus;
import cn.com.myproject.user.entity.PO.UserTeacherRewardOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/27.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IUserService {

    @PostMapping("/user/reigister")
    int reigister(@RequestBody User user);

    @GetMapping("/user/getByLogin")
    User getByLogin(String loginName);

    @GetMapping("/user/login")
    Map<String,Object> login(@RequestParam("counttriesCode") String counttriesCode, @RequestParam("loginName") String loginName, @RequestParam("pwd") String pwd);

    @PostMapping("/user/checkLoginName")
    int checkLoginName(@RequestParam("loginName") String loginName);

    @PostMapping("/user/checkPhone")
    int checkPhone(@RequestParam("phone") String phone);

    @PostMapping("/user/updateUser")
    void updateUser(@RequestBody User user);

    @PostMapping("/user/updatePwd")
    void updatePwd(@RequestBody User user);

    @PostMapping("/user/updatePay")
    void updatePay(@RequestBody User user);

    @PostMapping("/user/selectById")
    User selectById(@RequestParam("userId")String userId);

    @PostMapping("/user/findPwd")
    void findPwd(@RequestBody User user);

    @PostMapping("/user/selecUserDetail")
    User selecUserDetail(@RequestParam("userId") String userId);

    @PostMapping("/user/findPay")
    void findPay(@RequestBody User user);

    @PostMapping("/user/selectMyFocusTeachers")
    List<UserTeacherFocus> selectMyFocusTeachers(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestBody UserTeacherFocus focus);


    @PostMapping("/user/selectFocusMeUsers")
    List<UserTeacherFocus> selectFocusMeUsers(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestBody UserTeacherFocus focus);

    @PostMapping("/user/zfbReturnSuccessUpdateRewardOrderStatus")
    int zfbReturnSuccessUpdateRewardOrderStatus(@RequestParam("usertTeacherRewardOrderId") String usertTeacherRewardOrderId,@RequestParam("transactionId") String transactionId);

    @PostMapping("/user/checkUserNameOnly")
    int checkUserNameOnly(@RequestParam("userName") String userName);

    @PostMapping("/user/updateVIP")
    int updateVIP(@RequestBody User user);

    @PostMapping("/user/getUserMessageByIm")
    User getUserMessageByIm(@RequestParam("accid") String accid);

    @PostMapping("/user/addShareUser")
    int addShareUser(@RequestParam("referrerId") String referrerId,@RequestParam("registerId") String registerId);

    @PostMapping("/user/getLabelName")
    String getLabelName(@RequestParam("labelId") String labelId);

    @PostMapping("/user/checkCodeAndPhone")
    int checkCodeAndPhone(@RequestParam("countryCode") String countryCode,@RequestParam("phone") String phone);

    @PostMapping("/user/createQrCode")
    String createQrCode(@RequestParam("urlVal") String urlVal,@RequestParam("userId") String userId);

    @PostMapping("/user/getUserByOpenId")
    User getUserByOpenId(@RequestParam("openId") String openId);

    @PostMapping("/user/bindQQ")
    int bindQQ(@RequestBody User user);

    @PostMapping("/user/selectUserByPhoneNum")
    User selectUserByPhoneNum(@RequestParam("phone") String phone);

    @PostMapping("/user/getUserByWxOpenId")
    User getUserByWxOpenId(@RequestParam("wxOpenId") String wxOpenId);

    @PostMapping("/user/bindWx")
    int bindWx(@RequestBody User user);

    @PostMapping("/user/getUserTeacherRewardOrder")
    UserTeacherRewardOrder getUserTeacherRewardOrder(@RequestBody UserTeacherRewardOrder order);

    @PostMapping("/user/getUserByWeiboOpenId")
    User getUserByWeiboOpenId(@RequestParam("weiboOpenId") String weiboOpenId);

    @PostMapping("/user/bindWeiBo")
    int bindWeiBo(@RequestBody User user);

    @PostMapping("/configSetting/selectConfigSettingBySign")
    ConfigSetting selectConfigSettingBySign(@RequestParam("configSign")  String configSign);

    @PostMapping("/user/updateQrCodeImg")
    int  updateQrCodeImg(@RequestBody User user);

    @PostMapping("/user/createImg")
    String  createImg(@RequestBody User user,@RequestParam("shareImg") String shareImg);

    @PostMapping("/user/batchQrCode")
    String  batchQrCode(@RequestBody User user);

    @GetMapping("/user/searchUserIdentityByUserId")
    int searchUserIdentityByUserId(@RequestParam("userId") String userId);

    @PostMapping("/user/selectThePublicOpenId")
    String selectThePublicOpenId(@RequestParam("userId") String userId);

    @PostMapping("/user/updateThePublicOpenId")
    int updateThePublicOpenId(@RequestBody  User user);

    @PostMapping("/user/updatePwdByUserId")
    void updatePwdByUserId(@RequestBody User user);

    @PostMapping("/user/updatePayByUserId")
    void updatePayByUserId(@RequestBody User user);

}