package cn.com.myproject.user.mapper;

import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据用户ID查询用户身份(1代表普通用户，2代表会员)
     *
     * @param userId
     * @return
     */
    int selectUserIdentityByUserId(@Param("userId") String userId);

    User selectById(@Param("userId") String userId);

    User selectByPhoneNum(@Param("countryCode") String countryCode,@Param("phone") String phone);

    APITearcherUser selectTeacherUserById(@Param("userId") String userId);

    User selectByLoginName(@Param("counttriesCode") String counttriesCode,@Param("loginName") String loginName);

    User selectByPhone(@Param("counttriesCode") String counttriesCode,@Param("phone") String phone);

    int insert(User user);

    int updateAccId(@Param("userId") String userId, @Param("accid") String accid, @Param("accidToken") String accidToken);

    List<User> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    void updatePay(User user);

    int  updateQrCodeImg(User user);

    void updatePwd(User user);

    List<User> getPageForeach(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,
                              @Param("map") Map<String, Object> map);

    List<User> getCouponUserPageForeach(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,
                              @Param("map") Map<String, Object> map);

    List<Map<String, Object>> getTeacherUsersPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,
                                                  @Param("map") Map<String, Object> map);
    List<APITearcherUser> getAPITeacherUsersPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,
                                                 @Param("map") Map<String, Object> map);

    void change(Integer id);

    void userend(Integer id);

    void userstart(Integer id);

    Integer checktype(Integer id);

    List<User> getUserList();

    List<User> getTweetyUserList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<User> getNonsubordinateProfitShareRelationUserList(@Param("userId") String userId);

    int insertTeacherUser(@Param("map") Map<String, Object> map);

    int updateTeacherUser(@Param("map") Map<String, Object> map);

    Map<String, Object> selectUserByUserId(@Param("userId") String userId);

    int delUserByUserId(@Param("userId") String userId);

    List<Map<String, Object>> selectTeacherUserByUserTypeId(@Param("userTypeId") String userTypeId);

    List<User> getUserListByMap(Map<String, Object> map);

    int checkPhone(String phone);

    void findPwd(User user);

    void updateUser(User user);

    int checkLoginName(String loginName);

    User selecUserDetail(String userId);

    void findPay(User user);

    List<User> selectByUserType(int userType);

    int checkUserNameOnly(@Param("userName") String userName);

    int updateVIP(User user);

    User getUserMessageByIm(@Param("accid") String accid);

    int delUserChatRoomByUserId(@Param("userId") String userId);

    int delUserLiveRoomByUserId(@Param("userId") String userId);

    int checkCodeAndPhone(@Param("countryCode") String countryCode,@Param("phone") String phone);

    int checkPhoneIsExist(User user);

    List<APITearcherUser> searchIndexFourTeachers(Map<String,Object> map);

    User getUserByOpenId(@Param("openId") String openId);

    void bindQQ(User user);

    void changeIdentity(@Param("userIdentity") int userIdentity,@Param("expirationDate") long expirationDate,@Param("userId") String userId);

    User selectUserByPhoneNum(String phone);

    User getUserByWxOpenId(String wxOpenId);

    void bindWx(User user);

    /**
     * 根据用户ID查询用户级别(1是普通用户，2是会员用户)
     *
     * @param userId
     * @return
     */
    String searchUserLevel(@Param("userId") String userId);

    //微博的登录
    User getUserByWeiboOpenId(String weiboOpenId);
    //微博的绑定
    void bindWeiBo(User user);

    /**
     * 查询用户ID和用户名
     *
     * @return
     */
    List<User> selectUserIdAndName();

    /**
     * 更改为教头并且为永久会员
     */

    int change_forever(@Param("expirationDate") long expirationDate,@Param("userTypeId") String userTypeId,@Param("userId") String userId);

    /**
     * 查询用户是否关注公众号
     * @param userId
     * @return
     */
    String selectThePublicOpenId(@Param("userId") String userId);

    /**
     * 记录用户的openId
     * @param user
     * @return
     */
    int updateThePublicOpenId(User user);

    void updatePwdByUserId(User user);

    void updatePayByUserId(User user);

}