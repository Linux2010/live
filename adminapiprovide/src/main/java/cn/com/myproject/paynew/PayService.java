package cn.com.myproject.paynew;




import cn.com.myproject.paynew.entity.VO.PayOrder;
import cn.com.myproject.paynew.entity.VO.PayRefund;
import cn.com.myproject.paynew.entity.VO.PayTransfer;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
public interface PayService {


    Map<String, String> payOrder(PayOrder order);

    String refundOrder(PayRefund refund);

    String transfer(PayTransfer transfer);

    boolean check(Map<String, String> map);

}
