package cn.com.myproject.api.wap;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.admincon.IProfitShareRelationService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.VO.MyTeamVO;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jyp on 2017/9/6 0006.
 */
@RequestMapping("/wap/myteam")
@Controller
public class MyTeamController extends BaseController{

    @Autowired
    private IProfitShareRelationService profitShareRelationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @RequestMapping("/")
    public String myteam_index(HttpServletRequest request){
            Message message = new Message();
            // 从session中获取userId
            String userId = "";
            User user = getCurrUser();
            if(user != null){
                userId = user.getUserId();
            }
            User personal_user = userService.selectById(userId);
            String ccStr = "";
            try {
                ccStr = java.net.URLDecoder.decode(personal_user.getSignature(), "UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            personal_user.setSignature(ccStr);
            request.getSession().setAttribute("personal_user",personal_user);//个人信息
            ProfitShareRelation superior_user =  profitShareRelationService.getUpLevelUser(userId);
            if(superior_user != null){
                   String ccStr1 = "";
                   try {
                       ccStr1 = java.net.URLDecoder.decode(superior_user.getSignature(), "UTF-8");
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                   superior_user.setSignature(ccStr1);
            }
            request.getSession().setAttribute("superior_user",superior_user);//上级用户
            List<ProfitShareRelation> second_user = profitShareRelationService.getSecondLevelUser(1,3,userId);
            request.getSession().setAttribute("second_user",second_user);//二级用户
            int third_user_count = profitShareRelationService.getThreeLevelUserCount(userId);//三级用户人数
            request.getSession().setAttribute("third_user_count",third_user_count);
            request.getSession().setAttribute("result","success");
            return "/wap/myTeam";
    }

    @RequestMapping("/second_more_list")
    public ModelAndView myteam_more_index(){
        ModelAndView model = new ModelAndView("/wap/myteam_second_more_list");
        return model;
    }

    /**
     * 我的二级用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_second_more_list")
    public Message get_second_more_list(@RequestParam(value = "pageNum",defaultValue = "1") String pageNum,@RequestParam(value = "pageSize",defaultValue = "27") String pageSize){
        Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
       /* List<ProfitShareRelation> second_user = profitShareRelationService.getSecondLevelUser(Integer.valueOf(pageNum),Integer.valueOf(pageSize),userId);*/
        Map<String ,Object> second_user = profitShareRelationService.getSecondLevelUserMap(Integer.valueOf(pageNum),Integer.valueOf(pageSize),userId);
        message = MessageUtils.getSuccess("success");
        message.setData(second_user);//二级用户
        return message;
    }

    /**
     * 我的二级用户全部列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_second_more_list1")
    public Message get_second_more_list1(){
        Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        List<ProfitShareRelation> second_user = profitShareRelationService.getSecondLevelUserAll(userId);
        message = MessageUtils.getSuccess("success");
        message.setData(second_user);//二级用户
        return message;
    }

    //我的收藏页面
    @RequestMapping("/myCollection")
    public ModelAndView myCollection(){
        ModelAndView view = new ModelAndView("/my/myCollection");
        return view;
    }

    //我的收藏列表
    @RequestMapping("/myCollectionList")
    @ResponseBody
    public List<Course> myCollectionList(int pageNum, int pageSize){

        String userId = "";
        User user = getCurrUser();
        if (user != null){
            userId = user.getUserId();
        }
        List<Course> collectCourseList = courseService.searchMyCollectCourseList(pageNum, pageSize, userId);
        return collectCourseList;
    }

    //我的课程页面
    @RequestMapping("/myCourse")
    public ModelAndView myCourse(){

        ModelAndView view = new ModelAndView("/my/mykecheng");
        int userType = 0;
        User user = getCurrUser();
        if (user != null){
            userType = user.getUserType();
            view.addObject("userType", userType);
        }
        return view;
    }

    //我的课程列表
    @RequestMapping("/myCourseList")
    @ResponseBody
    public List<Course> myCourseList(int pageNum, int pageSize, int flag){

        String userId = "";
        User user = getCurrUser();
        if (user != null){
            userId = user.getUserId();
        }
        List<Course> myCourseList;
        if (flag == 1){//1代表查询的发布课程列表
            myCourseList= courseService.searchMyCourseList(pageNum, pageSize, flag, userId);
        }else {//2代表我购买的课程
            myCourseList= courseService.searchMyCourseList(pageNum, pageSize, flag, userId);
        }
        return myCourseList;
    }
}
