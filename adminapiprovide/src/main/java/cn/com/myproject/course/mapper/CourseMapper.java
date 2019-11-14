package cn.com.myproject.course.mapper;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseCollect;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.user.entity.PO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程Mapper接口
 */
@Mapper
public interface CourseMapper {

    /**
     * 根据课程ID查询该课程的订单数量
     *
     * @param courseId
     * @param userId
     * @return
     */
    int selectCoCountByCId(@Param("courseId") String courseId,@Param("userId") String userId);

    /**
     * 根据课程ID查询文字课程文章是否观看的标记
     *
     * @param courseId
     * @param userId
     * @return
     */
    Integer selectWakcFlag(@Param("courseId") String courseId,@Param("userId") String userId);

    /**
     * 根据课程ID更新文字课程文章是否观看的标记
     *
     * @param courseId
     * @param userId
     * @return
     */
    int updateWzkcFlag(@Param("courseId") String courseId,@Param("userId") String userId);

    /**
     * 根据课程ID查询课程信息
     *
     * @param courseId
     * @return
     */
    Course searchCourseInfoById(@Param("courseId") String courseId);

    /**
     * 插入课程信息
     *
     * @param c
     * @return
     */
    int insertCourse(Course c);

    /**
     * 根据courseId删除课程信息
     *
     * @param courseId
     * @return
     */
    int deleteCourse(@Param("courseId") String courseId);

    /**
     * 修改课程信息
     *
     * @param c
     * @return
     */
    int updateCourse(Course c);

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
    List<Course> searchCourseList(@Param("pageNumKey") int pageNum,
                                         @Param("pageSizeKey") int pageSize,
                                         @Param("typeVal") String typeVal,
                                         @Param("courseTitle") String courseTitle,
                                         @Param("zbType") String zbType);

    /**
     * API分页查询直播课程信息
     *
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @return
     */
    List<Course> searchLiveCourseList(  @Param("typeVal") String typeVal,
                                         @Param("courseTitle") String courseTitle,
                                         @Param("zbType") String zbType,
                                         @Param("teacherId")  String teacherId);

    /**
     * 根据教师ID查询课程信息
     *
     * @param teacherId
     * @return
     */
    List<Course> searchCourseListByTeacherId(@Param("pageNumKey") String pageNum, @Param("pageSizeKey")String pageSize, @Param("teacherId") String teacherId,@Param("type") String type);

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
    List<Course> searchAllCourseList(@Param("pageNumKey") int pageNum,
                                            @Param("pageSizeKey") int pageSize,
                                            @Param("courseTypeId") String courseTypeId,
                                            @Param("courseTitle") String courseTitle,
                                            @Param("zbType") String zbType,
                                            @Param("typeVal") String typeVal,
                                            @Param("courseTypeLevel") int courseTypeLevel);

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
    Course searchCourseById(@Param("courseId") String courseId);

    /**
     * 修改视频课程内容
     *
     * @param c
     * @return
     */
    int updateVideo(Course c);

    /**
     * 根据courseId查询课程考题
     *
     * @param courseId
     * @return
     */
    List<CourseTopic> searchCtListByCId(@Param("courseId") String courseId);

    /**
     * 修改文字课程内容
     *
     * @param c
     * @return
     */
    int updateCourseContent(Course c);

    /**
     * 根据courseId查询文字课程内容
     *
     * @param courseId
     * @return
     */
    Course searchCourseContentById(@Param("courseId") String courseId);

    /**
     * 根据courseId查询课程考题数量
     *
     * @param courseId
     * @return
     */
    int searchCtCountByCId(@Param("courseId") String courseId);

    /**
     * 根据用户ID和讲师ID查询用户是否已关注该讲师
     *
     * @param uId
     * @param tId
     * @return
     */
    int searchAttCount(@Param("uId") String uId,@Param("tId") String tId);

    /**
     * 根据用户ID和课程ID查询用户是否已收藏该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    int searchCollCount(@Param("uId") String uId,@Param("cId") String cId);

    /**
     * 根据用户ID和课程ID查询用户是否已购买该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    int searchOrderCount(@Param("uId") String uId,@Param("cId") String cId);

    /**
     * 插入课程收藏记录
     *
     * @param courseCollect
     * @return
     */
    int insertCc(CourseCollect courseCollect);

    /**
     * 删除课程收藏记录
     *
     * @param courseCollect
     * @return
     */
    int deleteCc(CourseCollect courseCollect);

    /**
     * 根据课程ID查询订单中的课程信息
     *
     * @param courseId
     * @return
     */
    CourseOrderVO searchOrderCourse(@Param("courseId") String courseId);

    /**
     * 根据用户ID查询订单中的用户信息
     *
     * @param userId
     * @return
     */
    CourseOrderVO searchOrderUser(@Param("userId") String userId);

    /**
     * 查询最近三天的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> searchLtcList(@Param("pageNumKey") int pageNum,
                                      @Param("pageSizeKey") int pageSize,
                                      @Param("userId") String userId);

    /**
     * 查询我发布的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> serachMyFbcList(@Param("pageNumKey") int pageNum,
                                        @Param("pageSizeKey") int pageSize,
                                        @Param("userId") String userId);

    /**
     * 查询我购买的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> serachMyGmcList(@Param("pageNumKey") int pageNum,
                                        @Param("pageSizeKey") int pageSize,
                                        @Param("userId") String userId);

    /**
     * 查询我收藏的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> searchMyCcList(@Param("pageNumKey") int pageNum,
                                       @Param("pageSizeKey") int pageSize,
                                       @Param("userId") String userId);

    /**
     * 根据订单编号删除订单
     *
     * @param courseOrderNo
     * @return
     */
    int deleteCoByNo(@Param("courseOrderNo") String courseOrderNo);

    /**
     * 根据用户ID查询用户类型
     *
     * @param userId
     * @return
     */
    Integer searchUserType(@Param("userId") String userId);

    /**
     * 根据用户ID查询发布课程的数量
     *
     * @param userId
     * @return
     */
    int serachMyFbcCount(@Param("userId") String userId);

    /**
     * 根据用户ID查询购买课程的数量
     *
     * @param userId
     * @return
     */
    int serachMyGmcCount(@Param("userId") String userId);

    /**
     * 根据用户ID查询收藏课程的数量
     *
     * @param userId
     * @return
     */
    int searchMyCcCount(@Param("userId") String userId);

    /**
     * 查询所有的课程
     *
     *
     * @return
     */
    List<Course> selectAllCourse();

    /**
     * 获取订单状态为1
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Course> getCourseByOrderUserId(@Param("pageNumKey") int pageNum,
                                               @Param("pageSizeKey") int pageSize,
                                               @Param("userId") String userId);

    /**
     * 根据课程ID和用户ID查询用户是否针对该课程答过题
     *
     * @param courseId
     * @param userId
     * @return
     */
    int searchDtCount(@Param("courseId") String courseId,@Param("userId") String userId);

    /**
     * 后台分页查询课程信息
     *
     * @param pageNum
     * @param pageSize
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @return
     */
    List<Course> selectCourseList(@Param("pageNumKey") int pageNum,
                                  @Param("pageSizeKey") int pageSize,
                                  @Param("typeVal") String typeVal,
                                  @Param("courseTitle") String courseTitle,
                                  @Param("zbType") String zbType);

    int buyCourseNum(String userId);

    int buyCourseTeacherNum(String userId);

    List<Course> selectCategory(String type_val);

    /**
     * 获取课程类型
     * @param courseId
     * @return
     */
    String selectCourseType(@Param("courseId") String courseId);

}