package cn.com.myproject.logi.service;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import cn.com.myproject.logi.entity.VO.OrderDeliverVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jyp on 2017/9/18 0018.
 */
public interface IDeliverMessageService {

    public DeliverMessage selectByDeliNo(String deliNo);

    public int insertDeli(DeliverMessage deliverMessage);

    public DeliverMessageVO selectLogiMessage(String orderNo);

    public PageInfo<OrderDeliverVO> selectOrderDeliverMessage(int pageNum, int pageSize);

    public boolean updateDeliNo(String deliNo, String deliId);
}
