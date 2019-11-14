package cn.com.myproject.course.service.Impl;

import cn.com.myproject.admincon.service.impl.ProfitShareRelationDealService;
import cn.com.myproject.aliyun.push.IAliyunPushService;
import cn.com.myproject.course.mapper.CourseMapper;
import cn.com.myproject.course.mapper.CourseShareMapper;
import cn.com.myproject.course.mapper.CourseTypeMapper;
import cn.com.myproject.course.service.ICourseService;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.service.IMessageRecordService;
import cn.com.myproject.enums.CapitalLogDesEnum;
import cn.com.myproject.live.entity.PO.*;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseOrderVO;
import cn.com.myproject.live.entity.VO.CourseReplyVO;
import cn.com.myproject.live.mapper.CourseCommentMapper;
import cn.com.myproject.live.mapper.CourseOrderMapper;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.mapper.UserCapitalLogMapper;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

/*
 * Created by pangdatao on 2017-08-16
 * desc：课程Service接口实现类
 */
@Service
public class CourseService implements ICourseService{

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Autowired
    private UserCapitalLogMapper userCapitalLogMapper;

    @Autowired
    private CourseShareMapper courseShareMapper;

    @Autowired
    private ProfitShareRelationDealService profitShareRelationDealService;

    @Autowired
    private IMessageRecordService messageRecordService;

    @Autowired
    private IAliyunPushService aliyunPushService;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    /**
     * 根据课程ID查询该课程的订单数量
     *
     * @param courseId
     * @return
     */
    @Override
    public int searchCoCountByCId(String courseId,String userId){
        return courseMapper.selectCoCountByCId(courseId,userId);
    }

    /**
     * 根据课程ID查询文字课程文章是否观看的标记
     *
     * @param courseId
     * @return
     */
    @Override
    public Integer searchWzkcFlag(String courseId,String userId){
        return courseMapper.selectWakcFlag(courseId,userId);
    }

    /**
     * 根据课程ID更新文字课程文章是否观看的标记
     *
     * @param courseId
     * @return
     */
    @Override
    @Transactional
    public boolean modifyWzkcFlag(String courseId,String userId){
        boolean flagVal = false;
        int countVal = courseMapper.updateWzkcFlag(courseId,userId);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据课程ID查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course searchCourseInfoById(String courseId){
        return courseMapper.searchCourseInfoById(courseId);
    }

    /**
     * 发布课程信息
     *
     * @param c
     * @return
     */
    @Override
    @Transactional
    public boolean addCourse(Course c){
        boolean flagVal = false;
        int countVal = courseMapper.insertCourse(c);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据courseId删除课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    @Transactional
    public boolean removeCourse(String courseId){
        boolean flagVal = false;
        int countVal = courseMapper.deleteCourse(courseId);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 修改课程信息
     *
     * @param c
     * @return
     */
    @Override
    @Transactional
    public boolean modifyCourse(Course c){
        boolean flagVal = false;
        int countVal = courseMapper.updateCourse(c);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

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
    @Override
    public PageInfo<Course> searchCourseList(int pageNum, int pageSize, String typeVal, String courseTitle, String zbType){
        List<Course> cList = courseMapper.selectCourseList(pageNum,pageSize,typeVal,courseTitle,zbType);
        return convert(cList);
    }

    /**
     * 查询讲师列表
     *
     * @return
     */
    @Override
    public List<User> searchTeaList(){
        return courseMapper.searchTeaList();
    }

    /**
     * 根据courseId查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course searchCourseById(String courseId){
        return courseMapper.searchCourseById(courseId);
    }

    /**
     * 修改视频课程内容
     *
     * @param c
     * @return
     */
    @Override
    @Transactional
    public boolean modifyVideo(Course c){
        boolean flagVal = false;
        int countVal = courseMapper.updateVideo(c);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据courseId查询课程考题
     *
     * @param courseId
     * @return
     */
    @Override
    public List<CourseTopic> searchCtListByCId(String courseId){
        return courseMapper.searchCtListByCId(courseId);
    }

    /**
     * 修改文字课程内容
     *
     * @param c
     * @return
     */
    @Override
    @Transactional
    public boolean modifyCourseContent(Course c){
        boolean flagVal = false;
        int countVal = courseMapper.updateCourseContent(c);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据courseId查询文字课程内容
     *
     * @param courseId
     * @return
     */
    @Override
    public Course searchCourseContentById(String courseId){
        return courseMapper.searchCourseContentById(courseId);
    }

    /**
     * 根据courseId查询课程考题数量
     *
     * @param courseId
     * @return
     */
    @Override
    public int searchCtCountByCId(String courseId){
        return courseMapper.searchCtCountByCId(courseId);
    }

    /**
     * 查询全部课程信息
     *
     * @param pageNum
     * @param pageSize
     * @param courseTypeId
     * @param courseTitle
     * @param zbType
     * @return
     */
    @Override
    public List<Course> searchAllCourseList(int pageNum,int pageSize,String courseTypeId,String courseTitle,String zbType,String typeVal,int courseTypeLevel){
        return courseMapper.searchAllCourseList(pageNum,pageSize,courseTypeId,courseTitle,zbType,typeVal,courseTypeLevel);
    }

    /**
     * 根据课程ID查询课程评论列表
     *
     * @param pageNum
     * @param pageSize
     * @param courseId
     * @return
     */
    @Override
    public List<CourseCommentVO> searchCcList(int pageNum, int pageSize, String courseId){
        return courseCommentMapper.searchCcList(pageNum,pageSize,courseId);
    }

    /**
     * 根据评论ID查询评论回复列表
     *
     * @param ccId
     * @return
     */
    @Override
    public List<CourseReplyVO> searchCrList(String ccId){
        return courseCommentMapper.searchCrList(ccId);
    }

    /**
     * 添加评论
     *
     * @param courseComment
     * @return
     */
    @Override
    @Transactional
    public boolean addComm(CourseComment courseComment){
        boolean flagVal = false;
        int countVal = courseCommentMapper.insertComm(courseComment);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据用户ID和讲师ID查询用户是否关注该讲师
     *
     * @param uId
     * @param tId
     * @return
     */
    @Override
    public int searchAttCount(String uId,String tId){
        return courseMapper.searchAttCount(uId,tId);
    }

    /**
     * 根据用户ID和课程ID查询用户是否收藏该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    @Override
    public int searchCollCount(String uId,String cId){
        return courseMapper.searchCollCount(uId,cId);
    }

    /**
     * 根据用户ID和课程ID查询用户是否已购买该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    @Override
    public int searchOrderCount(String uId,String cId){
        return courseMapper.searchOrderCount(uId,cId);
    }

    /**
     * 收藏课程
     *
     * @param courseCollect
     * @return
     */
    @Override
    @Transactional
    public boolean addCc(CourseCollect courseCollect){
        boolean flagVal = false;
        int countVal = courseMapper.insertCc(courseCollect);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 取消收藏课程
     *
     * @param courseCollect
     * @return
     */
    @Override
    @Transactional
    public boolean removeCc(CourseCollect courseCollect){
        boolean flagVal = false;
        int countVal = courseMapper.deleteCc(courseCollect);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据课程ID和用户ID查询用户课程订单信息
     *
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public CourseOrderVO searchCo(String courseId, String userId){
        CourseOrderVO courseCo = courseMapper.searchOrderCourse(courseId);
        CourseOrderVO userCo = courseMapper.searchOrderUser(userId);
        if(courseCo != null && userCo != null){
            courseCo.setUserId(userCo.getUserId());
            courseCo.setUserName(userCo.getUserName());
            courseCo.setUserPhone(userCo.getUserPhone());
            courseCo.setUserEmail(userCo.getUserEmail());
            courseCo.setRealName(userCo.getRealName());
        }
        return courseCo;
    }

    /**
     * 插入订单数据
     *
     * @param courseOrder
     * @return
     */
    @Override
    @Transactional
    public boolean addOrder(CourseOrder courseOrder){
        boolean flagVal = false;
        // 新建订单之前，先判断是否已经下过单，如果已经下过单，则先取消原来的订单，再下单
        List<String> coList = courseOrderMapper.searchOrderByUIdAndCId(courseOrder.getUserId(),courseOrder.getCourseId());
        int cancelVal = 1;
        if(coList != null && coList.size() > 0){
            // 取消订单
            for(int m = 0;m < coList.size();m++){
                if(courseMapper.deleteCoByNo(coList.get(m)) == 0){
                    cancelVal = 0;
                }
            }
            if(cancelVal > 0){
                int countVal = courseOrderMapper.insertOrder(courseOrder);
                if(countVal > 0){
                    if(courseOrder.getPayType() == 0){// 如果是银两支付成功的话，则扣除用户银两余额，然后记录操作日志

                        // 查询用户银两信息
                        UserCapital userCapital = userCapitalMapper.selectByUserId(courseOrder.getUserId());
                        BigDecimal minus_money = new BigDecimal(String.valueOf(courseOrder.getTotalMoney()*10)).multiply(new BigDecimal("-1"));
                        BigDecimal user_total_money = userCapital.getTael();

                        // 更新用户银两信息
                        UserCapital uc = new UserCapital();
                        uc.setTael(minus_money);
                        uc.setCapitalId(userCapital.getCapitalId());
                        int subUserCapitalResult = userCapitalMapper.updateByPrimaryKeySelective(uc);

                        // 记录操作日志
                        if (subUserCapitalResult > 0) {
                            UserCapitalLog userCapitalLog = new UserCapitalLog();
                            userCapitalLog.setAccount(minus_money.multiply(new BigDecimal("-1"))); // 操作账目
                            userCapitalLog.setOperateType((short) -1); // 操作类型   -1 减  1  加
                            userCapitalLog.setBalance(user_total_money.subtract(new BigDecimal(String.valueOf(courseOrder.getTotalMoney()*10)))); // 操作后账户余额
                            userCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户
                            userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
                            userCapitalLog.setDescription(CapitalLogDesEnum.BUY_COUSE.getKey());
                            userCapitalLog.setRelationType((short) 3); // 关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
                            userCapitalLog.setUserId(courseOrder.getUserId());
                            userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
                            userCapitalLog.setVersion(1);
                            userCapitalLog.setStatus((short) 1);
                            int userCapitalLogInsertResult = userCapitalLogMapper.insertSelective(userCapitalLog);
                            if(userCapitalLogInsertResult > 0){
                                int orderCountVal = modifyPayStatus(courseOrder.getCourseOrderNo(),"");
                                if(orderCountVal > 0){
                                    flagVal = true;
                                }else{
                                    logger.info("购买课程成功扣除用户银两后更新订单状态失败");
                                    throw new RuntimeException("购买课程成功扣除用户银两后更新订单状态失败");
                                }
                            }else{
                                logger.info("购买课程扣除用户银两日志记录失败");
                                throw new RuntimeException("购买课程扣除用户银两日志记录失败");
                            }
                        }else{
                            logger.info("购买课程扣除用户银两失败");
                            throw new RuntimeException("购买课程扣除用户银两失败");
                        }
                    }else{
                        flagVal = true;
                    }
                }else{
                    logger.info("购买课程生成订单失败");
                    throw new RuntimeException("购买课程生成订单失败");
                }
            }else{
                logger.info("取消订单失败");
                throw new RuntimeException("取消订单失败");
            }
        }else{
            int countVal = courseOrderMapper.insertOrder(courseOrder);
            if(countVal > 0){
                if(courseOrder.getPayType() == 0){// 如果是银两支付成功的话，则扣除用户银两余额，然后记录操作日志

                    // 查询用户银两信息
                    UserCapital userCapital = userCapitalMapper.selectByUserId(courseOrder.getUserId());
                    BigDecimal minus_money = new BigDecimal(String.valueOf(courseOrder.getTotalMoney()*10)).multiply(new BigDecimal("-1"));
                    BigDecimal user_total_money = userCapital.getTael();

                    // 更新用户银两信息
                    UserCapital uc = new UserCapital();
                    uc.setTael(minus_money);
                    uc.setCapitalId(userCapital.getCapitalId());
                    int subUserCapitalResult = userCapitalMapper.updateByPrimaryKeySelective(uc);

                    // 记录操作日志
                    if (subUserCapitalResult > 0) {
                        UserCapitalLog userCapitalLog = new UserCapitalLog();
                        userCapitalLog.setAccount(minus_money.multiply(new BigDecimal("-1"))); // 操作账目
                        userCapitalLog.setOperateType((short) -1); // 操作类型   -1 减  1  加
                        userCapitalLog.setBalance(user_total_money.subtract(new BigDecimal(String.valueOf(courseOrder.getTotalMoney()*10)))); // 操作后账户余额
                        userCapitalLog.setAccountType((short) 1);// 账户类型  1 银两账户  2 积分账户
                        userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
                        userCapitalLog.setDescription(CapitalLogDesEnum.BUY_COUSE.getKey());
                        userCapitalLog.setRelationType((short) 3); // 关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
                        userCapitalLog.setUserId(courseOrder.getUserId());
                        userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
                        userCapitalLog.setVersion(1);
                        userCapitalLog.setStatus((short) 1);
                        int userCapitalLogInsertResult = userCapitalLogMapper.insertSelective(userCapitalLog);
                        if(userCapitalLogInsertResult > 0){
                            int orderCountVal = modifyPayStatus(courseOrder.getCourseOrderNo(),"");
                            if(orderCountVal > 0){
                                flagVal = true;
                            }else{
                                logger.info("购买课程成功扣除用户银两后更新订单状态失败");
                                throw new RuntimeException("购买课程成功扣除用户银两后更新订单状态失败");
                            }
                        }else{
                            logger.info("购买课程扣除用户银两日志记录失败");
                            throw new RuntimeException("购买课程扣除用户银两日志记录失败");
                        }
                    }else{
                        logger.info("购买课程扣除用户银两失败");
                        throw new RuntimeException("购买课程扣除用户银两失败");
                    }
                }else{
                    flagVal = true;
                }
            }else{
                logger.info("购买课程生成订单失败");
                throw new RuntimeException("购买课程生成订单失败");
            }
        }

        return flagVal;
    }

    /**
     * 修改订单状态
     *
     * @param courseOrder
     * @return
     */
    @Override
    @Transactional
    public boolean modifyOrderStatus(CourseOrder courseOrder){
        boolean flagVal = false;
        int countVal = courseOrderMapper.updateOrderStatus(courseOrder);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据用户ID查询用户支付密码
     *
     * @param userId
     * @return
     */
    @Override
    public String searchUserPayPass(String userId){
        return courseOrderMapper.searchUserPayPass(userId);
    }

    /**
     * 根据用户ID和用户支付密码判断用户支付密码是否正确
     *
     * @param userId
     * @param payPass
     * @return
     */
    @Override
    public boolean searchUserPpCount(String userId,String payPass){
        boolean flagVal = false;
        int countVal = courseOrderMapper.searchUserPpCount(userId,payPass);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * 根据课程订单编号修改订单支付状态
     *
     * @param courseOrderNo
     * @return
     */
    @Override
    @Transactional
    public int modifyPayStatus(String courseOrderNo,String transactionId){

        int returnVal = 0;

        int result = courseOrderMapper.updatePayStatus(courseOrderNo,transactionId);

        if(result > 0){

            // 分润
            profitShareRelationDealService.dealCourseOrder(courseOrderNo.substring(1));

            // 购买课程，送给用户积分，买多少钱的课程，送多少积分
            CourseOrder courseOrder = courseOrderMapper.searchCoInfoByCno(courseOrderNo);

            // 查询用户积分信息
            UserCapital userCapital = userCapitalMapper.selectByUserId(courseOrder.getUserId());
            if(userCapital == null){// 如果用户资金表不存在该用户信息，则插入该用户信息
                userCapital = new UserCapital();
                userCapital.setUserId(courseOrder.getUserId());
                userCapital.setIntegral(new BigDecimal(0));
                userCapital.setTael(new BigDecimal(0));
                userCapital.setFreezeintegral(new BigDecimal(0));
                userCapital.setFreezetael(new BigDecimal(0));
                userCapital.setCapitalId(UUID.randomUUID().toString().replace("-", ""));
                userCapital.setCreateTime(new Date().getTime());
                userCapital.setVersion(1);
                userCapital.setStatus((short)1);
                userCapitalMapper.insert(userCapital);
            }

            // 买多少钱的课程，送多少积分
            BigDecimal add_jifen = new BigDecimal(String.valueOf(courseOrder.getTotalMoney()));
            BigDecimal user_total_jifen = userCapital.getIntegral();
            UserCapital uc = new UserCapital();
            uc.setIntegral(add_jifen);
            uc.setCapitalId(userCapital.getCapitalId());
            int subUserCapitalResult = userCapitalMapper.updateByPrimaryKeySelective(uc);

            // 记录操作日志
            if (subUserCapitalResult > 0) {
                UserCapitalLog userCapitalLog = new UserCapitalLog();
                userCapitalLog.setAccount(new BigDecimal(String.valueOf(courseOrder.getTotalMoney()))); // 操作账目
                userCapitalLog.setOperateType((short) 1); // 操作类型   -1 减  1  加
                userCapitalLog.setBalance(user_total_jifen.add(new BigDecimal(String.valueOf(courseOrder.getTotalMoney())))); // 操作后账户积分
                userCapitalLog.setAccountType((short) 2);// 账户类型  1 银两账户  2 积分账户
                userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
                userCapitalLog.setDescription(CapitalLogDesEnum.BUY_COUSE.getKey());
                userCapitalLog.setRelationType((short) 3); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏）
                userCapitalLog.setUserId(courseOrder.getUserId());
                userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
                userCapitalLog.setVersion(1);
                userCapitalLog.setStatus((short) 1);
                int userCapitalLogInsertResult = userCapitalLogMapper.insertSelective(userCapitalLog);
                if(userCapitalLogInsertResult > 0){
                    returnVal = 1;
                    pushBuyCourseMessage(courseOrder);
                }else{
                    logger.info("购买课程后送给用户积分记录日志失败");
                    throw new RuntimeException("购买课程后送给用户积分记录日志失败");
                }
            }else{
                logger.info("购买课程后送给用户积分失败");
                throw new RuntimeException("购买课程后送给用户积分失败");
            }
        }else{
            logger.info("购买课程后更新订单状态失败");
            throw new RuntimeException("购买课程后更新订单状态失败");
        }
        return returnVal;
    }

    /**
     * 分享课程
     *
     * @param courseShare
     * @return
     */
    @Override
    @Transactional
    public boolean shareCourse(CourseShare courseShare){
        boolean flagVal = false;
        int scCountVal = courseShareMapper.searchCourseShareCount(courseShare.getShareUserId());
        String csId = UUID.randomUUID().toString().replace("-", "");
        courseShare.setCourseShareId(csId);
        courseShare.setShareTime(new Date().getTime());
        int addScCountVal = courseShareMapper.insertCourseShare(courseShare);
        if(addScCountVal > 0){
            if(scCountVal < 5){// 如果每天分享次数小于等于5次，则每次分享送给用户20积分，否则不送积分

                // 查询用户积分信息
                UserCapital userCapital = userCapitalMapper.selectByUserId(courseShare.getShareUserId());
                if(userCapital == null){// 如果用户资金表不存在该用户信息，则插入该用户信息
                    userCapital = new UserCapital();
                    userCapital.setUserId(courseShare.getShareUserId());
                    userCapital.setIntegral(new BigDecimal(0));
                    userCapital.setTael(new BigDecimal(0));
                    userCapital.setFreezeintegral(new BigDecimal(0));
                    userCapital.setFreezetael(new BigDecimal(0));
                    userCapital.setCapitalId(UUID.randomUUID().toString().replace("-", ""));
                    userCapital.setCreateTime(new Date().getTime());
                    userCapital.setVersion(1);
                    userCapital.setStatus((short)1);
                    userCapitalMapper.insert(userCapital);
                }

                // 每次分享给用户20积分
                BigDecimal add_jifen = new BigDecimal(20);
                BigDecimal user_total_jifen = userCapital.getIntegral();
                UserCapital uc = new UserCapital();
                uc.setIntegral(add_jifen);
                uc.setCapitalId(userCapital.getCapitalId());
                int subUserCapitalResult = userCapitalMapper.updateByPrimaryKeySelective(uc);

                // 记录操作日志
                if (subUserCapitalResult > 0) {
                    UserCapitalLog userCapitalLog = new UserCapitalLog();
                    userCapitalLog.setAccount(new BigDecimal(20)); // 操作账目
                    userCapitalLog.setOperateType((short) 1); // 操作类型   -1 减  1  加
                    userCapitalLog.setBalance(user_total_jifen.add(new BigDecimal(20))); // 操作后账户积分
                    userCapitalLog.setAccountType((short) 2);// 账户类型  1 银两账户  2 积分账户
                    userCapitalLog.setCapitalLogId(UUID.randomUUID().toString().replace("-", ""));
                    userCapitalLog.setDescription(CapitalLogDesEnum.SHARE_COUSE.getKey());
                    userCapitalLog.setRelationType((short) 9); //关联类型 （1、充值  2、提现  3 、消费  4、签到  5、打赏  6、课程分润 7、会员分润 8、商品分润 9、分享课程 10、开设课程）
                    userCapitalLog.setUserId(courseShare.getShareUserId());
                    userCapitalLog.setCreateTime(Calendar.getInstance().getTimeInMillis());
                    userCapitalLog.setVersion(1);
                    userCapitalLog.setStatus((short) 1);
                    int userCapitalLogInsertResult = userCapitalLogMapper.insertSelective(userCapitalLog);
                    if(userCapitalLogInsertResult > 0){
                        flagVal = true;
                    }else{
                        logger.info("分享课程给用户发放积分记录日志失败");
                        throw new RuntimeException("分享课程给用户发放积分记录日志失败");
                    }
                }else{
                    logger.info("分享课程给用户发放积分失败");
                    throw new RuntimeException("分享课程给用户发放积分失败");
                }
            }else{
                flagVal = true;
            }
        }else{
            logger.info("分享课程失败");
            throw new RuntimeException("分享课程失败");
        }
        return flagVal;
    }

    /**
     * 查询最近三天的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public List<Course> searchLtcList(int pageNum,int pageSize,String userId){
        return courseMapper.searchLtcList(pageNum,pageSize,userId);
    }

    /**
     * 查询我的课程
     *
     * @param pageNum
     * @param pageSize
     * @param flagVal
     * @param userId
     * @return
     */
    @Override
    public List<Course> searchMyCourseList(int pageNum,int pageSize,int flagVal,String userId){
        List<Course> courseList = new ArrayList<Course>();
        if(flagVal == 1){// 1代表发布的课程
            courseList.addAll(courseMapper.serachMyFbcList(pageNum,pageSize,userId));
        }
        if(flagVal == 2){// 2代表购买的课程
            courseList.addAll(courseMapper.serachMyGmcList(pageNum,pageSize,userId));
        }
        return courseList;
    }

    /**
     * 查询我收藏的课程
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public List<Course> searchMyCollectCourseList(int pageNum,int pageSize,String userId){
        return courseMapper.searchMyCcList(pageNum,pageSize,userId);
    }

    /**
     * 根据订单编号删除订单
     *
     * @param courseOrderNo
     * @return
     */
    @Override
    @Transactional
    public int removeCoByNo(String courseOrderNo){
        return courseMapper.deleteCoByNo(courseOrderNo);
    }

    /**
     * 根据用户ID查询我的课程数量
     *
     * @param userId
     * @return
     */
    @Override
    public int searchMyCourseCount(String userId){
        // 根据用户ID获取用户类型：1代表教头，2代表普通用户
        Integer userType = courseMapper.searchUserType(userId);
        Integer returnCountVal = 0;
        if(userType == 1){// 如果是教头，则返回发布课程的数量
            returnCountVal = courseMapper.serachMyFbcCount(userId);
        }else if(userType == 2){// 如果是普通用户，则返回购买课程的数量
            returnCountVal = courseMapper.serachMyGmcCount(userId);
        }else{// 默认返回发布课程和购买课程的数量之和
            returnCountVal = courseMapper.serachMyFbcCount(userId)+courseMapper.serachMyGmcCount(userId);
        }
        return returnCountVal == null?0:returnCountVal;
    }

    /**
     * 根据用户ID查询我的收藏数量
     *
     * @param userId
     * @return
     */
    @Override
    public int searchMyCcCount(String userId){
        Integer num  = courseMapper.searchMyCcCount(userId);
        return num == null ? 0:num;
    }

    /**
     * 查询所有的课程
     *
     * @return
     */
    @Override
    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse();
    }

    /**
     * 根据课程ID和用户ID查询用户是否针对该课程答过题
     *
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public int searchDtCount(String courseId,String userId){
        return courseMapper.searchDtCount(courseId,userId);
    }

    /**
     * 查询课程评论总数量
     *
     * @param courseId
     * @return
     */
    @Override
    public int searchCcCount(String courseId){
        return courseCommentMapper.searchCcCount(courseId);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<Course> convert(List<Course> list) {
        PageInfo<Course> info = new PageInfo(list);
        List<Course> _list = info.getList();
        info.setList(null);
        List<Course> __list = new ArrayList<>(10);
        PageInfo<Course> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(Course c : _list) {
                __list.add(c);
            }
            _info.setList(__list);
        }
        return _info;
    }

    @Override
    public List<Course> selectCategory(String type_val) {
        return courseMapper.selectCategory(type_val);
    }

    @Override
    public String selectCourseType(String courseId) {
        return courseMapper.selectCourseType(courseId);
    }

    @Override
    public List<Course> getCourseByOrderUserId(int pageNum, int pageSize, String userId) {
        return courseMapper.getCourseByOrderUserId(pageNum,pageSize,userId);
    }

    @Override
    public int buyCourseNum(String userId) {
        return courseMapper.buyCourseNum(userId);
    }

    @Override
    public int buyCourseTeacherNum(String userId) {
        return courseMapper.buyCourseTeacherNum(userId);
    }

    @Override
    public List<Course> searchAPICourseList(int pageNum, int pageSize, String typeVal, String courseTitle, String zbType){
        List<Course> cList = courseMapper.searchCourseList(pageNum,pageSize,typeVal,courseTitle,zbType);
        return cList;
    }

    @Override
    public List<Course> searchLiveCourseList(String typeVal, String courseTitle, String zbType,String teacherId){
        List<Course> cList = courseMapper.searchLiveCourseList(typeVal,courseTitle,zbType,teacherId);
        return cList;
    }

    @Override
    public List<Course> searchCourseListByTeacherId(String pageNum,String pageSize, String teacherId,String type){
        List<Course> cList = courseMapper.searchCourseListByTeacherId(pageNum,pageSize, teacherId,type);
        return cList;
    }

    private void pushBuyCourseMessage(CourseOrder courseOrder){

        Course course = courseMapper.searchCourseById(courseOrder.getCourseId());
        if(null != course){

            MessageRecord buyCourseMessage = new MessageRecord();
            buyCourseMessage.setTitle("购买课程");
            buyCourseMessage.setIntro("课程购买成功");
            buyCourseMessage.setContent("课程购买成功");
            buyCourseMessage.setSendUserId("-1");
            buyCourseMessage.setClassify((short)2);
            buyCourseMessage.setMessageType((short)1);
            buyCourseMessage.setReceiveUserId(courseOrder.getUserId());
            buyCourseMessage.setRelationId(courseOrder.getUserId());
            buyCourseMessage.setRelationType((short)1);
            buyCourseMessage.setCreateTime(new Date().getTime());
            buyCourseMessage.setMessageId(UUID.randomUUID().toString().replace("-",""));
            buyCourseMessage.setUrl(course.getCourseCover());
            buyCourseMessage.setStatus((short)1);
            buyCourseMessage.setVersion(1);
            buyCourseMessage.setMessageStatus((short)0);
            buyCourseMessage.setOperId(course.getCourseId());
            CourseType courseType = courseTypeMapper.searchCtById(course.getCourseTypeId());
            if(null != courseType){
                buyCourseMessage.setOperType(courseType.getTypeVal());
            }

            messageRecordService.insert(buyCourseMessage);

            //发送通知
            aliyunPushService.pushNotice("购买课程","课程购买成功",courseOrder.getUserId(),"{\"type\":\"buycourse\",\"courseId\":\""+course.getCourseTypeId()+"\",\"courseTypeVal\":\""+courseType.getCourseTypeId()+"\"}");

        }
    }
}