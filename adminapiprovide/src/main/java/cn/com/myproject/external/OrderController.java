package cn.com.myproject.external;

import cn.com.myproject.enums.OrderEnum;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.live.entity.VO.JsVo;
import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderDetailVO;
import cn.com.myproject.shop.entity.VO.OrderMoneyVO;
import cn.com.myproject.shop.entity.VO.OrderVO;
import cn.com.myproject.shop.service.ICartService;
import cn.com.myproject.shop.service.IOrderService;
import cn.com.myproject.util.OrderUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单控制器类
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService iCartService;

    @PostMapping("/searchOrderList")
    public PageInfo<OrderVO> searchOrderList(@RequestBody OrderVO orderVO){
        return orderService.searchOrderList(orderVO);
    }

    @PostMapping("/modifyOrderStatus")
    public boolean modifyOrderStatus(@RequestBody Order order){
        return orderService.modifyOrderStatus(order);
    }

    @PostMapping("/modifyFhStatus")
    public boolean modifyFhStatus(@RequestBody Order order){
        return orderService.modifyFhStatus(order);
    }

    @GetMapping("/searchODList")
    public List<OrderDetailVO> searchODList(String orderId,int orderType){
        return orderService.searchODList(orderId,orderType);
    }

    @GetMapping("/searchOrderById")
    public Order searchOrderById(String orderId){
        return orderService.searchOrderById(orderId);
    }

    @GetMapping("/searchOMList")
    public List<OrderMoneyVO> searchOMList(String userId){
        return orderService.searchOMList(userId);
    }

    @PostMapping("/addOrder")
    public JsVo addOrder(@RequestBody Order order){
        String oId = UUID.randomUUID().toString().replace("-", "");
        order.setOrderId(oId);
        order.setOrderNo(OrderUtil.createOrderNo(OrderEnum.shoporder.getKey()));
        order.setPayStatus(1);// 默认待付款
        order.setOrderStatus(1);// 默认待发货
        order.setStatus((short)1);// 默认有效
        order.setCreateTime(new Date().getTime());// 订单创建时间默认当前时间
        boolean flagVal = orderService.addOrder(order);
        JsVo jv = new JsVo();
        jv.setCourseOrderNo(order.getOrderNo());
        if(flagVal){
            jv.setResultVal(1);
        }else{
            jv.setResultVal(0);
        }
        return jv;
    }

    @GetMapping("/searchLogiList")
    public List<LogiCompany> searchLogiList(){
        return orderService.searchLogiList();
    }

    @GetMapping("/searchCouponList")
    public List<Coupon> searchCouponList(String userId,int useFlag){
        List<Coupon> couponList = new ArrayList<Coupon>();
        String totalMoneyStr = iCartService.searchCgTmByUserId(userId);
        double totalMoney = 0;
        if(StringUtils.isNotBlank(totalMoneyStr)){
            totalMoney = Double.parseDouble(totalMoneyStr);
        }
        if(useFlag == 1){
            couponList.addAll(orderService.searchUClist(userId,totalMoney));
        }else if(useFlag == 2){
            couponList.addAll(orderService.searchNUCList(userId));
        }else{
            couponList.addAll(orderService.searchUClist(userId,totalMoney));
        }
        return couponList;
    }

    @GetMapping("/searchCmById")
    public String searchCmById(String couponId){
        return orderService.searchCmById(couponId);
    }

    @GetMapping("/searchGoodsSpecInfo")
    public GoodsSpec searchGoodsSpecInfo(String goodsId,String goodsSpecId){
        return orderService.searchGoodsSpecInfo(goodsId,goodsSpecId);
    }

    @GetMapping("/searchUserLevel")
    public String searchUserLevel(String userId){
        return orderService.searchUserLevel(userId);
    }

}