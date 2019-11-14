package cn.com.myproject.external.paynew;

import cn.com.myproject.paynew.ali.AliPayService;
import cn.com.myproject.paynew.ali.AliPayWapService;
import cn.com.myproject.paynew.entity.VO.PayOrder;
import cn.com.myproject.paynew.wx.WXAppPayService;
import cn.com.myproject.paynew.wx.WXJSPayService;
import cn.com.myproject.paynew.wx.WXWapPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@RestController
@Component("PayNewController")
@RequestMapping("/paynew")
public class PayController {


    @Autowired
    private AliPayService aliPayService;//支付宝app

    @Autowired
    private AliPayWapService aliPayWapService;  //支付宝H5

    @Autowired
    private WXAppPayService wxAppPayService; //微信app

    @Autowired
    private WXJSPayService wxJSPayService; //微信公众号

    @Autowired
    private WXWapPayService wxWapPayService; //微信H5

    /**
     * 支付宝app支付
     * @param order
     * @return
     */
    @PostMapping("/app/aliPayOrder")
    public Map<String, String>  aliPayOrder(@RequestBody PayOrder order){
        Map<String, String>  result = null;
        if(null != order){
            result = aliPayService.payOrder(order);
        }
        return result;
    }

    /**
     * 支付宝H5支付
     * @param order
     * @return
     */
    @PostMapping("/wap/aliWapPayOrder")
    public Map<String, String>  aliWapPayOrder(@RequestBody PayOrder order){
        Map<String, String>  result = null;
        if(null != order){
            result = aliPayWapService.payOrder(order);
        }
        return result;
    }

    /**
     * 微信app支付
     * @param order
     * @return
     */
    @PostMapping("/app/wxPayOrder")
    public Map<String,String> wxPayOrder(@RequestBody PayOrder order){
        Map<String, String> result = wxAppPayService.payOrder(order);
        return result;
    }



    /**
     * 微信App支付回调验证
     * @param result
     * @return
     */
    @PostMapping("/app/wxAppCheck")
    public boolean wxAppCheck(@RequestBody  Map<String, String> result ){
        boolean flag = wxAppPayService.check(result);
        return flag;
    }

    /**
     * 微信H5支付
     * @param order
     * @return
     */
    @PostMapping("/wap/wxWapPayOrder")
    public Map<String,String> wxWapPayOrder(@RequestBody PayOrder order){
        Map<String, String> result = wxWapPayService.payOrder(order);
        return result;
    }

    /**
     * 微信公众号支付
     * @param order
     * @return
     */
    @PostMapping("/jsp/wxJspPayOrder")
    public Map<String,String> wxJspPayOrder(@RequestBody PayOrder order){
        Map<String, String> result = wxJSPayService.payOrder(order);
        return result;
    }

    /**
     * 微信公众号支付回调验证
     * @param result
     * @return
     */
    @PostMapping("/jsp/wxJspCheck")
    public boolean wxJspCheck(@RequestBody  Map<String, String> result ){
       boolean  flag = wxJSPayService.check(result);
       return flag;
    }
}
