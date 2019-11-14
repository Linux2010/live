package cn.com.myproject.api.pay.controller;

import cn.com.myproject.api.config.MyWXPayConfig;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.admincon.IProfitShareDealService;
import cn.com.myproject.api.service.pay.IWxpayService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther LeiJia
 * @create 2017.9.21
 */
@RestController
@RequestMapping(value = "/api/wxpay")
@Api(value="微信app支付通知",tags = "微信app支付通知")
public class WxAppPayController {

    private static final Logger logger = LoggerFactory.getLogger(WxAppPayController.class);

    @Autowired
    private IWxpayService wxpayService;


    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    @Autowired
    private IProfitShareDealService profitShareDealService;
    @RequestMapping("/notify")
    @ResponseBody
    public String paynotify(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br  = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
            String line = null;
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "FAIL";
        }

        MyWXPayConfig config = new MyWXPayConfig();
        config.setKey("19db41eb64ac48a68e44ffb3e9959cb3");
        config.setAppID("wx5a3468977fdf22c8");
        config.setMchID("1488299762");
        WXPay wxpay = new WXPay(config);

        Map<String, String> notifyMap = null;  // 转换成map
        try {
            notifyMap = WXPayUtil.xmlToMap(sb.toString());
            if (wxpay.isPayResultNotifySignatureValid(notifyMap) && notifyMap.get("result_code").equals("SUCCESS") ) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功

                String outTradeNo = notifyMap.get("out_trade_no");

                String transaction_id = notifyMap.get("transaction_id"); //第三方交易订单号

                logger.info("旧的微信app支付回调通知" + Calendar.getInstance().getTime().toString()+"，订单号："+outTradeNo);
                logger.info("旧的微信app支付回调通知" +Calendar.getInstance().getTime().toString()+"，交易订单号："+transaction_id);
                if(StringUtils.isNotBlank(outTradeNo)){
                    String firstPlace = outTradeNo.substring(0,1);
                    int reInt=0;
                    if("a".equals(firstPlace)){ //课程订单结算订单编号

                        reInt = courseService.modifyPayStatus(outTradeNo,transaction_id);

                    }else if("b".equals(firstPlace)){ //打赏教头结算订单编号

                        reInt = userService.zfbReturnSuccessUpdateRewardOrderStatus(outTradeNo,transaction_id);

                    }else if("c".equals(firstPlace)){//购买VIP会员订单编号

                        reInt = rechargeMemberService.setPayFinishRechargeMemberByOrderOn(outTradeNo,transaction_id);

                    }else if("d".equals(firstPlace)){ //充值银两

                        reInt = rwService.setPayFinishRecharge(outTradeNo.substring(1),transaction_id);

                    }

                    if(0 == reInt){
                        return "fail";
                    }
                }

                return "SUCCESS";
            }else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                logger.info("支付失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }


        return "FAIL";
    }


}
