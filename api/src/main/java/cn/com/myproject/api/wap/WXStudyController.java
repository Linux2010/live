package cn.com.myproject.api.wap;

import cn.com.myproject.api.redis.IRedisService;
import cn.com.myproject.api.service.*;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import cn.com.myproject.user.entity.PO.Advertise;
import cn.com.myproject.user.entity.PO.Article;
import cn.com.myproject.user.entity.PO.GoodArticle;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.VO.StudyTodayVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jyp on 2017/10/9 0009.
 */
@Controller
@RequestMapping("/wap/study")
public class WXStudyController extends BaseController{

    private static  final Logger logger = LoggerFactory.getLogger(WXStudyController.class);

    @Autowired
    private IRecSettingsService recSettingsService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodArticleService goodArticleService;

    @Autowired
    private IRecSettingsService settingsService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseFinishRecordService courseFinishRecordService;

    @Autowired
    private IAdvertiseService advertiseService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     * 跳转到学习页面
     * @return
     */
    @RequestMapping("/study_page")
    public ModelAndView to_study_page(){
        ModelAndView model = new ModelAndView("/study/study");
        return  model;
    }

    /**
     * 跳转到学习-推荐页面
     * @return
     */
    @RequestMapping("/recommend_page")
    public ModelAndView to_tuijian_page(){
        ModelAndView model = new ModelAndView("/study/tuijian");
        return  model;
    }

    /**
     * 跳转到学习-报告页面
     * @return
     */
    @RequestMapping("/report_page")
    public ModelAndView to_report_page(){
        ModelAndView model = new ModelAndView("/study/report");
        return model;
    }
    /**
     * 学习-今日 数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/study_today")
    public ModelAndView study_today(@RequestParam(value = "pageNum",defaultValue = "1") String pageNum,@RequestParam(value = "pageSize",defaultValue = "3")  String pageSize){
        ModelAndView model = new ModelAndView("/study/study");
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        if(StringUtils.isBlank(userId)){
            //StudyTodayVO data = new StudyTodayVO();
            int pageNumVal = 0;
            int pageSizeVal = 0;
            if(StringUtils.isNotBlank(pageNum)){
                pageNumVal = Integer.parseInt(pageNum);
            }

            if(StringUtils.isNotBlank(pageSize)){
                pageSizeVal = Integer.parseInt(pageSize);
            }

            model.addObject("message","你暂时还没有登录，请先登录!");

            List<Course> excecourlist = settingsService.select_today_excellentcour(pageNumVal,pageSizeVal);//优质课程
            List<GoodArticle> goodarticlelist = goodArticleService.select_good_article(pageNumVal,pageSizeVal); //好文推荐
            //优质课程
            model.addObject("excecourlist",excecourlist);
            //好文推荐
            model.addObject("goodarticlelist",goodarticlelist);
            //今日学习
            model.addObject("todaystudy","");

            model.addObject("todayrecomlist","");
            return model;
        }else{
            model.addObject("message","success");
            //StudyTodayVO data = new StudyTodayVO();
            int pageNumVal = 0;
            int pageSizeVal = 0;
            if(StringUtils.isNotBlank(pageNum)){
                pageNumVal = Integer.parseInt(pageNum);
            }

            if(StringUtils.isNotBlank(pageSize)){
                pageSizeVal = Integer.parseInt(pageSize);
            }

            List<Course> todayrecomlist = new ArrayList<Course>();
            //判断用户是会员还是普通用户
            User curMemmber = userService.selectById(userId);
            if(curMemmber.getUserIdentity() == 1){//普通用户
                // settingsService.select_today_gcour(pageNumVal,pageSizeVal);//今日课程--随机推送
                todayrecomlist.addAll(settingsService.select_today_gcour(pageNumVal,pageSizeVal));
                //data.setTodayrecomlist(todayrecomlist);//今日课程
                model.addObject("todayrecomlist",todayrecomlist);
            }else if(curMemmber.getUserIdentity() == 2){//会员用户
                if(curMemmber.getLabelId() != null){
                    String s[] = curMemmber.getLabelId().split(",");
                    for(int i= 0;i<s.length;i++){
                        //settingsService.select_today_gcour_by_label(s[i],pageNumVal,pageSizeVal);//今日课程--根据用户标签
                        todayrecomlist.addAll(settingsService.select_today_gcour_by_label(s[i],pageNumVal,pageSizeVal));
                        //data.setTodayrecomlist(todayrecomlist);//今日课程
                        model.addObject("todayrecomlist",todayrecomlist);
                    }
                }else{
                    todayrecomlist.addAll(settingsService.select_today_gcour(pageNumVal,pageSizeVal));
                    //data.setTodayrecomlist(todayrecomlist);//今日课程（没有标签的）
                    model.addObject("todayrecomlist",todayrecomlist);
                }
            }
            List<Course> excecourlist = settingsService.select_today_excellentcour(pageNumVal,pageSizeVal);//优质课程
            List<GoodArticle> goodarticlelist = goodArticleService.select_good_article(pageNumVal,pageSizeVal); //好文推荐
            List<Course> todaystudy = goodArticleService.select_today_study(pageNumVal,pageSizeVal,userId); //今日学习
            for (GoodArticle goodArticle: goodarticlelist) {
                goodArticle.setContentUrl(jtxyappUrl+"/api/recset/goodArticleInfo?goodArticleId="+goodArticle.getGoodArticleId());
            }


            //优质课程
            model.addObject("excecourlist",excecourlist);
            //好文推荐
            model.addObject("goodarticlelist",goodarticlelist);
            //今日学习
            model.addObject("todaystudy",todaystudy);
            return model;
        }
    }

    /**
     * 学习 - 今日 -今日课程更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/today_course_more")
    @ResponseBody
    public Message today_course_more(@RequestParam(value = "pageNum",defaultValue = "1") String pageNum,@RequestParam(value = "pageSize",defaultValue = "10")  String pageSize){
        Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        User curMemmber = userService.selectById(userId);
        List<Course> list = new ArrayList<>();
        if(curMemmber.getUserIdentity() == 1){//普通用户
            list = settingsService.select_today_gcour(pageNumVal,pageSizeVal);
        }else if(curMemmber.getUserIdentity() == 2){//会员用户
            if(curMemmber.getLabelId() != null){
                String s[] = curMemmber.getLabelId().split(",");
                for(int i= 0;i<s.length;i++){
                    list.addAll(settingsService.select_today_gcour_by_label(s[i],pageNumVal,pageSizeVal));
                }
            }else{
                list = settingsService.select_today_gcour(pageNumVal,pageSizeVal);
            }
        }
        message.setData(list);
        return message;
    }

    /**
     * 学习-推荐 数据
     * @return
     */
    @RequestMapping("/today_recomm")
    public ModelAndView today_recomm(){

        int userIdentity = 0;
        User user = getCurrUser();
        if (user != null){
            user = userService.selectById(user.getUserId());
            userIdentity = user.getUserIdentity();
            ModelAndView model = new ModelAndView("/study/tuijian");
            List<User> tealist = settingsService.select_recomm_teacher(1,4);//优质课程
            List<Course> goodreclist = settingsService.select_tui_tcour(1,2);//今日课程
            List<Advertise> advertiseList = advertiseService.selectAdverType(2,1);//学习轮播图
            //<Course> goodselectlist = settingsService.select_today_goods(1,2);//优选商品
            model.addObject("tealist",tealist);
            model.addObject("goodreclist",goodreclist);
            model.addObject("advertiseList", advertiseList);
            model.addObject("userIdentity", userIdentity);
            return model;
        }else{
            userIdentity = 100;
            ModelAndView model = new ModelAndView("/study/tuijian");
            List<User> tealist = settingsService.select_recomm_teacher(1,4);//优质课程
            List<Course> goodreclist = settingsService.select_tui_tcour(1,2);//今日课程
            List<Advertise> advertiseList = advertiseService.selectAdverType(2,1);//学习轮播图
            //<Course> goodselectlist = settingsService.select_today_goods(1,2);//优选商品
            model.addObject("tealist",tealist);
            model.addObject("goodreclist",goodreclist);
            model.addObject("advertiseList", advertiseList);
            model.addObject("userIdentity", userIdentity);
            return model;
        }

    }

    /**
     * 获取文章海报信息
     * @return
     */
    @RequestMapping("/article_list")
    @ResponseBody
    public Message articleList(){
        try {
            Article article = articleService.selectByStatus(2);
            article.setContent(jtxyappUrl+"/api/article/selectArticleContent");
            Message message = MessageUtils.getSuccess("获取成功！");
            message.setData(article);
            return message;
        }catch (RuntimeException e){
            Message message = MessageUtils.getFail("获取失败！"+e.getMessage());
            return message;
        }
    }

    /**
     * 详情
     * @return
     */
    public ModelAndView post_detail(){
        ModelAndView view = new ModelAndView("/article/articleContent");
        Article article = articleService.selectByStatus(2);
        String articleContent = "";
        if(article != null){
            articleContent = article.getContent();
        }
        view.addObject("articleContent",articleContent==null?"暂无内容":articleContent);
        return view;
    }

    /**
     * 今日 - 好文推荐 更多
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping("/good_article_list")
    @ResponseBody
    public Message<List<GoodArticle>> good_article_list(String pageNum, String pageSize,HttpServletRequest request){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<GoodArticle> list = goodArticleService.select_good_article(pageNumVal,pageSizeVal);
        for (GoodArticle goodArticle: list) {
            goodArticle.setContentUrl(jtxyappUrl+"/api/recset/goodArticleInfo?goodArticleId="+goodArticle.getGoodArticleId());
        }
        Message<List<GoodArticle>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    public ModelAndView goodArticleInfo(String goodArticleId){
        ModelAndView modelAndView = new ModelAndView("/article/goodArticleIntro");
        GoodArticle goodArticle  =  goodArticleService.selectById(goodArticleId);
        modelAndView.addObject("goodArticelInfo",goodArticle.getContent()==null?"无简介附件":goodArticle.getContent());
        return modelAndView;
    }

    /**
     * 跳转到学习-好文推荐更多页面
     * @return
     */
    @RequestMapping("/good_article_page")
    public ModelAndView good_article_page(){
        ModelAndView model = new ModelAndView("/study/good_article_more");
        return  model;
    }

    /**
     * 跳转到学习-好文推荐更多页面
     * @return
     */
    @RequestMapping("/today_course_more_page")
    public ModelAndView today_course_more_page(){
        ModelAndView model = new ModelAndView("/study/today_course_more");
        return  model;
    }

    /**
     * 跳转到学习-优质课程更多页面
     * @return
     */
    @RequestMapping("/good_course_more_page")
    public ModelAndView good_course_more_page(){

        int userIdentity = 0;
        User user = getCurrUser();
        if (user != null){
             user = userService.selectById(user.getUserId());
            userIdentity = user.getUserIdentity();
            ModelAndView model = new ModelAndView("/study/today_good_course_more");
            model.addObject("userIdentity", userIdentity);
            return  model;
        }else{
            userIdentity = 100;
            ModelAndView model = new ModelAndView("/study/today_good_course_more");
            model.addObject("userIdentity", userIdentity);
            return  model;
        }

    }

    /**
     * 今日 - 优质课程更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/select_today_exc_list")
    @ResponseBody
    public Message<List<Course>> select_today_exc_list(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<Course> list = settingsService.select_today_excellentcour(pageNumVal,pageSizeVal);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     * 跳转到学习-今日学习更多页面
     * @return
     */
    @RequestMapping("/my_today_course_more_page")
    public ModelAndView my_today_course_more_page(){

        int userIdentity = 0;
        User user = getCurrUser();
        if (user != null){
            user = userService.selectById(user.getUserId());
        }
        userIdentity = user.getUserIdentity();
        ModelAndView model = new ModelAndView("/study/my_course_more");
        model.addObject("userIdentity", userIdentity);
        return  model;
    }

    /**
     * 今日学习 - 更多
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @RequestMapping("/today_study_list")
    @ResponseBody
    public Message<List<Course>> today_study_list(String pageNum, String pageSize){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        if(StringUtils.isBlank(userId)){
            return MessageUtils.getFail("tokenIsNull");
        }else{
            int pageNumVal = 0;
            int pageSizeVal = 0;
            if(StringUtils.isNotBlank(pageNum)){
                pageNumVal = Integer.parseInt(pageNum);
            }
            if(StringUtils.isNotBlank(pageSize)){
                pageSizeVal = Integer.parseInt(pageSize);
            }
            List<Course> list = goodArticleService.select_today_study(pageNumVal,pageSizeVal,userId);
            Message<List<Course>> message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }
    }


    /**
     * 跳转到推荐-优质课程更多页面
     * @return
     */
    @RequestMapping("/excellent_page")
    public ModelAndView excellent_page(){

        int userIdentity = 0;
        User user = getCurrUser();
        if (user != null){
            user = userService.selectById(user.getUserId());
            userIdentity = user.getUserIdentity();
            ModelAndView model = new ModelAndView("/study/excellent_course_more");
            model.addObject("userIdentity", userIdentity);
            return  model;
        }else{
            userIdentity = 100;
            ModelAndView model = new ModelAndView("/study/excellent_course_more");
            model.addObject("userIdentity", userIdentity);
            return  model;
        }

    }


    /**
     * 推荐 - 优质课程更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getCourPage")
    @ResponseBody
    public Message<List<Course>> getCourPage(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        String status = "1";
        List<Course> list = settingsService.select_tui_tcour(pageNumVal,pageSizeVal);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     * 跳转到推荐-精英教头更多页面
     * @return
     */
    @RequestMapping("/teacher_page")
    public ModelAndView teacher_page(){


        ModelAndView model = new ModelAndView("/study/teacher_more");
        return  model;
    }


    /**
     * 推荐 - 精英教头更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/recomm_tea_list")
    @ResponseBody
    public Message<List<User>> recomm_tea_list(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<User> list = settingsService.select_recomm_teacher(pageNumVal,pageSizeVal);
        Message<List<User>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     *优质课程的展示
     * @return
     */
    @RequestMapping("/youzhicourse")
    @ResponseBody
    public List<Course> youzhicourse(){
        List<Course> goodreclist = settingsService.select_tui_tcour(1,2);//今日课程
        return goodreclist;
    }

    /**
     *优质课程的展示
     * @return
     */
    @RequestMapping("/today_detail")
    @ResponseBody
    public ModelAndView today_detail(String courseId){
        ModelAndView model = new ModelAndView("/study/today_course_detail");
        model.addObject("courseId",courseId);
        return model;
    }


    @RequestMapping("/getTodayStudyDetails")
    @ResponseBody
    public Message getTodayStudyDetails(String courseId){
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        if(StringUtils.isBlank(userId)){
            return MessageUtils.getFail("tokenIsNull");
        }else{
            Course course = courseService.searchCourseById(courseId);
            // int dtVal = courseService.searchDtCount(courseId,userId);
            if(course != null){
                if(course.getVideoId() != null && !"".equals(course.getVideoId())){
                    String wyUrlVal = courseService.getVideoUrl(Integer.parseInt(course.getVideoId()));
                    course.setWyUrlVal(wyUrlVal);
                }
                if(course.getCourseContent() != null && !"".equals(course.getCourseContent())){
                    // 先把路径默认成ip路径，以后如果是域名的话，则以配置的形式来写路径
                    // String contentUrl = Constants.urlVal+ "/api/course/searchWzkcContent?courseId="+courseId;
                    String contentUrl = jtxyappUrl+ "/api/course/searchWzkcContent?courseId="+courseId;
                    course.setCourseContent(contentUrl);
                }
            }
           /* if(dtVal > 0){// 是否答过题
                course.setDtFlag(1);// 1代表已答过题
            }else{
                course.setDtFlag(0);// 0代表未答过题
            }*/
            // 设置是否有课程考题
            int ktYnFlag = courseService.searchCtCountByCId(courseId);
            if(ktYnFlag > 0){
                course.setKtYnFlag(1);
            }else{
                course.setKtYnFlag(0);
            }
            // 获取课程所对应的考卷ID
            String kjId = courseService.selectKjIdByCId(courseId);
            // 如果有考题
            if(ktYnFlag > 0){
                // 当前课程是否已被当前用户进行了答题操作
                int dtVal = courseService.searchDtCount(courseId,userId);
                // 当前课程是否已被当前用户答题
                if(dtVal > 0){// 是否答过题
                    course.setDtFlag(1);// 1代表已答过题
                }else{
                    course.setKjId(kjId);// 为课程设置考卷ID
                    course.setDtFlag(0);// 0代表未答过题
                }
            }
            CourseFinishRecord courseFinishRecord = courseFinishRecordService.selectByCourseIdAndUserId(userId,courseId);
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

            //查找类型
            String type_ = courseService.selectCourseType(courseId);
            course.setTyval(type_);
            Message message = MessageUtils.getSuccess("success");
            message.setData(course);
            return message;
        }
    }

}
