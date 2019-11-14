package cn.com.myproject.logi.service.impl;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import cn.com.myproject.logi.entity.VO.OrderDeliverVO;
import cn.com.myproject.logi.mapper.DeliverMessageMapper;
import cn.com.myproject.logi.service.IDeliverMessageService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jyp on 2017/9/18 0018.
 */
@Service
public class DeliverMessageService implements IDeliverMessageService{

    private static  final Logger logger = LoggerFactory.getLogger(DeliverMessageService.class);
    @Autowired
    private DeliverMessageMapper deliverMessageMapper;
    @Override
    public DeliverMessage selectByDeliNo(String deliNo) {
        return deliverMessageMapper.selectByDeliNo(deliNo);
    }

    @Override
    public int insertDeli(DeliverMessage deliverMessage) {
        return deliverMessageMapper.insertDeli(deliverMessage);
    }

    @Override
    public DeliverMessageVO selectLogiMessage(String orderNo) {
        return deliverMessageMapper.selectLogiMessage(orderNo);
    }

    @Override
    public PageInfo<OrderDeliverVO> selectOrderDeliverMessage(int pageNum, int pageSize) {
        List<OrderDeliverVO> list = deliverMessageMapper.selectOrderDeliverMessage(pageNum,pageSize);
        return new PageInfo(list);
    }

    @Override
    public boolean updateDeliNo(String deliNo, String deliId) {
        boolean flatVal = false;
        try{
            int countVal = deliverMessageMapper.updateDeliNo(deliNo,deliId);
            if(countVal > 0){
                flatVal = true;
            }else{
                logger.error("更新失败!");
            }
        }catch (Exception e){
            logger.error("更新失败!"+e);
            e.printStackTrace();
        }
        return flatVal;
    }
}
