package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.api.util.TokenProcessor;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/27.
 */
@RestController
@RequestMapping("/api/login")
@Api(value="登录类",tags = "登录")
public class LoginController {


    @Autowired
    private IUserService userService;

    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ICourseService courseService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;


    @Value("${server.context-path}")
    private String contextPath;

    @PostMapping(value="/testfile", consumes = "multipart/form-data")
    public String demofile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String filename = file.getName();
        return "{\"status\":200}";
    }


    @RequestMapping(value="/",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "用户名密码登录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "code", value = "区号", required = true, dataType = "String",defaultValue = "86"),
            @ApiImplicitParam(paramType="query",name = "loginName", value = "登录名", required = true, dataType = "String",defaultValue = "test000"),
            @ApiImplicitParam(paramType="query",name = "password", value = "登录密码", required = true, dataType = "String",defaultValue = "111111")
    })
    public Message login(@RequestParam String code,@RequestParam String loginName,@RequestParam  String password,HttpSession httpSession,HttpServletRequest request) {

        String codeVal = "86";

        if(code != null && !"".equals(code)) {
            codeVal = code;
        }

        if(StringUtils.isBlank(loginName)) {
            return MessageUtils.getFail("登录失败，登录名不能为空");
        }

        if(StringUtils.isBlank(password)) {
            return MessageUtils.getFail("登录失败，密码不能为空");
        }

        Map<String,Object> loginMap = userService.login(codeVal,loginName,DigestUtils.md5Hex(password));
        User user;
        if("success".equals(loginMap.get("status"))) {
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            return MessageUtils.getFail(String.valueOf(loginMap.get("message")));
        }

        if(null == user) {
            return MessageUtils.getFail("登录失败,用户不存在");
        }
        String is_pay = "1";

        if(StringUtils.isBlank(user.getPayPassword())) {
            is_pay = "0";
        }

        if(user.getQrCodeUrl() != null && !"".equals(user.getQrCodeUrl())) {
            user.setQrCodePage(jtxyappUrl + "/api/login/showQrCodePage?qrCodeUrl="+user.getQrCodeUrl()+"&userName="+user.getUserName());
        }

        //缓存处理
        if(user.getStatus() == 1) {

            String seed = DigestUtils.md5Hex(user.getCountryCode()+user.getLoginName());
            String token = TokenProcessor.getInstance().generateToken(seed,true);
            long timeout = 24*60*14;//后续从缓存取系统配置时间

            user.setToken(token);
            String userStr = JSON.toJSONString(user);

            //限制一个账号只能登录一端
            String _token = redisService.getValue(seed);
            if(StringUtils.isNotBlank(_token)) {
                redisService.delete(_token);
            }

            redisService.setValue(seed,token,timeout);

            redisService.setValue(token,userStr,timeout);

            Message message = MessageUtils.getSuccess("登录成功");
            //FIXME 改进

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
     * 展示二维码页面
     *
     * @param qrCodeUrl
     * @param userName
     * @return
     */
    @GetMapping("/showQrCodePage")
    @ApiOperation(value = "展示二维码页面，APP不可使用", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "qrCodeUrl",value = "二维码路径",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "userName",value = "用户名",
                    required = true, dataType = "String",defaultValue = "")
    })
    public ModelAndView showQrCodePage(String qrCodeUrl,String userName){
        ModelAndView view = new ModelAndView("/user/qrCode");
        view.addObject("userName",userName);
        view.addObject("qrCodeUrl",qrCodeUrl);
        return view;
    }

}