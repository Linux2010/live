package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.paynew.IPayService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.IPUtil;
import cn.com.myproject.api.wap.service.IGetOrderService;
import cn.com.myproject.paynew.entity.VO.PayOrder;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝H5
 * Created by liyang-macbook on 2017/9/7.
 * update LeiJia 2017/10/30
 */
@Controller
@RequestMapping("/wap/ali")
public class WXAliPayController {

    private static final Logger logger = LoggerFactory.getLogger(WXAliPayController.class);


    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    @Autowired
    private IPayService payService;

    @Autowired
    private IGetOrderService getOrderService;

    /**
     * 支付宝H5统一下单
     * @param orderNo
     * @param request
     * @return
     */
    @RequestMapping("/topayWap")
    public String topayWap(String orderNo,String returnUrl,HttpServletRequest request,HttpServletResponse httpResponse) {

        logger.info("+--------------------------------------");
        logger.info("支付宝H5统一下单" + Calendar.getInstance().getTimeInMillis());

        logger.info("支付宝H5支付统一一下单" +Calendar.getInstance().getTime().toString()+"，订单号："+orderNo);
        logger.info("支付宝H5支付统一下单returnUrl" +returnUrl);

        Map<String,String> map = null;
        if(StringUtils.isNotBlank(orderNo)){
            map = getOrderService.getOrder(orderNo);
        }
        if(null ==  map) {
            return "/home/teahcerReward/payFail";
        }

        Map<String,String> resp = new HashMap<>();
        PayOrder payOrder = new PayOrder();
        payOrder.setBody(map.get("order_body"));
        payOrder.setOut_trade_no(orderNo);
        payOrder.setTotal_amount(String.valueOf(map.get("order_money")));
        // 设置同步地址
        payOrder.setTimeout_express("90m");
        payOrder.setReturnUrl(returnUrl);

        try {
            payOrder.setSpbill_create_ip(IPUtil.getIpAddress(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //正式环境需要注释
      // payOrder.setSpbill_create_ip("1.198.222.105");
        try {
            resp= payService.aliWapPayOrder(payOrder); //支付宝H5支付
            if(resp !=null && resp.containsKey("sign")) {
                request.setAttribute("sign",resp.get("sign"));


//                httpResponse.setContentType("text/html;charset=" + "utf-8");
//                httpResponse.getWriter().write(resp.get("sign"));//直接将完整的表单html输出到页面
//                httpResponse.getWriter().flush();
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付宝H5支付下单异常",e.getMessage());
            return "/fail";
        }
            return "/wap/aliwappay";
    }



    /**
     * 支付宝H5支付回调通知
     * @param request
     * @return
     */
    @RequestMapping("/wapnotify")
    @ResponseBody
    public String wapnotify(HttpServletRequest request) {
        logger.info("支付宝H5支付回调通知" + Calendar.getInstance().getTimeInMillis());
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
        Map<String, String> notifyMap = null;  // 转换成map
        try {
            notifyMap = WXPayUtil.xmlToMap(sb.toString());
            if (payService.wxAppCheck(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功

                String outTradeNo = notifyMap.get("out_trade_no");
                String transaction_id = notifyMap.get("transaction_id");

                logger.info("支付宝H5支付回调通知" +Calendar.getInstance().getTime().toString()+"，订单号："+outTradeNo);
                logger.info("支付宝H5支付回调通知" +Calendar.getInstance().getTime().toString()+"，交易订单号："+transaction_id);
                if(StringUtils.isNotBlank(outTradeNo)){
                    int reInt =  updateOrder( outTradeNo, transaction_id );
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


    /**
     * 更新订单
     * @param outTradeNo
     * @param transaction_id
     */
    public int updateOrder(String outTradeNo,String transaction_id ){
        String firstPlace = outTradeNo.substring(0,1);
        int reInt=0;
        if("a".equals(firstPlace)){ //课程订单结算订单编号

            reInt = courseService.modifyPayStatus(outTradeNo,transaction_id);

            //更新课程订单的交易订单号

        }else if("b".equals(firstPlace)){ //打赏教头结算订单编号

            reInt = userService.zfbReturnSuccessUpdateRewardOrderStatus(outTradeNo,transaction_id);

        }else if("c".equals(firstPlace)){//购买VIP会员订单编号

            reInt = rechargeMemberService.setPayFinishRechargeMemberByOrderOn(outTradeNo,transaction_id);

        }else if("d".equals(firstPlace)){ //充值银两

            reInt = rwService.setPayFinishRecharge(outTradeNo.substring(1),transaction_id);

        }
       return reInt;
    }


}
