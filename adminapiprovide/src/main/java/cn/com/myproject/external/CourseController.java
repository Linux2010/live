package cn.com.myproject.external;

import cn.com.myproject.course.service.ICourseService;
import cn.com.myproject.course.service.ICourseTopicService;
import cn.com.myproject.enums.OrderEnum;
import cn.com.myproject.live.entity.PO.*;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.live.entity.VO.CourseReplyVO;
import cn.com.myproject.live.entity.VO.JsVo;
import cn.com.myproject.netease.VO.vod.GetVideoVO;
import cn.com.myproject.netease.VO.vod.ResultVod;
import cn.com.myproject.netease.service.IVodService;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.util.OrderUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-08-17
 * desc：课程服务控制器类
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IVodService vodService;

    @Autowired
    private ICourseTopicService iCourseTopicService;

    @PostMapping("/addCourse")
    public boolean addCourse(@RequestBody Course c){
        if(StringUtils.isNotBlank(c.getCourseTitle())){
            String ctId = UUID.randomUUID().toString().replace("-", "");
            c.setCourseId(ctId);// 设置课程的ID
            c.setCreateTime(new Date().getTime());// 默认当前时间
            c.setVersion(1);// 默认第一版本
            c.setStatus((short)1);// 默认有效
        }
        return courseService.addCourse(c);
    }

    @PostMapping("/removeCourse")
    public boolean removeCourse(String courseId){
        return courseService.removeCourse(courseId);
    }

    @PostMapping("/modifyCourse")
    public boolean modifyCourse(@RequestBody Course c){
        return courseService.modifyCourse(c);
    }

    @GetMapping("/searchCourseList")
    public PageInfo<Course> searchCourseList(int pageNum,int pageSize,String typeVal,String courseTitle,String zbType){
        return courseService.searchCourseList(pageNum,pageSize,typeVal,courseTitle,zbType);
    }

    @PostMapping("/searchAPICourseList")
    public List<Course> searchAPICourseList(int pageNum,int pageSize,String typeVal,String courseTitle,String zbType){
        return courseService.searchAPICourseList(pageNum,pageSize,typeVal,courseTitle,zbType);
    }

    @PostMapping("/searchLiveCourseList")
    public List<Course> searchLiveCourseList(String typeVal,String courseTitle,String zbType,String teacherId){
        return courseService.searchLiveCourseList(typeVal,courseTitle,zbType,teacherId);
    }

    @PostMapping("/searchCourseListByTeacherId")
    public List<Course> searchCourseListByTeacherId(String pageNum,String pageSize, String teacherId,String type){
        return courseService.searchCourseListByTeacherId(pageNum,pageSize, teacherId,type);
    }

    @GetMapping("/searchTeaList")
    public List<User> searchTeaList(){
        return courseService.searchTeaList();
    }

    @GetMapping("/searchCourseById")
    public Course searchCourseById(String courseId){
        return courseService.searchCourseById(courseId);
    }

    @GetMapping("/searchCourseInfoById")
    public Course searchCourseInfoById(String courseId){
        return courseService.searchCourseInfoById(courseId);
    }

    @PostMapping("/modifyVideo")
    public boolean modifyVideo(@RequestBody Course c){
        return courseService.modifyVideo(c);
    }

    @PostMapping("/getVideoUrl")
    public String getVideoUrl(int vId){
        String returnStr = "";
        GetVideoVO gvv = new GetVideoVO();
        gvv.setVid(vId);
        long l = System.currentTimeMillis();
        logger.info("开始调用网易服务-获取视频详情");
        ResultVod rvObj =  vodService.getVideo(gvv);
        logger.info("结束调用网易服务-获取视频详情"+(System.currentTimeMillis()-l));
        if(rvObj != null){
            Map<String,Object> returnMap = rvObj.getRet();
            if(returnMap != null){
                if(returnMap.containsKey("sdMp4Url")){
                    returnStr = returnMap.get("sdMp4Url").toString();
                }else{
                    returnStr = returnMap.get("origUrl").toString();
                }
            }
        }
        return returnStr;
    }

    @GetMapping("/searchCtListByCId")
    public List<CourseTopic> searchCtListByCId(String courseId){
        return courseService.searchCtListByCId(courseId);
    }

    @PostMapping("/modifyCourseContent")
    public boolean modifyCourseContent(@RequestBody Course c){
        return courseService.modifyCourseContent(c);
    }

    @GetMapping("/searchCourseContentById")
    public Course searchCourseContentById(String courseId){
        return courseService.searchCourseContentById(courseId);
    }

    @GetMapping("/searchCtCountByCId")
    public int searchCtCountByCId(String courseId){
        return courseService.searchCtCountByCId(courseId);
    }

    @GetMapping("/searchAllCourseList")
    public List<Course> searchAllCourseList(int pageNum,int pageSize,String courseTypeId,String courseTitle,String zbType,String typeVal,int courseTypeLevel){
        return courseService.searchAllCourseList(pageNum,pageSize,courseTypeId,courseTitle,zbType,typeVal,courseTypeLevel);
    }

    @GetMapping("/searchCcList")
    public List<CourseCommentVO> searchCcList(int pageNum, int pageSize, String courseId){
        return courseService.searchCcList(pageNum,pageSize,courseId);
    }

    @GetMapping("/searchCrList")
    public List<CourseReplyVO> searchCrList(String ccId){
        return courseService.searchCrList(ccId);
    }

    @PostMapping("/addComm")
    public boolean addComm(@RequestBody CourseComment courseComment){
        return courseService.addComm(courseComment);
    }

    @GetMapping("/searchAttCount")
    public int searchAttCount(String uId,String tId){
        return courseService.searchAttCount(uId,tId);
    }

    @GetMapping("/searchCollCount")
    public int searchCollCount(String uId,String cId){
        return courseService.searchCollCount(uId,cId);
    }

    @GetMapping("/searchOrderCount")
    public int searchOrderCount(String uId,String cId){
        return courseService.searchOrderCount(uId,cId);
    }

    @PostMapping("/addCc")
    public boolean addCc(@RequestBody CourseCollect courseCollect){
        return courseService.addCc(courseCollect);
    }

    @PostMapping("/removeCc")
    public boolean removeCc(@RequestBody CourseCollect courseCollect){
        return courseService.removeCc(courseCollect);
    }

    @GetMapping("/searchCo")
    public CourseOrderVO searchCo(String courseId,String userId){
        return courseService.searchCo(courseId,userId);
    }

    @PostMapping("/addOrder")
    public JsVo addOrder(@RequestBody CourseOrder courseOrder){
        String coId = UUID.randomUUID().toString().replace("-", "");
        courseOrder.setCourseOrderId(coId);
        courseOrder.setCourseOrderNo(OrderUtil.createOrderNo(OrderEnum.goumaikecheng.getKey()));
        courseOrder.setOrderCreateTime(new Date().getTime());
        courseOrder.setOrderStatus(0);// 待付款
        courseOrder.setPayStatus(0);// 未支付
        courseOrder.setCreateTime(new Date().getTime());
        courseOrder.setStatus((short)1);// 默认有效
        courseOrder.setVersion(1);
        boolean flagVal = courseService.addOrder(courseOrder);
        JsVo jv = new JsVo();
        jv.setCourseOrderNo(courseOrder.getCourseOrderNo());
        if(flagVal){
            jv.setResultVal(1);
        }else{
            jv.setResultVal(0);
        }
        return jv;
    }

    @PostMapping("/modifyOrderStatus")
    public boolean modifyOrderStatus(@RequestBody CourseOrder courseOrder){
        return courseService.modifyOrderStatus(courseOrder);
    }

    @GetMapping("/searchUserPayPass")
    public String searchUserPayPass(String userId){
        return courseService.searchUserPayPass(userId);
    }

    @GetMapping("/searchUserPpCount")
    public boolean searchUserPpCount(String userId,String payPass){
        return courseService.searchUserPpCount(userId,payPass);
    }

    @PostMapping("/shareCourse")
    public boolean shareCourse(@RequestBody CourseShare courseShare){
        return courseService.shareCourse(courseShare);
    }

    @GetMapping("/searchLtcList")
    public List<Course> searchLtcList(int pageNum,int pageSize,String userId){
        return courseService.searchLtcList(pageNum,pageSize,userId);
    }

    @GetMapping("/searchMyCourseList")
    public List<Course> searchMyCourseList(int pageNum,int pageSize,int flagVal,String userId){
        return courseService.searchMyCourseList(pageNum,pageSize,flagVal,userId);
    }

    @GetMapping("/searchMyCollectCourseList")
    public List<Course> searchMyCollectCourseList(int pageNum,int pageSize,String userId){
        return courseService.searchMyCollectCourseList(pageNum,pageSize,userId);
    }

    @PostMapping("/removeCoByNo")
    public int removeCoByNo(String courseOrderNo){
        return courseService.removeCoByNo(courseOrderNo);
    }

    @GetMapping("/searchMyCourseCount")
    public int searchMyCourseCount(String userId){
        return courseService.searchMyCourseCount(userId);
    }

    @GetMapping("/searchMyCcCount")
    public int searchMyCcCount(String userId){
        return courseService.searchMyCcCount(userId);
    }

    @GetMapping("/selectAllCourse")
    public List<Course> selectAllCourse(){
        return courseService.selectAllCourse();
    }

    @GetMapping("/getCourseByOrderUserId")
    public List<Course> getCourseByOrderUserId(int pageNum, int pageSize, String userId){
        return courseService.getCourseByOrderUserId(pageNum,pageSize,userId);
    }

    @GetMapping("/modifyPayStatus")
    public int modifyPayStatus(String courseOrderNo,String transactionId){
        return courseService.modifyPayStatus(courseOrderNo,transactionId);
    }

    @PostMapping("/selectCategory")
    public List<Course> selectCategory(String type_val){
        return courseService.selectCategory(type_val);
    }

    @GetMapping("/searchDtCount")
    public int searchDtCount(String courseId,String userId){
        return courseService.searchDtCount(courseId,userId);
    }

    @GetMapping("/searchCcCount")
    public int searchCcCount(String courseId){
        return courseService.searchCcCount(courseId);
    }

    @GetMapping("/buyCourseNum")
    public int buyCourseNum(String userId){
        return courseService.buyCourseNum(userId);
    }

    @GetMapping("/buyCourseTeacherNum")
    public int buyCourseTeacherNum(String userId){
        return courseService.buyCourseTeacherNum(userId);
    }

    @GetMapping("/selectKjIdByCId")
    public String selectKjIdByCId(String courseId){
        return iCourseTopicService.selectKjIdByCId(courseId);
    }

    @PostMapping("/modifyWzkcFlag")
    public boolean modifyWzkcFlag(String courseId,String userId){
        return courseService.modifyWzkcFlag(courseId,userId);
    }

    @PostMapping("/selectCourseType")
    public String selectCourseType(String courseId){
        return courseService.selectCourseType(courseId);
    }

    @GetMapping("/searchWzkcFlag")
    public Integer searchWzkcFlag(String courseId,String userId){
        return courseService.searchWzkcFlag(courseId,userId);
    }

    @GetMapping("/searchCoCountByCId")
    public int searchCoCountByCId(String courseId,String userId){
        return courseService.searchCoCountByCId(courseId,userId);
    }

}