package cn.com.myproject.api.live.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/search")
@Api(value="课程/教头/商品搜索",tags = "搜索")
public class SearchController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("SearchController");

    @Autowired
    private ISearchService iSearchService;

    @Autowired
    private IUserService userService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl  ;

    /**
     * 课程搜索
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @ApiOperation(value = "课程搜索", produces = "application/json")
    @ApiImplicitParams({
    @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
    @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
    @ApiImplicitParam(paramType="query",name = "keyword", value = "搜索关键字", required = true, dataType = "String",defaultValue = "基础")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Course.class, responseContainer = "List" ) })
    @PostMapping("/searchCourses")
    public  Message<List<Course>> searchCourses(String pageNum, String pageSize, String keyword) {
        List<Course> courseList = null;
        try{
            courseList =iSearchService.searchAPICourseList(Integer.valueOf(pageNum),Integer.valueOf(pageSize),"",keyword,"");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("课程搜索异常iSearchService.searchAPICourseList(Integer.valueOf(pageNum),Integer.valueOf(pageSize),\"\",keyword,\"\")");
            return MessageUtils.getFail(e.getMessage());
        }
        Message<List<Course>> message=  MessageUtils.getSuccess("success");
        message.setData(courseList);
        return  message;
    }
    @ApiOperation(value = "教头搜索", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "keyword", value = "搜索关键字", required = true, dataType = "String",defaultValue = "18737184102")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = APITearcherUser.class, responseContainer = "List" ) })
    @PostMapping("/searchTeachers")
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
    @ApiOperation(value = "首页-四位教头", produces = "application/json")
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = APITearcherUser.class, responseContainer = "List" ) })
    @PostMapping("/searchIndexFourTeachers")
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
     * @param userId
     * @return
     */
    @ApiOperation(value = "教头详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "fgdg")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = APITearcherUser.class ) })
    @PostMapping("/teacherDetail")
    public Message<APITearcherUser> teacherDetail(String teacherId, String userId, HttpServletRequest request){
        APITearcherUser teacher  = null;
        try{
            teacher  =  iSearchService.getAPITeacherUserByUserId(teacherId);
            if(teacher == null){
                return MessageUtils.getFail("该教头用户不存在!");
            }
            if(userService.selectById(userId) == null){
                return MessageUtils.getFail("非法会员ID!");
            }
            teacher.setUserLevel(getUserLevel(teacher.getScore()));
            int cousrseNum = iSearchService.searchMyCourseCount(teacherId);
            teacher.setUserAwards(getUserAwards(new Double(cousrseNum)));
            if(teacher.getUserId().equals("da7ba7f59c824f65b9a1561d416695d1") || (teacher.getUserName() != null &&teacher.getUserName().equals("林伟贤")) || ( teacher.getRealName()!=null && teacher.getRealName().equals("林伟贤"))){

                teacher.setUserAwards("顶尖教头");
            }
            teacher.setTeacherIntroUrl(jtxyappUrl+ "/api/search/teacherIntro?teacherId="+teacherId);

            //查询用户关注该教头对象
            UserTeacherFocus focus = iSearchService.getUserTeacherFocus(new UserTeacherFocus(userId,teacherId));

            if(focus == null){
                teacher.setIsFocus(0);
            }else{
                teacher.setIsFocus(focus.getIsFocus());
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
     * 关注或取消关注教头
     * @param teacherId
     * @param userId
     * @return
     */
    @ApiOperation(value = "关注或取消教头", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "fgdg"),
            @ApiImplicitParam(paramType="query",name = "isFocus", value = "是否关注0：取消关注；1:关注", required = true, dataType = "String",defaultValue = "1")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = APITearcherUser.class ) })
    @PostMapping("/focusOrCancelFocusTeahcer")
    public Message focusOrCancelFocusTeahcer(String teacherId,String userId ,String isFocus){
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
     * @param teacherId
     * @return
     */
    @ApiOperation(value = "教师课程发布动态或作品列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
            @ApiImplicitParam(paramType="query",name = "type", value = "查询动态还是作品，0：动态；1：作品", required = true, dataType = "String",defaultValue = "0")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Course.class, responseContainer = "List" ) })
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
     * 教师简介页面
     * @return
     */
    @ApiOperation(value = "教师简介页面(app不许调用，教师详情有url）", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = APITearcherUser.class, responseContainer = "List" ) })
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
     * @param money
     */
    @ApiOperation(value = "打赏教头", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348"),
            @ApiImplicitParam(paramType="query",name = "money", value = "打赏金额(单位：学分/银两)", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "payWay", value = "支付方式(0:银两支付（余额）；1:支付宝；2：微信；3：PayPal)", required = true, dataType = "String",defaultValue = "0")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = TeacerRewardVO.class, responseContainer = "List" ) })
    @PostMapping ("/giveTeahcerReward")
    public  Message giveTeahcerReward(String teacherId,String userId,String money ,String payWay){
        Map<String,Object> result = null;

        try{
            if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
               return MessageUtils.getFail("该教头用户不存在!");
            }
            if(userService.selectById(userId) == null){
                return MessageUtils.getFail("非法会员ID!");
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
     * 教师详情-问答-列表
     * @param userId
     */
    @ApiOperation(value = "教师详情-问答-用户与教头的问答列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = UserTeacherQuestion.class, responseContainer = "List" ) })
    @PostMapping ("/questionAndReplyList")
    public Message<List<UserTeacherQuestion>>  questionAndReplyList( String pageNum,String pageSize, String teacherId,String userId){

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
     * @param userId
     * @param teacherId
     * @param question
     */
    @ApiOperation(value = "教师详情-问答-会员提问", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "da7ba7f59c824f65b9a1561d416695d1"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348"),
            @ApiImplicitParam(paramType="query",name = "question", value = "问题（100字以内）", required = true, dataType = "String",defaultValue = "你好，老师请教你个问题。")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "List" ) })
    @PostMapping ("/userAnswerTeacherQuestion")
    public Message userAnswerTeacherQuestion(String teacherId, String userId ,String question){

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
     *  消息列表-教头答复
     * @param teacherId
     * @param reply
     * @param userTeacherQuestionId
     * @return
     */
    @ApiOperation(value = "消息列表-教头答复", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "teacherId", value = "教头ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339"),
            @ApiImplicitParam(paramType="query",name = "reply", value = "问题（100字以内）", required = true, dataType = "String",defaultValue = "你好，请问"),
            @ApiImplicitParam(paramType="query",name = "userTeacherQuestionId", value = "用户提问教头表业务ID", required = true, dataType = "String",defaultValue = "2b39224656014867980f9154013bcc08")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "List" ) })
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

    @ApiOperation(value = "消息列表-用户提问问题列表或向用户提问问题列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID", required = true, dataType = "String",defaultValue = "cd1ef565af884e318810673b9c1c1031"),
            @ApiImplicitParam(paramType="query",name = "type", value = "查询类别0：我的提问列表，1：别人提问我的问题列表", required = true, dataType = "String",defaultValue = "0")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "List" ) })
    @PostMapping ("/questionOrReplyList")
    public Message<List<UserTeacherQuestion>> questionOrReplyList(String pageNum,String pageSize,String userId, String type){
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
    @ApiOperation(value = "名优教头直面授课-教头分类查询", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="query",name = "typeVal", value = "类别：0:会员；1：教头/讲师", required = true, dataType = "String",defaultValue = "1"),  @ApiImplicitParam(paramType="query",name = "parentId", value = "分类父ID,一级分类传入-1，二级分类传入一级分类的业务ID", required = true, dataType = "String",defaultValue = "-1"),
       @ApiImplicitParam(paramType="query",name = "level", value = "分类级别：1代表一级分类，2代表二级分类", required = true, dataType = "String",defaultValue = "1")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = UserType.class, responseContainer = "List" ) })
    @PostMapping ("/teacherTypeListByUserLevel")
    public Message<List<UserType>> teacherTypeListByUserLevel(String typeVal,String parentId,String level){
        List<UserType> userTypeList = iSearchService.searchUserTypeList(Integer.valueOf(typeVal),parentId,level);
        Message<List<UserType>> message = MessageUtils.getSuccess("success");
        message.setData(userTypeList);
        return message;
    }

    @ApiOperation(value = "名优教头直面授课-教头分类下的名师列表", produces = "application/json")
    @ApiImplicitParams({
          @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
          @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
          @ApiImplicitParam(paramType="query",name = "keyword", value = "查询关键字", required = false, dataType = "String",defaultValue = "187"),
          @ApiImplicitParam(paramType="query",name = "userTypeId", value = "二级分类ID", required = true, dataType = "String",defaultValue = "6a8ae4498ea04cb9968df98655564882")})
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = APITearcherUser.class, responseContainer = "List" ) })
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
