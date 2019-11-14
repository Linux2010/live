package cn.com.myproject.api.wap;

import cn.com.myproject.api.config.MyWXPayConfig;
import cn.com.myproject.api.paynew.controller.WXAppPayConfig;
import cn.com.myproject.api.paynew.controller.WXAppPayProp;
import cn.com.myproject.api.service.ICourseOrderService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.paynew.IPayService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.CommonUtil;
import cn.com.myproject.api.util.IPUtil;
import cn.com.myproject.api.wap.service.IGetOrderService;
import cn.com.myproject.paynew.entity.VO.PayOrder;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import cn.com.myproject.user.entity.PO.UserTeacherRewardOrder;
import com.github.wxpay.sdk.WXPay;
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
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by liyang-macbook on 2017/9/7.
 * update LeiJia 2017/10/30
 */
@Controller
@RequestMapping("/wap/wx")
public class WXPayController {

    private static final Logger logger = LoggerFactory.getLogger(WXPayController.class);


    @Autowired
    private WXJspPayProp wXJspPayProp;

    @Autowired
    private WXAppPayProp wXAppPayProp;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    @Autowired
    private ICourseOrderService iCourseOrderService;

    @Autowired
    private IPayService payService;


    @Autowired
    private IGetOrderService getOrderService;
    /**
     * 微信公众号授权
     * @param orderId
     * @param response
     * @param  returnUrl 回调地址
     * @return
     * @throws IOException
     */
    @RequestMapping("/auth")
    public String auth(String orderId,String returnUrl,HttpServletResponse response) throws IOException {
        //共账号及商户相关参数
        String appid = new WXJspPayConfig().getAppID();
        String backUri = "http://jtxyapp.doers.cn/wap/wx/topay";
        //授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
        //最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
        //比如 Sign = %3D%2F%CS%
        String orderNo=appid + String.valueOf(System.currentTimeMillis() / 1000);
        backUri = backUri+"?returnUrl="+returnUrl;
        backUri = backUri+"&orderNo="+orderId;
        //URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
        backUri = URLEncoder.encode(backUri);
        //scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=" + appid+
                    "&redirect_uri=" +
                    backUri+
                    "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        response.sendRedirect(url);
        return null;
    }


    /**
     * 微信公众号统一下单
     * @param orderNo
     * @param code
     * @param  returnUrl 回调地址
     * @param request
     * @return
     */
    @RequestMapping("/topay")
    public String topay(String orderNo, String code,String returnUrl,HttpServletRequest request) {

        logger.info("微信公众号统一下单" +Calendar.getInstance().getTime().toString()+"，订单号："+orderNo);
        logger.info("微信公众号统一下单returnUrl" +returnUrl);
        logger.info("+--------------------------------------");

        WXJspPayConfig config = new WXJspPayConfig();


        String openId = ""; //opneId
        String userId = "";

        BigDecimal tael = BigDecimal.ZERO;

        Map<String,String> map = null;

        if(StringUtils.isNotBlank(orderNo)){
            map = getOrderService.getOrder(orderNo);
        }

        if(map == null) {
            return "/fail";
        }

        openId = userService.selectThePublicOpenId(userId);
        if(StringUtils.isEmpty(openId) || StringUtils.isBlank(openId)) {

            String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + config.getAppID() + "&secret=" +wXJspPayProp.getSecret()+ "&code=" + code + "&grant_type=authorization_code";

            net.sf.json.JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
            if (null != jsonObject && jsonObject.containsKey("openid")) {
                openId = jsonObject.getString("openid");
                User user = new User();
                user.setUserId(map.get("user_id"));
                user.setOpenId(openId);
                try{
                    userService.updateThePublicOpenId(user); //记录用户的openId
                }catch(Exception e){
                    logger.error("记录用户的openId异常userService.updateThePublicOpenId(user)",e.getMessage());
                    e.printStackTrace();
                }
            } else {
                logger.info("获取统一支付接口的openid失败");
                return "/fail";
            }
        }

        Map<String,String> resp = new HashMap<>();
        PayOrder payOrder = new PayOrder();
        payOrder.setBody(map.get("order_body"));
        payOrder.setOut_trade_no(orderNo);
        payOrder.setTotal_amount(map.get("order_money"));
        payOrder.setSpbill_create_ip(request.getRemoteAddr());
        payOrder.setCode(openId);

        try {
            resp= payService.wxJspPayOrder(payOrder); //微信公众号支付
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS")) {
                SortedMap<String, String> finalpackage = new TreeMap<String, String>();
                String appid2 = config.getAppID();
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String nonceStr2 = resp.get("nonce_str");
                String prepay_id2 = "prepay_id="+resp.get("prepay_id");
                String packages = prepay_id2;
                finalpackage.put("appId", appid2);
                finalpackage.put("timeStamp", timestamp);
                finalpackage.put("nonceStr", nonceStr2);
                finalpackage.put("package", packages);
                finalpackage.put("signType", "MD5");
                /*
                 *商户秘钥
                 */
                config.setKey(wXJspPayProp.getBusinessSecretKey());
                String finalsign = WXPayUtil.generateSignature(finalpackage,config.getKey());
                request.setAttribute("appid",config.getAppID());
                request.setAttribute("timeStamp",timestamp);
                request.setAttribute("nonceStr",nonceStr2);
                request.setAttribute("package",packages);
                request.setAttribute("paySign",finalsign);
                request.setAttribute("returnUrl",returnUrl);

                return "/wap/pay";
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("",e);
        }
        logger.info("=====================================================================================");

        return "/fail";
    }



    /**
     * 微信公众号H5统一下单
     * @param orderNo
     * @param request
     * @param returnUrl  //返回页面
     * @return
     */
    @RequestMapping("/topayWap")
    public String topayWap(String orderNo,String returnUrl,HttpServletRequest request) {
        logger.info("微信公众号H5统一下单" + Calendar.getInstance().getTimeInMillis());

        logger.info("进入微信H5支付--------------------------------------");

        logger.info("微信H5支付统一一下单" +Calendar.getInstance().getTime().toString()+"，订单号："+orderNo);
        logger.info("微信H5支付统一下单returnUrl" +returnUrl);

        WXAppPayConfig config = new WXAppPayConfig();

        Map<String,String> map = null;
        if(StringUtils.isNotBlank(orderNo)){
            map = getOrderService.getOrder(orderNo);
        }

        if(map == null) {
            return "/fail";
        }

        Map<String,String> resp = new HashMap<>();
        PayOrder payOrder = new PayOrder();
        payOrder.setBody(map.get("order_body"));
        payOrder.setOut_trade_no(orderNo);
        payOrder.setTotal_amount(map.get("order_money"));

        try {
            payOrder.setSpbill_create_ip(IPUtil.getIpAddress(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  payOrder.setSpbill_create_ip("1.198.222.105");

        try {
            resp= payService.wxWapPayOrder(payOrder); //微信H5支付
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS")) {
                request.setAttribute("mweb_url",resp.get("mweb_url"));
                request.setAttribute("returnUrl",returnUrl);
                request.setAttribute("orderNo",orderNo);
                return "/wap/wappay";
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("",e);
        }
        return "/fail";
    }


    /**
     * 微信H5支付回调通知
     * @param request
     * @return
     */
    @RequestMapping("/wapnotify")
    @ResponseBody
    public String wapnotify(HttpServletRequest request) {
        logger.info("微信H5支付回调通知" + Calendar.getInstance().getTimeInMillis());
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

        WXAppPayConfig config = new WXAppPayConfig();
        config.setKey(wXAppPayProp.getBusinessSecretKey()); //商户号私钥
        Map<String, String> notifyMap = null;  // 转换成map
        try {
            notifyMap = WXPayUtil.xmlToMap(sb.toString());
            if (payService.wxAppCheck(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功

                String outTradeNo = notifyMap.get("out_trade_no");
                String transaction_id = notifyMap.get("transaction_id");

                logger.info("微信H5支付回调通知" +Calendar.getInstance().getTime().toString()+"，订单号："+outTradeNo);
                logger.info("微信H5支付回调通知" +Calendar.getInstance().getTime().toString()+"，交易订单号："+transaction_id);
                if(StringUtils.isNotBlank(outTradeNo)){
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
     * 微信公众号支付回调通知
     * @param request
     * @return
     */
    @RequestMapping("/notify")
    @ResponseBody
    public String paynotify(HttpServletRequest request) {
        logger.info("微信公众号支付回调通知" + Calendar.getInstance().getTimeInMillis());
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

        WXJspPayConfig config = new WXJspPayConfig();
        config.setKey(wXJspPayProp.getBusinessSecretKey()); //商户号私钥
        Map<String, String> notifyMap = null;  // 转换成map
        try {
            notifyMap = WXPayUtil.xmlToMap(sb.toString());
            if (payService.wxJspCheck(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功

                String outTradeNo = notifyMap.get("out_trade_no");
                String transaction_id = notifyMap.get("transaction_id");

                logger.info("微信公众号支付回调通知" +Calendar.getInstance().getTime().toString()+"，订单号："+outTradeNo);
                logger.info("微信公众号支付回调通知" +Calendar.getInstance().getTime().toString()+"，交易订单号："+transaction_id);
                if(StringUtils.isNotBlank(outTradeNo)){
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

    @RequestMapping("/topay1")
    @Deprecated
    public String topay1(String orderNo, String code, HttpServletRequest request) {

        logger.info("+--------------------------------------");

        MyWXPayConfig config = new MyWXPayConfig();
        WXPay wxpay = new WXPay(config);


        String openId ="";
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+config.getAppID()+"&secret="+config.getKey()+"&code="+code+"&grant_type=authorization_code";

        net.sf.json.JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
        if (null != jsonObject &&   jsonObject.containsKey("openid")) {
            openId = jsonObject.getString("openid");
        }else {
            //FIXME  转向错误界面
            logger.info("--------------------------------------");

            return "/fail";
        }
        String order_money="";
        String order_body="";
        BigDecimal tael = BigDecimal.ZERO;
        if(StringUtils.isNotBlank(orderNo)){
            String firstPlace = orderNo.substring(0,1);
            int reInt=0;
            if("a".equals(firstPlace)){ //课程订单结算订单编号
                order_body = "教头学院-购买课程";
                if(iCourseOrderService.searchCoMoneyByCno(orderNo) != null){
                    if(iCourseOrderService.searchCoMoneyByCno(orderNo).getTotalMoney() != null){
                        // 购买课程传递的是金额，单位是人民币
                        order_money = String.valueOf(iCourseOrderService.searchCoMoneyByCno(orderNo).getTotalMoney());
                    }
                }else{
                    logger.info("没有获取到购买课程支付金额");
                    return "/fail";
                }

            }else if("b".equals(firstPlace)){ //打赏教头结算订单编号
                UserTeacherRewardOrder order = new UserTeacherRewardOrder();
                order.setUserTeacherRewardOrderId(orderNo);
                order =  userService.getUserTeacherRewardOrder(order);
                if(order == null){
                    logger.info("没有获取到打赏教头支付学分");
                    return "/fail";
                }
                order_money = new BigDecimal(String.valueOf(order.getMoney())).divide(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP).toString(); //单位：银两/学分
                order_body="教头学院-用户打赏";

            }else if("c".equals(firstPlace)){//购买VIP会员订单编号
                RechargeMember rechargeMember = rechargeMemberService.selectByOrderOn(orderNo);//支付金额
                if(rechargeMember == null) {
                    logger.info("没有获取到支付金额");
                    return "/fail";
                }
                order_money =String.valueOf(rechargeMember.getRechargeMoney());
                order_body="教头学院-购买会员";

            }else if("d".equals(firstPlace)){ //充值银两
                UserRechargeWithraw rechargewith = rwService.selectByPrimaryKey(orderNo.substring(1));
                if(rechargewith == null){
                    logger.info("没有获取到充值银两");
                    return "/fail";
                }
                order_money =new BigDecimal(String.valueOf(rechargewith.getMoney())).divide(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                order_body="教头学院-充值积分";
            }
        }

        int money =  new BigDecimal(order_money).multiply(new BigDecimal("100")).intValue();
        Map<String, String> data = new HashMap<String, String>();
        data.put("body",order_body);
        data.put("out_trade_no", orderNo);
        data.put("fee_type", "CNY");
        data.put("total_fee",String.valueOf(money));
        data.put("spbill_create_ip", request.getRemoteAddr());

        //回调地址
        data.put("notify_url", "http://jtxyapp.doers.cn/wap/wx/notify");

        data.put("trade_type", "JSAPI");
        data.put("openid", openId);

        for(String str:data.keySet()) {

            logger.info(str+",,"+data.get(str));

        }
        try {
            config.setKey("fb59bafef3be4b4bba40456686f377d3");
            wxpay = new WXPay(config);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS")) {
                SortedMap<String, String> finalpackage = new TreeMap<String, String>();
                String appid2 = config.getAppID();
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String nonceStr2 = resp.get("nonce_str");
                String prepay_id2 = "prepay_id="+resp.get("prepay_id");
                String packages = prepay_id2;
                finalpackage.put("appId", appid2);
                finalpackage.put("timeStamp", timestamp);
                finalpackage.put("nonceStr", nonceStr2);
                finalpackage.put("package", packages);
                finalpackage.put("signType", "MD5");
                String finalsign = WXPayUtil.generateSignature(finalpackage,config.getKey());
                request.setAttribute("appid",config.getAppID());
                request.setAttribute("timeStamp",timestamp);
                request.setAttribute("nonceStr",nonceStr2);
                request.setAttribute("package",packages);
                request.setAttribute("paySign",finalsign);


                return "/wap/pay";
            }else {
                for(String str:resp.keySet()) {

                    logger.info(str+","+resp.get(str));

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("",e);
        }
        logger.info("=====================================================================================");

        return "/fail";
    }
    @RequestMapping("/notify1")
    @ResponseBody
    @Deprecated
    public String paynotify1(HttpServletRequest request) {
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
        config.setKey("fb59bafef3be4b4bba40456686f377d3");
        WXPay wxpay = new WXPay(config);

        Map<String, String> notifyMap = null;  // 转换成map
        try {
            notifyMap = WXPayUtil.xmlToMap(sb.toString());
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)  && notifyMap.get("result_code").equals("SUCCESS") ) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功

                String outTradeNo = notifyMap.get("out_trade_no");
                String transaction_id = notifyMap.get("transaction_id");

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
