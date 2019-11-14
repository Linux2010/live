package cn.com.myproject.api.service;

import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.live.entity.VO.JsVo;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderMoneyVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-19
 * desc：订单Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IOrderService {

    @GetMapping("/order/searchOMList")
    List<OrderMoneyVO> searchOMList(@RequestParam("userId") String userId);

    @PostMapping("/order/addOrder")
    JsVo addOrder(@RequestBody Order order);

    @GetMapping("/order/searchCouponList")
    List<Coupon> searchCouponList(@RequestParam("userId") String userId,@RequestParam("useFlag") int useFlag);

    @GetMapping("/order/searchCmById")
    String searchCmById(@RequestParam("couponId") String couponId);

    @GetMapping("/order/searchGoodsSpecInfo")
    GoodsSpec searchGoodsSpecInfo(@RequestParam("goodsId") String goodsId, @RequestParam("goodsSpecId") String goodsSpecId);

    @GetMapping("/order/searchUserLevel")
    String searchUserLevel(@RequestParam("userId") String userId);

}