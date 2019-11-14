package cn.com.myproject.shop.service.Impl;

import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.goods.mapper.CouponMapper;
import cn.com.myproject.goods.mapper.GoodsSpecMapper;
import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.logi.mapper.DeliverMessageMapper;
import cn.com.myproject.logi.mapper.LogiCompanyMapper;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderDetailVO;
import cn.com.myproject.shop.entity.VO.OrderMoneyVO;
import cn.com.myproject.shop.entity.VO.OrderVO;
import cn.com.myproject.shop.mapper.OrderDetailMapper;
import cn.com.myproject.shop.mapper.OrderMapper;
import cn.com.myproject.shop.service.IOrderService;
import cn.com.myproject.user.mapper.UserMapper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单Service接口实现类
 */
@Service
public class OrderService implements IOrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private LogiCompanyMapper logiCompanyMapper;

    @Autowired
    private DeliverMessageMapper deliverMessageMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 生成订单
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public boolean addOrder(Order order){
        boolean flagVal = false;
        try {
            int countVal = orderMapper.insertOrder(order);// 插入订单数据
            if(countVal > 0){
                int num = 0;
                if(order.getOrderDetailList() != null && order.getOrderDetailList().size() > 0){
                    for(int i = 0;i < order.getOrderDetailList().size();i++){
                        order.getOrderDetailList().get(i).setOrderId(order.getOrderId());
                        order.getOrderDetailList().get(i).setOrderDetailId(UUID.randomUUID().toString().replace("-", ""));
                        order.getOrderDetailList().get(i).setCreateTime(new Date().getTime());
                        countVal = orderDetailMapper.insertOrderDetail(order.getOrderDetailList().get(i));// 插入订单详情数据
                        if(countVal == 0){
                            num++;
                        }
                    }
                }
                if(num == 0){
                    flagVal = true;
                }else{
                    logger.error("插入订单详情数据失败");
                }
            }else{
                logger.error("插入订单数据失败");
            }
        } catch (Exception e) {
            logger.error("生成订单失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 查询用户金额订单详情
     *
     * @param userId
     * @return
     */
    @Override
    public List<OrderMoneyVO> searchOMList(String userId){
        return orderMapper.searchOMList(userId);
    }

    /**
     * 根据订单ID查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public Order searchOrderById(String orderId){
        return orderMapper.searchOrderById(orderId);
    }

    /**
     * 根据订单ID和订单类型查询订单详情
     *
     * @param orderId
     * @param orderType
     * @return
     */
    @Override
    public List<OrderDetailVO> searchODList(String orderId, int orderType){
        List<OrderDetailVO> odList = orderDetailMapper.searchMGList(orderId);
        return odList;
    }

    /**
     * 更新订单状态
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public boolean modifyOrderStatus(Order order){
        boolean flagVal = false;
        try {
            int countVal = orderMapper.updateOrderStatus(order);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("更新订单状态失败");
            }
        } catch (Exception e) {
            logger.error("更新订单状态失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 更新发货状态
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public boolean modifyFhStatus(Order order){
        boolean flagVal = false;
        try {
            int countVal = orderMapper.updateFhStatus(order);
            if(countVal > 0){
                // 保存快递信息
                if(order.getKdNo() != null && order.getOrderNo() != null && order.getLogiId() != null && order.getAddressId() != null){//插入订单物流信息数据
                    DeliverMessage deliverMessage = new DeliverMessage();
                    deliverMessage.setDeliId(UUID.randomUUID().toString().replace("-",""));
                    deliverMessage.setDeliNo(order.getKdNo());//运输单号
                    deliverMessage.setAddressId(order.getAddressId());//收货地址Id
                    deliverMessage.setLogiId(order.getLogiId());//物流公司Id
                    deliverMessage.setOrderNo(order.getOrderNo());//订单号
                    deliverMessage.setCreateTime(new Date().getTime());//创建时间
                    int deliver_mess = deliverMessageMapper.insertDeli(deliverMessage);
                    if(deliver_mess > 0){
                        flagVal = true;
                    }else{
                        logger.error("插入物流信息数据失败!");
                    }

                }else{
                    logger.error("物流信息数据为空!");
                }
            }else{
                logger.error("更新发货状态失败");
            }
        } catch (Exception e) {
            logger.error("更新发货状态失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 分页查询订单列表数据
     *
     * @param orderVO
     * @return
     */
    @Override
    public PageInfo<OrderVO> searchOrderList(OrderVO orderVO){
        List<OrderVO> orderVOList = orderMapper.searchOrderList(orderVO);
        return convert(orderVOList);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<OrderVO> convert(List<OrderVO> list) {
        PageInfo<OrderVO> info = new PageInfo(list);
        List<OrderVO> _list = info.getList();
        info.setList(null);
        List<OrderVO> __list = new ArrayList<>(10);
        PageInfo<OrderVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null != _list && _list.size() != 0) {
            for(OrderVO o : _list) {
                __list.add(o);
            }
            _info.setList(__list);
        }
        return _info;
    }

    /**
     * 查询快递公司列表
     *
     * @return
     */
    @Override
    public List<LogiCompany> searchLogiList(){
        return logiCompanyMapper.searchLogiList();
    }

    /**
     * 查询可用优惠券
     *
     * @param userId
     * @param orderMoney
     * @return
     */
    @Override
    public List<Coupon> searchUClist(String userId, double orderMoney){
        return couponMapper.searchUClist(userId,orderMoney);
    }

    /**
     * 查询不可用优惠券
     *
     * @param userId
     * @return
     */
    @Override
    public List<Coupon> searchNUCList(String userId){
        return couponMapper.searchNUCList(userId);
    }

    /**
     * 根据优惠券ID查询优惠券金额
     *
     * @param couponId
     * @return
     */
    @Override
    public String searchCmById(String couponId){
        return couponMapper.searchCmById(couponId);
    }

    /**
     * 根据商品ID和skuId查询商品信息
     *
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    @Override
    public GoodsSpec searchGoodsSpecInfo(String goodsId, String goodsSpecId){
        return goodsSpecMapper.searchGoodsSpecInfo(goodsId,goodsSpecId);
    }

    /**
     * 根据用户ID查询用户级别(1是普通用户，2是会员用户)
     *
     * @param userId
     * @return
     */
    @Override
    public String searchUserLevel(String userId){
        return userMapper.searchUserLevel(userId);
    }

    /**
     * 根据订单编号更新支付状态和实付款
     *
     * @param sfMoney
     * @param orderNo
     * @return
     */
    @Override
    @Transactional
    public boolean modifyPsAndSfByOrderNo(double sfMoney,String orderNo){
        boolean flagVal = false;
        try {
            Order order = new Order();
            order.setOrderSf(sfMoney);
            order.setOrderNo(orderNo);
            int countVal = orderMapper.updatePsAndSfByOrderNo(order);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("更新订单支付状态失败");
            }
        } catch (Exception e) {
            logger.error("更新订单支付状态失败");
            e.printStackTrace();
        }
        return flagVal;
    }

}