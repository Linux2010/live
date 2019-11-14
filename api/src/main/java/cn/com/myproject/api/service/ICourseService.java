package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.*;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.live.entity.VO.CourseReplyVO;
import cn.com.myproject.live.entity.VO.JsVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-28
 * desc：课程Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseService {

    @GetMapping("/course/searchAllCourseList")
    List<Course> searchAllCourseList(@RequestParam("pageNum") int pageNum,
                                     @RequestParam("pageSize") int pageSize,
                                     @RequestParam("courseTypeId") String courseTypeId,
                                     @RequestParam("courseTitle") String courseTitle,
                                     @RequestParam("zbType") String zbType,
                                     @RequestParam("typeVal") String typeVal,
                                     @RequestParam("courseTypeLevel") int courseTypeLevel);

    @GetMapping("/course/searchCourseById")
    Course searchCourseById(@RequestParam("courseId") String courseId);

    @GetMapping("/course/searchCcList")
    List<CourseCommentVO> searchCcList(@RequestParam("pageNum") int pageNum,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam("courseId") String courseId);

    @GetMapping("/course/searchCrList")
    List<CourseReplyVO> searchCrList(@RequestParam("ccId") String ccId);

    @PostMapping("/course/addComm")
    boolean addComm(@RequestBody CourseComment courseComment);

    @GetMapping("/course/searchAttCount")
    int searchAttCount(@RequestParam("uId") String uId, @RequestParam("tId") String tId);

    @GetMapping("/course/searchCollCount")
    int searchCollCount(@RequestParam("uId") String uId, @RequestParam("cId") String cId);

    @GetMapping("/course/searchOrderCount")
    int searchOrderCount(@RequestParam("uId") String uId, @RequestParam("cId") String cId);

    @PostMapping("/course/addCc")
    boolean addCc(@RequestBody CourseCollect courseCollect);

    @PostMapping("/course/removeCc")
    boolean removeCc(@RequestBody CourseCollect courseCollect);

    @PostMapping("/course/getVideoUrl")
    String getVideoUrl(@RequestParam("vId") int vId);

    @GetMapping("/course/searchCo")
    CourseOrderVO searchCo(@RequestParam("courseId") String courseId, @RequestParam("userId") String userId);

    @PostMapping("/course/addOrder")
    JsVo addOrder(@RequestBody CourseOrder courseOrder);

    @GetMapping("/course/searchUserPayPass")
    String searchUserPayPass(@RequestParam("userId") String userId);

    @GetMapping("/course/searchUserPpCount")
    boolean searchUserPpCount(@RequestParam("userId") String userId, @RequestParam("payPass") String payPass);

    @PostMapping("/course/modifyOrderStatus")
    boolean modifyOrderStatus(@RequestBody CourseOrder courseOrder);

    @PostMapping("/course/shareCourse")
    boolean shareCourse(@RequestBody CourseShare courseShare);

    @GetMapping("/course/searchLtcList")
    List<Course> searchLtcList(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam("userId") String userId);

    @GetMapping("/course/searchMyCourseList")
    List<Course> searchMyCourseList(@RequestParam("pageNum") int pageNum,
                                    @RequestParam("pageSize") int pageSize,
                                    @RequestParam("flagVal") int flagVal,
                                    @RequestParam("userId") String userId);

    @GetMapping("/course/searchMyCollectCourseList")
    List<Course> searchMyCollectCourseList(@RequestParam("pageNum") int pageNum,
                                           @RequestParam("pageSize") int pageSize,
                                           @RequestParam("userId") String userId);

    @PostMapping("/course/removeCoByNo")
    int removeCoByNo(@RequestParam("courseOrderNo") String courseOrderNo);

    @GetMapping("/course/searchMyCourseCount")
    int searchMyCourseCount(@RequestParam("userId") String userId);

    @GetMapping("/course/searchMyCcCount")
    int searchMyCcCount(@RequestParam("userId") String userId);

    @GetMapping("/course/getCourseByOrderUserId")
    List<Course> getCourseByOrderUserId(@RequestParam("pageNum") int pageNum,
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam("userId") String userId);

    @GetMapping("/course/modifyPayStatus")
    int modifyPayStatus(@RequestParam("courseOrderNo") String courseOrderNo,@RequestParam("transactionId") String transactionId);

    @GetMapping("/course/searchDtCount")
    int searchDtCount(@RequestParam("courseId") String courseId, @RequestParam("userId") String userId);

    @GetMapping("/course/searchCcCount")
    int searchCcCount(@RequestParam("courseId") String courseId);

    @GetMapping("/course/buyCourseNum")
    int buyCourseNum(@RequestParam("userId") String userId);

    @GetMapping("/course/buyCourseTeacherNum")
    int buyCourseTeacherNum(@RequestParam("userId") String userId);

    @GetMapping("/course/searchCtCountByCId")
    int searchCtCountByCId(@RequestParam("courseId") String courseId);

    @GetMapping("/course/searchCourseInfoById")
    Course searchCourseInfoById(@RequestParam("courseId") String courseId);

    @GetMapping("/course/selectKjIdByCId")
    String selectKjIdByCId(@RequestParam("courseId") String courseId);

    @PostMapping("/course/modifyWzkcFlag")
    boolean modifyWzkcFlag(@RequestParam("courseId") String courseId,@RequestParam("userId") String userId);

    @PostMapping("/course/selectCourseType")
    String selectCourseType(@RequestParam("courseId") String courseId);

    @GetMapping("/course/searchWzkcFlag")
    Integer searchWzkcFlag(@RequestParam("courseId") String courseId,@RequestParam("userId") String userId);

    @GetMapping("/course/searchCoCountByCId")
    int searchCoCountByCId(@RequestParam("courseId") String courseId,@RequestParam("userId") String userId);

}