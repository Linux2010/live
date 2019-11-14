package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * Created by JYP on 2017/8/15 0015.
 */
@Mapper
public interface CourseOrderMapper {

    List<CourseOrder> getPageForQuery(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("map") Map<String, Object> map);

    CourseOrder getById(String corderid);

    void addcorder(CourseOrder courseOrder);

    void updatecorder(CourseOrder courseOrder);

    Integer ckeckPurchase(CourseOrder courseOrder);

    /**
     * 插入订单数据
     *
     * @param courseOrder
     * @return
     */
    public int insertOrder(CourseOrder courseOrder);

    /**
     * 修改订单状态
     *
     * @param courseOrder
     * @return
     */
    public int updateOrderStatus(CourseOrder courseOrder);

    /**
     * 根据用户ID查询用户支付密码
     *
     * @param userId
     * @return
     */
    public String searchUserPayPass(@Param("userId") String userId);

    /**
     * 根据用户ID和用户支付密码判断用户支付密码是否正确
     *
     * @param userId
     * @param payPass
     * @return
     */
    public int searchUserPpCount(@Param("userId") String userId,@Param("payPass") String payPass);

    /**
     * 根据课程订单编号修改订单支付状态
     *
     * @param courseOrderNo
     * @param  transactionId
     * @return
     */
    public int updatePayStatus(@Param("courseOrderNo") String courseOrderNo,@Param("transactionId") String transactionId);

    List<Course> select_today_study(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("userId") String userId);

    /**
     * 根据订单编号查询订单信息
     *
     * @param courseOrderNo
     * @return
     */
    CourseOrder searchCoInfoByCno(@Param("courseOrderNo") String courseOrderNo);

    /**
     * 根据订单编号查询订单金额
     *
     * @param courseOrderNo
     * @return
     */
    CourseOrder searchCoMoneyByCno(@Param("courseOrderNo") String courseOrderNo);

    /**
     * 根据用户ID和课程ID查询用户是否已购买该课程
     *
     * @param uId
     * @param cId
     * @return
     */
    List<String> searchOrderByUIdAndCId(@Param("uId") String uId,@Param("cId") String cId);

}