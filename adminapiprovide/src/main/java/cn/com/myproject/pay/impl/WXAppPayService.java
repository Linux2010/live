package cn.com.myproject.pay.impl;
import cn.com.myproject.pay.MyWXPayConfig;
import cn.com.myproject.pay.PayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import cn.com.myproject.util.MD5Util;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by LeiJia on 2017/9/21.
 */
@Service
public class WXAppPayService implements PayService {
    private static final Logger logger = LoggerFactory.getLogger(WXAppPayService.class);

    @Value("${jtxyapp.url}")
    private String jtxyappUrl  ;
    @Override
    public String payOrder(PayOrder order) {
        return "error";
    }
    @Override
    public Map<String,String> appPayOrder(PayOrder order) {
        Map<String, String> resultMap=new LinkedHashMap<>();
        MyWXPayConfig config = new MyWXPayConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", order.getBody());
        data.put("out_trade_no", order.getOut_trade_no());
        data.put("fee_type", "CNY");
        data.put("total_fee",new BigDecimal(order.getTotal_amount()).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP).toString());
        data.put("spbill_create_ip", order.getSpbill_create_ip());
        //回调地址
        data.put("notify_url", jtxyappUrl+"/api/wxpay/notify");
       //data.put("notify_url", "http://192.168.1.162.tunnel.qydev.com/api/wxpay/notify");
        data.put("trade_type", "APP");

        for(String str:data.keySet()) {
            logger.info(str+",,"+data.get(str));
        }
        try {
            config.setKey("19db41eb64ac48a68e44ffb3e9959cb3");  //API秘钥
            wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS") && resp.containsKey("result_code")&& resp.get("result_code").equals("SUCCESS")) {
                SortedMap<String, String> finalpackage = new TreeMap<String, String>();
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String nonceStr2 = resp.get("nonce_str");
                resultMap.put("appid",resp.get("appid"));
                resultMap.put("partnerid",resp.get("mch_id"));
                resultMap.put("prepayid",resp.get("prepay_id"));
                resultMap.put("package","Sign=WXPay");
                resultMap.put("noncestr",nonceStr2);
                resultMap.put("timestamp",timestamp);
                String signs = "appid=" + config.getAppID() + "&noncestr=" + nonceStr2 + "&package=Sign=WXPay&partnerid="
                        + resp.get("mch_id").toString() + "&prepayid=" + resp.get("prepay_id").toString() + "&timestamp=" + timestamp + "&key="
                        + config.getKey();
                resultMap.put("sign", MD5Util.MD5Encode(signs, "utf8").toUpperCase());
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                    resultMap.put(str,resp.get("str"));
                }
                resultMap.put("return_msg",resp.get("return_msg"));
                resultMap.put("return_code",resp.get("return_code"));
                resultMap.put("nonce_str",resp.get("nonce_str"));
                resultMap.put("appid",resp.get("appid"));
                resultMap.put("sign",resp.get("sign"));
                resultMap.put("err_code",resp.get("err_code"));
                resultMap.put("return_msg",resp.get("return_msg"));
                resultMap.put("result_code",resp.get("result_code"));
                resultMap.put("err_code_des",resp.get("err_code_des"));
                resultMap.put("mch_id",resp.get("mch_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用微信APP支付接口失败",e);
        }

        return resultMap;
    }
    @Override
    public String refundOrder(PayRefund refund) {
        return "error";
    }

    @Override
    public String transfer(PayTransfer transfer){
        return "error";
    }
}
