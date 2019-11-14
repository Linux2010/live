package cn.com.myproject.alipay;

import cn.com.myproject.AdminApiProvideApplication;
import cn.com.myproject.pay.PayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdminApiProvideApplication.class)
@WebAppConfiguration
public class AliPayServiceTest {

    @Autowired
    private PayService aliPayService;

    @Test
    public void testpayOrder() {
        PayOrder order = new PayOrder();
        order.setBody("订单-B0000-0001");
        order.setSubject("订单-B0000-0001");
        order.setOut_trade_no("B0000-0001");
        order.setTotal_amount("0.01");
        String str = "{\"type\":\"1\"}";
        try {
            order.setPassback_params(URLEncoder.encode(str,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        aliPayService.payOrder(order);
    }

    @Test
    public void testRefu() {
        PayRefund refund = new PayRefund();
        refund.setOut_request_no("B1000-0002");
        refund.setOut_trade_no("B0000-0002");
        refund.setRefund_amount("0.01");
        refund.setRefund_reason("退款");
       // refund.setTrade_no("2017081721001004980255438098");
        aliPayService.refundOrder(refund);
    }

    @Test
    public void testtransfer() {
        PayTransfer transfer = new PayTransfer();
        transfer.setAmount("0.1");
        transfer.setOut_biz_no("T0000-0001");
        transfer.setPayee_account("18600890788");
        transfer.setPayee_real_name("李阳");
        transfer.setPayer_show_name("河南东亨");
        transfer.setRemark("提现");
        aliPayService.transfer(transfer);


    }
}
