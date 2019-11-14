package cn.com.myproject.logi.mapper;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import cn.com.myproject.logi.entity.VO.OrderDeliverVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jyp on 2017/9/18 0018.
 */
@Mapper
public interface DeliverMessageMapper {

    public DeliverMessage selectByDeliNo(@Param("deliNo") String deliNo);

    public int insertDeli(DeliverMessage deliverMessage);

    /**
     * 根据订单号查询物流信息
     * @param orderNo
     * @return
     */
    public DeliverMessageVO selectLogiMessage(@Param("orderNo") String orderNo);

    /**
     * 修改订单号
     * @param deliNo
     * @param deliId
     * @return
     */
    public int updateDeliNo(@Param("deliNo") String deliNo,@Param("deliId") String deliId);

    /**
     * 订单的物流信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<OrderDeliverVO> selectOrderDeliverMessage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);
}
