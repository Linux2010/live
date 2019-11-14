package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.IAdvertiseService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.user.IUserSignInService;
import cn.com.myproject.api.util.CookieUtil;
import cn.com.myproject.api.util.DateUtils;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.Advertise;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserSignIn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSG on 2017/9/5 0005.
 */
@RequestMapping("/wap")
@Controller
public class IndexController extends BaseController{

    @Autowired
    private IAdvertiseService advertiseService;

    @Autowired
    private IUserSignInService userSignInService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        int userIdentity = 0;
        User user = getCurrUser();
        ModelAndView view = new ModelAndView("index");
        if (user != null){
            user = userService.selectById(user.getUserId());
            userIdentity = user.getUserIdentity();
            view.addObject("userIdentity", userIdentity);
        }



        List<Advertise> advertiseList = advertiseService.selectAdverType(1, 1);
        view.addObject("advertiseList", advertiseList);
//        if(StringUtils.isNotBlank(CookieUtil.getCookie("userId",request))){
//            CookieUtil.setCookie("userId",CookieUtil.getCookie("userId",request),response);
//        }
//        if(StringUtils.isNotBlank(CookieUtil.getCookie("userName",request))){
//            CookieUtil.setCookie("userName",CookieUtil.getCookie("userName",request),response);
//        }
//        if(StringUtils.isNotBlank(CookieUtil.getCookie("userPhoto",request))){
//            CookieUtil.setCookie("userPhoto",CookieUtil.getCookie("userPhoto",request),response);
//        }
//        if(StringUtils.isNotBlank(CookieUtil.getCookie("userFx",request))){
//            CookieUtil.setCookie("userFx",CookieUtil.getCookie("userFx",request),response);
//        }
//        if(StringUtils.isNotBlank(CookieUtil.getCookie("userToken",request))){
//            CookieUtil.setCookie("userToken",CookieUtil.getCookie("userToken",request),response);
//        }
        String payFlagStr = request.getParameter("payFlag");
        if(StringUtils.isNotBlank(payFlagStr)){
            if("1".equals(payFlagStr)){
                request.getSession().setAttribute("userIdentity",2);
            }
        }
        return view;
    }

    @RequestMapping(value = "/qrCode")
    public ModelAndView qrCode(){

        ModelAndView view = new ModelAndView("wap/download");
        return view;
    }

    //用户打卡签到页面
    @RequestMapping(value = "/sign")
    public ModelAndView sign(){

        ModelAndView view = new ModelAndView("/home/sign");
        return view;
    }

    //用户签到
    @RequestMapping("/signIn")
    @ResponseBody
    public Message signIn() throws ParseException {

        String userId = "";
        User user = getCurrUser();
        if (user != null){
            userId = user.getUserId();
        }
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


    //判断用户是否签到
    @RequestMapping(value = "/isSignIn")
    @ResponseBody
    public Message isSignIn(String userId) throws ParseException{
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

    @RequestMapping(value = "/ceshi")
    public ModelAndView ceshi(){

        ModelAndView view = new ModelAndView("/my/ceshi");
        return view;
    }
}