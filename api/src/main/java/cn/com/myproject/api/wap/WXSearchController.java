package cn.com.myproject.api.wap;


import cn.com.myproject.api.service.ISearchService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.PO.*;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import cn.com.myproject.user.entity.VO.TeacerRewardVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by LeiJia  2017/08/28
 */
@RestController
@RequestMapping("/wap/search")
@Api(value="课程/教头/商品搜索",tags = "搜索")
public class WXSearchController extends BaseController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("WXSearchController");

    @Autowired
    private ISearchService iSearchService;

    @Autowired
    private IUserService userService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl  ;

    /**
     * 进入搜索页面
     * @return
     */
    @RequestMapping("/home/allSearch")
    public ModelAndView allSearch(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/home/allSearch");
        User currUser = getCurrUser();
        if(currUser!=null){
            User user= userService.selectById(currUser.getUserId());
            view.addObject("userIdentity",user.getUserIdentity());
        }
        return view;
    }

    /**
     * 教头详情页面
     * @return
     */
    @RequestMapping("/wellTeacherHome")
    public ModelAndView wellTeacherHome(String teacherId,HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/home/wellTeacherHome");
        view.addObject("teacherId",teacherId);

        User currUser = getCurrUser();
        if(currUser!=null){
            User user= userService.selectById(currUser.getUserId());
            view.addObject("userIdentity",user.getUserIdentity());
        }
        //查询教头
//        List<String> teacherIntroImgs = iSearchService.searchTeacherIntroImgs(teacherId);
//        view.addObject("teacherIntroImgs",teacherIntroImgs);

        return view;
    }


    /**
     * 教头分类页面
     * @return
     */
    @RequestMapping("/home/classify")
    public ModelAndView classify() {
        ModelAndView view = new ModelAndView("/home/classify");
        return view;
    }
    /**
     * 教头分类下的教头列表
     * @return
     */
    @RequestMapping("/home/wellTeacherList")
    public ModelAndView wellTeacherList(String userTypeId) {
        ModelAndView view = new ModelAndView("/home/wellTeacherList");
        view.addObject("userTypeId",userTypeId);
        return view;
    }
    /**
     * 设置支付密码页面
     * @return
     */
    @RequestMapping("/setZhifuPwd")
    public ModelAndView setZhifuPwd(String returnUrl) {
        ModelAndView view = new ModelAndView("/my/setZhifuPwd");
        view.addObject("returnUrl",returnUrl);
        return view;
    }

    /**
     * 跳转到支付方式页面
     * @return
     */
    @RequestMapping("/teahcerReward/payOrder")
    public ModelAndView payOrder(String teacherId,String money) {
        ModelAndView view = new ModelAndView("/home/teahcerReward/payOrder");
        view.addObject("teacherId",teacherId);
        view.addObject("money",money);
        return view;
    }

    /**
     * 教头打赏失败失败页面
     */
    @RequestMapping("/teahcerReward/giveTeahcerRewardFail")
    public ModelAndView giveTeahcerRewardFail(String teacherId){
        ModelAndView view = new ModelAndView("/home/teahcerReward/payFail");
        view.addObject("teacherId",teacherId);
        view.addObject("backUrl","/wap/search/wellTeacherHome?teacherId="+teacherId);
        return view;
    }

    /**
     * 教头问答页面
     * @return
     */
    @RequestMapping("/message/myTeacherAnswer")
    public ModelAndView myTeacherAnswer(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/my/myTeacherAnswer");
        return view;
    }

    /**
     * 课程搜索
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @PostMapping("/home/searchCourses")
    public  Message<List<Course>> searchCourses(String pageNum, String pageSize, String keyword ,Model model,HttpServletRequest request) {
        List<Course> courseList = null;
        try{
            courseList =iSearchService.searchAPICourseList(Integer.valueOf(pageNum),Integer.valueOf(pageSize),"",keyword,"");
            User currUser = getCurrUser();
            if(currUser!=null){
                User user = userService.selectById(currUser.getUserId());
                model.addAttribute("userIdentity",user.getUserIdentity());
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("课程搜索异常iSearchService.searchAPICourseList(Integer.valueOf(pageNum),Integer.valueOf(pageSize),\"\",keyword,\"\")");
            return MessageUtils.getFail(e.getMessage());
        }
        Message<List<Course>> message=  MessageUtils.getSuccess("success");
        message.setData(courseList);
        return  message;
    }

    /**
     * 教头搜索
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @PostMapping("/home/searchTeachers")
    public Message< List<APITearcherUser>> searchTeachers(String pageNum, String pageSize, String keyword) {
        Map<String,Object> paramMap = new LinkedHashMap<>();
        List<APITearcherUser> list = null;
        try{
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
                keyword = URLDecoder.decode(keyword, "UTF-8");
                paramMap.put("keyword",keyword.trim());
            }
            paramMap.put("pageNum",pageNum);
            paramMap.put("pageSize",pageSize);
            paramMap.put("status",1);//type 类型 1：教头，2：用户// user_identity用户身份1:普通用户2：会员用户 //status 0：禁用账号;1:启用账号
            paramMap.put("identity",null);
            paramMap.put("type",1);
            list =  iSearchService.getAPITeacherUsersPage(paramMap);
        }catch(Exception e){
            logger.error("教头搜索异常iSearchService.getAPITeacherUsersPage(paramMap)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message< List<APITearcherUser>> message = MessageUtils.getSuccess("success");
        message.setData(list);

        return message;
    }
    /**
     * 首页-四位教头
     */
    @PostMapping("/home/searchIndexFourTeachers")
    public Message< List<APITearcherUser>> searchIndexFourTeachers() {
        Map<String,Object> paramMap = new LinkedHashMap<>();
        List<APITearcherUser> list = null;
        try{
            list =  iSearchService.searchIndexFourTeachers(paramMap);
        }catch(Exception e){
            logger.error("首页-四位教头搜索异常iSearchService.searchIndexFourTeachers(paramMap)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message< List<APITearcherUser>> message = MessageUtils.getSuccess("success");
        message.setData(list);

        return message;
    }

    /**
     * 教头详情
     * @param teacherId
     * @return
     */
    @PostMapping("/teacherDetail")
    public Message<APITearcherUser> teacherDetail(String teacherId,  HttpServletRequest request){
        APITearcherUser teacher  = null;
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
           userId = currUser.getUserId();
        }
        try{
            teacher  =  iSearchService.getAPITeacherUserByUserId(teacherId);
            if(teacher == null){
                return MessageUtils.getFail("该教头用户不存在!");
            }
            teacher.setUserLevel(getUserLevel(teacher.getScore()));
            int cousrseNum = iSearchService.searchMyCourseCount(teacherId);
            teacher.setUserAwards(getUserAwards(new Double(cousrseNum)));
            if(teacher.getUserId().equals("da7ba7f59c824f65b9a1561d416695d1") || (teacher.getUserName() != null &&teacher.getUserName().equals("林伟贤")) || ( teacher.getRealName()!=null && teacher.getRealName().equals("林伟贤"))){

                teacher.setUserAwards("顶尖教头");
            }
            teacher.setTeacherIntroUrl(jtxyappUrl+ "/wap/search/teacherIntro?teacherId="+teacherId);

            if(StringUtils.isNotBlank(userId)){

                //查询用户关注该教头对象
                UserTeacherFocus focus = iSearchService.getUserTeacherFocus(new UserTeacherFocus(userId,teacherId));

                if(focus == null){
                    teacher.setIsFocus(0);
                }else{
                    teacher.setIsFocus(focus.getIsFocus());
                }
            }else{
                teacher.setIsFocus(0);
            }

            //查询教头
            List<String> teacherIntroImgs = iSearchService.searchTeacherIntroImgs(teacherId);
            if(teacherIntroImgs != null){
                teacher.setTeacherIntroImgs(teacherIntroImgs);
            }
        }catch(Exception e){
            logger.error("教头详情异常iSearchService.getUserTeacherFocus(new UserTeacherFocus(userId,teacherId))");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message<APITearcherUser> message  = MessageUtils.getSuccess("success");
        message.setData(teacher);
        return message;
    }
    /**
     * 关注或取消教头
     * @param teacherId
     * @param isFocus 是否关注0：取消关注；1:关注
     * @return
     */
    @PostMapping("/focusOrCancelFocusTeahcer")
    public Message focusOrCancelFocusTeahcer(String teacherId,String isFocus){
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
        //查询用户关注该教头对象
        int focusOrCancelResult =0;
        String msg="fail";

        try{
            if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
                return MessageUtils.getFail("该教头用户不存在!");
            }
            if(userService.selectById(userId) == null){
                return MessageUtils.getFail("非法会员ID!");
            }
            if(teacherId.equals(userId)){
                return MessageUtils.getFail("不能关注自己!");
            }
            UserTeacherFocus focus = iSearchService.getUserTeacherFocus(new UserTeacherFocus(userId,teacherId));
            if(focus == null){
                focus = new UserTeacherFocus(userId,teacherId);
                focus.setIsFocus(isFocus.equals("1")?1:0);
                focus.setUserTeacherFocusId(UUID.randomUUID().toString().replace("-", ""));
                focus.setStatus((short)1);
                focus.setVersion(1);
                focus.setCreateTime(Calendar.getInstance().getTimeInMillis());
                focusOrCancelResult = iSearchService.insertUserTeacherFocus(focus);
            }else{
                focus.setIsFocus(isFocus.equals("1")?1:0);
                focusOrCancelResult =iSearchService.updateUserTeacherFocus(focus);
            }
            if(focusOrCancelResult==1 && isFocus.equals("1")){
                msg = "success";
            }else if(focusOrCancelResult==1 && isFocus.equals("0")){
                msg = "success";
            }
        }catch(Exception e){
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message   message = MessageUtils.getSuccess(msg);
        message.setData(new ArrayList<>());
        message.setResult(1);
        return message;

    }

    /**
     * 教师课程发布动态或作品列表
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @param type 查询动态还是作品，0：动态；1：作品
     * @return
     */
    @PostMapping("/courseRelaseDynamicList")
    public Message<List<Course>>  courseRelaseDynamicList(String pageNum,String pageSize, String teacherId,String type){
        Message<List<Course>> message= null;
        List<Course> courseList = null;
        try{

            if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
                return MessageUtils.getFail("该教头用户不存在!");
            }
            courseList =iSearchService.searchCourseListByTeacherId(pageNum,pageSize,teacherId,type);
        }catch(Exception e){
            logger.error("教师课程发布动态或作品列表异常iSearchService.searchCourseListByTeacherId(pageNum,pageSize,teacherId,type)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        message = MessageUtils.getSuccess("success");
        message.setData(courseList);
        message.setResult(1);
        return message;
    }
    /**
     * 教师简介页面(app不许调用，教师详情有url）
     * @param teacherId
     * @return
     */
    @GetMapping ("/teacherIntro")
    public ModelAndView teacherIntro(String teacherId  ){
        ModelAndView view = new ModelAndView("/user/userIntro");
        APITearcherUser teacher  =  iSearchService.getAPITeacherUserByUserId(teacherId);
        view.addObject("userIntro",teacher.getUserIntro()==null?"无简介附件":teacher.getUserIntro());
        return view;
    }

    /**
     * 打赏教头
     * @param teacherId
     * @param money 打赏金额(单位：学分/银两)
     * @param payWay 支付方式(0:银两支付（余额）；1:支付宝；2：微信；3：PayPal
     * @return
     */
    @PostMapping ("/giveTeahcerReward")
    public  Message giveTeahcerReward(String teacherId,String money ,String payWay){
        Map<String,Object> result = null;
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
        try{
            if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
               return MessageUtils.getFail("该教头用户不存在!");
            }
            if(userService.selectById(userId) == null){
                return MessageUtils.getFail("非法会员ID!");
            }
            if(teacherId.equals(userId)){
                return MessageUtils.getFail("不能打赏自己!");
            }
            //查询会员用户资金记录
            if("0".equals(payWay)){// 如果是学分支付的话，才判断账户余额
                UserCapital userCapital = iSearchService.selectUserCapitalByUserId(userId);
                if(userCapital == null || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(0))<=0)  || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(money))<0)){
                    return MessageUtils.getSuccess("余额不足");
                }
            }
            result = iSearchService.giveTeahcerReward(teacherId,userId,money,payWay);

        }catch(Exception e){
            logger.error("打赏教头异常iSearchService.giveTeahcerReward(teacherId,userId,money,payWay)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        if("1".equals(result.get("payResult").toString()) ){
            TeacerRewardVO vo = new TeacerRewardVO();
            vo.setOrderId(result.get("orderId").toString());
            Message message = MessageUtils.getSuccess("success");
            message.setData(vo);
            message.setResult(1);
            return message;
        }else {
            Message message = MessageUtils.getSuccess("fail");
            message.setData(result);
            message.setResult(0);
            return message;
        }

    }
    /**
     * 教师详情-问答-用户与教头的问答列表
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @return
     */
    @PostMapping ("/questionAndReplyList")
    public Message<List<UserTeacherQuestion>>  questionAndReplyList( String pageNum,String pageSize, String teacherId){
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
        if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
            return MessageUtils.getFail("该教头用户不存在!");
        }
        if(userService.selectById(userId) == null){
            return MessageUtils.getFail("非法会员ID!");
        }
        List<UserTeacherQuestion> questionList = iSearchService.questionAndReplyList(Integer.valueOf(pageNum),Integer.valueOf(pageSize),teacherId,userId);
        for(UserTeacherQuestion question:questionList){
            question.setQuestion(URLDecoder.decode(question.getQuestion()));
            List<UserTeacherQuestionReply> replyList = question.getReplyList();
            if(replyList != null && replyList.size()>0){
                for(UserTeacherQuestionReply reply:replyList){
                    reply.setReply(URLDecoder.decode(reply.getReply()));
                }
            }
        }
        Message< List<UserTeacherQuestion>> message = MessageUtils.getSuccess("success");
        message.setData(questionList);
        return message;
    }
    /**
     * 教师详情-问答-会员提问
     * @param teacherId
     * @param question 问题（100字以内）
     * @return
     */
    @PostMapping ("/userAnswerTeacherQuestion")
    public Message userAnswerTeacherQuestion(String teacherId,String question){
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
        if(StringUtils.isNotBlank(question)){
            if(question.length() > 100){
                Message<Integer> message = MessageUtils.getFail("问题内容不能超过100字");
                return message;
            }
        }
        if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
            return MessageUtils.getFail("该教头用户不存在!");
        }
        if(userService.selectById(userId) == null){
            return MessageUtils.getFail("非法会员ID!");
        }

        if(teacherId.equals(userId)){
            return MessageUtils.getFail("不能向自己提问!");
        }
        try {
            // 存入数据库前进行转码
            question = URLEncoder.encode(question, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return MessageUtils.getFail("转码失败!");
        }
         int i= iSearchService.insertUserAnswerTeacherQuestion(teacherId,userId,question);
         if(i<1){
             Message message = MessageUtils.getFail("fail");
             message.setData(new ArrayList<>());
             return message;
         }
        Message message = MessageUtils.getSuccess("success");
        message.setData(new ArrayList<>());
        return message;
    }


    /**
     * 消息列表-教头答复
     * @param teacherId
     * @param reply 问题（100字以内）
     * @param userTeacherQuestionId 用户提问教头表业务ID
     * @return
     */
    @PostMapping ("/teacherReplyUserQuestion")
    public Message teacherReplyUserQuestion(String teacherId , String reply,String userTeacherQuestionId){
        if(StringUtils.isNotBlank(reply)){
            if(reply.length() >100){
                Message<Integer> message = MessageUtils.getFail("回复内容不能超过100字");
                return message;
            }
        }
        if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
            return MessageUtils.getFail("该教头用户不存在!");
        }
        try {
            // 存入数据库前进行转码
            reply = URLEncoder.encode(reply, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return MessageUtils.getFail("转码失败!");
        }
        int i= iSearchService.insertTeacherReplyUserQuestion(teacherId,reply,userTeacherQuestionId);
        if(i<1){
            Message message = MessageUtils.getFail("fail");
            message.setData(new ArrayList<>());
            return message;
        }
        Message message = MessageUtils.getSuccess("success");
        message.setData(new ArrayList<>());
        return message;
    }

    /**
     * 消息列表-用户提问问题列表或向用户提问问题列表
     * @param pageNum
     * @param pageSize
     * @param type 查询类别0：我的提问列表，1：别人提问我的问题列表
     * @return
     */
    @PostMapping ("/questionOrReplyList")
    @ResponseBody
    public Message<List<UserTeacherQuestion>> questionOrReplyList(String pageNum,String pageSize,String type){
        String userId="";
        User currUser = getCurrUser();
        if(currUser!=null){
            userId = currUser.getUserId();
        }
           List<UserTeacherQuestion> list = null;
          if(StringUtils.isEmpty(userId)|| StringUtils.isBlank(userId)){
               return MessageUtils.getFail("用户ID不能为空");
           }
           if(StringUtils.isEmpty(type)|| StringUtils.isBlank(type)){
            return MessageUtils.getFail("查询类别不能为空");
           }
           if( userService.selectById(userId) == null){
               return MessageUtils.getFail("该用户不存在!");
           }
           list = iSearchService.questionOrReplyList(Integer.valueOf(pageNum),Integer.valueOf(pageSize),userId,type);

           for(UserTeacherQuestion question:list){
                question.setQuestion(URLDecoder.decode(question.getQuestion()));
                List<UserTeacherQuestionReply> replyList = question.getReplyList();
                if(replyList != null && replyList.size()>0){
                    for(UserTeacherQuestionReply reply:replyList){
                        reply.setReply(URLDecoder.decode(reply.getReply()));
                    }
                }
            }
           Message<List<UserTeacherQuestion>> message = null;
           message = MessageUtils.getSuccess("success");
           message.setData(list);
           return message;
    }

    /**
     * 名优教头直面授课-教头分类查询
     * @param typeVal 类别：0:会员；1：教头/讲师
     * @param parentId 分类父ID,一级分类传入-1，二级分类传入一级分类的业务ID
     * @param level 分类级别：1代表一级分类，2代表二级分类
     * @return
     */
    @PostMapping ("/teacherTypeListByUserLevel")
    public Message<List<UserType>> teacherTypeListByUserLevel(String typeVal,String parentId,String level){
        List<UserType> userTypeList = iSearchService.searchUserTypeList(Integer.valueOf(typeVal),parentId,level);
        Message<List<UserType>> message = MessageUtils.getSuccess("success");
        message.setData(userTypeList);
        return message;
    }

    /**
     * 名优教头直面授课-教头分类下的名师列表
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @param userTypeId 二级分类ID
     * @return
     */
    @PostMapping ("/teacherUserListByUserTypeId")
    public Message<List<APITearcherUser>> teacherUserListByUserTypeId(String pageNum, String pageSize, String keyword,String userTypeId){
        Map<String,Object> paramMap = new LinkedHashMap<>();
        List<APITearcherUser> list = null;
        try{
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
                keyword = URLDecoder.decode(keyword, "UTF-8");
                paramMap.put("keyword",keyword.trim());
            }
            paramMap.put("pageNum",pageNum);
            paramMap.put("pageSize",pageSize);
            paramMap.put("status",1);//type 类型 1：教头，2：用户// user_identity用户身份1:普通用户2：会员用户 //status 0：禁用账号;1:启用账号
            paramMap.put("identity",null);
            paramMap.put("type",1);
            paramMap.put("userTypeId",userTypeId);
            list =  iSearchService.getAPITeacherUsersPage(paramMap);
        }catch(Exception e){
            logger.error("名优教头直面授课-教头分类下的名师列表异常 iSearchService.getAPITeacherUsersPage(paramMap)");
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }

        Message<List<APITearcherUser>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    public String getUserAwards(Double score){
        if(score == null ) score = new Double(0);
        String awards="初出茅庐";
        int d0 = score.compareTo(new Double(0));
        int d9 = score.compareTo(new Double(9));

        int d10 = score.compareTo(new Double(10));
        int d30 = score.compareTo(new Double(30));

        int d31 = score.compareTo(new Double(31));
        int d50 = score.compareTo(new Double(50));

        int d51 = score.compareTo(new Double(51));
        int d100 = score.compareTo(new Double(100));

        if(d0>= 0 && d9 <= 0) {
            awards = "初出茅庐";
        }else if(d10 >= 0 && d30 <= 0){
            awards = "小有成就";
        }else if(d31 >= 0 && d50 <= 0){
            awards = "游刃有余";
            return awards;
        }else if(d51 >= 0 && d100 <= 0){
            awards = "顶尖教头";
        }
        return awards;
    }

    public String getUserLevel(Double score){
        if(score == null ) score = new Double(0);
        String userLevel="一星";
        int d0 = score.compareTo(new Double(0));
        int d199 = score.compareTo(new Double(199));

        int d200 = score.compareTo(new Double(200));
        int d699 = score.compareTo(new Double(699));

        int d700 = score.compareTo(new Double(700));
        int d1499 = score.compareTo(new Double(1499));

        int d1500 = score.compareTo(new Double(1500));
        int d2999 = score.compareTo(new Double(2999));

        int d3000 = score.compareTo(new Double(3000));
        int d9999 = score.compareTo(new Double(9999));

        int d10000 = score.compareTo(new Double(10000));
        int d29999 = score.compareTo(new Double(29999));

        int d30000 = score.compareTo(new Double(30000));

        if(d0 >=0 && d199 <= 0){
            userLevel="一星";
        }else if(d200 >=0 && d699 <=0){
            userLevel="二星";
        }else if(d700 >=0 && d1499 <=0){
            userLevel="三星";
        }else if(d1500 >=0 && d2999 <=0){
            userLevel="四星";
        }else if(d3000 >=0 && d9999 <=0){
            userLevel="五星";
        }else if(d10000 >=0 && d29999 <=0){
            userLevel="白金";
        }else if(d30000 >= 0){
            userLevel="钻石";
        }
        return userLevel;
    }


}
