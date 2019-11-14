package cn.com.myproject.external.pay;

import cn.com.myproject.pay.PayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import cn.com.myproject.user.entity.PO.Article;
import cn.com.myproject.util.SpringUtil;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@RestController
@RequestMapping("/pay")
public class PayController  {


    private PayService payService;

    @PostMapping("/{type}/goodsorder")
    public String payorder(@PathVariable("type") String type,@RequestBody PayOrder order) {
        payService = (PayService) SpringUtil.getBean(type+"PayService");
       /* PayOrder order = new PayOrder();
        order.setBody("订单-B0000-0003");
        order.setSubject("订单-B0000-0003");
        order.setOut_trade_no("B0000-0003");
        order.setTotal_amount("0.01");
        String str = "{\"type\":\"1\"}";*/
        try {
          /*  order.setPassback_params(URLEncoder.encode(str,"utf-8"));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<>();
        map.put("order",payService.payOrder(order));
        return  new Gson().toJson(map);
    }
}
