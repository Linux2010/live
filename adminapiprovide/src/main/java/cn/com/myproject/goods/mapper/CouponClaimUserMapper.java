package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.CouponClaimUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponClaimUserMapper {
    /**
     * 插入用户认领优惠劵记录
     * @param couponClaimUser
     * @return
     */
    public int insertCouponClaimUser(CouponClaimUser couponClaimUser);

    /**
     * 根据ID删除用户认领优惠劵记录
     * @param couponClaimUserId
     * @return
     */
    public int deleteCouponClaimUser(@Param("couponClaimUserId") String couponClaimUserId);

    /**
     * 修改用户认领优惠劵记录
     * @param couponClaimUser
     * @return
     */
    public int updateCouponClaimUser(CouponClaimUser couponClaimUser);

    /**
     * 分页查询用户认领优惠劵记录列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<CouponClaimUser> searchCouponClaimUserList(@Param("pageNumKey") int pageNum,
                                         @Param("pageSizeKey") int pageSize);
    /**
     * 查询用户认领优惠劵记录列表
     * @return
     */
    public List<CouponClaimUser> getCouponClaimUserList(@Param("userId") String userId);

    /**
     * 根据ID查询用户认领优惠劵记录信息
     * @param userClaimCouponId
     * @return
     */
    public CouponClaimUser searchCouponClaimUserById(@Param("userClaimCouponId") String userClaimCouponId);


    /**
     * 查询用户认领优惠劵记录详情
     * @param couponClaimUser
     * @return
     */
    public CouponClaimUser searchCouponClaimUserDetail(CouponClaimUser couponClaimUser );
}
