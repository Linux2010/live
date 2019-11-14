package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.*;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.*;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.live.entity.VO.JsVo;
import cn.com.myproject.live.entity.VO.SpectatorLiveRoomVO;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserTeacherFocus;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/*
 * Created by pangdatao on 2017-09-30
 * desc：微信端课程控制器类
 */
@Controller
@RequestMapping("/wap/course")
public class WXCourseController extends BaseController{

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private ICourseTypeService iCourseTypeService;

    @Autowired
    private ICourseFinishRecordService iCourseFinishRecordService;

    @Autowired
    private ISearchService iSearchService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IChatRoomService iChatRoomService;

    /**
     * 展示高德地图页面上的定位
     *
     * @param xVal
     * @param yVal
     * @return
     */
    @RequestMapping("/showGdMap")
    public ModelAndView showGdMap(String xVal,String yVal){
        ModelAndView view = new ModelAndView("/course/gaodeMap");
        view.addObject("xVal",xVal);
        view.addObject("yVal",yVal);
        return view;
    }

    /**
     * 进入直播间页面
     *
     * @param teaId
     * @param imRoomid
     * @return
     */
    @RequestMapping("/showRoom")
    public ModelAndView showRoom(String teaId,String imRoomid){
        // 查询教头详情
        User teaUser = iUserService.selectById(teaId);
        // 获取当前登录用户信息
        User user = getCurrUser();
        ModelAndView view = new ModelAndView("/course/room");
        if(teaUser != null){
            view.addObject("teaAccid",teaUser.getAccid());
            view.addObject("teaPhoto",teaUser.getPhoto());
            view.addObject("teaUserName",teaUser.getUserName());
        }
        if(user != null){
            view.addObject("accId",user.getAccid());
            view.addObject("accidToken",user.getAccidToken());
            view.addObject("userName",user.getUserName());
        }
        view.addObject("imRoomid",imRoomid);
        view.addObject("jtxyappUrl",jtxyappUrl);
        return view;
    }

    /**
     * 进入搜索页面
     *
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView search() {
        ModelAndView view = new ModelAndView("/course/search");
        return view;
    }

    /**
     * 进入课程分类页面
     *
     * @return
     */
    @RequestMapping("/courseType")
    public ModelAndView courseType(String typeVal) {
        ModelAndView view = new ModelAndView("/course/courseType");
        view.addObject("typeVal",typeVal);
        return view;
    }

    /**
     * 进入视频课程列表页面
     *
     * @return
     */
    @RequestMapping("/spkcIndex")
    public ModelAndView spkcIndex() {
        ModelAndView view = new ModelAndView("/course/spkcIndex");
        return view;
    }

    /**
     * 进入直播课程列表页面
     *
     * @return
     */
    @RequestMapping("/zbkcIndex")
    public ModelAndView zbkcIndex() {
        ModelAndView view = new ModelAndView("/course/zbkcIndex");
        return view;
    }

    /**
     * 进入文字课程列表页面
     *
     * @return
     */
    @RequestMapping("/wzkcIndex")
    public ModelAndView wzkcIndex() {
        ModelAndView view = new ModelAndView("/course/wzkcIndex");
        return view;
    }

    /**
     * 进入音频课程列表页面
     *
     * @return
     */
    @RequestMapping("/ypkcIndex")
    public ModelAndView ypkcIndex() {
        ModelAndView view = new ModelAndView("/course/ypkcIndex");
        return view;
    }

    /**
     * 进入江湖大课列表页面
     *
     * @return
     */
    @RequestMapping("/jhdkIndex")
    public ModelAndView jhdkIndex() {
        ModelAndView view = new ModelAndView("/course/jhdkIndex");
        return view;
    }

    /**
     * 进入在播课程列表页面
     *
     * @return
     */
    @RequestMapping("/onLiveIndex")
    public ModelAndView onLiveIndex() {
        ModelAndView view = new ModelAndView("/course/onLiveIndex");
        return view;
    }

    /**
     * 进入课程详情页面
     *
     * @param courseId
     * @param courseType spkc代表视频课程、zbkc代表直播课程、wzkc代表文字课程、ypkc代表音频课程、jhdk代表江湖大课
     * @return
     */
    @RequestMapping("/courseDetail")
    public ModelAndView courseDetail(String courseType,String courseId) {
        ModelAndView view = new ModelAndView();
        switch (courseType){
            case "spkc":
                view.setViewName("/course/spkcDetail");
                break;
            case "zbkc":
                view.setViewName("/course/zbkcDetail");
                break;
            case "wzkc":
                view.setViewName("/course/wzkcDetail");
                break;
            case "ypkc":
                view.setViewName("/course/ypkcDetail");
                break;
            case "jhdk":
                view.setViewName("/course/jhdkDetail");
                break;
            default:break;
        }
        view.addObject("courseId",courseId);
        view.addObject("courseType",courseType);
        return view;
    }

    /**
     * 进入结算页面
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/showJsPage")
    public ModelAndView showJsPage(String courseId,String courseType){
        ModelAndView view = new ModelAndView("/course/jiesuan");
        view.addObject("courseId",courseId);
        view.addObject("courseType",courseType);
        return view;
    }

    /**
     * 进入支付课程订单页面
     *
     * @param courseId
     * @param courseType
     * @return
     */
    @RequestMapping("/showPayCoPage")
    public ModelAndView showPayCoPage(String courseId,String courseType){
        ModelAndView view = new ModelAndView("/course/payCo");
        Course course = iCourseService.searchCourseInfoById(courseId);
        double totalMoeny = 0;
        if(course != null){
            totalMoeny = course.getCostPrice();
        }
        view.addObject("courseId",courseId);
        view.addObject("totalMoney",totalMoeny);
        view.addObject("courseType",courseType);
        return view;
    }

    /**
     * 支付失败
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/showPayCoFailPage")
    public ModelAndView showPayCoFailPage(String courseId,String courseType){
        ModelAndView view = new ModelAndView("/course/payCoFail");
        view.addObject("courseId",courseId);
        view.addObject("backUrl","/wap/course/courseDetail?courseType="+courseType+"&courseId="+courseId);
        return view;
    }

    /**
     * 根据课程类型和父节点ID查询课程分类
     *
     * @param typeVal
     * @param parentId
     * @return
     */
    @RequestMapping("/searchCtList")
    @ResponseBody
    public List<CourseType> searchCtList(String typeVal, String parentId){
        return iCourseTypeService.searchAllCtList(typeVal,parentId);
    }

    /**
     * 查询视频课程列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTitle
     * @param courseTypeId
     * @return
     */
    @RequestMapping("/showSpkcList")
    @ResponseBody
    public List<Course> showSpkcList(int pageNum,int pageSize,String courseTitle,String courseTypeId){
        // 从session中获取用户类型：1代表普通用户，2代表会员
        int userIdentity = 0;
        User user = getCurrUser();
        if(user != null){
            userIdentity = user.getUserIdentity();
        }
        List<Course> courseList = iCourseService.searchAllCourseList(pageNum,pageSize,courseTypeId,courseTitle,"","spkc",1);
        if(courseList != null && courseList.size() > 0){
            for(int i = 0;i < courseList.size();i++){
                courseList.get(i).setUserIdentity(userIdentity);
            }
        }
        return courseList;
    }

    /**
     * 查询直播课程列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTitle
     * @return
     */
    @RequestMapping("/showZbkcList")
    @ResponseBody
    public List<Course> showZbkcList(int pageNum,int pageSize,String courseTitle){
        // 从session中获取用户类型：1代表普通用户，2代表会员
        int userIdentity = 0;
        User user = getCurrUser();
        if(user != null){
            userIdentity = user.getUserIdentity();
        }
        List<Course> courseList = iCourseService.searchAllCourseList(pageNum,pageSize,"",courseTitle,"","zbkc",0);
        if(courseList != null && courseList.size() > 0){
            for(int i = 0;i < courseList.size();i++){
                courseList.get(i).setUserIdentity(userIdentity);
            }
        }
        return courseList;
    }

    /**
     * 查询文字课程列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTitle
     * @return
     */
    @RequestMapping("/showWzkcList")
    @ResponseBody
    public List<Course> showWzkcList(int pageNum,int pageSize,String courseTitle){
        // 从session中获取用户类型：1代表普通用户，2代表会员
        int userIdentity = 0;
        User user = getCurrUser();
        if(user != null){
            userIdentity = user.getUserIdentity();
        }
        List<Course> courseList = iCourseService.searchAllCourseList(pageNum,pageSize,"",courseTitle,"","wzkc",0);
        if(courseList != null && courseList.size() > 0){
            for(int i = 0;i < courseList.size();i++){
                courseList.get(i).setUserIdentity(userIdentity);
            }
        }
        return courseList;
    }

    /**
     * 查询音频课程列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTitle
     * @return
     */
    @RequestMapping("/showYpkcList")
    @ResponseBody
    public List<Course> showYpkcList(int pageNum,int pageSize,String courseTitle){
        // 从session中获取用户类型：1代表普通用户，2代表会员
        int userIdentity = 0;
        User user = getCurrUser();
        if(user != null){
            userIdentity = user.getUserIdentity();
        }
        List<Course> courseList = iCourseService.searchAllCourseList(pageNum,pageSize,"",courseTitle,"","ypkc",0);
        if(courseList != null && courseList.size() > 0){
            for(int i = 0;i < courseList.size();i++){
                courseList.get(i).setUserIdentity(userIdentity);
            }
        }
        return courseList;
    }

    /**
     * 查询江湖大课列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTitle
     * @return
     */
    @RequestMapping("/showJhdkList")
    @ResponseBody
    public List<Course> searchJhfkList(int pageNum,int pageSize,String courseTitle){
        // 从session中获取用户类型：1代表普通用户，2代表会员
        int userIdentity = 0;
        User user = getCurrUser();
        if(user != null){
            userIdentity = user.getUserIdentity();
        }
        List<Course> courseList = iCourseService.searchAllCourseList(pageNum,pageSize,"",courseTitle,"","jhdk",0);
        if(courseList != null && courseList.size() > 0){
            for(int i = 0;i < courseList.size();i++){
                courseList.get(i).setUserIdentity(userIdentity);
            }
        }
        return courseList;
    }

    /**
     * 查询正在进行中的课程列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showOnLiveList")
    @ResponseBody
    public List<Course> showOnLiveList(int pageNum,int pageSize){
        // 从session中获取userId
        String userId = "";
        int userIdentity = 0;
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
            userIdentity = user.getUserIdentity();
        }
        List<Course> courseList = iCourseService.searchLtcList(pageNum,pageSize,userId);
        if(courseList != null && courseList.size() > 0){
            for(int i = 0;i < courseList.size();i++){
                courseList.get(i).setUserIdentity(userIdentity);
            }
        }
        return courseList;
    }

    /**
     * 根据课程ID查询课程信息
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/searchCourseById")
    @ResponseBody
    public Course searchCourseById(String courseId){

        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }

        // 根据课程ID查询课程信息
        Course course = iCourseService.searchCourseById(courseId);

        String teacherId = "";
        if(course != null){
            teacherId = course.getTeacherId();
        }

        // 当前课程的讲师是否已被当前用户关注
        int attentionVal = 0;

        // 当前课程是否已被当前用户收藏
        int collectVal = 0;

        // 当前课程是否已被当前用户购买
        int orderVal = 0;

        // 获取当前登录用户身份(1代表普通用户，2代表会员)
        int userIdentity = 0;

        if(!"".equals(userId)){
            attentionVal = iCourseService.searchAttCount(userId,teacherId);
            collectVal = iCourseService.searchCollCount(userId,courseId);
            orderVal = iCourseService.searchOrderCount(userId,courseId);
            userIdentity = iUserService.searchUserIdentityByUserId(userId);
        }

        // 当前课程被评论的总条数
        int totalCcCount = iCourseService.searchCcCount(courseId);

        // 获取课程所对应的考卷ID
        String kjId = iCourseService.selectKjIdByCId(courseId);

        // 如果课程对象不为空，则把相应附加属性设置进去
        if(course != null){

            if("".equals(userId)){
                course.setViewFinishStatus(0);
                course.setPauseDuration(0);
            }else{
                // 当前课程是否已被当前用户学完
                CourseFinishRecord courseFinishRecord = iCourseFinishRecordService.selectByCourseIdAndUserId(userId,courseId);
                if(courseFinishRecord == null){
                    course.setViewFinishStatus(0);
                    course.setPauseDuration(0);
                }else{
                    int finishStatus = 0;
                    if(courseFinishRecord.getRecordStatus() !=null){
                        finishStatus = courseFinishRecord.getRecordStatus();
                    }
                    course.setViewFinishStatus(finishStatus);
                    course.setPauseDuration(courseFinishRecord.getPauseDuration());
                }
            }

            // 设置是否有课程考题
            int ktYnFlag = iCourseService.searchCtCountByCId(courseId);
            if(ktYnFlag > 0){
                course.setKtYnFlag(1);
            }else{
                course.setKtYnFlag(0);
            }

            // 如果有考题
            if(ktYnFlag > 0){
                if("".equals(userId)){
                    course.setKjId(kjId);// 为课程设置考卷ID
                    course.setDtFlag(0);// 0代表未答过题
                }else{
                    // 当前课程是否已被当前用户进行了答题操作
                    int dtVal = iCourseService.searchDtCount(courseId,userId);
                    // 当前课程是否已被当前用户答题
                    if(dtVal > 0){// 是否答过题
                        course.setDtFlag(1);// 1代表已答过题
                    }else{
                        course.setKjId(kjId);// 为课程设置考卷ID
                        course.setDtFlag(0);// 0代表未答过题
                    }
                }
            }

            // 当前课程讲师是否已被当前用户关注
            if(attentionVal > 0){
                course.setAttentionFlag(1);
            }else{
                course.setAttentionFlag(0);
            }

            // 当前课程是否已被当前用户收藏
            if(collectVal > 0){
                course.setCollectFlag(1);
            }else{
                course.setCollectFlag(0);
            }

            // 当前课程是否已被当前用户购买
            if(orderVal > 0){
                course.setOrderFlag(1);
            }else{
                course.setOrderFlag(0);
            }

            // 当前课程被评论的总条数
            course.setTotalCcCount(totalCcCount);

            // 当前登录用户身份(1代表普通用户，2代表会员)
            course.setUserIdentity(userIdentity);

            // 如果是视频或音频，则返回相应播放路径
            if(course.getVideoId() != null && !"".equals(course.getVideoId())){
                String wyUrlVal = iCourseService.getVideoUrl(Integer.parseInt(course.getVideoId()));
                course.setWyUrlVal(wyUrlVal);
            }

            // 设置文字课程是否观看标记，默认是1，即未观看
            int versionVal = 1;

            // 如果是文字课程，则返回文字课程相应的内容路径
            if(course.getCourseContent() != null && !"".equals(course.getCourseContent())){
                String contentUrl = jtxyappUrl + "/api/course/searchWzkcContent?courseId="+courseId;
                course.setCourseContent(contentUrl);
                if(StringUtils.isNotBlank(userId)){
                    // 获取文字课程是否观看标记
                    if(iCourseService.searchWzkcFlag(courseId,userId) != null){
                        versionVal = iCourseService.searchWzkcFlag(courseId,userId).intValue();
                    }
                }
            }

            course.setVersion(versionVal);

            // 给讲师的个性签名解码
            if(StringUtils.isNotBlank(course.getSignature())){
                try {
                    String signatureStr  = java.net.URLDecoder.decode(course.getSignature(), "UTF-8");
                    course.setSignature(signatureStr);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }

        return course;

    }

    /**
     * 根据课程ID查询课程评论
     *
     * @param pageNum
     * @param pageSize
     * @param courseId
     * @return
     */
    @RequestMapping("/searchCcList")
    @ResponseBody
    public List<CourseCommentVO> searchCcList(int pageNum,int pageSize,String courseId){
        List<CourseCommentVO> ccList = iCourseService.searchCcList(pageNum,pageSize,courseId);
        if(ccList != null && ccList.size() > 0){
            for(int i = 0;i < ccList.size();i++){
                if(ccList.get(i) != null){
                    try {
                        // 展示之前进行解码
                        if(StringUtils.isNotBlank(ccList.get(i).getCommentContent())){
                            String ccStr = java.net.URLDecoder.decode(ccList.get(i).getCommentContent(), "UTF-8");
                            ccList.get(i).setCommentContent(ccStr);
                        }
                        if(StringUtils.isNotBlank(ccList.get(i).getAccname())){
                            String accnameStr = java.net.URLDecoder.decode(ccList.get(i).getAccname(), "UTF-8");
                            ccList.get(i).setAccname(accnameStr);
                        }
                        if(StringUtils.isNotBlank(ccList.get(i).getSignature())){
                            String signatureStr = java.net.URLDecoder.decode(ccList.get(i).getSignature(), "UTF-8");
                            ccList.get(i).setSignature(signatureStr);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    ccList.get(i).setCrList(iCourseService.searchCrList(ccList.get(i).getCourseCommentId()));
                }
            }
        }
        return ccList;
    }

    /**
     * 评论课程
     *
     * @param courseId
     * @param commentContent
     * @param commentLevel
     * @return
     */
    @RequestMapping("/addComm")
    @ResponseBody
    public String addComm(String courseId,String commentContent,String commentLevel){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String resultStr = "0";
        if(StringUtils.isNotBlank(commentContent)){
            if(commentContent.length() > 100){
                resultStr = "评论内容不能超过100字";
            }
        }
        CourseComment courseComment = new CourseComment();
        String ccId = UUID.randomUUID().toString().replace("-", "");
        courseComment.setCourseCommentId(ccId);
        courseComment.setCourseId(courseId);
        try {
            // 存入数据库前进行转码
            courseComment.setCommentContent(java.net.URLEncoder.encode(commentContent, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        courseComment.setUserId(userId);
        courseComment.setCommentTime(new Date().getTime());
        int clVal = 1;// 默认好评
        if(StringUtils.isNotBlank(commentLevel)){
            clVal = Integer.parseInt(commentLevel);
        }
        courseComment.setCommentLevel(clVal);
        boolean flagVal = iCourseService.addComm(courseComment);
        if(!flagVal){
            resultStr = "评论失败";
        }
        return resultStr;
    }

    /**
     * 收藏课程
     *
     * @param courseId
     * @param flagVal
     * @return
     */
    @RequestMapping("/addCc")
    @ResponseBody
    public String addCc(String courseId,String flagVal){
        String resultStr = "0";
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        CourseCollect courseCollect = new CourseCollect();
        courseCollect.setCourseId(courseId);
        courseCollect.setUserId(userId);
        // 0代表取消收藏，1代表收藏
        if("0".equals(flagVal)){
            boolean flag = iCourseService.removeCc(courseCollect);
            if(!flag){
                resultStr = "取消收藏课程失败";
            }
        }else{
            String ccId = UUID.randomUUID().toString().replace("-", "");
            courseCollect.setCollectId(ccId);
            boolean flag = iCourseService.addCc(courseCollect);
            if(!flag){
                resultStr = "收藏课程失败";
            }
        }
        return resultStr;
    }

    /**
     * 关注讲师
     *
     * @param teacherId
     * @param isFocus
     * @return
     */
    @RequestMapping("/fcTea")
    @ResponseBody
    public String fcTea(String teacherId,String isFocus){
        String resultStr = "0";
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        int focusOrCancelResult = 0;
        if(iSearchService.getAPITeacherUserByUserId(teacherId) == null){
            resultStr = "该教头用户不存在";
        }
        if(iUserService.selectById(userId) == null){
            resultStr = "非法会员ID";
        }
        if(teacherId.equals(userId)){
            resultStr ="不能关注自己!";
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
        if(focusOrCancelResult == 0){
            if("0".equals(isFocus)){
                resultStr = "取消关注失败";
            }else{
                resultStr = "关注失败";
            }
        }
        return resultStr;
    }

    /**
     * 验证支付密码，0代表没有支付密码，1代表支付密码不正确，2代表支付密码正确
     *
     * @param payPass
     * @return
     */
    @RequestMapping("/searchUserPpCount")
    @ResponseBody
    public Message<Integer> searchUserPpCount(String payPass){
        int userPpCount = 0;
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String payPassStr = iCourseService.searchUserPayPass(userId);
        if(payPassStr != null && !"".equals(payPassStr)){
            boolean flagVal = iCourseService.searchUserPpCount(userId, DigestUtils.md5Hex(payPass));
            if(flagVal){
                userPpCount = 2;
            }else{
                userPpCount = 1;
            }
        }else{
            Message<Integer> message = MessageUtils.getSuccess("未设置支付密码");
            message.setData(userPpCount);
            return message;
        }
        String returnStr = "";
        if(userPpCount == 1){
            returnStr = "支付密码错误";
        }else{
            returnStr = "success";
        }
        Message<Integer> message = MessageUtils.getSuccess(returnStr);
        message.setData(userPpCount);
        message.setResult(1);
        return message;
    }

    /**
     * 查找用户支付密码
     *
     * @return
     */
    @RequestMapping("/searchUserPayPass")
    @ResponseBody
    public Message<String> searchUserPayPass(){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String payPassStr = iCourseService.searchUserPayPass(userId);
        Message<String> message = MessageUtils.getSuccess("success");
        message.setData(payPassStr);
        return message;
    }

    /**
     * 进行课程订单信息的查询
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/searchCo")
    @ResponseBody
    public CourseOrderVO searchCo(String courseId){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        return iCourseService.searchCo(courseId,userId);
    }

    /**
     * 生成课程订单
     *
     * @param courseId
     * @param payType
     * @param totalMoney
     * @return
     */
    @RequestMapping("/addCo")
    @ResponseBody
    public JsVo addCo(String courseId, String payType, String totalMoney){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        int pageTypeVal = 0;
        if(payType != null && !"".equals(payType)){
            pageTypeVal = Integer.parseInt(payType);
        }
        double totalMoneyVal = 0;
        if(totalMoney != null && !"".equals(totalMoney)){
            totalMoneyVal = Double.parseDouble(totalMoney);
        }
        if(pageTypeVal == 0){// 如果是银两支付成功的话，则先验证用户账户银两是否充足
            if(totalMoneyVal > 0){
                UserCapital userCapital = iSearchService.selectUserCapitalByUserId(userId);
                if(userCapital == null || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(0))<=0)
                        || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(String.valueOf(Double.parseDouble(totalMoney)*10)))<=0)){
                    JsVo jv = new JsVo();
                    jv.setCourseOrderNo("余额不足");
                    jv.setResultVal(0);
                    return jv;
                }
            }
        }
        CourseOrder courseOrder = new CourseOrder();
        courseOrder.setCourseId(courseId);
        courseOrder.setUserId(userId);
        courseOrder.setPayType(pageTypeVal);
        courseOrder.setTotalMoney(totalMoneyVal);
        JsVo jsVo = iCourseService.addOrder(courseOrder);
        return jsVo;
    }

    /**
     * 会员可以直接查看免费课程
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/addVipOrder")
    @ResponseBody
    public String addVipOrder(String courseId){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        String resultStr = "1";
        CourseOrder courseOrder = new CourseOrder();
        courseOrder.setCourseId(courseId);
        courseOrder.setUserId(userId);
        courseOrder.setPayType(0);
        courseOrder.setTotalMoney(0.00);
        courseOrder.setPayStatus(1);
        courseOrder.setOrderStatus(1);
        JsVo jsVo = iCourseService.addOrder(courseOrder);
        if(jsVo != null && jsVo.getResultVal() == 1){
            resultStr = "0";
        }
        return resultStr;
    }

    /**
     * 根据课程ID更新文字课程文章是否观看的标记
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/modifyWzkcFlag")
    @ResponseBody
    public String modifyWzkcFlag(String courseId){
        String resultStr = "1";
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        boolean flagVal = iCourseService.modifyWzkcFlag(courseId,userId);
        if(flagVal){
            resultStr = "0";
        }
        return resultStr;
    }

    /**
     * 课程观看记录
     *
     * @param isEnd 是否结束，0 否 1 是
     * @param courseId 课程Id
     * @param seeTime 观看时长（秒）
     * @param courseTime 课程时长（秒）
     * @return
     */
    @RequestMapping("/operCourseFinishRecord")
    @ResponseBody
    public String operCourseFinishRecord(String isEnd,String courseId,String seeTime,String courseTime){

        String resultStr = "1";

        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }

        CourseFinishRecord courseFinishRecord = iCourseFinishRecordService.selectByCourseIdAndUserId(userId,courseId);

        int result = 0;

        if(courseFinishRecord != null){
            Integer _pauseDuration = courseFinishRecord.getPauseDuration();
            Integer pauseDuration = Integer.valueOf(seeTime);
            if(pauseDuration>_pauseDuration){
                courseFinishRecord.setPauseDuration(pauseDuration);
            }else{
                courseFinishRecord.setPauseDuration(null);
            }
            courseFinishRecord.setRecordStatus((short)Integer.valueOf(isEnd).intValue());
            if(StringUtils.isNotBlank(courseTime)){
                courseFinishRecord.setDuration(Integer.valueOf(courseTime));
            }else{
                courseFinishRecord.setDuration(null);
            }
            result = iCourseFinishRecordService.updateByPrimaryKeySelective(courseFinishRecord);
        }else{
            courseFinishRecord = new CourseFinishRecord();
            courseFinishRecord.setPauseDuration(Integer.valueOf(seeTime));
            if(StringUtils.isNotBlank(courseTime)){
                courseFinishRecord.setDuration(Integer.valueOf(courseTime));
            }else{
                courseFinishRecord.setDuration(null);
            }
            courseFinishRecord.setCourseId(courseId);
            courseFinishRecord.setUserId(userId);
            courseFinishRecord.setRecordStatus((short)Integer.valueOf(isEnd).intValue());
            result = iCourseFinishRecordService.insertSelective(courseFinishRecord);
        }

        if(result == 1){
            resultStr = "0";
        }

        return resultStr;

    }

    /**
     * 举报
     *
     *
     * @return
     */
    @RequestMapping("/report")
    @ResponseBody
    public String report(){
        return "0";
    }

    /**
     * 直播间发送礼物
     *
     * @param imRoomId
     * @param giftId
     * @param giftNum
     * @return
     */
    @RequestMapping("/sendGift")
    @ResponseBody
    public String sendGift(String imRoomId, String giftId, String giftNum){

        String resultStr = "0";

        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }

        LiveRoom room = iChatRoomService.spectatorByRoomId(imRoomId);
        if(room == null){
            resultStr = "该直播间不存在";
        }

        String teacherId = room.getUserId();
        if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
            resultStr = "该教头用户不存在";
        }

        Gift gift = new Gift(giftId);
        gift = iChatRoomService.getGiftByGiftId(giftId);
        if(gift == null){
            resultStr = "礼物不存在";
        }

        // 查询会员用户资金记录
        BigDecimal money = new BigDecimal(giftNum).multiply(gift.getVirtualMoney());
        UserCapital userCapital = iSearchService.selectUserCapitalByUserId(userId);
        if(userCapital == null || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(0))<=0)
                || (userCapital != null && userCapital.getTael().compareTo(money) <= 0)){
            resultStr = "余额不足";
        }
        int i = iChatRoomService.gifBrushLog(userId,teacherId,giftId,giftNum,imRoomId);
        if (i == 0){
            resultStr = "发送礼物失败";
        }
        return resultStr;
    }

    /**
     * 根据课程ID查询该课程的订单数量
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/searchCoCountByCId")
    @ResponseBody
    public int searchCoCountByCId(String courseId){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        return iCourseService.searchCoCountByCId(courseId,userId);
    }

    /**
     * 根据直播间ID查询直播间信息
     *
     * @param imRoomId
     * @return
     */
    @RequestMapping(value = "/searchRoomInfo")
    @ResponseBody
    public SpectatorLiveRoomVO searchRoomInfo(String imRoomId){
        SpectatorLiveRoomVO vo = new SpectatorLiveRoomVO();
        LiveRoom room = iChatRoomService.spectatorByRoomId(imRoomId);
        if(room == null){
            vo.setName("0");
        }else{
            vo.setPullUrl(room.getRtmppullurl());
            vo.setHlspullurl(room.getHlspullurl());
            vo.setHttppullurl(room.getHttppullurl());
            vo.setType((short)room.getType());
            vo.setRoomId(room.getImRoomId());
            vo.setName(room.getRoomName());
            Random random = new Random();
            // 使用随机数生成在线人数
            // int baseNum = random.nextInt(316)%(316-203+1)+203;
            // int stepNum = random.nextInt(11)%(11-3+1)+3;
            // vo.setOnlineUserCount(Long.valueOf(baseNum+stepNum*room.getOnlineusercount()));
            vo.setOnlineUserCount(Long.valueOf(room.getOnlineusercount()));
        }
        return vo;
    }

}