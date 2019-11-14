package cn.com.myproject.api.service;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-30
 * desc：优惠劵Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICouponService {

    @PostMapping("/coupon/searchUserCoupons")
    public  List<Coupon> searchUserCoupons(@RequestParam("pageNum") int pageNum ,@RequestParam("pageSize") int pageSize ,@RequestParam("userId") String userId);

    @PostMapping("/coupon/searchCouponGoods")
    public List<Goods> searchCouponGoods(@RequestParam("pageNum") int pageNum ,@RequestParam("pageSize")  int pageSize ,@RequestParam("couponId") String couponId);
}