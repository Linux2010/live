package cn.com.myproject.shop.mapper;

import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.VO.OrderMoneyVO;
import cn.com.myproject.shop.entity.VO.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-13
 * desc：订单Mapper接口
 */
@Mapper
public interface OrderMapper {

    /**
     * 查询用户金额订单详情
     *
     * @param userId
     * @return
     */
    List<OrderMoneyVO> searchOMList(@Param("userId") String userId);

    /**
     * 分页查询订单列表数据
     *
     * @param orderVO
     * @return
     */
    List<OrderVO> searchOrderList(OrderVO orderVO);

    /**
     * 修改订单状态
     *
     * @param order
     * @return
     */
    int updateOrderStatus(Order order);

    /**
     * 修改发货状态
     *
     * @param order
     * @return
     */
    int updateFhStatus(Order order);

    /**
     * 根据订单ID查询订单详情
     *
     * @param orderId
     * @return
     */
    Order searchOrderById(@Param("orderId") String orderId);

    /**
     * 插入订单数据
     *
     * @param order
     * @return
     */
    int insertOrder(Order order);

    /**
     * 根据订单编号修改支付状态和实付款
     *
     * @param order
     * @return
     */
    int updatePsAndSfByOrderNo(Order order);

}