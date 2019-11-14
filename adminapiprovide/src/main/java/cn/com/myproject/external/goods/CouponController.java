package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.goods.service.ICouponService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by LeiJia on 2017-09-26
 * desc：优惠劵服务控制器类
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @PostMapping("/addCoupon")
    public int addCoupon(@RequestBody Coupon coupon){
        if(StringUtils.isNotBlank(coupon.getCouponName())){
            String couponId = UUID.randomUUID().toString().replace("-", "");
            coupon.setCouponId(couponId);// 设置教师分类的ID
            coupon.setCreateTime(new Date().getTime());// 默认当前时间
            coupon.setVersion(1);// 默认第一版本
            coupon.setStatus((short)1);
        }
        return couponService.addCoupon(coupon);
    }

    @PostMapping("/removeCoupon")
    public int removeCoupon(String couponId){
        return couponService.removeCoupon(couponId);
    }

    @PostMapping("/addCouponGoods")
    public int addCouponGoods(String ids,String couponId){
        return couponService.addCouponGoods(ids,couponId);
    }

    @PostMapping("/removeCouponGoods")
    public int removeCouponGoods(String ids,String couponId){
        return couponService.removeCouponGoods(ids,couponId);
    }


    @PostMapping("/addUserCoupon")
    public int addUserCoupon(String ids,String couponId){
        return couponService.addUserCoupon(ids,couponId);
    }

    @PostMapping("/cancelUserCoupon")
    public int cancelUserCoupon(String ids,String couponId){
        return couponService.cancelUserCoupon(ids,couponId);
    }


    @PostMapping("/modifyCoupon")
    public int modifyCoupon(@RequestBody Coupon coupon){
        return couponService.modifyCoupon(coupon);
    }

    @GetMapping("/searchCouponList")
    public PageInfo<Coupon> searchCouponList(int pageNum,int pageSize,String couponName){
        return couponService.searchCouponList(pageNum,pageSize,couponName);
    }

    @GetMapping("/searchCouponById")
    public Coupon searchCouponById(String couponId){
        return couponService.searchCouponById(couponId);
    }


    @GetMapping("/searchCouponGoodsList")
    public List<Goods> searchCouponGoodsList(@RequestParam("couponId") String couponId){
        return couponService.searchCouponGoodsList(couponId);
    }

    @PostMapping("/searchUserCoupons")
    public  List<Coupon> searchUserCoupons(@RequestParam("pageNum") int pageNum ,@RequestParam("pageSize") int pageSize ,@RequestParam("userId") String userId){
        return couponService.searchUserCoupons(pageNum,pageSize,userId);

    }

    @PostMapping("/searchCouponGoods")
    public List<Goods> searchCouponGoods(@RequestParam("pageNum") int pageNum ,@RequestParam("pageSize")  int pageSize , @RequestParam("couponId")  String couponId){
        return couponService.searchCouponGoods(pageNum,pageSize,couponId);
    }
}