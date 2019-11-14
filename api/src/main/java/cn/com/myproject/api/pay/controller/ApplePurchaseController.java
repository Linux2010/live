package cn.com.myproject.api.pay.controller;

import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;

/**
 * @auther CQC
 * @create 2017.9.18
 */
@RestController
@RequestMapping(value = "/api/applepurchase")
@Api(value="苹果内购支付",tags = "苹果内购支付")
public class ApplePurchaseController {


    @Autowired
    private AliPayProp aliPayProp;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    private static final Logger logger = LoggerFactory.getLogger(ApplePurchaseController.class);

    //购买凭证验证地址
    private static final String certificateUrl = "https://buy.itunes.apple.com/verifyReceipt";

    //测试的购买凭证验证地址
    private static final String certificateUrlTest = "https://sandbox.itunes.apple.com/verifyReceipt";

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
        logger.info("接收iOS端发过来的购买凭证:" + Calendar.getInstance().getTimeInMillis());
        logger.info("接收iOS端发过来的购买凭证" +Calendar.getInstance().getTime().toString()+"，订单号："+orderId);
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
            String transaction_id = String.valueOf(resultJson.get("original_transaction_id")); //待测试
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

                    logger.info("========4、返回状态成功后更新了订单状态=======返回结果:"+reInt);
                }
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
            String transaction_id = String.valueOf(resultJson.get("original_transaction_id")); //待测试
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
