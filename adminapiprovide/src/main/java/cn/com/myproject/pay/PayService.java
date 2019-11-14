package cn.com.myproject.pay;

import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
public interface PayService {

    String payOrder(PayOrder order);

    Map<String, String> appPayOrder(PayOrder order);

    String refundOrder(PayRefund refund);

    String transfer(PayTransfer transfer);

}
