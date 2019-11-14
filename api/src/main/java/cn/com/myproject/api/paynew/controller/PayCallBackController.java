package cn.com.myproject.api.paynew.controller;
import cn.com.myproject.api.config.MyWXPayConfig;
import cn.com.myproject.api.pay.controller.AliPayProp;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.paynew.IPayService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @auther LeiJia
 * @create 2017.10.27
 */
@RestController
@Component("PayCallBackNewController")
@RequestMapping(value = "/api/paycallback")
@Api(value="支付回调",tags = "支付")
public class PayCallBackController {
    private static final Logger logger = LoggerFactory.getLogger(PayCallBackController.class);

    @Autowired
    private AliPayProp aliPayProp;

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
    private IPayService payService;

    //购买凭证验证地址
    private static final String certificateUrl = "https://buy.itunes.apple.com/verifyReceipt";

    //测试的购买凭证验证地址
    private static final String certificateUrlTest = "https://sandbox.itunes.apple.com/verifyReceipt";

    /**
     * 支付宝app回调通知
     * @param request
     * @return
     */
    @RequestMapping("/alipay")
    @ResponseBody
    public String alipay(HttpServletRequest request){

        logger.info("支付宝app回调通知" + Calendar.getInstance().getTimeInMillis());
        Map<String,String> params = new HashMap<String,String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        try {

            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean flag = AlipaySignature.rsaCheckV1(params, aliPayProp.getAlipayPublicKey(), "utf-8","RSA2");
            if(flag) {
                //原支付请求的商户订单号
                String outTradeNo = params.get("out_trade_no");
                String transaction_id = params.get("trade_no"); //第三方交易订单号

                logger.info("支付宝app支付回调通知" +Calendar.getInstance().getTime().toString()+"，订单号："+outTradeNo);
                logger.info("支付宝app支付回调通知" +Calendar.getInstance().getTime().toString()+"，交易订单号："+transaction_id);
                if(StringUtils.isNotBlank(outTradeNo)){
                    int reInt=updateOrderStatus(  outTradeNo, transaction_id);

                    if(0 == reInt){
                        return "fail";
                    }
                }

                return "success";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 微信app支付通知
     * @param request
     * @return
     */
    @RequestMapping("/wxAppNotify")
    @ResponseBody
    public String wxWapNotify(HttpServletRequest request) {
        logger.info("微信app支付通知" + Calendar.getInstance().getTimeInMillis());
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
                String transaction_id = notifyMap.get("transaction_id"); //第三方交易订单号


                logger.info("新的微信app支付回调通知" +Calendar.getInstance().getTime().toString()+"，订单号："+outTradeNo);
                logger.info("新的微信app支付回调通知" +Calendar.getInstance().getTime().toString()+"，交易订单号："+transaction_id);
                if(StringUtils.isNotBlank(outTradeNo)){
                    int reInt=updateOrderStatus(  outTradeNo, transaction_id);
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
     * 修改订单状态并记录订单交易号
     * @param outTradeNo
     * @param transaction_id
     * @return
     */
    public int updateOrderStatus( String outTradeNo,String transaction_id){
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
        return reInt;
    }


    /**
     * andorid 和ios paypad 回调通知
     * @param request
     * @return
     */
    @PostMapping("/paypad")
    public String paypad(HttpServletRequest request){
        Enumeration en = request.getParameterNames();
        String str = "";
        while(en.hasMoreElements()){
            String paramName = (String)en.nextElement();
            String paramValue = request.getParameter(paramName);
            try {
                str = str + paramName + "=" + URLEncoder.encode(paramValue, "utf-8") + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        str+="cmd=_notify-validate";
        URL u = null;
        URLConnection uc = null;
        try {
            //在 Sandbox 情况下，设置：
            //u= new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
            u = new URL("https://www.paypal.com/cgi-bin/webscr");
            uc = u.openConnection();
            uc.setDoOutput(true);
            uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            PrintWriter pw = new PrintWriter(uc.getOutputStream());
            pw.println(str);
            pw.close();

            BufferedReader in= new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String res = in.readLine();
            in.close();

            String itemName = request.getParameter("item_name");
            String itemNumber = request.getParameter("item_number");
            String paymentStatus = request.getParameter("payment_status");
            String paymentAmount = request.getParameter("mc_gross");
            String paymentCurrency = request.getParameter("mc_currency");
            String txnId = request.getParameter("txn_id");
            String receiverEmail = request.getParameter("receiver_email");
            String payerEmail = request.getParameter("payer_email");

            String custom = request.getParameter("custom");
            String transaction_id = txnId; //交易号
            if(res.equals("VERIFIED")) {

                //检查付款状态
                //检查 txn_id 是否已经处理过
                //检查 receiver_email 是否是您的 PayPal 账户中的 EMAIL 地址
                //检查付款金额和货币单位是否正确
                //处理其他数据，包括写数据库

                if(!"Completed".equals(paymentStatus)){
                    return "fail";
                }


                if(StringUtils.isNotBlank(custom)){

                    int reInt=updateOrderStatus(  custom, transaction_id);

                    if(0 == reInt){
                        return "fail";
                    }else{
                        return "success";
                    }

                }


            }else if(res.equals("INVALID")) {
                //非法信息，可以将此记录到您的日志文件中以备调查
            }else {
                //处理其他错误
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * 重写X509TrustManager
     */
    private static TrustManager myX509TrustManager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }
    };

    /**
     * 接收iOS端发过来的购买凭证
     * @param orderId
     * @param receipt
     * @param chooseEnv
     */
    @PostMapping("/setIapCertificate")
    public Message setIapCertificate(String orderId, String receipt, String chooseEnv){
        logger.info("========1、开始执行内购回调=======");
        Message message = new Message();
        if(StringUtils.isEmpty(orderId) || StringUtils.isEmpty(receipt)){
            message = MessageUtils.getSuccess("参数为空");
        }
        String url = null;
        url = "1".equals(chooseEnv) == true?certificateUrl:certificateUrlTest;
        logger.info("========2、选择了内购执行路径=======url:"+url);
        final String certificateCode = receipt;
        if(StringUtils.isNotEmpty(certificateCode)){
            String result = sendHttpsCoon(url,certificateCode);
            com.alibaba.fastjson.JSONObject resultJson = JSON.parseObject(result);
            int status = Integer.valueOf(String.valueOf(resultJson.get("status")));
            logger.info("========3、获取了第一次路径的返回状态=======status:"+status);
            String transaction_id = String.valueOf(resultJson.get("transaction_id")); //待测试
            if(0==status){
                int reInt=updateOrderStatus(  orderId, transaction_id);
                logger.info("========4、返回状态成功后更新了订单状态=======返回结果:"+reInt);
                if(0 == reInt){
                    message = MessageUtils.getSuccess("支付失败！");
                }else{
                    message = MessageUtils.getSuccess("success");
                }
            }else{
                if("1".equals(chooseEnv)){
                    chooseEnv = "0";
                }else{
                    chooseEnv = "1";
                }
                logger.info("========5、返回状态失败后进入第二次路径的请求=======是否线上(0代表线下，1代表线上):"+chooseEnv);
                message = setIapCertificateTwo(orderId,receipt,chooseEnv);
            }
            logger.info(result);
        }else{
            logger.info("参数为空！");
            message = MessageUtils.getSuccess("参数为空！");
        }
        return message;

    }



    public Message setIapCertificateTwo(String orderId, String receipt, String chooseEnv){
        logger.info("========6、开始执行内购第二次回调=======");
        Message message = new Message();
        if(StringUtils.isEmpty(orderId) || StringUtils.isEmpty(receipt)){
            message = MessageUtils.getSuccess("参数为空");
        }
        String url = null;
        url = "1".equals(chooseEnv) == true?certificateUrl:certificateUrlTest;
        logger.info("========7、第二次选择了内购执行路径=======url:"+url);
        final String certificateCode = receipt;
        if(StringUtils.isNotEmpty(certificateCode)){
            String result = sendHttpsCoon(url,certificateCode);
            com.alibaba.fastjson.JSONObject resultJson = JSON.parseObject(result);
            int status = Integer.valueOf(String.valueOf(resultJson.get("status")));
            logger.info("========8、获取了第二次路径的返回状态=======status:"+status);
            String transaction_id = String.valueOf(resultJson.get("transaction_id")); //待测试
            if(0==status){
                String firstPlace = orderId.substring(0,1);
                int reInt=0;
                if("a".equals(firstPlace)){ //课程订单结算订单编号

                    reInt = courseService.modifyPayStatus(orderId,transaction_id);

                }else if("b".equals(firstPlace)){ //打赏教头结算订单编号

                    reInt = userService.zfbReturnSuccessUpdateRewardOrderStatus(orderId,transaction_id);

                }else if("c".equals(firstPlace)){//购买VIP会员订单编号

                    reInt = rechargeMemberService.setPayFinishRechargeMemberByOrderOn(orderId,transaction_id);

                }else if("d".equals(firstPlace)){ //充值银两

                    reInt = rwService.setPayFinishRecharge(orderId.substring(1),transaction_id);
                    logger.info("========9、返回状态成功后充值更新了订单状态=======返回结果:"+reInt);
                }
                if(0 == reInt){
                    message = MessageUtils.getSuccess("支付失败！");
                }else{
                    logger.info("========10、回调成功=======");
                    message = MessageUtils.getSuccess("success");
                }
            }else{
                message = MessageUtils.getSuccess("支付失败！");
            }
            logger.info(result);
        }else{
            logger.info("参数为空！");
            message = MessageUtils.getSuccess("参数为空！");
        }
        return message;

    }

    /**
     * 发送请求
     * @param url
     * @param code
     * @return
     */
    private String sendHttpsCoon(String url, String code){
        if(url.isEmpty()){
            return null;
        }
        try {
            //设置SSLContext
            SSLContext ssl = SSLContext.getInstance("SSL");
            ssl.init(null, new TrustManager[]{myX509TrustManager}, null);

            //打开连接
            HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();

            //设置套接工厂
            conn.setSSLSocketFactory(ssl.getSocketFactory());

            //加入数据
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type","application/json");

            JSONObject obj = new JSONObject();
            obj.put("receipt-data", code);

            BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
            buffOutStr.write(obj.toString().getBytes());
            buffOutStr.flush();
            buffOutStr.close();

            //获取输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line = reader.readLine())!= null){
                sb.append(line);
            }
            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }
}
