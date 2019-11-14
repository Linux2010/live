package cn.com.myproject.goods.mapper;

import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CouponMapper {

    /**
     * 插入优惠劵
     * @param coupon
     * @return
     */
    public int insertCoupon(Coupon coupon);

    /**
     * 根据ID删除优惠劵
     * @param couponId
     * @return
     */
    public int deleteCoupon(@Param("couponId") String couponId);

    /**
     * 修改优惠劵
     * @param coupon
     * @return
     */
    public int updateCoupon(Coupon coupon);

    /**
     * 分页查询优惠劵列表
     * @param pageNum
     * @param pageSize
     * @param couponName
     * @return
     */
    public List<Coupon> searchCouponList(@Param("pageNumKey") int pageNum,
                                     @Param("pageSizeKey") int pageSize,
                                     @Param("couponName") String couponName);
    /**
     * 查询优惠劵列表
     * @return
     */
    public List<Coupon> getCouponList();

    /**
     * 根据ID查询规格信息
     * @param couponId
     * @return
     */
    public Coupon searchCouponById(@Param("couponId") String couponId);


    /**
     * 根据ID查询规格信息
     * @param couponId
     * @return
     */
    public List<Goods> searchCouponGoodsList(@Param("couponId") String couponId);

    /**
     * 查询可用优惠券
     *
     * @param userId
     * @param orderMoney
     * @return
     */
    List<Coupon> searchUClist(@Param("userId") String userId,@Param("orderMoney") double orderMoney);

    /**
     * 查询不可用优惠券
     *
     * @param userId
     * @return
     */
    List<Coupon> searchNUCList(@Param("userId") String userId);

    /**
     * 根据优惠券ID查询优惠券金额
     *
     * @param couponId
     * @return
     */
    String searchCmById(@Param("couponId") String couponId);


    /**
     * 查询用户的优惠劵列表
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    List<Coupon> searchUserCoupons(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("userId") String userId);


    /**
     * 分页查询优化劵下的优惠商品
     * @param pageNum
     * @param pageSize
     * @param couponId
     * @return
     */
    public List<Goods> searchCouponGoods( @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize ,@Param("couponId") String couponId);


}