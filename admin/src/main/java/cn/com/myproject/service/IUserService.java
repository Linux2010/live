package cn.com.myproject.service;


import cn.com.myproject.sysuser.entity.PO.SysRegion;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserTeacherIntroImg;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * Created by liyang-macbook on 2017/7/26.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IUserService {

    @GetMapping("/user/getPage")
    public PageInfo<User> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/user/updatePwd")
    void updatePwd(@RequestBody User user);

    @PostMapping("/user/updatePay")
    void updatePay(@RequestBody User user);

    @PostMapping("/user/updateQrCodeImg")
    int  updateQrCodeImg(@RequestBody User user);

    @PostMapping("/user/getPageForeach")
    PageInfo <User> getPageForeach(@RequestBody Map<String,Object> map);

    @PostMapping("/user/getCouponUserPageForeach")
    PageInfo <User> getCouponUserPageForeach(@RequestBody Map<String,Object> map);

    @PostMapping("/user/change")
    void change(@RequestBody Integer id);

    @PostMapping("/user/userend")
    void userend(@RequestBody Integer id);

    @PostMapping("/user/userstart")
    void userstart(@RequestBody Integer id);

    @PostMapping("/user/checktype")
    Integer checktype(@RequestBody Integer id);

    @GetMapping("/user/getUserList")
    List<User> getUserList();

    @GetMapping("/user/getTweetyUserList")
    List<User> getTweetyUserList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @GetMapping("/user/getNonsubordinateProfitShareRelationUserList")
    public  List<User> getNonsubordinateProfitShareRelationUserList(@RequestParam("userId")  String userId);

    @PostMapping("/user/getTeacherUsersPage")
    PageInfo<Map<String,Object>> getTeacherUsersPage(@RequestBody Map<String,Object> map);

    @PostMapping("/user/insertTeacherUser")
   //int  insertTeacherUser(@RequestParam("data") String data,@RequestParam("userId") String userId); //此种方式仍和GET提交一样有数据大小限制
    int  insertTeacherUser(@RequestBody Map<String,String> map);

    @PostMapping("/user/updateTeacherUser")
  //  int  updateTeacherUser(@RequestParam("data") String data,@RequestParam("userId")  String userId);
    int  updateTeacherUser(@RequestBody Map<String,String> map);

    @PostMapping("/user/selectUserByUserId")
    Map<String,Object> selectUserByUserId(@RequestParam("userId")  String userId);

    @PostMapping("/user/delUserByUserId")
    int delUserByUserId(@RequestParam("userId")  String userId);

    @PostMapping("/user/selectTeacherUserByUserTypeId")
    List<Map<String,Object>> selectTeacherUserByUserTypeId(@RequestParam("userTypeId") String userTypeId);

    @PostMapping("/user/selectByUserType")
    List<User> selectByUserType(@RequestParam("userType") int userType);

    @PostMapping("/user/getLabelName")
    String getLabelName(@RequestParam("labelId") String labelId);

    @PostMapping("/user/selectById")
    User selectById(@RequestParam("userId")String userId);

    @PostMapping("/user/user_lable_name")
    void user_lable_name(@RequestBody User user);

    @PostMapping("/user/checkLoginName")
    int checkLoginName(@RequestParam("loginName") String loginName);

    @PostMapping("/user/checkPhoneIsExist")
    public int checkPhoneIsExist(@RequestBody User user);

    @GetMapping("/sysregion/select_countries_code")
    public List<SysRegion> select_countries_code();

    @PostMapping("/user/changeIdentity")
    public int changeIdentity(@RequestParam("userIdentity") int userIdentity,@RequestParam("expirationDate") long expirationDate,@RequestParam("userId") String userId);

    @GetMapping("/course/searchMyCourseCount")
    public int searchMyCourseCount(@RequestParam("userId") String userId);

    @PostMapping("/user/searchUserTeacherIntroImgList")
    public PageInfo<UserTeacherIntroImg> searchUserTeacherIntroImgList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize ,@RequestParam("teacherId") String teacherId );

    /**
     * 插入教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    @PostMapping("/user/insertUserTeacherIntroImg")
    public int insertUserTeacherIntroImg(@RequestBody UserTeacherIntroImg userTeacherIntroImg);

    /**
     * 根据ID删除教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    @PostMapping("/user/deleteUserTeacherIntroImg")
    public int deleteUserTeacherIntroImg(@RequestParam("teacherIntroImgId") String teacherIntroImgId);


    @PostMapping("/user/createImg")
    public String  createImg(@RequestBody User user,@RequestParam("shareImg") String shareImg,@RequestParam("imgNumber") int imgNumber);

    @PostMapping("/user/change_forever")
    public int change_forever(@RequestParam("expirationDate") long expirationDate,@RequestParam("userTypeId") String userTypeId,@RequestParam("userId") String userId);

    @PostMapping("/user/selectByPhoneNum")
    public User selectByPhoneNum(@RequestParam("countryCode") String countryCode, @RequestParam("phone")  String phone );

    @PostMapping("/user/batchAllUserQrCodeImg")
    public boolean  batchAllUserQrCodeImg();

}
