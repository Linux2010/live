package cn.com.myproject.external.pay;

import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.impl.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @auther CQC
 * @create 2017.9.18
 */
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    @PostMapping("/alipayorder")
    public String payOrder(@RequestBody PayOrder order){
        String result = "";
        if(null != order){
            result = aliPayService.payOrder(order);
        }
        return result;
    }
}
