package cn.com.myproject.service;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderDetailVO;
import cn.com.myproject.shop.entity.VO.OrderVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IOrderService {

    @PostMapping("/order/searchOrderList")
    PageInfo<OrderVO> searchOrderList(@RequestBody OrderVO orderVO);

    @PostMapping("/order/modifyOrderStatus")
    boolean modifyOrderStatus(@RequestBody Order order);

    @PostMapping("/order/modifyFhStatus")
    boolean modifyFhStatus(@RequestBody Order order);

    @GetMapping("/order/searchODList")
    List<OrderDetailVO> searchODList(@RequestParam("orderId") String orderId,
                                     @RequestParam("orderType") int orderType);

    @GetMapping("/order/searchOrderById")
    Order searchOrderById(@RequestParam("orderId") String orderId);

    @GetMapping("/order/searchLogiList")
    List<LogiCompany> searchLogiList();

}