package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.*;
import cn.com.myproject.api.service.user.IUserCapticalService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import cn.com.myproject.user.entity.PO.Advertise;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyp on 2017/10/11 0011.
 */
@Controller
@RequestMapping("/wap/report")
public class WXReportController extends  BaseController{

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserCapticalService userCapticalService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseFinishRecordService courseFinishRecordService;

    @Autowired
    private ICourseShareService courseShareService;

    @Autowired
    private IAdvertiseService advertiseService;


    /**
     * 获取用户积分信息
     * @return
     */
    @RequestMapping("/intellMessage")
    @ResponseBody
    public Map<String,Object> getIntellMessage(){
        Map<String,Object> map = new HashMap<String,Object>();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        try{
            User user1 = userService.selectById(userId);
            //查询用户等级
            UserCapital userCapital = userCapticalService.selectByUserId(user1.getUserId());
            BigDecimal integral = BigDecimal.ZERO;

            if(null != userCapital){
                integral = userCapital.getIntegral() == null?BigDecimal.ZERO:userCapital.getIntegral();
            }

            //用户等级划分
            /*用户等级分为：一星（0-199积分）、二星（200-699积分）、三星（700-1499积分）、四星（1500-2999积分）、五星（3000-9999积分）、白金（10000-29999积分）、钻石（30000积分以上）*/
            map.putAll(getLevelInterval(integral));
            map.put("result","1");
            map.put("message","success");
        }catch (Exception e){
            map.put("result","0");
            map.put("message","fail");
        }
        return map;
    }

    //返回用户级别
    public static Map getLevelInterval(BigDecimal integral){
        Map<String,Object> result = new HashMap<>();

        String lastLevel = "";
        String lowerLevel = "";
        String presentLevel = "";
        long lastIntegral = 0l;
        long lowerIntegral = 0l;
        long presentIntegral = integral.longValue();

        if(0l <=presentIntegral && presentIntegral<= 199l){
            lastLevel = "一星";
            lowerLevel = "二星";
            presentLevel = "一星";
            lastIntegral = 0l;
            lowerIntegral = 199l;
        }else if(200l <=presentIntegral && presentIntegral<= 699l){
            lastLevel = "一星";
            lowerLevel = "三星";
            presentLevel = "二星";
            lastIntegral = 200l;
            lowerIntegral = 699l;
        }else if(700l <=presentIntegral && presentIntegral<= 1499l){
            lastLevel = "二星";
            lowerLevel = "四星";
            presentLevel = "三星";
            lastIntegral = 700l;
            lowerIntegral = 1499l;
        }else if(1500l <=presentIntegral && presentIntegral<= 2999l){
            lastLevel = "三星";
            lowerLevel = "五星";
            presentLevel = "四星";
            lastIntegral = 1500l;
            lowerIntegral = 2999l;
        }else if(3000l <=presentIntegral && presentIntegral<= 9999l){
            lastLevel = "四星";
            lowerLevel = "白金";
            presentLevel = "五星";
            lastIntegral = 3000l;
            lowerIntegral = 9999l;
        }else if(10000l <=presentIntegral && presentIntegral< 29999l){
            lastLevel = "五星";
            lowerLevel = "钻石";
            presentLevel = "白金";
            lastIntegral = 10000l;
            lowerIntegral = 29999l;
        }else if(presentIntegral >= 30000l){
            lastLevel = "白金";
            lowerLevel = "钻石";
            presentLevel = "钻石";
            lastIntegral = 30000l;
            lowerIntegral = 30000l;
        }
        result.put("lastLevel",lastLevel);
        result.put("lowerLevel",lowerLevel);
        result.put("lastIntegral",lastIntegral);
        result.put("lowerIntegral",lowerIntegral);
        result.put("presentLevel",presentLevel);
        result.put("presentInteger",Double.valueOf(new DecimalFormat("#.00").format(integral)));
        return result;
    }

    /**
     * 获取课程分析柱状体数据
     * @return
     */
    @RequestMapping("/histogramMessage")
    @ResponseBody
    public Message histogramMessage(){
        Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        try{

            //已学课程数
            //int finish = courseFinishRecordService.getFinishNum(userId);
            int finish = courseService.buyCourseNum(userId);

            //已学讲师人数
            //int finishTearch = courseFinishRecordService.getFinishTeacherNum(userId);
            int finishTearch = courseService.buyCourseTeacherNum(userId);

            //分享课程次数
            int shareCourseNum = courseShareService.getCourseShareCount(userId);

            //邀请好友人数
            int inviteFriendNum = courseFinishRecordService.searchCuCountByPId(userId);

            //学习总时长
            long totalDuration = courseFinishRecordService.getTotalDuration(userId);

            Map<String,Object> map = new HashMap<>();
            map.put("finish",finish);
            map.put("finishTearch",finishTearch);
            map.put("shareCourseNum",shareCourseNum);
            map.put("inviteFriendNum",inviteFriendNum);
            map.put("totalDuration",totalDuration);
            message = MessageUtils.getSuccess("获取成功");
            message.setData(map);

        }catch(Exception e){
            message = MessageUtils.getFail("获取失败");
        }
        return message;
    }

    /**
     * 学习的课程详情
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/studyCourseMessage")
    @ResponseBody
    public Message index4CourseList(String pageNum,String pageSize){
        Message message = new Message();
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        try{

            if(StringUtils.isBlank(userId)){
                message = MessageUtils.getSuccess("会员Id不能为空！");
                return message;
            }

            int pageNumVal = 0;
            int pageSizeVal = 0;
            if(StringUtils.isNotBlank(pageNum)){
                pageNumVal = Integer.parseInt(pageNum);
            }
            if(StringUtils.isNotBlank(pageSize)){
                pageSizeVal = Integer.parseInt(pageSize);
            }

            List<Course> courseList = courseService.getCourseByOrderUserId(pageNumVal,pageSizeVal,userId);

            List<CourseFinishRecord> courseFinishRecordList = courseFinishRecordService.selectByUserId(userId);

            for(Course course:courseList){
                course.setViewFinishStatus(0);
            }
            for(Course course:courseList){
                for(CourseFinishRecord courseFinishRecord:courseFinishRecordList){
                    if(null != course.getCourseId() && course.getCourseId().equals(courseFinishRecord.getCourseId())){
                        course.setViewFinishStatus(Integer.valueOf(courseFinishRecord.getRecordStatus()));
                    }
                }
            }
            message = MessageUtils.getSuccess("获取成功");
            message.setData(courseList);
        }catch (Exception e){
            message = MessageUtils.getFail("获取失败");
        }
        return message;
    }

    /**
     * 学习广告
     * @return
     */
    @RequestMapping("/indexImg")
    @ResponseBody
    public Message indexImg(){
        try {
            List<Advertise> list = new ArrayList<>();
            list = advertiseService.selectAdverType(2,1);
            Message message = MessageUtils.getSuccess("获取成功！");
            message.setData(list);
            return message;
        }catch (RuntimeException e){
            Message message = MessageUtils.getFail("获取失败！"+e.getMessage());
            return message;
        }
    }
}
