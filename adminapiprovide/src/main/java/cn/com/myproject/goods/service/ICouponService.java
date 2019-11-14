package cn.com.myproject.goods.service;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-26
 * desc：优惠劵Service接口
 */
public interface ICouponService {

    public int addCoupon(Coupon coupon);

    public int removeCoupon(String couponId);

    public int addCouponGoods(String ids,String couponId);

    public int removeCouponGoods(String ids,String couponId);

    public int addUserCoupon(String ids,String couponId);

    public int cancelUserCoupon(String ids,String couponId);

    public int modifyCoupon(Coupon coupon);

    public PageInfo<Coupon> searchCouponList(int pageNum,
                                         int pageSize,
                                         String couponName);

    public Coupon searchCouponById(String couponId);

    public  List<Goods> searchCouponGoodsList(String couponId);

    public  List<Coupon> searchUserCoupons(int pageNum ,int pageSize ,String userId);

    public List<Goods> searchCouponGoods(int pageNum , int pageSize ,String couponId);
}