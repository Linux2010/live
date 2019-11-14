package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.ISendMessageService;
import cn.com.myproject.api.service.IUploadImgService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.user.IUserSignInService;
import cn.com.myproject.api.util.DateUtils;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserSignIn;
import cn.com.myproject.user.entity.PO.UserTeacherFocus;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by JYP on 2017/8/29 0029.
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "用户类", tags = "用户")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private IUserSignInService userSignInService;

    @Autowired
    private ISendMessageService sendMessageService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ICourseService courseService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     * 短信验证码前缀
     */
    private static final String SMS_PREFIX = "【教头学院】";

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "修改个人信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "昵称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "realName", value = "姓名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "signature", value = "签名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "countries", value = "国家名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "province", value = "省名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "city", value = "市名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "coun_id", value = "国家Id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pro_id", value = "省id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "city_id", value = "市id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "String")
    })
    public Message updateUser(MultipartFile file, String phone, String email, String signature, String countries, String province, String city, String coun_id, String pro_id, String city_id, HttpServletResponse response, String userId, String userName, String realName) {
        Long time0= System.currentTimeMillis();
        logger.info("修改用户信息-开始 time0 ：" +time0);
        if(StringUtils.isEmpty(userName)|| StringUtils.isBlank(userName)){
            return MessageUtils.getFail("userName不能为空");
        }
      /* if(StringUtils.isEmpty(realName)|| StringUtils.isBlank(realName)){
            return MessageUtils.getFail("realName不能为空");
        }*/
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("tokenIsNull");
        } else {
            User user = new User();
            try {
                String fileUrl = uploadImgService.uploadImg(file);
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
                User old = userService.selectById(userId);
                if (!old.getUserName().equals(userName)) {
                    if (userName != null && !"".equals(userName)) {

                        if (userService.checkUserNameOnly(userName) == 1) {
                            return MessageUtils.getFail("修改失败，用户名已存在!");
                        } else {
                            user.setUserName(userName);
                            user.setNickName(userName);
                            user.setLoginName(userName);

                            //用户名发生变化重新合成邀请好友海报分享图片
                            user.setUserId(old.getUserId());
                            Long time1= System.currentTimeMillis();
                            logger.info("修改用户信息-合成邀请好友图片开始 time1 ：" +time1);
                            String imgUrl = userService.batchQrCode(user); //合成邀请好友图片

                            if(StringUtils.isNotEmpty(imgUrl)){
                                logger.info("用户名发生变化重新合成邀请好友海报分享图片成功");
                                user.setQrCodeImgUrl(imgUrl);
                            }
                            Long time2 = System.currentTimeMillis();
                            logger.info("修改用户信息-合成邀请好友图片结束 time2 ：" +time2);
                            System.out.println("修改用户信息-合成邀请好友图片需要时间：" +(time2-time1)/1000+"秒");
                        }
                    } else {
                        user.setUserName(userName);
                        user.setNickName(userName);
                        user.setLoginName(userName);
                    }
                } else {
                    user.setUserName(old.getUserName());
                    user.setLoginName(old.getLoginName());
                    user.setNickName(old.getNickName());
                }

                user.setRealName(realName);
                if (StringUtils.isNotBlank(fileUrl)) {
                    user.setPhoto(fileUrl);
                } else {
                    user.setPhoto(old.getPhoto());
                }
                user.setPhone(phone);

                user.setEmail(email);

                user.setSignature(signature);

                user.setCountries(countries);

                user.setProvince(province);

                user.setCity(city);

                user.setCountriesId(coun_id);

                user.setProvinceId(pro_id);

                user.setCityId(city_id);

                user.setUserId(userId);
                userService.updateUser(user);

                Long time3 = System.currentTimeMillis();
                logger.info("修改用户信息-结束时间 time3 ：" +time3);
                logger.info("修改用户信息- 总计费时：" +(time3-time0)/1000 +"秒");
                return MessageUtils.getSuccess("success");
            } catch (Exception e) {
                logger.info("修改个人信息失败:" + e.getMessage());
                return MessageUtils.getFail("修改失败");
            }
        }
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "修改登录密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "oldpwd", value = "老登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "loginpwd", value = "登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "String")
    })
    public Message updatePwd(String oldpwd, String loginpwd, String userId) {
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("tokenIsNull");
        } else {
            User user = new User();
            try {
                if (StringUtils.isBlank(oldpwd)) {
                    return MessageUtils.getFail("修改失败,老密码不能为空!");
                }
                if (StringUtils.isBlank(loginpwd)) {
                    return MessageUtils.getFail("修改失败,登录密码不能为空!");
                }
                User old = userService.selectById(userId);
                if (!DigestUtils.md5Hex(oldpwd).equals(old.getPassword())) {
                    return MessageUtils.getFail("修改失败,老密码不正确!");
                }
                user.setUserId(userId);
                user.setPassword(DigestUtils.md5Hex(loginpwd));
                userService.updatePwdByUserId(user);
                return MessageUtils.getSuccess("success");
            } catch (Exception e) {
                logger.info("修改登录密码失败:" + e.getMessage());
                return MessageUtils.getFail("修改失败");
            }
        }
    }

    @RequestMapping(value = "/updatePay", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "修改支付密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "oldpaypwd", value = "老支付密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "paypwd", value = "支付密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = false, dataType = "String")
    })
    public Message updatePay(String oldpaypwd, String paypwd, String userId) {
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("tokenIsNull");
        } else {
            User user = new User();
            try {
                if (StringUtils.isBlank(oldpaypwd)) {
                    return MessageUtils.getFail("修改失败,老密码不能为空!");
                }
                if (StringUtils.isBlank(paypwd)) {
                    return MessageUtils.getFail("修改失败,支付密码不能为空!");
                }
                User old = userService.selectById(userId);
                if (!DigestUtils.md5Hex(oldpaypwd).equals(old.getPayPassword())) {
                    return MessageUtils.getFail("修改失败,老密码不正确!");
                }
                user.setUserId(userId);
                user.setPayPassword(DigestUtils.md5Hex(paypwd));
                System.out.print(DigestUtils.md5Hex(paypwd));
                userService.updatePayByUserId(user);
                return MessageUtils.getSuccess("success");
            } catch (Exception e) {
                logger.info("修改支付密码失败:" + e.getMessage());
                return MessageUtils.getFail("修改失败");
            }
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "用户签到", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id",
                    required = true, dataType = "String", defaultValue = "")
    })
    public Message signIn(String userId) throws ParseException {

        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("参数不能为空");
        }

        int result = 0;

        UserSignIn userSignIn = userSignInService.selectByUserId(userId);
        if (null != userSignIn) {
            Date lastSignDate = new Date(userSignIn.getLastSignTime());
            Date nowDate = new Date();
            int i = DateUtils.isDateContin(lastSignDate, nowDate);
            if (1 == i) { // 至少是前天。
                userSignIn.setContinueDays(0);
                userSignIn.setLastSignTime(new Date().getTime());
                userSignIn.setSignTimeRecord(userSignIn.getSignTimeRecord() + "," + String.valueOf(new Date().getTime()));
            } else if (0 == i) { // 昨天
                userSignIn.setContinueDays(userSignIn.getContinueDays() + 1);
                userSignIn.setLastSignTime(new Date().getTime());
                userSignIn.setSignTimeRecord(userSignIn.getSignTimeRecord() + "," + String.valueOf(new Date().getTime()));
            } else if (-1 == i) { //今天
                return MessageUtils.getSuccess("今天已签到");
            }
            result = userSignInService.updateByPrimaryKeySelective(userSignIn);
        } else {
            userSignIn = new UserSignIn();
            userSignIn.setContinueDays(0);
            userSignIn.setLastSignTime(new Date().getTime());
            userSignIn.setUserId(userId);
            userSignIn.setSignTimeRecord(String.valueOf(new Date().getTime()));
            result = userSignInService.insertSelective(userSignIn);
        }
        Message message = new Message();
        if (result == 1) {
            message = MessageUtils.getSuccess("success");
        } else {
            message = MessageUtils.getFail("签到失败");
        }
        return message;
    }


    @RequestMapping(value = "/isSignIn", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "用户是否签到", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id",
                    required = true, dataType = "String", defaultValue = "")
    })
    public Message isSignIn(String userId) throws ParseException {
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getSuccess("参数不能为空");
        }
        Message message = MessageUtils.getSuccess("success");
        Map<String, Object> map = new HashMap<>();
        map.put("userSignIn", "false");
        int result = 0;
        UserSignIn userSignIn = userSignInService.selectByUserId(userId);
        if (null != userSignIn) {
            Date lastSignDate = new Date(userSignIn.getLastSignTime());
            Date nowDate = new Date();
            int i = DateUtils.isDateContin(lastSignDate, nowDate);
            if (-1 == i) { //今天已经签到。
                map.put("userSignIn", "true");
            }
        }
        message.setData(map);
        return message;
    }

    @RequestMapping(value = "/findPwd", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "找回登录密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newpwd", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "countrise_code", value = "区号", required = true, dataType = "String")
    })
    public Message findPwd(String phone, String code, String newpwd, String countrise_code) {
        User user = new User();
        if (StringUtils.isBlank(phone)) {
            return MessageUtils.getFail("发送失败,手机号码不能为空!");
        }
        if (StringUtils.isNotBlank(phone)) {
            if (userService.checkCodeAndPhone(countrise_code, phone) == 0) {
                return MessageUtils.getFail("修改失败，此手机号尚未注册!");
            }
        }
        if (StringUtils.isBlank(code)) {
            return MessageUtils.getFail("修改失败，短信验证码不能为空!");
        }
        //String mobile_code = "111111";
        String mobile_code = redisService.getValue("message_code_" + phone);
        if (!mobile_code.equals(code)) {
            return MessageUtils.getFail("修改失败，短信验证码错误!");
        }
        if (StringUtils.isBlank(newpwd)) {
            return MessageUtils.getFail("修改失败，密码不能为空!");
        }
        try {
            user.setPassword(DigestUtils.md5Hex(newpwd));
            user.setPhone(phone);
            userService.findPwd(user);
        } catch (Exception e) {
            logger.info("找回登录密码失败:" + e.getMessage());
            return MessageUtils.getFail("修改失败");
        }
        return MessageUtils.getSuccess("success");
    }

    @RequestMapping(value = "/sendCode", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "获取验证码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "countries_code", value = "区号", required = true, dataType = "String")
    })
    public Message sendCode(String phone, String countries_code) {
        if (StringUtils.isBlank(countries_code)) {
            return MessageUtils.getFail("发送失败,你还没有选择国家!");
        }
        if (StringUtils.isBlank(phone)) {
            return MessageUtils.getFail("发送失败,手机号码不能为空!");
        }
        if (StringUtils.isNotBlank(phone)) {
            if (userService.checkCodeAndPhone(countries_code, phone) == 0) {
                return MessageUtils.getFail("发送失败,此手机号尚未注册!");
            }
        }
        String mobileCode = "" + (int) ((Math.random() * 9 + 1) * 100000);
        String content = SMS_PREFIX + "您找回密码的验证码为：" + mobileCode + ",如非本人操作,请忽略本短信!";

        try {
            if (StringUtils.isNotBlank(countries_code)) {
                if (countries_code.equals("86")) {//发送国内短信
                    sendMessageService.sendChina(phone, content);
                } else {//发送国外短信
                    String comphone = countries_code + phone;//区号和手机号拼接
                    sendMessageService.sendInternation(comphone, content);
                }
            }
        } catch (Exception e) {
            logger.error("发送找回找回密码的验证码失败" + e);
            return MessageUtils.getFail("fial");
        }
        String message_code_ = "message_code_" + phone;
        long timeout = 1 * 60;
        boolean seedSign = redisService.setValue(message_code_, mobileCode, timeout);
        if (seedSign) {
            redisService.setValue(message_code_, mobileCode, timeout);
        }
        return MessageUtils.getSuccess("success");

    }

    /**
     *支付密码校验是否设置
     * @param userId
     * @return 0：未设置 1：已设置
     *//*
    @RequestMapping(value="/checkPay",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "校验支付密码是否设置", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id", required = true, dataType = "String")
    })
    public Message checkPay(String userId){
        User user = userService.selectById(userId);
        String is_pay;
        if( user.getPayPassword() == null || user.getPayPassword() == ""){
                is_pay = "0";
            }else{
                is_pay = "1";
            }
            Message message = MessageUtils.getSuccess("success");
            message.setData(is_pay);
            return message;
    }*/

    /**
     * 设置支付密码
     *
     * @param userId
     * @param paypwd
     * @return
     */
    @RequestMapping(value = "/settingPay", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "设置支付密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "paypwd", value = "支付密码", required = true, dataType = "String")
    })
    public Message settingPay(String userId, String paypwd) {
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("tokenIsNull");
        } else {
            Message message = null;
            User user = new User();
            try {
                if (StringUtils.isBlank(paypwd)) {
                    return MessageUtils.getFail("设置失败,支付密码不能为空");
                }
                user.setUserId(userId);
                user.setPayPassword(DigestUtils.md5Hex(paypwd));
                userService.updatePayByUserId(user);
                message = MessageUtils.getSuccess("success");
                ;
            } catch (Exception e) {
                logger.info("设置支付密码失败:" + e.getMessage());
                message = MessageUtils.getFail("设置失败");
            }
            return message;
        }
    }

    /**
     * 获取登录人信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
    @ApiOperation(value = "获取登录人信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id", required = true, dataType = "String")
    })
    public Message<User> getUserDetail(String userId, HttpServletRequest request) {
        if (StringUtils.isBlank(userId)) {
            return MessageUtils.getFail("tokenIsNull");
        } else {
            User user = userService.selecUserDetail(userId);
            if (null == user) {
                return MessageUtils.getFail("没有此用户！");
            }
            if (user.getPayPassword() == null || user.getPayPassword() == "") {
                user.setIs_pay("0");
            } else {
                user.setIs_pay("1");
            }
            if (user.getQrCodeUrl() != null && !"".equals(user.getQrCodeUrl())) {
                user.setQrCodePage(jtxyappUrl + "/api/login/showQrCodePage?qrCodeUrl=" + user.getQrCodeUrl() + "&userName=" + user.getUserName());
            }
            Message message = MessageUtils.getSuccess("success");
            int mkNumVal = courseService.searchMyCourseCount(userId);// 得到我的课程数量
            int msNumVal = courseService.searchMyCcCount(userId);// 得到我的收藏数量
            user.setMkNum(mkNumVal);
            user.setMsNum(msNumVal);
            user.setToken(redisService.getValue(DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName())));
            if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getCountries()) || StringUtils.isBlank(user.getSignature())) {
                user.setIs_complet(0);
            } else {
                user.setIs_complet(1);
            }
            message.setData(user);
            return message;
        }

    }

    @RequestMapping(value = "/findPay", method = RequestMethod.POST)
    @ApiOperation(value = "找回支付密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newpwd", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "countrise_code", value = "区号", required = true, dataType = "String")
    })
    public Message findPay(String phone, String code, String newpwd, String countrise_code) {
        User user = new User();
        if (StringUtils.isBlank(phone)) {
            return MessageUtils.getFail("发送失败,手机号码不能为空!");
        }
        if (StringUtils.isNotBlank(phone)) {
            if (userService.checkCodeAndPhone(countrise_code, phone) == 0) {
                return MessageUtils.getFail("修改失败，此手机号尚未注册!");
            }
        }
        if (StringUtils.isBlank(code)) {
            return MessageUtils.getFail("修改失败，短信验证码不能为空!");
        }
        //String mobile_code = "111111";
        String mobile_code = redisService.getValue("message_code_" + phone);
        if (!mobile_code.equals(code)) {
            return MessageUtils.getFail("修改失败，短信验证码错误!");
        }
        if (StringUtils.isBlank(newpwd)) {
            return MessageUtils.getFail("修改失败，密码不能为空!");
        }
        try {
            user.setPayPassword(DigestUtils.md5Hex(newpwd));
            user.setPhone(phone);
            userService.findPay(user);
        } catch (Exception e) {
            logger.info("找回支付密码失败:" + e.getMessage());
            return MessageUtils.getFail("修改失败");
        }
        return MessageUtils.getSuccess("success");
    }

    /**
     * 我的关注-关注我的与我的关注
     *
     * @param userId
     * @param type
     * @return
     * @author LeiJia
     */
    @PostMapping("/selectMyFocusTeachers")
    @ApiOperation(value = "我的关注-关注我的与我的关注", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", required = true, dataType = "String", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", required = true, dataType = "String", defaultValue = "10"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户业务ID", required = true, dataType = "String", defaultValue = "ss2"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "查询类型0：关注我的，1：我的关注", required = true, dataType = "String", defaultValue = "1")
    })
    public Message<List<UserTeacherFocus>> selectMyFocusTeachers(String pageNum, String pageSize, String userId, String type) {
        if(userService.selectById(userId) == null){
            return MessageUtils.getFail("非法会员ID!");
        }
        UserTeacherFocus focus = new UserTeacherFocus(userId);
        List<UserTeacherFocus> focusList = null;
        if ("0".equals(type)) {
            focusList = userService.selectFocusMeUsers(Integer.valueOf(pageNum), Integer.valueOf(pageSize), focus);
        } else if ("1".equals(type)) {
            focusList = userService.selectMyFocusTeachers(Integer.valueOf(pageNum), Integer.valueOf(pageSize), focus);
        }
        Message<List<UserTeacherFocus>> message = MessageUtils.getSuccess("success");
        message.setData(focusList);

        return message;
    }

    @RequestMapping(value = "/getUserMessageByIm", method = RequestMethod.POST)
    @ApiOperation(value = "根据Im账号获取个人信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "accid", value = "IM账号", required = true, dataType = "String")
    })
    public Message getUserMessageByIm(String accid) {
        Message message = null;
        if (StringUtils.isBlank(accid)) {
            return MessageUtils.getFail("IM账号不能为空!");
        }
        User user = userService.getUserMessageByIm(accid);
        if (user == null) {
            return MessageUtils.getFail("此账户不存在!");
        }
        message = MessageUtils.getSuccess("success");
        message.setData(user);
        return message;
    }

    @RequestMapping(value = "/getSoftVersion", method = RequestMethod.POST)
    @ApiOperation(value = "获取应用软件最新版本号", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "type", value = "应用软件类型 0:ios最新版本号,1：android最新版本号", required = true, dataType = "String",defaultValue = "0")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "Message" ) })
    public Message getSoftVersion(String type) {
        Message message = null;
        if (StringUtils.isBlank(type)||StringUtils.isEmpty(type)) {
            return MessageUtils.getFail("应用软件类型不能为空!");
        }
        ConfigSetting configSetting =null;
        if(type.equals("0")){
            configSetting =  userService.selectConfigSettingBySign("ios_version");
        }else if(type.equals("1")){
            configSetting =  userService.selectConfigSettingBySign("android_version");
        }else{
            return MessageUtils.getFail("不能识别该应用软件类型!");
        }

        ConfigSetting configSettingIsEnforceUpdate =null;
        configSettingIsEnforceUpdate =  userService.selectConfigSettingBySign("isEnforceUpdate");
        if(configSetting == null){
            return MessageUtils.getFail("没有找到该应用软件最新版本号!");
        }
        if(configSettingIsEnforceUpdate == null){
           logger.info("没有找到android是否强制更新信息!");
        }
        message = MessageUtils.getSuccess("success");
        Map<String,String> map = new LinkedHashMap<>();
        map.put("version",configSetting.getConfig_value());
        map.put("isEnforceUpdate",configSettingIsEnforceUpdate.getConfig_value());
        message.setData(map);
        return message;
    }

    @RequestMapping(value = "/getPosterShareImages", method = RequestMethod.POST)
    @ApiOperation(value = "邀请好友-获取海报分享图片", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "Message" ) })
    public Message getPosterShareImages(String userId) {
        Message message = null;
        if (StringUtils.isBlank(userId)||StringUtils.isEmpty(userId)) {
            return MessageUtils.getFail("tokenIsNull");
        }
        try{
            User user= userService.selectById(userId);
            if(user  == null){
                return MessageUtils.getFail("非法会员ID!");
            }
            String qrCodeImgUrl= user.getQrCodeImgUrl();
            message = MessageUtils.getSuccess("success");
            if(StringUtils.isNotBlank(qrCodeImgUrl) && StringUtils.isNotEmpty(qrCodeImgUrl)){
                message.setData(qrCodeImgUrl.split(","));
            }else{
                Long time1= System.currentTimeMillis();
                logger.info("合成邀请好友图片开始 time1 ：" +time1);
                String imgUrl =  userService.batchQrCode(user); //合成邀请好友图片
                Long time2 = System.currentTimeMillis();
                logger.info("合成邀请好友图片结束 time2 ：" +time2);
                System.out.println("合成邀请好友图片需要时间：" +(time2-time1)/1000+"秒");

                //记录合成图片
                if(StringUtils.isNotBlank(imgUrl)){
                    user.setQrCodeImgUrl(imgUrl);
                    int updateQrCode =  userService.updateQrCodeImg(user); //记录合成的海报分享二维码图片
                    if(updateQrCode <1){
                        logger.error("记录合成的海报分享二维码图片失败  userService.updateQrCodeImg(user)");
                        message.setData(new ArrayList<String>());
                    }
                    message.setData(imgUrl.split(","));
                }
                Long time3 = System.currentTimeMillis();
                logger.info("记录合成图片结束时间 time3 ：" +time3);
                logger.info("记录合成图片 费时：" +(time3-time2)/1000 +"秒");
                logger.info("邀请好友-获取海报分享图片 总计费时：" +(time3-time1)/1000 +"秒");
            }
        }catch (Exception e){
            logger.error("API-userController 邀请好友-获取海报分享图片异常"+e.getMessage());
            e.printStackTrace();
            return MessageUtils.getFail("获取海报分享图片异常"+e.getMessage());
        }
        return message;
    }




}