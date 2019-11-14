package cn.com.myproject.api.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @auther CQC
 * @create 2017.8.28
 */
@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private AssessInterceptor assessInterceptor;

    @Autowired
    private WapInterceptor wapInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration ir = registry.addInterceptor(tokenInterceptor);

     //   ir.addPathPatterns("/**"); //需要拦截的地址

        String[] str = new String[]{"/api/login/**","/error*","/swagger-resources/**","/webjars/**","/swagger-ui.html",
                "/api/search/**",
                "/api/user/findPwd",
                "/api/paycallback/**","/api/wxpay/**",
                "/api/index/advertiseList","/api/courseType/searchAllCtList","/api/course/searchAllCourseList",
                "/api/index/shortVideo",
                "/api/register/**","/static/**","/wap/**","/api/region/**","/api/article/selectArticleContent",
                "/api/sysDoc/**","/api/course/searchWzkcContent","/api/news/searchNewsContent",
                "/api/recset/goodArticleInfo","/api/message/getMessageDetails","/api/information/informationDetail",
                "/api/user/getSoftVersion","/api/rechargeMember/rechargeMember",
                "/api/search/teacherTypeListByUserLevel","/api/search/teacherUserListByUserTypeId",
                "/api/search/searchTeachers","/api/user/sendCode","/api/register/simp_register",
                "/api/search/searchCourses","/api/region/get_countries_code_list","/api/region/get_address_list",
                "/api/register/sendCode","/api/third/login/wxLogin","/api/third/login/weibo_login",
                "/api/third/login/qq_login","/api/third/login/binWx","/api/third/login/user_bind_weibo",
                "/api/third/login/user_bind_qq","/api/payCallBack/**","/api/pay/**","/api/thirdPay/**",
                "/api/chatroom/requestAddress","/api/information/informationIntro","/api/paycallback/wxAppNotify","/api/information/searchInfoList","/api/news/index"};
          ir.excludePathPatterns(str);   //排除掉的拦截地址



        registry.addInterceptor(assessInterceptor);


        //wap站拦截
        InterceptorRegistration ir1 = registry.addInterceptor(wapInterceptor);
        String[] str1 = new String[]{"/wap/wx/login","/wap/wx/logout","/wap/wx/countryNumber","/api/**","/error*",
                "/swagger-resources/**","/webjars/**","/swagger-ui.html",
                "/wap/wx/userLogin","/wap/user/findPwd","/wap/wx/addUser","/wap/user/findPwdToSecond",
                "/wap/user/findPwdbyphone","/wap/user/checkCode","/wap/user/send_code","/wap/wx/sendCode",
                "/wap/wx/registerAndLogin","/wap/wx/notify","/wap/app/help","/wap/search/teacherIntro","/wap/sysDoc/**","/wap/wx/zhuce","/wap/wx/topay","/search/teahcerReward/giveTeahcerRewardFail","/wap/search/teacherTypeListByUserLevel","/wap/search/teacherUserListByUserTypeId",
                "/wap/search/searchTeachers",
                "/wap/index","/wap/course/courseDetail","/wap/search/home/searchIndexFourTeachers","/wap/remchargeMember/vip",
                "/wap/news/newsView","/wap/news/newsList","/wap/news/newsDetail",
                "/wap/course/spkcIndex","/wap/course/zbkcIndex","/wap/course/wzkcIndex",
                "/wap/course/ypkcIndex","/wap/course/jhdkIndex",
                "/wap/course/searchCtList","/wap/course/showSpkcList","/wap/course/showZbkcList",
                "/wap/course/showWzkcList","/wap/course/showYpkcList","/wap/course/showJhdkList",
                "/wap/course/searchCourseById","/wap/course/searchCcList",
                "/wap/news/newsView","/wap/news/newsList","/wap/news/newsDetail",
                "/wap/study/today_recomm","/wap/study/article_list","/wap/study/recomm_tea_list",
                "/wap/study/youzhicourse","/wap/studygetCourPage","/wap/study/study_today","/wap/study/study_page",
                "/wap/study/today_course_more","/wap/study/good_article_list","/wap/study/good_article_page",
                "/wap/study/good_course_more_page","/wap/study/select_today_exc_list","/wap/user/get_num",
                "/wap/study/excellent_page","/wap/study/teacher_page","/wap/study/recomm_tea_list",
                "/wap/study/getCourPage","/wap/search/wellTeacherHome","/wap/search/teacherDetail",
                "/wap/search/courseRelaseDynamicList","/wap/course/showGdMap","/wap/wx/wapnotify",
                "/wap/course/showRoom","/wap/course/searchRoomInfo"};
        ir1.excludePathPatterns(str1);   //排除掉的拦截地址

        super.addInterceptors(registry);
    }
}
