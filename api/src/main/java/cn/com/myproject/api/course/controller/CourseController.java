package cn.com.myproject.api.course.controller;

import cn.com.myproject.api.service.ICourseFinishRecordService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.ISearchService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.*;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.live.entity.VO.JsVo;
import cn.com.myproject.user.entity.PO.UserCapital;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-08-28
 * desc：课程控制器类
 */
@RestController
@RequestMapping(value = "/api/course")
@Api(value="课程",tags = "课程管理")
public class CourseController {

    private static final Logger logger  = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private ICourseService courseSerivce;

    @Autowired
    private ISearchService iSearchService;

    @Autowired
    private ICourseFinishRecordService courseFinishRecordService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     * 根据视频或音频ID查询视频或音频播放路径
     *
     * @param videoId
     * @return
     */
    @PostMapping("/searchVideoUrlByVideoId")
    @ApiOperation(value = "根据视频或音频ID查询视频或音频播放路径", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "videoId",value = "视频或音频Id",
                    required = true, dataType = "String",defaultValue = "16950458")
    })
    public Message<String> searchVideoUrlByVideoId(String videoId){
        String videoUrl = courseSerivce.getVideoUrl(Integer.parseInt(videoId));
        Message<String> message = MessageUtils.getSuccess("success");
        message.setData(videoUrl);
        return message;
    }

    /**
     * 查询课程详情信息
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/searchCourseInfoById")
    @ApiOperation(value = "查询课程详情信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<Course> searchCourseInfoById(String courseId, String userId){

        // 根据课程ID查询课程信息
        Course course = courseSerivce.searchCourseById(courseId);

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

        if(StringUtils.isNotBlank(userId)){
            attentionVal = courseSerivce.searchAttCount(userId,teacherId);
            collectVal = courseSerivce.searchCollCount(userId,courseId);
            orderVal = courseSerivce.searchOrderCount(userId,courseId);
        }

        // 当前课程被评论的总条数
        int totalCcCount = courseSerivce.searchCcCount(courseId);

        // 如果课程对象不为空，则把相应附加属性设置进去
        if(course != null){

            // 当前课程是否已被当前用户学完
            if(StringUtils.isBlank(userId)){
                course.setViewFinishStatus(0);
                course.setPauseDuration(0);
            }else{
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
            }

            // 设置是否有课程考题
            int ktYnFlag = courseSerivce.searchCtCountByCId(courseId);
            if(ktYnFlag > 0){
                course.setKtYnFlag(1);
            }else{
                course.setKtYnFlag(0);
            }

            // 如果有考题
            if(ktYnFlag > 0){
                if(StringUtils.isBlank(userId)){
                    course.setDtFlag(0);// 0代表未答过题
                }else{
                    // 当前课程是否已被当前用户进行了答题操作
                    int dtVal = courseSerivce.searchDtCount(courseId,userId);
                    // 当前课程是否已被当前用户答题
                    if(dtVal > 0){// 是否答过题
                        course.setDtFlag(1);// 1代表已答过题
                    }else{
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

            // 如果是文字课程，则返回文字课程相应的内容路径
            if(course.getCourseContent() != null && !"".equals(course.getCourseContent())){
                String contentUrl = jtxyappUrl + "/api/course/searchWzkcContent?courseId="+courseId;
                course.setCourseContent(contentUrl);
            }

        }

        Message<Course> message = MessageUtils.getSuccess("success");
        message.setData(course);
        return message;
    }

    /**
     * 查询我的课程
     *
     * @param pageNum
     * @param pageSize
     * @param flagVal
     * @param userId
     * @return
     */
    @PostMapping("/searchMyCourseList")
    @ApiOperation(value = "查询我的课程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "flagVal", value = "1代表发布的课程，2代表购买的课程",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<List<Course>> searchMyCourseList(String pageNum,String pageSize,String flagVal,String userId){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        int flag = 1;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        if(StringUtils.isNotBlank(flagVal)){
            flag = Integer.parseInt(flagVal);
        }
        // 查询我的课程
        List<Course> cList = courseSerivce.searchMyCourseList(pageNumVal,pageSizeVal,flag,userId);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 查询我的收藏
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @PostMapping("/searchMyCollectCourseList")
    @ApiOperation(value = "查询我的收藏", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "bb25ab0d7412436d8baf7ce53b2fccba")
    })
    public Message<List<Course>> searchMyCollectCourseList(String pageNum,String pageSize,String userId){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        // 查询我的收藏
        List<Course> cList = courseSerivce.searchMyCollectCourseList(pageNumVal,pageSizeVal,userId);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 查询最近三天的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @PostMapping("/searchLtcList")
    @ApiOperation(value = "查询最近三天的课程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<List<Course>> searchLtcList(String pageNum,String pageSize,String userId){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        // 查询最近三天的课程
        List<Course> cList = courseSerivce.searchLtcList(pageNumVal,pageSizeVal,userId);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 查询江湖大课列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTitle
     * @return
     */
    @PostMapping("/searchJhdkList")
    @ApiOperation(value = "查询江湖大课列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "courseTitle", value = "课程名称",
                    required = false, dataType = "String",defaultValue = "")
    })
    public Message<List<Course>> searchJhdkList(String pageNum,String pageSize,String courseTitle){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        // 查询江湖大课列表信息
        List<Course> cList = courseSerivce.searchAllCourseList(pageNumVal,pageSizeVal,"",courseTitle,"","jhdk",0);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 查询课程列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseTypeId
     * @param courseTitle
     * @param zbType
     * @param courseTypeLevel
     * @param typeVal
     * @return
     */
    @PostMapping("/searchAllCourseList")
    @ApiOperation(value = "查询课程列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "courseTypeId", value = "课程分类ID",
                    required = false, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "courseTitle", value = "课程名称",
                    required = false, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "zbType", value = "直播类型：sp代表视频，yp代表音频",
                    required = false, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "courseTypeLevel", value = "课程分类级别：默认按二级分类查询，如果输入1，则按一级分类查询课程",
                    required = false, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "typeVal",
                    value = "课程类别：spkc指视频课程，wzkc指文字课程，ypkc指音频课程，zbkc指直播课程",
                    required = false, dataType = "String",defaultValue = "")
    })
    public Message<List<Course>> searchAllCtList(String pageNum,String pageSize,String courseTypeId,String courseTitle,String zbType,String courseTypeLevel,String typeVal){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        int courseTlVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        if(StringUtils.isNotBlank(courseTypeLevel)){
            courseTlVal = Integer.parseInt(courseTypeLevel);
        }
        List<Course> cList = courseSerivce.searchAllCourseList(pageNumVal,pageSizeVal,courseTypeId,courseTitle,zbType,typeVal,courseTlVal);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 根据课程ID查询课程信息
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/searchCourseById")
    @ApiOperation(value = "根据课程ID查询课程信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<Course> searchCourseById(String courseId, String userId){

        // 根据课程ID查询课程信息
        Course course = courseSerivce.searchCourseById(courseId);

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

        if(StringUtils.isNotBlank(userId)){
            attentionVal = courseSerivce.searchAttCount(userId,teacherId);
            collectVal = courseSerivce.searchCollCount(userId,courseId);
            orderVal = courseSerivce.searchOrderCount(userId,courseId);
        }

        // 当前课程被评论的总条数
        int totalCcCount = courseSerivce.searchCcCount(courseId);

        // 如果课程对象不为空，则把相应附加属性设置进去
        if(course != null){

            // 当前课程是否已被当前用户学完
            if(StringUtils.isBlank(userId)){
                course.setViewFinishStatus(0);
                course.setPauseDuration(0);
            }else{
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
            }

            // 设置是否有课程考题
            int ktYnFlag = courseSerivce.searchCtCountByCId(courseId);
            if(ktYnFlag > 0){
                course.setKtYnFlag(1);
            }else{
                course.setKtYnFlag(0);
            }

            // 如果有考题
            if(ktYnFlag > 0){
                if(StringUtils.isBlank(userId)){
                    course.setDtFlag(0);// 0代表未答过题
                }else{
                    // 当前课程是否已被当前用户进行了答题操作
                    int dtVal = courseSerivce.searchDtCount(courseId,userId);
                    // 当前课程是否已被当前用户答题
                    if(dtVal > 0){// 是否答过题
                        course.setDtFlag(1);// 1代表已答过题
                    }else{
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

            // 如果是视频或音频，则返回相应播放路径
            if(course.getVideoId() != null && !"".equals(course.getVideoId())){
                String wyUrlVal = courseSerivce.getVideoUrl(Integer.parseInt(course.getVideoId()));
                course.setWyUrlVal(wyUrlVal);
            }

            // 如果是文字课程，则返回文字课程相应的内容路径
            if(course.getCourseContent() != null && !"".equals(course.getCourseContent())){
                String contentUrl = jtxyappUrl + "/api/course/searchWzkcContent?courseId="+courseId;
                course.setCourseContent(contentUrl);
            }

        }

        Message<Course> message = MessageUtils.getSuccess("success");
        message.setData(course);
        return message;
    }

    /**
     * 根据课程ID查询课程评论列表
     *
     * @param courseId
     * @return
     */
    @PostMapping("/searchCcList")
    @ApiOperation(value = "根据课程ID查询课程评论列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15")
    })
    public Message<List<CourseCommentVO>> searchCcList(String pageNum,String pageSize,String courseId){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<CourseCommentVO> ccList = courseSerivce.searchCcList(pageNumVal,pageSizeVal,courseId);
        if(ccList != null && ccList.size() > 0){
            for(int i = 0;i < ccList.size();i++){
                if(ccList.get(i) != null){
                    try {
                        // 展示之前进行解码
                        String ccStr = java.net.URLDecoder.decode(ccList.get(i).getCommentContent(), "UTF-8");
                        ccList.get(i).setCommentContent(ccStr);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    ccList.get(i).setCrList(courseSerivce.searchCrList(ccList.get(i).getCourseCommentId()));
                }
            }
        }
        Message<List<CourseCommentVO>> message = MessageUtils.getSuccess("success");
        message.setData(ccList);
        return message;
    }

    /**
     * 课程评论：0代表评论课程失败，1代表评论课程成功
     *
     * @param courseId
     * @param commentContent
     * @param userId
     * @return
     */
    @PostMapping("/addComm")
    @ApiOperation(value = "课程评论：0代表评论课程失败，1代表评论课程成功", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "commentContent",value = "评论内容",
                    required = true, dataType = "String",defaultValue = "课程内容不错"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "评论用户的ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "commentLevel",value = "评论级别：1是好评，2是差评",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public Message<Integer> addComm(String courseId,String commentContent,String userId,String commentLevel){
        if(StringUtils.isNotBlank(commentContent)){
            if(commentContent.length() > 100){
                Message<Integer> message = MessageUtils.getFail("评论内容不能超过100字");
                message.setData(0);
                return message;
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
        boolean flagVal = courseSerivce.addComm(courseComment);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 收藏课程：0代表收藏课程失败，1代表收藏课程成功
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/addCc")
    @ApiOperation(value = "收藏课程：0代表收藏课程失败，1代表收藏课程成功", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<Integer> addCc(String courseId,String userId){
        CourseCollect courseCollect = new CourseCollect();
        String ccId = UUID.randomUUID().toString().replace("-", "");
        courseCollect.setCollectId(ccId);
        courseCollect.setCourseId(courseId);
        courseCollect.setUserId(userId);
        boolean flagVal = courseSerivce.addCc(courseCollect);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 取消收藏课程，0代表取消收藏失败，1代表取消收藏成功
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/removeCc")
    @ApiOperation(value = "取消收藏课程，0代表取消收藏失败，1代表取消收藏成功", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<Integer> removeCc(String courseId,String userId){
        CourseCollect courseCollect = new CourseCollect();
        courseCollect.setCourseId(courseId);
        courseCollect.setUserId(userId);
        boolean flagVal = courseSerivce.removeCc(courseCollect);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 根据用户ID和课程ID查询用户课程订单
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/searchCo")
    @ApiOperation(value = "根据用户ID和课程ID查询用户课程订单", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<CourseOrderVO> searchCo(String courseId, String userId){
        CourseOrderVO courseOrderVO = courseSerivce.searchCo(courseId,userId);
        Message<CourseOrderVO> message = MessageUtils.getSuccess("success");
        message.setData(courseOrderVO);
        return message;
    }

    /**
     * 会员可以直接查看免费课程
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/addVipOrder")
    @ApiOperation(value = "会员可以直接查看免费课程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<JsVo> addVipOrder(String courseId, String userId){
        CourseOrder courseOrder = new CourseOrder();
        courseOrder.setCourseId(courseId);
        courseOrder.setUserId(userId);
        courseOrder.setPayType(0);
        courseOrder.setTotalMoney(0.00);
        courseOrder.setPayStatus(1);
        courseOrder.setOrderStatus(1);
        JsVo jsVo = courseSerivce.addOrder(courseOrder);
        Message<JsVo> message = null;
        if(jsVo != null && jsVo.getResultVal() == 1){
            message = MessageUtils.getSuccess("success");
            message.setResult(1);
        }else{
            message = MessageUtils.getFail("fail");
            message.setResult(0);
        }
        message.setData(jsVo);
        return message;
    }

    /**
     * 结算，0代表结算失败，1代表结算成功
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/addOrder")
    @ApiOperation(value = "结算，0代表结算失败，1代表结算成功", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "payType",value = "支付类型：0：银两支付1:支付宝 2:微信3：paypal",
                    required = true, dataType = "String",defaultValue = "0"),
            @ApiImplicitParam(paramType="query",name = "totalMoney",value = "总金额",
                    required = true, dataType = "String",defaultValue = "100")
    })
    public Message<JsVo> addOrder(String courseId, String userId, String payType, String totalMoney){
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
                    Message<JsVo> message = MessageUtils.getFail("余额不足");
                    JsVo jv = new JsVo();
                    jv.setCourseOrderNo("");
                    jv.setResultVal(0);
                    message.setData(jv);
                    message.setResult(1);
                    return message;
                }
            }
        }
        CourseOrder courseOrder = new CourseOrder();
        courseOrder.setCourseId(courseId);
        courseOrder.setUserId(userId);
        courseOrder.setPayType(pageTypeVal);
        courseOrder.setTotalMoney(totalMoneyVal);
        JsVo jsVo = courseSerivce.addOrder(courseOrder);
        Message<JsVo> message = null;
        if(jsVo != null && jsVo.getResultVal() == 1){
            message = MessageUtils.getSuccess("success");
            message.setResult(1);
        }else{
            message = MessageUtils.getFail("fail");
            message.setResult(0);
        }
        message.setData(jsVo);
        return message;
    }

    /**
     * 根据课程ID查询文字课程内容
     *
     * @param courseId
     * @return
     */
    @GetMapping("/searchWzkcContent")
    @ApiOperation(value = "根据课程ID查询文字课程内容，APP不可使用", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15")
    })
    public ModelAndView searchWzkcContent(String courseId){
        ModelAndView view = new ModelAndView("/course/wzkcContent");
        Course course = courseSerivce.searchCourseById(courseId);
        String wzkcContent = "";
        if(course != null){
            wzkcContent = course.getCourseContent();
        }
        view.addObject("wzkcContent",wzkcContent==null?"暂无内容":wzkcContent);
        return view;
    }

    /**
     * 验证支付密码，0代表没有支付密码，1代表支付密码不正确，2代表支付密码正确
     *
     * @param userId
     * @param payPass
     * @return
     */
    @PostMapping("/searchUserPpCount")
    @ApiOperation(value = "验证支付密码，0代表没有支付密码，1代表支付密码不正确，2代表支付密码正确", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "payPass",value = "用户支付密码",
                    required = true, dataType = "String",defaultValue = "123456")
    })
    public Message<Integer> searchUserPpCount(String userId,String payPass){
        int userPpCount = 0;
        String payPassStr = courseSerivce.searchUserPayPass(userId);
        if(payPassStr != null && !"".equals(payPassStr)){
            boolean flagVal = courseSerivce.searchUserPpCount(userId, DigestUtils.md5Hex(payPass));
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
     * 课程分享，0代表分享失败，1代表分享成功
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/shareCourse")
    @ApiOperation(value = "课程分享，0代表分享失败，1代表分享成功", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseId",value = "课程ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "分享用户的ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<Integer> shareCourse(String courseId,String userId){
        CourseShare courseShare = new CourseShare();
        courseShare.setCourseId(courseId);
        courseShare.setShareUserId(userId);
        int shareTypeVal = 0;// 0:朋友圈;1:微信好友;2：QQ空间;3:QQ好友;4:微博，暂时未用，先默认成0
        courseShare.setShareType(shareTypeVal);
        boolean flagVal = courseSerivce.shareCourse(courseShare);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 取消订单，0代表取消失败，1代表取消成功
     *
     * @param courseOrderNo
     * @return
     */
    @PostMapping("/removeCoByNo")
    @ApiOperation(value = "取消订单，0代表取消失败，1代表取消成功", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "courseOrderNo",value = "课程订单编号",
                    required = true, dataType = "String",defaultValue = "ac991168baeeb4477abbd775d8db36fb5")
    })
    public Message<Integer> removeCoByNo(String courseOrderNo){
        Message<Integer> message = null;
        int resultVal = courseSerivce.removeCoByNo(courseOrderNo);
        if(resultVal == 1){
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 举报
     *
     * @return
     */
    @PostMapping("/report")
    @ApiOperation(value = "举报", produces = "application/json")
    public Message<Integer> report(){
        Message<Integer> message = MessageUtils.getSuccess("success");
        message.setData(1);
        return message;
    }

}