package cn.com.myproject.api.report.controller;

import cn.com.myproject.api.service.*;
import cn.com.myproject.api.service.user.IUserCapticalService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @auther CQC
 * @create 2017.9.1
 */

@RestController
@RequestMapping("/api/report")
@Api(value="报告",tags = "报告")
public class ReportController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseFinishRecordService courseFinishRecordService;

    @Autowired
    private ICourseShareService courseShareService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseOrderService courseOrderService;

    @Autowired
    private IUserCapticalService userCapticalService;

    private static final Logger logger = LogManager.getLogger(ReportController.class.getName());

    @PostMapping("/userinfo")
    @ApiOperation(value = "会员信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID",
                    required = true, dataType = "String")
    })
    public Message index4Member(String userId){
        Message message = new Message();
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            User user = userService.selectById(userId);
            String photo = "";
            String signature = "";
            String userName = "";
            if(user != null){
                photo = user.getPhoto();
                signature = user.getSignature();
                userName = user.getUserName();
            }

            //查询用户等级
            UserCapital userCapital = userCapticalService.selectByUserId(user.getUserId());
            BigDecimal integral = BigDecimal.ZERO;

            if(null != userCapital){
                integral = userCapital.getIntegral() == null?BigDecimal.ZERO:userCapital.getIntegral();
            }

            //用户等级划分
            /*用户等级分为：一星（0-199积分）、二星（200-699积分）、三星（700-1499积分）、四星（1500-2999积分）、五星（3000-9999积分）、白金（10000-29999积分）、钻石（30000积分以上）*/
            map.putAll(getLevelInterval(integral));
            map.put("phone",user.getPhone());
            map.put("signature",user.getSignature());
            map.put("userName",userName);
            message = MessageUtils.getSuccess("success");
            message.setData(map);
        }catch (Exception e){
            message = MessageUtils.getFail("获取失败");
        }
        return message;
    }

    @PostMapping("/statis")
    @ApiOperation(value = "获取通知统计", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID",
                    required = true, dataType = "String")
    })
    public Message index4Statis(String userId){
        Message message = new Message();
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

    @PostMapping("/courseOrderList")
    @ApiOperation(value = "获取我的课程列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页条数",required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员ID", required = true, dataType = "String")
    })
    public Message index4CourseList(String pageNum,String pageSize,String userId){
        Message message = new Message();
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

    @PostMapping("/operCourseFinishRecord")
    @ApiOperation(value = "课程观看记录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "isEnd", value = "是否结束，0 否 1 是", required = true, dataType = "String",defaultValue = "0"),
            @ApiImplicitParam(paramType="query",name = "courseId", value = "课程Id",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员Id",required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "seeTime", value = "观看时长（秒）", required = true, dataType = "String",defaultValue = "0"),
            @ApiImplicitParam(paramType="query",name = "courseTime", value = "课程时长（秒）", required = true, dataType = "String",defaultValue = "0")
    })
    public Message operCourseFinishRecord(String isEnd,String courseId,String userId,String seeTime,String courseTime){
        Message message = new Message();
        try{

            CourseFinishRecord courseFinishRecord = courseFinishRecordService.selectByCourseIdAndUserId(userId,courseId);
            int result = 0;

            if(null != courseFinishRecord){

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
                result = courseFinishRecordService.updateByPrimaryKeySelective(courseFinishRecord);
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
                result = courseFinishRecordService.insertSelective(courseFinishRecord);
            }

            if(1==result){
                message = MessageUtils.getSuccess("success");
            }else{
                message = MessageUtils.getFail("操作失败");
            }

        }catch(Exception e){
            message = MessageUtils.getFail("获取失败");
        }
        return message;
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
}
