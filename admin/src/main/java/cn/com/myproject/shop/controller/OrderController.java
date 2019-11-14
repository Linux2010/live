package cn.com.myproject.shop.controller;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.service.IOrderService;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderDetailVO;
import cn.com.myproject.shop.entity.VO.OrderVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单控制器类
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 进入订单列表页面
     *
     * @return
     */
    @RequestMapping("/orderIndex")
    public String orderIndex(){
        return "/shop/order_index";
    }

    /**
     * 进入订单详情页面
     *
     * @return
     */
    @RequestMapping("/orderDetail")
    public ModelAndView orderDetail(String orderId,int orderType){
        ModelAndView view = new ModelAndView("/shop/order_detail");
        view.addObject("orderId",orderId);
        view.addObject("orderType",orderType);
        return view;
    }

    /**
     * 进入订单发货页面
     *
     * @return
     */
    @RequestMapping("/orderFh")
    public ModelAndView orderFh(String orderId){
        ModelAndView view = new ModelAndView("/shop/order_fh");
        view.addObject("orderId",orderId);
        return view;
    }

    /**
     * 展示快递公司列表
     *
     * @return
     */
    @RequestMapping("/searchLogiList")
    @ResponseBody
    public List<LogiCompany> searchLogiList(){
        return orderService.searchLogiList();
    }

    /**
     * 展示订单列表数据
     *
     * @param page
     * @param rows
     * @param orderVO
     * @return
     */
    @RequestMapping("/searchOrderList")
    @ResponseBody
    public PageInfo<OrderVO> searchOrderList(int page,int rows,OrderVO orderVO){
        if(orderVO != null){
            orderVO.setPageNum(page);
            orderVO.setPageSize(rows);
        }else{
            orderVO = new OrderVO();
            orderVO.setPageNum(page);
            orderVO.setPageSize(rows);
        }
        return orderService.searchOrderList(orderVO);
    }

    /**
     * 更新订单状态
     *
     * @param order
     * @return
     */
    @RequestMapping("/modifyOs")
    @ResponseBody
    public String modifyOs(Order order){
        String returnStr = "1";
        boolean flagVal = orderService.modifyOrderStatus(order);
        if(flagVal){
            returnStr = "0";
        }
        return returnStr;
    }

    /**
     * 更新订单发货状态
     *
     * @param order
     * @return
     */
    @RequestMapping("/modifyFHS")
    @ResponseBody
    public String modifyFHS(Order order){
        String returnStr = "1";
        boolean flagVal = orderService.modifyFhStatus(order);
        if(flagVal){
            returnStr = "0";
        }
        return returnStr;
    }

    /**
     * 根据订单ID查询订单商品详情
     *
     * @param orderId
     * @param orderType
     * @return
     */
    @RequestMapping("/searchODList")
    @ResponseBody
    public List<OrderDetailVO> searchODList(String orderId,int orderType){
        return orderService.searchODList(orderId,orderType);
    }

    /**
     * 根据订单ID查询订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/searchOrderById")
    @ResponseBody
    public Order searchOrderById(String orderId){
        return orderService.searchOrderById(orderId);
    }

}