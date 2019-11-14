package cn.com.myproject.user.service;

import cn.com.myproject.user.entity.PO.*;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/30.
 */
public interface IUserService {

    /**
     * 根据用户ID查询用户身份(1代表普通用户，2代表会员)
     *
     * @param userId
     * @return
     */
    int searchUserIdentityByUserId(String userId);

    int reigister(User user);

    User getByLogin(String loginName);

    User selectByPhone(String phone);

    Map<String,Object> login(String counttriesCode, String loginName, String pwd);

    PageInfo<User> getPage(int pageNum, int pageSize);

    PageInfo<Map<String, Object>> getTeacherUsersPage(Map<String, Object> map);

    List<APITearcherUser> getAPITeacherUsersPage(Map<String, Object> map);

    void updatePay(User user);

    void updatePwd(User user);

    int  updateQrCodeImg(User user);

    PageInfo<User> getPageForeach(int pageNum, int pageSize, Map<String, Object> map);

    PageInfo<User> getCouponUserPageForeach(int pageNum, int pageSize, Map<String, Object> map);

    void change(Integer id);

    void userend(Integer id);

    void userstart(Integer id);

    Integer checktype(Integer id);

    List<User> getUserList();

    List<User> getTweetyUserList(int pageNum,  int pageSize);

    List<User> getNonsubordinateProfitShareRelationUserList(String userId);

    int insertTeacherUser(String data, String userId,String photo ,String rectanglePhoto);

    int updateTeacherUser(String data, String userId,String photo,String rectanglePhoto);

    Map<String, Object> selectUserByUserId(String userId);

    int delUserByUserId(String userId);

    List<Map<String, Object>> selectTeacherUserByUserTypeId(String userTypeId);

    List<User> getUserListByMap(Map<String, Object> map);

    int checkPhone(String phone);

    void findPwd(User user);

    void updateUser(User user);

    int checkLoginName(String loginName);

    APITearcherUser selectTeacherUserByUserId(String userId);

    UserTeacherFocus getUserTeacherFocus(UserTeacherFocus focus);

    int insertUserTeacherFocus(UserTeacherFocus focus);

    int updateUserTeacherFocus(UserTeacherFocus focus);

    User selectById(String userId);

    Map<String,Object> giveTeahcerReward( String teacherId,String userId,String money,String payWay);

    List<UserTeacherQuestion> questionAndReplyList(int pageNum, int pageSize, String teacherId, String userId);

    int insertUserAnswerTeacherQuestion( String teacherId, String userId, String question);

   int updateUserTeacherRewardOrderStatus(String usertTeacherRewardOrderId,String transactionId);

   int insertTeacherReplyUserQuestion( String teacherId, String reply,String userTeacherQuestionId);

   User selecUserDetail(String userId);

   void findPay(User user);

   List<UserTeacherFocus> selectMyFocusTeachers(int pageNum,int pageSize,UserTeacherFocus focus);

   List<UserTeacherFocus> selectFocusMeUsers(int pageNum,int pageSize,UserTeacherFocus focus);

    List<User> selectByUserType(int userType);

    int checkUserNameOnly(String userName);

    void updateVIP(User user);

    String createQrCode(String urlVal,String userId);

    User getUserMessageByIm(String accid);

    int updateAccId(String userId,String accId,String tokenStr);

    int addUc(UserCapital uc);

    int checkCodeAndPhone(String countryCode,String phone);

    int checkPhoneIsExist(User user);

    int  deleteFocusMeAndMyFocus(String userId);

    List<APITearcherUser> searchIndexFourTeachers(Map<String,Object> map);

    User getUserByOpenId(String openId);

    void bindQQ(User user);

    void changeIdentity(int userIdentity,long expirationDate, String userId);

    User selectUserByPhoneNum(String phone);

    User getUserByWxOpenId(String wxOpenId);

    void bindWx(User user);

    UserTeacherRewardOrder getUserTeacherRewardOrder(UserTeacherRewardOrder order);

    User getUserByWeiboOpenId(String weiboOpenId);

    void bindWeiBo(User user);

    PageInfo<UserTeacherIntroImg> searchUserTeacherIntroImgList(int pageNum,int pageSize,String userId );


    /**
     * 根据ID查询教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    UserTeacherIntroImg searchUserTeacherIntroImgById(String teacherIntroImgId);

    /**
     * 插入教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    int insertUserTeacherIntroImg(UserTeacherIntroImg userTeacherIntroImg);

    /**
     * 根据ID删除教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    int deleteUserTeacherIntroImg(String teacherIntroImgId);

    /**
     * 修改教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    int updateUserTeacherIntroImg(UserTeacherIntroImg userTeacherIntroImg);


    /**
     * 根据教头用户ID查询教头简介图片URL列表
     * @param teacherId
     * @return
     */
    List<String> searchTeacherIntroImgs(String teacherId);

    /**
     * 合成用户邀请好友图片
     * @param user
     * @return
     */
    String   insertQrCodeImg(User user,String shareImg ,int imgNumber);

    /**
     * 批量合成邀请好友图片
     * @param user
     * @return
     */
      String  batchQrCode( User user);

    int change_forever(long expirationDate,String userTypeId, String userId);

    /**
     * 消息列表-用户提问问题列表或向用户提问问题列表
     *
     * @param userId
     * @param type
     * @return
     */
    List<UserTeacherQuestion> questionOrReplyList(int pageNum,int pageSize,String userId , String type);

    /**
     * 根据用户名查询用户
     *
     * @param countryCode
     * @param phone
     * @return
     */
     User selectByPhoneNum(String countryCode, String phone);

    /**
     * 查询用户是否关注公众号
     * @param userId
     * @return
     */
    String selectThePublicOpenId(String userId);

    /**
     * 记录用户的openId
     * @param user
     * @return
     */
    int updateThePublicOpenId(User user);


    void updatePwdByUserId(User user);

    void updatePayByUserId(User user);

    boolean  batchAllUserQrCodeImg();
}