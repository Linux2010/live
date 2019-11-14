package cn.com.myproject.service.goods;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-14
 * desc：优惠劵Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICouponService {

    @PostMapping("/coupon/addCoupon")
    public int addCoupon(@RequestBody Coupon coupon);

    @PostMapping("/coupon/removeCoupon")
    public int removeCoupon(@RequestParam("couponId") String couponId);


    @PostMapping("/coupon/addCouponGoods")
    public int addCouponGoods(@RequestParam("ids") String ids,@RequestParam("couponId") String couponId);

    @PostMapping("/coupon/removeCouponGoods")
    public int removeCouponGoods(@RequestParam("ids") String ids,@RequestParam("couponId") String couponId);


    @PostMapping("/coupon/addUserCoupon")
    public int addUserCoupon(@RequestParam("ids") String ids,@RequestParam("couponId") String couponId);

    @PostMapping("/coupon/cancelUserCoupon")
    public int cancelUserCoupon(@RequestParam("ids") String ids,@RequestParam("couponId") String couponId);


    @PostMapping("/coupon/modifyCoupon")
    public int modifyCoupon(@RequestBody Coupon coupon);

    @GetMapping("/coupon/searchCouponList")
    public PageInfo<Coupon> searchCouponList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam("couponName") String couponName);


    @GetMapping("/coupon/getCouponList")
    public List<Coupon> getCouponList();

    @GetMapping("/coupon/searchCouponById")
    public Coupon searchCouponById(@RequestParam("couponId") String couponId);

    @GetMapping("/coupon/searchCouponGoodsList")
    public  List<Goods> searchCouponGoodsList(@RequestParam("couponId") String couponId);
}