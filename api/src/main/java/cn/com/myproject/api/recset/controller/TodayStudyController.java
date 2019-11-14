package cn.com.myproject.api.recset.controller;

import cn.com.myproject.api.service.ICourseFinishRecordService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import cn.com.myproject.live.entity.PO.CourseOrder;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JYP on 2017/9/2 0002.
 */
@RestController
@RequestMapping("/api/today/study/detail")
@Api(value="今日学习详情",tags = "今日学习详情")
public class TodayStudyController {

    @Autowired
    private ICourseFinishRecordService courseFinishRecordService;

    @Autowired
    private ICourseService courseService;


    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    @PostMapping("/getTodayStudyDetails")
    @ApiOperation(value = "今日学习课程详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "87c707e10d744d78b21633bb035dd4a4"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "7a8f8ccf1cbb47dcacf0eb72446bb766")
    })
    public Message getTodayStudyDetails(String courseId, String userId, HttpServletRequest request){
       if(StringUtils.isBlank(userId)){
           return MessageUtils.getFail("tokenIsNull");
       }else{
           Course course = courseService.searchCourseById(courseId);
           int dtVal = courseService.searchDtCount(courseId,userId);
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
           if(dtVal > 0){// 是否答过题
               course.setDtFlag(1);// 1代表已答过题
           }else{
               course.setDtFlag(0);// 0代表未答过题
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



           Message message = MessageUtils.getSuccess("success");
           message.setData(course);
           return message;
       }
    }
}
