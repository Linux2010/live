package cn.com.myproject.api.wap;

import cn.com.myproject.api.config.Constants;
import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.ISendMessageService;
import cn.com.myproject.api.service.ISysRegionService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.user.controller.RegisterController;
import cn.com.myproject.api.util.*;
import cn.com.myproject.sysuser.entity.PO.SysRegion;
import cn.com.myproject.user.entity.Constant;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LeiJia on 2017/9/6 0005.
 */
@Controller
@RequestMapping("/wap/wx")
public class WXLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ISendMessageService sendMessageService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ISysRegionService sysRegionService;

    private static final String SMS_PREFIX = "【教头学院】";

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    @RequestMapping("/login")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/wap/login");
        view.addObject("message","");
        return view;
    }

    @RequestMapping("/zhuce")
    public ModelAndView zhuce(){
        ModelAndView view = new ModelAndView("/wap/zhuce");
        return view;
    }
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("userPhoto");
        request.getSession().removeAttribute("userFx");
        request.getSession().removeAttribute("userToken");
        request.getSession().removeAttribute("userIdentity");
        CookieTool.clear(request,response,"/");
        return new ModelAndView("/wap/login");
    }

    /**
     * 用户登录
     *
     * @param phoneNum
     * @param password
     * @param counttriesCode
     * @return
     */
    @RequestMapping("/userLogin")
    public ModelAndView userLogin(String phoneNum, String password, String counttriesCode, HttpServletResponse response, HttpServletRequest request,int flagVal){
        ModelAndView view = new ModelAndView();
        if(StringUtils.isBlank(phoneNum)){
            view.addObject("message","手机号不能为空");
            view.setViewName("/wap/login");
            return view;
        }
        if(StringUtils.isBlank(password)){
            view.addObject("message","密码不能为空");
            view.setViewName("/wap/login");
            return view;
        }
        if(flagVal != 1){
            password = DigestUtils.md5Hex(password);
        }
        Map<String,Object> loginMap = userService.login(counttriesCode,phoneNum,password);
        User user = new User();
        if("success".equals(loginMap.get("status"))){
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            view.addObject("message",loginMap.get("message"));
            view.setViewName("/wap/login");
            return view;
        }
        if(null == user) {
            view.addObject("message","登录失败,用户不存在");
            view.setViewName("/wap/login");
            return view;
        }
        String is_pay;
        if( user.getPayPassword() == null || user.getPayPassword() == ""){
            is_pay = "0";
        }else{
            is_pay = "1";
        }
        if(StringUtils.isBlank(user.getQrCodeUrl())){
            user.setQrCodeUrl(jtxyappUrl + "/api/register/showShare?userId="+user.getUserId());
            user.setQrCodeUrl( userService.createQrCode(user.getQrCodeUrl(),user.getUserId()));
            User updateUser = new User();
            updateUser.setQrCodeUrl(user.getQrCodeUrl());
            updateUser.setUserId(user.getUserId());
            userService.updateUser(updateUser);
        }
        user.setQrCodePage(jtxyappUrl + "/api/login/showQrCodePage?qrCodeUrl="+user.getQrCodeUrl()+"&userName="+user.getUserName());
        // 缓存处理
        if(user.getStatus() == 1) {


            String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
            String token = request.getSession().getId();
            long timeout = 24*60*14;//后续从缓存取系统配置时间
            //限制一个账号只能登录一端
            String _token = redisService.getValue(seed);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                redisService.delete(_token);
            }
            CookieUtil.setCookie(Constants.SESSION_ID,token,response);
            redisService.setValue(seed,token,timeout);
            user.setToken(token);
            String userStr = JSON.toJSONString(user);
            redisService.setValue(token,userStr,timeout);

            user.setIs_pay(is_pay);
            if(StringUtils.isBlank(user.getQrCodePage())){
                user.setQrCodePage("");
            }
            if(StringUtils.isBlank(user.getToken())){
                user.setToken("");
            }

//            // 设置用户ID到cookie中
//            CookieUtil.setCookie("userId",user.getUserId(),response);
//
//            // 设置用户名到cookie中
//            CookieUtil.setCookie("userName",user.getUserName(),response);
//
//            // 设置用户头像到cookie中
//            CookieUtil.setCookie("userPhoto",user.getPhoto(),response);
//
//            // 设置用户分享路径到cookie中
//            CookieUtil.setCookie("userFx",user.getQrCodePage(),response);
//
//            // 设置用户token路径到cookie中
//            CookieUtil.setCookie("userToken",user.getToken(),response);

           // view.setViewName("index");
            view.setViewName("/my/my");

            Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

            Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量


            User myfocus = userService.selecUserDetail(user.getUserId());//我的关注的数量

            request.getSession().setAttribute("userId",user.getUserId());
            request.getSession().setAttribute("userName",user.getUserName());
            request.getSession().setAttribute("userPhoto",user.getPhoto());
            request.getSession().setAttribute("userFx",user.getQrCodePage());
            request.getSession().setAttribute("userToken",user.getUserName());
            request.getSession().setAttribute("userIdentity",user.getUserIdentity());
            //request.getSession().setAttribute("mkNumVal",mkNumVal);
           // request.getSession().setAttribute("msNumVal",msNumVal);
           // request.getSession().setAttribute("myFocus",myfocus.getMyFocuNumber());
            request.getSession().setAttribute("userType",user.getUserType());
            return view;

        }

        return view;

    }

    /**
     * 微信用户注册
     *
     * @param counttriesCode
     * @param phone
     * @param code
     * @param password
     * @param userName
     * @param request
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    @ApiOperation(value = "微信用户注册", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "counttriesCode",value = "区号",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "phone",value = "手机号",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "code",value = "验证码",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "password",value = "密码",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "userName",value = "用户名",
                    required = true, dataType = "String",defaultValue = "")
    })
    public Message addUser(String counttriesCode, String phone, String code, String password, String userName, HttpServletRequest request, HttpServletResponse response){
        Message message = null;
        if(StringUtils.isBlank(phone)){
            message = MessageUtils.getSuccess("手机号码不能为空");
            message.setData(0);
            return message;
        }
        if(StringUtils.isNotBlank(phone)) {
            if (userService.checkCodeAndPhone(counttriesCode,phone) >= 1) {
                message = MessageUtils.getSuccess("此手机号已注册");
                message.setData(0);
                return message;
            }
        }
        if(StringUtils.isBlank(userName)){
            message = MessageUtils.getSuccess("用户名不能为空");
            message.setData(0);
            return message;
        }
        if(StringUtils.isNotBlank(userName)){
            if(userService.checkLoginName(userName) == 1){
                message = MessageUtils.getSuccess("用户名已经存在");
                message.setData(0);
                return message;
            }
        }
        if(!code.equals(redisService.getValue("message_code_register"+phone))){
            message = MessageUtils.getSuccess("你输入的验证码不正确");
            message.setData(0);
            return message;
        }
        if(StringUtils.isBlank(password)){
            message = MessageUtils.getSuccess("密码不能为空");
            message.setData(0);
            return message;
        }
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setLoginName(userName);
        user.setUserName(userName);
        user.setRealName(userName);
        user.setNickName(userName);
        user.setCountryCode(counttriesCode);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setPhone(phone);
        user.setQrCodeUrl(jtxyappUrl + "/api/register/showShare?userId="+user.getUserId());
        user.setPhoto("http://appphoto.doers.cn/f8e09d3b-d32a-420c-a06b-9caf4d7eb58b.png");// 默认一个头像
        userService.reigister(user);
        //记录分润上下级关系
        int addCountVal = userService.addShareUser("",user.getUserId());
        if(addCountVal > 0){
            message = MessageUtils.getSuccess("注册成功");
            message.setData(1);
            userLogin(phone, user.getPassword(), user.getCountryCode(), response, request,1);
        }else{
            message = MessageUtils.getSuccess("注册失败");
            message.setData(0);
        }
        return message;
    }

    /**
     * 获取验证码
     *
     * @param phone
     * @param countries_code
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/sendCode",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "获取验证码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "countries_code", value = "区号", required = true, dataType = "String")
    })
    public Message sendCode(String phone,String countries_code){
        if(StringUtils.isBlank(phone)){
            return MessageUtils.getFail("发送失败,手机号码不能为空!");
        }
        String mobileCode = "" + (int) ((Math.random() * 9 + 1) * 100000);
        String content = SMS_PREFIX + "您好，我是小师妹，您的注册验证码：" + mobileCode + "。快去学习吧。如非本人操作请忽略此信息。";
        try{
            if(StringUtils.isNotBlank(countries_code)){
                if(countries_code.equals("86")){//发送国内短信
                    sendMessageService.sendChina(phone,content);
                }else{//发送国外短信
                    String comphone = countries_code+phone;//区号和手机号拼接
                    sendMessageService.sendInternation(comphone,content);
                }
            }
        }catch(Exception e) {
            logger.error("验证码发送失败",e);
            return  MessageUtils.getFail("fail");
        }
        String message_code_register_ = "message_code_register"+phone;
        long timeout = 5*60;
        boolean seedSign = redisService.setValue(message_code_register_,mobileCode,timeout);
        if(seedSign){
            redisService.setValue(message_code_register_,mobileCode,timeout);
        }
        System.out.print(redisService.getValue("message_code_register"+phone));
        return MessageUtils.getSuccess("success");
    }

    @RequestMapping("/registerAndLogin")
    public ModelAndView registerAndLogin(String phoneNum,HttpServletResponse response, HttpServletRequest request){
        User user = userService.selectUserByPhoneNum(phoneNum);
        return userLogin(phoneNum, user.getPassword(), user.getCountryCode(), response, request,1);
    }

    //获取区号
    @RequestMapping("/countryNumber")
    @ResponseBody
    public List<SysRegion> countryNumber(){

        List<SysRegion> sysRegionList = sysRegionService.select_countries_code();
        return sysRegionList;
    }

    @RequestMapping("/judgmentThePublicOpenId")
    @ResponseBody
    public String judgmentThePublicOpenId(){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String result = userService.selectThePublicOpenId(userId);
        if (StringUtils.isBlank(result)){
            result = "";
        }
        return result;
    }

    /**
     * 检测用户的手机号
     *
     * @param cCode
     * @param phone
     * @return
     */
    @RequestMapping("/checkUserPhone")
    @ResponseBody
    public String checkUserPhone(String cCode,String phone){
        String resultStr = "0";
        if (userService.checkCodeAndPhone(cCode,phone) >= 1) {
            resultStr = "1";
        }
        return resultStr;
    }

    /**
     * 检测用户的用户名
     *
     * @param userName
     * @return
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(String userName){
        String resultStr = "0";
        if (userService.checkLoginName(userName) >= 1) {
            resultStr = "1";
        }
        return resultStr;
    }

}