package cn.com.myproject.user.controller;

import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.IUserService;
import cn.com.myproject.sysuser.entity.PO.SysRegion;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserTeacherIntroImg;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.formula.functions.Int;
import org.bouncycastle.crypto.tls.UserMappingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by JYP on 2017/8/7 0007.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadImgService uploadImgService;


    @Value("${ueditor.url}")
    private String path;

    @RequestMapping("/teacher/")
    public String teacher_index() {
        return "user/teacher_index";
    }

    @RequestMapping("/")
    public String index() {
        return "buiness/account";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<User> getList(Integer rows, Integer page) {
        PageInfo<User> list = userService.getPage(page, rows);
        return list;
    }

    @ResponseBody
    @RequestMapping("/resetPwd")
    public int resetPwd(@RequestParam Integer id) {
        int result = 0;
        try {

            User user = new User();
            user.setPassword(DigestUtils.md5Hex("888888"));
            user.setId(id);
            userService.updatePwd(user);
            result = 1;
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/resetPay")
    public int resetPay(@RequestParam Integer id) {
        int result = 0;
        try {
            User user = new User();
            user.setPayPassword(DigestUtils.md5Hex("888888"));
            user.setId(id);
            userService.updatePay(user);
            result = 1;
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/getPageForeach")
    @ResponseBody
    public PageInfo<User> getPageForeach(int rows, int page, HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        String keyword =request.getParameter("keyword");
        String phone = request.getParameter("phone");
        if("" != request.getParameter("status") && null != request.getParameter("status")){
            Integer s = Integer.parseInt(request.getParameter("status"));
            map.put("status",s);
        }else{
            map.put("status","");
        }
        if("" != request.getParameter("identity")  && null != request.getParameter("identity")){
            Integer i = Integer.parseInt(request.getParameter("identity"));
            map.put("identity",i);
        }else{
            map.put("identity","");
        }
        if(request.getParameter("type") != null && request.getParameter("type") != ""){
            Integer t = Integer.parseInt(request.getParameter("type"));
            map.put("type",t);
        }else{
            map.put("type","");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(phone)) {
            map.put("phone",phone);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        PageInfo<User> list = userService.getPageForeach(map);
        return list;
    }

    /**
     * 分页优惠劵发放的用户列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/getCouponUserPageForeach")
    @ResponseBody
    public PageInfo<User> getCouponUserPageForeach(int rows, int page,HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        String keyword =request.getParameter("keyword");
        String phone = request.getParameter("phone");
        String catId = request.getParameter("catId");
        String couponId = request.getParameter("couponId");
        if("" != request.getParameter("status") && null != request.getParameter("status")){
            Integer s = Integer.parseInt(request.getParameter("status"));
            map.put("status",s);
        }else{
            map.put("status","");
        }
        if("" != request.getParameter("identity")  && null != request.getParameter("identity")){
            Integer i = Integer.parseInt(request.getParameter("identity"));
            map.put("identity",i);
        }else{
            map.put("identity","");
        }
        if(request.getParameter("type") != null && request.getParameter("type") != ""){
            Integer t = Integer.parseInt(request.getParameter("type"));
            map.put("type",t);
        }else{
            map.put("type","");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(phone)) {
            map.put("phone",phone);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(catId)) {
            map.put("catId",catId);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(couponId)) {
            map.put("couponId",couponId);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        PageInfo<User> list = userService.getCouponUserPageForeach(map);
        return list;
    }

    @RequestMapping("/change")
    @ResponseBody
    public int change(@RequestParam Integer id) {
        int result = 0;
        try {
            userService.change(id);
            result = 1;
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/userend")
    @ResponseBody
    public int userend(@RequestParam Integer id) {
        int result = 0;
        try {
            userService.userend(id);
            result = 1;
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/userstart")
    @ResponseBody
    public int userstart(@RequestParam Integer id) {
        int result = 0;
        try {
            userService.userstart(id);
            result = 1;
        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/checktype")
    @ResponseBody
    public int checktype(@RequestBody Integer id){

          return  userService.checktype(id);

    }

    @RequestMapping("/delUserByUserId")
    @ResponseBody
    public int delUserByUserId(String userId){
       return userService.delUserByUserId(userId);
    }

    @RequestMapping("/selectUserByUserId")
    @ResponseBody
    public Map<String,Object> selectUserByUserId(String userId){
       Map<String,Object> objectMap =userService.selectUserByUserId(userId);
       return objectMap;
    }

    @RequestMapping(value = "/insertTeacherUser" ,method = RequestMethod.POST)
    @ResponseBody
    public int  insertTeacherUser(MultipartFile file ,MultipartFile file1 ,String data, HttpServletRequest request)throws  Exception{
        int userInsertResult =0;
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
            SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
            Map<String,String> map = new HashMap<>();
            map.put("data", data);
            map.put("userId",user.getUserId());
            if(file != null && file1 != null){
                String urlVal = uploadImgService.uploadImg(file,"userPhoto");
                if(StringUtils.isNotBlank(urlVal)){
                    map.put("photo",urlVal);
                }
                String urlVal1 = uploadImgService.uploadImg(file1,"userPhoto");
                if(StringUtils.isNotBlank(urlVal1)){
                    map.put("rectanglePhoto",urlVal1);
                }
            }
            userInsertResult = userService.insertTeacherUser(map);
            if(userInsertResult == 1){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;

    }

    @RequestMapping(value="/updateTeacherUser" ,method = RequestMethod.POST)
    @ResponseBody
    public int  updateTeacherUser(MultipartFile file ,MultipartFile file1 ,String data,HttpServletRequest request)throws  Exception{
        int userInsertResult =0;
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
            SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
            Map<String,String> map = new HashMap<>();
            map.put("data",data);
            map.put("userId",user.getUserId());
            if(file != null){
                String urlVal = uploadImgService.uploadImg(file,"userPhoto");
                if(StringUtils.isNotBlank(urlVal)){
                    map.put("photo",urlVal);
                }
            }
            if( file1 !=null){
                String urlVal1 = uploadImgService.uploadImg(file1,"rectanglePhoto");
                if(StringUtils.isNotBlank(urlVal1)){
                    map.put("rectanglePhoto",urlVal1);
                }
            }
            userInsertResult = userService.updateTeacherUser(map);
            if(userInsertResult == 1){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;

    }

    @RequestMapping("/getTeacherUsersPage")
    @ResponseBody
    public PageInfo<Map<String,Object>> getTeacherUsersPage(int rows, int page, HttpServletRequest request){
        Map<String,Object> paramMap = new LinkedHashMap<>();
        PageInfo<Map<String,Object>>  list = null;
        try{

            String keyword =request.getParameter("keyword");
            String phone = request.getParameter("phone");
            if("" != request.getParameter("status") && null != request.getParameter("status")){
                Integer s = Integer.parseInt(request.getParameter("status").trim());
                paramMap.put("status",s);
            }else{
                paramMap.put("status","");
            }
            if("" != request.getParameter("identity")  && null != request.getParameter("identity")){
                Integer i = Integer.parseInt(request.getParameter("identity").trim());
                paramMap.put("identity",i);
            }else{
                paramMap.put("identity","");
            }
            if(request.getParameter("type") != null && request.getParameter("type") != ""){
                Integer t = Integer.parseInt(request.getParameter("type").trim());
                paramMap.put("type",t);
            }else{
                paramMap.put("type","");
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
                keyword = URLDecoder.decode(keyword, "UTF-8");
                paramMap.put("keyword",keyword.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(phone)) {
                paramMap.put("phone",phone.trim());
            }
            paramMap.put("pageNum",page);
            paramMap.put("pageSize",rows);
            list =  userService.getTeacherUsersPage(paramMap);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询讲师列表异常");
        }

        return list;
    }

    @RequestMapping("selectTeacherUserByUserTypeId")
    @ResponseBody
    public List<Map<String,Object>> selectTeacherUserByUserTypeId(String userTypeId){
            return userService.selectTeacherUserByUserTypeId(userTypeId);
    }

    /**
     * 添加教师页面
     */
    @RequestMapping(value = "/teacher/addTeacher")
    public ModelAndView teacherTypeChildren(){
        ModelAndView view = new ModelAndView("/user/teacher_add_index");
        return view;
    }

    /**
     * 更新教师页面
     */
    @RequestMapping(value = "/teacher/updateTeacher")
    public ModelAndView updateTeacher(String userId){
        ModelAndView view = new ModelAndView("/user/teacher_update_index");
        Map<String,Object> objectMap =userService.selectUserByUserId(userId);
        view.addObject("data",objectMap);
        return view;
    }

    /*标签查询显示
    * */
    @ResponseBody
    @RequestMapping(value = "/getUserLabelName")
    public User getUserLabelName(@RequestParam("userId") String userId){
        User user = userService.selectById(userId);
        String label_name_str = "";
        if(user.getLabelId() != null){
           String s[] = user.getLabelId().split(",");
           for(int i= 0;i<s.length;i++){
               String label_name = userService.getLabelName(s[i]);
               label_name_str +=label_name+",";
           }
       }
       user.setLabelName(label_name_str);
        return user;
    }

    @RequestMapping("/user_detail")
    public ModelAndView user_detail(String userId) {
        ModelAndView model = new ModelAndView("buiness/account_detail");
        model.addObject("userId", userId);
        return model;
    }

    /**
     * 检测登录名是否重复
     * @param loginName
     * @return
     */
    @RequestMapping("/checkLoginName")
    @ResponseBody
    public int checkLoginName(String loginName){
         int i = userService.checkLoginName(loginName);
        return i;
    }

    /**
     * 检测手机号在国际上是否有重复的用户
     * @param countryCode
     * @param phone
     * @return
     */
    @RequestMapping("/checkPhoneIsExist")
    @ResponseBody
    public int checkPhoneIsExist(String countryCode,String phone){
        User user = new User();
        user.setCountryCode(countryCode);
        user.setPhone(phone);
        int i = userService.checkPhoneIsExist(user);
        return i;
    }

    /**
     * 获取各国区号
     * @return
     */
    @RequestMapping(value="/get_countries_code_list",method = RequestMethod.GET)
    @ResponseBody
    public List<SysRegion> get_countries_code(){
        List<SysRegion> countries_code = userService.select_countries_code();
        return  countries_code;
    }

    @RequestMapping(value="/change_identity")
    @ResponseBody
    public int changeIdentity(@RequestParam("userIdentity") int userIdentity,@RequestParam("userId") String userId){
        int info = 0;
        if(StringUtils.isBlank(userId)){
            return info;
        }
        try {
            if(userIdentity == 1){//普通用户
                userService.changeIdentity(userIdentity,new Date().getTime(),userId);
                info = 1;
            }else if(userIdentity == 2){//会员用户
                long limit_date = plusDay2(366);//年限为一年
                userService.changeIdentity(userIdentity,limit_date,userId);
                info = 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  info;
    }
    /**
     * 根据用户ID查询发布课程的数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/searchMyCourseCount")
    @ResponseBody
    public int searchMyCourseCount(@RequestParam("userId") String userId){
       return  userService.searchMyCourseCount(userId);
    }

    /**
     * 后台操作会员是年限的计算
     * @param num
     * @return
     */
    public static long plusDay2(int num) throws ParseException {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        //System.out.println("现在的日期是：" + currdate);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        //System.out.println("增加天数以后的日期：" + enddate);
        // SimpleDateFormat format =  newSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String time="1970-01-06 11:45:55";
        Date date = format.parse(enddate);
        //System.out.print("Format To times:"+date.getTime());
        return date.getTime();
    }

    /**
     * 讲师上传简介图片页面
     * @param userId
     * @return
     */
    @RequestMapping("/teacher_intro_img_index")
    public ModelAndView teacher_intro_img_index(String userId ){
        ModelAndView view = new ModelAndView("user/teacher_intro_img_index");
        User teacher = userService.selectById(userId);
        view.addObject("teacher",teacher);
        return view;
    }

    /**
     * 讲师简介图片列表
     * @param rows
     * @param page
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchUserTeacherIntroImgList")
    public PageInfo<UserTeacherIntroImg> searchUserTeacherIntroImgList(int rows, int page,String userId ){
        PageInfo<UserTeacherIntroImg> list =   userService.searchUserTeacherIntroImgList(page,rows,userId);
        return list;
    }

    /**
     * 添加讲师简介图片
     * @param file
     * @param teacherId
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertUserTeacherIntroImg")
    public int insertUserTeacherIntroImg(MultipartFile[] file,String[] seqno,String teacherId){
        int flag = 1;
      for(int i =0;i<file.length;i++){
          MultipartFile f = file[i];
          UserTeacherIntroImg userTeacherIntroImg = new UserTeacherIntroImg();
          String urlVal = uploadImgService.uploadImg( f,"goods");
           if(StringUtils.isNotBlank(urlVal)){
              userTeacherIntroImg.setUrl(urlVal);
              userTeacherIntroImg.setSeqno(Integer.valueOf(seqno[i]));
              userTeacherIntroImg.setTeacherId(teacherId);
              userTeacherIntroImg.setTeacherIntroImgId(UUID.randomUUID().toString().replace("-", ""));
              int insertResult = userService.insertUserTeacherIntroImg(userTeacherIntroImg);
              if(insertResult <1){
                  flag = 0;
                  logger.error("插入教师简介图片失败userService.insertUserTeacherIntroImg(userTeacherIntroImg)");
              }
          }
      }
      return flag;
    }
    /**
     * 删除讲师简介图片
     * @param teacherIntroImgId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUserTeacherIntroImg")
    public int deleteUserTeacherIntroImg(String teacherIntroImgId){
        return userService.deleteUserTeacherIntroImg(teacherIntroImgId);
    }

    @RequestMapping(value="/change_forever")
    @ResponseBody
    public int change_forever(@RequestParam("userTypeId") String userTypeId,@RequestParam("userId") String userId){
        int info = 0;
        if(StringUtils.isBlank(userId)){
            return info;
        }
        try {
                userService.change_forever(-1,userTypeId,userId);
                info = 1;
        }catch (Exception e){
            logger.info("升级教头操作失败:admin-userController",e);
            e.printStackTrace();
        }
        return  info;
    }

}
