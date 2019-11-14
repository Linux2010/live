package cn.com.myproject.course.service;

import cn.com.myproject.live.entity.PO.*;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.live.entity.VO.CourseReplyVO;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程Service接口
 */
public interface ICourseService {

    /**
     * 根据课程ID查询文字课程文章是否观看的标记
     *
     * @param courseId
     * @param userId
     * @return
     */
    Integer searchWzkcFlag(String courseId,String userId);

    /**
     * 根据课程ID更新文字课程文章是否观看的标记
     *
     * @param courseId
     * @param userId
     * @return
     */
    boolean modifyWzkcFlag(String courseId,String userId);

    /**
     * 根据课程ID查询课程信息
     *
     * @param courseId
     * @return
     */
    Course searchCourseInfoById(String courseId);

    /**
     * 发布课程信息
     *
     * @param c
     * @return
     */
    boolean addCourse(Course c);

    /**
     * 根据courseId删除课程信息
     *
     * @param courseId
     * @return
     */
    boolean removeCourse(String courseId);

    /**
     * 修改课程信息
     *
     * @param c
     * @return
     */
    boolean modifyCourse(Course c);

    /**
     * 分页查询课程信息
     *
     * @param pageNum
     * @param pageSize
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @return
     */
    PageInfo<Course> searchCourseList(int pageNum,int pageSize,String typeVal,String courseTitle,String zbType);

    /**
     * API分页查询课程信息
     *
     * @param pageNum
     * @param pageSize
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @return
     */
    List<Course> searchAPICourseList(int pageNum,int pageSize,String typeVal,String courseTitle,String zbType);

    /**
     * API查询直播课程信息
     *
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @return
     */
    List<Course> searchLiveCourseList(String typeVal,String courseTitle,String zbType,String teacherId);

    /**
     * API根据教师ID与TYPE查询课程信息
     *
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @param type
     * @return
     */
    List<Course> searchCourseListByTeacherId(String pageNum,String pageSize, String teacherId,String type);

    /**
     * 查询讲师列表
     *
     * @return
     */
    List<User> searchTeaList();

    /**
     * 根据courseId查询课程信息
     *
     * @param courseId
     * @return
     */
    Course searchCourseById(String courseId);

    /**
     * 修改视频课程内容
     *
     * @param c
     * @return
     */
    boolean modifyVideo(Course c);

    /**
     * 根据courseId查询课程考题
     *
     * @param courseId
     * @return
     */
    List<CourseTopic> searchCtListByCId(String courseId);

    /**
     * 修改文字课程内容
     *
     * @param c
     * @return
     */
    boolean modifyCourseContent(Course c);

    /**
     * 根据courseId查询文字课程内容
     *
     * @param courseId
     * @return
     */
    Course searchCourseContentById(String courseId);

    /**
     * 根据courseId查询课程考题数量
     *
     * @param courseId
     * @return
     */
    int searchCtCountByCId(String courseId);

    /**
     * 查询全部课程信息
     *
     * @param pageNum
     * @param pageSize
     * @param courseTypeId
     * @param courseTitle
     * @param zbType
     * @param typeVal
     * @param courseTypeLevel
     * @return
     */
    List<Course> searchAllCourseList(int pageNum,int pageSize,String courseTypeId,String courseTitle,String zbType,String typeVal,int courseTypeLevel);

    /**
     * 根据课程ID查询课程评论列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseId
     * @return
     */
    List<CourseCommentVO> searchCcList(int pageNum, int pageSize, String courseId);

    /**
     * 根据评论ID查询评论回复列表
     *
     * @param ccId
     * @return
     */
    List<CourseReplyVO> searchCrList(String ccId);

    /**
     * 添加评论
     *
     * @param courseComment
     * @return
     */
    boolean addComm(CourseComment courseComment);

    /**
     * 根据用户ID和讲师ID查询用户是否关注该讲师
     *
     * @param uId
     * @param tId
     * @return
     */
    int searchAttCount(String uId,String tId);

    /**
     * 根据用户ID和课程ID查询用户是否收藏该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    int searchCollCount(String uId,String cId);

    /**
     * 根据用户ID和课程ID查询用户是否已购买该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    int searchOrderCount(String uId,String cId);

    /**
     * 收藏课程
     *
     * @param courseCollect
     * @return
     */
    boolean addCc(CourseCollect courseCollect);

    /**
     * 取消收藏课程
     *
     * @param courseCollect
     * @return
     */
    boolean removeCc(CourseCollect courseCollect);

    /**
     * 根据课程ID和用户ID查询用户课程订单信息
     *
     * @param courseId
     * @param userId
     * @return
     */
    CourseOrderVO searchCo(String courseId,String userId);

    /**
     * 插入订单数据
     *
     * @param courseOrder
     * @return
     */
    boolean addOrder(CourseOrder courseOrder);

    /**
     * 修改订单状态
     *
     * @param courseOrder
     * @return
     */
    boolean modifyOrderStatus(CourseOrder courseOrder);

    /**
     * 根据用户ID查询用户支付密码
     *
     * @param userId
     * @return
     */
    String searchUserPayPass(String userId);

    /**
     * 根据用户ID和用户支付密码判断用户支付密码是否正确
     *
     * @param userId
     * @param payPass
     * @return
     */
    boolean searchUserPpCount(String userId,String payPass);

    /**
     * 根据课程订单编号修改订单支付状态
     *
     * @param courseOrderNo
     * @return
     */
    int modifyPayStatus(String courseOrderNo,String transactionId);

    /**
     * 分享课程
     *
     * @param courseShare
     * @return
     */
    boolean shareCourse(CourseShare courseShare);

    /**
     * 查询最近三天的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> searchLtcList(int pageNum,int pageSize,String userId);

    /**
     * 查询我的课程
     *
     * @param pageNum
     * @param pageSize
     * @param flagVal
     * @param userId
     * @return
     */
    List<Course> searchMyCourseList(int pageNum,int pageSize,int flagVal,String userId);

    /**
     * 查询我收藏的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> searchMyCollectCourseList(int pageNum,int pageSize,String userId);

    /**
     * 根据订单编号删除订单
     *
     * @param courseOrderNo
     * @return
     */
    int removeCoByNo(String courseOrderNo);

    /**
     * 根据用户ID查询我的课程数量
     *
     * @param userId
     * @return
     */
    int searchMyCourseCount(String userId);

    /**
     * 根据用户ID查询我的收藏数量
     *
     * @param userId
     * @return
     */
    int searchMyCcCount(String userId);

    /**
     * 查询所有课程
     *
     * @return
     */
    List<Course> selectAllCourse();

    /**
     * 根据课程ID和用户ID查询用户是否针对该课程答过题
     *
     * @param courseId
     * @param userId
     * @return
     */
    int searchDtCount(String courseId,String userId);

    /**
     * 查询课程评论总数量
     *
     * @param courseId
     * @return
     */
    int searchCcCount(String courseId);

    int buyCourseNum(String userId);

    int buyCourseTeacherNum(String userId);

    List<Course> getCourseByOrderUserId(int pageNum,int pageSize,String userId);

    List<Course> selectCategory(String type_val);

    String selectCourseType(String courseId);

    /**
     * 根据课程ID查询该课程的订单数量
     *
     * @param courseId
     * @param userId
     * @return
     */
    int searchCoCountByCId(String courseId,String userId);

}