package cn.com.myproject.external;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import cn.com.myproject.logi.entity.VO.OrderDeliverVO;
import cn.com.myproject.logi.service.IDeliverMessageService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jyp on 2017/9/26 0026.
 */
@RestController
@RequestMapping("/deliverMessage")
public class DeliverMessageController {

    private static  final Logger logger = LoggerFactory.getLogger(DeliverMessageController.class);

    @Autowired
    private IDeliverMessageService deliverMessageService;

    @GetMapping("/getMessage")
    public DeliverMessageVO getMessage(String orderNo){
        return deliverMessageService.selectLogiMessage(orderNo);
    }

    @GetMapping("/selectOrderDeliverMessage")
    public PageInfo<OrderDeliverVO> selectOrderDeliverMessage(int pageNum, int pageSize){
        return deliverMessageService.selectOrderDeliverMessage(pageNum,pageSize);
    }

    @GetMapping("/updateDeliNo")
    public boolean updateDeliNo(String deliNo, String deliId){
        return deliverMessageService.updateDeliNo(deliNo,deliId);
    }
    @GetMapping("/selectByDeliNo")
    public DeliverMessage selectByDeliNo(String deliNo){
        return deliverMessageService.selectByDeliNo(deliNo);
    }
}
