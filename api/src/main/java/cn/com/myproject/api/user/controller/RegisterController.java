package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.ICourseTopicService;
import cn.com.myproject.api.service.ISendMessageService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.api.util.TokenProcessor;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import cn.com.myproject.user.entity.PO.User;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by liyang-macbook on 2017/6/30.
 */
@RestController
@RequestMapping("/api/register")
@Api(value="注册类",tags = "用户注册")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ISendMessageService sendMessageService;

    @Autowired
    private ICourseTopicService courseTopicService;

    @Autowired
    private IRedisService redisService;
    /**
     * 短信验证码前缀
     */
    private static final String SMS_PREFIX = "【教头学院】";

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     * 展示注册页面
     *
     * @param userId
     * @return
     */
    @GetMapping("/showShare")
    @ApiOperation(value = "根据用户ID分享用户注册页面，APP不可使用", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "ss2")
    })
    public ModelAndView showShare(String userId){
       ModelAndView view = new ModelAndView("/user/share");
       User user = userService.selecUserDetail(userId);
       String photoUrl = "";
       String userNameVal = "";
       if(user != null){
           photoUrl = user.getPhoto();
           if(photoUrl == null || "".equals(photoUrl)){
               photoUrl = "http://appphoto.doers.cn/f8e09d3b-d32a-420c-a06b-9caf4d7eb58b.png";// 默认一个头像
           }
           userNameVal = user.getUserName();
       }
       view.addObject("userId",userId);
       view.addObject("photo",photoUrl);
       view.addObject("userName",userNameVal);
       return view;
    }

    /**
     * 分享用户注册
     *
     * @param counttriesCode
     * @param phone
     * @param code
     * @param password
     * @param shareUserId
     * @return
     */
    @PostMapping("/addShareUser")
    @ApiOperation(value = "分享用户注册，APP不可使用", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "counttriesCode",value = "区号",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "phone",value = "手机号",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "code",value = "验证码",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "password",value = "密码",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "shareUserId",value = "分享用户ID",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "userName",value = "用户名",
                    required = true, dataType = "String",defaultValue = "")
    })
    public Message addShareUser(String counttriesCode,String phone,String code,String password,
                                String shareUserId,String userName,HttpServletRequest request) throws Exception{
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
            if (userService.checkLoginName(userName) >= 1) {
                message = MessageUtils.getSuccess("此用户名已经存在");
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
        User parentUser = userService.selectById(shareUserId);
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setLoginName(phone);
        user.setUserName(userName);
        user.setRealName(userName);
        user.setNickName(userName);
        user.setCountryCode(counttriesCode);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setPhone(phone);
        user.setQrCodeUrl(jtxyappUrl + "/api/register/showShare?userId="+user.getUserId());
        user.setPhoto("http://appphoto.doers.cn/f8e09d3b-d32a-420c-a06b-9caf4d7eb58b.png");// 默认一个头像
        if(parentUser != null){
            user.setCommendname(parentUser.getCountryCode()+"-"+parentUser.getPhone());
        }
        int addCountVal = userService.reigister(user);
        //记录分润上下级关系
        // int addCountVal = userService.addShareUser(shareUserId,user.getUserId());
        if(addCountVal > 0){
            message = MessageUtils.getSuccess("注册成功");
            message.setData(1);
        }else{
            message = MessageUtils.getSuccess("注册失败");
            message.setData(0);
        }
        return message;
    }

    @ApiOperation(value = "用户注册", produces = "application/json")
    @PostMapping(value="/")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "username", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "password", value = "登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "realname", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "email", value = "邮箱", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "recommendname", value = "推荐人", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "country_id", value = "国家ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "province_id", value = "省市ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "city_id", value = "市ID", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "countries", value = "国家名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "province", value = "省市名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "city", value = "市名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "counttries_code", value = "区号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "code", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "answer", value = "注册考题答案", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "recommencode", value = "推荐的手机号的区号", required = true, dataType = "String")
    })
    public Message register(String username, String password, String realname, String phone, String email, String recommendname, String country_id, String province_id, String city_id, String countries, String province, String city, String counttries_code, String code, String  answer,String recommencode,HttpSession httpSession, HttpServletRequest request){

        if(StringUtils.isBlank(counttries_code)){
            return MessageUtils.getFail("发送失败,你还没有选择地区");
        }
        if(StringUtils.isBlank(phone)){
            return MessageUtils.getFail("发送失败,手机号码不能为空");
        }
        if(StringUtils.isNotBlank(phone)) {
            if (userService.checkCodeAndPhone(counttries_code,phone) >= 1) {
                return MessageUtils.getFail("此手机号已注册");
            }
        }

        if(StringUtils.isBlank(code)){
            return MessageUtils.getSuccess("注册失败,手机验证码不能为空");
        }

        if(!code.equals(redisService.getValue("message_code_register"+phone))){
            return MessageUtils.getSuccess("注册失败,你输入的验证码不正确");
        }

        if(StringUtils.isBlank(username)){
            return MessageUtils.getSuccess("注册失败，用户名不能为空");
        }
        if(userService.checkLoginName(username) == 1){
            return MessageUtils.getSuccess("注册失败，此用户名已经存在");
        }

        if(StringUtils.isBlank(password)){
            return MessageUtils.getSuccess("注册失败，密码不能为空");
        }

       /* if(StringUtils.isBlank(email)){
            return MessageUtils.getSuccess("注册失败，邮箱不能为空");
        }*///邮箱是选填的

        if(StringUtils.isNotBlank(recommendname)){
            if(StringUtils.isBlank(recommencode)){
                return MessageUtils.getFail("发送失败,你填写的推荐的手机号没有选择地区");
            }
            if(userService.checkCodeAndPhone(recommencode,recommendname) == 0){
                return MessageUtils.getSuccess("注册失败,您填写的推荐手机号尚未注册");
            }
        }
        String recommendUserPhone;
       if(StringUtils.isNotBlank(recommencode) && StringUtils.isNotBlank(recommendname)){
           recommendUserPhone = recommencode+"-"+recommendname;//拼接推荐人的手机号
       }else{
           recommendUserPhone = "";
       }

        User user = new User();
        user.setUserName(username);
        user.setLoginName(username);
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setPassword(DigestUtils.md5Hex(password));
        user.setNickName(username);//用户名也是昵称
        if(StringUtils.isEmpty(realname)){
            user.setRealName(username);
        }else{
            user.setRealName(realname);
        }
        user.setPhone(phone);
        user.setEmail(email);
        user.setCommendname(recommendUserPhone);
        user.setCountriesId(country_id);
        user.setProvinceId(province_id);
        user.setCityId(city_id);
        user.setCountries(countries);
        user.setProvince(province);
        user.setCity(city);
        user.setCountryCode(counttries_code);
        user.setQrCodeUrl(jtxyappUrl + "/api/register/showShare?userId="+user.getUserId());
        user.setPhoto("http://appphoto.doers.cn/f8e09d3b-d32a-420c-a06b-9caf4d7eb58b.png");// 默认一个头像

        //记录考题答案
        JsonElement jsonElement = new JsonParser().parse(answer);
        JsonArray array = jsonElement.getAsJsonArray();
        Iterator<JsonElement> it = array.iterator();
        CourseTopicUserAnswer topicanswer = new CourseTopicUserAnswer();
        CourseTopicExamination RegisterExamMeaaget = courseTopicService.getRegisterExamMessage();
        while(it.hasNext()) {
            JsonElement jsonElement1 = it.next();
            String _str_topicno = jsonElement1.getAsJsonObject().get("topicId").getAsString();
            String _str_answer = jsonElement1.getAsJsonObject().get("answer").getAsString();
            topicanswer.setCourseTopicExaminationId(RegisterExamMeaaget.getCourseTopicExaminationId());
            topicanswer.setUserId(user.getUserId());
            topicanswer.setCourseTopicId(_str_topicno);
            topicanswer.setAnswer(_str_answer);
            courseTopicService.insertanswer(topicanswer);
        }

        List<String> label_answer_list = getNoAnswer(user.getUserId());
        HashSet<String> h=new HashSet();//用set去除重复的
        for(int i=0;i<label_answer_list.size();i++){
            h.add(label_answer_list.get(i));
        }
        String label_str = "";//遍历拼接标签
        for (String str : h) {
           label_str += str+",";
        }
        try {
            user.setLabelId(label_str.substring(0,label_str.length() - 1));//存标签
            userService.reigister(user);
        }catch (RuntimeException e) {
            logger.error("注册失败============",e);
            return MessageUtils.getSuccess("注册失败");
        }

        //调用登录方法
        Map<String,Object> loginMap = userService.login(counttries_code,phone,DigestUtils.md5Hex(password));
        if("success".equals(loginMap.get("status"))){
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            return MessageUtils.getFail(String.valueOf(loginMap.get("message")));
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
            if(StringUtils.isNotBlank(_token)) {
                redisService.delete(_token);
            }

            redisService.setValue(seed,token,timeout);
            user.setToken(token);
            String userStr = JSON.toJSONString(user);
            redisService.setValue(token,userStr,timeout);
        }else{
            return MessageUtils.getFail("登录失败,用户状态不对");
        }
        Message message = MessageUtils.getSuccess("success");
        user.setIs_pay(is_pay);
        message.setData(user);
        return message;
    }

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
            logger.error("注册验证码发送失败",e);
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

    //获取标签
    public List<String> getNoAnswer(String userId){
        CourseTopicExamination registerExam = courseTopicService.getRegisterExamMessage();//得到注册考卷的信息
        List<CourseTopic> ctopins_list = courseTopicService.getExam(registerExam.getCourseTopicExaminationId());//得到注册考卷的所有的考题
        List<String> label_list = new ArrayList();
        CourseTopicUserAnswer registerUserAnswer = new CourseTopicUserAnswer();
        registerUserAnswer.setCourseTopicExaminationId(registerExam.getCourseTopicExaminationId());
        registerUserAnswer.setUserId(userId);
        CourseTopic topicnoLable = new CourseTopic();
        topicnoLable.setCourseTopicExaminationId(registerExam.getCourseTopicExaminationId());
        for (int i=0;i<ctopins_list.size();i++)
        {
                registerUserAnswer.setCourseTopicId(ctopins_list.get(i).getCourseTopicId());
                CourseTopicUserAnswer answer = courseTopicService.selectTopicNoAnswer(registerUserAnswer);//根据考题No查询自己的答案
                topicnoLable.setCourseTopicId(ctopins_list.get(i).getCourseTopicId());
                CourseTopic topic_label_type_list = courseTopicService.selectTopicLable(topicnoLable);//根据考题No查询考题的标签
                String[] aa = answer.getAnswer().split(",");
                if(answer.getAnswer() == null || answer.getAnswer().equals("")){
                    continue;
                }
                for (int j = 0 ; j <aa.length ; j++ ) {
                    String split_answer = aa[j];
                    if(split_answer.equals("A")){
                        label_list.add(topic_label_type_list.getTopicAlabelId());//放进数组//标签放进数组里
                    }else if(split_answer.equals("B")){
                        label_list.add(topic_label_type_list.getTopicBlabelId());//放进数组//标签放进数组里
                    }else if(split_answer.equals("C")){
                        label_list.add(topic_label_type_list.getTopicClabelId());//放进数组//标签放进数组里
                    }else if(split_answer.equals("D")){
                        label_list.add(topic_label_type_list.getTopicDlabelId());//放进数组//标签放进数组里
                    }
                }

        }
        return  label_list;

    }

    @ApiOperation(value = "用户简易注册", produces = "application/json")
    @PostMapping(value="/simp_register")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "password", value = "登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "recommendname", value = "推荐人", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "counttries_code", value = "区号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "code", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "recommencode", value = "推荐的手机号的区号", required = false, dataType = "String")
    })
    public Message register_(String username, String password,String phone, String recommendname,String counttries_code,String code,String recommencode){

        if(StringUtils.isBlank(counttries_code)){
            return MessageUtils.getFail("发送失败,你还没有选择地区");
        }
        if(StringUtils.isBlank(phone)){
            return MessageUtils.getFail("发送失败,手机号码不能为空");
        }
        if(StringUtils.isNotBlank(phone)) {
            if (userService.checkCodeAndPhone(counttries_code,phone) >= 1) {
                return MessageUtils.getFail("此手机号已注册");
            }
        }

        if(StringUtils.isBlank(code)){
            return MessageUtils.getSuccess("注册失败,手机验证码不能为空");
        }

        if(!code.equals(redisService.getValue("message_code_register"+phone))){
            return MessageUtils.getSuccess("注册失败,你输入的验证码不正确");
        }

        if(StringUtils.isBlank(username)){
            return MessageUtils.getSuccess("注册失败，用户名不能为空");
        }
        if(userService.checkLoginName(username) == 1){
            return MessageUtils.getSuccess("注册失败，此用户名已经存在");
        }

        if(StringUtils.isBlank(password)){
            return MessageUtils.getSuccess("注册失败，密码不能为空");
        }

        if(StringUtils.isNotBlank(recommendname)){
            if(StringUtils.isBlank(recommencode)){
                return MessageUtils.getFail("发送失败,请选择推荐手机号的地区");
            }
            if(userService.checkCodeAndPhone(recommencode,recommendname) == 0){
                return MessageUtils.getSuccess("注册失败,您填写的推荐手机号尚未注册");
            }
        }
        String recommendUserPhone;
        if(StringUtils.isNotBlank(recommencode) && StringUtils.isNotBlank(recommendname)){
            recommendUserPhone = recommencode+"-"+recommendname;//拼接推荐人的手机号
        }else{
            recommendUserPhone = "";
        }

        User user = new User();
        user.setUserName(username);
        user.setLoginName(username);
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setPassword(DigestUtils.md5Hex(password));
        user.setNickName(username);//用户名也是昵称
        user.setRealName(username);
        user.setPhone(phone);
        user.setCommendname(recommendUserPhone);
        user.setCountryCode(counttries_code);
        user.setUserLevel(5);//默认为一星
        user.setQrCodeUrl(jtxyappUrl + "/api/register/showShare?userId="+user.getUserId());
        user.setPhoto("http://appphoto.doers.cn/f8e09d3b-d32a-420c-a06b-9caf4d7eb58b.png");// 默认一个头像
        int i= 0;
        try {
            i = userService.reigister(user);
        }catch (RuntimeException e) {
            logger.error("simp_register-注册失败============",e);
            return MessageUtils.getSuccess("注册失败");
        }

        if(i<1){
            return MessageUtils.getSuccess("注册失败");
        }
        //调用登录方法
        Map<String,Object> loginMap = userService.login(counttries_code,phone,DigestUtils.md5Hex(password));
        if("success".equals(loginMap.get("status"))){
            user = JSON.parseObject(String.valueOf(loginMap.get("user")),User.class);
        }else{
            return MessageUtils.getFail(String.valueOf(loginMap.get("message")));
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
            if(StringUtils.isNotBlank(_token)) {
                redisService.delete(_token);
            }

            redisService.setValue(seed,token,timeout);
            user.setToken(token);
            String userStr = JSON.toJSONString(user);
            redisService.setValue(token,userStr,timeout);
        }else{
            return MessageUtils.getFail("登录失败,用户状态不对");
        }
        Message message = MessageUtils.getSuccess("success");
        user.setIs_pay(is_pay);
        message.setData(user);
        return message;
    }
}















