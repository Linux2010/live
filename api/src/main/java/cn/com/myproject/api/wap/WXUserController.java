package cn.com.myproject.api.wap;

import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.ISendMessageService;
import cn.com.myproject.api.service.IUploadImgService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserTeacherFocus;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyp on 2017/9/19 0019.
 */
@Controller
@RequestMapping("/wap/user")
public class WXUserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(WXUserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ISendMessageService sendMessageService;

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private ICourseService courseService;

    private static final String SMS_PREFIX = "【教头学院】";


    @RequestMapping("/send_code")
    @ResponseBody
    public Message send_wap_code(@RequestParam("countries_code") String countries_code, @RequestParam("phone") String phone) {
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
     * 跳转到个人中心
     */
    @RequestMapping("/userInfo")
    @ResponseBody
    public ModelAndView userInfo(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/my/personalData");
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(userId)) {
            model.addObject("result", "0");
            model.addObject("message", "用户Id不能为空");
        }
        User userInfo = userService.selecUserDetail(userId);
        if (null == userInfo) {
            model.addObject("result", "0");
            model.addObject("message", "没有此用户的信息");
        }
        if (userInfo.getPayPassword() == null || userInfo.getPayPassword() == "") {
            userInfo.setIs_pay("0");
        } else {
            userInfo.setIs_pay("1");
        }
        String ccStr = "";
        try {
             ccStr = java.net.URLDecoder.decode(userInfo.getSignature(), "UTF-8");
        }catch (Exception e){
                e.printStackTrace();
        }
        userInfo.setSignature(ccStr);
        request.getSession().setAttribute("userInfo", userInfo);
        return model;
    }

    /**
     * 跳转到设置页面
     */
    @RequestMapping("/setting")
    @ResponseBody
    public ModelAndView setting() {
        ModelAndView model = new ModelAndView("/my/set");
        return model;
    }

    /**
     * 跳转到签名修改页面
     */
    @RequestMapping("/signature")
    @ResponseBody
    public ModelAndView signature() {
        ModelAndView model = new ModelAndView("/my/signature");
        return model;
    }

    /**
     * @param file
     * @param response
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(MultipartFile file,User user_,HttpServletResponse response){
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(userId)) {
            map.put("result", "0");
            map.put("message", "用户Id不能为空!");
            return map;
        } else {
            User user = new User();
            try {
                String fileUrl = uploadImgService.uploadImg(file);
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
                User old = userService.selectById(userId);
                if (!old.getUserName().equals(user_.getUserName())) {
                    if (user_.getUserName() != null && !"".equals(user_.getUserName())) {
                        if (userService.checkUserNameOnly(user_.getUserName()) == 1) {
                            map.put("result", "0");
                            map.put("message", "用户名已存在!");
                            return map;
                        } else {
                            user.setUserName(user_.getUserName());
                            user.setNickName(user_.getUserName());
                            user.setLoginName(user_.getUserName());

                            //用户名发生变化重新合成邀请好友海报分享图片
                            user.setUserId(old.getUserId());
                            Long time1= System.currentTimeMillis();
                            logger.info("修改用户信息-合成邀请好友图片开始 time1 ：" +time1);
                            String imgUrl = userService.batchQrCode(user_); //合成邀请好友图片

                            if(StringUtils.isNotEmpty(imgUrl)){
                                logger.info("用户名发生变化重新合成邀请好友海报分享图片成功");
                                user.setQrCodeImgUrl(imgUrl);
                            }
                            Long time2 = System.currentTimeMillis();
                            logger.info("修改用户信息-合成邀请好友图片结束 time2 ：" +time2);
                            System.out.println("修改用户信息-合成邀请好友图片需要时间：" +(time2-time1)/1000+"秒");
                        }
                    } else {
                        user.setUserName(old.getUserName());
                        user.setLoginName(old.getLoginName());
                        user.setNickName(old.getNickName());
                    }
                } else {
                    user.setUserName(old.getUserName());
                    user.setLoginName(old.getLoginName());
                    user.setNickName(old.getNickName());
                }
                user.setRealName(user_.getRealName());
                if (StringUtils.isNotBlank(fileUrl)) {
                    user.setPhoto(fileUrl);
                } else {
                    user.setPhoto(old.getPhoto());
                }
                user.setPhone(user_.getPhone());

                user.setEmail(user_.getEmail());

                user.setSignature(old.getSignature());

                user.setCountries(user_.getCountries());

                user.setProvince(user_.getProvince());

                user.setCity(user_.getCity());

                user.setCountriesId(user_.getCountriesId());

                user.setProvinceId(user_.getProvinceId());

                user.setCityId(user_.getCityId());

                user.setUserId(userId);
                userService.updateUser(user);
                map.put("result", "1");
                map.put("message", "修改成功!");
            }
            catch (Exception e) {
                logger.info("修改个人信息失败:" + e.getMessage());
                map.put("result", "0");
                map.put("message", "修改失败!");
            }
        }
        return map;
    }

    /**
     * 修改签名
     *
     * @param signature
     * @return
     */
    @RequestMapping("/updateSignature")
    @ResponseBody
    public Map<String, Object> updateSignature(@RequestParam("signature") String signature) {
        Map<String, Object> map = new HashMap<>();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        User old = userService.selectById(userId);
        if (null == old) {
            map.put("result", "0");
            map.put("message", "没有此用户的信息");
        }
        User user = new User();
        try {
            user.setRealName(old.getRealName());
            user.setUserId(userId);
            user.setUserName(old.getUserName());
            user.setNickName(old.getNickName());
            user.setLoginName(old.getLoginName());
            user.setPhoto(old.getPhoto());
            user.setPhone(old.getPhone());
            try {
                // 存入数据库前进行转码
                user.setSignature(java.net.URLEncoder.encode(signature, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            user.setEmail(old.getEmail());
            user.setCountries(old.getCountries());
            user.setCity(old.getCity());
            user.setProvince(old.getProvince());
            user.setCountriesId(old.getCountriesId());
            user.setProvinceId(old.getProvinceId());
            user.setCityId(old.getCityId());
            user.setQrCodeUrl(old.getQrCodeUrl());
            userService.updateUser(user);
            map.put("result", "1");
            map.put("message", "修改成功");
        } catch (Exception e) {
            logger.info("修改个人信息失败:" + e.getMessage());
            map.put("result", "0");
            map.put("message", "修改失败");
        }
        return map;
    }

    /**
     * 跳转到修改登录密码页面
     *
     * @return
     */
    @RequestMapping("/updatePwdPage")
    @ResponseBody
    public ModelAndView updatePwd() {
        ModelAndView model = new ModelAndView("/my/resetPwd");
        return model;
    }

    /**
     * 修改登录密码
     *
     * @param oldpwd
     * @param newpwd
     * @param userId
     * @return
     */
    @RequestMapping(value = "/updatePwd")
    @ResponseBody
    public Map<String, Object> updatePwd(String oldpwd, String newpwd) {
        Map<String, Object> map = new HashMap<>();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        if (StringUtils.isBlank(userId)) {
            map.put("result", "0");
            map.put("message", "userId不能为空!");
        } else {
            User user = new User();
            try {
                if (StringUtils.isBlank(oldpwd)) {
                    map.put("result", "0");
                    map.put("message", "修改失败,老密码不能为空!");
                    return map;
                }
                if (StringUtils.isBlank(newpwd)) {
                    map.put("result", "0");
                    map.put("message", "修改失败,新密码不能为空!");
                    return map;
                }
                User old = userService.selectById(userId);
                if (!DigestUtils.md5Hex(oldpwd).equals(old.getPassword())) {
                    map.put("result", "0");
                    map.put("message", "修改失败,老密码不能为空!");
                    return map;
                }
                user.setUserId(userId);
                user.setPassword(DigestUtils.md5Hex(newpwd));
                userService.updatePwdByUserId(user);
                map.put("result", "1");
                map.put("message", "登录密码修改成功!");
            } catch (Exception e) {
                logger.info("修改登录密码失败:" + e.getMessage());
                map.put("result", "0");
                map.put("message", "登录密码修改失败!");
            }
        }
        return map;
    }

    /**
     * 跳转到设置支付密码页面
     *
     * @return
     */
    @RequestMapping("/payPwdPage")
    @ResponseBody
    public ModelAndView payPwdPage() {
        ModelAndView model = new ModelAndView();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        User userInfo = userService.selecUserDetail(userId);
        if (null == userInfo) {
            model.addObject("result", "0");
            model.addObject("message", "没有此用户的信息");
        }
        if (userInfo.getPayPassword() == null || userInfo.getPayPassword() == "") {
            userInfo.setIs_pay("0");
            model.setViewName("/my/setZhifuPwd");
        } else {
            userInfo.setIs_pay("1");
            model.setViewName("/my/resetZhifuPwd");
        }
        return model;
    }

    /**
     * 设置支付密码
     *
     * @param userId
     * @param paypwd
     * @return
     */
    @RequestMapping("/settingPay")
    @ResponseBody
    public Map<String, Object> settingPay(String paypwd,String returnUrl) {
        Map<String, Object> map = new HashMap<>();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        if (StringUtils.isBlank(userId)) {
            map.put("result", "0");
            map.put("message", "userId不能为空!");
            return map;
        } else {
            Message message = null;
            User user = new User();
            try {
                if (StringUtils.isBlank(paypwd)) {
                    map.put("result", "0");
                    map.put("message", "设置失败,支付密码不能为空!");
                    return map;
                }
                user.setUserId(userId);
                user.setPayPassword(DigestUtils.md5Hex(paypwd));
                userService.updatePayByUserId(user);
                map.put("result", "1");
                map.put("message", "支付密码设置成功!");
            } catch (Exception e) {
                logger.info("设置支付密码失败:" + e.getMessage());
                map.put("result", "0");
                map.put("message", "支付密码设置失败!");
            }
            map.put("returnUrl",returnUrl);
        }
        return map;
    }

    /**
     * 修改支付密码
     *
     * @param oldpaypwd
     * @param paypwd
     * @param userId
     * @return
     */
    @RequestMapping("updatePay")
    @ResponseBody
    public Map<String, Object> updatePay(String oldpaypwd, String paypwd) {
        Map<String, Object> map = new HashMap<>();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        if (StringUtils.isBlank(userId)) {
            map.put("result", "0");
            map.put("message", "userId不能为空!");
        } else {
            User user = new User();
            try {
                if (StringUtils.isBlank(oldpaypwd)) {
                    map.put("result", "0");
                    map.put("message", "修改失败,老密码不能为空!");
                    return map;
                }
                if (StringUtils.isBlank(paypwd)) {
                    map.put("result", "0");
                    map.put("message", "修改失败,新密码不能为空!");
                    return map;
                }
                User old = userService.selectById(userId);
                if (!DigestUtils.md5Hex(oldpaypwd).equals(old.getPayPassword())) {
                    map.put("result", "0");
                    map.put("message", "修改失败,老密码不正确!");
                    return map;
                }
                user.setUserId(userId);
                user.setPayPassword(DigestUtils.md5Hex(paypwd));
                System.out.print(DigestUtils.md5Hex(paypwd));
                userService.updatePayByUserId(user);
                map.put("result", "1");
                map.put("message", "支付密码修改成功!");
            } catch (Exception e) {
                logger.info("修改支付密码失败:" + e.getMessage());
                map.put("result", "0");
                map.put("message", "支付密码修改失败!");
            }
        }
        return map;
    }

    /**
     * 从设置页面回到我的页面
     *
     * @return
     */
    @RequestMapping("/set_to_my")
    @ResponseBody
    public ModelAndView set_to_my() {
        ModelAndView model = new ModelAndView("/my/my");
        return model;
    }

    /**
     * 跳转到手机号找回密码页面
     *
     * @return
     */
    @RequestMapping("/findPwd")
    @ResponseBody
    public ModelAndView findPwd() {
        ModelAndView model = new ModelAndView("/my/findPwd1");
        return model;
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/tologin")
    @ResponseBody
    public ModelAndView tologin() {
        ModelAndView model = new ModelAndView("/wx/login");
        return model;
    }

    /**
     * 跳转到找回密码的第二步
     *
     * @return
     */
    @RequestMapping("/findPwdToSecond")
    @ResponseBody
    public ModelAndView findPwdToSecond(String phone, String code, String coun_code) {
        ModelAndView model = new ModelAndView("/my/findPwd2");
        model.addObject("phone", phone);
        model.addObject("code", code);
        model.addObject("coun_code", coun_code);
        return model;
    }

    /**
     * 重新设置登录密码
     *
     * @return
     */
    @RequestMapping("/findPwdbyphone")
    @ResponseBody
    public Message findPwdbyphone(String phone, String code, String newpwd, String countrise_code) {
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
        return MessageUtils.getSuccess("登录密码重置成功!");
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(String code, String phone) {
        String result = "0";
        String mobile_code = redisService.getValue("message_code_" + phone);
        if (code == null) {
            code = "";
        }
        if (!mobile_code.equals(code)) {
            result = "0";
        } else {
            result = "1";
        }
        return result;
    }

    /**
     * 跳转到找回支付密码的页面
     */
    @RequestMapping("/findPayPage")
    @ResponseBody
    public ModelAndView findPayPage() {
        ModelAndView model = new ModelAndView("/my/findPayPwd1");
        return model;
    }

    /**
     * 跳转到找回支付密码的第二步
     *
     * @return
     */
    @RequestMapping("/findPayPwdToSecond")
    @ResponseBody
    public ModelAndView findPayPwdToSecond(String phone, String code, String coun_code) {
        ModelAndView model = new ModelAndView("/my/findPayPwd2");
        model.addObject("phone", phone);
        model.addObject("code", code);
        model.addObject("coun_code", coun_code);
        return model;
    }

    /**
     * 重新设置支付密码
     *
     * @return
     */
    @RequestMapping("/findPayPwdbyphone")
    @ResponseBody
    public Message findPayPwdbyphone(String phone, String code, String newpwd, String countrise_code) {
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
        return MessageUtils.getSuccess("支付密码重置成功!");
    }

    /**
     * 报告页面获取个人信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String,Object> getUserInfo(){
        Map<String,Object> map = new HashMap<>();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        User user1 = userService.selecUserDetail(userId);
        if(null == user1){
            map.put("result","0");
            map.put("message","没有此用户信息!");
            return map;
        }
        String ccStr = "";
        try {
            ccStr = java.net.URLDecoder.decode(user1.getSignature(), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        user1.setSignature(ccStr);
        map.put("result","1");
        map.put("message","success");
        map.put("data",user1);
        return map;
    }

    /**
     * 跳转到我的钱包页面
     */
    @RequestMapping("/myBalance")
    @ResponseBody
    public ModelAndView myBalance() {
        ModelAndView model = new ModelAndView("/my/myPurse");
        return model;
    }

    /**
     * 判断个人资料是否填写完整
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/is_impl")
    public Message is_impl(){
        Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
        if (StringUtils.isBlank(userId)){
          message = MessageUtils.getFail("用户Id为空!");
        }
        User user = userService.selecUserDetail(userId);
        if (null == user){
            message = MessageUtils.getFail("用户不存在!");
        }else{
            if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getCountries()) || StringUtils.isBlank(user.getSignature())) {
                user.setIs_complet(0);
            } else {
                user.setIs_complet(1);
            }
        }
        message.setData(user);
        return message;
    }

    /**
     * 获取个人模块三个数值
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_num")
    public Message getUserDetail(){
       Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user1 = getCurrUser();
        if(user1 != null){
            userId = user1.getUserId();
        }
       if(StringUtils.isBlank(userId)){
           message  = MessageUtils.getFail("你还没有登录!");
           return message;
       }
        User user = userService.selecUserDetail(userId);
        Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

        Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

        user.setMkNum(mkNumVal==null?0:mkNumVal);
        user.setMsNum(msNumVal==null?0:msNumVal);
        User myfocus = userService.selecUserDetail(userId);//我的关注的数量
        user.setMyFocuNumber(myfocus.getMyFocuNumber());
        if(null != user){
            message  = MessageUtils.getSuccess("success");
        }else
        {
            message  = MessageUtils.getFail("没有此用户的信息!");
        }
        message.setData(user);
        return message;
    }
    /**
     * 我的关注页面
     * @return
     */
    @RequestMapping("/my/myfollow")
    public ModelAndView myfollow() {
        ModelAndView view = new ModelAndView("/my/myfollow");
        return view;
    }
    /**
     * 我的关注-关注我的与我的关注
     * @param pageNum
     * @param pageSize
     * @param type 查询类型0：关注我的，1：我的关注
     * @return
     */
    @PostMapping("/selectMyFocusTeachers")
    @ResponseBody
    public Message<List<UserTeacherFocus>> selectMyFocusTeachers(String pageNum, String pageSize, String type) {

        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
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

    /**
     * 邀请好友页面
     * @return
     */
    @RequestMapping("/my/please")
    public ModelAndView please() {
        ModelAndView view = new ModelAndView("/my/please");
        return view;
    }
    /**
     * 邀请好友-获取海报分享图片
     * @return
     */
    @RequestMapping(value = "/getPosterShareImages", method = RequestMethod.POST)
    @ResponseBody
    public Message getPosterShareImages() {

        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
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



