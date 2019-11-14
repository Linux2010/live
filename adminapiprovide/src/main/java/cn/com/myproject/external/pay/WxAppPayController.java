package cn.com.myproject.external.pay;

import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.impl.AliPayService;
import cn.com.myproject.pay.impl.WXAppPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @auther LeiJia
 * @create 2017.9.18
 */
@RestController
@RequestMapping("/wxAppPay")
public class WxAppPayController {

    @Autowired
    private WXAppPayService wxAppPayService;

    @PostMapping("/appPayOrder")
    public Map<String,String> appPayOrder(@RequestBody PayOrder order){
        Map<String, String> result = wxAppPayService.appPayOrder(order);
        return result;
    }
}
