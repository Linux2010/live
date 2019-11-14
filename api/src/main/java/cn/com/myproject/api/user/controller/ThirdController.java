package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.api.util.TokenProcessor;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jyp on 2017/9/19 0019.
 */
@RestController
@RequestMapping("/api/third/login")
@Api(value="第三方登录类",tags = "第三方登录")
public class ThirdController {

    private static final Logger logger = LoggerFactory.getLogger(ThirdController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ICourseService courseService;


    /**
     * qq登录
     * @param openId
     * @return
     */
    @RequestMapping(value="/qq_login",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "qq登录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "openId", value = "openId", required = true, dataType = "String",defaultValue = "")
    })
    public Message qqlogin(String openId){
        if(StringUtils.isBlank(openId)){
            return MessageUtils.getFail("openId Is Null");
        }
        User user = userService.getUserByOpenId(openId);
        if(null == user){
            return MessageUtils.getFail("该QQ暂未绑定账号，是否去绑定QQ?");
        }
        String is_pay;
        if( user.getPayPassword() == null || user.getPayPassword() == ""){
            is_pay = "0";
        }else{
            is_pay = "1";
        }
        //缓存处理
        if(user.getStatus() == 1) {

            String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
            String token = TokenProcessor.getInstance().generateToken(seed,true);
            long timeout = 24*60*14;//后续从缓存取系统配置时间
            //限制一个账号只能登录一端
            String _token = redisService.getValue(seed);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                redisService.delete(_token);
            }

            redisService.setValue(seed,token,timeout);
            user.setToken(token);
            String userStr = JSON.toJSONString(user);
            redisService.setValue(token,userStr,timeout);

            Message message = MessageUtils.getSuccess("success");
            Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

            Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

            user.setMkNum(mkNumVal==null?0:mkNumVal);
            user.setMsNum(msNumVal==null?0:msNumVal);
            user.setIs_pay(is_pay);
            message.setData(user);
            return message;
        }else{
            //FIXME 状态等待扩展
            return MessageUtils.getFail("登录失败,用户状态不对");
        }
    }


    /**
     *绑定扣扣
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value="/user_bind_qq",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "绑定扣扣", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "loginName", value = "登录名", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "password", value = "登录密码", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "countrise_code", value = "国家区号", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "openId", value = "openId", required = true, dataType = "String",defaultValue = "")
    })
        public Message user_bind_qq(String countrise_code,String loginName,String password,String openId){

        Message message = MessageUtils.getSuccess("success");
        if(StringUtils.isBlank(loginName)){
            return MessageUtils.getFail("请输入登录名");
        }
        if(StringUtils.isBlank(password)){
            return MessageUtils.getFail("请输入密码");
        }
        if(StringUtils.isBlank(openId)){
            return MessageUtils.getFail("open不能为空");
        }
        Map<String,Object> loginMap = userService.login(countrise_code,loginName,DigestUtils.md5Hex(password));
        User user = new User();
        if("success".equals(loginMap.get("status"))){
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            return MessageUtils.getFail(String.valueOf(loginMap.get("message")));
        }
        if(null == user) {
            return MessageUtils.getFail("登录失败,用户不存在");
        }else{
            if(user.getOpenId() == null || user.getOpenId() == "" || user.getOpenId().equals("")){
                User uu = new User();
                uu.setUserId(user.getUserId());
                uu.setOpenId(openId);
                try{
                    if(userService.bindQQ(uu) != 1){
                        return MessageUtils.getFail("绑定失败");
                    }
                    String is_pay;
                    if( user.getPayPassword() == null || user.getPayPassword() == ""){
                        is_pay = "0";
                    }else{
                        is_pay = "1";
                    }
                    //缓存处理
                    if(user.getStatus() == 1) {

                        String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
                        String token = TokenProcessor.getInstance().generateToken(seed,true);
                        long timeout = 24*60*14;//后续从缓存取系统配置时间
                        //限制一个账号只能登录一端
                        String _token = redisService.getValue(seed);
                        if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                            redisService.delete(_token);
                        }

                        redisService.setValue(seed,token,timeout);
                        user.setToken(token);
                        String userStr = JSON.toJSONString(user);
                        redisService.setValue(token,userStr,timeout);

                        Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

                        Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

                        user.setMkNum(mkNumVal==null?0:mkNumVal);
                        user.setMsNum(msNumVal==null?0:msNumVal);
                        user.setIs_pay(is_pay);
                        message.setData(user);
                    }else{
                        //FIXME 状态等待扩展
                        return MessageUtils.getFail("登录失败,用户状态不对");
                    }
                }catch (Exception e){
                    logger.error("绑定扣扣，失败:"+e);
                    e.printStackTrace();
                }
            }else{
                return MessageUtils.getFail("你已经绑定过QQ");
            }
        }
        return message;
    }

    @PostMapping(value = "/wxLogin")
    @ApiOperation(value = "APP微信快捷登录", produces = "application/json")
    @ApiImplicitParam(paramType = "query", name = "wxOpenId", value = "微信openId",
            required = true, dataType = "String", defaultValue = "")
    public Message wxLogin(String wxOpenId){

        try {
            if (StringUtils.isBlank(wxOpenId)){
                return MessageUtils.getFail("微信openId不能为空！");
            }
            User user = userService.getUserByWxOpenId(wxOpenId);
            if (null == user){
                return MessageUtils.getFail("该微信暂未绑定账号，是否去绑定微信?");
            }
            String is_pay;
            if( user.getPayPassword() == null || user.getPayPassword() == ""){
                is_pay = "0";
            }else{
                is_pay = "1";
            }
            //缓存处理
            if(user.getStatus() == 1) {

                String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
                String token = TokenProcessor.getInstance().generateToken(seed,true);
                long timeout = 24*60*14;//后续从缓存取系统配置时间
                //限制一个账号只能登录一端
                String _token = redisService.getValue(seed);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                    redisService.delete(_token);
                }

                redisService.setValue(seed,token,timeout);
                user.setToken(token);
                String userStr = JSON.toJSONString(user);
                redisService.setValue(token,userStr,timeout);

                Message message = MessageUtils.getSuccess("success");
                Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

                Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

                user.setMkNum(mkNumVal==null?0:mkNumVal);
                user.setMsNum(msNumVal==null?0:msNumVal);
                user.setIs_pay(is_pay);
                message.setData(user);
                return message;
            }else{
                //FIXME 状态等待扩展
                return MessageUtils.getFail("登录失败,用户状态不对");
            }
        }catch (RuntimeException e){
            return MessageUtils.getFail("登录失败！");
        }
    }

    @PostMapping(value = "/binWx")
    @ApiOperation(value = "微信绑定", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "loginName", value = "登录名", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "password", value = "登录密码", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "countrise_code", value = "国家区号", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "wxOpenId", value = "wxOpenId", required = true, dataType = "String",defaultValue = "")
    })
    public Message binWx(String countrise_code,String loginName,String password,String wxOpenId){

        Message message = MessageUtils.getSuccess("success");
        if(StringUtils.isBlank(loginName)){
            return MessageUtils.getFail("请输入登录名");
        }
        if(StringUtils.isBlank(password)){
            return MessageUtils.getFail("请输入密码");
        }
        if(StringUtils.isBlank(wxOpenId)){
            return MessageUtils.getFail("微信openId不能为空");
        }
        Map<String,Object> loginMap = userService.login(countrise_code,loginName,DigestUtils.md5Hex(password));
        User user = new User();
        if("success".equals(loginMap.get("status"))){
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            return MessageUtils.getFail(String.valueOf(loginMap.get("message")));
        }
        if(null == user) {
            return MessageUtils.getFail("登录失败,用户不存在");
        }else{
            if(StringUtils.isBlank(user.getWxOpenId())){
                User uu = new User();
                uu.setUserId(user.getUserId());
                uu.setWxOpenId(wxOpenId);
                try{
                    if(userService.bindWx(uu) != 1){
                        return MessageUtils.getFail("绑定失败");
                    }
                    String is_pay;
                    if( user.getPayPassword() == null || user.getPayPassword() == ""){
                        is_pay = "0";
                    }else{
                        is_pay = "1";
                    }
                    //缓存处理
                    if(user.getStatus() == 1) {

                        String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
                        String token = TokenProcessor.getInstance().generateToken(seed,true);
                        long timeout = 24*60*14;//后续从缓存取系统配置时间
                        //限制一个账号只能登录一端
                        String _token = redisService.getValue(seed);
                        if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                            redisService.delete(_token);
                        }

                        redisService.setValue(seed,token,timeout);
                        user.setToken(token);
                        String userStr = JSON.toJSONString(user);
                        redisService.setValue(token,userStr,timeout);


                        Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

                        Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

                        user.setMkNum(mkNumVal==null?0:mkNumVal);
                        user.setMsNum(msNumVal==null?0:msNumVal);
                        user.setIs_pay(is_pay);
                        message.setData(user);
                    }else{
                        //FIXME 状态等待扩展
                        return MessageUtils.getFail("登录失败,用户状态不对");
                    }
                }catch (Exception e){
                    logger.error("绑定微信，失败:"+e);
                    e.printStackTrace();
                }
            }else{
                return MessageUtils.getFail("你已经绑定过微信");
            }
        }
        return message;
    }

    /**
     * 微博登录
     * @param weiboOpenId
     * @return
     */
    @RequestMapping(value="/weibo_login",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "微博登录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "weiboOpenId", value = "weiboOpenId", required = true, dataType = "String",defaultValue = "")
    })
    public Message weibologin(String weiboOpenId){
        if(StringUtils.isBlank(weiboOpenId)){
            return MessageUtils.getFail("weiboOpenId Is Null");
        }
        User user = userService.getUserByWeiboOpenId(weiboOpenId);
        if(null == user){
            return MessageUtils.getFail("该微博暂未绑定账号，是否去绑定微博?");
        }
        String is_pay;
        if( user.getPayPassword() == null || user.getPayPassword() == ""){
            is_pay = "0";
        }else{
            is_pay = "1";
        }
        //缓存处理
        if(user.getStatus() == 1) {

            String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
            String token = TokenProcessor.getInstance().generateToken(seed,true);
            long timeout = 24*60*14;//后续从缓存取系统配置时间
            //限制一个账号只能登录一端
            String _token = redisService.getValue(seed);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                redisService.delete(_token);
            }

            redisService.setValue(seed,token,timeout);
            user.setToken(token);
            String userStr = JSON.toJSONString(user);
            redisService.setValue(token,userStr,timeout);

            Message message = MessageUtils.getSuccess("success");
            Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

            Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

            user.setMkNum(mkNumVal==null?0:mkNumVal);
            user.setMsNum(msNumVal==null?0:msNumVal);
            user.setIs_pay(is_pay);
            message.setData(user);
            return message;
        }else{
            //FIXME 状态等待扩展
            return MessageUtils.getFail("登录失败,用户状态不对");
        }
    }

    /**
     *绑定微博
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value="/user_bind_weibo",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "绑定微博", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "loginName", value = "登录名", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "password", value = "登录密码", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "countrise_code", value = "国家区号", required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "weiboOpenId", value = "weiboOpenId", required = true, dataType = "String",defaultValue = "")
    })
    public Message user_bind_weibo(String countrise_code,String loginName,String password,String weiboOpenId){

        Message message = MessageUtils.getSuccess("success");
        if(StringUtils.isBlank(loginName)){
            return MessageUtils.getFail("请输入登录名");
        }
        if(StringUtils.isBlank(password)){
            return MessageUtils.getFail("请输入密码");
        }
        if(StringUtils.isBlank(weiboOpenId)){
            return MessageUtils.getFail("weiboOpenId不能为空");
        }
        Map<String,Object> loginMap = userService.login(countrise_code,loginName,DigestUtils.md5Hex(password));
        User user = new User();
        if("success".equals(loginMap.get("status"))){
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            return MessageUtils.getFail(String.valueOf(loginMap.get("message")));
        }
        if(null == user) {
            return MessageUtils.getFail("登录失败,用户不存在");
        }else{
            if(user.getWeiboOpenId() == null || user.getWeiboOpenId() == "" || user.getWeiboOpenId().equals("")){
                User uu = new User();
                uu.setUserId(user.getUserId());
                uu.setWeiboOpenId(weiboOpenId);
                try{
                    if(userService.bindWeiBo(uu) != 1){
                        return MessageUtils.getFail("绑定失败");
                    }
                    String is_pay;
                    if( user.getPayPassword() == null || user.getPayPassword() == ""){
                        is_pay = "0";
                    }else{
                        is_pay = "1";
                    }
                    //缓存处理
                    if(user.getStatus() == 1) {

                        String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
                        String token = TokenProcessor.getInstance().generateToken(seed,true);
                        long timeout = 24*60*14;//后续从缓存取系统配置时间
                        //限制一个账号只能登录一端
                        String _token = redisService.getValue(seed);
                        if(org.apache.commons.lang3.StringUtils.isNotBlank(_token)) {
                            redisService.delete(_token);
                        }

                        redisService.setValue(seed,token,timeout);
                        user.setToken(token);
                        String userStr = JSON.toJSONString(user);
                        redisService.setValue(token,userStr,timeout);


                        Integer mkNumVal = courseService.searchMyCourseCount(user.getUserId());// 得到我的课程数量

                        Integer msNumVal = courseService.searchMyCcCount(user.getUserId());// 得到我的收藏数量

                        user.setMkNum(mkNumVal==null?0:mkNumVal);
                        user.setMsNum(msNumVal==null?0:msNumVal);
                        user.setIs_pay(is_pay);
                        message.setData(user);
                    }else{
                        //FIXME 状态等待扩展
                        return MessageUtils.getFail("登录失败,用户状态不对");
                    }
                }catch (Exception e){
                    logger.error("绑定微博，失败:"+e);
                    e.printStackTrace();
                }
            }else{
                return MessageUtils.getFail("你已经绑定过微博");
            }
        }
        return message;
    }
}
