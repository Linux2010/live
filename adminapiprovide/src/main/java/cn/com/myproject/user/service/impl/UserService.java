package cn.com.myproject.user.service.impl;

import cn.com.myproject.admincon.service.impl.ProfitShareRelationService;
import cn.com.myproject.aliyun.oss.IAliyunOssService;
import cn.com.myproject.configSetting.mapper.ConfigSettingMapper;
import cn.com.myproject.enums.OrderEnum;
import cn.com.myproject.live.service.ILiveRoomService;
import cn.com.myproject.netease.VO.ResultInfo;
import cn.com.myproject.netease.VO.account.IMCreateVO;
import cn.com.myproject.netease.service.IAccountService;
import cn.com.myproject.user.entity.PO.*;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import cn.com.myproject.user.entity.VO.UserStatus;
import cn.com.myproject.user.mapper.*;
import cn.com.myproject.user.service.IUserService;
import cn.com.myproject.util.ChartGraphics;
import cn.com.myproject.util.OrderUtil;
import cn.com.myproject.util.QrCodeCreateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by liyang-macbook on 2017/6/30.
 */
@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private UserTeacherFocusMapper userTeacherFocusMapper;

    @Autowired
    private UserCapitalService userCapitalService;

    @Autowired
    private UserCapitalLogService userCapitalLogService;

    @Autowired
    private UserTeacherQuestionMapper userTeacherQuestionMapper;

    @Autowired
    private UserTeacherQuestionReplyMapper userTeacherQuestionReplyMapper;

    @Autowired
    private UserTeacherRewardOrderMapper userTeacherRewardOrderMapper;

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private UserTeacherIntroImgMapper userTeacherIntroImgMapper;

    @Autowired
    private IAliyunOssService aliyunOssService;

    @Autowired
    private ConfigSettingMapper configSettingMapper;

    @Autowired
    private ProfitShareRelationService profitShareRelationService;

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     * 根据用户ID查询用户身份(1代表普通用户，2代表会员)
     *
     * @param userId
     * @return
     */
    @Override
    public int searchUserIdentityByUserId(String userId){
        return userMapper.selectUserIdentityByUserId(userId);
    }

    @Override
    @Transactional
    public int reigister(User user) {

        user.setVersion(1);
        user.setCreateTime(Calendar.getInstance().getTimeInMillis());
        user.setStatus(UserStatus.S_1.getId());
        user.setUserIdentity(1);// 默认为普通
        user.setUserType(2);// 默认为用户

        int i = userMapper.insert(user);

        if (i == 1) {
            IMCreateVO vo = new IMCreateVO();
            vo.setAccid(UUID.randomUUID().toString().replace("-", ""));
            vo.setName(user.getNickName());
            ResultInfo result = accountService.create(vo);
            if (result.getCode() == 200) {
                Map<String, String> map = result.getInfo();
                int updatenum = updateAccId(user.getUserId(), map.get("accid"), map.get("token"));
                if (updatenum == 0) {
                    i = 0;
                    throw new RuntimeException("更新失败");
                }else{
                    // 注册用户时，初始化用户资金记录
                    UserCapital userCapital = new UserCapital();
                    userCapital.setUserId(user.getUserId());
                    userCapital.setIntegral(new BigDecimal(0));
                    userCapital.setTael(new BigDecimal(0));
                    userCapital.setFreezeintegral(new BigDecimal(0));
                    userCapital.setFreezetael(new BigDecimal(0));
                    userCapital.setCapitalId(UUID.randomUUID().toString().replace("-", ""));
                    userCapital.setCreateTime(new Date().getTime());
                    userCapital.setVersion(1);
                    userCapital.setStatus((short)1);
                    int flagVal = addUc(userCapital);
                    if(flagVal == 0){
                        i = 0;
                        logger.error("注册用户时，用户资金记录插入失败");
                        throw new RuntimeException("注册用户时，用户资金记录插入失败");
                    }
                }
            } else {
                i = 0;
                logger.error("插入用户失败userMapper.insert(user)");
                throw new RuntimeException("创建网易IM账号失败,code:"+result.getCode()+",message="+result.getDesc()+",NickName="+user.getNickName());
            }
            int operRelation = registerOperRelation(user);
            if(operRelation < 1){
                logger.error("{[]}{[]}","会员注册，确立上下级会员");
            }
        } else {
            i = 0;
            logger.error("插入用户失败userMapper.insert(user)");
            throw new RuntimeException("插入失败");
        }
        return i;
    }

    @Override
    @Transactional
    public int updateAccId(String userId,String accId,String tokenStr){
        return userMapper.updateAccId(userId, accId,tokenStr);
    }

    @Override
    @Transactional
    public int addUc(UserCapital uc){
        return userCapitalMapper.insert(uc);
    }

    @Override
    public int checkCodeAndPhone(String countryCode, String phone) {
        return userMapper.checkCodeAndPhone(countryCode,phone);
    }

    @Override
    public User getByLogin(String loginName) {
        return userMapper.selectByLoginName("",loginName);
    }

    @Override
    public User selectByPhone(String phone) {
        return userMapper.selectByPhone("",phone);
    }

    @Override
    public Map<String,Object> login(String counttriesCode, String loginName, String pwd) {
        Map<String,Object> result = new HashMap<>();
        User _user = userMapper.selectByLoginName(counttriesCode,loginName);
        if(null == _user){
            _user = userMapper.selectByPhone(counttriesCode,loginName);
        }
        if(null == _user){
            result.put("status","fail");
            result.put("message","该用户不存在");
        }else{
            if(pwd.equals(_user.getPassword())){
                result.put("status","success");
                result.put("user", JSON.toJSONString(_user));
            }else{
                result.put("status","fail");
                result.put("message","密码错误，请重试");
            }
        }
        return result;
    }

    @Override
    public PageInfo<User> getPage(int pageNum, int pageSize) {
        List<User> list = userMapper.getPage(pageNum, pageSize);
        return convert(list);
    }

    @Override
    public void updatePay(User user) {
        userMapper.updatePay(user);
    }
    @Override
    public int  updateQrCodeImg(User user){
        return  userMapper.updateQrCodeImg(user);
    }

    @Override
    public void updatePwd(User user) {
        userMapper.updatePwd(user);
    }

    @Override
    public PageInfo<User> getPageForeach(int pageNum, int pageSize, Map<String, Object> map) {
        List<User> list = userMapper.getPageForeach(pageNum, pageSize, map);
        return convert(list);
    }

    @Override
    public PageInfo<User> getCouponUserPageForeach(int pageNum, int pageSize, Map<String, Object> map) {
        List<User> list = userMapper.getCouponUserPageForeach(pageNum, pageSize, map);
        return convert(list);
    }

    @Override
    public PageInfo<Map<String, Object>> getTeacherUsersPage(Map<String, Object> map) {
        List<Map<String, Object>> list = null;
        try {
            if (!map.isEmpty() && map.get("keyword") != null && org.apache.commons.lang.StringUtils.isNotBlank(map.get("keyword").toString())) {
                //keyword= URLDecoder.decode(keyword,"UTF-8");
                map.put("keyword", URLDecoder.decode(map.get("keyword").toString(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("编码格式解析错误");
        }
        // String status = map.get("status").toString();
        list = userMapper.getTeacherUsersPage(Integer.valueOf(map.get("pageNum").toString()), Integer.valueOf(map.get("pageSize").toString()), map);
        return new PageInfo<Map<String, Object>>(list);
    }
    @Override
    public List<APITearcherUser> getAPITeacherUsersPage(Map<String, Object> map) {
        List<APITearcherUser> list = null;
        try {
            if (!map.isEmpty() && map.get("keyword") != null && org.apache.commons.lang.StringUtils.isNotBlank(map.get("keyword").toString())) {
                //keyword= URLDecoder.decode(keyword,"UTF-8");
                map.put("keyword", URLDecoder.decode(map.get("keyword").toString(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("编码格式解析错误");
        }
        // String status = map.get("status").toString();
        list = userMapper.getAPITeacherUsersPage(Integer.valueOf(map.get("pageNum").toString()), Integer.valueOf(map.get("pageSize").toString()), map);
        return list;
    }

    @Override
    public void change(Integer id) {
        userMapper.change(id);
    }

    @Override
    public void userend(Integer id) {
        userMapper.userend(id);
    }

    @Override
    public void userstart(Integer id) {
        userMapper.userstart(id);
    }

    @Override
    public Integer checktype(Integer id) {
        return userMapper.checktype(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
    @Override
    public List<User> getTweetyUserList(int pageNum,  int pageSize) {
        return userMapper.getTweetyUserList(pageNum,pageSize);
    }
    @Override
    public  List<User> getNonsubordinateProfitShareRelationUserList(String userId) {
        return userMapper.getNonsubordinateProfitShareRelationUserList(userId);
    }

    @Override
    public List<User> getUserListByMap(Map<String, Object> map) {
        return userMapper.getUserListByMap(map);
    }

    @Override
    public int checkPhone(String phone) {
        return userMapper.checkPhone(phone);
    }

    @Override
    public void findPwd(User user) {
        userMapper.findPwd(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public int checkLoginName(String loginName) {
        return userMapper.checkLoginName(loginName);
    }

    private PageInfo<User> convert(List<User> list) {
        PageInfo<User> info = new PageInfo(list);

        List<User> _list = info.getList();
        info.setList(null);
        List<User> __list = new ArrayList<>(10);

        PageInfo<User> _info = new PageInfo();
        BeanUtils.copyProperties(info, _info);
        if (null != _list && _list.size() != 0) {
            for (User user : _list) {
                __list.add(user);
            }
            _info.setList(__list);
        }

        return _info;
    }

    @Override
    @Transactional
    public int insertTeacherUser(String data, String sysUserId,String photo ,String rectanglePhoto) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        Map<String, Object> paramMap = new LinkedHashMap<>();
        String userId = UUID.randomUUID().toString().replace("-", "");
        if (jsonObject != null) {
            if (jsonObject.get("userTypeId") != null) {
                paramMap.put("userTypeId", jsonObject.get("userTypeId"));
            }
            if (jsonObject.get("realName") != null) {
                paramMap.put("realName", jsonObject.get("realName"));
            }
            if (jsonObject.get("userName") != null) {
                paramMap.put("userName", jsonObject.get("userName"));
            }
            if (jsonObject.get("nickName") != null) {
                paramMap.put("nickName", jsonObject.get("nickName"));
            }
            if (jsonObject.get("phone") != null) {
                paramMap.put("phone", jsonObject.get("phone"));
            }
            if (jsonObject.get("loginName") != null) {
                paramMap.put("loginName", jsonObject.get("loginName"));
            }
            if (jsonObject.get("password") != null) {
                paramMap.put("password",DigestUtils.md5Hex(jsonObject.get("password").toString()) );
            }
            if (jsonObject.get("seqno") != null) {
                paramMap.put("seqno", jsonObject.get("seqno"));
            }
            if (jsonObject.get("userIntro") != null) {
                paramMap.put("userIntro", jsonObject.get("userIntro"));
            }
            if (jsonObject.get("userIntrText") != null) {
                paramMap.put("userIntrText", jsonObject.get("userIntrText"));
            }
            if (jsonObject.get("userType") != null) {
                paramMap.put("userType", jsonObject.get("userType"));
            }
            if (jsonObject.get("status") != null) {
                paramMap.put("status", jsonObject.get("status"));
            }
            if (jsonObject.get("identity") != null) {
                paramMap.put("userIdentity", jsonObject.get("identity"));
            }
            if (jsonObject.get("signature") != null) {
                paramMap.put("signature", jsonObject.get("signature"));
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(photo)) {
                paramMap.put("photo", photo);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(rectanglePhoto)) {
                paramMap.put("rectanglePhoto", rectanglePhoto);
            }
            if (jsonObject.get("countryCode") != null) {
                paramMap.put("countryCode", jsonObject.get("countryCode"));
            }
            paramMap.put("creater", sysUserId);
            paramMap.put("createTime", Calendar.getInstance().getTimeInMillis());
            paramMap.put("userId",userId);
            paramMap.put("version", 1);
        }
        String qrCodeUrl =createQrCode(jtxyappUrl + "/api/register/showShare?userId="+userId,userId);
        paramMap.put("qrCodeUrl",qrCodeUrl);
        int insertTeacherUserResult = userMapper.insertTeacherUser(paramMap);
        if (insertTeacherUserResult > 0) {
            if (insertTeacherUserResult == 1) {
                //向网易云直播注册IM用户
                IMCreateVO vo = new IMCreateVO();
                vo.setAccid(UUID.randomUUID().toString().replace("-", ""));
                vo.setName(jsonObject.get("userName").toString());
                ResultInfo result = accountService.create(vo);
                if (result.getCode() == 200) {
                    Map<String, String> map = result.getInfo();
                    int updatenum = userMapper.updateAccId(paramMap.get("userId").toString(), map.get("accid"), map.get("token"));
                    if (updatenum != 1) {
                        logger.error("AccId更新失败");
                        throw new RuntimeException("AccId更新失败");
                    }
                } else {
                    logger.error("插入用户失败userMapper.insert(user)");
                    throw new RuntimeException("创建网易IM账号失败");
                }
            } else {
                logger.error("插入用户失败userMapper.insert(user)");
                throw new RuntimeException("插入失败");
            }
            //创建直播频道


            return 1;
        } else {
            logger.error("添加讲师用户失败userMapper.insertTeacherUser(paramMap)");
            throw new RuntimeException("添加讲师用户失败");
        }
    }

    @Override
    @Transactional
    public int updateTeacherUser(String data, String sysUserId,String photo,String rectanglePhoto) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        Map<String, Object> paramMap = new LinkedHashMap<>();
        if (jsonObject != null) {
            if (jsonObject.get("userTypeId") != null) {
                paramMap.put("userTypeId", jsonObject.get("userTypeId"));
            }
            if (jsonObject.get("realName") != null) {
                paramMap.put("realName", jsonObject.get("realName"));
            }
            if (jsonObject.get("userName") != null) {
                paramMap.put("userName", jsonObject.get("userName"));
            }
            if (jsonObject.get("nickName") != null) {
                paramMap.put("nickName", jsonObject.get("nickName"));
            }
            if (jsonObject.get("phone") != null) {
                paramMap.put("phone", jsonObject.get("phone"));
            }
            if (jsonObject.get("loginName") != null) {
                paramMap.put("loginName", jsonObject.get("loginName"));
            }
            if (jsonObject.get("password") != null) {
                paramMap.put("password",DigestUtils.md5Hex(jsonObject.get("password").toString()) );
            }
            if (jsonObject.get("seqno") != null) {
                paramMap.put("seqno", jsonObject.get("seqno"));
            }
            if (jsonObject.get("userIntro") != null) {
                paramMap.put("userIntro", jsonObject.get("userIntro"));
            }
            if (jsonObject.get("userIntrText") != null) {
                paramMap.put("userIntrText", jsonObject.get("userIntrText"));
            }
            if (jsonObject.get("userId") != null) {
                paramMap.put("userId", jsonObject.get("userId"));

                User old = userMapper.selectById( jsonObject.get("userId").toString());
                if(StringUtils.isEmpty(old.getQrCodeUrl())){
                    String qrCodeUrl =createQrCode(jtxyappUrl + "/api/register/showShare?userId="+old.getUserId(),old.getUserId());
                    paramMap.put("qrCodeUrl",qrCodeUrl);
                    old.setQrCodeUrl(qrCodeUrl);
                }
                if(!old.getUserName().equals(jsonObject.get("userName").toString())){
                    //用户名发生变化重新合成邀请好友海报分享图片
                    old.setUserName(jsonObject.get("userName").toString());
                    String imgUrl = batchQrCode(old); //合成邀请好友图片
                    if(StringUtils.isNotEmpty(imgUrl)){
                        logger.info("用户名发生变化重新合成邀请好友海报分享图片成功");
                        paramMap.put("qrCodeImgUrl", imgUrl);
                    }
                }
            }
            if (jsonObject.get("userType") != null) {
                paramMap.put("userType", jsonObject.get("userType"));
            }

            if (jsonObject.get("status") != null) {
                paramMap.put("status", jsonObject.get("status"));
            }

            if (jsonObject.get("identity") != null) {
                paramMap.put("identity", jsonObject.get("identity"));
            }
            if (jsonObject.get("signature") != null) {
                paramMap.put("signature", jsonObject.get("signature"));
            }
            if (jsonObject.get("photo") != null && org.apache.commons.lang.StringUtils.isNotBlank(jsonObject.get("photo").toString())) {
                paramMap.put("photo", jsonObject.get("photo"));
            }

            if (org.apache.commons.lang.StringUtils.isNotBlank(photo)) {
                paramMap.put("photo", photo);
            }

            if (org.apache.commons.lang.StringUtils.isNotBlank(rectanglePhoto)) {
                paramMap.put("rectanglePhoto", rectanglePhoto);
            }
            if (jsonObject.get("countryCode") != null) {
                paramMap.put("countryCode", jsonObject.get("countryCode"));
            }
            paramMap.put("expirationDate",-1);
        }

        int updateTeacherUserResult = userMapper.updateTeacherUser(paramMap);
        if (updateTeacherUserResult > 0) {
            return 1;
        } else {
            logger.error("修改讲师用户失败userMapper.insertTeacherUser(paramMap)");
            throw new RuntimeException("修改讲师用户失败");
        }
    }

    @Override
    public Map<String, Object> selectUserByUserId(String userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    @Transactional
    public int delUserByUserId(String userId) {
        int i = userMapper.delUserByUserId(userId);
        if(i == 1){
            //删除教头聊天室
            userMapper.delUserChatRoomByUserId(userId);
            //删除教头直播间信息
            userMapper.delUserLiveRoomByUserId(userId);
            //删除用户关注信息(我关注的和关注我的)
            userTeacherFocusMapper.deleteFocusMeAndMyFocus(userId);
        }else{
            logger.error("通过用户ID删除用户失败userMapper.delUserByUserId(userId)");
            throw new RuntimeException("通过用户ID删除用户失败userMapper.delUserByUserId(userId)");
        }
        return i;
    }

    @Override
    public List<Map<String, Object>> selectTeacherUserByUserTypeId(String userTypeId) {
        return userMapper.selectTeacherUserByUserTypeId(userTypeId);
    }

    @Override
    public APITearcherUser selectTeacherUserByUserId(String userId) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("userId",userId);
        map.put("status",1);//type 类型 1：教头，2：用户// user_identity用户身份1:普通用户2：会员用户 //status 0：禁用账号;1:启用账号
        map.put("identity",null);
        map.put("type",1);
        APITearcherUser user = userMapper.selectTeacherUserById(userId);
//        if(user == null){
//            logger.error("通过用户ID获取用户失败userMapper.getAPITeacherUsersPage(1,1,map)");
//            throw new RuntimeException("通过用户ID获取用户失败");
//        }
        return user;
    }
    @Override
    public UserTeacherFocus getUserTeacherFocus(UserTeacherFocus focus){
        return userTeacherFocusMapper.getUserTeacherFocus(focus);
    }

    @Override
    public int insertUserTeacherFocus(UserTeacherFocus focus){
        return userTeacherFocusMapper.insertUserFocus(focus);
    }

    @Override
    public int updateUserTeacherFocus(UserTeacherFocus focus){
        return userTeacherFocusMapper.updateUserFocusStatus(focus);
    }

    @Override
    public User selectById(String userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 微信,支付宝支付
     * @param teacherId
     * @param userId
     * @param money
     * @return
     */
    public int  weixin( String teacherId,String userId,String money,String msg){
        //查询会员用户资金记录
        UserCapital userCapital = userCapitalService.selectByUserId(userId);
        //查询教头用户资金记录
        UserCapital teacherUserCapital = userCapitalService.selectByUserId(teacherId);
        if (teacherUserCapital == null) {
            teacherUserCapital = new UserCapital();
            teacherUserCapital.setCapitalId(UUID.randomUUID().toString().replace("-", ""));
            teacherUserCapital.setTael(new BigDecimal(0));
            teacherUserCapital.setUserId(teacherId);
            teacherUserCapital.setCreateTime(Calendar.getInstance().getTimeInMillis());
            teacherUserCapital.setVersion(1);
            teacherUserCapital.setStatus((short) 1);
            int i = userCapitalService.insertSelective(teacherUserCapital);
            if (i < 1) {
                logger.error("初始化教头用户资金记录异常 iSearchService.insertTeacherUserCapital(teacherUserCapital)");
                throw new RuntimeException("初始化教头用户资金记录异常 iSearchService.insertTeacherUserCapital(teacherUserCapital)");
            }
        }
        BigDecimal minus_money = new BigDecimal(money).multiply(new BigDecimal(-1));
        //微信打赏未减少会员资金只做日志记录
        BigDecimal user_total_money = userCapital.getTael();
        //增加教头用户资金
        BigDecimal add_money = new BigDecimal(money);
        BigDecimal teacher_user_total_money = teacherUserCapital.getTael();
        int subTeacherCapitalResult = userCapitalService.updateByPrimaryKeySelective(new UserCapital(add_money,teacherId,teacherUserCapital.getCapitalId()));
        if (subTeacherCapitalResult < 1) {
            logger.error("更新会员用户资金记录异常 iSearchService.updateUserCapitalLogByPrimaryKeySelective(userCapital)");
            throw new RuntimeException("更新会员用户资金记录异常 iSearchService.updateUserCapitalLogByPrimaryKeySelective(userCapital)");
        }
        //减少会员资金做日志记录
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        userCapitalLog.setAccount(new BigDecimal(money)); //操作账目
        userCapitalLog.setOperateType((short) -1); //操作类型   -1 减  1  加
        userCapitalLog.setBalance(user_total_money); //操作后账户余额 //此处资金来源于微信，账号金额未被减去
        //userCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户 //此处资金来源于第三方支付，账号金额未被减去
        userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
        userCapitalLog.setDescription(msg);
        userCapitalLog.setRelationType((short) 5); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
        userCapitalLog.setUserId(userId);
        userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
        userCapitalLog.setVersion(1);
        userCapitalLog.setStatus((short) 1);
        userCapitalLog.setRelationId(teacherUserCapital.getCapitalId()); //关联Id 教头会员资金信息表业务ID
        userCapitalLog.setRelationObj(teacherId); //关联对象 教头用户业务ID
        int userCapitalLogInsertResult = userCapitalLogService.insertSelective(userCapitalLog);
        if (userCapitalLogInsertResult < 1) {
            logger.error("减少会员资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
            throw new RuntimeException("减少会员资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
        }
        //增加教头资金做日志记录
        UserCapitalLog teacherCapitalLog = new UserCapitalLog();
        teacherCapitalLog.setAccount(new BigDecimal(money)); //操作账目
        teacherCapitalLog.setOperateType((short) 1); //操作类型   -1 减  1  加
        teacherCapitalLog.setBalance(teacher_user_total_money.add(new BigDecimal(money))); //操作后账户余额 //此处注意userCapital.getTael()是否已经被减去
        //teacherCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户  // 账户类型  1 银两账户  2 积分账户 //此处资金来源于第三方支付，账号金额未被减去
        teacherCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
        teacherCapitalLog.setDescription(msg);
        teacherCapitalLog.setRelationType((short) 5); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
        teacherCapitalLog.setUserId(teacherId);
        teacherCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
        teacherCapitalLog.setVersion(1);
        teacherCapitalLog.setStatus((short) 1);
        teacherCapitalLog.setRelationId(teacherUserCapital.getCapitalId()); //关联Id教头会员资金信息表业务ID
        teacherCapitalLog.setRelationObj(userId); //关联对象 打赏者用户业务ID
        int teacherCapitalLogInsertResult = userCapitalLogService.insertSelective(teacherCapitalLog);
        if (teacherCapitalLogInsertResult < 1) {
            logger.error("增加教头资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
            throw new RuntimeException("增加教头资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
        }
        return 1;
    }


    /**
     * 银两支付
     * @param teacherId
     * @param userId
     * @param money
     * @return
     */
    public int  yinliang( String teacherId,String userId,String money,String msg){
        //查询会员用户资金记录
        UserCapital userCapital = userCapitalService.selectByUserId(userId);
        //查询教头用户资金记录
        UserCapital teacherUserCapital = userCapitalService.selectByUserId(teacherId);
        if (teacherUserCapital == null) {
            teacherUserCapital = new UserCapital();
            teacherUserCapital.setCapitalId(UUID.randomUUID().toString().replace("-", ""));
            teacherUserCapital.setTael(new BigDecimal(0));
            teacherUserCapital.setUserId(teacherId);
            teacherUserCapital.setCreateTime(Calendar.getInstance().getTimeInMillis());
            teacherUserCapital.setVersion(1);
            teacherUserCapital.setStatus((short) 1);
            int i = userCapitalService.insertSelective(teacherUserCapital);
            if (i < 1) {
                logger.error("初始化教头用户资金记录异常 iSearchService.insertTeacherUserCapital(teacherUserCapital)");
                throw new RuntimeException("初始化教头用户资金记录异常 iSearchService.insertTeacherUserCapital(teacherUserCapital)");
            }
        }
        BigDecimal minus_money = new BigDecimal(money).multiply(new BigDecimal(-1));
        //减少会员资金
        BigDecimal user_total_money = userCapital.getTael();
        int subUserCapitalResult = userCapitalService.updateByPrimaryKeySelective(new UserCapital(minus_money,userId,userCapital.getCapitalId()));
        if (subUserCapitalResult < 1) {
            logger.error("更新会员用户资金记录异常 iSearchService.updateUserCapitalLogByPrimaryKeySelective(userCapital)");
            throw new RuntimeException("更新会员用户资金记录异常 iSearchService.updateUserCapitalLogByPrimaryKeySelective(userCapital)");
        }
        //增加教头用户资金
        BigDecimal add_money = new BigDecimal(money);
        BigDecimal teacher_user_total_money = teacherUserCapital.getTael();
        int subTeacherCapitalResult = userCapitalService.updateByPrimaryKeySelective(new UserCapital(add_money,teacherId,teacherUserCapital.getCapitalId()));
        if (subTeacherCapitalResult < 1) {
            logger.error("更新会员用户资金记录异常 iSearchService.updateUserCapitalLogByPrimaryKeySelective(userCapital)");
            throw new RuntimeException("更新会员用户资金记录异常 iSearchService.updateUserCapitalLogByPrimaryKeySelective(userCapital)");
        }
        //减少会员资金做日志记录
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        userCapitalLog.setAccount(new BigDecimal(money)); //操作账目
        userCapitalLog.setOperateType((short) -1); //操作类型   -1 减  1  加
        userCapitalLog.setBalance(user_total_money.subtract(new BigDecimal(money))); //操作后账户余额 //此处注意userCapital.getTael()是否已经被减去
        userCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户
        userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
        userCapitalLog.setDescription(msg);
        userCapitalLog.setRelationType((short) 5); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
        userCapitalLog.setUserId(userId);
        userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
        userCapitalLog.setVersion(1);
        userCapitalLog.setStatus((short) 1);
        userCapitalLog.setRelationId(userCapital.getCapitalId()); //关联Id 会员资金信息表业务ID
        userCapitalLog.setRelationObj(teacherId); //关联对象 教头用户业务ID
        int userCapitalLogInsertResult = userCapitalLogService.insertSelective(userCapitalLog);
        if (userCapitalLogInsertResult < 1) {
            logger.error("减少会员资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
            throw new RuntimeException("减少会员资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
        }
        //增加教头资金做日志记录
        UserCapitalLog teacherCapitalLog = new UserCapitalLog();
        teacherCapitalLog.setAccount(new BigDecimal(money)); //操作账目
        teacherCapitalLog.setOperateType((short) 1); //操作类型   -1 减  1  加
        teacherCapitalLog.setBalance(teacher_user_total_money.add(new BigDecimal(money))); //操作后账户余额 //此处注意userCapital.getTael()是否已经被减去
        teacherCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户
        teacherCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
        teacherCapitalLog.setDescription(msg);
        teacherCapitalLog.setRelationType((short) 5); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
        teacherCapitalLog.setUserId(teacherId);
        teacherCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
        teacherCapitalLog.setVersion(1);
        teacherCapitalLog.setStatus((short) 1);
        teacherCapitalLog.setRelationId(teacherUserCapital.getCapitalId()); //关联Id教头会员资金信息表业务ID
        teacherCapitalLog.setRelationObj(userId); //关联对象 打赏者用户业务ID
        int teacherCapitalLogInsertResult = userCapitalLogService.insertSelective(teacherCapitalLog);
        if (teacherCapitalLogInsertResult < 1) {
            logger.error("增加教头资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
            throw new RuntimeException("增加教头资金做日志记录异常 iSearchService.insertUserCapitalLogSelective(userCapitalLog)");
        }
        return 1;
    }

    @Transactional
    @Override
    public  Map<String,Object> giveTeahcerReward( String teacherId,String userId,String money,String payWay) {
        Map<String,Object> resultMap = new LinkedHashMap<>();
        int payResult=0;
        //生产打赏记录表
        UserTeacherRewardOrder order = new UserTeacherRewardOrder();
        if("0".equals(payWay)){ //支付方式(0:银两支付；1:支付宝；2：微信；3：PayPal 4：Apple Pay)
            payResult = yinliang(teacherId,userId,money,"用户打赏");
            if(payResult == 1){
                order.setOrderStatus(payResult == 1?1:0);//订单状态（0：未支付；1：已支付)
                order.setPayStatus(payResult == 1?1:0);//订单状态（0：代付款；1：已购买)
            }
        }else{//除银两支付外的支付方式
            order.setOrderStatus(0);//订单状态（0：未支付；1：已支付)
            order.setPayStatus(0);//订单状态（0：代付款；1：已购买)
        }
        order.setMoney(new Double(money));
        order.setPayWay(Integer.valueOf(payWay));//支付方式(0:银两支付；1:支付宝；2：微信；3：Apple Pay 4：Andriod Pay)
        order.setTeacherId(teacherId);
        order.setUserId(userId);
        String uuid= UUID.randomUUID().toString().replace("-", "");
        order.setUserTeacherRewardId(uuid);
        order.setCreateTime(Calendar.getInstance().getTimeInMillis());
        // order.setUserTeacherRewardOrderId("b"+uuid); //订单编号优化 2017-09-15
        String orderId = OrderUtil.createOrderNo(OrderEnum.dashang.getKey());
        order.setUserTeacherRewardOrderId(orderId);
        order.setStatus((short)1);
        order.setVersion(1);
        int orderInsert = userTeacherRewardOrderMapper.insertUserTeacherRewardOrder(order);
        resultMap.put("orderId",orderId);
        if(payResult == 1 ){
            resultMap.put("payResult",1);
            return resultMap;
        }if(payResult == 0 && orderInsert == 1){
            resultMap.put("payResult",1);
            return resultMap;
        }else{
            logger.error("打赏教头订单记录异常 userTeacherRewardOrderMapper.insertUserTeacherRewardOrder(order)");
            throw new RuntimeException("打赏教头订单记录异常 userTeacherRewardOrderMapper.insertUserTeacherRewardOrder(order)");
        }

    }

    @Override
    public int updateUserTeacherRewardOrderStatus(String usertTeacherRewardOrderId,String transactionId){
        UserTeacherRewardOrder order = new UserTeacherRewardOrder();
        order.setPayStatus(1);//订单状态（0：代付款；1：已购买)
        order.setOrderStatus(1);//订单状态（0：未支付；1：已支付)
        order.setUserTeacherRewardOrderId(usertTeacherRewardOrderId);
        order.setTransactionId(transactionId);
        int result = userTeacherRewardOrderMapper.updateUserTeacherRewardOrder(order);
        if(result < 1){
            logger.error("更新打赏订单异常 userTeacherRewardOrderMapper.updateUserTeacherRewardOrder(order)");;
            throw new RuntimeException("更新打赏订单异常 userTeacherRewardOrderMapper.updateUserTeacherRewardOrder(order)");
        }else{
            //记录用户资金变动日志
            int i =  updateUserTeacherRewardOrder(order );
            if(i<1){
                logger.error("更新打赏订单记录用户资金日志异常updateUserTeacherRewardOrder(order )");;
                throw new RuntimeException("更新打赏订单记录用户资金日志异常updateUserTeacherRewardOrder(order )");
            }
        }
        return result;
    }

    //记录用户资金变动日志
    public  int  updateUserTeacherRewardOrder(UserTeacherRewardOrder order ){
        UserTeacherRewardOrder rewardOrder =  userTeacherRewardOrderMapper.getUserTeacherRewardOrder(order);
        //讲师用户增加资金
        if(rewardOrder == null){
            logger.error("订单查询异常userTeacherRewardOrderMapper.getUserTeacherRewardOrder(order)");
            throw new RuntimeException("订单查询异常userTeacherRewardOrderMapper.getUserTeacherRewardOrder(order)");
        }
        //讲师用户资金变动日志记录
        //打赏者用户记录资金变动日志记录
        String teacherId = rewardOrder.getTeacherId();
        String userId = rewardOrder.getUserId();
        String money = String.valueOf(rewardOrder.getMoney());
        int payResult = weixin(teacherId,userId,money,"用户打赏");
        return payResult;
    }

    @Override
    public List<UserTeacherQuestion> questionAndReplyList(int pageNum, int pageSize, String teacherId, String userId){
        return userTeacherQuestionMapper.getUserTeacherQuestionAndReplyList(pageNum,pageSize,teacherId,userId);
    }

    @Override
    @Transactional
    public int insertUserAnswerTeacherQuestion( String teacherId, String userId, String question){
        UserTeacherQuestion userTeacherQuestion= new UserTeacherQuestion();
        userTeacherQuestion.setQuestion(question);
        userTeacherQuestion.setTeacherId(teacherId);
        userTeacherQuestion.setUserId(userId);
        userTeacherQuestion.setUserTeacherQuestionId(UUID.randomUUID().toString().replace("-", ""));
        userTeacherQuestion.setCreateTime(Calendar.getInstance().getTimeInMillis());
        userTeacherQuestion.setStatus((short)1);
        userTeacherQuestion.setVersion(1);
        int result = userTeacherQuestionMapper.insertUserTeacherQuestion( userTeacherQuestion);
        if(result <1){
            logger.error("记录教头详情-问题-用户提问记录异常 userTeacherQuestionMapper.insertUserTeacherQuestion( userTeacherQuestion)");
            throw new RuntimeException("记录教头详情-问题-用户提问记录异常 userTeacherQuestionMapper.insertUserTeacherQuestion( userTeacherQuestion)");
        }
        return 1;
    }


    @Override
    @Transactional
    public int insertTeacherReplyUserQuestion( String teacherId, String reply,String userTeacherQuestionId){
        UserTeacherQuestionReply userTeacherQuestionReply= new UserTeacherQuestionReply();
        userTeacherQuestionReply.setReply(reply);
        userTeacherQuestionReply.setTeacherId(teacherId);
        userTeacherQuestionReply.setUserTeacherQuestionId(userTeacherQuestionId);
        userTeacherQuestionReply.setUserTeacherQuestionReplyId(UUID.randomUUID().toString().replace("-", ""));
        userTeacherQuestionReply.setCreateTime(Calendar.getInstance().getTimeInMillis());
        userTeacherQuestionReply.setStatus((short)1);
        userTeacherQuestionReply.setVersion(1);
        int result = userTeacherQuestionReplyMapper.insertUserTeacherQuestionReply(userTeacherQuestionReply);
        if(result <1){
            logger.error("记录教头详情-问题-教头答复记录异常 userTeacherQuestionMapper.insertUserTeacherQuestion( userTeacherQuestion)");
            throw new RuntimeException("记录教头详情-问题-教头答复记录异常 userTeacherQuestionMapper.insertUserTeacherQuestion( userTeacherQuestion)");
        }
        return 1;
    }

    @Override
    public User selecUserDetail(String userId) {
        return userMapper.selecUserDetail(userId);
    }

    @Override
    public void findPay(User user) {
        userMapper.findPay(user);
    }

    @Override
    public List<UserTeacherFocus> selectMyFocusTeachers(int pageNum,int pageSize,UserTeacherFocus focus){
        return userTeacherFocusMapper.selectMyFocusTeachers(pageNum,pageSize,focus.getUserId());
    }

    @Override
    public List<UserTeacherFocus> selectFocusMeUsers(int pageNum,int pageSize,UserTeacherFocus focus){
        return userTeacherFocusMapper.selectFocusMeUsers(pageNum,pageSize,focus.getUserId());
    }

    @Override
    public List<User> selectByUserType(int userType) {

        return userMapper.selectByUserType(userType);
    }

    @Override
    public int checkUserNameOnly(String userName) {
        return userMapper.checkUserNameOnly(userName);
    }

    @Override
    public void updateVIP(User user) {

        userMapper.updateVIP(user);
    }

    @Override
    public String createQrCode(String urlVal,String userId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            boolean b = QrCodeCreateUtil.createQrCode(out,urlVal,1500,"JPEG");
            if(b) {
                ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
                String url = aliyunOssService.upload("qrcode/"+userId+".jpeg",in);
                out.close();
                in.close();
                return url;
            }
        } catch (WriterException e) {
            logger.error("{[]}时，发生异常，异常信息为{[]}","用户注册-生成二维码图片",e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("{[]}时，发生异常，异常信息为{[]}","用户注册-生成二维码图片",e);
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public User getUserMessageByIm(String accid) {
        return userMapper.getUserMessageByIm(accid);
    }

    @Override
    public int checkPhoneIsExist(User user){
        return userMapper.checkPhoneIsExist(user);
    }


    @Override
    public int  deleteFocusMeAndMyFocus(String userId){
        return userTeacherFocusMapper.deleteFocusMeAndMyFocus(userId);
    }

    @Override
    public List<APITearcherUser> searchIndexFourTeachers(Map<String,Object> map){
        List<APITearcherUser>  list =  userMapper.searchIndexFourTeachers(map);
        for(APITearcherUser user :list){
            if(StringUtils.isNotEmpty(user.getRectanglePhoto()))
                user.setPhoto(user.getRectanglePhoto());
        }
        return list;
    }

    @Override
    public User getUserByOpenId(String openId) {
        return userMapper.getUserByOpenId(openId);
    }

    @Override
    public void bindQQ(User user) {
        userMapper.bindQQ(user);
    }

    @Override
    public void changeIdentity(int userIdentity, long expirationDate, String userId) {
        userMapper.changeIdentity(userIdentity,expirationDate,userId);
    }

    @Override
    public User selectUserByPhoneNum(String phone) {

        return userMapper.selectUserByPhoneNum(phone);
    }

    @Override
    public User getUserByWxOpenId(String wxOpenId) {

        return userMapper.getUserByWxOpenId(wxOpenId);
    }

    @Override
    public void bindWx(User user) {

        userMapper.bindWx(user);
    }
    @Override
    public UserTeacherRewardOrder getUserTeacherRewardOrder(UserTeacherRewardOrder order){
        return  userTeacherRewardOrderMapper.getUserTeacherRewardOrder(order);
    }
    @Override
    public User getUserByWeiboOpenId(String weiboOpenId) {
        return userMapper.getUserByWeiboOpenId(weiboOpenId);
    }

    @Override
    public void bindWeiBo(User user) {
        userMapper.bindWeiBo(user);
    }
    @Override
    public PageInfo<UserTeacherIntroImg> searchUserTeacherIntroImgList(int pageNum,int pageSize,String userId ){
        return new PageInfo<UserTeacherIntroImg>(userTeacherIntroImgMapper.searchUserTeacherIntroImgList(pageNum,pageSize ,userId));
    }


    /**
     * 根据ID查询教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    @Override
    public UserTeacherIntroImg searchUserTeacherIntroImgById(String teacherIntroImgId){
        return  userTeacherIntroImgMapper.searchUserTeacherIntroImgById(teacherIntroImgId);
    }

    /**
     * 插入教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    @Override
    @Transactional
    public int insertUserTeacherIntroImg(UserTeacherIntroImg userTeacherIntroImg){
        int insertResult =  userTeacherIntroImgMapper.insertUserTeacherIntroImg(userTeacherIntroImg);
        if(insertResult <1){
            logger.error("插入教头简介图片失败userTeacherIntroImgMapper.insertUserTeacherIntroImg(userTeacherIntroImg)");
            throw new RuntimeException("插入教头简介图片失败userTeacherIntroImgMapper.insertUserTeacherIntroImg(userTeacherIntroImg)");
        }
        return insertResult;
    }

    /**
     * 根据ID删除教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    @Override
    @Transactional
    public int deleteUserTeacherIntroImg(String teacherIntroImgId){
        int delResult =  userTeacherIntroImgMapper.deleteUserTeacherIntroImg(teacherIntroImgId);
        if(delResult <1){
            logger.error("删除教头简介图片失败userTeacherIntroImgMapper.deleteUserTeacherIntroImg(userTeacherIntroImg)");
            throw new RuntimeException("删除教头简介图片失败userTeacherIntroImgMapper.deleteUserTeacherIntroImg(userTeacherIntroImg)");
        }
        return delResult;
    }

    /**
     * 修改教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    @Override
    @Transactional
    public int updateUserTeacherIntroImg(UserTeacherIntroImg userTeacherIntroImg){
        int updateResult =  userTeacherIntroImgMapper.updateUserTeacherIntroImg(userTeacherIntroImg);
        if(updateResult <1){
            logger.error("修改教头简介图片失败userTeacherIntroImgMapper.updateUserTeacherIntroImg(userTeacherIntroImg)");
            throw new RuntimeException("修改教头简介图片失败userTeacherIntroImgMapper.updateUserTeacherIntroImg(userTeacherIntroImg)");
        }
        return updateResult;
    }

    /**
     * 根据教头用户ID查询教头简介图片URL列表
     * @param teacherId
     * @return
     */
    @Override
    public List<String> searchTeacherIntroImgs(String teacherId){
        List<String> list =  userTeacherIntroImgMapper.searchTeacherIntroImgs(teacherId);
        return list;
    }

    /**
     * 合成用户邀请好友图片(合成海报模板与二维码图片)
     * @param user
     * @return
     */
    @Override
    public String  insertQrCodeImg( User user,String shareImg,int imgNumber){
        logger.debug("调用者："+discoveryClient.getServices());
        String urlVal ="";
        try{

            ChartGraphics cg = new ChartGraphics();
            String url = user.getQrCodeUrl();//二维码图片
            if(StringUtils.isEmpty(url)){
                url  =createQrCode(jtxyappUrl + "/api/register/showShare?userId="+user.getUserId(),user.getUserId());
                user.setQrCodeUrl(url);
                userMapper.updateUser(user);
            }
            String createImageUrl = this.getClass().getResource("/").getPath();
            if(!new File(createImageUrl).exists()){
                new File(createImageUrl).mkdirs();
            }
            String tempPng =UUID.randomUUID().toString().replace("-", "")+".png";
            createImageUrl = createImageUrl+tempPng;
            logger.info("name========"+user.getUserName());
            cg.graphicsGeneration(shareImg,url,user.getUserName(),createImageUrl,imgNumber);//合成海报与用户分享二维码码为一个图片
            //上传到网易云服务器上
            File  imageFile = new File(createImageUrl);
            if(null == imageFile || !imageFile.exists()) {
                return "";
            }
            String filename = imageFile.getName();
            urlVal = getImgPath("qrCodeImg")+filename.substring(filename.lastIndexOf("."));
            try {
                urlVal = aliyunOssService.upload(urlVal,new FileInputStream(imageFile));
            } catch (IOException e) {
                logger.error("上传失败",e);
                return "";
            }
            if(StringUtils.isNotEmpty(urlVal)){
                imageFile.delete();
            }
        }catch (Exception e){
            logger.error("合成的海报分享二维码图片失败"+e.getMessage());
            e.printStackTrace();
        }
        return urlVal;
    }


    private static String getImgPath(String dir) {
        LocalDate date = LocalDate.now();
        if(StringUtils.isNotBlank(dir)) {
            return dir+"/"+date.getYear()+"/"+date.getMonth().getValue()+"/"+date.getDayOfMonth()+"/"+ UUID.randomUUID();
        }
        return "img/"+date.getYear()+"/"+date.getMonth().getValue()+"/"+date.getDayOfMonth()+"/"+ UUID.randomUUID();
    }

    /**
     * 合成邀请好友图片
     * @param user
     * @return
     */
    @Override
    public   String  batchQrCode( User user) {
        ConfigSetting configSetting0 = configSettingMapper.selectConfigSettingBySign("share0");
        ConfigSetting configSetting1 = configSettingMapper.selectConfigSettingBySign("share1");
        ConfigSetting configSetting2 = configSettingMapper.selectConfigSettingBySign("share2");
        String qrCodeImgurl ="";
        String share0 = "";
        String share1 = "";
        String share2 = "";
        String urlVal0="";
        String urlVal1="";
        String urlVal2="";
        if (configSetting0 != null){
            share0 = configSetting0.getConfig_value();
        }
        if (configSetting1 != null){
            share1 = configSetting1.getConfig_value();
        }
        if (configSetting2 != null){
            share2 = configSetting2.getConfig_value();
        }

        //第一张海报
        if(StringUtils.isNotEmpty(share0)){
            urlVal0 = insertQrCodeImg(user,share0,1);
            if(StringUtils.isNotBlank(urlVal0)){
                qrCodeImgurl +=urlVal0+",";
            }
        }
        //第二张海报
        if(StringUtils.isNotEmpty(share1)){
            urlVal1 = insertQrCodeImg(user,share1,2);
            if(StringUtils.isNotBlank(urlVal1)){
                qrCodeImgurl +=urlVal1+",";
            }

        }

        //第三张海报
        if(StringUtils.isNotEmpty(share2)){
            urlVal2 =insertQrCodeImg(user,share2,3);
            if(StringUtils.isNotBlank(urlVal2)){
                qrCodeImgurl +=urlVal2+",";
            }

        }
        return qrCodeImgurl;

    }

    @Override
    public int change_forever(long expirationDate, String userTypeId, String userId) {
        return userMapper.change_forever(expirationDate,userTypeId,userId);
    }

    /*
   * 建立上下级关系
   * @param user
   * @return
   */
    private int registerOperRelation(User user){
        int result = 0;
        try{
            if(null != user && StringUtils.isNotBlank(user.getCommendname())){

                String commendname = user.getCommendname();
                String recommendPhoneCode = commendname.substring(0,commendname.indexOf("-"));
                String recomemdPhone = commendname.substring(commendname.indexOf("-")+1,commendname.length());
                User recommendUser = userMapper.selectByPhone(recommendPhoneCode,recomemdPhone);
                if(null != recommendUser){
                    result = profitShareRelationService.addProfitShareRelation(recommendUser.getUserId(),user.getUserId());
                }else{
                    result = 3;
                }
            }else{
                result = 2;
            }
        }catch (Exception e){
            logger.error("{[]}时，发生异常，异常信息为:{[]}","用户注册-确立上下级关系",e.getMessage());
        }
        return result;

    }

    /**
     * 消息列表-用户提问问题列表或向用户提问问题列表
     * @param userId
     * @param type 查询类别0：我的提问列表，1：向用户提问列表
     * @return
     */
    @Override
    public List<UserTeacherQuestion> questionOrReplyList(int pageNum,int pageSize,String userId , String type){
        List<UserTeacherQuestion> list = null;
        if(type.equals("0")){
            list = userTeacherQuestionMapper.myQuestionsList(pageNum,pageSize,userId);
        }else if(type.equals("1")){
            list = userTeacherQuestionMapper.otherQuestionsList(pageNum,pageSize,userId);
        }

        return list;
    }


    /**
     * 根据用户名查询用户
     * @param countryCode
     * @param phone
     * @return
     */
    @Override
    public User selectByPhoneNum(String countryCode, String phone){
        return userMapper.selectByPhoneNum(countryCode,phone);
    }

    @Override
    public String selectThePublicOpenId(String userId) {
        return userMapper.selectThePublicOpenId(userId);
    }

    @Override
    public int updateThePublicOpenId(User user) {

        int flagVal = userMapper.updateThePublicOpenId(user);
        if(flagVal < 1){
            logger.error("添加公众号openId/admin-provide/updateThePublicOpenId)");
            throw new RuntimeException("添加公众号openId/admin-provide/updateThePublicOpenId)");
        }
        return flagVal;
    }

    @Override
    public void updatePwdByUserId(User user) {
        userMapper.updatePwdByUserId(user);
    }

    @Override
    public void updatePayByUserId(User user) {
        userMapper.updatePayByUserId(user);
    }

    /**
     * 批量生成系统用户二维码
     * @return
     */
    @Override
    public boolean  batchAllUserQrCodeImg(){
        try{
            Long start = System.currentTimeMillis();
            logger.info(" admin 批量生成系统用户二维码开始时间："+start);
            List<User> users = this.getUserList();
            for(User user:users){
                User updater = new User();
                String url="";
                try {
                    url = createQrCode(jtxyappUrl + "/api/register/showShare?userId=" + user.getUserId(), user.getUserId());
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("用户username为："+user.getUserName() +"用户重新生成二维码图片异常："+e.getMessage());
                }
                updater.setUserId(user.getUserId());
                updater.setQrCodeUrl(url);
                try {
                    userMapper.updateUser(updater);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("记录用户username为："+user.getUserName() +"的新二维码图片异常："+e.getMessage());
                }
                logger.info("用户username为："+user.getUserName() +"用户重新生成二维码记录成功");
            }

            Long end = System.currentTimeMillis();
            logger.info(" admin 批量生成系统用户二维码结束时间："+end + "，合计用时 end-start" + (end-start) +" 毫秒");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("admin 批量生成系统用户二维码异常："+e.getMessage());
        }
        return true;
    }
}

