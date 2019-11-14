package cn.com.myproject.paynew.wx;

import cn.com.myproject.paynew.PayService;
import cn.com.myproject.paynew.entity.VO.PayOrder;
import cn.com.myproject.paynew.entity.VO.PayRefund;
import cn.com.myproject.paynew.entity.VO.PayTransfer;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  微信H5支付
 */
@Service("wXWapPayServiceNew")
public class WXWapPayService implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(WXWapPayService.class);
    @Autowired
    private WXJspPayProp wXJspPayProp;

    @Autowired
    private WXAppPayProp wXAppPayProp;

    @Override
    public Map<String,String> payOrder(PayOrder order) {
        Map<String, String> resultMap=new LinkedHashMap<>();
       // WXJspPayConfig config = new WXJspPayConfig();
        WXAppPayConfig config = new WXAppPayConfig();
        WXPay wxpay = null;
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", order.getBody());
        data.put("out_trade_no", order.getOut_trade_no());
        data.put("fee_type", "CNY");
        data.put("total_fee",new BigDecimal(order.getTotal_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        data.put("spbill_create_ip", order.getSpbill_create_ip());
        //回调地址
        data.put("notify_url", wXAppPayProp.getWapNotifyUrl());
        data.put("trade_type", "MWEB");
        data.put("scene_info","{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://jtxyapp.doers.cn\",\"wap_name\": \"教头学院\"}}");

        for(String str:data.keySet()) {
            logger.info(str+",,"+data.get(str));
        }
        try {

            config.setKey(wXAppPayProp.getBusinessSecretKey());
            wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS")) {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                    resultMap.put(str,resp.get(str));
                }
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                    resultMap.put(str,resp.get(str));
                }
                resultMap.put("return_msg",resp.get("return_msg"));
                resultMap.put("return_code",resp.get("return_code"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用微信H5支付接口失败",e);
        }

        return resultMap;
    }

    @Override
    public String refundOrder(PayRefund refund) {
        WXJspPayConfig config = new WXJspPayConfig();
        WXPay wxpay = null;


        Map<String, String> data = new HashMap<String, String>();
        data.put("transaction_id",refund.getTrade_no());
        data.put("out_refund_no",refund.getOut_request_no());
        data.put("total_fee",new BigDecimal(refund.getTotal_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        data.put("refund_fee",new BigDecimal(refund.getRefund_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        try {
            wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
            Map<String, String> resp = wxpay.refund(data);

            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS") && resp.get("result_code").equals("SUCCESS")) {
                String refunId = "未知";
                if(resp.containsKey("refund_id_0")) {
                    refunId = resp.get("refund_id_0");
                }
                return "success,"+refunId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @Override
    public String transfer(PayTransfer transfer){
        return "error";
    }

    @Override
    public boolean check(Map<String, String> map) {
        WXJspPayConfig config = new WXJspPayConfig();
        WXPay wxpay = new WXPay(config);
        try {
            if (wxpay.isPayResultNotifySignatureValid(map)  && map.get("result_code").equals("SUCCESS") ) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
