package cn.com.myproject.external;

import cn.com.myproject.admincon.service.IProfitShareRelationService;
import cn.com.myproject.user.entity.PO.*;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import cn.com.myproject.user.service.IStudyLabelService;
import cn.com.myproject.user.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/27.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudyLabelService studyLabelService;

    @Autowired
    private IProfitShareRelationService profitShareRelationService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/addShareUser")
    public int addShareUser(String referrerId,String registerId){
        return profitShareRelationService.addProfitShareRelation(referrerId,registerId);
    }

    @PostMapping("/reigister")
    public int  reigister(@RequestBody User user) {
        String qrCodeUrl = "";
        int i=0;
        try{
            if(user.getQrCodeUrl() != null && !"".equals(user.getQrCodeUrl())){
                qrCodeUrl = userService.createQrCode(user.getQrCodeUrl(),user.getUserId());
            }
            user.setQrCodeUrl(qrCodeUrl);
            i= userService.reigister(user);
        }catch (Exception e){
            logger.error("{[]}时，发生异常，异常信息为{[]}","用户注册（adminapi-controller-reigister）",e);
        }
        return i;
    }

    @GetMapping("/getByLogin")
    public User getByLogin(String loginName) {
        return userService.getByLogin(loginName);
    }

    @GetMapping("/login")
    public Map<String,Object> login(String counttriesCode,String loginName,String pwd) {
        return userService.login(counttriesCode,loginName,pwd);
    }

    @GetMapping("/getPage")
    public PageInfo<User> getPage(int pageNum,int pageSize){
        return userService.getPage(pageNum,pageSize);
    }

    @PostMapping("/updatePwd")
    public void updatePwd(@RequestBody User user){
        userService.updatePwd(user);
    }

    @PostMapping("/updatePay")
    public void updatePay(@RequestBody User user){
        userService.updatePay(user);
    }

    @PostMapping("/updateQrCodeImg")
    public int  updateQrCodeImg(@RequestBody User user){
       return  userService.updateQrCodeImg(user);
    }
    @PostMapping("/getPageForeach")
    public PageInfo<User> getPageForeach(@RequestBody Map<String,Object> map) throws UnsupportedEncodingException {
        if(!map.isEmpty() && map.get("keyword")!=null && org.apache.commons.lang.StringUtils.isNotBlank(map.get("keyword").toString())){
           //keyword= URLDecoder.decode(keyword,"UTF-8");
            map.put("keyword",URLDecoder.decode(map.get("keyword").toString(),"UTF-8"));
        }

       PageInfo<User> list = userService.getPageForeach(Integer.parseInt(map.get("pageNum")+""),Integer.parseInt(map.get("pageSize")+""),map);
        return list;
    }

    @PostMapping("/getCouponUserPageForeach")
    public PageInfo<User> getCouponUserPageForeach(@RequestBody Map<String,Object> map) throws UnsupportedEncodingException {
        if(!map.isEmpty() && map.get("keyword")!=null && org.apache.commons.lang.StringUtils.isNotBlank(map.get("keyword").toString())){
            //keyword= URLDecoder.decode(keyword,"UTF-8");
            map.put("keyword",URLDecoder.decode(map.get("keyword").toString(),"UTF-8"));
        }

        PageInfo<User> list = userService.getCouponUserPageForeach(Integer.parseInt(map.get("pageNum")+""),Integer.parseInt(map.get("pageSize")+""),map);
        return list;
    }

    @PostMapping("/getTeacherUsersPage")
    public PageInfo<Map<String,Object>> getTeacherUsersPage(@RequestBody Map<String,Object> map){
        PageInfo<Map<String,Object>> list = userService.getTeacherUsersPage(map);
        return list;
    }

    @PostMapping("/change")
    public int change(@RequestBody Integer id){
        int result = 0;
        try{
            userService.change(id);
            result = 1;
        }catch(Exception ex){
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping("/userend")
    public int userend(@RequestBody Integer id){
        int result = 0;
        try{
            userService.userend(id);
            result = 1;
        }catch(Exception ex){
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }
    @PostMapping("/userstart")
    public int userstart(@RequestBody Integer id){
        int result = 0;
        try{
            userService.userstart(id);
            result = 1;
        }catch(Exception ex){
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping("/checktype")
    public Integer checktype(@RequestBody Integer id){
        return userService.checktype(id);
    }

    @GetMapping("/getTweetyUserList")
    public List<User> getTweetyUserList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
        return userService.getTweetyUserList(pageNum,pageSize);
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @GetMapping("/getNonsubordinateProfitShareRelationUserList")
    public  List<User> getNonsubordinateProfitShareRelationUserList(String userId){
        return userService.getNonsubordinateProfitShareRelationUserList(userId);
    }

    /*public int  insertTeacherUser(String data,String userId){
        return userService.insertTeacherUser(data,userId);
    }*/

    @PostMapping("/insertTeacherUser")
    public int  insertTeacherUser(@RequestBody Map<String,String> map){
        return userService.insertTeacherUser(map.get("data"),map.get("userId"),map.get("photo")==null?"":map.get("photo").toString(),map.get("rectanglePhoto")==null?"":map.get("rectanglePhoto").toString());
    }

    @PostMapping("/updateTeacherUser")
    public int  updateTeacherUser(@RequestBody Map<String,String> map){
        return userService.updateTeacherUser(map.get("data"),map.get("userId"),map.get("photo")==null?"":map.get("photo").toString(),map.get("rectanglePhoto")==null?"":map.get("rectanglePhoto").toString());
    }
//    public int  updateTeacherUser(String data,String userId){
//        return userService.updateTeacherUser(data,userId);
//    }


    @PostMapping("/selectUserByUserId")
    public  Map<String,Object> selectUserByUserId( String userId){
        return userService.selectUserByUserId(userId);
    }

    @PostMapping("/delUserByUserId")
    public int delUserByUserId(String userId){
        return userService.delUserByUserId(userId);
    }

    @PostMapping("/selectTeacherUserByUserTypeId")
    public List<Map<String,Object>> selectTeacherUserByUserTypeId(String userTypeId){
           return userService.selectTeacherUserByUserTypeId(userTypeId);
    }

   @PostMapping("/checkPhone")
    public int checkPhone(String phone) {
       return userService.checkPhone(phone);
   }

    @PostMapping("/checkPhoneIsExist")
    public int checkPhoneIsExist(@RequestBody User user){
        return userService.checkPhoneIsExist(user);
    }


    @PostMapping("/findPwd")
    public void findPwd(@RequestBody User user) {
        userService.findPwd(user);
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }


    @PostMapping("/getAPITeacherUsersPage")
    public List<APITearcherUser> getAPITeacherUsersPage(@RequestBody Map<String,Object> map){
        List<APITearcherUser> list = userService.getAPITeacherUsersPage(map);
        return list;
    }
    @PostMapping("/selectTeacherUserByUserId")
    public APITearcherUser selectTeacherUserByUserId(String userId){
        APITearcherUser user = userService.selectTeacherUserByUserId(userId);
        return user;
    }
    @PostMapping("/getUserTeacherFocus")
    public UserTeacherFocus getUserTeacherFocus(@RequestBody UserTeacherFocus focus){
        return userService.getUserTeacherFocus(focus);
    }
    @PostMapping("/insertUserTeacherFocus")
    public int insertUserTeacherFocus(@RequestBody UserTeacherFocus focus){
        return userService.insertUserTeacherFocus(focus);
    }

    @PostMapping("/updateUserTeacherFocus")
    public int updateUserTeacherFocus(@RequestBody UserTeacherFocus focus){
        return userService.updateUserTeacherFocus(focus);
    }
    @PostMapping("/checkLoginName")
    public int checkLoginName(String loginName){
         return  userService.checkLoginName(loginName);

    }
    @PostMapping("/selectById")
    public User selectById(String userId){
        return userService.selectById(userId);
    }

    @PostMapping("/giveTeahcerReward")
    public  Map<String,Object> giveTeahcerReward( String teacherId,String userId,String money,String payWay){
        return userService.giveTeahcerReward(  teacherId, userId, money,payWay);
    }

    @PostMapping("/questionAndReplyList")
    public List<UserTeacherQuestion> questionAndReplyList(int pageNum,int pageSize, String teacherId,String userId){
        return userService.questionAndReplyList(pageNum,pageSize,teacherId,userId);
    }

    @PostMapping("/insertUserAnswerTeacherQuestion")
    public int insertUserAnswerTeacherQuestion( String teacherId, String userId, String question){
        return userService.insertUserAnswerTeacherQuestion(teacherId,userId,question);
    }

    @PostMapping("/insertTeacherReplyUserQuestion")
    public int insertTeacherReplyUserQuestion( String teacherId, String reply,String userTeacherQuestionId){
        return userService.insertTeacherReplyUserQuestion(teacherId,reply, userTeacherQuestionId);
    }

    //打赏支付宝支付成功通知修改订单状态
    @PostMapping("/zfbReturnSuccessUpdateRewardOrderStatus")
    public int  zfbReturnSuccessUpdateRewardOrderStatus(String usertTeacherRewardOrderId ,String transactionId){
       return userService.updateUserTeacherRewardOrderStatus(usertTeacherRewardOrderId,transactionId);
    }

    @PostMapping("/selecUserDetail")
    public User selecUserDetail(String userId){
        return userService.selecUserDetail(userId);
    }

    @PostMapping("/findPay")
    public Integer findPay(@RequestBody User user){
        int info = 0;
        try{
            userService.findPay(user);
            info = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  info;
    }

    @PostMapping("/selectMyFocusTeachers")
    public List<UserTeacherFocus> selectMyFocusTeachers(int pageNum,int pageSize,@RequestBody UserTeacherFocus focus){
        List<UserTeacherFocus> list  = userService. selectMyFocusTeachers(pageNum,pageSize, focus);
        return list;
    }


    @PostMapping("/selectFocusMeUsers")
    public List<UserTeacherFocus> selectFocusMeUsers(int pageNum,int pageSize,@RequestBody UserTeacherFocus focus){
        List<UserTeacherFocus> list  = userService. selectFocusMeUsers( pageNum,pageSize,focus);
        return list;
    }

    @PostMapping("/selectByUserType")
    public List<User> selectByUserType(int userType){

        return userService.selectByUserType(userType);
    }

    @PostMapping("/checkUserNameOnly")
    public int checkUserNameOnly(String userName){

        return userService.checkUserNameOnly(userName);
    }

    @PostMapping("/updateVIP")
    public int updateVIP(@RequestBody User user){

        int result = 0;
        try {
            userService.updateVIP(user);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 1;
            return result;
        }
        return result;
    }

    @RequestMapping("/getUserMessageByIm")
    public User getUserMessageByIm(String accid) {
        return userService.getUserMessageByIm(accid);
    }

    @RequestMapping("/getLabelName")
    public String getLabelName(String labelId){
        return studyLabelService.getLabelName(labelId);
    }

    @RequestMapping("/checkCodeAndPhone")
    public int checkCodeAndPhone(String countryCode,String phone){
        return userService.checkCodeAndPhone(countryCode,phone);
    }
    @PostMapping("/createQrCode")
    public String createQrCode(String urlVal,String userId){
        return userService.createQrCode(urlVal,userId);
    }


    /**
     * 首页前四位教头
     * @param map
     * @return
     */
    @PostMapping("/searchIndexFourTeachers")
    public List<APITearcherUser> searchIndexFourTeachers(@RequestBody Map<String,Object> map){
            return userService.searchIndexFourTeachers(map);
    }

    @PostMapping("/getUserByOpenId")
    public User getUserByOpenId(@RequestParam("openId") String openId){
        return userService.getUserByOpenId(openId);
    }

    @PostMapping("/bindQQ")
    public int bindQQ(@RequestBody User user){
        int info = 0;
        try{
            userService.bindQQ(user);
            info = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return info;
    }

    @PostMapping("/changeIdentity")
    public int changeIdentity(int userIdentity,long expirationDate,String userId){
        int info = 0;
        try{
            userService.changeIdentity(userIdentity,expirationDate,userId);
            info = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return info;
    }

    @PostMapping("/selectUserByPhoneNum")
    public User selectUserByPhoneNum(String phone){

        return userService.selectUserByPhoneNum(phone);
    }

    @PostMapping("/getUserByWxOpenId")
    public User getUserByWxOpenId(String wxOpenId){

        return userService.getUserByWxOpenId(wxOpenId);
    }

    @PostMapping("/bindWx")
    public int binWx(@RequestBody User user){

        int result = 0;
        try {
            userService.bindWx(user);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/getUserTeacherRewardOrder")
    public UserTeacherRewardOrder getUserTeacherRewardOrder(@RequestBody UserTeacherRewardOrder order){
        return userService.getUserTeacherRewardOrder(order);
    }

    @PostMapping("/getUserByWeiboOpenId")
    public User getUserByWeiboOpenId(@RequestParam("weiboOpenId") String weiboOpenId){
        return userService.getUserByWeiboOpenId(weiboOpenId);
    }

    @PostMapping("/bindWeiBo")
    public int bindWeiBo(@RequestBody User user){
        int info = 0;
        try{
            userService.bindWeiBo(user);
            info = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return info;
    }
    /**
     * 根据教头ID查询其简介图片列表
     * @param teacherId
     * @return
     */
    @PostMapping("/searchUserTeacherIntroImgList")
    public PageInfo<UserTeacherIntroImg> searchUserTeacherIntroImgList(int pageNum, int pageSize, String teacherId ){
        return userService.searchUserTeacherIntroImgList(pageNum,pageSize,teacherId);
    }
    /**
     * 根据ID查询教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    @PostMapping("/searchUserTeacherIntroImgById")
    public UserTeacherIntroImg searchUserTeacherIntroImgById(String teacherIntroImgId){
        return userService.searchUserTeacherIntroImgById(teacherIntroImgId);
    }

    /**
     * 插入教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    @PostMapping("/insertUserTeacherIntroImg")
    public int insertUserTeacherIntroImg(@RequestBody UserTeacherIntroImg userTeacherIntroImg){
        return userService.insertUserTeacherIntroImg(userTeacherIntroImg);
    }

    /**
     * 根据ID删除教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    @PostMapping("/deleteUserTeacherIntroImg")
    public int deleteUserTeacherIntroImg(String teacherIntroImgId){
        return userService.deleteUserTeacherIntroImg(teacherIntroImgId);
    }

    /**
     * 修改教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    @PostMapping("/updateUserTeacherIntroImg")
    public int updateUserTeacherIntroImg(@RequestBody UserTeacherIntroImg userTeacherIntroImg){
        return userService.updateUserTeacherIntroImg(userTeacherIntroImg);
    }


    /**
     * 根据教头用户ID查询教头简介图片URL列表
     * @param teacherId
     * @return
     */
    @GetMapping("/searchTeacherIntroImgs")
    public List<String> searchTeacherIntroImgs(String teacherId){
       List<String > list = userService.searchTeacherIntroImgs(teacherId);
       if(list.size() == 0){
           return null;
       }
       return list;
    }
    /**
     * 合成海报与二维码图片为分享图片
     * @param user
     * @param shareImg
     * @return
     */
    @PostMapping("/createImg")
    public String  createImg(@RequestBody User user,@RequestParam("shareImg") String shareImg,@RequestParam("imgNumber") int imgNumber ){
        return userService.insertQrCodeImg(user,shareImg,imgNumber);
    }
    @PostMapping("/batchQrCode")
    public String  batchQrCode(@RequestBody User user){
        return userService.batchQrCode(user);
    }

    @PostMapping("/change_forever")
    public int change_forever(long expirationDate,String userTypeId, String userId){
        int result = 0;
        try{
            userService.change_forever(expirationDate,userTypeId,userId);
            result = 1;
        }catch(Exception ex){
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping("/questionOrReplyList")
    public List<UserTeacherQuestion> questionOrReplyList(@RequestParam("pageNum") int pageNum,
                                                         @RequestParam("pageSize") int pageSize,
                                                         @RequestParam("userId") String userId ,
                                                         @RequestParam("type") String type){
      return   userService.questionOrReplyList(pageNum,pageSize,userId,type);
    }

    @PostMapping("/selectByPhoneNum")
    public User selectByPhoneNum(@RequestParam("countryCode") String countryCode, @RequestParam("phone")  String phone ){
        return userService.selectByPhoneNum(countryCode,phone);
    }

    @GetMapping("/searchUserIdentityByUserId")
    public int searchUserIdentityByUserId(String userId){
        return userService.searchUserIdentityByUserId(userId);
    }


    @PostMapping("/selectThePublicOpenId")
    public String selectThePublicOpenId(String userId){
        return userService.selectThePublicOpenId(userId);
    }

    @PostMapping("/updateThePublicOpenId")
    public int updateThePublicOpenId(@RequestBody User user){
        return userService.updateThePublicOpenId(user);
    }

    @PostMapping("/updatePwdByUserId")
    public void updatePwdByUserId(@RequestBody User user){
        userService.updatePwdByUserId(user);
    }

    @PostMapping("/updatePayByUserId")
    public void updatePayByUserId(@RequestBody User user){
        userService.updatePayByUserId(user);
    }

    @PostMapping("/batchAllUserQrCodeImg")
    public boolean  batchAllUserQrCodeImg(){
        return  userService.batchAllUserQrCodeImg();
    }


}