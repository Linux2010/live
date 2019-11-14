package cn.com.myproject.shop.service;

import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderDetailVO;
import cn.com.myproject.shop.entity.VO.OrderMoneyVO;
import cn.com.myproject.shop.entity.VO.OrderVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单Service接口
 */
public interface IOrderService {

    /**
     * 查询用户金额订单详情
     *
     * @param userId
     * @return
     */
    List<OrderMoneyVO> searchOMList(String userId);

    /**
     * 分页查询订单列表数据
     *
     * @param orderVO
     * @return
     */
    PageInfo<OrderVO> searchOrderList(OrderVO orderVO);

    /**
     * 更新订单状态
     *
     * @param order
     * @return
     */
    boolean modifyOrderStatus(Order order);

    /**
     * 更新发货状态
     *
     * @param order
     * @return
     */
    boolean modifyFhStatus(Order order);

    /**
     * 根据订单ID和订单类型查询订单详情
     *
     * @param orderId
     * @param orderType
     * @return
     */
    List<OrderDetailVO> searchODList(String orderId,int orderType);

    /**
     * 根据订单ID查询订单详情
     *
     * @param orderId
     * @return
     */
    Order searchOrderById(String orderId);

    /**
     * 生成订单
     *
     * @param order
     * @return
     */
    boolean addOrder(Order order);

    /**
     * 查询快递公司列表
     *
     * @return
     */
    List<LogiCompany> searchLogiList();

    /**
     * 查询可用优惠券
     *
     * @param userId
     * @param orderMoney
     * @return
     */
    List<Coupon> searchUClist(String userId,double orderMoney);

    /**
     * 查询不可用优惠券
     *
     * @param userId
     * @return
     */
    List<Coupon> searchNUCList(String userId);

    /**
     * 根据优惠券ID查询优惠券金额
     *
     * @param couponId
     * @return
     */
    String searchCmById(String couponId);

    /**
     * 根据商品ID和skuId查询商品信息
     *
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    GoodsSpec searchGoodsSpecInfo(String goodsId,String goodsSpecId);

    /**
     * 根据用户ID查询用户级别(1是普通用户，2是会员用户)
     *
     * @param userId
     * @return
     */
    String searchUserLevel(String userId);

    /**
     * 根据订单编号更新支付状态和实付款
     *
     * @param sfMoney
     * @param orderNo
     * @return
     */
    boolean modifyPsAndSfByOrderNo(double sfMoney,String orderNo);

}