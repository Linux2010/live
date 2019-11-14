package cn.com.myproject.shop.mapper;

import cn.com.myproject.shop.entity.PO.OrderDetail;
import cn.com.myproject.shop.entity.VO.OrderDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-15
 * desc：订单详情Mapper接口
 */
@Mapper
public interface OrderDetailMapper {

    /**
     * 插入订单详情数据
     *
     * @param orderDetail
     * @return
     */
    int insertOrderDetail(OrderDetail orderDetail);

    /**
     * 根据订单ID查询金额商品订单详情
     *
     * @param orderId
     * @return
     */
    List<OrderDetailVO> searchMGList(@Param("orderId") String orderId);

}