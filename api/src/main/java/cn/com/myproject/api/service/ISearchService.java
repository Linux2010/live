package cn.com.myproject.api.service;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.PO.*;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by LeiJia  2017/08/28
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface ISearchService {

    /**
     * 分页查询课程列表
     * @param pageNum
     * @param pageSize
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @return
     */
    @PostMapping("/course/searchAPICourseList")
    public List<Course> searchAPICourseList(@RequestParam("pageNum") int pageNum,
                                             @RequestParam("pageSize") int pageSize,
                                             @RequestParam("typeVal") String typeVal,
                                             @RequestParam("courseTitle") String courseTitle,
                                             @RequestParam("zbType") String zbType);

    /**
     * 查询讲师直播课程列表
     * @param typeVal
     * @param courseTitle
     * @param zbType
     * @param teacherId
     * @return
     */
    @PostMapping("/course/searchLiveCourseList")
    public List<Course> searchLiveCourseList( @RequestParam("typeVal") String typeVal,
                                            @RequestParam("courseTitle") String courseTitle,
                                            @RequestParam("zbType") String zbType,
                                            @RequestParam("teacherId") String teacherId);

    @PostMapping("/course/searchCourseListByTeacherId")
    public List<Course> searchCourseListByTeacherId(@RequestParam("pageNum") String pageNum,@RequestParam("pageSize") String pageSize, @RequestParam("teacherId") String teacherId, @RequestParam("type")String type );

    /**
     * 分页查询教头用户列表
     * @param map
     * @return
     */
    @PostMapping("/user/getAPITeacherUsersPage")
    public List<APITearcherUser> getAPITeacherUsersPage(@RequestBody Map<String,Object> map);

    /**
     * 首页前四位教头
     * @param map
     * @return
     */
    @PostMapping("/user/searchIndexFourTeachers")
    public List<APITearcherUser> searchIndexFourTeachers(@RequestBody Map<String,Object> map);


    /**
     * 查询教头详细
     * @param userId
     * @return
     */
    @PostMapping("/user/selectTeacherUserByUserId")
    public APITearcherUser getAPITeacherUserByUserId(@RequestParam("userId") String userId);


    /**
     * 查询用户关注教头对象
     * @param focus
     * @return
     */
    @PostMapping("/user/getUserTeacherFocus")
    public  UserTeacherFocus getUserTeacherFocus(@RequestBody UserTeacherFocus focus);


    /**
     * 添加用户关注教头对象
     * @param focus
     * @return
     */
    @PostMapping("/user/insertUserTeacherFocus")
    public  int insertUserTeacherFocus(@RequestBody UserTeacherFocus focus);


    /**
     * 更新用户关注教头对象
     * @param focus
     * @return
     */
    @PostMapping("/user/updateUserTeacherFocus")
    public  int updateUserTeacherFocus(@RequestBody UserTeacherFocus focus);

    /**
     * 查询用户资金记录
     * @param userId
     * @return
     */
    @GetMapping("/usercapital/selectByUserId")
    public  UserCapital selectUserCapitalByUserId(@RequestParam("userId") String userId);

    /**
     * 向教头用户打赏
     * @param teacherId
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/user/giveTeahcerReward")
    public  Map<String,Object> giveTeahcerReward(@RequestParam("teacherId") String teacherId,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("money") String money,
                                  @RequestParam("payWay") String payWay);

    /**
     * 根据教师ID和用户ID查询问答列表
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @param userId
     * @return
     */
    @PostMapping("/user/questionAndReplyList")
    public List<UserTeacherQuestion> questionAndReplyList(@RequestParam("pageNum") int pageNum,
                                                          @RequestParam("pageSize") int pageSize,
                                                          @RequestParam("teacherId") String teacherId,
                                                          @RequestParam("userId") String userId);

    /**
     * 记录教头详情-问题-用户提问记录
     * @param teacherId
     * @param userId
     * @param question
     * @return
     */
    @PostMapping("/user/insertUserAnswerTeacherQuestion")
    public int insertUserAnswerTeacherQuestion( @RequestParam("teacherId") String teacherId,
                                                @RequestParam("userId") String userId,
                                                @RequestParam("question") String question);

    /**
     * 记录消息列表-教头答复
     * @param teacherId
     * @param reply
     * @return
     */
    @PostMapping("/user/insertTeacherReplyUserQuestion")
    public int insertTeacherReplyUserQuestion( @RequestParam("teacherId") String teacherId,
                                               @RequestParam("reply") String reply,
                                               @RequestParam("userTeacherQuestionId") String userTeacherQuestionId);

    @GetMapping("/userType/searchTeacherTypeList")
    public List<UserType> searchUserTypeList(@RequestParam("typeVal") int typeVal,
                                             @RequestParam("parentId") String parentId ,
                                             @RequestParam("level") String level);

    /**
     * 教头发布课数量程统计
     * @param userId
     * @return
     */
    @GetMapping("/course/searchMyCourseCount")
    public int searchMyCourseCount(@RequestParam("userId") String userId);

    /**
     * 根据教头用户ID查询教头简介图片URL列表
     * @param teacherId
     * @return
     */
    @GetMapping("/user/searchTeacherIntroImgs")
    public List<String> searchTeacherIntroImgs(@RequestParam("teacherId") String teacherId);

    /**
     * 消息列表-用户提问问题列表或向用户提问问题列表
     * @param userId
     * @param type
     * @return
     */
    @PostMapping("/user/questionOrReplyList")
    public List<UserTeacherQuestion> questionOrReplyList(@RequestParam("pageNum") int pageNum,
                                                         @RequestParam("pageSize") int pageSize,
                                                         @RequestParam("userId") String userId ,
                                                         @RequestParam("type") String type);

 }
